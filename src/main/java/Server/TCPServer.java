package Server;

import Client.LectorCSV;
import java.net.*;
import java.io.*;
import java.util.List;

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
                //Inyecion de dependencias
                String instrucciones = in.readUTF();
                System.out.println("Recibido: " + instrucciones);

                String[] data = instrucciones.split("\\|");

                if (data.length >= 4) {
                    String comando = data[0];
                    String origen = data[1];
                    String destino = data[2];
                    String datos = data[3];

                    if (comando.equals("LOGIN")) {

                        String[] UandP = datos.split("/");

                        if (UandP.length == 2) {
                            String usuario = UandP[0];
                            String password = UandP[1];

                            System.out.println("Usuario: " + usuario);
                            System.out.println("Password: " + password);

                            String ruta = "usuarios.csv";
                            List<String[]> listaDeDatos = LectorCSV.leerDatos(ruta);

                            boolean loginExitoso = false;

                            for (String[] fila : listaDeDatos) {
                                String userCSV = fila[0].trim();
                                String passCSV = fila[1].trim();

                                if (userCSV.equals(usuario) && passCSV.equals(password)) {
                                    loginExitoso = true;
                                    break;
                                }
                            }

                            if (loginExitoso) {
                                System.out.println("Login correcto para: " + usuario);
                                out.writeUTF("LOGIN_OK");
                            } else {
                                System.out.println("Login fallido para: " + usuario);
                                out.writeUTF("LOGIN_INCORRECTO");
                            }
                        } else {
                            System.out.println("Error: El formato de usuario/contraseña no es válido.");
                        }
                    }
                } else {
                    System.out.println("Error: La trama recibida está incompleta.");
                }
            }
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
            }
        }
    }
}
