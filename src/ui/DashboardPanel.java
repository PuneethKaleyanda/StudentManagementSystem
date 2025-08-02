package ui;

import dao.StudentDAO;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {
        setLayout(new GridLayout(1, 1));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        setBackground(new Color(245, 248, 252));

        StudentDAO dao = new StudentDAO();
        int total = dao.getAllStudents().size();

        JLabel label = new JLabel("\uD83D\uDC68\u200D\uD83C\uDF93 Total Students: " + total, JLabel.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 24));
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setForeground(Color.DARK_GRAY);
        label.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        add(label);
    }
}