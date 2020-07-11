package edu.pucmm.eict.vaadin14.views.grid;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Route("ejemplo-grid")
public class EjemploGrid extends VerticalLayout {

    List<Estudiante> lista;
    Grid<Estudiante> tabla;

    public EjemploGrid() {

        lista = new ArrayList<>();
        lista.add(new Estudiante(20011136, "Carlos Camacho"));
        lista.add(new Estudiante(20011135, "Domingo Jímenez"));
        lista.add(new Estudiante(20011137, "Francisco Grullón"));

        // Create a grid bound to the list
        tabla = new Grid<>();
        tabla.setItems(lista);
        tabla.addColumn(new ComponentRenderer<>(estudiante -> {
            return new Icon(VaadinIcon.MALE);
        })).setHeader("Imagen");
        tabla.addColumn(Estudiante::getMatricula).setHeader("Matricula").setFooter("Matricula");
        tabla.addColumn(Estudiante::getNombre).setHeader("Nombre").setFooter("Nombre");
        tabla.addColumn(new LocalDateRenderer<>(Estudiante::getFechaNacimiento, "dd/MM/yyyy")).setHeader("Fecha Nacimiento").setFooter("Fecha Nacimiento");
        tabla.addColumn(new NativeButtonRenderer<Estudiante>("Eliminar", evento ->{
            Notification.show("Eliminando el registro: "+evento.getMatricula());
        }));

        
        //Campo complejo.
        tabla.addColumn(new ComponentRenderer<>(person -> {

            // 
            TextField name = new TextField("Nombre");
            name.setValue(person.getNombre());

            // button for saving the name to backend
            Button update = new Button("Actualizar", event -> {
                person.setNombre(name.getValue());
                tabla.getDataProvider().refreshItem(person);
            });

            // button that removes the item
            Button remove = new Button("Remover", event -> {
               //....
            });

            // layouts for placing the text field on top of the buttons
            HorizontalLayout buttons = new HorizontalLayout(update, remove);
            return new VerticalLayout(name, buttons);
        })).setHeader("Actions");

        //evento de selección.
        tabla.addSelectionListener((SelectionListener<Grid<Estudiante>, Estudiante>) event -> {
            if(event.getFirstSelectedItem().isPresent()) {
                System.out.println("Seleccionando el estudiante: " + event.getFirstSelectedItem().get().getNombre());
            } else{
                System.out.println("No tengo nada seleccionado....");
            }
        });

        //
        add(tabla);
        setHeight("100%");

    }

    public static class Estudiante{

        int matricula;
        String nombre;
        LocalDate fechaNacimiento;
        String correo;


        public Estudiante() {
        }

        public Estudiante(int matricula, String nombre) {
            this.matricula = matricula;
            this.nombre = nombre;
            fechaNacimiento = LocalDate.now();
        }

        public int getMatricula() {
            return matricula;
        }

        public void setMatricula(int matricula) {
            this.matricula = matricula;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public LocalDate getFechaNacimiento() {
            return fechaNacimiento;
        }

        public void setFechaNacimiento(LocalDate fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        @Override
        public String toString() {
            return "Estudiante{" +
                    "matricula=" + matricula +
                    ", nombre='" + nombre + '\'' +
                    '}';
        }
    }
    
}


