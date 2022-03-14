package model;

public class Monomial {
    private int degree;
    private float coeff;

    public Monomial(int degree, float coeff) {
        this.degree = degree;
        this.coeff = coeff;
    }

    public int getDegree() {
        return degree;
    }

    public float getCoeff() {
        return coeff;
    }

    public Monomial divide(Monomial m){
        Monomial result = new Monomial(this.getDegree()-m.getDegree(),this.getCoeff()/m.getCoeff());
        return result;
    }
}


