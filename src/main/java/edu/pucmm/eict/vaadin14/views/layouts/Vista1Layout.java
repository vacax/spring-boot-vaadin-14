package edu.pucmm.eict.vaadin14.views.layouts;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.Route;

@Route(value = "vista1-layout", layout = MainLayout.class)
public class Vista1Layout extends Div {

    public Vista1Layout(){
        add(new Label("Visualizando la Vista 1"));
    }
}
