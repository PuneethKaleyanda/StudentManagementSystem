import ui.MainDashboard;

import javax.swing.*;

public class StudentManagementSystem {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new MainDashboard());
    }
}
