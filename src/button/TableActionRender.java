/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package button;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Huu Thai
 */
public class TableActionRender extends DefaultTableCellRenderer{

   private final int numberOfButton;

    public TableActionRender(int numberOfButton) {
        this.numberOfButton = numberOfButton;
    }
    

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        // Gọi phương thức gốc để lấy component cơ sở
        Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
        JPanel action;
        if(numberOfButton == 2){
            action = new PanelAction();  // Tạo panel chứa các nút action
        }else {
            action = new Btn_chiTiet();
        }
        
        if (!isSelected) {
            action.setBackground(Color.WHITE);  // Thiết lập background cho trạng thái không được chọn
        } else {
            action.setBackground(com.getBackground());  // Sử dụng background của component cha nếu được chọn
        }
        return action;  // Trả về panel chứa action
    }

    
}
