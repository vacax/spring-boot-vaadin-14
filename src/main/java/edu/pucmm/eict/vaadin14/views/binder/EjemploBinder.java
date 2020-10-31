package edu.pucmm.eict.vaadin14.views.binder;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.*;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.Route;
import edu.pucmm.eict.vaadin14.views.grid.EjemploGrid;

@Route("ejemplo-binders")
public class EjemploBinder extends VerticalLayout {

    //Propuedades.
    FormLayout formLayout;
    TextField matriculaField;
    TextField nombreField;
    TextField correoField;
    EjemploGrid.Estudiante estudiante = new EjemploGrid.Estudiante(20011136, "Carlos Camacho");
    Button aceptar, cancelar;
    //instanciando el Binder.
    Binder<EjemploGrid.Estudiante> binder;

    public EjemploBinder() {
        //Instanciando los componentes.
        matriculaField = new TextField("Matrícula");
        nombreField = new TextField("Nombre");
        correoField = new TextField("Correo");
        aceptar = new Button("Aceptar", new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> event) {

                try {
                    binder.writeBean(estudiante);
                    Notification.show("El estudiante:"+estudiante);
                }catch (ValidationException e){
                    Notification.show(e.getMessage());
                }
            }
        });

        cancelar = new Button("Cancelar", event -> {
            Notification.show("Presionando el botón: "+event.getSource().getText());
        });

        //Aplicando el binder
        binder = new Binder<>();

        //La implementación basada en interfaz.
        binder.bind(nombreField, new ValueProvider<EjemploGrid.Estudiante, String>() {
                    @Override
                    public String apply(EjemploGrid.Estudiante estudiante) {
                        return estudiante.getNombre();
                    }
                },
                new Setter<EjemploGrid.Estudiante, String>() {
                    @Override
                    public void accept(EjemploGrid.Estudiante estudiante, String name) {
                        estudiante.setNombre(name);
                    }
                });
        
        //binder con validador.
        binder.forField(correoField).asRequired("Debe Ser indicado")
                .withValidator(new EmailValidator("Debe ser un correo..."))
                .bind(EjemploGrid.Estudiante::getCorreo, EjemploGrid.Estudiante::setCorreo);

        //trabajando con la conversión por defecto.
        /*binder.forField(matriculaField)
                .withConverter(new StringToIntegerConverter("Debe ser entero"))
                .bind(EjemploGrid.Estudiante::getMatricula, EjemploGrid.Estudiante::setMatricula);*/

        //trabajando con la conversión propia.
        binder.forField(matriculaField)
                .withConverter(new ConvertidorMatricula())
                .bind(EjemploGrid.Estudiante::getMatricula, EjemploGrid.Estudiante::setMatricula);

        //instanciando el Layout.
        formLayout = new FormLayout(matriculaField, nombreField, correoField);
        formLayout.add(new HorizontalLayout(aceptar, cancelar));
        add(formLayout);

        //cargando la información en el formulario dado el objeto.
        binder.readBean(estudiante);

    }


    /**
     * Representa un convertidor para trabajar con 
     */
    public static class ConvertidorMatricula implements Converter<String, Integer> {

        @Override
        public Result<Integer> convertToModel(String value, ValueContext context) {
            try{
                return Result.ok(Integer.valueOf(value.replaceAll("-","")));
            } catch (NumberFormatException e){
                return Result.error("Debe ser un valor entero");
            }
        }

        @Override
        public String convertToPresentation(Integer value, ValueContext context) {
            return value.toString().substring(0,4)+"-"+value.toString().substring(4);
        }
    }
}
