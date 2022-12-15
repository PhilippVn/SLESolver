/*
 * Application Entry Point
 */
package slesolver;

import math.Fraction;
import ui.Ui;

public class SLESolver {
    public static void main(String[] args) {
        //new Ui();

        Fraction a = new Fraction(1,5);
        Fraction b = new Fraction(6,-9);
        System.out.println(new Fraction(1,5) + " + " + b + " = " + a.add(b).reduce());
        a = new Fraction(1,5);
        System.out.println(new Fraction(1,5) + " - " + b + " = " + a.sub(b).reduce());
        a = new Fraction(1,5);
        System.out.println(new Fraction(1,5) + " * " + b + " = " + a.mul(b).reduce());
        a = new Fraction(1,5);
        System.out.println(new Fraction(1,5) + " / " + b + " = " + a.div(b).reduce());

        a = new Fraction(5,10);
        b = new Fraction(10,20);
        System.out.println(a + " == " + b + " : " + a.equals(b));

        System.out.println(a + " > " + b + " : " + (a.compareTo(b.neg()) > 0));


        System.out.println(new Fraction(5,1));
        System.out.println(new Fraction(1,-6));
        System.out.println(new Fraction(-1,-6));
        System.out.println(Fraction.FRACTION_ONE);
        System.out.println(Fraction.FRACTION_ZERO);

        a = new Fraction(1,5);
        b = new Fraction(6,-9);
        System.out.println(a + " + " + b + " = " + Fraction.reduce(Fraction.add(a,b)));
        System.out.println(a + " - " + b + " = " + Fraction.reduce(Fraction.sub(a,b)));
        System.out.println(a + " * " + b + " = " + Fraction.reduce(Fraction.mul(a,b)));
        System.out.println(a + " / " + b + " = " + Fraction.reduce(Fraction.div(a,b)));

    }
}
