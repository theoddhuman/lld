package com.subham.lld.excel;


import com.subham.lld.excel.utility.ExpressionUtil;

import java.util.*;

/**
 * Author: the_odd_human
 * Date: 16/04/25
 */
public class ExpressionBuilder {
    private Stack<Expression> expressions = new Stack<>();

    private Stack<String> operators = new Stack<>();

    private Map<String, String> cells;

    public ExpressionBuilder(Map<String, String> cells) {
        this.cells = cells;
    }

    public Expression build(String exp) {
        parse(exp);
        buildExpressions();
        if (expressions.size() > 1 || !operators.isEmpty()) {
            System.out.println("ERROR!");
        }
        return expressions.pop();
    }

    private void parse(String exp) {
        List<String> tokens = ExpressionUtil.tokenize(exp);
        for (String token : tokens) {
            switch (token) {
                case "+":
                    operators.push("ADD");
                    break;
                case "-":
                    operators.push("SUB");
                    break;
                default:
                    expressions.push(new ValueExpression(token, cells));
                    break;
            }
        }
    }

    private void buildExpressions() {
        while (!operators.isEmpty()) {
            String operator = operators.pop();
            Expression exp1;
            Expression exp2;
            Expression exp;
            switch (operator) {
                case "ADD":
                    exp1 = expressions.pop();
                    exp2 = expressions.pop();
                    exp = new AddExpression(exp1, exp2);
                    break;
                case "SUB":
                    exp1 = expressions.pop();
                    exp2 = expressions.pop();
                    exp = new SubExpression(exp2, exp1);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown operator:" + operator);
            }
            expressions.push(exp);
        }
    }
}
