package co.sqasa.datepicker.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.LocalDate;
import java.time.YearMonth;

public class LimiteDelCalendario implements Question<Boolean> {
    private final LocalDate limite;
    private final boolean inicial;
    private LimiteDelCalendario(LocalDate limite, boolean inicial) { this.limite = limite; this.inicial = inicial; }
    public static LimiteDelCalendario para(LocalDate limite, boolean inicial) { return new LimiteDelCalendario(limite, inicial); }
    @Override public Boolean answeredBy(Actor actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        String dia = Integer.toString(limite.getDayOfMonth());
        boolean habilitado = !driver.findElements(By.xpath("//table[contains(@class,'ui-datepicker-calendar')]//td/a[text()='" + dia + "']")).isEmpty();
        LocalDate fuera = inicial ? limite.minusDays(1) : limite.plusDays(1);
        if (!YearMonth.from(limite).equals(YearMonth.from(fuera))) {
            String selector = inicial ? ".ui-datepicker-prev" : ".ui-datepicker-next";
            return habilitado && (driver.findElements(By.cssSelector(selector)).isEmpty()
                    || driver.findElement(By.cssSelector(selector)).getAttribute("class").contains("ui-state-disabled"));
        }
        String xpath = "//table[contains(@class,'ui-datepicker-calendar')]//td[contains(@class,'ui-datepicker-unselectable')]/span[text()='" + fuera.getDayOfMonth() + "']";
        return habilitado && !driver.findElements(By.xpath(xpath)).isEmpty();
    }
}
