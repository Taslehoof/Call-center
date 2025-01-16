package domainapp.modules.simple.dom.ayudante;

import jakarta.inject.Inject;

import jakarta.inject.Named;

import lombok.RequiredArgsConstructor;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.SemanticsOf;
import org.apache.causeway.applib.services.repository.RepositoryService;

import java.util.List;

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
