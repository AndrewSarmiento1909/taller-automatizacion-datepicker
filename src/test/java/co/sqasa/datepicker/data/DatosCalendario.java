package co.sqasa.datepicker.data;

public final class DatosCalendario {
    private DatosCalendario() { }

    public static int diaSeleccion(int ejemplo) {
        String valor = System.getProperty("taller.dia", Integer.toString(ejemplo));
        try {
            int dia = Integer.parseInt(valor);
            if (dia < 1 || dia > 28) throw new IllegalArgumentException("El día debe estar entre 1 y 28 para ser válido en todos los meses");
            return dia;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El día configurado debe ser un número entre 1 y 28", e);
        }
    }
}
