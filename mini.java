package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class mini extends JFrame implements ActionListener {
    String pin_number;
    JButton button;

    mini(String pin_number) {
        this.pin_number = pin_number;
        getContentPane().setBackground(new Color(255, 204, 204));
        setSize(450, 600);
        setLocation(300, 100);
        setLayout(null);

        JLabel header = new JLabel("Transaction History");
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setForeground(Color.BLACK);
        header.setBounds(130, 20, 250, 30);
        add(header);

        JLabel cardLabel = new JLabel();
        cardLabel.setFont(new Font("Arial", Font.BOLD, 14));
        cardLabel.setForeground(Color.BLACK);
        cardLabel.setBounds(20, 60, 400, 20);
        add(cardLabel);

        JLabel balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        balanceLabel.setForeground(Color.BLACK);
        balanceLabel.setBounds(20, 460, 400, 30);
        add(balanceLabel);

        // Panel for transactions inside Scroll Pane
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE); // White background

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(20, 100, 400, 350);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane);

        try {
            Connn c = new Connn();

            String query = "SELECT l.card_number, b.date, b.type, b.amount FROM login l " +
                    "JOIN bank b ON l.pin_number = b.pin_number " +
                    "WHERE l.pin_number = ? ORDER BY b.date ASC";

            PreparedStatement stmt = c.connection.prepareStatement(query);
            stmt.setString(1, pin_number);
            ResultSet resultSet = stmt.executeQuery();

            String cardNumber = "";
            ArrayList<JPanel> transactionsList = new ArrayList<>();
            int balance = 0;

            while (resultSet.next()) {
                if (cardNumber.isEmpty()) {
                    cardNumber = resultSet.getString("card_number");
                    cardLabel.setText("Card Number: " + cardNumber.substring(0, 4) + "XXXXXXXX" + cardNumber.substring(12));
                }

                String date = resultSet.getString("date");
                String type = resultSet.getString("type");
                int amount = resultSet.getInt("amount");

                // Define color based on transaction type
                Color amountColor = type.equals("Deposit") ? new Color(46, 204, 113) : new Color(231, 76, 60); // Green for Deposit, Red for Withdraw

                // Create Panel for each transaction
                JPanel transactionPanel = new JPanel();
                transactionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                transactionPanel.setBackground(Color.WHITE);

                JLabel dateLabel = new JLabel(date + " | ");
                dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                dateLabel.setForeground(Color.BLACK);

                JLabel typeLabel = new JLabel(type + " | ");
                typeLabel.setFont(new Font("Arial", Font.BOLD, 12));
                typeLabel.setForeground(amountColor);

                JLabel amountLabel = new JLabel("Rs " + amount);
                amountLabel.setFont(new Font("Arial", Font.BOLD, 12));
                amountLabel.setForeground(amountColor);

                transactionPanel.add(dateLabel);
                transactionPanel.add(typeLabel);
                transactionPanel.add(amountLabel);

                transactionsList.add(transactionPanel);

                // Update balance
                if (type.equals("Deposit")) {
                    balance += amount;
                } else {
                    balance -= amount;
                }
            }

            // Show only last 10 transactions
            if (transactionsList.size() > 10) {
                transactionsList = new ArrayList<>(transactionsList.subList(transactionsList.size() - 10, transactionsList.size()));
            }

            for (JPanel transactionPanel : transactionsList) {
                panel.add(transactionPanel);
            }

            balanceLabel.setText("Your Total Balance: Rs " + balance);

            stmt.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        button = new JButton("Exit");
        button.setBounds(160, 520, 100, 30);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new mini("");
    }
}
