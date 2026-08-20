package com.subham.lld.excel;


import com.subham.lld.excel.utility.ExpressionUtil;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Author: the_odd_human
 * Date: 16/04/25
 */
public class Excel {
    Map<String, String> cells;
    Map<String, Expression> expressions;
    //Map<String, Set<String>> dependencies;
    Map<String, Set<String>> dependents;
    ExpressionBuilder expressionBuilder;

    public Excel() {
        cells = new HashMap<>();
        //dependencies = new HashMap<>();
        dependents = new HashMap<>();
        expressions = new HashMap<>();
        this.expressionBuilder = new ExpressionBuilder(cells);
    }

//    public String getValue(String cell) {
//        return cells.getOrDefault(cell, "0");
//    }

    public String getValueNew(String cell) {
        return cells.get(cell);
    }

    public void setValueNew(String cell, String value) {
        if(isFormula(value)) {
            Expression expression =  expressionBuilder.build(value);
            expressions.put(cell, expression);
            List<String> tokens = ExpressionUtil.tokenize(value);
            for(String token : tokens) {
                if(!isOperator(token)) {
                    this.dependents.putIfAbsent(token, new HashSet<>());
                    this.dependents.get(token).add(cell);
                }
            }
            evaluate(cell);
        } else {
            cells.put(cell, value);
        }
    }

    public void evaluate(String cell) {
        cells.put(cell, expressions.get(cell).evaluate());
        if(CollectionUtils.isEmpty(dependents.get(cell))) {
            return;
        }
        for(String dependent : dependents.get(cell)) {
            evaluate(dependent);
        }
    }

//    public void setValue(String cell, String value) {
//        dependencies.putIfAbsent(cell, new HashSet<>());
//        dependents.put(cell, new HashSet<>());
//        if(cells.containsKey(cell)) {
//            for(String dependency : dependencies.get(cell)) {
//                dependents.get(dependency).remove(cell);
//            }
//            dependencies.put(cell, new HashSet<>());
//        }
//        if(isFormula(value)) {
//            String[] dCells = value.split("\\+");
//            for(String dCell : dCells) {
//                dependencies.get(cell).add(dCell);
//                dependents.putIfAbsent(dCell, new HashSet<>());
//                dependents.get(dCell).add(cell);
//            }
//        }
//        cells.put(cell, value);
//        recalculate(cell);
//    }

    public void print() {
        System.out.println(cells);
    }

    private boolean isFormula(String value) {
        try {
            Integer.parseInt(value);
            return false;
        } catch (NumberFormatException e) {
           return true;
        }
    }

    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("/") || s.equals("*") || s.equals("-");
    }
//
//    private String evaluateExpression(String cell) {
//        Set<String> dependencies = this.dependencies.get(cell);
//        if(dependencies.isEmpty()) {
//            return cells.get(cell);
//        }
//        int sum = 0;
//        for (String dependency : dependencies) {
//            sum += Integer.parseInt(cells.get(dependency));
//        }
//        return String.valueOf(sum);
//    }
//
//    private void recalculate(String cell) {
//        Set<String> dependents = this.dependents.get(cell);
//        cells.put(cell, evaluateExpression(cell));
//        for(String dependent : dependents) {
//            recalculate(dependent);
//        }
//    }
}
