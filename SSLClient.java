import javax.net.ssl.*;
import java.io.*;
import java.util.Scanner;
import java.security.KeyStore;

public class SSLClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8888;

        try {
            // Load the keystore file
            KeyStore keyStore = KeyStore.getInstance("JKS");
            InputStream keyStoreInput = new FileInputStream("keystore.jks");
            char[] keyStorePassword = "123456".toCharArray();
            keyStore.load(keyStoreInput, keyStorePassword);

            // Create SSL context
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(keyStore);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

            // Create SSL socket factory
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            SSLSocket socket = (SSLSocket) socketFactory.createSocket(host, port);

            // Send and receive data
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a message to send to the server: ");
            String message = scanner.nextLine();

            out.println(message);
            String response = in.readLine();
         
            System.out.println("Server response: " + response);
            // Close connections
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}