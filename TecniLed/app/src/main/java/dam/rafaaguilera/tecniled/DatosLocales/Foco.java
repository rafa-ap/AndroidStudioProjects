package dam.rafaaguilera.tecniled.DatosLocales;

import java.io.Serializable;
import java.util.Objects;

public class Foco implements Serializable {
    int id;
    private String nombre;
    private String marca;
    private String direccion;
    private String canal;
    private String dmx;

    public Foco(int id, String nombre, String marca, String direccion, String canal, String dmx) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.direccion = direccion;
        this.canal = canal;
        this.dmx = dmx;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + Objects.hashCode(this.marca);
        hash = 29 * hash + Objects.hashCode(this.direccion);
        hash = 29 * hash + Objects.hashCode(this.canal);
        hash = 29 * hash + Objects.hashCode(this.marca);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Foco other = (Foco) obj;

        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        if (!Objects.equals(this.canal, other.canal)) {
            return false;
        }
        if (!Objects.equals(this.dmx, other.dmx)) {
            return false;
        }

        return true;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }


    public String getDireccion() {
        return direccion;
    }

    public String getCanal() {
        return canal;
    }

    public String getDmx() {
        return dmx;
    }
}
