package domainapp.modules.simple.dom.cuadrilla;


import domainapp.modules.simple.dom.ayudante.Ayudante;
import domainapp.modules.simple.dom.tecnico.Tecnico;

import jakarta.inject.Inject;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.Optionality;
import org.apache.causeway.applib.annotation.Parameter;
import org.apache.causeway.applib.annotation.ParameterLayout;

public class CuadrillaMenu {

    @Action
    @ActionLayout(named = "Crear Cuadrilla")
    public Cuadrillas create(
            @Parameter(maxLength = 40)
            @ParameterLayout(named = "Nombre: ")
            final String nombre,

            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Tecnico: ")
            final Tecnico tecnico,

            @Parameter(optionality = Optionality.MANDATORY)
            @ParameterLayout(named = "Ayudante: ")
            final Ayudante ayudante) {

        return cuadrillaRepository.create(nombre, tecnico, ayudante);
    }

    @Inject
    CuadrillaRepo cuadrillaRepository;
}
