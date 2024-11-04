package domainapp.modules.simple.dom.reclamo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclaRepo  extends JpaRepository<Reclamo, Long> {

    //List<Reclamo> Listar(final String reclamo);

    //Reclamo CambiarEstado(Estado estado);
}
