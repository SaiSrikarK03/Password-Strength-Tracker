import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordStrengthTracker extends JFrame {

    private JPasswordField passwordField; // Changed to JPasswordField to hide password
    private JLabel strengthLabel;
    private JCheckBox showPasswordCheckBox; // Checkbox to toggle visibility

    public PasswordStrengthTracker() {
        // Set up the frame
        setTitle("Password Strength Tracker");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        passwordField = new JPasswordField();
        JButton checkButton = new JButton("Check Strength");
        strengthLabel = new JLabel();
        showPasswordCheckBox = new JCheckBox("Show Password"); // Toggle visibility

        // Add components to the frame
        add(passwordField, BorderLayout.NORTH);
        add(checkButton, BorderLayout.CENTER);
        add(showPasswordCheckBox, BorderLayout.EAST); // Add the checkbox to toggle visibility
        add(strengthLabel, BorderLayout.SOUTH);

        // Add action listener to the button
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkStrengthAndDisplay();
            }
        });

        // Add action listener to the checkbox to toggle password visibility
        showPasswordCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0); // Show password
                } else {
                    passwordField.setEchoChar('*'); // Hide password
                }
            }
        });
    }

    private int checkPasswordStrength(String password) {
        int strength = 0;

        // Check if the password is empty
        if (password.isEmpty()) {
            return -1; // Represents Invalid Strength
        }

        // Check length
        if (password.length() >= 8) {
            strength++;
        }

        // Check for uppercase letters
        if (containsUppercase(password)) {
            strength++;
        }

        // Check for lowercase letters
        if (containsLowercase(password)) {
            strength++;
        }

        // Check for numbers
        if (containsNumber(password)) {
            strength++;
        }

        // Check for special characters
        if (containsSpecialCharacter(password)) {
            strength++;
        }

        return strength;
    }

    private boolean containsUppercase(String password) {
        return !password.equals(password.toLowerCase());
    }

    private boolean containsLowercase(String password) {
        return !password.equals(password.toUpperCase());
    }

    private boolean containsNumber(String password) {
        return password.matches(".*\\d.*");
    }

    private boolean containsSpecialCharacter(String password) {
        return password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?\\\\|].*");
    }

    private String getStrengthLabel(int strength) {
        if (strength == -1) {
            return "Invalid Strength";
        } else if (strength == 0) {
            return "No Strength";
        } else if (strength == 1) {
            return "Very Weak";
        } else if (strength == 2) {
            return "Weak";
        } else if (strength == 3) {
            return "Average";
        } else if (strength == 4) {
            return "Strong";
        } else {
            return "Very Strong";
        }
    }

    private void checkStrengthAndDisplay() {
        String password = new String(passwordField.getPassword()); // Get password as string
        int strength = checkPasswordStrength(password);
        String strengthLabelString = getStrengthLabel(strength);

        // Update the label text and color
        strengthLabel.setText("Password Strength: " + strengthLabelString);
        if (strength == -1 || strength == 0) {
            strengthLabel.setForeground(Color.BLACK);
        } else if (strength == 1) {
            strengthLabel.setForeground(Color.RED);
        } else if (strength == 2) {
            strengthLabel.setForeground(Color.ORANGE);
        } else if (strength == 3) {
            strengthLabel.setForeground(Color.GREEN);
        } else if (strength == 4) {
            strengthLabel.setForeground(Color.BLUE);
        } else {
            strengthLabel.setForeground(Color.BLACK);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PasswordStrengthTracker passwordStrengthTrackerUI = new PasswordStrengthTracker();
                passwordStrengthTrackerUI.setVisible(true); // Call setVisible on the instance
            }
        });
    }
}
