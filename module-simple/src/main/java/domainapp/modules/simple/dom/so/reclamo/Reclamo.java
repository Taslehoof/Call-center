package domainapp.modules.simple.dom.so.reclamo;

import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.dom.so.cuadrilla.Cuadrilla;
import domainapp.modules.simple.dom.so.usuario.Usuario;

import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;

import jakarta.persistence.NamedQuery;

import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.metamodel.IdentifiableType;

import lombok.AccessLevel;
import lombok.Getter;

import lombok.Setter;

import lombok.ToString;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.BookmarkPolicy;
import org.apache.causeway.applib.annotation.DomainObjectLayout;
import org.apache.causeway.applib.annotation.Optionality;
import org.apache.causeway.applib.annotation.Parameter;
import org.apache.causeway.applib.annotation.ParameterLayout;
import org.apache.causeway.applib.annotation.Programmatic;
import org.apache.causeway.applib.annotation.SemanticsOf;
import org.apache.causeway.applib.services.message.MessageService;
import org.apache.causeway.applib.services.repository.RepositoryService;
import org.apache.causeway.applib.util.ObjectContracts;

import java.time.LocalDate;

@Entity
@Table(
        schema = SimpleModule.SCHEMA,
        uniqueConstraints ={
                @UniqueConstraint(name="Reclamo_nroReclamo_UNQ", columnNames = {"nroReclamo"})
        }
)
@NamedQueries({
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
                +"ORDER BY nroReclamo ASC"),
})
//@PersistentCapable(identityType = IdentityType.DATASTORE, Schema = SimpleModule.SCHEMA)
@DomainObjectLayout(bookmarking = BookmarkPolicy.AS_ROOT)
@ToString(onlyExplicitlyIncluded = true)
@Getter @Setter
public class Reclamo implements Comparable<Reclamo>{

    static final String FIND = "Reclamo.find";
    static final String FIND_LAST = "Reclamo.findLast";
    static final String FIND_BY_NRO_RECLAMO = "Reclamo.findByNroReclamo";

    private String nroReclamo;
    private Usuario usuario;
    private String direccion;
    private LocalDate fecha = LocalDate.now();
    private TipoReclamo tipoReclamo;
    private String descripcion;
    private Estado estado;
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
