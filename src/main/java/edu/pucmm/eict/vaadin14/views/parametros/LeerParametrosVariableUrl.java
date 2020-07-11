package edu.pucmm.eict.vaadin14.views.parametros;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route("leer-parametros")
public class LeerParametrosVariableUrl extends Div implements HasUrlParameter<String> {

    public LeerParametrosVariableUrl() {
        
    }

    /**
     * Para llamarlo debe ser:
     * http://localhost:8080/leer-parametros/Carlos%20Camacho
     * @param event
     * @param parameter
     */
    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        setText(String.format("Obteniendo el parametro %s", parameter));
    }
}
