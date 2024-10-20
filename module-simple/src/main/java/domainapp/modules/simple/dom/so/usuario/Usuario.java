package domainapp.modules.simple.dom.so.usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.BookmarkPolicy;
import org.apache.causeway.applib.annotation.Collection;
import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.DomainObjectLayout;
import org.apache.causeway.applib.annotation.Editing;
import org.apache.causeway.applib.annotation.ParameterLayout;
import org.apache.causeway.applib.annotation.Property;
import org.apache.causeway.applib.annotation.Title;
import org.apache.causeway.applib.services.factory.FactoryService;
import org.apache.causeway.applib.services.repository.RepositoryService;
import org.apache.causeway.applib.util.ObjectContracts;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.val;

import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.dom.so.reclamo.Estado;
import domainapp.modules.simple.dom.so.reclamo.Reclamo;
import domainapp.modules.simple.dom.so.reclamo.TipoReclamo;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        schema = SimpleModule.SCHEMA,
        uniqueConstraints = {@UniqueConstraint(name = "Usuario_dni_UNQ", columnNames = {"dni"})}
)
@NamedQueries({
        @NamedQuery(
                name= Usuario.FIND,
                query = "SELECT "
                + "FROM domainapp.modules.simple.dom.so.usuario.Usuario"
                + "ORDER BY nombre ASC"),
        @NamedQuery(
                name= Usuario.FIND_BY_NRO_RECLAMO,
                query = "SELECT "
                + "FROM domainapp.modules.simple.dom.so.usuario.Usuario"
                + "WHERE dni == :dni"
                + "ORDER BY dni ASC")
})
@DomainObject(editing = Editing.DISABLED)
//@Named(SimpleModule.NAMESPACE + ".usuario")
@Named(SimpleModule.NAMESPACE)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_ROOT)
@ToString(onlyExplicitlyIncluded = true)
@Getter @Setter
public class Usuario implements Comparable<Usuario>{

    static final String FIND = "Usuario.find";
    static final String FIND_BY_NRO_RECLAMO= "Usuario.findByNroReclamo";

    @Id
    @Column(nullable = true, length = 8)
    private int dni;

    @Column(nullable = true, length = 40)
    @Title
    private String nombre;

    @Column(nullable = true, length = 40)
    @Title
    private String apellido;

    @Column(nullable = true, length = 40)
    @Property
    private String direccion;

    @Column(nullable = true, length = 19)
    @Property
    private String email;

    @Column(nullable = true, length = 19)
    @Property
    private int telefono;

    //@Persistent(mappedBy = "usuario", dependentElement = "true")
    @OneToOne
    @JoinColumn(name = "reclamo_nroReclamo", referencedColumnName = "nroReclamo")
    @Collection
    private List<Reclamo> reclamo = new ArrayList<Reclamo>();

    public static Usuario withName(final String nombre){
        val usuario = new Usuario();
        usuario.setNombre(nombre);
        return usuario;
    }

    public int RepoDni(){return this.dni;}
    public String RepoNombre(){return this.nombre;}
    public String RepoApellido(){return this.apellido;}
    public String RepoDireccion(){return this.direccion;}
    public int RepoTelefono(){return this.telefono;}

    public Usuario(){}

    public Usuario(int dni, String nombre, String apellido, String direccion, String email, int telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }

    public Usuario(int dni, String nombre, String apellido, String direccion, String email, int telefono, List<Reclamo> reclamo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.reclamo = reclamo;
    }

    public int default0Update(){return getDni();}
    public String default1Update(){return getNombre();}
    public String default2Update(){return getApellido();}
    public String default3Update(){return getDireccion();}
    public String default4Update(){return getEmail();}
    public int default5Update(){return getTelefono();}

    @Action
    @ActionLayout(named = "Cargar reclamo")
    public Usuario addReclamo(@ParameterLayout(named = "Tipo de Reclamo") final TipoReclamo tipoReclamo){
        final Reclamo recla = factoryService.create(Reclamo.class);
        //reclamo.setUsuario(this);
        recla.setDireccion(this.direccion);
        recla.setFecha(LocalDate.now());
        recla.setTipoReclamo(tipoReclamo);
        recla.setEstado(Estado.Sin_Asignar);
        getReclamo().add(recla);
        repositoryService.persist(reclamo);
        return this;
    }

    @Override
    public int compareTo(final Usuario other) {
        return ObjectContracts.compare(this,other, "dni");
    }

    @Inject
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    FactoryService factoryService;

    @Inject
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    RepositoryService repositoryService;
}
