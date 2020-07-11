package edu.pucmm.eict.vaadin14.views.parametros;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.Map;

@Route("leer-query-param")
public class LeerQueryParam extends VerticalLayout implements HasUrlParameter<String> {


    /**
     * 
     * @param event
     * @param parameter
     */
    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        if(parameter == null){
            add(new Label(String.format("Deben enviar variables en la URL", parameter)));
        }  else{
            add(new Label(String.format("Obteniendo la variable en la URL %s", parameter)));
        }
        //recuperando los parametros de la URL.
        Map<String, List<String>> parametrosUrl = event.getLocation().getQueryParameters().getParameters();
        parametrosUrl.forEach((k,v) -> add(new Label(String.format("parametro[%s] = %s", k, v.toString()))));

    }
}
