package domainapp.modules.simple.dom.so.reclamo;

import domainapp.modules.simple.dom.so.usuario.Usuario;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.Optionality;
import org.apache.causeway.applib.annotation.Parameter;
import org.apache.causeway.applib.annotation.ParameterLayout;
import org.apache.causeway.applib.annotation.SemanticsOf;
import org.apache.causeway.applib.services.message.MessageService;
import org.apache.causeway.applib.services.repository.RepositoryService;
import org.apache.causeway.applib.util.ObjectContracts;

import java.time.LocalDate;

public class Reclamo implements Comparable<Reclamo>{

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
                CammbiarEstado(Estado.Sin_Asignar);
                messageService.warnUser("El reclamo no fue asignado todaviakl");
        }

        return this;

    }

    @Override
    public int compareTo(final Reclamo other){
       return ObjectContracts.compare(this, other,nroReclamo) ;
    }

    RepositoryService repositoryService;

    MessageService messageService;
}
