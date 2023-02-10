package Others;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
 
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
 
/**
 * A simple renderer class for JTable component.
 * @author www.codejava.net
 *
 */
@SuppressWarnings("serial")
public class TableHeader extends JLabel implements TableCellRenderer {
 
    public TableHeader() {
        setFont(new Font("Cambria Math", Font.BOLD, 15));
        setForeground(Color.BLUE);
        setBackground(Color.black);
        setBorder(BorderFactory.createEtchedBorder());
        setHorizontalAlignment(CENTER);
    }
     
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        return this;
    }
 
}