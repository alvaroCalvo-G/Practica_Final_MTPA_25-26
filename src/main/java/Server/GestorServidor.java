package Server;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class GestorServidor {

    private static List<Connection> conexiones = Collections.synchronizedList(new ArrayList<>());
    private static java.util.Map<String, List<Connection>> salonesActivos = new java.util.concurrent.ConcurrentHashMap<>();

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
        System.out.println("INFO SERVIDOR");
        System.out.println("Usuarios conectados: " + conexiones.size());
        salonesActivos.forEach((salon, miembros) -> System.out.println("Salon " + salon + ": " + miembros.size() + " usuarios"));
    }

    public static void unirseASalon(String salon, Connection c) {
        salonesActivos.computeIfAbsent(salon, k -> Collections.synchronizedList(new ArrayList<>())).add(c);
    }

    public static void salirDeSalon(String salon, Connection c) {
        if (salonesActivos.containsKey(salon)) {
            salonesActivos.get(salon).remove(c);
        }
    }

    public static List<Connection> getMiembrosSalon(String salon) {
        return salonesActivos.getOrDefault(salon, new ArrayList<>());
    }
}
