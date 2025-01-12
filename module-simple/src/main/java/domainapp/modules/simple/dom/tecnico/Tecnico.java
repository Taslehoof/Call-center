package domainapp.modules.simple.dom.tecnico;

import domainapp.modules.simple.dom.cuadrilla.Cuadrilla;

import lombok.Getter;
import lombok.Setter;

import lombok.val;

import org.apache.causeway.applib.util.ObjectContracts;

import java.util.List;

@Getter @Setter
public class Tecnico implements Comparable<Tecnico> {

    private String dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private int telefono;
    private List<Cuadrilla> cuadrillaTecnico;

    public Tecnico(String dni, String nombre, String apellido, String direccion, int telefono, List<Cuadrilla> cuadrillaTecnico) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuadrillaTecnico = cuadrillaTecnico;
    }

    public Tecnico() {
    }

    public static Tecnico withName(final String nombre){
        val tecnico = new Tecnico();
        tecnico.setNombre(nombre);
        return tecnico;
    }

    @Override
    public int compareTo(final Tecnico other) {
        return ObjectContracts.compare(this, other, dni);
    }
}
