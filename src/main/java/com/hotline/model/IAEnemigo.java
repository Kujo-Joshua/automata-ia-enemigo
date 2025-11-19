package com.hotline.model;

public class IAEnemigo {
    // Matriz de transición: [Fila = Estado Actual][Columna = Input]
    private Estados[][] matrizTransicion;
    
    private Estados estadoActual = Estados.PATRULLANDO;

    public IAEnemigo() {
        int numEstados = Estados.values().length;
        int numInputs = Inputs.values().length;
        matrizTransicion = new Estados[numEstados][numInputs];

        inicializarReglas();
    }

    public void setEstadoActual(Estados estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Estados getEstadoActual() {
        return estadoActual;
    }

    public Estados calcularSiguienteEstado(Inputs input, Estados estadoActual) {
        // Protección contra índices fuera de rango (opcional pero recomendada)
        if (input == null || estadoActual == null) return estadoActual;
        return matrizTransicion[estadoActual.ordinal()][input.ordinal()];
    }

    private void inicializarReglas() {
        // 1. Inicialización defensiva: Loopback (quedarse en el mismo estado)
        for (Estados estado : Estados.values()) {
            for (Inputs input : Inputs.values()) {
                matrizTransicion[estado.ordinal()][input.ordinal()] = estado;
            }
        }

        // 2. Definición de Transiciones (Tu lógica de Hotline Miami)

        // --- Desde PATRULLANDO ---
        matrizTransicion[Estados.PATRULLANDO.ordinal()][Inputs.CONTACTO_VISUAL.ordinal()] = Estados.PERSIGUIENDO;
        matrizTransicion[Estados.PATRULLANDO.ordinal()][Inputs.ATAQUE.ordinal()]          = Estados.ATURDIDO;

        // --- Desde PERSIGUIENDO ---
        matrizTransicion[Estados.PERSIGUIENDO.ordinal()][Inputs.CONTACTO_VISUAL.ordinal()]       = Estados.APUNTANDO;
        matrizTransicion[Estados.PERSIGUIENDO.ordinal()][Inputs.RASTRO_PERDIDO.ordinal()]        = Estados.CONFUNDIDO;
        matrizTransicion[Estados.PERSIGUIENDO.ordinal()][Inputs.COBERTURA_VISUALIZADA.ordinal()] = Estados.APUNTANDO;
        matrizTransicion[Estados.PERSIGUIENDO.ordinal()][Inputs.ATAQUE.ordinal()]                = Estados.ATURDIDO;
        matrizTransicion[Estados.PERSIGUIENDO.ordinal()][Inputs.ENEMIGO_CAMBIO_DE_PASILLO.ordinal()] = Estados.APUNTANDO;

        // --- Desde DISPARANDO ---
        matrizTransicion[Estados.DISPARANDO.ordinal()][Inputs.SIN_NOVEDAD.ordinal()]             = Estados.CONFUNDIDO;
        matrizTransicion[Estados.DISPARANDO.ordinal()][Inputs.CONTACTO_VISUAL.ordinal()]         = Estados.PERSIGUIENDO;
        matrizTransicion[Estados.DISPARANDO.ordinal()][Inputs.RASTRO_PERDIDO.ordinal()]          = Estados.CONFUNDIDO;
        matrizTransicion[Estados.DISPARANDO.ordinal()][Inputs.COBERTURA_VISUALIZADA.ordinal()]   = Estados.APUNTANDO;
        matrizTransicion[Estados.DISPARANDO.ordinal()][Inputs.ATAQUE.ordinal()]                  = Estados.ATURDIDO;
        matrizTransicion[Estados.DISPARANDO.ordinal()][Inputs.ENEMIGO_CAMBIO_DE_PASILLO.ordinal()] = Estados.CONFUNDIDO;

        // --- Desde Apuntando ---
        matrizTransicion[Estados.APUNTANDO.ordinal()][Inputs.SIN_NOVEDAD.ordinal()]             = Estados.DISPARANDO;
        matrizTransicion[Estados.APUNTANDO.ordinal()][Inputs.CONTACTO_VISUAL.ordinal()]         = Estados.DISPARANDO;
        matrizTransicion[Estados.APUNTANDO.ordinal()][Inputs.RASTRO_PERDIDO.ordinal()]          = Estados.CONFUNDIDO;
        matrizTransicion[Estados.APUNTANDO.ordinal()][Inputs.COBERTURA_VISUALIZADA.ordinal()]   = Estados.DISPARANDO;
        matrizTransicion[Estados.APUNTANDO.ordinal()][Inputs.ATAQUE.ordinal()]                  = Estados.DISPARANDO;
        matrizTransicion[Estados.APUNTANDO.ordinal()][Inputs.ENEMIGO_CAMBIO_DE_PASILLO.ordinal()] = Estados.DISPARANDO;
         matrizTransicion[Estados.APUNTANDO.ordinal()][Inputs.OBJETIVO_EN_MIRA.ordinal()] = Estados.DISPARANDO;


        // --- Desde CONFUNDIDO ---
        matrizTransicion[Estados.CONFUNDIDO.ordinal()][Inputs.SIN_NOVEDAD.ordinal()]         = Estados.LLAMANDO_REFUERZOS;
        matrizTransicion[Estados.CONFUNDIDO.ordinal()][Inputs.CONTACTO_VISUAL.ordinal()]         = Estados.PERSIGUIENDO;
        matrizTransicion[Estados.CONFUNDIDO.ordinal()][Inputs.RASTRO_PERDIDO.ordinal()]          = Estados.LLAMANDO_REFUERZOS;
        matrizTransicion[Estados.CONFUNDIDO.ordinal()][Inputs.ATAQUE.ordinal()]                  = Estados.ATURDIDO;
        matrizTransicion[Estados.CONFUNDIDO.ordinal()][Inputs.ENEMIGO_CAMBIO_DE_PASILLO.ordinal()] = Estados.LLAMANDO_REFUERZOS;

        // --- Desde ATURDIDO ---
        matrizTransicion[Estados.ATURDIDO.ordinal()][Inputs.SIN_NOVEDAD.ordinal()]               = Estados.CONFUNDIDO;
        matrizTransicion[Estados.ATURDIDO.ordinal()][Inputs.CONTACTO_VISUAL.ordinal()]           = Estados.CONFUNDIDO;
        matrizTransicion[Estados.ATURDIDO.ordinal()][Inputs.RASTRO_PERDIDO.ordinal()]            = Estados.CONFUNDIDO;
        matrizTransicion[Estados.ATURDIDO.ordinal()][Inputs.ATAQUE.ordinal()]                    = Estados.MUERTO;
        matrizTransicion[Estados.ATURDIDO.ordinal()][Inputs.ENEMIGO_CAMBIO_DE_PASILLO.ordinal()] = Estados.CONFUNDIDO;

        // --- Desde LLAMANDO REFUERZOS ---
        matrizTransicion[Estados.LLAMANDO_REFUERZOS.ordinal()][Inputs.SIN_NOVEDAD.ordinal()]     = Estados.PATRULLANDO;
        matrizTransicion[Estados.LLAMANDO_REFUERZOS.ordinal()][Inputs.CONTACTO_VISUAL.ordinal()] = Estados.PERSIGUIENDO;
        matrizTransicion[Estados.LLAMANDO_REFUERZOS.ordinal()][Inputs.ATAQUE.ordinal()]          = Estados.ATURDIDO;
        matrizTransicion[Estados.LLAMANDO_REFUERZOS.ordinal()][Inputs.ENEMIGO_CAMBIO_DE_PASILLO.ordinal()] = Estados.PATRULLANDO;
    }

    public String getMensajeCorrespondiente() {
        // Switch Expression de Java moderno (Requiere Java 14+)
        return switch (estadoActual) {
            case PATRULLANDO        -> "El enemigo está patrullando la zona.";
            case PERSIGUIENDO       -> "¡Te ha visto! El enemigo te está persiguiendo.";
            case APUNTANDO          -> "¡Cuidado! El enemigo te tiene en la mira.";
            case DISPARANDO         -> "¡Fuego! El enemigo está disparando.";
            case CONFUNDIDO         -> "¿Huh? El enemigo perdió tu rastro y está confundido.";
            case ATURDIDO           -> "¡Golpe exitoso! El enemigo está aturdido.";
            case LLAMANDO_REFUERZOS -> "El enemigo está pidiendo refuerzos por radio.";
            case MUERTO             -> "Enemigo neutralizado.";
            default                 -> "Estado desconocido: " + estadoActual;
        };
    }

    public String getRutaImagen() {
        // Ruta base dentro de resources (Asegúrate que la carpeta 'images' exista)
        String baseDir = "/com/hotline/images/";
        
        String nombreArchivo = switch (estadoActual) {
            case PATRULLANDO        -> "Patrullando.jpg";
            case PERSIGUIENDO       -> "Persiguiendo.jpg";
            case APUNTANDO          -> "Apuntando.jpg";
            case DISPARANDO         -> "Disparo.jpg";    // En tu foto se llama "Disparo"
            case CONFUNDIDO         -> "Confundido.jpg";
            case LLAMANDO_REFUERZOS -> "Refuerzos.jpg";  // En tu foto se llama "Refuerzos"
            case MUERTO             -> "Muerto.jpg";
            
            case ATURDIDO           -> "Aturdido.jpg"; 
            
            default                 -> "Patrullando.jpg"; // Fallback por si acaso
        };

        return baseDir + nombreArchivo;
    }
}