import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Calculator {

    private static JFrame frame;
    //private static JTextField field;
    private static Label field;
    private static Label inputLabel;
    private static JPanel contentPanel;
    private static JPanel buttonPanel;
    private static boolean begin = true;
    private static double result = 0;
    private static String lastInput = "=";
    private static ActionListener insert = new InsertAction();
    private static ActionListener command = new CommandAction();


    private static class InsertAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input = e.getActionCommand();
            String text = field.getText();

            inputLabel.setText("Last input : " + input);

            if (begin) {
                field.setText("");
                begin = false;
            }
            if (text.startsWith(".")) {
                field.setText("0" + field.getText() + input);
            } else if (text.startsWith("-0.") || text.startsWith("0.")) {
                field.setText(field.getText() + input);
            } else if (text.startsWith("-0")) {
                field.setText("-" + input);
            } else if (text.startsWith("0")) {
                field.setText(input);
            } else {
                field.setText(field.getText() + input);
            }
        }
    }
    private static class CommandAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("C"))
            {
                field.setText("0");
                begin = false;
                lastInput = "=";
            }
            else {
                if (begin) {
                    if (command.equals("-")) {
                        field.setText(command);
                        begin = false;
                    } else {
                        lastInput = command;
                    }
                } else {
                    calculate(Double.parseDouble(field.getText()));
                    lastInput = command;
                    begin = true;
                }
            }
            inputLabel.setText("Last input : " + command);

        }
    }

    public static void calculate(double x) {
        char operator = lastInput.charAt(0);
        switch (operator) {
            case '+':
                result += x;
                break;
            case '-':
                result -= x;
                break;
            case '*':
                result *= x;
                break;
            case '/':
                result /= x;
                break;
            case '=':
                result = x;
                break;
            case '%':
                result %= x;
                break;
        }
        field.setText("" + result);
    }
    public static void main(String[] args)
    {
        frame = new JFrame("Une calculatrice");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputLabel = new Label();
        field = new Label();
        contentPanel = new JPanel();
        buttonPanel = new JPanel();

        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(new BorderLayout(0, 0));
        contentPanel.add(inputLabel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.CENTER);


        inputLabel.setText("Last Input : =");
        inputLabel.setAlignment(Label.RIGHT);

        field.setText("0");
        field.setAlignment(Label.RIGHT);

        buttonPanel.setLayout(new GridLayout(6,3,5,5));

        JButton number7 = new JButton("7");
        number7.addActionListener(insert);

        JButton number8 = new JButton("8");
        number8.addActionListener(insert);

        JButton number9 = new JButton("9");
        number9.addActionListener(insert);

        JButton divide = new JButton("/");
        divide.addActionListener(command);

        JButton number4 = new JButton("4");
        number4.addActionListener(insert);

        JButton number5 = new JButton("5");
        number5.addActionListener(insert);

        JButton number6 = new JButton("6");
        number6.addActionListener(insert);

        JButton multiply = new JButton("*");
        multiply.addActionListener(command);

        JButton number1 = new JButton("1");
        number1.addActionListener(insert);

        JButton number2 = new JButton("2");
        number2.addActionListener(insert);

        JButton number3 = new JButton("3");
        number3.addActionListener(insert);

        JButton subtract = new JButton("-");
        subtract.addActionListener(command);

        JButton number0 = new JButton("0");
        number0.addActionListener(insert);

        JButton dot = new JButton(".");
        dot.addActionListener(insert);

        JButton equal = new JButton("=");
        equal.addActionListener(command);

        JButton add = new JButton("+");
        add.addActionListener(command);

        JButton clear = new JButton("C");
        clear.addActionListener(command);

        JButton mod = new JButton("%");
        mod.addActionListener(command);

        buttonPanel.add(clear);
        buttonPanel.add(mod);
        buttonPanel.add(number0);
        buttonPanel.add(number1);
        buttonPanel.add(number2);
        buttonPanel.add(number3);
        buttonPanel.add(number4);
        buttonPanel.add(number5);
        buttonPanel.add(number6);
        buttonPanel.add(number7);
        buttonPanel.add(number8);
        buttonPanel.add(number9);
        buttonPanel.add(divide);
        buttonPanel.add(multiply);
        buttonPanel.add(subtract);
        buttonPanel.add(dot);
        buttonPanel.add(equal);
        buttonPanel.add(add);


        frame.setLayout(new GridLayout(1, 2));
        frame.add(field);
        //frame.add(inputLabel);
        frame.add(contentPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
