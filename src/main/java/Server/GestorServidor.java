package Server;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class GestorServidor {

    private static List<Connection> conexiones = Collections.synchronizedList(new ArrayList<>());
    private static java.util.Map<String, List<Connection>> salonesActivos = new java.util.concurrent.ConcurrentHashMap<>();
    private static java.util.Map<String, Integer> mensajesPorSalon = new java.util.concurrent.ConcurrentHashMap<>();

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

        System.out.println("-- Usuarios por salon --");
        if (salonesActivos.isEmpty()) {
            System.out.println("  Sin salones activos");
        } else {
            salonesActivos.forEach((salon, miembros) -> System.out.println("  " + salon + ": " + miembros.size() + " usuarios"));
        }

        System.out.println("-- Mensajes por salon --");
        if (mensajesPorSalon.isEmpty()) {
            System.out.println("  Sin mensajes enviados");
        } else {
            mensajesPorSalon.forEach((salon, total) -> System.out.println("  " + salon + ": " + total + " mensajes"));
        }
    }

    public static void unirseASalon(String salon, Connection c) {
        salonesActivos.computeIfAbsent(salon, k -> Collections.synchronizedList(new ArrayList<>())).add(c);
    }

    public static void salirDeSalon(String salon, Connection c) {
        if (salonesActivos.containsKey(salon)) {
            salonesActivos.get(salon).remove(c);
        }
    }

    public static void contarMensaje(String salon) {
        mensajesPorSalon.merge(salon, 1, Integer::sum);
    }

    public static List<Connection> getMiembrosSalon(String salon) {
        return salonesActivos.getOrDefault(salon, new ArrayList<>());
    }
}
