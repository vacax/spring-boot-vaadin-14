package edu.pucmm.eict.vaadin14.views.componentes;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("comp/holamundo")
public class HolaMundoComponente extends Div {

    public HolaMundoComponente() {
        setText("Hola Mundo Componente....");
    }
}
