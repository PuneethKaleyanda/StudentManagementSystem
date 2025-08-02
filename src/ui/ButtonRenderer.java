package ui;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer(String text) {
        setText(text);
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setBackground(new Color(230, 230, 230));
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value != null ? value.toString() : "");
        return this;
    }
}
