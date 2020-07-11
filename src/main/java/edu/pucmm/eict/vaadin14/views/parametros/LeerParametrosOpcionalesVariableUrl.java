package edu.pucmm.eict.vaadin14.views.parametros;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

@Route("leer-parametros-opcionales")
public class LeerParametrosOpcionalesVariableUrl extends Div implements HasUrlParameter<String> {

    /**
     * Para marcar el parametro opcional debo indicar la anotación
     * @param event
     * @param parameter
     */
    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        if(parameter == null){
            setText("Funcionalidad para leer parametros opcionales....");
        }  else{
            setText(String.format("Obteniendo el parametro %s", parameter));
        }
        
    }
}
