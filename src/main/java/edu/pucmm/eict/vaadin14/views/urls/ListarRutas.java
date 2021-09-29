package edu.pucmm.eict.vaadin14.views.urls;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouteData;
import com.vaadin.flow.router.RouterLink;

import java.util.List;

@Route("listar-rutas-definidas")
public class ListarRutas extends VerticalLayout {

    public ListarRutas() {

        List<RouteData> routes = RouteConfiguration.forSessionScope().getAvailableRoutes();
        for(RouteData d : routes){
            if(d.getRouteParameters().size() == 0) {
                add(new RouterLink("Link #"+d.getTemplate(), d.getNavigationTarget()));
            }
        }

    }
}
