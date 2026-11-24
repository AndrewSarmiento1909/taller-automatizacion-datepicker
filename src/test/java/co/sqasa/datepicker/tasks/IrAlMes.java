package co.sqasa.datepicker.tasks;

import co.sqasa.datepicker.userinterfaces.Calendario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public class IrAlMes implements Task {
    private final YearMonth mes;
    public IrAlMes(YearMonth mes) { this.mes = mes; }
    public static IrAlMes de(YearMonth mes) { return Tasks.instrumented(IrAlMes.class, mes); }
    @Override public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(Calendario.CAMPO_FECHA));
        long distancia = ChronoUnit.MONTHS.between(YearMonth.now(), mes);
        Target boton = distancia < 0 ? Target.the("mes anterior").locatedBy(".ui-datepicker-prev") : Calendario.SIGUIENTE;
        for (int i = 0; i < Math.abs(distancia); i++) actor.attemptsTo(Click.on(boton));
    }
}
