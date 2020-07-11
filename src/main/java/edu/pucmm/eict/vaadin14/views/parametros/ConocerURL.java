package edu.pucmm.eict.vaadin14.views.parametros;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;

@Route("indice-parametros")
public class ConocerURL extends VerticalLayout {

    public ConocerURL() {
        add(new H1("Ejemplos de Uso de parametros de la URL"));
        add(new Anchor(RouteConfiguration.forSessionScope().getUrl(LeerParametrosVariableUrl.class, "parametro1"), "Parametros Variable Obligatorio"));
        add(new Anchor(RouteConfiguration.forSessionScope().getUrl(LeerParametrosOpcionalesVariableUrl.class), "Parametros Variable Opcionales"));
        add(new Anchor(RouteConfiguration.forSessionScope().getUrl(LeerMasParametrosVariableUrl.class, "asda/asdas/asd"), "Mas de un Parametro Variable"));
        add(new Anchor(RouteConfiguration.forSessionScope().getUrl(LeerQueryParam.class, "variable?matricula=12313&nombre=123132"), "Query Param URL"));
    }


}
