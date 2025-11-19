package com.hotline;

import java.net.URL;

import com.hotline.model.Inputs;
import com.hotline.model.UIEsenciales;
import com.hotline.service.EnemigoService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrimaryController {
    @FXML
    private Label lblEstadoActual;
    @FXML
    private ImageView imgEnemigo;

    @FXML
    private Button primaryButton;

    EnemigoService enemigoService=new EnemigoService();

    @FXML
    public void initialize() {
        procesar(Inputs.SIN_NOVEDAD); 
    }

    private void actualizarVista(UIEsenciales objeto) {
        try {
            // 1. Obtenemos la ruta de texto (ej: "/com/hotline/images/Patrullando.jpg")
            String rutaTexto = objeto.getSrcImg();
            
            // 2. Convertimos esa ruta de texto a una URL real del sistema
            URL urlImagen = getClass().getResource(rutaTexto);
            
            if (urlImagen != null) {
                // 3. Creamos la FOTO (Image) usando la URL
                Image fotoNueva = new Image(urlImagen.toExternalForm());
                
                // 4. Ponemos la FOTO dentro del MARCO (ImageView) que ya existe en pantalla
                imgEnemigo.setImage(fotoNueva);
                
                lblEstadoActual.setText(objeto.getMensaje()); // Actualizamos tambien el texto
            } else {
                System.out.println("No se encontró la imagen en la ruta: " + rutaTexto);
            }

        } catch (Exception e) {
            System.out.println("Error al cambiar imagen: " + e.getMessage());
            e.printStackTrace(); // Esto te ayudará a ver el error exacto en la consola
        }

    }

    private void procesar(Inputs input){
        UIEsenciales elementos=enemigoService.nuevoInput(input);
        actualizarVista(elementos);
    }
    @FXML
    void onCoberturaVisualizada(ActionEvent event) {
        procesar(Inputs.COBERTURA_VISUALIZADA);
    }

    @FXML
    void onContactoVisual(ActionEvent event) {
        procesar(Inputs.CONTACTO_VISUAL);
    }

    @FXML
    void onEnemigoCambioPasillo(ActionEvent event) {
        procesar(Inputs.ENEMIGO_CAMBIO_DE_PASILLO);
    }

    @FXML
    void onObjetivoEnMira(ActionEvent event) {
        procesar(Inputs.OBJETIVO_EN_MIRA);
    }

    @FXML
    void onRastroPerdido(ActionEvent event) {
        procesar(Inputs.RASTRO_PERDIDO);
    }

    @FXML
    void onRecibirAtaque(ActionEvent event) {
        procesar(Inputs.ATAQUE);
    }

    @FXML
    void onSinNovedad(ActionEvent event) {
        procesar(Inputs.SIN_NOVEDAD);
    }
}
