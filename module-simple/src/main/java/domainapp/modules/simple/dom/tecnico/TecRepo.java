package domainapp.modules.simple.dom.tecnico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TecRepo extends JpaRepository<Tecnico, Long> {

    Tecnico findDni(final int dni);

}
