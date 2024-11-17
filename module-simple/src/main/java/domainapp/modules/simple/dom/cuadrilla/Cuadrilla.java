package domainapp.modules.simple.dom.cuadrilla;

import domainapp.modules.simple.SimpleModule;

import domainapp.modules.simple.dom.ayudante.Ayudante;
import domainapp.modules.simple.dom.reclamo.Reclamo;

import domainapp.modules.simple.dom.tecnico.Tecnico;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.apache.causeway.applib.annotation.BookmarkPolicy;
import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.DomainObjectLayout;
import org.apache.causeway.applib.annotation.Editing;
import org.apache.causeway.applib.util.ObjectContracts;

import javax.persistence.metamodel.IdentifiableType;

import java.util.List;

@Entity
@Table(
        schema = SimpleModule.SCHEMA,
        uniqueConstraints = {@UniqueConstraint(name = "Cuadrilla_nombre_UNQ", columnNames = {"nombre"})}
)
@DomainObject(editing = Editing.DISABLED)
//@PersistenceCapable(identityType = IdentityType.DATASTORE, schema = SimpleModule.SCHEMA)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_ROOT)
@ToString(onlyExplicitlyIncluded = true)
@Getter @Setter
public class Cuadrilla implements Comparable<Cuadrilla>{

    @Column(allowsNull ="")
    private String nombre;
    private Tecnico tecnico;
    private Ayudante ayudante;
    private List<Reclamo> reclamosAsignados;

    public Cuadrilla(){}

    public Cuadrilla(String nombre, Tecnico tecnico, Ayudante ayudante, List<Reclamo> reclamosAsignados){
        this.nombre = nombre;
        this.tecnico = tecnico;
        this.ayudante = ayudante;
        this.reclamosAsignados = reclamosAsignados;
    }

    public static Cuadrilla create(final String nombre, final Tecnico tecnico, final Ayudante ayudante) {
        Cuadrilla cuadrilla = new Cuadrilla();
        cuadrilla.setNombre(nombre);
        cuadrilla.setTecnico(tecnico);
        cuadrilla.setAyudante(ayudante);
        return cuadrilla;
    }

    public static Cuadrilla update(String nombre, Tecnico tecnico, Ayudante ayudante) {
        Cuadrilla cuadrilla = new Cuadrilla();
        cuadrilla.setNombre(nombre);
        cuadrilla.setTecnico(tecnico);
        cuadrilla.setAyudante(ayudante);
        return cuadrilla;
    }

    @Override
    public int compareTo(Cuadrilla other) {
        return ObjectContracts.compare(this,other,"nombre");
    }
}
