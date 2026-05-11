package Client;

import java.net.*;
import java.io.*;

public class TCPClient {

    private Socket s = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private InterfazGraficaInicio vista;

    private String estado = null;

    public void setVista(InterfazGraficaInicio vista) {
        this.vista = vista;
    }

    public TCPClient() {
    }

    public void conectar() {
        try {
            int serverPort = 7896;
            s = new Socket("localhost", serverPort);
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());
            if (vista != null) {
                vista.infoConnect("Conectado");
            }
        } catch (UnknownHostException e) {
            if (vista != null) {
                vista.info("Socket:" + e.getMessage());
            }
        } catch (IOException e) {
            if (vista != null) {
                vista.info("IO:" + e.getMessage());
            }
        }
    }

    public void estadoCliente() throws IOException {
        out.writeUTF(estado);
    }

    public void mandarComando(Comando cadena) {
        if (out != null && in != null) {
            try {
                out.writeUTF(cadena.toString());
            } catch (EOFException e) {
                if (vista != null) {
                    vista.info("EOF:" + e.getMessage());
                }
            } catch (IOException e) {
                if (vista != null) {
                    vista.info("readline:" + e.getMessage());
                }
            }
        } else {
            if (vista != null) {
                vista.info("Error: No hay conexión con el servidor.");
            }
        }
    }

    public void cerrarConexion() {
        if (s != null) {
            try {
                s.close();
            } catch (IOException e) {
                if (vista != null) {
                    vista.info("close:" + e.getMessage());
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        TCPClient cliente = new TCPClient();
        InterfazGraficaInicio iu = new InterfazGraficaInicio(cliente);

        cliente.setVista(iu);
        iu.setVisible(true);
        
        cliente.conectar();
    }
}
