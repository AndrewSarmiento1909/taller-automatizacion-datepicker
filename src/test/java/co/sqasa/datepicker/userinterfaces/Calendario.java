package co.sqasa.datepicker.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public final class Calendario {
    public static final Target CAMPO_FECHA = Target.the("campo de fecha").locatedBy("#datepicker");
    public static final Target EMERGENTE = Target.the("calendario emergente").locatedBy("#ui-datepicker-div");
    public static final Target SIGUIENTE = Target.the("mes siguiente").locatedBy(".ui-datepicker-next");
    public static final Target DIA = Target.the("día {0} del mes visible")
            .locatedBy("//table[contains(@class,'ui-datepicker-calendar')]//td[not(contains(@class,'ui-datepicker-other-month'))]/a[text()='{0}']");
    private Calendario() { }
}
