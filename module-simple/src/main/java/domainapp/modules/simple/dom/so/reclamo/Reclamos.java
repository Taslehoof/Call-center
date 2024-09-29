package domainapp.modules.simple.dom.so.reclamo;

import domainapp.modules.simple.SimpleModule;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import org.apache.causeway.applib.annotation.DomainService;
import org.apache.causeway.applib.annotation.PriorityPrecedence;
import org.apache.causeway.applib.annotation.Programmatic;
import org.apache.causeway.applib.services.repository.RepositoryService;

import java.util.List;

@Named(SimpleModule.NAMESPACE+".Reclamos")
@DomainService
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class Reclamos {

    final ReclaRepo ReclaRepo;
    final RepositoryService repositoryService;

    public Estado CambiarEstado(Estado estado){
       return ReclaRepo.CambiarEstado(estado);
    }

    @Programmatic
    public List<Reclamo> Listar(){
        return ReclaRepo.findAll();
    }
}
