package ui;

import dao.StudentDAO;
import model.Student;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;

    public ButtonEditor(JCheckBox checkBox, String label) {
        super(checkBox);
        this.label = label;
        button = new JButton(label);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        if (label.equals("Delete")) {
            button.setBackground(new Color(220, 53, 69)); // Bootstrap Danger Red
        } else if (label.equals("Update")) {
            button.setBackground(new Color(23, 162, 184)); // Bootstrap Info Blue
        } else {
            button.setBackground(new Color(108, 117, 125)); // Secondary Gray
        }

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                int row = table.getSelectedRow();
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());

                if (label.equals("Delete")) {
                    int confirm = JOptionPane.showConfirmDialog(button, "Delete student ID " + id + "?",
                            "Confirm", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        new StudentDAO().deleteStudent(id);
                        ((ViewStudentsPanel) SwingUtilities.getAncestorOfClass(ViewStudentsPanel.class, table)).loadStudents();
                    }
                } else if (label.equals("Update")) {
                    Student s = new StudentDAO().getStudentById(id);
                    ViewStudentsPanel viewPanel = (ViewStudentsPanel) SwingUtilities.getAncestorOfClass(ViewStudentsPanel.class, table);
                    new UpdateStudentForm(s, viewPanel).setVisible(true);
                }
            }
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        this.table = table;
        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        isPushed = false;
        return label;
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
