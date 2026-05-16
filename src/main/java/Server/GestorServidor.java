package Server;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class GestorServidor {

    private static List<Connection> conexiones = Collections.synchronizedList(new ArrayList<>());

    public static void agregarConexion(Connection c) {
        conexiones.add(c);
    }

    public static void eliminarConexion(Connection c) {
        conexiones.remove(c);
    }

    public static int getConexiones() {
        return conexiones.size();
    }

    public static void mostrarInfo() {
        System.out.println("Usuarios conectados: " + conexiones.size());
    }
}
