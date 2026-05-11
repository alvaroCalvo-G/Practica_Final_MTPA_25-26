package Client;

public class Comando {
    private String comando;
    private String origen;
    private String destino;
    private String datos;

    public Comando(String comando, String origen, String destino, String datos) {
        this.comando = comando;
        this.origen = origen;
        this.destino = destino;
        this.datos = datos;
    }

    public String getComando() {
        return comando;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getDatos() {
        return datos;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }

    @Override
    public String toString() {
        return comando + "|" + origen + "|" + destino + "|" + datos;
    }
}
 