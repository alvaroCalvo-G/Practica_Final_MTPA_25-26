package Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV {

    public static List<String[]> leerDatos(String rutaArchivo) {
        List<String[]> datosExtraidos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.split(";");
                datosExtraidos.add(columnas);
            }
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }

        return datosExtraidos;
    }
}