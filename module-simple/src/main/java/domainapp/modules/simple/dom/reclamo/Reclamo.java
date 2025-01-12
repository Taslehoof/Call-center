package domainapp.modules.simple.dom.reclamo;

import java.time.LocalDate;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.BookmarkPolicy;
import org.apache.causeway.applib.annotation.DomainObjectLayout;
import org.apache.causeway.applib.annotation.Editing;
import org.apache.causeway.applib.annotation.Optionality;
import org.apache.causeway.applib.annotation.Parameter;
import org.apache.causeway.applib.annotation.ParameterLayout;
import org.apache.causeway.applib.annotation.Programmatic;
import org.apache.causeway.applib.annotation.Property;
import org.apache.causeway.applib.annotation.PropertyLayout;
import org.apache.causeway.applib.annotation.SemanticsOf;
import org.apache.causeway.applib.annotation.Title;
import org.apache.causeway.applib.services.message.MessageService;
import org.apache.causeway.applib.services.repository.RepositoryService;
import org.apache.causeway.applib.util.ObjectContracts;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.dom.cuadrilla.Cuadrilla;
import domainapp.modules.simple.dom.usuario.Usuario;
import jakarta.inject.Inject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        schema = SimpleModule.SCHEMA,
        uniqueConstraints ={ @UniqueConstraint(name="Reclamo_nroReclamo_UNQ", columnNames = {"nroReclamo"})
        }
)
/*@NamedQueries({
        @NamedQuery(
                name = Reclamo.FIND,
                query = "SELECT "),
        @NamedQuery(
                name = Reclamo.FIND_LAST,
                query = "SELECT "
                +"ORDER BY nroReclamo DESC"),
        @NamedQuery(
                name = Reclamo.FIND_BY_NRO_RECLAMO,
                query = "SELECT "
                +"FROM domainapp.modules.simple.dom.reclamo.Reclamo "
                +" WHERE nroRclamos == :nroReclamo"
                +"ORDER BY nroReclamo ASC")
})*/
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_ROOT)
//@Named(SimpleModule.NAMESPACE + ".reclamos")
@ToString(onlyExplicitlyIncluded = true)
@Getter @Setter
public class Reclamo implements Comparable<Reclamo>{

    //static final String FIND = "Reclamo.find";
    //static final String FIND_LAST = "Reclamo.findLast";
    //static final String FIND_BY_NRO_RECLAMO = "Reclamo.findByNroReclamo";

    @Column(nullable = true, length = 10)
    @Property(editing = Editing.DISABLED)
    @Id
    private String nroReclamo;

   //@Column(nullable = false)
    @NonNull
    @Property
    private Usuario usuario;

    @Column(nullable = true)
    @NonNull
    @Property
    private String direccion;

    @Column(nullable = false)
    @NonNull
    @PropertyLayout(named = "Fecha de Creacion del Reclamo: ")
    @Property(editing = Editing.DISABLED)
    private LocalDate fecha = LocalDate.now();

    @Column(nullable = true)
    @Title(prepend = "Reclamo: ")
    @Property(editing = Editing.ENABLED)
    private TipoReclamo tipoReclamo;

    @Column(nullable = false, length = 2000)
    @Property(editing = Editing.ENABLED)
    private String descripcion;

    @Column(nullable = false, length = 2000)
    @Property(editing = Editing.DISABLED)
    private Estado estado;

    @Column(nullable = true, name = "cuadrilla_asig_id")
    @Property
    @PropertyLayout(named = "Cuadrilla")
    private Cuadrilla cuadrilla;

    public Reclamo() {
    }

    public Reclamo(Usuario usuario, String direccion, LocalDate fecha, TipoReclamo tipoReclamo, Estado estado) {
        this.usuario = usuario;
        this.direccion = direccion;
        this.fecha = fecha;
        this.tipoReclamo = tipoReclamo;
        this.estado = estado;
    }

    public Reclamo(String nroReclamo, Usuario usuario, String direccion, LocalDate fecha, TipoReclamo tipoReclamo, Estado estado) {
        this.nroReclamo = nroReclamo;
        this.usuario = usuario;
        this.direccion = direccion;
        this.fecha = fecha;
        this.tipoReclamo = tipoReclamo;
        this.estado = estado;
    }
    public Reclamo(String nroReclamo, Usuario usuario, String direccion, LocalDate fecha, TipoReclamo tipoReclamo, String descripcion, Estado estado) {
        this.nroReclamo = nroReclamo;
        this.usuario = usuario;
        this.direccion = direccion;
        this.fecha = fecha;
        this.tipoReclamo = tipoReclamo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    @Programmatic
    public void CambiarEstado(Estado estado){
        this.estado = estado;
    }

    @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
    @ActionLayout(named = "Asignar Cuadrilla")
    public Reclamo AsignarCuadrilla(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Cuadrilla")
            final Cuadrilla cuadrilla){

        switch (estado){
            case Anulado:
                messageService.warnUser("No se puede asignar un reclamo Anulado!");
                break;
            case Cerrado:
                messageService.warnUser("No se puede asignar un reclamo Cerrado!");
                break;
            case En_Proceso:
                messageService.warnUser("El reclamo ya posee una cuaadrilla asignada!");
                break;
            case Asignado:
                messageService.warnUser("Reclamo Asignado");
                break;
            default:
                CambiarEstado(Estado.Sin_Asignar);
                messageService.warnUser("El reclamo no fue asignado todaviakl");
        }

        return this;

    }

    @Override
    public int compareTo(final Reclamo other){
       return ObjectContracts.compare(this, other,nroReclamo) ;
    }

    @Inject
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    RepositoryService repositoryService;

    @Inject
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    MessageService messageService;
}
