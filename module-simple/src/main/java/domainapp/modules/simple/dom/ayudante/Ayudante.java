package domainapp.modules.simple.dom.ayudante;

import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.dom.cuadrilla.Cuadrilla;

import lombok.Getter;

import lombok.Setter;

import org.apache.causeway.applib.annotation.BookmarkPolicy;
import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.DomainObjectLayout;
import org.apache.causeway.applib.annotation.Editing;
import org.apache.causeway.applib.util.ObjectContracts;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import java.util.List;

@Entity @Table(
        schema = SimpleModule.SCHEMA,
        uniqueConstraints = {@UniqueConstraint(name = "Ayudante_dni_UNQ", columnNames= {"dni"})}
)

@NamedQueries({
        @NamedQuery(
                name = Ayudante.FIND,
                query = "SELECT "
                + "FROM domainapp.modules.simple.dom.ayudante.Ayudante "
                + "ORDER BY nombre ASC"
        ),
        @NamedQuery(
                name = Ayudante.FIND_BY_DNI,
                query = "SELECT "
                + "FROM domainapp.modules.simple.dom.ayudante.Ayudante"
                + " WHERE dni == :dni"
                + "ORDER BY dni ASC"
        )
})
@DomainObject(editing = Editing.DISABLED)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_ROOT)
@Getter @Setter
public class Ayudante implements Comparable<Ayudante> {

    static final String FIND = "Ayudante.find";
    static final String FIND_BY_DNI = "Ayudante.findByDni";

    private int dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private int telefono;
    private List<Cuadrilla> cuadrillaAyudante;

    public Ayudante() {
    }

    public Ayudante(int dni, String nombre, String apellido, String direccion, int telefono, List<Cuadrilla> cuadrillaAyudante) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuadrillaAyudante = cuadrillaAyudante;
    }

    @Override
    public int compareTo(final Ayudante other) {
        return ObjectContracts.compare(this, other, "dni");
    }
}
