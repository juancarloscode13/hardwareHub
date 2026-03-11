package com.juanCarlos.hardwareHub.dsl.parser;

import com.juanCarlos.hardwareHub.dsl.model.FilterCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Se encarga de leer el texto de filtro que llega desde los argumentos de la URL y convertirlo en una lista de filtros
 * individuales (FilterCriteria).
 *
 * @author Juan Carlos
 * @see FilterCriteria
 */
public class QueryDslParser {

    private static final Pattern PATTERN = Pattern.compile("(\\w+)(==|!=|>=|<=|>|<|~)(.+)");

    public List<FilterCriteria> parse(String query) {
        List<FilterCriteria> filters = new ArrayList<>();

        if (query == null || query.isEmpty())
            return filters;

        String[] expressions = query.split(";");

        for (String exp : expressions) {

            Matcher matcher = PATTERN.matcher(exp);

            if (matcher.find()) {

                filters.add(
                        new FilterCriteria(
                                matcher.group(1),
                                matcher.group(2),
                                matcher.group(3)));
            }
        }
        return filters;
    }
}
