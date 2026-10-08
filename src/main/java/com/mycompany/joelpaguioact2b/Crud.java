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
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author pagsc
 */
public class Crud extends javax.swing.JFrame {
    Connection conn;
    PreparedStatement pst;
    private javax.swing.Timer countdownTimer;
    
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
        startCountdownTimer();
    }
    
    
    private void startCountdownTimer() {
        // Runs every 1 second (1000 ms)
        countdownTimer = new javax.swing.Timer(1000, e -> updateTableTimers());
        countdownTimer.start();
    }
    
    

    private void updateTableTimers() {
        DefaultTableModel model = (DefaultTableModel) display.getModel();
        
        for (int i = 0; i < model.getRowCount(); i++) {
            Object timeVal = model.getValueAt(i, 3);
            if (timeVal == null) continue;

            String timeStr = timeVal.toString().trim();
            if (timeStr.isEmpty() || timeStr.equals("00:00") || timeStr.equals("00:00:00")) {
                continue; // Skip finished/empty timers
            }

            // Convert "MM:SS" or "HH:MM:SS" string to total seconds
            int totalSeconds = parseTimeToSeconds(timeStr);

            if (totalSeconds > 0) {
                totalSeconds--; // Decrement by 1 second

                String newTimeStr = formatSecondsToTime(totalSeconds);
                model.setValueAt(newTimeStr, i, 3); // Update display table live

                // Automatically save to database when timer hits zero
                if (totalSeconds == 0) {
                    String id = model.getValueAt(i, 0).toString();
                    updateDatabaseTime(id, "00:00");
                }
            }
        }
    }

    // Convert "MM:SS" or "HH:MM:SS" to total seconds
    private int parseTimeToSeconds(String timeStr) {
        try {
            String[] parts = timeStr.split(":");
            if (parts.length == 2) { // MM:SS
                int minutes = Integer.parseInt(parts[0]);
                int seconds = Integer.parseInt(parts[1]);
                return (minutes * 60) + seconds;
            } else if (parts.length == 3) { // HH:MM:SS
                int hours = Integer.parseInt(parts[0]);
                int minutes = Integer.parseInt(parts[1]);
                int seconds = Integer.parseInt(parts[2]);
                return (hours * 3600) + (minutes * 60) + seconds;
            }
        } catch (NumberFormatException e) {
            // Invalid time format
        }
        return 0;
    }

    // Convert total seconds back to formatted string ("MM:SS" or "HH:MM:SS")
    private String formatSecondsToTime(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%02d:%02d", minutes, seconds);
        }
    }

    private void updateDatabaseTime(String id, String time) {
        String sql = "UPDATE pcaccounts SET time = ? WHERE ID = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, time);
            pst.setString(2, id);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            System.err.println("Error updating time in DB: " + e.getMessage());
        }
    }

    
     // everytime na kinocall out mo sya rerefersh nya lahat sa jtable 
//    
//    
//    
//    
//    
//    
    private void reading() {
    String sql = "SELECT * FROM pcaccounts";

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

    String sql = "INSERT INTO pcaccounts (pcnum, activity, time) VALUES (?, ?, ?)";

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

    String sql = "DELETE FROM pcaccounts WHERE ID = ?";

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
                "Please select an account to update.");
        return;
    }

    String id = display.getValueAt(selectedRow, 0).toString();

    String pcnum = JOptionPane.showInputDialog(this,
            "Enter PC Number:",
            display.getValueAt(selectedRow, 1));

    String activity = JOptionPane.showInputDialog(this,
            "Enter Activity:",
            display.getValueAt(selectedRow, 2));

    String time = JOptionPane.showInputDialog(this,
            "Enter Time:",
            display.getValueAt(selectedRow, 3));

    // Cancel if any input dialog was closed/cancelled
    if (pcnum == null || activity == null || time == null) {
        return;
    }

    String sql = "UPDATE pcaccounts SET pcnum = ?, activity = ?, time = ? WHERE ID = ?";

    try {
        pst = conn.prepareStatement(sql);

        // Setting all parameters as Strings
        pst.setString(1, pcnum);
        pst.setString(2, activity);
        pst.setString(3, time);
        pst.setString(4, id);

        int updated = pst.executeUpdate();

        if (updated > 0) {
            JOptionPane.showMessageDialog(this,
                    "Account updated successfully!");

            reading();
        }

        pst.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Error updating account: " + e.getMessage());
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        display = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

        display.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        display.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Unique #", "PC #", "Activity", "Time"
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

        jButton2.setText("Open PC");
        jButton2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton1.setText("Edit PC");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton4.setText("Turnoff PC");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
