package com.subham.lld.excel;


/**
 * Author: the_odd_human
 * Date: 16/04/25
 */
public class Test {
    public static void main(String[] args) {
        Excel excel = new Excel();
        excel.setValueNew("A1", "2");
        excel.setValueNew("A2", "3");
        excel.setValueNew("A3", "A1+A2");
        excel.setValueNew("A4", "A2+A3");
        excel.setValueNew("A5", "A3+A4");
        excel.setValueNew("A6", "A3+A4-A1");
        excel.print();
    }
}
