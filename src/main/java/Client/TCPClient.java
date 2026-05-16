package Client;

import Client.IU.InterfazGraficaInicio;
import Client.IU.InterfazGraficaLogin;
import Client.Intefaces.Informacion;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TCPClient {

    private Socket s = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    private InterfazGraficaInicio IUInicio;
    private InterfazGraficaLogin IULog;

    private List<Informacion> generales = new ArrayList<>();

    public void addListener(Informacion listener) {
        generales.add(listener);
    }

    public void removeListener(Informacion listener) {
        generales.remove(listener);
    }

    private void notificarTodos(String mensaje) {
        for (Informacion I : generales) {
            I.ventanaEmergente(mensaje);
        }
    }

    public TCPClient() {
    }

    public void conectar() {
        try {
            int serverPort = 7896;

            s = new Socket("localhost", serverPort);
            in = new DataInputStream(s.getInputStream());
            out = new DataOutputStream(s.getOutputStream());

            iniciarEscucha();

        } catch (UnknownHostException e) {
            notificarTodos("Socket:" + e.getMessage());
        } catch (IOException e) {
            notificarTodos("IO:" + e.getMessage());
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
                    notificarTodos("Conexión con el servidor terminada.");
                }
            }
        });
        hiloEscucha.start();
    }

    private void procesarRespuesta(String respuesta) {
        System.out.println("Mensaje del servidor: " + respuesta);

        switch (respuesta) {
            case "LOGIN_OK":
                if (IULog != null) {
                    IULog.statusLog("Acceso correcto");
                    IULog.greenInfoLabel();
                    IULog.logOk();
                }
                break;
            case "LOGIN_INCORRECTO":
                if (IULog != null) {
                    IULog.statusLog("Login incorrecto");
                    IULog.redInfoLabel();
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

    public void mandarComando(Comando cadena) {
        if (out != null && in != null) {
            try {
                out.writeUTF(cadena.toString());
            } catch (EOFException e) {
                notificarTodos("EOF:" + e.getMessage());
            } catch (IOException e) {
                notificarTodos("readline:" + e.getMessage());
            }
        } else {
            notificarTodos("Error: No hay conexión con el servidor.");
        }
    }

    public void cerrarConexion() {
        if (s != null) {
            try {
                s.close();
            } catch (IOException e) {
                notificarTodos("close:" + e.getMessage());
            }
        }
    }

    public static void main(String args[]) throws IOException {
        TCPClient cliente = new TCPClient();

        InterfazGraficaInicio iu = new InterfazGraficaInicio(cliente);
        InterfazGraficaLogin log = new InterfazGraficaLogin(cliente);

        cliente.addListener(iu);
        cliente.addListener(log);

        iu.setVisible(true);

        cliente.conectar();
    }
}
