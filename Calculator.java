import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

import static java.awt.Color.*;

public class Calculator extends JFrame implements ActionListener {
    double result = 0;
    String count(ArrayList<String> operation, String value){
        if(value.isEmpty()) return "error";
        else {
            try {
                String[] sValues = value.split("[\\+\\*\\-\\/]");
                String blad = "";
                ArrayList<Double> dValues = new ArrayList<>();
                for (int i = 0; i < sValues.length; i++) {
                    for (int j = 0; j < sValues[i].length(); j++) {
                        if (sValues[i].charAt(j) == '(') {
                            sValues[i] = sValues[i].substring(1);
                        } else if (sValues[i].charAt(j) == ')') {
                            sValues[i] = sValues[i].substring(0, sValues[i].length() - 1);
                        }
                    }
                }


                for (int i = 0; i < sValues.length; i++) {
                    if (sValues[i].length() == 0) sValues[i + 1] = "-" + sValues[i + 1];
                    else {
                        if(sValues[i].charAt(sValues[i].length()-1) == ')' && sValues[i].charAt(0) != '(') sValues[i] = sValues[i].substring(0,sValues[i].length()-2);
                 
                        dValues.add(Double.parseDouble(sValues[i]));
                    }
                }
                for (int i = operation.size() - 1; i >= 0; i--) {

                    if (operation.get(i) == "*") {
                        dValues.set(i, dValues.get(i) * dValues.get(i + 1));
                        operation.remove(i);
                        dValues.remove(i + 1);
                    } else if (operation.get(i) == "/") {
                        dValues.set(i, dValues.get(i) / dValues.get(i + 1));
                        operation.remove(i);
                        dValues.remove(i + 1);
                    }
                }
                result = dValues.get(0);
                for (int i = 0; i < operation.size(); i++) {
                    if (operation.get(i) == "+") result += dValues.get(i + 1);
                    else if (operation.get(i) == "-") {
                        result -= dValues.get(i + 1);
                    }
                }
                return String.valueOf(result);
                
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return "error";
            }
        }
    }
    double silnia(double x) {
        if(x == 0) return 1;
        else return x*silnia(x-1);
    }
    ArrayList<String> actions(String equation){
        ArrayList<String> operations = new ArrayList<>();
        for(int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == '*') operations.add("*");
            else if (equation.charAt(i) == '/') operations.add("/");
            else if (equation.charAt(i) == '+') operations.add("+");
            else if (equation.charAt(i) == '-') {
                if(i != 0 && (equation.charAt(i-1) == '-' ||
                equation.charAt(i-1) == '*' ||
                equation.charAt(i-1) == '/' ||
                equation.charAt(i-1) == '+')) ;
                else if( i == 0 || equation.charAt(i-1) == '(');
                else operations.add("-");
            }
        }
        return  operations;

    }
    String funkcjaLiczaca(String equation){

        int from = 0, to = 0;
        while(true) {
            int i;
            for (i = 0; i < equation.length(); i++) {
                if (equation.charAt(i) == ')') {
                    to = i;
                    break;
                }
            }
            if(i == equation.length()) break;
            for (i = to ; i >= 0; i--) {
                if (equation.charAt(i) == '(') {
                    from = i;
                    break;
                }
            }
            if(i == 0) break;


            equation = equation.substring(0,from)+count(actions(equation.substring(from+1,to)),equation.substring(from+1,to))+equation.substring(to+1);


        }
        return count(actions(equation),equation);
    }
    String functions(String equation){
        int from,to, i;
        int nawiasy =0;
        int licznik = 0;
      

        while(true) {
            from = -2;
            to = 0;
            int dlugosc =0;
            double wykladnik;
            for(i = equation.length()-1; i >=0; i--){
                if(equation.charAt(i) == '^'){
                    from = i;
                    break;
                }
            }
            if(from == -2){
                break;
            }
            else{
                if(equation.charAt(from+1) == '(') {
                    for (i = from + 2; i < equation.length(); i++) {
                        if (equation.charAt(i) == '(') {
                            nawiasy ++;

                        }
                        else if (equation.charAt(i) == ')' && nawiasy == 0) {
                          
                            to = i+1;
                            break;
                        }
                        else if(equation.charAt(i) == ')') nawiasy --;
                     
                    }

                    dlugosc = equation.substring(from+1,to).length();
                    wykladnik = Double.parseDouble(functions(equation.substring(from+1,to)));
                
                }
                else if(equation.charAt(from+1) == 'l' || equation.charAt(from+1) == 's' || equation.charAt(from+1) == 'c'
                || equation.charAt(from+1) == 't'){
                    if(equation.charAt(i+3) == '(') {
                        for (i = from + 4; i < equation.length(); i++) {
                            if (equation.charAt(i) == '(') {
                                nawiasy++;
                            } else if (equation.charAt(i) == ')' && nawiasy == 0) {
                                to = i + 1;
                                break;
                            } else if (equation.charAt(i) == ')') nawiasy--;

                        }
                    }
                    else{
                        for (i = from + 5; i < equation.length(); i++) {
                            if (equation.charAt(i) == '(') {
                                nawiasy++;
                            } else if (equation.charAt(i) == ')' && nawiasy == 0) {
                                to = i + 1;
                                break;
                            } else if (equation.charAt(i) == ')') nawiasy--;

                        }
                    }
                    dlugosc = equation.substring(from+1,to).length();
                    wykladnik = Double.parseDouble(functions(equation.substring(from+1,to)));
                   
                }
                else{
                    String[] wyk = equation.substring(from+1).split("[\\+\\-\\*\\/\\)]");
                    dlugosc = wyk[wyk.length-1].length();
                    wykladnik = Double.parseDouble(functions(wyk[wyk.length-1]));
                    
                }

                equation = equation.substring(0,from - Integer.parseInt(expSqrts.get(1))) + "(" + Math.pow(Double.parseDouble(expSqrts.get(0)),wykladnik) + ")" + equation.substring(from +1 + dlugosc);
              

                    expSqrts.remove(1);
                    expSqrts.remove(0);


            }
            
         
        }

        while(true) {
            from = -2;
            to = 0;
            for (i = 0; i < equation.length()-3; i++) {
                if (equation.substring(i,i+3).equals("log")) {
                    from = i + 4;
                    break;
                }
            }
            if(from == -2) break;
            for(i = from; i < equation.length(); i++){
                if(equation.charAt(i) == '('){
                    nawiasy++;
                }
                else if(equation.charAt(i) == ')'){
                    if(nawiasy == 0){
                        to = i;
                        break;
                    }
                    nawiasy --;
                }
            }

            equation = equation.substring(0,from-4) + String.valueOf(Math.log10(Double.parseDouble(functions(equation.substring(from,to))))) + equation.substring(to+1);

            licznik ++;
        }
        while(true) {
            from = -2;
            to = 0;
            for (i = 0; i < equation.length()-3; i++) {
                if (equation.substring(i,i+3).equals("sin")) {
                    from = i + 4;
                    break;
                }
            }
            if(from == -2) break;
            for(i = from; i < equation.length(); i++){
                if(equation.charAt(i) == '('){
                    nawiasy++;
                }
                else if(equation.charAt(i) == ')'){
                    if(nawiasy == 0){
                        to = i;
                        break;
                    }
                    nawiasy --;
                }
            }


            equation = equation.substring(0,from-4) + String.valueOf(Math.sin(Math.toRadians(Double.parseDouble(functions(equation.substring(from,to)))))) + equation.substring(to+1);

            licznik ++;
        }
        while(true) {
            from = -2;
            to = 0;
            for (i = 0; i < equation.length()-3; i++) {
                if (equation.substring(i,i+3).equals("cos")) {
                    from = i + 4;
                    break;
                }
            }
            if(from == -2) break;
            for(i = from; i < equation.length(); i++){
                if(equation.charAt(i) == '('){
                    nawiasy++;
                }
                else if(equation.charAt(i) == ')'){
                    if(nawiasy == 0){
                        to = i;
                        break;
                    }
                    nawiasy --;
                }
            }


            equation = equation.substring(0,from-4) + String.valueOf(Math.cos(Math.toRadians(Double.parseDouble(functions(equation.substring(from,to)))))) + equation.substring(to+1);

            licznik ++;
        }
        while(true) {
            from = -2;
            to = 0;
            for (i = 0; i < equation.length()-3; i++) {
                if (equation.substring(i,i+2).equals("ln")) {
                    from = i + 3;
                    break;
                }
            }
            if(from == -2) break;
            for(i = from; i < equation.length(); i++){
                if(equation.charAt(i) == '('){
                    nawiasy++;
                }
                else if(equation.charAt(i) == ')'){
                    if(nawiasy == 0){
                        to = i;
                        break;
                    }
                    nawiasy --;
                }
            }


            equation = equation.substring(0,from-3) + String.valueOf(Math.log(Double.parseDouble(functions(equation.substring(from,to))))) + equation.substring(to+1);

            licznik ++;
        }
        while(true) {
            from = -2;
            to = 0;
            for (i = 0; i < equation.length()-3; i++) {
                if (equation.substring(i,i+2).equals("tg")) {
                    from = i + 3;
                    break;
                }
            }
            if(from == -2) break;
            for(i = from; i < equation.length(); i++){
                if(equation.charAt(i) == '('){
                    nawiasy++;
                }
                else if(equation.charAt(i) == ')'){
                    if(nawiasy == 0){
                        to = i;
                        break;
                    }
                    nawiasy --;
                }
            }


            equation = equation.substring(0,from-3) + String.valueOf(Math.tan(Math.toRadians(Double.parseDouble(functions(equation.substring(from,to)))))) + equation.substring(to+1);

            licznik ++;
        }
        return expSqrt(equation);

    }
    String expSqrt(String equation){
        int from,to, i;
        int nawiasy =0;


        while(true) {
            from = -2;
            to = 0;
            for (i = 0; i < equation.length(); i++) {
                if (equation.charAt(i) == '\u221a') {
                    from = i + 1;
                    break;
                }
            }
            if(from == -2) break;
            if(equation.charAt(from) == '-') return "error";
            for(i = from; i < equation.length(); i++){

                if(equation.charAt(i) == '('){
                    nawiasy++;
                }
                else if(equation.charAt(i) == ')'){
                    if(nawiasy == 0){
                        to = i;
                        break;
                    }
                    nawiasy --;
                }
            }
            if(to == 0){
                for(i = from; i < equation.length(); i++){
                    if(equation.charAt(i) == '+'
                            || equation.charAt(i) == '*'
                            || equation.charAt(i) == '/'
                            || equation.charAt(i) == '-'
                            || equation.charAt(i) == ')'){
                        to = i;
                        break;
                    }
                    to = equation.length();

                }
                equation = equation.substring(0,from-1) + String.valueOf(Math.sqrt(Double.parseDouble(functions(equation.substring(from,to))))) + equation.substring(to);
            }

            else {
                //System.out.println(equation.substring(from,to));
                equation = equation.substring(0,from-1) + String.valueOf(Math.sqrt(Double.parseDouble(functions(equation.substring(from,to))))) + equation.substring(to);
            }


        }
        equation = funkcjaLiczaca(equation);
        return  equation;
    }

    Font fCalculate = new Font("Arial", Font.PLAIN,30);
    Font fClearUndo = new Font("Arial", Font.PLAIN,20);
    Font fNumbers = new Font("Arial", Font.PLAIN,15);

    DecimalFormat df = new DecimalFormat("#####0.0###");
    DecimalFormatSymbols dfs;

    {
        dfs = df.getDecimalFormatSymbols();
    }



    JButton bCalculate;
    JButton bAdd, bSubstract, bMultiply, bDivide;
    JButton bZero, bOne, bTwo, bThree, bFour, bFive, bSix, bSeven, bEight, bNine;
    JButton bComma, bPercent;
    JButton bClear, bUndo;
    JButton bEuler, bPi;
    JButton bModulo;
    JButton bRoot, bExponent, bFactorial, bLogaritm, bLogaritmNatural;
    JButton bSinus, bCosinus, bTangens;
    JButton bStartGroup, bStopGroup;
    JComboBox cbMode;
    JLabel lResult;


    Border border = BorderFactory.createMatteBorder(0,0,4,0,WHITE);
    Border borderResult = BorderFactory.createMatteBorder(1,1,1,1,BLACK);

    ArrayList<String> expSqrts = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Calculator window = new Calculator();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Calculator(){
        setSize(395,405);
        setTitle("Calculator");
        setLayout(null);
        setResizable(false);
        ImageIcon img = new ImageIcon("tlo.jpg");
        getContentPane().setBackground(darkGray);
        GradientPaint blackgray = new GradientPaint(50,50,BLACK,50,50,DARK_GRAY);


        lResult = new JLabel("",SwingConstants.RIGHT);
        lResult.setBounds(5,5,385,80);
        lResult.setFont(fClearUndo);
        lResult.setOpaque(true);
        lResult.setBorder(borderResult);
        lResult.setBackground(darkGray);
        lResult.setForeground(WHITE);
        lResult.setText("");
        add(lResult);

        bCalculate = new JButton();
        bCalculate.setBounds(265,285,125,60);
        bCalculate.setText("=");
        bCalculate.setBorder(border);
        bCalculate.setForeground(WHITE);
        bCalculate.setBackground(BLACK);
        bCalculate.addMouseListener(new java.awt.event.MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                bCalculate.setBackground(RED);
            }
        });
        bCalculate.setFont(fCalculate);
        add(bCalculate);
        bCalculate.addActionListener(this);

        cbMode = new JComboBox();
        cbMode.setBounds(getWidth()/2-100,350,200,20);
        cbMode.addItem("Standard mode");
        cbMode.addItem("Advanced mode");
        cbMode.setFocusable(false);
        cbMode.setBackground(DARK_GRAY);
        cbMode.setForeground(WHITE);
        cbMode.setBorder(null);
        add(cbMode);
        cbMode.addActionListener(this);


        bClear = new JButton();
        bClear.setBounds(330,90,60,60);
        bClear.setText("C");
        bClear.setFont(fClearUndo);
        bClear.setBorder(border);
        bClear.setBackground(BLACK);
        bClear.setForeground(WHITE);
        add(bClear);
        bClear.addActionListener(this);

        bUndo = new JButton();
        bUndo.setBounds(265,90,60,60);
        bUndo.setText("←");
        bUndo.setBorder(border);
        bUndo.setFont(fClearUndo);
        bUndo.setBackground(BLACK);
        bUndo.setForeground(WHITE);
        add(bUndo);
        bUndo.addActionListener(this);

        bZero = new JButton();

        bZero.setBounds(5,285,60,60);
        bZero.setText("0");
        bZero.setFont(fNumbers);
        bZero.setBorder(border);
        bZero.setForeground(WHITE);
        bZero.setBackground(BLACK);
        add(bZero);
        bZero.addActionListener(this);

        bComma = new JButton();
        bComma.setBounds(70,285,60,60);
        bComma.setText(".");
        bComma.setBorder(border);
        bComma.setFont(fCalculate);
        bComma.setBackground(BLACK);
        bComma.setForeground(WHITE);
        add(bComma);
        bComma.addActionListener(this);

        bSeven = new JButton();
        bSeven.setBounds(5,90,60,60);
        bSeven.setText("7");
        bSeven.setBorder(border);
        bSeven.setFont(fNumbers);
        bSeven.setBorder(border);
        bSeven.setBackground(BLACK);
        bSeven.setForeground(WHITE);
        add(bSeven);
        bSeven.addActionListener(this);

        bEight = new JButton();
        bEight.setBounds(70,90,60,60);
        bEight.setText("8");
        bEight.setFont(fNumbers);
        bEight.setBorder(border);
        bEight.setBackground(BLACK);
        bEight.setForeground(WHITE);
        add(bEight);
        bEight.addActionListener(this);

        bNine = new JButton();
        bNine.setBounds(135,90,60,60);
        bNine.setText("9");
        bNine.setFont(fNumbers);
        bNine.setBorder(border);
        bNine.setBackground(BLACK);
        bNine.setForeground(WHITE);
        add(bNine);
        bNine.addActionListener(this);

        bFour = new JButton();
        bFour.setBounds(5,155,60,60);
        bFour.setText("4");
        bFour.setBorder(border);
        bFour.setFont(fNumbers);
        bFour.setBackground(BLACK);
        bFour.setForeground(WHITE);
        add(bFour);
        bFour.addActionListener(this);

        bFive = new JButton();
        bFive.setBounds(70,155,60,60);
        bFive.setText("5");
        bFive.setFont(fNumbers);
        bFive.setBorder(border);
        bFive.setBackground(BLACK);
        bFive.setForeground(WHITE);
        add(bFive);
        bFive.addActionListener(this);

        bSix = new JButton();
        bSix.setBounds(135,155,60,60);
        bSix.setText("6");
        bSix.setBorder(border);
        bSix.setFont(fNumbers);
        bSix.setBackground(BLACK);
        bSix.setForeground(WHITE);
        add(bSix);
        bSix.addActionListener(this);

        bOne = new JButton();
        bOne.setBounds(5,220,60,60);
        bOne.setText("1");
        bOne.setBorder(border);
        bOne.setFont(fNumbers);
        bOne.setBackground(BLACK);
        bOne.setForeground(WHITE);
        add(bOne);
        bOne.addActionListener(this);

        bTwo = new JButton();
        bTwo.setBounds(70,220,60,60);
        bTwo.setText("2");
        bTwo.setBorder(border);
        bTwo.setFont(fNumbers);
        bTwo.setBackground(BLACK);
        bTwo.setForeground(WHITE);
        add(bTwo);
        bTwo.addActionListener(this);

        bThree = new JButton();
        bThree.setBounds(135,220,60,60);
        bThree.setText("3");
        bThree.setBorder(border);
        bThree.setFont(fNumbers);
        bThree.setBackground(BLACK);
        bThree.setForeground(WHITE);
        add(bThree);
        bThree.addActionListener(this);

        bAdd = new JButton();
        bAdd.setBounds(200,285,60,60);
        bAdd.setText("+");
        bAdd.setBorder(border);
        bAdd.setFont(fNumbers);
        bAdd.setBackground(BLACK);
        bAdd.setForeground(WHITE);
        add(bAdd);
        bAdd.addActionListener(this);

        bSubstract = new JButton();
        bSubstract.setBounds(200,220,60,60);
        bSubstract.setText("-");
        bSubstract.setBorder(border);
        bSubstract.setFont(fCalculate);
        bSubstract.setBackground(BLACK);
        bSubstract.setForeground(WHITE);
        add(bSubstract);
        bSubstract.addActionListener(this);

        bMultiply = new JButton();
        bMultiply.setBounds(200,155,60,60);
        bMultiply.setText("x");
        bMultiply.setBorder(border);
        bMultiply.setFont(fNumbers);
        bMultiply.setBackground(BLACK);
        bMultiply.setForeground(WHITE);
        add(bMultiply);
        bMultiply.addActionListener(this);

        bDivide = new JButton();
        bDivide.setBounds(200,90,60,60);
        bDivide.setText("/");
        bDivide.setBorder(border);
        bDivide.setFont(fNumbers);
        bDivide.setBackground(BLACK);
        bDivide.setForeground(WHITE);
        add(bDivide);
        bDivide.addActionListener(this);

        bPercent = new JButton();
        bPercent.setBounds(135,285,60,60);
        bPercent.setText("%");
        bPercent.setBorder(border);
        bPercent.setBackground(BLACK);
        bPercent.setForeground(WHITE);
        add(bPercent);
        bPercent.addActionListener(this);


        bStartGroup = new JButton();
        bStartGroup.setBounds(265,155,60,60);
        bStartGroup.setText("(");
        bStartGroup.setBorder(border);
        bStartGroup.setFont(fClearUndo);
        bStartGroup.setBackground(BLACK);
        bStartGroup.setForeground(WHITE);
        add(bStartGroup);
        bStartGroup.addActionListener(this);

        bStopGroup = new JButton();
        bStopGroup.setBounds(330,155,60,60);
        bStopGroup.setText(")");
        bStopGroup.setBorder(border);
        bStopGroup.setFont(fClearUndo);
        bStopGroup.setBackground(BLACK);
        bStopGroup.setForeground(WHITE);
        add(bStopGroup);
        bStopGroup.addActionListener(this);

        bFactorial = new JButton();
        bFactorial.setBounds(395,155,60,60);
        bFactorial.setText("!");
        bFactorial.setFont(fClearUndo);
        bFactorial.setForeground(WHITE);
        bFactorial.setBackground(BLACK);
        bFactorial.setBorder(border);
        add(bFactorial);
        bFactorial.addActionListener(this);

        bLogaritm = new JButton();
        bLogaritm.setBounds(395,90,60,60);
        bLogaritm.setText("log()");
        bLogaritm.setForeground(WHITE);
        bLogaritm.setBackground(BLACK);
        bLogaritm.setBorder(border);
        bLogaritm.setFont(fClearUndo);
        add(bLogaritm);
        bLogaritm.addActionListener(this);

        bLogaritmNatural = new JButton();
        bLogaritmNatural.setBounds(460,90,60,60);
        bLogaritmNatural.setText("ln()");
        bLogaritmNatural.setBorder(border);
        bLogaritmNatural.setBackground(BLACK);
        bLogaritmNatural.setForeground(WHITE);
        bLogaritmNatural.setFont(fClearUndo);
        add(bLogaritmNatural);
        bLogaritmNatural.addActionListener(this);

        bRoot = new JButton();
        bRoot.setBounds(265,220,60,60);
        bRoot.setText("\u221a");
        bRoot.setFont(fNumbers);
        bRoot.setBorder(border);
        bRoot.setBackground(BLACK);
        bRoot.setForeground(WHITE);
        add(bRoot);
        bRoot.addActionListener(this);

        bExponent = new JButton();
        bExponent.setBounds(330,220,60,60);
        bExponent.setText("^");
        bExponent.setBorder(border);
        bExponent.setFont(fClearUndo);
        bExponent.setBackground(BLACK);
        bExponent.setForeground(WHITE);
        add(bExponent);
        bExponent.addActionListener(this);

        bSinus = new JButton();
        bSinus.setBounds(395,220,60,60);
        bSinus.setText("sin()");
        bSinus.setBackground(BLACK);
        bSinus.setFont(fClearUndo);
        bSinus.setForeground(WHITE);
        bSinus.setBorder(border);
        add(bSinus);
        bSinus.addActionListener(this);

        bCosinus = new JButton();
        bCosinus.setBounds(460,220,60,60);
        bCosinus.setText("cos()");
        bCosinus.setBorder(border);
        bCosinus.setFont(fClearUndo);
        bCosinus.setBackground(BLACK);
        bCosinus.setForeground(WHITE);

        add(bCosinus);
        bCosinus.addActionListener(this);

        bTangens = new JButton();
        bTangens.setBounds(460,155,60,60);
        bTangens.setText("tg()");
        bTangens.setFont(fClearUndo);
        bTangens.setBorder(border);
        bTangens.setBackground(BLACK);
        bTangens.setForeground(WHITE);
        add(bTangens);
        bTangens.addActionListener(this);

        bEuler = new JButton();
        bEuler.setBounds(395,285,60,60);
        bEuler.setText("e");
        bEuler.setFont(fClearUndo);
        bEuler.setForeground(WHITE);
        bEuler.setBackground(BLACK);
        bEuler.setBorder(border);
        add(bEuler);
        bEuler.addActionListener(this);

        bPi = new JButton();
        bPi.setBounds(460,285,60,60);
        bPi.setText("\u03C0");
        bPi.setFont(fClearUndo);
        bPi.setBorder(border);
        bPi.setBackground(BLACK);
        bPi.setForeground(WHITE);
        add(bPi);
        bPi.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object button = e.getSource();
        dfs.setDecimalSeparator('.');
        dfs.setGroupingSeparator(' ');
        df.setDecimalFormatSymbols(dfs);
        if(button == bCalculate){

            int openBrackets = 0, closeBrackets = 0;
            for(int x = 0; x < lResult.getText().length(); x ++){
                if(lResult.getText().charAt(x) == '(') openBrackets ++;
                else if(lResult.getText().charAt(x) == ')') closeBrackets++;
            }
            if(openBrackets != closeBrackets){
                lResult.setText("error");
            }

            //System.out.println(lResult.getText());

           if(lResult.getText().isEmpty()) lResult.setText("0");
           else if(lResult.getText().equals("error")) ;
           else {
               
                String wynik = functions(lResult.getText());
                if(wynik.equals("error")) lResult.setText("Chuy wie");
                else wynik = (df.format(Double.parseDouble(wynik)));
                if(wynik.equals("∞")) lResult.setText("Dividing by zero!");
                else lResult.setText(String.format("### ###.###",Double.parseDouble(wynik)));
           }

        }
        else if(button == cbMode){
            String mode = cbMode.getSelectedItem().toString();
            if(mode.equals("Advanced mode")) {
                setSize(525,405);
                lResult.setSize(getWidth()-10,80);
                cbMode.setSize(getWidth()-200,20);
            }
            else if(mode.equals("Standard mode")){
                setSize(395,405);
                lResult.setSize(getWidth()-10,80);
                cbMode.setSize(200,20);
            }
        }
        else if(button == bClear){
            expSqrts.clear();
            lResult.setText("");
        }
        else if(button == bZero){
            lResult.setText(lResult.getText()+"0");
        }
        else if(button == bOne){
            lResult.setText(lResult.getText()+"1");
        }
        else if(button == bTwo){
            lResult.setText(lResult.getText()+"2");
        }
        else if(button == bThree){
            lResult.setText(lResult.getText()+"3");
        }
        else if(button == bFour){
            lResult.setText(lResult.getText()+"4");
        }
        else if(button == bFive){
            lResult.setText(lResult.getText()+"5");
        }
        else if(button == bSix){
            lResult.setText(lResult.getText()+"6");
        }
        else if(button == bSeven){
            lResult.setText(lResult.getText()+"7");
        }
        else if(button == bEight){
            lResult.setText(lResult.getText()+"8");
        }
        else if(button == bNine){
            lResult.setText(lResult.getText()+"9");
        }
        else if(button == bSubstract){
            if(lResult.getText().isEmpty()) lResult.setText(lResult.getText() + "-");
            else if(lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '.'){


                lResult.setText(lResult.getText().substring(0, lResult.getText().length()-1));
                lResult.setText(lResult.getText() + "-");

            }
            else {


                lResult.setText(lResult.getText() + "-");

            }
        }
        else if(button == bAdd){
            if(lResult.getText().isEmpty() || lResult.getText().charAt(lResult.getText().length()-1) == '(');
            else if(lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '.'){


                lResult.setText(lResult.getText().substring(0, lResult.getText().length()-1));
                lResult.setText(lResult.getText() + "+");

            }
            else {


                lResult.setText(lResult.getText() + "+");

            }
        }
        else if(button == bMultiply){
            if(lResult.getText().isEmpty() || lResult.getText().charAt(lResult.getText().length()-1) == '(');
            else if(lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '.'){

                lResult.setText(lResult.getText().substring(0, lResult.getText().length()-1));
                lResult.setText(lResult.getText() + "*");

            }
            else {

                lResult.setText(lResult.getText() + "*");

            }
        }
        else if(button == bDivide){
            if(lResult.getText().isEmpty() || lResult.getText().charAt(lResult.getText().length()-1) == '(');
            else if(lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '.'){


                lResult.setText(lResult.getText().substring(0, lResult.getText().length()-1));
                lResult.setText(lResult.getText() + "/");

            }
            else {


                lResult.setText(lResult.getText() + "/");

            }
        }
        else if(button == bUndo){
            if(lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '('
                    || lResult.getText().charAt(lResult.getText().length()-1) == ')') {


                lResult.setText(lResult.getText().substring(0, lResult.getText().length() - 1));

            }else if(lResult.getText().charAt(lResult.getText().length()-1) == '^'){
                lResult.setText(lResult.getText().substring(0, lResult.getText().length() - 1));
                expSqrts.remove(1);
                expSqrts.remove(0);
            }
            else lResult.setText(lResult.getText().substring(0, lResult.getText().length() - 1));
        }
        else if(button == bStartGroup){
            if(lResult.getText().isEmpty()
                    || lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '('
                    || lResult.getText().charAt(lResult.getText().length()-1) == '.'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '^'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '\u221a') {

                lResult.setText(lResult.getText() + "(");


            }
            else {
                lResult.setText(lResult.getText() + "*(");

            }
        }
        else if(button == bStopGroup){
            if(!(lResult.getText().isEmpty()
                    || lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '('
                    || lResult.getText().charAt(lResult.getText().length()-1) == '.')) {

                lResult.setText(lResult.getText() + ")");


            }
        }
        else if(button == bComma){
            if(!(lResult.getText().isEmpty()
                    || lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '('
                    || lResult.getText().charAt(lResult.getText().length()-1) == ')'
            )) {
                String[] lastnum = lResult.getText().split("[\\+\\*\\-\\/]");
                if(lastnum[lastnum.length-1].contains("."));
                else {

                    lResult.setText(lResult.getText() + ".");
                }
            }
        }
        else if(button == bPercent){
            if(!(lResult.getText().isEmpty()
                    || lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '('
                    || lResult.getText().charAt(lResult.getText().length()-1) == '.'
            )){
                String[] lastnum = lResult.getText().split("[\\+\\*\\-\\/]");
                double x;
                int nawiasy = 0;
                if(lResult.getText().charAt(lResult.getText().length()-1) == ')'){
                    int from = 0;
                    for(int i = lResult.getText().length() - 2; i >= 0; i--){
                        if(lResult.getText().charAt(i) == ')') nawiasy++;
                        else if(lResult.getText().charAt(i) == '('){
                            if(nawiasy == 0){
                                from = i;
                                if(i != 0 && lResult.getText().charAt(i-1) == '√'){
                                    x = Double.parseDouble(functions(lResult.getText().substring(from-1)));
                                    lResult.setText(lResult.getText().substring(0,from-1) + String.valueOf(x * 0.01));
                                }
                                else {
                                    x = Double.parseDouble(functions(lResult.getText().substring(from)));
                                    lResult.setText(lResult.getText().substring(0, from) + String.valueOf(x * 0.01));
                                }
                            }
                            else nawiasy --;
                        }

                    }

                }
                else {

                    x = Double.parseDouble(lastnum[lastnum.length - 1]);
                    lResult.setText(lResult.getText().substring(0,lResult.getText().length() - lastnum[lastnum.length-1].length())+ x*1/100);
                }


            }
        }
        else if(button == bExponent){
            if(!(lResult.getText().isEmpty()
                    || lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '('
            )){
                double result = 0;
                int nawiasy = 0, from = -1, size=0;
                String[] podstawa = lResult.getText().split("[\\^\\+\\-\\*\\/]");
                if(lResult.getText().charAt(lResult.getText().length()-1) == ')'){
                    for(int i = lResult.getText().length()-2; i >= 0; i --){
                        if(lResult.getText().charAt(i) == ')') nawiasy ++;
                        else if(lResult.getText().charAt(i) == '(' && nawiasy == 0) {
                            from = i;
                            //System.out.println(from);
                            break;
                        }
                        else if(lResult.getText().charAt(i) == '(') nawiasy --;
                    }
                    if(from == 0){
                        size = lResult.getText().substring(from).length();
                        result = Double.parseDouble(functions(lResult.getText().substring(from)));
                    }
                    else if(lResult.getText().charAt(from-1) == 'g' || lResult.getText().charAt(from -1) =='s' || lResult.getText().charAt(from -1) == 'n'){
                        if(lResult.getText().charAt(from -1) == 'g'){
                            if(lResult.getText().charAt(from -2) == 't') {
                                size = lResult.getText().substring(from - 2).length();
                                result = Double.parseDouble(functions(lResult.getText().substring(from-2)));
                            }
                            else {
                                size = lResult.getText().substring(from-3).length();
                                result = Double.parseDouble(functions(lResult.getText().substring(from - 3)));
                            }
                        }
                        else if(lResult.getText().charAt(from -1) == 'n') {
                            if (lResult.getText().charAt(from - 2) == 'l'){
                                size = lResult.getText().substring(from -2).length();
                                result = Double.parseDouble(functions(lResult.getText().substring(from - 2)));
                            }
                            else {
                                size = lResult.getText().substring(from - 3).length();
                                result = Double.parseDouble(functions(lResult.getText().substring(from - 3)));
                            }
                        }
                        else{
                            size = lResult.getText().substring(from - 3).length();
                            result = Double.parseDouble(functions(lResult.getText().substring(from - 3)));
                        }
                    }

                }
                else {

                    if(podstawa[podstawa.length-1].charAt(0) == 'l' || podstawa[podstawa.length-1].charAt(0) == 's' || podstawa[podstawa.length-1].charAt(0) == 't'
                    || podstawa[podstawa.length-1].charAt(0) == 'c'){
                        if(podstawa[podstawa.length-1].charAt(0) == 'l'){
                            if(podstawa[podstawa.length-1].charAt(1) == 'n') podstawa[podstawa.length-1] = podstawa[podstawa.length-1].substring(2);
                            else podstawa[podstawa.length-1] = podstawa[podstawa.length-1].substring(3);
                        }
                        else if(podstawa[podstawa.length-1].charAt(0) == 's'){
                            podstawa[podstawa.length-1] = podstawa[podstawa.length-1].substring(3);
                        }
                        else if(podstawa[podstawa.length-1].charAt(0) == 't'){
                            podstawa[podstawa.length-1] = podstawa[podstawa.length-1].substring(2);
                        }
                        else podstawa[podstawa.length-1] = podstawa[podstawa.length-1].substring(3);
                    }
                    size = podstawa[podstawa.length-1].length();
                    result = Double.parseDouble(functions(podstawa[podstawa.length-1]));
                }
                expSqrts.add(0,String.valueOf(result));
                expSqrts.add(1,String.valueOf(size));
                lResult.setText(lResult.getText()+"^");
            }
   
        }

        else if(button == bRoot){
            if(!(lResult.getText().isEmpty()
                    || lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '('
                    || lResult.getText().charAt(lResult.getText().length()-1) == '^'
            )){

                lResult.setText(lResult.getText() + "*\u221a");
            }
            else{
                lResult.setText(lResult.getText() + "\u221a");
            }
        }
        else if(button == bLogaritm){

            if(!(lResult.getText().isEmpty()
                    || lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '('
                    || lResult.getText().charAt(lResult.getText().length()-1) == '^'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '\u221a'
            )){

                lResult.setText(lResult.getText() + "*log(");
            }
            else{
                lResult.setText(lResult.getText() + "log(");
            }
        }
        else if (button == bSinus) {
            if(!(lResult.getText().isEmpty()
                    || lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '('
                    || lResult.getText().charAt(lResult.getText().length()-1) == '^'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '\u221a'
            )){

                lResult.setText(lResult.getText() + "*sin(");
            }
            else{
                lResult.setText(lResult.getText() + "sin(");
            }
        }
        else if (button == bCosinus) {
            if(!(lResult.getText().isEmpty()
                    || lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '('
                    || lResult.getText().charAt(lResult.getText().length()-1) == '^'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '^'
            )){

                lResult.setText(lResult.getText() + "*cos(");
            }
            else{
                lResult.setText(lResult.getText() + "cos(");
            }
        }
        else if (button == bLogaritmNatural) {
            if(!(lResult.getText().isEmpty()
                    || lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '('
                    || lResult.getText().charAt(lResult.getText().length()-1) == '^'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '\u221a'
            )){

                lResult.setText(lResult.getText() + "*ln(");
            }
            else{
                lResult.setText(lResult.getText() + "ln(");
            }
        }
        else if(button == bPi){
            if(lResult.getText().isEmpty()) lResult.setText(String.valueOf(df.format(Math.PI)));
            else if(lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '.'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '(') {



                lResult.setText(lResult.getText() + String.valueOf(df.format(Math.PI)));
            }
            else lResult.setText(lResult.getText()+"*"+String.valueOf(df.format(Math.PI)));
        }
        else if(button == bEuler){
            if(lResult.getText().isEmpty()) lResult.setText(String.valueOf(df.format(Math.E)));
            else if(lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '.'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '(') {



                lResult.setText(lResult.getText() + String.valueOf(df.format(Math.E)));
            }
            else lResult.setText(lResult.getText()+"*"+String.valueOf(df.format(Math.E)));
        }
        else if(button == bFactorial){
            if(!(lResult.getText().isEmpty()
                    || lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '('
            )){
                double x;
                String[] lastnum = lResult.getText().split("[\\+\\*\\-\\/]");
                if(lResult.getText().charAt(lResult.getText().length()-1)== ')'){
                    int from = 0;
                    for(int i = lResult.getText().length() - 1; i >= 0; i--){
                        if(lResult.getText().charAt(i) == '('){
                            from = i;
                            x = Double.parseDouble(functions(lResult.getText().substring(i)));
                            if(x == Math.floor(x) && !Double.isInfinite(x)){
                                lResult.setText(lResult.getText().substring(0, from) + String.valueOf(silnia(x)));
                            }
                        }

                    }
                }
                else {

                    x = Double.parseDouble(lastnum[lastnum.length - 1]);
                    if(x == Math.floor(x) && !Double.isInfinite(x)) {
                        x = silnia(x);
                        lResult.setText(lResult.getText().substring(0, lResult.getText().length() - lastnum[lastnum.length - 1].length()) + String.valueOf(x));
                    }
                }


            }
        }
        else if(button == bTangens){
            if(!(lResult.getText().isEmpty()
                    || lResult.getText().charAt(lResult.getText().length()-1) == '+'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '*'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '/'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '-'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '('
                    || lResult.getText().charAt(lResult.getText().length()-1) == ')'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '^'
                    || lResult.getText().charAt(lResult.getText().length()-1) == '\u221a'
            )){

                lResult.setText(lResult.getText() + "*tg(");
            }
            else{
                lResult.setText(lResult.getText() + "tg(");
            }
        }

    }
}
