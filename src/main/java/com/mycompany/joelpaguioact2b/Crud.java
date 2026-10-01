/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.joelpaguioact2b;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author pagsc
 */
public class Crud extends javax.swing.JFrame {
    Connection conn;
    PreparedStatement pst;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Crud.class.getName());

    /**
     * Creates new form Crud
     */
    public Crud() {
        initComponents();
        display.setRowHeight(35);
        conn = InventoryConn.conn();
        setLocationRelativeTo(null);
        reading();
    }
    
    
    // everytime na kinocall out mo sya rerefersh nya lahat sa jtable 
//    
//    
//    
//    
//    
//    
    private void reading() {
    String sql = "SELECT * FROM product";

    try {
        pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        javax.swing.table.DefaultTableModel model =
                (javax.swing.table.DefaultTableModel) display.getModel();

        
        model.setRowCount(0);

        
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getObject(2), //i wanna add a label + "PC "  
                rs.getObject(3),  
                rs.getObject(4),  
                rs.getObject(5)   
            });
        }

        rs.close();
        pst.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Error reading products: " + e.getMessage());
    }
}
    
    
    
    
    
    //Create ka ng produkto 
    //    
//    
//    
//    
//    
//    
    private void creating() {
        
        
    String pc = JOptionPane.showInputDialog(
            this, "Enter PC number:");

    if (pc == null || pc.trim().isEmpty()) {
        return;
    }
        
    String username = JOptionPane.showInputDialog(
            this, "Enter Username:");

    if (username == null || username.trim().isEmpty()) {
        return;
    }

    String time = JOptionPane.showInputDialog(
            this, "Enter Time:");

    if (time == null || time.trim().isEmpty()) {
        return;
    }

    String password = JOptionPane.showInputDialog(
            this, "Enter Password:");

    if (password == null || password.trim().isEmpty()) {
        return;
    }

    String sql = "INSERT INTO product (pc, username, time, password) VALUES (?, ?, ?, ?)";

    try {
        pst = conn.prepareStatement(sql);

        pst.setString(2, pc);
        pst.setString(3, username);
        pst.setString(4, time);
        pst.setString(5, password);

        int inserted = pst.executeUpdate();

        if (inserted > 0) {
            JOptionPane.showMessageDialog(this,
                    "Account added successfully!");

            // Refresh the table
            reading();
        }

        pst.close();

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,
                "QTY must be a whole number and Price must be a number.");

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Error adding product: " + e.getMessage());
    }
}
    
     
    //delete mo yung isang row
    //    
//    
//    
//    
//    
//    
    private void deleting() {
    int selectedRow = display.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this,
                "Please select a product to delete.");
        return;
    }

    String id = display.getValueAt(selectedRow, 0).toString();

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this product?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
    );

    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    String sql = "DELETE FROM product WHERE ID = ?";

    try {
        pst = conn.prepareStatement(sql);
        pst.setInt(1, Integer.parseInt(id));

        int deleted = pst.executeUpdate();

        if (deleted > 0) {
            JOptionPane.showMessageDialog(this,
                    "Product deleted successfully!");

       
            reading();
        }

        pst.close();

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,
                "Invalid product ID.");

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Error deleting product: " + e.getMessage());
    }
}
    
    
    
    //Inaaupdate mo sila kapag sinelect mo yung jtable tas btn na update
