package edu.pucmm.eict.vaadin14.views.inicio;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;
import edu.pucmm.eict.vaadin14.views.basico.HolaMundoVaadin;
import edu.pucmm.eict.vaadin14.views.binder.EjemploBinder;
import edu.pucmm.eict.vaadin14.views.calendario.Calendario;
import edu.pucmm.eict.vaadin14.views.crud.EjemploCrud;
import edu.pucmm.eict.vaadin14.views.grid.EjemploGrid;
import edu.pucmm.eict.vaadin14.views.layouts.Vista1Layout;
import edu.pucmm.eict.vaadin14.views.main.MainView;
import edu.pucmm.eict.vaadin14.views.parametros.ConocerURL;
import edu.pucmm.eict.vaadin14.views.urls.CambiadoTituloPaginaDinamico;
import edu.pucmm.eict.vaadin14.views.urls.ListarRutas;

@Route(value = "empty", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Empty")
@CssImport("styles/views/empty/empty-view.css")
public class InicioView extends Div {

    public InicioView() {
        setId("empty-view");
        crearRutas();
    }

    /**
     * Creando la creación de las rutas.
     */
    private void crearRutas(){
        VerticalLayout caja = new VerticalLayout();
        caja.add(new H2("Enlaces a funcionalidades:"));
        //con RouterLink el renderizado no recarga la pagina.
        caja.add(new RouterLink("Hola Mundo", HolaMundoVaadin.class));
        caja.add(new RouterLink("Indice de URL parametros", ConocerURL.class));
        caja.add(new RouterLink("Ejemplo de Grid", EjemploGrid.class));
        caja.add(new RouterLink("Ejemplo de Binder", EjemploBinder.class));
        caja.add(new RouterLink("Cambiar Titulo de Forma Dinámica", CambiadoTituloPaginaDinamico.class));
        caja.add(new RouterLink("Listar todas las rutas", ListarRutas.class));
        caja.add(new RouterLink("CRUD", EjemploCrud.class));
        caja.add(new RouterLink("Ejemplo de Layout", Vista1Layout.class));
        caja.add(new RouterLink("Ejemplo de Calendario", Calendario.class));
        add(caja);
    }

}
