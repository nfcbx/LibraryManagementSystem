package com.tools;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class setColumnSize {
    public static void setTableColumnSize(JTable table, int i, int preferedWidth, int maxWidth, int minWidth){
        //表格的列模型
        TableColumnModel cm = table.getColumnModel();
        //得到第i个列对象
        TableColumn column = cm.getColumn(i);
        column.setPreferredWidth(preferedWidth);
        column.setMaxWidth(maxWidth);
        column.setMinWidth(minWidth);
    }

}
