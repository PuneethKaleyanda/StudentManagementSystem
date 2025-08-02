package ui;

import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JFrame {

    public MainDashboard() {
        setTitle("Student Management Dashboard");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane tabs = new JTabbedPane();

        ViewStudentsPanel viewPanel = new ViewStudentsPanel();

        // 🧠 Pass the loadStudents method to AddStudentForm
        AddStudentForm addPanel = new AddStudentForm(viewPanel::loadStudents);

        tabs.addTab("➕ Add Student", addPanel);
        tabs.addTab("📋 View Students", viewPanel);

        add(tabs, BorderLayout.CENTER);
        setVisible(true);
    }
}
