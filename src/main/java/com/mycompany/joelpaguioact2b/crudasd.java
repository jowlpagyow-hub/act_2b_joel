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
 * @author CL2-PC
 */
public class crudasd extends javax.swing.JFrame {
    Connection conn;
    PreparedStatement pst;

    

    /**
     * Creates new form crudasd
     */
    public crudasd() {
        initComponents();
        conn = InventoryConn.conn();
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
                "00" + rs.getObject(1), 
                "PC " + rs.getObject(2),  
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
    
      
    private void creating() {
        
        
    
        
    String pc = JOptionPane.showInputDialog(
            this, "Enter PC number:");

    if (pc == null || pc.trim().isEmpty()) {
        return;
    }

    String activity = JOptionPane.showInputDialog(
            this, "Enter Activity:");

    if (activity == null || activity.trim().isEmpty()) {
        return;
    }

    String time = JOptionPane.showInputDialog(
            this, "Enter Time:");

    if (time == null || time.trim().isEmpty()) {
        return;
    }

    String sql = "INSERT INTO product (pcnum, activity, time) VALUES (?, ?, ?)";

    try {
        pst = conn.prepareStatement(sql);
        
        pst.setString(1, pc);
        pst.setString(2, activity);
        pst.setString(3, time);

        int inserted = pst.executeUpdate();

        if (inserted > 0) {
            JOptionPane.showMessageDialog(this,
                    "Account added successfully!");

            // Refresh the table
            reading();
        }

        pst.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Error adding account: " + e.getMessage());
    }
}
    
    
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

        jScrollPane1 = new javax.swing.JScrollPane();
        display = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        display.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        display.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Password", "PC #", "Activity", "Time"
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

        jButton2.setText("Add Account");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Add Time");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setText("Delete Account");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(190, Short.MAX_VALUE))
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(crudasd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(crudasd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(crudasd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(crudasd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new crudasd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable display;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
