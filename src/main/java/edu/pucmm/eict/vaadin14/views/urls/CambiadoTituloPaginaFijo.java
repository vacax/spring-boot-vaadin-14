package edu.pucmm.eict.vaadin14.views.urls;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Cambiando el Titulo de la pagina")
@Route("cambiando-titulo-pagina-fijo")
public class CambiadoTituloPaginaFijo extends Div {
    public CambiadoTituloPaginaFijo() {
        setText("Estoy cambiando el titulo @Router");
    }
}
