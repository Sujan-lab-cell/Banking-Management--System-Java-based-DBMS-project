package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JLabel label1, label2, label3;
    JTextField textField2;
    JPasswordField passwordField3;
    JButton button1, button2, button3;

    Login() {
        super("Bank Management System");

        // Set Fullscreen Mode
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        // Logo Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(650, 50, 150, 150);
        add(image);

        // Background Image
        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("icon/backbg.png"));
        Image bgImage = bgIcon.getImage().getScaledInstance(1920, 1080, Image.SCALE_DEFAULT);
        JLabel background = new JLabel(new ImageIcon(bgImage));
        background.setBounds(0, 0, 1920, 1080);
        add(background);

        // Welcome Label
        label1 = new JLabel("WELCOME TO PVS BANK ");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("AvantGarde", Font.BOLD, 40));
        label1.setBounds(600, 250, 600, 50);
        background.add(label1);

        // Card Number Field
        label2 = new JLabel("Card  No:");
        label2.setFont(new Font("Raleway", Font.BOLD, 28));
        label2.setForeground(Color.WHITE);
        label2.setBounds(600, 350, 200, 40);
        background.add(label2);

        textField2 = new JTextField(15);
        textField2.setBounds(800, 350, 300, 40);
        textField2.setFont(new Font("Arial", Font.BOLD, 18));
        background.add(textField2);

        // PIN Field
        label3 = new JLabel("PIN:");
        label3.setFont(new Font("Raleway", Font.BOLD, 28));
        label3.setForeground(Color.WHITE);
        label3.setBounds(600, 420, 200, 40);
        background.add(label3);

        passwordField3 = new JPasswordField(15);
        passwordField3.setBounds(800, 420, 300, 40);
        passwordField3.setFont(new Font("Arial", Font.BOLD, 18));
        background.add(passwordField3);

        // Buttons
        button1 = new JButton("SIGN IN");
        button1.setFont(new Font("Arial", Font.BOLD, 18));
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.BLACK);
        button1.setBounds(700, 500, 150, 40);
        button1.addActionListener(this);
        background.add(button1);

        button2 = new JButton("CLEAR");
        button2.setFont(new Font("Arial", Font.BOLD, 18));
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.BLACK);
        button2.setBounds(900, 500, 150, 40);
        button2.addActionListener(this);
        background.add(button2);

        button3 = new JButton("SIGN UP");
        button3.setFont(new Font("Arial", Font.BOLD, 18));
        button3.setForeground(Color.WHITE);
        button3.setBackground(Color.BLACK);
        button3.setBounds(700, 560, 350, 40);
        button3.addActionListener(this);
        background.add(button3);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == button1) {
                Connn c = new Connn();
                String cardno = textField2.getText();
                String pin_number = new String(passwordField3.getPassword());
                String q = "SELECT * FROM login WHERE card_number = '" + cardno + "' AND pin_number = '" + pin_number + "'";
                ResultSet resultSet = c.statement.executeQuery(q);

                if (resultSet.next()) {
                    setVisible(false);
                    new main_Class(pin_number);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } else if (e.getSource() == button2) {
                textField2.setText("");
                passwordField3.setText("");
            } else if (e.getSource() == button3) {
                new Signup();
                setVisible(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
