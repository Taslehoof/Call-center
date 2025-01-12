package domainapp.modules.simple.dom.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<Usuario, Long> {

    List<Usuario> Listar(final Usuario nombre);

    Usuario findByDni(final String dni);
}
