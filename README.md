# This code implements a simple SSL client that connects to a secure server and sends and receives data over an encrypted connection.

# Import statements:
- javax.net.ssl.*: Provides classes for secure communication using SSL and TLS protocols.
- java.io.*: Provides classes for input and output operations.
- java.util.Scanner: Used for reading user input from the console.
- java.security.KeyStore: Used to manage keys and certificates.
# Main method:
  - Defines the host and port of the server to connect to.
  - Example:Host:127.0.0.1 and Port:8080.
# Keystore setup:
  - Loads a keystore file (keystore.jks) containing the client's certificate and private key.
  - Creates a TrustManagerFactory to trust the server's certificate.
  - Creates an SSLContext using the loaded keystore and trust manager.

# Use This Command on Keystore Generator:
  - "keytool -genkey -alias server -validity 365 -keyalg RSA -keystore keystore"
# Data communication:
  - Creates a PrintWriter to send data to the server.
  - Creates a BufferedReader to read data from the server.
  - Prompts the user for a message to send.
  - Sends the message to the server using PrintWriter.
  - Reads the server's response using BufferedReader.
  - Prints the server's response to the console.
# Closing connections:
 - Closes all resources (PrintWriter, BufferedReader, and SSLSocket) to release resources and ensure a clean connection termination.
