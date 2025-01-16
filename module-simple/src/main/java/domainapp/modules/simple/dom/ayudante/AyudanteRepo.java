package domainapp.modules.simple.dom.ayudante;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AyudanteRepo  extends JpaRepository<Ayudante, Long> {

    Ayudante findByDni(final int dni);
}
