package domainapp.modules.simple.dom.so.usuario;

import domainapp.modules.simple.types.Name;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.PromptStyle;
import org.apache.causeway.applib.annotation.SemanticsOf;
import org.apache.causeway.applib.services.repository.RepositoryService;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.List;

public class Usuarios {

    final UserRepo UserRepo;
    final RepositoryService repositoryService;

    @Action(semantics = SemanticsOf.SAFE)
    public List<Usuario> Listar(){
        return UserRepo.findAll();
    }

    public Usuario findByDniExact(int dni) {
        return UserRepo.findByDni(dni);
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR)
    public List<Usuario> findByNombre(
            @Name final String nombre){
        return UserRepo.findByNombreContais(nombre);
    }


}
