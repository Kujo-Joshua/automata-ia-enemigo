package com.hotline.service;

import com.hotline.model.Estados;
import com.hotline.model.IAEnemigo;
import com.hotline.model.Inputs;
import com.hotline.model.UIEsenciales;

public class EnemigoService {
    IAEnemigo enemigo= new IAEnemigo();

    public UIEsenciales nuevoInput(Inputs input){
        Estados estadoAcual=enemigo.getEstadoActual();
        enemigo.setEstadoActual(enemigo.calcularSiguienteEstado(input, estadoAcual));

        return new UIEsenciales(enemigo.getMensajeCorrespondiente(), enemigo.getRutaImagen());
    }

}
