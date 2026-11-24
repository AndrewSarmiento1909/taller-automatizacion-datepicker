# Taller HU-001: selector de fecha

Automatización nueva de la demostración pública de jQuery UI. Tres pruebas Screenplay seleccionan un día configurable del mes actual, del siguiente y del mismo mes del año siguiente, con su valor en formato MM/dd/yyyy. Otras dos pruebas seleccionan la fecha mínima y máxima del ejemplo de rango restringido y comprueban los días que quedan justo fuera de ellas.

## Ejecución

Requisitos: JDK 17, el navegador y acceso a Internet. Selenium Manager necesita obtener un controlador compatible. El informe se genera en `target/site/serenity/index.html`. Las fechas se calculan a partir del reloj local.

```powershell
.\gradlew.bat clean test -Pbrowser=chrome
.\gradlew.bat clean test -Pbrowser=firefox
.\gradlew.bat clean test -Pbrowser=chrome -Pmovil=true
.\gradlew.bat clean test -Pbrowser=chrome -Pdia=7
```

Los días de selección están en las tablas `Ejemplos` del archivo `seleccion_fecha.feature`. Cada fila genera una ejecución. Para probar otro día en una ejecución puntual se puede usar `-Pdia`. Al usarlo, ese valor reemplaza cada ejemplo de día durante esa ejecución. Se aceptan días del 1 al 28 para que sean válidos en todos los meses. Las fechas mínima y máxima provienen de la configuración del ejemplo restringido.

La opción `-Pmovil=true` usa una ventana de 390 × 844 en Chrome de escritorio. No equivale a un navegador móvil real ni verifica interacción táctil.

El proyecto usa Gradle 8.14.7, Serenity 4.2.34, Cucumber y Screenplay.

## Estructura Screenplay

- `src/test/resources/features/seleccion_fecha.feature`: escenarios BDD en Gherkin.
- `runners/DatepickerRunner`: ejecuta Cucumber con Serenity.
- `stepdefinitions/SeleccionFechaSteps`: relaciona los pasos con el actor, las tareas y la pregunta.
- `tasks/`: apertura de la página y selección de la fecha.
- `questions/FechaMostrada`: obtiene el valor para la validación.
- `userinterfaces/Calendario`: localizadores del selector.
- `data/DatosCalendario`: valida el día del ejemplo y permite sobrescribirlo desde Gradle.
