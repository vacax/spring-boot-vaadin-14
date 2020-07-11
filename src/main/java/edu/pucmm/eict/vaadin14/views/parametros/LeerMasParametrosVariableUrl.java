package edu.pucmm.eict.vaadin14.views.parametros;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.WildcardParameter;

@Route("leer-mas-parametros")
public class LeerMasParametrosVariableUrl extends Div implements HasUrlParameter<String> {

    /**
     * La forma de poder leer más de un parametros:
     * 
     * @param event
     * @param parameter
     */
    @Override
    public void setParameter(BeforeEvent event, @WildcardParameter String parameter) {
        if (parameter.isEmpty()) {
            setText("Parametro no enviado.");
        } else {
            //Para obtener toda la información, separar el string obtenido.
            setText(String.format("Manejando varios parametros: %s.", parameter));
        }
    }
}
