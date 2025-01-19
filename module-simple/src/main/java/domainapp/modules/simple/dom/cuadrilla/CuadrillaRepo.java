package domainapp.modules.simple.dom.cuadrilla;

import domainapp.modules.simple.dom.ayudante.Ayudante;
import domainapp.modules.simple.dom.tecnico.Tecnico;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuadrillaRepo  extends JpaRepository<Cuadrilla,Long> {

    Cuadrilla cuadrilla(final String nombre);

    Cuadrillas create(final String nombre, final Tecnico tecnico, final Ayudante ayudante);

    Cuadrillas createUpdate(final String nombre, final Tecnico tecnico, final Ayudante ayudante);

    Cuadrilla findByNombre(final Cuadrilla nombre);

    List<Cuadrilla> findAll();

    boolean create();

}
