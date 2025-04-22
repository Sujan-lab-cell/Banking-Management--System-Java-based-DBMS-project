package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class main_Class extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4, b5, b6, b7;
    String pin_number;

    main_Class(String pin_number) {
        this.pin_number = pin_number;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);

        JLabel label = new JLabel("Please Select Your Transaction");
        label.setBounds(430, 180, 700, 35);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System", Font.BOLD, 28));
        l3.add(label);

        b1 = createButton("DEPOSIT", 410, 274, l3);
        b2 = createButton("CASH WITHDRAWL", 700, 274, l3);
        b3 = createButton("FAST CASH", 410, 318, l3);
        b4 = createButton("MINI STATEMENT", 700, 318, l3);
        b5 = createButton("PIN CHANGE", 410, 362, l3);
        b6 = createButton("BALANCE ENQUIRY", 700, 362, l3);
        b7 = createButton("EXIT", 700, 406, l3);

        setLayout(null);
        setSize(2000, 10900);
        setLocation(0, 0);
        setVisible(true);
    }

    private JButton createButton(String text, int x, int y, JLabel container) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(65, 125, 128));
        button.setBounds(x, y, 150, 35);
        button.addActionListener(this);
        container.add(button);

        // Adding Hover Effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(45, 105, 108));
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(new Color(65, 125, 128));
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            new Deposit(pin_number);
            setVisible(false);
        } else if (e.getSource() == b2) {
            new Withdrawl(pin_number);
            setVisible(false);
        } else if (e.getSource() == b3) {
            new FastCash(pin_number);
            setVisible(false);
        } else if (e.getSource() == b4) {
            new mini(pin_number);
        } else if (e.getSource() == b5) {
            new Pin(pin_number);
            setVisible(false);
        } else if (e.getSource() == b6) {
            new BalanceEnquriy(pin_number);
            setVisible(false);
        } else if (e.getSource() == b7) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new main_Class("");
    }
}
