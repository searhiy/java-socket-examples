package sockets;

import javax.net.ssl.SSLServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * <p>
 *
 * @author Serhii Kartashov
 * @since 1.0.0
 */
public class Server {

    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.keyStore", "sk.store");
        System.setProperty("javax.net.ssl.keyStorePassword", "password");
        ServerSocket serverSocket = ((SSLServerSocketFactory)SSLServerSocketFactory.getDefault()).createServerSocket(4444);
        System.out.println("Server Up and ready for connections....");
        while (true){
            new ServerThread(serverSocket.accept()).start();
        }
    }

}
