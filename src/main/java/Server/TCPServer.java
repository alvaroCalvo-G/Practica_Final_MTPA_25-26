package Server;

import Client.EscritorCSV;
import Client.LectorCSV;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TCPServer {

    public static void main(String args[]) {
        try {
            int cliente1 = 7896;

            ServerSocket listenSocket1 = new ServerSocket(cliente1);

            while (true) {
                Socket clientSocket = listenSocket1.accept();
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
    Estados estado = Estados.CONECTADO;

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
                System.out.println("Recibido: " + instrucciones);

                String[] data = instrucciones.split("\\|");

                if (data.length >= 4) {
                    String comando = data[0];
                    String origen = data[1];
                    String destino = data[2];
                    String datos = data[3];

                    switch (estado) {
                        case CONECTADO -> {
                            switch (comando) {
                                case "LOGIN" -> {
                                    String[] UandP = datos.split("/");

                                    if (UandP.length == 2) {
                                        String usuario = UandP[0];
                                        String password = UandP[1];

                                        String ruta = "usuarios.csv";
                                        List<String[]> listaDeDatos = LectorCSV.leerDatos(ruta);

                                        boolean loginExitoso = false;

                                        for (String[] fila : listaDeDatos) {
                                            if (fila.length < 2) {
                                                continue;
                                            }

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
                                            estado = estado.AUTENTIFICADO;
                                        } else {
                                            System.out.println("Login fallido para: " + usuario);
                                            out.writeUTF("LOGIN_INCORRECTO");
                                        }
                                    } else {
                                        System.out.println("Error: formato usuario/contraseña no válido.");
                                        out.writeUTF("FORMATO_INVALIDO");
                                    }
                                }
                                case "REG" -> {
                                    String userName = datos;

                                    String ruta = "usuarios.csv";
                                    List<String[]> usuarios = LectorCSV.leerDatos(ruta);

                                    boolean usuarioExiste = false;

                                    for (String[] fila : usuarios) {
                                        if (fila[0].trim().equals(userName)) {
                                            usuarioExiste = true;
                                            break;
                                        }
                                    }

                                    if (usuarioExiste) {
                                        out.writeUTF("USUARIO_YA_EXISTE");
                                    } else {
                                        String clave = GeneradorClave.generarClave(ruta);
                                        List<String[]> nuevoUsuario = new ArrayList<>();
                                        nuevoUsuario.add(new String[]{userName, clave});
                                        EscritorCSV.escribirDatos(ruta, nuevoUsuario, true);
                                        out.writeUTF("REG_OK|" + clave);
                                    }
                                }
                                default -> {
                                    out.writeUTF("NO_AUTENTICADO");
                                }
                            }
                        }
                        case AUTENTIFICADO -> {
                            switch (comando) {
                                case "LOGOUT" -> {
                                    
                                }

                            }
                        }
                    }
                } else {
                    System.out.println("Error: La información recibida está incompleta.");
                    out.writeUTF("FORMATO_INVALIDO");
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
