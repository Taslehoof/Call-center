package domainapp.modules.simple.dom.reclamo;

import java.util.List;

import org.apache.causeway.applib.annotation.DomainService;
import org.apache.causeway.applib.annotation.PriorityPrecedence;
import org.apache.causeway.applib.annotation.Programmatic;
import org.apache.causeway.applib.services.repository.RepositoryService;

import lombok.RequiredArgsConstructor;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;

//@Named(SimpleModule.NAMESPACE+".Reclamos")
@DomainService
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class Reclamos {

    final ReclaRepo reclaRepo;
    final RepositoryService repositoryService;

    /*public Reclamo CambiarEstado(Estado estado){
       return reclaRepo.CambiarEstado(estado);
    }*/

    @Programmatic
    public List<Reclamo> Listar(){
        return reclaRepo.findAll();
    }
}
