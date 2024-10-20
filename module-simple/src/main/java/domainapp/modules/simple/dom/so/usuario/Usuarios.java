package domainapp.modules.simple.dom.so.usuario;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.DomainService;
import org.apache.causeway.applib.annotation.Parameter;
import org.apache.causeway.applib.annotation.ParameterLayout;
import org.apache.causeway.applib.annotation.PriorityPrecedence;
import org.apache.causeway.applib.annotation.PromptStyle;
import org.apache.causeway.applib.annotation.SemanticsOf;
import org.apache.causeway.applib.services.repository.RepositoryService;
import org.apache.causeway.persistence.jpa.applib.services.JpaSupportService;

import lombok.RequiredArgsConstructor;

import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.types.Name;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.TypedQuery;

//@Named(SimpleModule.NAMESPACE +".Usuarios")
@Named(SimpleModule.NAMESPACE)
@DomainService
@Priority(PriorityPrecedence.EARLY)
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class Usuarios {

    final UserRepo userRepo;
    final JpaSupportService jpaSupportService;
    final RepositoryService repositoryService;

    @Action(semantics = SemanticsOf.SAFE)
    public List<Usuario> List(){
        return userRepo.findAll();
    }

    public Usuario findByDni(int dni) {
        Usuario user = userRepo.findByDni(dni);
        return user;
    }

    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR)
    public List<Usuario> findByNombre(String nombre){
        List<Usuario> listapersonas = userRepo.findByNombreContais(nombre);
        return listapersonas;
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


    public void ping() {
        jpaSupportService.getEntityManager(Usuario.class)
                .mapEmptyToFailure()
                .mapSuccessAsNullable(entityManager -> {
                    final TypedQuery<Usuario> q = entityManager.createQuery(
                                    "SELECT p FROM Usuario p ORDER BY p.name",
                                    Usuario.class)
                            .setMaxResults(1);
                    return q.getResultList();
                })
                .ifFailureFail();
    }
}
