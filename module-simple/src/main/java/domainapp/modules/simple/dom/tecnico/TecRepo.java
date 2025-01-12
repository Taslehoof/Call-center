package domainapp.modules.simple.dom.tecnico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TecRepo extends JpaRepository<Tecnico, Long> {

    List<Tecnico> Listar(final Tecnico nombre);

    Tecnico findByDni(final int dni);
}
