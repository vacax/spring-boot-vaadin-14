package edu.pucmm.eict.vaadin14.views.basico;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("hola-mundo-vaadin")
public class HolaMundoVaadin extends VerticalLayout {

    //componentes.
    TextField campo;
    Label label;
    Button boton;

    /**
     * Contructor
     */
    public HolaMundoVaadin(){

        //instanciando los componentes.
        campo = new TextField("Mensaje");
        label = new Label("");
        boton=new Button("Aceptar", e->{
            Notification.show("Presionando el boton...");
            label.setText("Hola Mundo "+campo.getValue());
        });

        //incluyendo los contenedores primarios.
        HorizontalLayout hz = new HorizontalLayout(campo, boton);
        hz.setAlignItems(Alignment.END);

        //Agregado los componentes.
        add(hz, label);

    }
}
