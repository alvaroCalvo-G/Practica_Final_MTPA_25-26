package Client;

import Client.IU.InterfazGraficaInicio;
import Client.IU.InterfazGraficaLogin;
import Client.IU.InterfazGraficaPrincipal;
import Client.IU.InterfazGraficaRegistro;
import Client.Intefaces.Informacion;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TCPClient {

    private Socket s = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    String nombreUsuario = null;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombre) {
        this.nombreUsuario = nombre;
    }

    private InterfazGraficaInicio IUInicio;
    private InterfazGraficaLogin IULog;
    private InterfazGraficaRegistro IUReg;
    private InterfazGraficaPrincipal IUMain;

    public void setIULog(InterfazGraficaLogin IULog) {
        this.IULog = IULog;
    }

    public void setIUReg(InterfazGraficaRegistro IUReg) {
        this.IUReg = IUReg;
    }

    public void setIUMain(InterfazGraficaPrincipal IUMain) {
        this.IUMain = IUMain;
    }

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

    private void procesarRespuesta(String respuesta) {
        switch (respuesta) {
            case "FORMATO_INVALIDO" -> {
                notificarTodos("FORMATO_INVALIDO");
            }
            case "NO_AUTENTICADO" -> {
                notificarTodos("Es necesario registrarse o iniciar sesion");
            }
            case "LOGIN_OK" -> {
                if (IULog != null) {
                    IULog.statusLog("Acceso correcto");
                    IULog.greenInfoLabel();
                    IULog.logOk();
                }
            }
            case "LOGIN_INCORRECTO" -> {
                if (IULog != null) {
                    IULog.statusLog("Login incorrecto");
                    IULog.redInfoLabel();
                }
            }
            case "USUARIO_YA_EXISTE" -> {
                IUReg.infoBannerChange("El nombre de usuario ya está en uso.");
                IUReg.infoBannerRed();
            }
            default -> {
                if (respuesta.startsWith("REG_OK")) {
                    String clave = respuesta.split("\\|")[1];
                    IUReg.infoBannerChange("Registro exitoso. Tu clave es: " + clave);
                    IUReg.infoBannerGreen();
                } else if (respuesta.startsWith("LIST_ROOMS_OK")) {
                    String[] partes = respuesta.split("\\|");
                    String[] salones = partes[1].split("/");
                    if (IUMain != null) {
                        IUMain.actualizarSalones(salones);
                    }
                } else if (respuesta.startsWith("JOIN_OK")) {
                    String[] partes = respuesta.split("\\|");
                    if (IUMain != null && partes.length > 1) {
                        IUMain.unirseASalon(partes[1]);
                    }
                } else {
                    notificarTodos("FORMATO_INVALIDO: " + respuesta);
                }

            }
        }
    }

    public static void main(String args[]) throws IOException {
        TCPClient cliente = new TCPClient();

        InterfazGraficaInicio iu = new InterfazGraficaInicio(cliente);

        iu.setVisible(true);

        cliente.conectar();
    }
}
