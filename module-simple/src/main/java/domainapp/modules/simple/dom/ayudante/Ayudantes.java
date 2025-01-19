package domainapp.modules.simple.dom.ayudante;

import domainapp.modules.simple.SimpleModule;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;

import jakarta.inject.Named;

import lombok.RequiredArgsConstructor;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.DomainService;
import org.apache.causeway.applib.annotation.PriorityPrecedence;
import org.apache.causeway.applib.annotation.SemanticsOf;
import org.apache.causeway.applib.services.repository.RepositoryService;

@Named(SimpleModule.NAMESPACE + ".ayudante")
@DomainService
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class Ayudantes {

    final AyudanteRepo ayundateRepo;
    final RepositoryService repositoryService;

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(named = "Listado de Ayundates")
    public Ayudante findByDni( final int dni) {
        return ayundateRepo.findByDni(dni);
    }
}
