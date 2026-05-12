package Client;

import java.net.*;
import java.io.*;

public class TCPClient {

    private Socket s = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private InterfazGraficaInicio vista;
    private InterfazGraficaLogin IULog;

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
            if (IULog != null) {
                IULog.infoConnect("Conectado");
            }

            iniciarEscucha();

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

    public void iniciarEscucha() {
        Thread hiloEscucha = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String respuesta = in.readUTF();

                        procesarRespuesta(respuesta);
                    }
                } catch (IOException e) {
                    if (vista != null) {
                        vista.info("Conexión con el servidor terminada.");
                    }
                }
            }
        });
        hiloEscucha.start();
    }

    public void setIULog(InterfazGraficaLogin IULog) {
        this.IULog = IULog;
    }

    private void procesarRespuesta(String respuesta) {
        System.out.println("Mensaje del servidor: " + respuesta);

        switch (respuesta) {
            case "LOGIN_OK":
                if (IULog != null) {
                    IULog.statusLog("ACCESO CONCEDIDO: " + respuesta);
                }
                break;
            case "LOGIN_INCORRECTO":
                if (IULog != null) {
                    IULog.statusLog("FALLO: " + respuesta);
                }
                break;

            default:
                System.out.println("Comando del servidor no reconocido: " + respuesta);
                if (IULog != null) {
                    IULog.statusLog("Respuesta: " + respuesta);
                }
                break;
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
