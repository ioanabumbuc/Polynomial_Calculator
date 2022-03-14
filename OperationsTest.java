import model.Monomial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import model.Polynomial;
import java.util.ArrayList;

public class OperationsTest {

    public Polynomial createP1(){
        ArrayList<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(4,2));
        monomials.add(new Monomial(3,1));
        monomials.add(new Monomial(1,8));
        monomials.add(new Monomial(0,3));
        Polynomial p = new Polynomial(monomials);
       return p;
    }

    public Polynomial createP2(){
        ArrayList<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(5,6));
        monomials.add(new Monomial(3,-2));
        monomials.add(new Monomial(2,1));
        monomials.add(new Monomial(1, 3.5f));
        monomials.add(new Monomial(0,3));
        Polynomial p2 = new Polynomial(monomials);
        return p2;
    }

    public Polynomial createP3(){
        ArrayList<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(3,2));
        monomials.add(new Monomial(2,5));
        monomials.add(new Monomial(0,-1));
        Polynomial p3 = new Polynomial(monomials);
        return p3;
    }

    public Polynomial createP4(){
        ArrayList<Monomial> monomials = new ArrayList<>();
        monomials.add(new Monomial(2,1));
        monomials.add(new Monomial(0,6));
        Polynomial p4 = new Polynomial(monomials);
        return p4;
    }
    ///////////////////ADDITION TEST/////////////////////////
    @Test
    void addition() {

        Polynomial p = createP1();
        Polynomial p2 = createP2();

        ArrayList<Monomial> resultMon = new ArrayList<>();
        resultMon.add(new Monomial(5,6));
        resultMon.add(new Monomial(4,2));
        resultMon.add(new Monomial(3,-1));
        resultMon.add(new Monomial(2,1));
        resultMon.add(new Monomial(1,11.5f));
        resultMon.add(new Monomial(0,6));
        Polynomial expectedSum = new Polynomial(resultMon);
        Polynomial calculatedSum = p.addPolynomial(p2);

        for (int i = 0; i < expectedSum.getMonomials().size(); i++) {
            Assertions.assertEquals(expectedSum.getMonomials().get(i).getDegree(),
                    calculatedSum.getMonomials().get(i).getDegree());
            Assertions.assertEquals(expectedSum.getMonomials().get(i).getCoeff(),
                    calculatedSum.getMonomials().get(i).getCoeff());
        }
    }
    /////////////////////SUBTRACTION TEST/////////////////////
    @Test
    void subtraction(){

        Polynomial p = createP1();
        Polynomial p2 = createP2();

        ArrayList<Monomial> resultMon = new ArrayList<>();
        resultMon.add(new Monomial(5,-6));
        resultMon.add(new Monomial(4,2));
        resultMon.add(new Monomial(3,3));
        resultMon.add(new Monomial(2,-1));
        resultMon.add(new Monomial(1,4.5f));
        Polynomial expectedDiff = new Polynomial(resultMon);
        Polynomial calculatedDiff = p.subtractPolynomial(p2);

        for (int i = 0; i < expectedDiff.getMonomials().size(); i++) {
            Assertions.assertEquals(expectedDiff.getMonomials().get(i).getDegree(),calculatedDiff.getMonomials().get(i).getDegree());
            Assertions.assertEquals(expectedDiff.getMonomials().get(i).getCoeff(),calculatedDiff.getMonomials().get(i).getCoeff());
        }
    }
    //////////////////MULTIPLICATION TEST//////////////////////
    @Test
    void multiplication(){
        Polynomial p3 = createP3();
        Polynomial p4 = createP4();

        ArrayList<Monomial> resultMon = new ArrayList<>();
        resultMon.add(new Monomial(5,2));
        resultMon.add(new Monomial(4,5));
        resultMon.add(new Monomial(3,12));
        resultMon.add(new Monomial(2,29));
        resultMon.add(new Monomial(0,-6));
        Polynomial expectedProduct = new Polynomial(resultMon);
        Polynomial calculatedProduct = p3.multiplyPolynomial(p4);

        for (int i = 0; i < expectedProduct.getMonomials().size(); i++) {
            Assertions.assertEquals(expectedProduct.getMonomials().get(i).getDegree(),calculatedProduct.getMonomials().get(i).getDegree());
            Assertions.assertEquals(expectedProduct.getMonomials().get(i).getCoeff(),calculatedProduct.getMonomials().get(i).getCoeff());
        }
    }
    ///////////////////DIVISION TEST///////////////////////////
    @Test
    void division(){
        Polynomial p1 = createP1();
        Polynomial p4 = createP4();
        ArrayList<Polynomial> expectedResult = new ArrayList<>();
        
        ArrayList<Monomial> quot = new ArrayList<>();
        quot.add(new Monomial(2,2));
        quot.add(new Monomial(1,1));
        quot.add(new Monomial(0,-12));
        expectedResult.add(new Polynomial(quot));
        ArrayList<Monomial> rem = new ArrayList<>();
        rem.add(new Monomial(1,2));
        rem.add(new Monomial(0,75));
        expectedResult.add(new Polynomial(rem));
        
        ArrayList<Polynomial> calculatedResult = p1.dividePolynomial(p4);

        for (int i = 0; i < expectedResult.get(0).getMonomials().size(); i++) {
            Assertions.assertEquals(expectedResult.get(0).getMonomials().get(i).getDegree(),
                    calculatedResult.get(0).getMonomials().get(i).getDegree());
            Assertions.assertEquals(expectedResult.get(0).getMonomials().get(i).getCoeff(),
                    calculatedResult.get(0).getMonomials().get(i).getCoeff());
        }

        for (int i = 0; i < expectedResult.get(1).getMonomials().size(); i++) {
            Assertions.assertEquals(expectedResult.get(1).getMonomials().get(i).getDegree(),
                    calculatedResult.get(1).getMonomials().get(i).getDegree());
            Assertions.assertEquals(expectedResult.get(1).getMonomials().get(i).getCoeff(),
                    calculatedResult.get(1).getMonomials().get(i).getCoeff());
        }
    }
    ///////////////////DIFFERENTIATION TEST////////////////////
    @Test
    void differentiate(){
        Polynomial p = createP1();
        ArrayList<Monomial> resultMon = new ArrayList<>();
        resultMon.add(new Monomial(3,8));
        resultMon.add(new Monomial(2,3));
        resultMon.add(new Monomial(0,8));
        Polynomial expectedDiff = new Polynomial(resultMon);
        Polynomial calculatedDiff = p.differentiatePolynomial();
        for (int i = 0; i < expectedDiff.getMonomials().size(); i++) {
            Assertions.assertEquals(expectedDiff.getMonomials().get(i).getDegree(),calculatedDiff.getMonomials().get(i).getDegree());
            Assertions.assertEquals(expectedDiff.getMonomials().get(i).getCoeff(),calculatedDiff.getMonomials().get(i).getCoeff());
        }
    }
    /////////////////INTEGRATION TEST///////////////////////////
    @Test
    void integrate(){
        Polynomial p = createP1();
        ArrayList<Monomial> resultMon = new ArrayList<>();
        resultMon.add(new Monomial(5,0.4f));
        resultMon.add(new Monomial(4,0.25f));
        resultMon.add(new Monomial(2,4));
        resultMon.add(new Monomial(1,3));
        Polynomial expectedInt = new Polynomial(resultMon);
        Polynomial calculatedInt = p.integratePolynomial();
        for (int i = 0; i < expectedInt.getMonomials().size(); i++) {
            Assertions.assertEquals(expectedInt.getMonomials().get(i).getDegree(),calculatedInt.getMonomials().get(i).getDegree());
            Assertions.assertEquals(expectedInt.getMonomials().get(i).getCoeff(),calculatedInt.getMonomials().get(i).getCoeff());
        }
    }

}
