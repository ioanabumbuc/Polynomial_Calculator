package model;
import java.util.ArrayList;

public class Polynomial{
    private ArrayList<Monomial> monomials;

    public Polynomial(ArrayList<Monomial> monomials) {
        this.monomials = monomials;
    }

    public ArrayList<Monomial> getMonomials() {
        return monomials;
    }

    public void setMonomials(ArrayList<Monomial> monomials) {
        this.monomials = monomials;
    }

    public void addToMonomialList(Monomial m){
        ArrayList <Monomial> monomials = this.getMonomials();
        monomials.add(m);
        this.setMonomials(monomials);
    }

    public Polynomial addPolynomial(Polynomial p){
        Polynomial result = new Polynomial(new ArrayList<>());
        boolean foundDeg;
        for(Monomial m1:this.getMonomials()){
            foundDeg=false;
            for(Monomial m2: p.getMonomials()){
                if(m1.getDegree()==m2.getDegree()){
                    if(m1.getCoeff()+m2.getCoeff()!=0) {
                        result.addToMonomialList(new Monomial(m1.getDegree(), m1.getCoeff() + m2.getCoeff()));
                    }
                    foundDeg=true;
                }
            }
            if(!foundDeg){
                result.addToMonomialList(new Monomial(m1.getDegree(),m1.getCoeff()));
            }
        }
        for(Monomial m2: p.getMonomials()){
            foundDeg=false;
            for(Monomial m1: this.getMonomials()){
                if(m1.getDegree() == m2.getDegree()){
                    foundDeg=true;
                    break;
                }
            }
            if(!foundDeg){
                result.addToMonomialList(new Monomial(m2.getDegree(),m2.getCoeff()));
            }
        }
        result.sortDegree();
        return result;
    }

    public Polynomial subtractPolynomial(Polynomial p){
        Polynomial result = new Polynomial(new ArrayList<>());
        boolean foundDeg;
        for(Monomial m1:this.getMonomials()){
            foundDeg=false;
            for(Monomial m2: p.getMonomials()){
                if(m1.getDegree()==m2.getDegree()){
                    if(m1.getCoeff()-m2.getCoeff()!=0) {
                        result.addToMonomialList(new Monomial(m1.getDegree(), m1.getCoeff() - m2.getCoeff()));
                    }
                    foundDeg=true;
                }
            }
            if(!foundDeg){
                result.addToMonomialList( new Monomial(m1.getDegree(),m1.getCoeff()));
            }
        }
        for(Monomial m2: p.getMonomials()){
            foundDeg=false;
            for(Monomial m1: this.getMonomials()){
                if(m1.getDegree() == m2.getDegree()){
                    foundDeg=true; break;
                }
            }
            if(!foundDeg){
                Monomial x = new Monomial(m2.getDegree(), -m2.getCoeff());
                result.addToMonomialList(x);
            }
        }
        result.sortDegree();
        return result;
    }

    public Polynomial multiplyPolynomial(Polynomial p){
        Polynomial partialResult = new Polynomial(new ArrayList<>());
        this.sortDegree();
        p.sortDegree();
        for(Monomial m1: this.getMonomials()){
            for(Monomial m2:p.getMonomials()){
                partialResult.addToMonomialList(new Monomial(m1.getDegree()+m2.getDegree(),m1.getCoeff()*m2.getCoeff()));
            }
        }
        partialResult.sortDegree();
        return normalizePolynomial(partialResult);
    }

    public Polynomial normalizePolynomial(Polynomial partialResult){
        Polynomial finalResult = new Polynomial(new ArrayList<>());
        for(int i=0;i<partialResult.getMonomials().size();i++){
            float coeff = partialResult.getMonomials().get(i).getCoeff();
            while(i+1 < partialResult.getMonomials().size() &&
                    partialResult.getMonomials().get(i).getDegree() == partialResult.getMonomials().get(i+1).getDegree()){
                coeff+=partialResult.getMonomials().get(i+1).getCoeff();
                i++;
            }
            finalResult.addToMonomialList(new Monomial(partialResult.getMonomials().get(i).getDegree(),coeff));
        }
        return  finalResult;
    }

    public ArrayList<Polynomial> dividePolynomial(Polynomial p){
        ArrayList<Polynomial> result = new ArrayList<>();
        Polynomial Q = new Polynomial(new ArrayList<>());
        Polynomial R = this;
        R.sortDegree();
        p.sortDegree();
        while(R.getMonomials().size()!=0 && R.getMonomials().get(0).getDegree() >= p.getMonomials().get(0).getDegree()){
            Monomial intermediateM = R.getMonomials().get(0).divide(p.getMonomials().get(0));
            Polynomial intermediateP = new Polynomial(new ArrayList<>());
            intermediateP.addToMonomialList(intermediateM);
            Q.addToMonomialList(intermediateM);
            R=R.subtractPolynomial(p.multiplyPolynomial(intermediateP));
        }
        if(Q.getMonomials().size()==0){
            Q.addToMonomialList(new Monomial(0,0));
        }
        if(R.getMonomials().size()==0){
            R.addToMonomialList(new Monomial(0,0));
        }
        result.add(Q);
        result.add(R);
        return result;
    }

    public Polynomial differentiatePolynomial(){
        Polynomial result = new Polynomial(new ArrayList<>());
        for(Monomial m:this.getMonomials()){
            if(m.getDegree()==0){ continue; }
            result.addToMonomialList(new Monomial(m.getDegree()-1,m.getCoeff()*m.getDegree()));
        }
        result.sortDegree();
        return result;
    }

    public Polynomial integratePolynomial(){
        Polynomial result = new Polynomial(new ArrayList<>());
        for(Monomial m: this.getMonomials()){
            result.addToMonomialList(new Monomial(m.getDegree()+1,m.getCoeff()/(m.getDegree()+1)));
        }
        result.sortDegree();
        return result;
    }

    public void sortDegree() {

        monomials.sort((m1, m2) -> Integer.compare(m2.getDegree(), m1.getDegree()));
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        this.sortDegree();
        for(Monomial m: this.getMonomials()){
            if(m.getCoeff()!=1.0f && m.getCoeff()!=-1.0f){
                if(m.getCoeff()>0){
                    if(m.getCoeff() != (long)m.getCoeff()){
                        s.append("+").append(String.format("%.2f", m.getCoeff()));
                    }
                    else{
                        s.append("+").append((long) m.getCoeff());
                    }
                }else{
                    if(m.getCoeff() != (long)m.getCoeff()){
                        s.append(String.format("%.2f", m.getCoeff()));
                    }else {
                        s.append((long) m.getCoeff());
                    }
                }
            }else if(m.getCoeff()==1.0f){
                if(this.getMonomials().indexOf(m) == this.getMonomials().size()-1 && m.getDegree()==0){ s.append("+1"); }
                else { s.append("+"); }
            }else{
                if(this.getMonomials().indexOf(m) == this.getMonomials().size()-1 && m.getDegree()==0){ s.append("-1"); }
                else { s.append("-"); }
            }
            if(m.getDegree()!=0){
                s.append("x");
                if(m.getDegree()!=1){
                    s.append("^").append(m.getDegree());
                }
            }
        }
        return s.toString();
    }

}
