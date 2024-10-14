package domainapp.modules.simple.dom.so.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNombreContais(final String nombre);

    Usuario findByDni(final int dni);
}
