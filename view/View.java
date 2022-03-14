package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {

    private final Font f = new Font("Roboto",Font.PLAIN,16);
    private final Font fbold = new Font("Roboto",Font.BOLD,16);
    private final Font fitalic = new Font("Roboto",Font.ITALIC,12);
    private final JLabel pol1 = new JLabel("Polynomial 1 ");
    private final JLabel pol2 = new JLabel("Polynomial 2 ");
    private final JLabel chooseOp = new JLabel("Choose Operation:");
    private final JTextField pol1Field = new JTextField("");
    private final JTextField pol2Field = new JTextField("");
    private final JButton calculate = new JButton("Calculate!");
    private final JLabel result = new JLabel("Result: ");
    private final String[] opString = {"Add","Subtract","Multiply","Divide","Differentiate","Integrate"};
    private final JComboBox oper = new JComboBox(opString);
    private final JLabel note = new JLabel("Differentiation and integration on 1st polynomial only");
    private final JLabel quot = new JLabel("Quotient: ");
    private final JLabel rem = new JLabel("Remainder: ");

    public View(){
        setSize(400,550);
        setLayout(null);
        setTitle("Polynomial Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#D6E3F3"));
        showResult("");
        addComponents();
    }

    private void addComponents(){
        pol1.setBounds(40,30,150,50);
        pol1.setFont(f);
        add(pol1);

        pol1Field.setBounds(40,80,300,30);
        pol1Field.setFont(f);
        add(pol1Field);

        pol2.setBounds(40,130,150,30);
        pol2.setFont(f);
        add(pol2);

        pol2Field.setBounds(40,170,300,30);
        pol2Field.setFont(f);
        add(pol2Field);

        note.setBounds(40,200,300,30);
        note.setFont(fitalic);
        note.setForeground(Color.red);
        add(note);

        chooseOp.setBounds(110,250,300,30);
        chooseOp.setFont(fbold);
        add(chooseOp);

        oper.setBounds(40,300,300,30);
        oper.setFont(f);
        //oper.setBackground(Color.decode("#FFDBE7"));
        oper.setBackground(Color.decode("#BDD4E6"));
        add(oper);

        calculate.setBounds(120,360,130,30);
        calculate.setFont(fbold);
        calculate.setBackground(Color.decode("#00B3E5"));

        add(calculate);
    }
    
    public String getPolynomial1(){
        return pol1Field.getText();
    }

    public String getPolynomial2(){
        return pol2Field.getText();
    }

    public String getOperation(){
        return oper.getSelectedItem().toString();
    }

    public void showResult(String res) {
        quot.setVisible(false);
        rem.setVisible(false);

        result.setBounds(40,410,280,30);
        result.setFont(fbold);
        result.setText("Result: "+res);
        result.setVisible(true);
        add(result);
    }

    public void showResult(String quotient, String remainder){
        result.setVisible(false);
        quot.setBounds(40,410,280,30);
        quot.setFont(fbold);
        quot.setText("Quotient: "+quotient);
        quot.setVisible(true);
        add(quot);

        rem.setBounds(40,440,280,30);
        rem.setFont(fbold);
        rem.setText("Remainder: "+remainder);
        rem.setVisible(true);
        add(rem);
    }

    public void addOperationListener(ActionListener actionListener){
        calculate.addActionListener(actionListener);
    }
}
