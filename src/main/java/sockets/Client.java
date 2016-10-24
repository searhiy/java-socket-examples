package sockets;

import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * <p>
 *
 * @author Serhii Kartashov
 * @since 1.0.0
 */
public class Client {

    public static void main(String[] args) throws IOException {
        System.setProperty("javax.net.ssl.trustStore", "sk.store");
        System.setProperty("javax.net.ssl.keyStorePassword", "password");
        Socket socket = ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket("localhost", 4444);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("please enter a username: ");
        printWriter.println(bufferedReader1.readLine());
        String message = null;
        while (true){
            System.out.println("please enter a message to send to server...");
            message = bufferedReader1.readLine();
            if (message.equals("quit")){
                printWriter.println(message);
                socket.close();
                break;
            }
            printWriter.println(message);
            System.out.println("Message reply from server...");
            System.out.println(bufferedReader.readLine());
        }
    }
}
