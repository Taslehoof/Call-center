package domainapp.modules.simple.dom.so.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<Usuario,Long> {

    Usuario findByDni(final int dni);

    List<Usuario> findByNombreContais(final String nombre);
}
