package Server;

import Client.LectorCSV;
import java.util.List;

public class GeneradorClave {

    public static String generarClave(String rutaArchivo, String userName) {
        int dia = java.time.LocalDate.now().getDayOfMonth();
        int clave = dia + 1 + userName.length();

        List<String[]> usuarios = LectorCSV.leerDatos(rutaArchivo);

        boolean repetida = true;
        while (repetida) {
            repetida = false;
            for (String[] fila : usuarios) {
                if (fila.length < 2) {
                    continue;
                }
                try {
                    int claveExistente = Integer.parseInt(fila[1].trim());
                    if (claveExistente == clave) {
                        clave++;
                        repetida = true;
                        break;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }

        return String.valueOf(clave);
    }
}
