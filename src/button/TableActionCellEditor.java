/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package button;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author Huu Thai
 */
public class TableActionCellEditor extends DefaultCellEditor{
    
   private final TableActionEvent event;
   private final JPanel action;

    public TableActionCellEditor(TableActionEvent e, int numberOfButton) {
        super(new JCheckBox());
        this.event = e;
        if(numberOfButton == 2) {
            action = new PanelAction();
        }else {
            action = new Btn_chiTiet();
        }
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        
        if (action instanceof PanelAction panelAction) {
                panelAction.initEvent(event, row);
        } else if (action instanceof Btn_chiTiet btn_chiTiet) {
            btn_chiTiet.initEvent(event, row);
        }

              // Khởi tạo sự kiện cho các nút dựa trên hàng
            action.setBackground(table.getSelectionBackground());  // Thiết lập background cho trạng thái được chọn
            return action;  // Trả về panel để hiển thị trong cell
        }
    
}