//    
//    
//    
//    
//    
//    
    
    private void updating() {
    int selectedRow = display.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this,
                "Please select a product to update.");
        return;
    }

    String id = display.getValueAt(selectedRow, 0).toString();
    String name = JOptionPane.showInputDialog(this,
            "Enter new product name:",
            display.getValueAt(selectedRow, 1));

    String qty = JOptionPane.showInputDialog(this,
            "Enter new quantity:",
            display.getValueAt(selectedRow, 2));

    String price = JOptionPane.showInputDialog(this,
            "Enter new price:",
            display.getValueAt(selectedRow, 3));

    if (name == null || qty == null || price == null) {
        return;
    }

    String sql = "UPDATE product SET product_name = ?, qty = ?, price = ? WHERE ID = ?";

    try {
        pst = conn.prepareStatement(sql);

        pst.setString(1, name);
        pst.setInt(2, Integer.parseInt(qty));
        pst.setDouble(3, Double.parseDouble(price));
        pst.setInt(4, Integer.parseInt(id));

        int updated = pst.executeUpdate();

        if (updated > 0) {
            JOptionPane.showMessageDialog(this,
                    "Product updated successfully!");

            reading();
        }

        pst.close();

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,
                "QTY must be a whole number and Price must be a number.");

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Error updating product: " + e.getMessage());
    }
}
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        display = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        pnl_1 = new javax.swing.JPanel();
        txt_piatos = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        btn_piatos_up = new javax.swing.JButton();
        jPanel49 = new javax.swing.JPanel();
        btn_piatos_down = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        rdo_piatos = new javax.swing.JRadioButton();
        pnl_2 = new javax.swing.JPanel();
        txt_piatos1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        btn_piatos_up1 = new javax.swing.JButton();
        jPanel50 = new javax.swing.JPanel();
        btn_piatos_down1 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        rdo_piatos1 = new javax.swing.JRadioButton();
        pnl_3 = new javax.swing.JPanel();
        txt_piatos2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        btn_piatos_up2 = new javax.swing.JButton();
        jPanel51 = new javax.swing.JPanel();
        btn_piatos_down2 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        rdo_piatos2 = new javax.swing.JRadioButton();
        pnl_4 = new javax.swing.JPanel();
        txt_piatos3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        btn_piatos_up3 = new javax.swing.JButton();
        jPanel52 = new javax.swing.JPanel();
        btn_piatos_down3 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        rdo_piatos3 = new javax.swing.JRadioButton();
        jPanel21 = new javax.swing.JPanel();
        rdo_piatos4 = new javax.swing.JRadioButton();
        pnl_5 = new javax.swing.JPanel();
        txt_piatos4 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        btn_piatos_up4 = new javax.swing.JButton();
        jPanel53 = new javax.swing.JPanel();
        btn_piatos_down4 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        rdo_piatos5 = new javax.swing.JRadioButton();
        pnl_6 = new javax.swing.JPanel();
        txt_piatos5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        btn_piatos_up5 = new javax.swing.JButton();
        jPanel54 = new javax.swing.JPanel();
        btn_piatos_down5 = new javax.swing.JButton();
        pnl_7 = new javax.swing.JPanel();
        txt_piatos6 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        btn_piatos_up6 = new javax.swing.JButton();
        jPanel55 = new javax.swing.JPanel();
        btn_piatos_down6 = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        rdo_piatos6 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        display.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        display.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Online to", "Username", "Time", "Password"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        display.setMinimumSize(new java.awt.Dimension(100, 100));
        jScrollPane1.setViewportView(display);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Comshop");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );

        jButton1.setText("Add Time");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("Add Account");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton4.setText("Delete Account");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(224, 224, 248));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnl_1.setBackground(new java.awt.Color(255, 255, 255));

        txt_piatos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_piatos.setForeground(new java.awt.Color(51, 255, 51));
        txt_piatos.addActionListener(this::txt_piatosActionPerformed);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 255));
        jLabel6.setText("PC# 01");

        jPanel13.setBackground(new java.awt.Color(204, 204, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_piatos_up.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_piatos_up.setText("-");
        btn_piatos_up.setContentAreaFilled(false);
        btn_piatos_up.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_piatos_up.setPreferredSize(new java.awt.Dimension(30, 20));
        btn_piatos_up.addActionListener(this::btn_piatos_upActionPerformed);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_up, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_up, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel49.setBackground(new java.awt.Color(204, 204, 255));
        jPanel49.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_piatos_down.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_piatos_down.setText("+");
        btn_piatos_down.setContentAreaFilled(false);
        btn_piatos_down.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_piatos_down.setPreferredSize(new java.awt.Dimension(30, 20));
        btn_piatos_down.addActionListener(this::btn_piatos_downActionPerformed);

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel49Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_piatos_down, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel49Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_down, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnl_1Layout = new javax.swing.GroupLayout(pnl_1);
        pnl_1.setLayout(pnl_1Layout);
        pnl_1Layout.setHorizontalGroup(
            pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_piatos, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(39, 39, 39))
        );
        pnl_1Layout.setVerticalGroup(
            pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_piatos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGroup(pnl_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel3.add(pnl_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 260, 50));

        jPanel12.setBackground(new java.awt.Color(204, 204, 255));

        rdo_piatos.setBackground(new java.awt.Color(204, 204, 255));
        rdo_piatos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rdo_piatos.addActionListener(this::rdo_piatosActionPerformed);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(rdo_piatos)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdo_piatos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 80, 50));

        pnl_2.setBackground(new java.awt.Color(255, 255, 255));

        txt_piatos1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_piatos1.setForeground(new java.awt.Color(51, 255, 51));
        txt_piatos1.addActionListener(this::txt_piatos1ActionPerformed);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 255));
        jLabel7.setText("PC# 01");

        jPanel14.setBackground(new java.awt.Color(204, 204, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_piatos_up1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_piatos_up1.setText("-");
        btn_piatos_up1.setContentAreaFilled(false);
        btn_piatos_up1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_piatos_up1.setPreferredSize(new java.awt.Dimension(30, 20));
        btn_piatos_up1.addActionListener(this::btn_piatos_up1ActionPerformed);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_up1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_up1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel50.setBackground(new java.awt.Color(204, 204, 255));
        jPanel50.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_piatos_down1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_piatos_down1.setText("+");
        btn_piatos_down1.setContentAreaFilled(false);
        btn_piatos_down1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_piatos_down1.setPreferredSize(new java.awt.Dimension(30, 20));
        btn_piatos_down1.addActionListener(this::btn_piatos_down1ActionPerformed);

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_piatos_down1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_down1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnl_2Layout = new javax.swing.GroupLayout(pnl_2);
        pnl_2.setLayout(pnl_2Layout);
        pnl_2Layout.setHorizontalGroup(
            pnl_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_piatos1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(39, 39, 39))
        );
        pnl_2Layout.setVerticalGroup(
            pnl_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_piatos1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addGroup(pnl_2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel3.add(pnl_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 260, -1));

        jPanel15.setBackground(new java.awt.Color(204, 204, 255));

        rdo_piatos1.setBackground(new java.awt.Color(204, 204, 255));
        rdo_piatos1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rdo_piatos1.addActionListener(this::rdo_piatos1ActionPerformed);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(rdo_piatos1)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdo_piatos1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        pnl_3.setBackground(new java.awt.Color(255, 255, 255));

        txt_piatos2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_piatos2.setForeground(new java.awt.Color(51, 255, 51));
        txt_piatos2.addActionListener(this::txt_piatos2ActionPerformed);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 255));
        jLabel8.setText("PC# 01");

        jPanel16.setBackground(new java.awt.Color(204, 204, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_piatos_up2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_piatos_up2.setText("-");
        btn_piatos_up2.setContentAreaFilled(false);
        btn_piatos_up2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_piatos_up2.setPreferredSize(new java.awt.Dimension(30, 20));
        btn_piatos_up2.addActionListener(this::btn_piatos_up2ActionPerformed);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_up2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_up2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel51.setBackground(new java.awt.Color(204, 204, 255));
        jPanel51.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_piatos_down2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_piatos_down2.setText("+");
        btn_piatos_down2.setContentAreaFilled(false);
        btn_piatos_down2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_piatos_down2.setPreferredSize(new java.awt.Dimension(30, 20));
        btn_piatos_down2.addActionListener(this::btn_piatos_down2ActionPerformed);

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_piatos_down2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_down2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnl_3Layout = new javax.swing.GroupLayout(pnl_3);
        pnl_3.setLayout(pnl_3Layout);
        pnl_3Layout.setHorizontalGroup(
            pnl_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_piatos2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(39, 39, 39))
        );
        pnl_3Layout.setVerticalGroup(
            pnl_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_piatos2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8))
                    .addGroup(pnl_3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel3.add(pnl_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 260, -1));

        jPanel17.setBackground(new java.awt.Color(204, 204, 255));

        rdo_piatos2.setBackground(new java.awt.Color(204, 204, 255));
        rdo_piatos2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rdo_piatos2.addActionListener(this::rdo_piatos2ActionPerformed);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(rdo_piatos2)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdo_piatos2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        pnl_4.setBackground(new java.awt.Color(255, 255, 255));

        txt_piatos3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_piatos3.setForeground(new java.awt.Color(51, 255, 51));
        txt_piatos3.addActionListener(this::txt_piatos3ActionPerformed);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 255));
        jLabel9.setText("PC# 01");

        jPanel18.setBackground(new java.awt.Color(204, 204, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_piatos_up3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_piatos_up3.setText("-");
        btn_piatos_up3.setContentAreaFilled(false);
        btn_piatos_up3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_piatos_up3.setPreferredSize(new java.awt.Dimension(30, 20));
        btn_piatos_up3.addActionListener(this::btn_piatos_up3ActionPerformed);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_up3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_up3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel52.setBackground(new java.awt.Color(204, 204, 255));
        jPanel52.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_piatos_down3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_piatos_down3.setText("+");
        btn_piatos_down3.setContentAreaFilled(false);
        btn_piatos_down3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_piatos_down3.setPreferredSize(new java.awt.Dimension(30, 20));
        btn_piatos_down3.addActionListener(this::btn_piatos_down3ActionPerformed);

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel52Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_piatos_down3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel52Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_down3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnl_4Layout = new javax.swing.GroupLayout(pnl_4);
        pnl_4.setLayout(pnl_4Layout);
        pnl_4Layout.setHorizontalGroup(
            pnl_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_4Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_piatos3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(39, 39, 39))
        );
        pnl_4Layout.setVerticalGroup(
            pnl_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_piatos3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addGroup(pnl_4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel3.add(pnl_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, 260, -1));

        jPanel19.setBackground(new java.awt.Color(204, 204, 255));

        rdo_piatos3.setBackground(new java.awt.Color(204, 204, 255));
        rdo_piatos3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rdo_piatos3.addActionListener(this::rdo_piatos3ActionPerformed);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(rdo_piatos3)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdo_piatos3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, -1, -1));

        jPanel21.setBackground(new java.awt.Color(204, 204, 255));

        rdo_piatos4.setBackground(new java.awt.Color(204, 204, 255));
        rdo_piatos4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rdo_piatos4.addActionListener(this::rdo_piatos4ActionPerformed);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(rdo_piatos4)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdo_piatos4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, -1));

        pnl_5.setBackground(new java.awt.Color(255, 255, 255));

        txt_piatos4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_piatos4.setForeground(new java.awt.Color(51, 255, 51));
        txt_piatos4.addActionListener(this::txt_piatos4ActionPerformed);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 255));
        jLabel10.setText("PC# 01");

        jPanel20.setBackground(new java.awt.Color(204, 204, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_piatos_up4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_piatos_up4.setText("-");
        btn_piatos_up4.setContentAreaFilled(false);
        btn_piatos_up4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_piatos_up4.setPreferredSize(new java.awt.Dimension(30, 20));
        btn_piatos_up4.addActionListener(this::btn_piatos_up4ActionPerformed);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_up4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_up4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel53.setBackground(new java.awt.Color(204, 204, 255));
        jPanel53.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_piatos_down4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_piatos_down4.setText("+");
        btn_piatos_down4.setContentAreaFilled(false);
        btn_piatos_down4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_piatos_down4.setPreferredSize(new java.awt.Dimension(30, 20));
        btn_piatos_down4.addActionListener(this::btn_piatos_down4ActionPerformed);

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel53Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_piatos_down4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel53Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_down4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnl_5Layout = new javax.swing.GroupLayout(pnl_5);
        pnl_5.setLayout(pnl_5Layout);
        pnl_5Layout.setHorizontalGroup(
            pnl_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_5Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_piatos4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(39, 39, 39))
        );
        pnl_5Layout.setVerticalGroup(
            pnl_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_piatos4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addGroup(pnl_5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel3.add(pnl_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 490, 260, -1));

        jPanel23.setBackground(new java.awt.Color(204, 204, 255));

        rdo_piatos5.setBackground(new java.awt.Color(204, 204, 255));
        rdo_piatos5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rdo_piatos5.addActionListener(this::rdo_piatos5ActionPerformed);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(rdo_piatos5)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdo_piatos5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, -1, -1));

        pnl_6.setBackground(new java.awt.Color(255, 255, 255));

        txt_piatos5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_piatos5.setForeground(new java.awt.Color(51, 255, 51));
        txt_piatos5.addActionListener(this::txt_piatos5ActionPerformed);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 255));
        jLabel11.setText("PC# 01");

        jPanel22.setBackground(new java.awt.Color(204, 204, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_piatos_up5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_piatos_up5.setText("-");
        btn_piatos_up5.setContentAreaFilled(false);
        btn_piatos_up5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_piatos_up5.setPreferredSize(new java.awt.Dimension(30, 20));
        btn_piatos_up5.addActionListener(this::btn_piatos_up5ActionPerformed);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_up5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_up5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel54.setBackground(new java.awt.Color(204, 204, 255));
        jPanel54.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_piatos_down5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_piatos_down5.setText("+");
        btn_piatos_down5.setContentAreaFilled(false);
        btn_piatos_down5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_piatos_down5.setPreferredSize(new java.awt.Dimension(30, 20));
        btn_piatos_down5.addActionListener(this::btn_piatos_down5ActionPerformed);

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_piatos_down5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_down5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnl_6Layout = new javax.swing.GroupLayout(pnl_6);
        pnl_6.setLayout(pnl_6Layout);
        pnl_6Layout.setHorizontalGroup(
            pnl_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_6Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_piatos5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(39, 39, 39))
        );
        pnl_6Layout.setVerticalGroup(
            pnl_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_piatos5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11))
                    .addGroup(pnl_6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel3.add(pnl_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 560, 260, -1));

        pnl_7.setBackground(new java.awt.Color(255, 255, 255));

        txt_piatos6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_piatos6.setForeground(new java.awt.Color(51, 255, 51));
        txt_piatos6.addActionListener(this::txt_piatos6ActionPerformed);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 255));
        jLabel12.setText("PC# 01");

        jPanel24.setBackground(new java.awt.Color(204, 204, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_piatos_up6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_piatos_up6.setText("-");
        btn_piatos_up6.setContentAreaFilled(false);
        btn_piatos_up6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_piatos_up6.setPreferredSize(new java.awt.Dimension(30, 20));
        btn_piatos_up6.addActionListener(this::btn_piatos_up6ActionPerformed);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_up6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_up6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel55.setBackground(new java.awt.Color(204, 204, 255));
        jPanel55.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_piatos_down6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_piatos_down6.setText("+");
        btn_piatos_down6.setContentAreaFilled(false);
        btn_piatos_down6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_piatos_down6.setPreferredSize(new java.awt.Dimension(30, 20));
        btn_piatos_down6.addActionListener(this::btn_piatos_down6ActionPerformed);

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel55Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_piatos_down6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel55Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_piatos_down6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnl_7Layout = new javax.swing.GroupLayout(pnl_7);
        pnl_7.setLayout(pnl_7Layout);
        pnl_7Layout.setHorizontalGroup(
            pnl_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_7Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_piatos6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(39, 39, 39))
        );
        pnl_7Layout.setVerticalGroup(
            pnl_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_piatos6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addGroup(pnl_7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel3.add(pnl_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 260, -1));

        jPanel25.setBackground(new java.awt.Color(204, 204, 255));

        rdo_piatos6.setBackground(new java.awt.Color(204, 204, 255));
        rdo_piatos6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        rdo_piatos6.addActionListener(this::rdo_piatos6ActionPerformed);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(rdo_piatos6)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdo_piatos6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Time:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 300, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        creating();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        updating();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        deleting();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txt_piatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_piatosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_piatosActionPerformed

    private void btn_piatos_upActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_piatos_upActionPerformed
      

    }//GEN-LAST:event_btn_piatos_upActionPerformed

    private void btn_piatos_downActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_piatos_downActionPerformed
     
    }//GEN-LAST:event_btn_piatos_downActionPerformed

    private void rdo_piatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_piatosActionPerformed
        
    }//GEN-LAST:event_rdo_piatosActionPerformed

    private void txt_piatos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_piatos1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_piatos1ActionPerformed

    private void btn_piatos_up1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_piatos_up1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_piatos_up1ActionPerformed

    private void btn_piatos_down1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_piatos_down1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_piatos_down1ActionPerformed

    private void rdo_piatos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_piatos1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_piatos1ActionPerformed

    private void txt_piatos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_piatos2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_piatos2ActionPerformed

    private void btn_piatos_up2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_piatos_up2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_piatos_up2ActionPerformed

    private void btn_piatos_down2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_piatos_down2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_piatos_down2ActionPerformed

    private void rdo_piatos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_piatos2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_piatos2ActionPerformed

    private void txt_piatos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_piatos3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_piatos3ActionPerformed

    private void btn_piatos_up3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_piatos_up3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_piatos_up3ActionPerformed

    private void btn_piatos_down3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_piatos_down3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_piatos_down3ActionPerformed

    private void rdo_piatos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_piatos3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_piatos3ActionPerformed

    private void txt_piatos4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_piatos4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_piatos4ActionPerformed

    private void btn_piatos_up4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_piatos_up4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_piatos_up4ActionPerformed

    private void btn_piatos_down4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_piatos_down4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_piatos_down4ActionPerformed

    private void rdo_piatos4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_piatos4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_piatos4ActionPerformed

    private void txt_piatos5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_piatos5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_piatos5ActionPerformed

    private void btn_piatos_up5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_piatos_up5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_piatos_up5ActionPerformed

    private void btn_piatos_down5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_piatos_down5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_piatos_down5ActionPerformed

    private void rdo_piatos5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_piatos5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_piatos5ActionPerformed

    private void txt_piatos6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_piatos6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_piatos6ActionPerformed

    private void btn_piatos_up6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_piatos_up6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_piatos_up6ActionPerformed

    private void btn_piatos_down6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_piatos_down6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_piatos_down6ActionPerformed

    private void rdo_piatos6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_piatos6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_piatos6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Crud().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_piatos_down;
    private javax.swing.JButton btn_piatos_down1;
    private javax.swing.JButton btn_piatos_down2;
    private javax.swing.JButton btn_piatos_down3;
    private javax.swing.JButton btn_piatos_down4;
    private javax.swing.JButton btn_piatos_down5;
    private javax.swing.JButton btn_piatos_down6;
    private javax.swing.JButton btn_piatos_up;
    private javax.swing.JButton btn_piatos_up1;
    private javax.swing.JButton btn_piatos_up2;
    private javax.swing.JButton btn_piatos_up3;
    private javax.swing.JButton btn_piatos_up4;
    private javax.swing.JButton btn_piatos_up5;
    private javax.swing.JButton btn_piatos_up6;
    private javax.swing.JTable display;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnl_1;
    private javax.swing.JPanel pnl_2;
    private javax.swing.JPanel pnl_3;
    private javax.swing.JPanel pnl_4;
    private javax.swing.JPanel pnl_5;
    private javax.swing.JPanel pnl_6;
    private javax.swing.JPanel pnl_7;
    private javax.swing.JRadioButton rdo_piatos;
    private javax.swing.JRadioButton rdo_piatos1;
    private javax.swing.JRadioButton rdo_piatos2;
    private javax.swing.JRadioButton rdo_piatos3;
    private javax.swing.JRadioButton rdo_piatos4;
    private javax.swing.JRadioButton rdo_piatos5;
    private javax.swing.JRadioButton rdo_piatos6;
    private javax.swing.JTextField txt_piatos;
    private javax.swing.JTextField txt_piatos1;
    private javax.swing.JTextField txt_piatos2;
    private javax.swing.JTextField txt_piatos3;
    private javax.swing.JTextField txt_piatos4;
    private javax.swing.JTextField txt_piatos5;
    private javax.swing.JTextField txt_piatos6;
    // End of variables declaration//GEN-END:variables
}
