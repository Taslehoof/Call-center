package domainapp.modules.simple.dom.cuadrilla;

import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.dom.ayudante.Ayudante;
import domainapp.modules.simple.dom.tecnico.Tecnico;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.DomainService;
import org.apache.causeway.applib.annotation.Optionality;
import org.apache.causeway.applib.annotation.Parameter;
import org.apache.causeway.applib.annotation.ParameterLayout;
import org.apache.causeway.applib.annotation.PriorityPrecedence;
import org.apache.causeway.applib.annotation.SemanticsOf;
import org.apache.causeway.applib.services.repository.RepositoryService;
import java.util.List;

@Named(SimpleModule.NAMESPACE+".Cuadrillas")
@DomainService

@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class Cuadrillas {

    final CuadrillaRepo cuadrillaRepo;
    final RepositoryService repositoryService;

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(named = "Crear Cuadrilla y Actualizar")
    public Cuadrilla createUpdate(

        @Parameter(maxLength = 40)
        @ParameterLayout(named = "Nombre")
        final String nombre,

        @Parameter(optionality = Optionality.MANDATORY)
        @ParameterLayout(named = "Tecnico")
        final Tecnico tecnico,

        @Parameter(optionality = Optionality.MANDATORY)
        @ParameterLayout(named = "Ayudante")
        final Ayudante ayudante){

        if (cuadrillaRepo.create() == true){
            return repositoryService.persist(Cuadrilla.create(nombre, tecnico, ayudante));
        } else {
            return Cuadrilla.create(nombre, tecnico, ayudante);
        }
    }

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(named = "Editar")
    public Cuadrilla update(

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Nombre")
            final String nombre,

            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Tecnico")
            final Tecnico tecnico,

            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Ayudante")
            final Ayudante ayudante){

        return repositoryService.persist(Cuadrilla.create(nombre,tecnico,ayudante));
    }

    @Action(semantics = SemanticsOf.SAFE)
    public List<Cuadrilla> Listar(){
        return cuadrillaRepo.findAll();
    }

    @ActionLayout(named = "Buscar cuadrilla")
    public Cuadrilla findByName(
            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Por Nombre: ")
            final Cuadrilla nombre){
        return cuadrillaRepo.findByNombre(nombre);
    }
}
