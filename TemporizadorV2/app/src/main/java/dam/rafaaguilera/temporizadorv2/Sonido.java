package dam.rafaaguilera.temporizadorv2;

public class Sonido {
    private String nombre;
    private int icono;
    private int sonidoId;

    public Sonido(String nombre, int icono, int sonidoId) {
        this.nombre = nombre;
        this.icono = icono;
        this.sonidoId = sonidoId;

    }

    public String getNombre() {
        return nombre;
    }

    public int getIcono() {
        return icono;
    }

    public int getSonidoId() {
        return sonidoId;
    }
}
