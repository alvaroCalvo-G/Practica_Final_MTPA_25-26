package Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorCSVColumna {

    public static String[] leerColumna(String rutaArchivo, int columna) {
        List<String> datos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length > columna && !partes[columna].trim().isEmpty()) {
                    datos.add(partes[columna].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }

        return datos.toArray(new String[0]);
    }
}