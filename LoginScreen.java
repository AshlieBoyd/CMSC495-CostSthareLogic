import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginScreen extends JFrame implements ActionListener {

    private Container container;
    private JLabel userLabel;
    private JTextField userTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton submitButton;
    private JLabel titleLabel;

    public LoginScreen() {
        setTitle("Cost sharing login Form");
        setBounds(10, 10, 370, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        // Title Label
        titleLabel = new JLabel("Login Form");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        titleLabel.setSize(300, 30);
        titleLabel.setLocation(100, 50);
        container.add(titleLabel);

        // Username Label
        userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        userLabel.setSize(100, 20);
        userLabel.setLocation(50, 150);
        container.add(userLabel);

        // Username Text Field
        userTextField = new JTextField();
        userTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        userTextField.setSize(190, 20);
        userTextField.setLocation(150, 150);
        container.add(userTextField);

        // Password Label
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setSize(100, 20);
        passwordLabel.setLocation(50, 220);
        container.add(passwordLabel);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordField.setSize(190, 20);
        passwordField.setLocation(150, 220);
        container.add(passwordField);

        // Submit Button
        submitButton = new JButton("Login");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 15));
        submitButton.setSize(100, 20);
        submitButton.setLocation(150, 300);
        submitButton.addActionListener(this);
        container.add(submitButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String userText = userTextField.getText();
            String pwdText = new String(passwordField.getPassword());

            // Use PreparedStatement to avoid SQL Injection
            String selectQuery = "SELECT * FROM users WHERE username = ? AND password = ?";

            try (Connection conn = ConnectingDB.connection();
                 PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {

                pstmt.setString(1, userText);
                pstmt.setString(2, pwdText);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(this, "Login Successful");
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error while accessing the database.");
            }
        }
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}

