package domainapp.modules.simple.dom.tecnico;

import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.dom.cuadrilla.Cuadrilla;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

import lombok.val;

import org.apache.causeway.applib.annotation.BookmarkPolicy;
import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.DomainObjectLayout;
import org.apache.causeway.applib.annotation.Editing;
import org.apache.causeway.applib.annotation.Property;
import org.apache.causeway.applib.annotation.Title;
import org.apache.causeway.applib.util.ObjectContracts;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
@Table(
        schema = SimpleModule.SCHEMA,
        uniqueConstraints = {@UniqueConstraint(name ="Tecnico_dni_UNQ", columnNames = {"dni"})}
)
@DomainObject(editing = Editing.DISABLED)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_ROOT)
@Getter @Setter
public class Tecnico implements Comparable<Tecnico> {

    @Property
    @Column(nullable = false, length = 40)
    @Title
    @Id
    private String dni;

    @Property
    @Column(nullable = false, length = 40)
    @Title
    private String nombre;

    @Property
    @Column(nullable = false, length = 40)
    private String apellido;

    @Property
    @Column(nullable = false, length = 40)
    private String direccion;

    @Property
    @Column(nullable = false, length = 40)
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
