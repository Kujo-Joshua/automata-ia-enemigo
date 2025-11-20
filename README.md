# NPC AI - Finite State Machine (FSM)

Simulador de Inteligencia Artificial para un NPC (Non-Playable Character) táctico, implementado mediante un Autómata Finito Determinista (AFD) con arquitectura limpia y renderizado en JavaFX.

# Demo y Visuales

(Interfaz gráfica reactiva con estética Neon inspirada en Hotline Miami)

Nota: Recuerda subir una captura de pantalla a una carpeta llamada assets y ponerle el nombre demo.png para que esta imagen cargue.

# Aspectos Técnicos Destacados (Engineering Highlights)

Este proyecto no es solo una implementación teórica, sino una aplicación de principios de Ingeniería de Software y Clean Code para resolver un problema de lógica de estados.

1. Arquitectura sin "Números Mágicos" (Type Safety)

    - En lugar de utilizar enteros crudos (int state = 0) que dificultan la lectura y el mantenimiento, se implementó el uso de Enums en Java:

    - Beneficio: Mayor legibilidad, seguridad de tipos (Type Safety) y prevención de estados inválidos.

    - Código: Estado.PATRULLANDO vs 0.

2. Implementación Matricial O(1) vs. Spaghetti Code

    - Se evitó el uso de cadenas anidadas de if/else o switch gigantes (Complejidad $O(n)$). En su lugar, se diseñó una Matriz de Transición de Estados:

    - Lógica: EstadoSiguiente = Matriz[EstadoActual][Input]

    - Rendimiento: Acceso a la transición en tiempo constante $O(1)$, lo cual es crítico para el rendimiento en videojuegos con múltiples agentes.

3. Patrón de Diseño MVC (Model-View-Controller)

    - El código está desacoplado en tres capas para garantizar la escalabilidad:

    - Model: (IAEnemigo) Contiene la lógica pura del autómata y la matriz de transición. No sabe nada de la interfaz gráfica.

    - View: (FXML + CSS) Definición declarativa de la UI y estilos.

    - Controller: (PrimaryController) Gestiona los eventos y comunica el modelo con la vista.

4. Características Modernas de Java (JDK 21)

    - Uso de Switch Expressions (Java 14+) para un código más conciso y funcional al determinar mensajes y rutas de recursos.

# Estructura del Proyecto

src/main/java/com/hotline
├── model/
│   ├── Estados.java      // Enum definitorio de Q (Conjunto de Estados)
│   ├── Inputs.java       // Enum definitorio de Σ (Alfabeto de Entrada)
│   └── IAEnemigo.java    // Lógica del Autómata (Matriz δ)
│   └── PrimaryController.java // Orquestador de eventos de UI
├── Launcher.java         // Punto de entrada para el Fat JAR
└── App.java              // Clase principal JavaFX


# Lógica del Autómata (State Machine)

    El agente NPC reacciona a estímulos del entorno basándose en su estado actual.

# Instalación y Ejecución

## Prerrequisitos

- Java JDK 21 o superior.

- Maven.

- Generar Ejecutable (Fat JAR)

- El proyecto utiliza maven-shade-plugin para empaquetar todas las dependencias de JavaFX en un solo archivo portable.

# Clonar el repositorio
    git clone [https://github.com/TuUsuario/hotline-miami-fsm.git](https://github.com/TuUsuario/hotline-miami-fsm.git)

# Construir el proyecto
    - mvn clean package


    - El ejecutable se generará en la carpeta target/ como automata-enemigo-1.0-SNAPSHOT.jar.

# Herramientas Utilizadas

    - Lenguaje: Java 21

    - GUI: JavaFX (con FXML y CSS personalizado)

    - Build Tool: Maven

    - Validación Teórica: JFLAP (Para diseño y pruebas del diagrama de estados)

Autor: Joshua Abad Murguia Garcia
Estudiante de Ingeniería en Computacion

[Mi linkedin](https://www.linkedin.com/in/joshua-abad-murguia-garcia-542a17288/)