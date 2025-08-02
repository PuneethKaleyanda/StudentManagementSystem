package ui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;
import java.awt.*;

public class UpdateStudentForm extends JFrame {
    public UpdateStudentForm(Student student, ViewStudentsPanel viewPanel) {
        setTitle("✏️ Update Student");
        setSize(450, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 12, 12));
        panel.setBackground(new Color(245, 248, 252));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Form fields
        JTextField nameField = new JTextField(student.getName());
        JTextField emailField = new JTextField(student.getEmail());
        JTextField courseField = new JTextField(student.getCourse());

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(courseLabel);
        panel.add(courseField);

        JButton updateBtn = new JButton("✔ Update Student");
        updateBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        updateBtn.setBackground(new Color(40, 167, 69)); // Bootstrap Success
        updateBtn.setForeground(Color.WHITE);
        updateBtn.setFocusPainted(false);
        updateBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panel.add(new JLabel()); // Empty cell
        panel.add(updateBtn);

        add(panel);

        updateBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String course = courseField.getText().trim();

            // Email validation
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                JOptionPane.showMessageDialog(this, "Enter a valid email address.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (name.isEmpty() || email.isEmpty() || course.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Student updated = new Student(student.getId(), name, email, course);
            boolean success = new StudentDAO().updateStudent(updated);
            if (success) {
                JOptionPane.showMessageDialog(this, "🎉 Student updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                viewPanel.loadStudents();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update student.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
