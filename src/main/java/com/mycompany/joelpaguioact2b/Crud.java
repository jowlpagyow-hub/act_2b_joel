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
                rs.getObject(1),  
                rs.getObject(2),  
                rs.getObject(3),  
                rs.getObject(4)   
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

    String name = JOptionPane.showInputDialog(
            this, "Enter product name:");

    if (name == null || name.trim().isEmpty()) {
        return;
    }

    String qty = JOptionPane.showInputDialog(
            this, "Enter quantity:");

    if (qty == null || qty.trim().isEmpty()) {
        return;
    }

    String price = JOptionPane.showInputDialog(
            this, "Enter price:");

    if (price == null || price.trim().isEmpty()) {
        return;
    }

    String sql = "INSERT INTO product (product_name, qty, price) VALUES (?, ?, ?)";

    try {
        pst = conn.prepareStatement(sql);

        pst.setString(1, name);
        pst.setInt(2, Integer.parseInt(qty));
        pst.setDouble(3, Double.parseDouble(price));

        int inserted = pst.executeUpdate();

        if (inserted > 0) {
            JOptionPane.showMessageDialog(this,
                    "Product added successfully!");

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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

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
                "Unique Number", "Product", "Quantity", "Price"
            }
        ));
        display.setMinimumSize(new java.awt.Dimension(100, 100));
        jScrollPane1.setViewportView(display);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        jButton1.setText("Update");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("Create");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton4.setText("Delete");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
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
    private javax.swing.JTable display;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
