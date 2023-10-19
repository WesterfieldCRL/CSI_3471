import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FirstSwingExample extends JPanel {
        // JTextField
        static JTextField textField;

        // JFrame
        static JFrame frame;

        // JButton
        static JButton button;

        // label to display text
        static JLabel label;

        // default constructor
        FirstSwingExample() {}
    private static int count = 0;
    public static void main(String[] args) {
        JFrame frame = new JFrame();// creating instance of JFrame
        frame.setSize(700, 700);// 300 width and 200 height
        frame.setLayout(new FlowLayout());// using no layout managers
        //frame.setVisible(true);// making the frame visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(4, 255, 18));


        JButton button = new JButton(String.valueOf(count));// creating instance of JButton
        button.setBounds(100, 70, 100, 40);// x axis, y axis, width, height

        frame.add(button);// adding button in JFrame
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                button.setText(String.valueOf(count));
            }
        });

        JButton jb1 = new JButton("Button 1");
        JButton jb2 = new JButton("Button 2");
        JButton jb3 = new JButton("Button 3");

        JPanel panel = new JPanel();
        panel.add(jb1);
        panel.add(jb2);
        panel.add(jb3);

        panel.setBackground(new Color(100,10,100));

        frame.add(panel);

        textField = new JTextField(16);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(textField.getText());
            }
        });

        frame.add(textField);

        JButton fancyButton = getjButton(frame);

        frame.add(fancyButton);

        frame.setVisible(true);

    }

    private static JButton getjButton(JFrame frame) {
        JButton fancyButton = new JButton("click me");
        fancyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = (String)JOptionPane.showInputDialog(
                        frame,
                        "Give me a color",
                        "Swing Tester",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "Red"
                );
                if(result != null && !result.isEmpty()){
                    fancyButton.setText("You selected: " + result);
                }else {
                    fancyButton.setText("None selected");
                }
            }
        });
        return fancyButton;
    }
}
