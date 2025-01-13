package domainapp.modules.simple.dom.tecnico;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.Programmatic;
import org.apache.causeway.applib.annotation.SemanticsOf;
import org.apache.causeway.applib.services.repository.RepositoryService;
import org.apache.causeway.applib.query.Query;

import java.util.List;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class Tecnicos {

    final TecRepo tecRepo;
    final RepositoryService repositoryService;

    //@Action(semantics = SemanticsOf.SAFE)
    @Programmatic
    public List<Tecnico> Listar(@Named final String nombre){
            return tecRepo.findAll();


    }

    @Action()
    public Tecnico findByDni(final int dni){
        return tecRepo.findDni(dni);
    }
}
