package controller;

import model.Monomial;
import model.Polynomial;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.*;

public class Controller {
    private final View view;

    public Controller(View view) {
        this.view = view;
        view.addOperationListener(new operationListener());
    }

    public Polynomial readPolynomial(String s){
        Polynomial polynomial = new Polynomial(new ArrayList<>());
        String format = "([+-]?[^-+]+)";
        Pattern p = Pattern.compile(format);
        Matcher m = p.matcher(s);
        while(!m.hitEnd()) {
            if (m.find()) {
                String group = m.group();
                String coeffString="";
                String degString="";
                Float coeff=1.0f;
                int deg=0;
                Monomial monomial = readMonomial(group,coeffString,degString,coeff,deg);
                polynomial.addToMonomialList(monomial);
            }else{ throw new RuntimeException("Invalid input");}
        }
        return polynomial;
    }

    public Monomial readMonomial(String group,String coeffString, String degString, Float coeff, int deg){
        int i=0;
        if(group.charAt(i)=='+' || group.charAt(i)=='-') { //CASE WHEN GROUP STARTS WITH +/-
            if(group.charAt(i)=='-') {
                coeff *= -1;
            }
            i++;
        }
        while(i<group.length() && isNumeric(group.charAt(i))){ //reads value of coefficient
            coeffString=coeffString.concat(String.valueOf(group.charAt(i)));
            i++;
        }
        if(!coeffString.isEmpty()) {
            coeff *= Float.parseFloat(coeffString);
        }
        //reads degree of monomial
        if(i<group.length() && isLetter(group.charAt(i))){ //degree is not zero
            i++;
            if(i<group.length() && group.charAt(i)=='^'){
                i++;
                while(i<group.length() && isNumeric(group.charAt(i))){
                    degString=degString.concat(String.valueOf(group.charAt(i)));
                    i++;
                }
                if(!degString.isEmpty()) {
                    deg = Integer.parseInt(degString);
                }else {
                    throw new RuntimeException("Invalid Input");
                }
            } else{ //no '^' means degree is 1
                deg=1;
            }
        }
        return new Monomial(deg,coeff);
    }

    public boolean isNumeric(char c){
        return Character.isDigit(c);
    }

    public boolean isLetter(char c){
        return Character.isLetter(c);
    }

    class operationListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedOp = view.getOperation();
            Polynomial p1 = readPolynomial(view.getPolynomial1());
            Polynomial p2 = readPolynomial(view.getPolynomial2());
            Polynomial result;
            switch (selectedOp) {
                case "Add" -> {
                    result = p1.addPolynomial(p2);
                    view.showResult(result.toString());
                }
                case "Subtract" -> {
                    result = p1.subtractPolynomial(p2);
                    view.showResult(result.toString());
                }
                case "Multiply" -> {
                    result = p1.multiplyPolynomial(p2);
                    view.showResult(result.toString());
                }
                case "Divide" ->
                    view.showResult(p1.dividePolynomial(p2).get(0).toString(),p1.dividePolynomial(p2).get(1).toString());
                case "Differentiate" -> {
                    result = p1.differentiatePolynomial();
                    view.showResult(result.toString());
                }
                default -> {
                    result = p1.integratePolynomial();
                    view.showResult(result.toString());
                }
            }
        }
    }

}
