package edu.pucmm.eict.vaadin14.views.crud;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.Route;
import edu.pucmm.eict.vaadin14.data.entity.Estudiante;
import edu.pucmm.eict.vaadin14.data.service.EstudianteService;
import edu.pucmm.eict.vaadin14.views.layouts.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@Route(value = "ejemplo-crud-estudiante", layout = MainLayout.class)
public class EjemploCrud extends VerticalLayout {

    //
    EstudianteService estudianteService;
    //componentes.
    Grid<Estudiante> tablaEstudiante;
    TextField tfMatricula;
    TextField tfNombre;
    Button btnAgregar, btnEliminar;
    Binder<Estudiante> binder;
    //Para permitir la busqueda paginando.
    DataProvider<Estudiante, Void> dataProvider;
    //
    Estudiante estudianteSeleccionado;

    /**
     * Constructor aplicando la inyección de dependecia
     * @param estudianteService
     */
    public EjemploCrud(@Autowired EstudianteService estudianteService){
        //referencia.
        this.estudianteService = estudianteService;

        //Instanciando el dato provider.
        dataProvider = DataProvider.fromCallbacks(
                //indicando la consulta que retorna la información
                query -> {

                    // Indicando el primer elemento cargado.
                    int offset = query.getOffset();
                    System.out.println("El offset: "+offset);
                    // La cantidad maxima a cargar
                    int limit = query.getLimit();
                    System.out.println("El limit: "+limit);
                    //Enviando el flujo
                    return estudianteService.listaEstudiantes(PageRequest.of(offset, limit));
                },
                query -> {
                    //Indicando la cantidad maxima de elementos.
                    return Math.toIntExact(estudianteService.cantidadEstudiante());
                }
        );
        //instanciando el binder.
        binder = new Binder<>();


        //La tabla
        tablaEstudiante = new Grid<>();
        tablaEstudiante.setDataProvider(dataProvider);
        tablaEstudiante.addColumn(Estudiante::getMatricula).setHeader("Matricula");
        tablaEstudiante.addColumn(Estudiante::getNombre).setHeader("Nombre");
        tablaEstudiante.addColumn(new NativeButtonRenderer<Estudiante>("Elminiar", e->{
            Notification.show("Eliminando el registro: "+e.getMatricula());
            estudianteService.borrarEstudiante(e);
            dataProvider.refreshAll();
        })).setHeader("Acciones");

        //evento de la tabla
        tablaEstudiante.addSelectionListener(s->{
            if(s.getFirstSelectedItem().isPresent()){
                estudianteSeleccionado = s.getFirstSelectedItem().get();
                binder.readBean(estudianteSeleccionado);
                btnEliminar.setEnabled(true);
            }else{
                tfMatricula.clear();
                tfNombre.clear();
                btnEliminar.setEnabled(false);
            }
        });

        //
        tablaEstudiante.setWidth("50%");

        //los campos.
        tfMatricula=new TextField("Matrícula");
        tfNombre=new TextField("Nombre");
        btnAgregar = new Button("Agregar", e->{
            try {
                Estudiante tempEstudiante = new Estudiante();
                binder.writeBean(tempEstudiante);
                estudianteService.crearEstudiante(tempEstudiante);
                //refrescando el data set.
                dataProvider.refreshAll();
            }catch (ValidationException ex){
                Notification.show("Error...: "+ex.getMessage());
            }
        });
        btnEliminar = new Button("Eliminar", e->{
            estudianteService.borrarEstudiante(estudianteSeleccionado);
            dataProvider.refreshAll();
        });
        btnEliminar.setEnabled(false);

        //referencia al binder.
        binder.forField(tfMatricula).asRequired("Debe indicar la matricuila")
                .withConverter(new StringToIntegerConverter("Debe ser un entero"))
                .bind(Estudiante::getMatricula,Estudiante::setMatricula);

        binder.forField(tfNombre).asRequired("Debe indicar un nombre")
                .bind(Estudiante::getNombre, Estudiante::setNombre);

        //layout para formularios.
        FormLayout fl = new FormLayout(tfMatricula, tfNombre);
        HorizontalLayout accionesForm = new HorizontalLayout(btnAgregar, btnEliminar);
        VerticalLayout vfl = new VerticalLayout(fl, accionesForm);

        //agregando el diseño.
        HorizontalLayout hz = new HorizontalLayout(tablaEstudiante, vfl);
        hz.setSizeFull();


        //
        add(hz);
        setSizeFull();
        //refrescando la tabla.
        dataProvider.refreshAll();
    }

}
