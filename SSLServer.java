import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class SSLServer {
    public static void main(String[] args) {
        int port = 8888;

        try {
            // Load the keystore file
            KeyStore keyStore = KeyStore.getInstance("JKS");
            InputStream keyStoreInput = new FileInputStream("keystore.jks");
            char[] keyStorePassword = "123456".toCharArray();
            keyStore.load(keyStoreInput, keyStorePassword);

            // Create SSL context
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, keyStorePassword);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), null, null);

            // Create SSL socket factory
            SSLServerSocketFactory socketFactory = sslContext.getServerSocketFactory();
            SSLServerSocket serverSocket = (SSLServerSocket) socketFactory.createServerSocket(port);

            System.out.println("Server started on port " + port);

            while (true) {
                // Accept client connection
                SSLSocket socket = (SSLSocket) serverSocket.accept();

                // Handle client request
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String request = in.readLine();
                System.out.println("Client request: " + request);
     
                
                out.println("Hello"+ request);

                // Close connections
                out.close();
                in.close();
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}