package Server;

import java.net.*;
import java.io.*;

public class TCPServer {

    public static void main(String args[]) {
        try {
            int cliente1 = 7896;
            ServerSocket listenSocket = new ServerSocket(cliente1);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }
    }
}

class Connection extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void run() {
        try {
            while (true) {
                String instrucciones = in.readUTF();
                System.out.println(instrucciones);

                String[] data = null;

                String comando = data[0];
                String origen = data[1];
                String destino = data[2];
                String datos = data[3];

                if (comando.equals("LOGIN")) {
                    String[] UandP = null;

                    String usuario = UandP[0];
                    String password = UandP[1];

                }

            }
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {/*close failed*/
            }
        }
    }
}
