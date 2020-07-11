package edu.pucmm.eict.vaadin14.views.layouts;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.Route;

@Route(value = "vista2-layout", layout = MainLayout.class)
public class Vista2Layout extends Div {

    public Vista2Layout(){
        add(new Label("Visualizando la Vista 2"));
    }
}
