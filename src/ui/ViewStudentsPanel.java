package ui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewStudentsPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    public ViewStudentsPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 248, 252));

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField searchField = new JTextField();
        JButton searchBtn = new JButton("\uD83D\uDD0D Search");

        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchBtn, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        String[] columns = {"ID", "Name", "Email", "Course", "Update", "Delete"};
        model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return column == 4 || column == 5;
            }
        };

        table = new JTable(model);
        table.setRowHeight(32);
        table.getColumn("Update").setCellRenderer(new ButtonRenderer("✏️ Update"));
        table.getColumn("Update").setCellEditor(new ButtonEditor(new JCheckBox(), "Update"));
        table.getColumn("Delete").setCellRenderer(new ButtonRenderer("🗑️ Delete"));
        table.getColumn("Delete").setCellEditor(new ButtonEditor(new JCheckBox(), "Delete"));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadStudents();

        searchBtn.addActionListener(e -> {
            String keyword = searchField.getText().toLowerCase();
            filterStudents(keyword);
        });
    }

    public void loadStudents() {
        model.setRowCount(0);
        StudentDAO dao = new StudentDAO();
        List<Student> students = dao.getAllStudents();
        for (Student s : students) {
            model.addRow(new Object[]{s.getId(), s.getName(), s.getEmail(), s.getCourse(), "Update", "Delete"});
        }
    }

    private void filterStudents(String keyword) {
        model.setRowCount(0);
        StudentDAO dao = new StudentDAO();
        for (Student s : dao.getAllStudents()) {
            if (s.getName().toLowerCase().contains(keyword) ||
                    s.getEmail().toLowerCase().contains(keyword) ||
                    s.getCourse().toLowerCase().contains(keyword)) {
                model.addRow(new Object[]{s.getId(), s.getName(), s.getEmail(), s.getCourse(), "Update", "Delete"});
            }
        }
    }
}
