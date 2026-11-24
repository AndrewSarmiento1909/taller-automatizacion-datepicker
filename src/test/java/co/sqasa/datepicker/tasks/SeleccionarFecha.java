package co.sqasa.datepicker.tasks;

import co.sqasa.datepicker.userinterfaces.Calendario;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SeleccionarFecha implements Task {
    private final LocalDate fecha;

    public SeleccionarFecha(LocalDate fecha) { this.fecha = fecha; }
    public static SeleccionarFecha enElCalendario(LocalDate fecha) {
        return Tasks.instrumented(SeleccionarFecha.class, fecha);
    }

    @Override
    @Step("{0} selecciona la fecha #fecha")
    public <T extends Actor> void performAs(T actor) {
        long meses = ChronoUnit.MONTHS.between(YearMonth.now(), YearMonth.from(fecha));
        if (meses < 0 || meses > 12) throw new IllegalArgumentException("La fecha debe estar entre el mes actual y los próximos 12 meses");
        actor.attemptsTo(Click.on(Calendario.CAMPO_FECHA),
                WaitUntil.the(Calendario.EMERGENTE, isVisible()).forNoMoreThan(10).seconds());
        for (int i = 0; i < meses; i++) actor.attemptsTo(Click.on(Calendario.SIGUIENTE));
        actor.attemptsTo(Click.on(Calendario.DIA.of(Integer.toString(fecha.getDayOfMonth()))));
    }
}
