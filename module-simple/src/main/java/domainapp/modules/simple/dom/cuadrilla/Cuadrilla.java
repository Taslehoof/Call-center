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
import org.apache.causeway.applib.annotation.Property;
import org.apache.causeway.applib.util.ObjectContracts;

import org.datanucleus.metadata.IdentityType;

import org.springframework.data.annotation.Persistent;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import java.util.List;

@Entity
@Table(
        schema = SimpleModule.SCHEMA,
        uniqueConstraints = {@UniqueConstraint(name = "Cuadrilla_nombre_UNQ", columnNames = {"nombre"})}
)
@NamedQueries({
        @NamedQuery(
                name = Cuadrilla.FIND,
                query = "SELECT "
                + "FROM domainapp.modules.simple.dom.cuadrilla.Cuadrilla"
                + "ORDER BY nombre ASC"),
        @NamedQuery(
                name = Cuadrilla.FIND_BY_NOMBRE,
                query = "SELECT "
                        + "FROM domainapp.modules.simple.dom.cuadrilla.Cuadrilla"
                        + "WHERE nombre == :nombre"),
        @NamedQuery(
                name = Cuadrilla.FIND_BY_TECNICO,
                query = "SELECT "
                        + "FROM domainapp.modules.simple.dom.cuadrilla.Cuadrilla"
                        + "WHERE tecnico == :tecnico"
                        + "ORDER BY nombre ASC"),
        @NamedQuery(
                name = Cuadrilla.FIND_BY_AYUDANTE,
                query = "SELECT "
                        + "FROM domainapp.modules.simple.dom.cuadrilla.Cuadrilla"
                        + "WHERE ayudante == :ayudante"
                        + "ORDER BY nombre ASC")
})
@DomainObject(editing = Editing.DISABLED)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_ROOT)
//@PersistenceContext(identityType = IdentityType.DATASTORE, schema = SimpleModule.SCHEMA)
@ToString(onlyExplicitlyIncluded = true)
@Getter @Setter
public class Cuadrilla implements Comparable<Cuadrilla>{

    static final String FIND = "Cuadrilla.find";
    static final String FIND_BY_NOMBRE = "Cuadrilla.findByNombre";
    static final String FIND_BY_TECNICO = "Cuadrilla.findByTecnico";
    static final String FIND_BY_AYUDANTE = "Cuadrilla.findByAyudante";

    @Column(nullable = false,length = 40)
    @Property
    private String nombre;

    @Column(nullable = false,length = 40)
    @Property
    private Tecnico tecnico;

    @Column(nullable=false)
    @Property
    private Ayudante ayudante;

    //@PersistenceUnit(mappedBy="cuadrillaAsignada",defaultFetchGroup= "true")
    @Column(nullable =false)
    @Property
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

    @Override
    public int compareTo(Cuadrilla other) {
        return ObjectContracts.compare(this,other,"nombre");
    }
}
