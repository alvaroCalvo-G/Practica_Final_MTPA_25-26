package Client;

import java.net.*;
import java.io.*;

public class TCPClient {

    private Socket s = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public TCPClient() {
        try {
            int serverPort = 7896;
            s = new Socket("localhost", serverPort);
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
            System.out.println("Conectado al servidor.");
        } catch (UnknownHostException e) {
            System.out.println("Socket:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        }
    }

    public void mandarComando(Comando cadena) {
        if (out != null && in != null) {
            try {
                out.writeUTF(cadena.toString());

                String data = in.readUTF();
                System.out.println("Received: " + data);

            } catch (EOFException e) {
                System.out.println("EOF:" + e.getMessage());
            } catch (IOException e) {
                System.out.println("readline:" + e.getMessage());
            }
        } else {
            System.out.println("Error: No hay conexión con el servidor.");
        }
    }
    
    public void cerrarConexion() {
        if (s != null) {
            try {
                s.close();
            } catch (IOException e) {
                System.out.println("close:" + e.getMessage());
            }
        }
    }
    
    public static void main(String args[]) {
        TCPClient clienteLogica = new TCPClient();
        
        InterfazGraficaCliente IU = new InterfazGraficaCliente(clienteLogica);
        IU.setVisible(true);
    }
}
