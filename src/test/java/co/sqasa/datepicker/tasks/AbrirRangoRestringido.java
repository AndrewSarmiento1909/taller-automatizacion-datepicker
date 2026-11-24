package co.sqasa.datepicker.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class AbrirRangoRestringido implements Task {
    public static AbrirRangoRestringido enLaDemo() { return Tasks.instrumented(AbrirRangoRestringido.class); }
    @Override public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url("https://jqueryui.com/resources/demos/datepicker/min-max.html"));
    }
}
