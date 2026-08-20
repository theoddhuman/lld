package com.subham.lld.excel;


/**
 * Author: the_odd_human
 * Date: 16/04/25
 */
public class AddExpression implements Expression {
    private Expression op1;
    private Expression op2;

    public AddExpression(Expression op1, Expression op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    @Override
    public String evaluate() {
        return String.valueOf(Integer.parseInt(op1.evaluate()) + Integer.parseInt(op2.evaluate()));
    }
}
