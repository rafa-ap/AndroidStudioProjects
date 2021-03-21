package dam.rafaaguilera.tecniled.DatosLocales;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Proyecto implements Serializable {
    private int id;
    private String nombre;
    private String fecha;
    private String observaciones;
    private List<Grupo> listaGrupos;

    public Proyecto(int id, String nombre, String fecha, String observaciones) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.listaGrupos = new ArrayList<Grupo>();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.nombre);
        hash = 79 * hash + Objects.hashCode(this.fecha);
        hash = 79 * hash + Objects.hashCode(this.observaciones);
        hash = 79 * hash + Objects.hashCode(this.listaGrupos);
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
        final Proyecto other = (Proyecto) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        if (!Objects.equals(this.listaGrupos, other.listaGrupos)) {
            return false;
        }
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public List<Grupo> getListaGrupos() {
        return listaGrupos;
    }

    public int getId() {
        return id;
    }
}
