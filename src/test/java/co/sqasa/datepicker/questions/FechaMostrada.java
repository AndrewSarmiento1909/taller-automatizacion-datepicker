package co.sqasa.datepicker.questions;

import co.sqasa.datepicker.userinterfaces.Calendario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Value;

public class FechaMostrada implements Question<String> {
    public static FechaMostrada enElCampo() { return new FechaMostrada(); }
    @Override public String answeredBy(Actor actor) { return Value.of(Calendario.CAMPO_FECHA).answeredBy(actor); }
}
