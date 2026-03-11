package com.juanCarlos.hardwareHub.dsl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un filtro individual de búsqueda. Los datos que se quieren guardar son el campo por el que se quiere
 * filtrar (field), el operador que determina que tipo de comparación se va a realizar (operator) y el valor respecto
 * al cual se va a realizar la comparación. Por ejemplo: "precio > 100" se guardaría como field="precio", operator=">",
 * value="100".
 *
 * @author Juan Carlos
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterCriteria {
    private String field;
    private String operator;
    private String value;
}
