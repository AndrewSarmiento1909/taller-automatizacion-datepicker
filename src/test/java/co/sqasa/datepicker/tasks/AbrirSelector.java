package co.sqasa.datepicker.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import org.openqa.selenium.By;

public class AbrirSelector implements Task {
    public static AbrirSelector deFecha() { return Tasks.instrumented(AbrirSelector.class); }

    @Override
    @Step("{0} abre la demostración del selector de fecha")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url("https://jqueryui.com/datepicker/"));
        BrowseTheWeb.as(actor).getDriver().switchTo().frame(
                BrowseTheWeb.as(actor).getDriver().findElement(By.cssSelector("iframe.demo-frame")));
    }
}
