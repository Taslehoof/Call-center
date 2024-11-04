package domainapp.modules.simple.dom.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Usuario, Long> {

    //List<Usuario> findNombreContais(final String nombre);

    Usuario findByDni(final String dni);
}
