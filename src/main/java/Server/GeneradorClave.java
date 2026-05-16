package Server;

import Client.LectorCSV;
import java.util.List;

public class GeneradorClave {

    public static String generarClave(String rutaArchivo) {
        List<String[]> usuarios = LectorCSV.leerDatos(rutaArchivo);

        int maxId = 0;

        for (String[] fila : usuarios) {
            try {
                int id = Integer.parseInt(fila[1].trim());
                if (id > maxId) {
                    maxId = id;
                }
            } catch (NumberFormatException e) {

            }
        }

        return String.valueOf(maxId + 1);
    }
}
