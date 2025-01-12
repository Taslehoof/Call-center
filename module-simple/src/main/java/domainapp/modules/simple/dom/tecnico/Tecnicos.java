package domainapp.modules.simple.dom.tecnico;

import jakarta.inject.Inject;
import jakarta.inject.Named;

import lombok.RequiredArgsConstructor;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.SemanticsOf;

import java.util.List;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class Tecnicos {

    final TecRepo tecRepo;

    @Action(semantics = SemanticsOf.SAFE)
    public List<Tecnico> Listar(@Named final String nombre){
        return tecRepo.Listar(Tecnico.withName(nombre));
    }
}
