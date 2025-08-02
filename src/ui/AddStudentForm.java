package ui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class AddStudentForm extends JPanel {

    public AddStudentForm(Runnable refreshCallback) {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 245, 250));

        // Card Panel
        JPanel card = new JPanel(new GridBagLayout());
        card.setPreferredSize(new Dimension(420, 350));
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
                new EmptyBorder(30, 30, 30, 30),
                new LineBorder(new Color(220, 220, 220), 1, true)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel heading = new JLabel("➕ Add New Student");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setForeground(new Color(50, 50, 50));

        RoundedTextField nameField = new RoundedTextField("👤 Full Name");
        RoundedTextField emailField = new RoundedTextField("✉️ Email Address");
        RoundedTextField courseField = new RoundedTextField("🎓 Course");

        JButton addButton = new JButton("✅ Add Student");
        styleButton(addButton);

        // Layout
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        card.add(heading, gbc);

        gbc.gridy++; card.add(nameField, gbc);
        gbc.gridy++; card.add(emailField, gbc);
        gbc.gridy++; card.add(courseField, gbc);
        gbc.gridy++; card.add(addButton, gbc);

        add(card);

        // Action
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String course = courseField.getText().trim();

            if (name.isEmpty() || email.isEmpty() || course.isEmpty() ||
                    name.equals("👤 Full Name") || email.equals("✉️ Email Address") || course.equals("🎓 Course")) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Validation", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Student s = new Student(0, name, email, course);
            new StudentDAO().addStudent(s);

            JOptionPane.showMessageDialog(this, "🎉 Student added successfully!");

            nameField.reset();
            emailField.reset();
            courseField.reset();

            refreshCallback.run();
        });
    }

    // Custom rounded text field with icon-like placeholder
    static class RoundedTextField extends JTextField {
        private final String placeholder;
        private boolean showingPlaceholder = true;

        public RoundedTextField(String placeholder) {
            super(placeholder);
            this.placeholder = placeholder;
            setFont(new Font("Segoe UI", Font.PLAIN, 14));
            setForeground(Color.GRAY);
            setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(200, 200, 200), 1, true),
                    new EmptyBorder(10, 14, 10, 14)
            ));
            setBackground(Color.WHITE);
            enablePlaceholder();

            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (showingPlaceholder) {
                        setText("");
                        setForeground(Color.BLACK);
                        showingPlaceholder = false;
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().isEmpty()) {
                        enablePlaceholder();
                    }
                }
            });
        }

        public void reset() {
            setText("");
            enablePlaceholder();
        }

        private void enablePlaceholder() {
            setText(placeholder);
            setForeground(Color.GRAY);
            showingPlaceholder = true;
        }
    }

    // Stylized button with hover animation
    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 153, 76));
        button.setForeground(Color.WHITE);
        button.setBorder(new LineBorder(new Color(0, 128, 64), 1, true));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(160, 40));

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(0, 180, 90));
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0, 153, 76));
            }
        });
    }
}
