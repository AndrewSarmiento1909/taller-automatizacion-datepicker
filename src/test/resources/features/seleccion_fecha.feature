# language: es
Característica: Selección de fecha desde el calendario
  Como usuario de la aplicación
  Quiero seleccionar una fecha en el calendario
  Para evitar errores de digitación

  Esquema del escenario: Seleccionar una fecha del mes actual
    Dado que el usuario abre el selector de fecha
    Cuando selecciona el día <dia> del mes actual
    Entonces el campo muestra la fecha seleccionada en el formato esperado

    Ejemplos:
      | dia |
      |  7  |
      |  21 |

  Esquema del escenario: Seleccionar una fecha específica de un mes diferente al actual
    Dado que el usuario abre el selector de fecha
    Cuando selecciona el día <dia> del mes siguiente
    Entonces el campo muestra la fecha seleccionada en el formato esperado

    Ejemplos:
      | dia |
      |  7  |
      |  21 |

  Esquema del escenario: Seleccionar una fecha del año siguiente
    Dado que el usuario abre el selector de fecha
    Cuando selecciona el día <dia> del mismo mes del año siguiente
    Entonces el campo muestra la fecha seleccionada en el formato esperado

    Ejemplos:
      | dia |
      |  7  |
      |  21 |

  Escenario: Validar el límite inicial del rango permitido
    Dado que el usuario abre el calendario con rango restringido
    Cuando consulta el límite inicial del rango
    Entonces la fecha límite está habilitada y la anterior no se puede seleccionar

  Escenario: Validar el límite final del rango permitido
    Dado que el usuario abre el calendario con rango restringido
    Cuando consulta el límite final del rango
    Entonces la fecha límite está habilitada y la siguiente no se puede seleccionar
