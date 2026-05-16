package Client;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EscritorCSV {

    public static void escribirDatos(String rutaArchivo, List<String[]> datos, boolean añadir) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, añadir))) {
            for (String[] fila : datos) {
                bw.write(String.join(";", fila));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }
}