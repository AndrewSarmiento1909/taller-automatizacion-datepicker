package co.sqasa.datepicker.stepdefinitions;

import co.sqasa.datepicker.questions.FechaMostrada;
import co.sqasa.datepicker.data.DatosCalendario;
import co.sqasa.datepicker.tasks.AbrirSelector;
import co.sqasa.datepicker.tasks.SeleccionarFecha;
import co.sqasa.datepicker.tasks.AbrirRangoRestringido;
import co.sqasa.datepicker.tasks.IrAlMes;
import co.sqasa.datepicker.questions.LimiteDelCalendario;
import co.sqasa.datepicker.userinterfaces.Calendario;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import org.openqa.selenium.Dimension;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class SeleccionFechaSteps {
    private final Actor usuario = Actor.named("Usuario");
    private LocalDate fecha;
    private boolean limiteInicial;

    @Dado("que el usuario abre el selector de fecha")
    public void abreSelector() {
        usuario.can(BrowseTheWeb.with(Serenity.getDriver()));
        if (Boolean.getBoolean("taller.viewport.movil")) {
            Serenity.getDriver().manage().window().setSize(new Dimension(390, 844));
        }
        usuario.attemptsTo(AbrirSelector.deFecha());
    }

    @Cuando("selecciona el día {int} del mes actual")
    public void seleccionaMesActual(int dia) { seleccionar(LocalDate.now().withDayOfMonth(DatosCalendario.diaSeleccion(dia))); }

    @Cuando("selecciona el día {int} del mes siguiente")
    public void seleccionaMesSiguiente(int dia) { seleccionar(LocalDate.now().plusMonths(1).withDayOfMonth(DatosCalendario.diaSeleccion(dia))); }

    @Cuando("selecciona el día {int} del mismo mes del año siguiente")
    public void seleccionaAnoSiguiente(int dia) { seleccionar(LocalDate.now().plusYears(1).withDayOfMonth(DatosCalendario.diaSeleccion(dia))); }

    private void seleccionar(LocalDate seleccion) {
        fecha = seleccion;
        usuario.attemptsTo(SeleccionarFecha.enElCalendario(fecha));
    }

    @Entonces("el campo muestra la fecha seleccionada en el formato esperado")
    public void verificaFecha() {
        usuario.should(seeThat("fecha mostrada", FechaMostrada.enElCampo(),
                equalTo(fecha.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) )));
    }

    @Dado("que el usuario abre el calendario con rango restringido")
    public void abreRango() {
        usuario.can(BrowseTheWeb.with(Serenity.getDriver()));
        if (Boolean.getBoolean("taller.viewport.movil")) Serenity.getDriver().manage().window().setSize(new Dimension(390, 844));
        usuario.attemptsTo(AbrirRangoRestringido.enLaDemo());
    }

    @Cuando("consulta el límite inicial del rango")
    public void consultaInicial() { limiteInicial = true; fecha = LocalDate.now().minusDays(20); usuario.attemptsTo(IrAlMes.de(YearMonth.from(fecha))); }

    @Cuando("consulta el límite final del rango")
    public void consultaFinal() { limiteInicial = false; fecha = LocalDate.now().plusMonths(1).plusDays(10); usuario.attemptsTo(IrAlMes.de(YearMonth.from(fecha))); }

    @Entonces("la fecha límite está habilitada y la anterior no se puede seleccionar")
    @Entonces("la fecha límite está habilitada y la siguiente no se puede seleccionar")
    public void validaLimite() {
        usuario.should(seeThat("límite y fecha fuera de rango", LimiteDelCalendario.para(fecha, limiteInicial), equalTo(true)));
        usuario.attemptsTo(Click.on(Calendario.DIA.of(Integer.toString(fecha.getDayOfMonth()))));
        usuario.should(seeThat("fecha límite seleccionada", FechaMostrada.enElCampo(),
                equalTo(fecha.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))));
    }
}
