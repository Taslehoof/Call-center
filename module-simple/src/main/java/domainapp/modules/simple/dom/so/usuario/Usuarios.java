package domainapp.modules.simple.dom.so.usuario;

import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.types.Name;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;

import jakarta.inject.Named;

import lombok.RequiredArgsConstructor;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.DomainService;
import org.apache.causeway.applib.annotation.Parameter;
import org.apache.causeway.applib.annotation.ParameterLayout;
import org.apache.causeway.applib.annotation.PriorityPrecedence;
import org.apache.causeway.applib.annotation.PromptStyle;
import org.apache.causeway.applib.annotation.SemanticsOf;
import org.apache.causeway.applib.services.repository.RepositoryService;

import java.util.List;
import java.util.regex.Pattern;

@Named(SimpleModule.NAMESPACE +"Usuarios")
@DomainService
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject})
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

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR)
    public Usuario create(
            @Parameter(maxLength = 8)
            @ParameterLayout(named = "DNI: ")
            final int dni,

            @Parameter(maxLength = 40)
            @Name
            @ParameterLayout(named = "Nombre: ")
            final String nombre,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Apellido: ")
            final String apellido,

            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Direccion: ")
            final String direccion,

            @Parameter(
                    maxLength = 40,
                    regexPattern = "(\\w+\\.)*\\w*@(\\w*\\.)*[A-Za-z]+",
                    regexPatternFlags = Pattern.CASE_INSENSITIVE,
                    regexPatternReplacement = "Debe ser una direccion de Correo Valida (que contiene '@')")
            @ParameterLayout(named = "Email:")
            final String email,

            @Parameter(
                    maxLength = 19,
                    regexPattern = "[+]?[0-9]+",
                    regexPatternReplacement =
                            "Solo puede especificar numeros, espacios y opcionalmente el prefijo '+'."+
                                "Por ejemplo: , '+54 299 4484857' ")
            @ParameterLayout(named = "Telefono: ")
            final int telefono){

            return repositoryService.persist(Usuario.withName(nombre));
    }

}
