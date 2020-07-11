package edu.pucmm.eict.vaadin14.views.urls;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.*;

/**
 * El uso de la anotación no puede utilizarse si se tiene de forma dinamica.
 */
@Route("cambiando-titulo-pagina-dinamico")
public class CambiadoTituloPaginaDinamico extends Div
        implements HasDynamicTitle, HasUrlParameter<String> {

    String tituloPagina = "";

    public CambiadoTituloPaginaDinamico() {
        mostrarTexto();
    }

    @Override
    public String getPageTitle() {
        return tituloPagina;
    }

    public void mostrarTexto(){
        setText("El titulo que debemos mostrar: "+tituloPagina);
    }


    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
           if(parameter != null){
               tituloPagina = parameter;
           } else{
               tituloPagina = "Enviar parametro para cambiar el titulo";
           }
           mostrarTexto();
    }
}
