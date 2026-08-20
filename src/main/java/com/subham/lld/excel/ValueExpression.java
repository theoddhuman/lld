package com.subham.lld.excel;


import java.util.Map;

/**
 * Author: the_odd_human
 * Date: 16/04/25
 */
public class ValueExpression implements Expression{
    private final String cell;
    private Map<String, String> cells;

    public ValueExpression(String cell, Map<String, String> cells) {
        this.cell = cell;
        this.cells = cells;
    }

    @Override
    public String evaluate() {
        return cells.get(cell);
    }
}
