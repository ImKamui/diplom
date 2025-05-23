package mainPackage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author danil
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class PracticeForm extends javax.swing.JFrame {

    /**
     * Creates new form PracticeForm
     */
    public PracticeForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resultQueryScrollPane = new javax.swing.JScrollPane();
        resultQueryTextArea = new javax.swing.JTextArea();
        QueryScrollPane = new javax.swing.JScrollPane();
        QueryTextArea = new javax.swing.JTextArea();
        createTableButton = new javax.swing.JButton();
        useQueryButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        outputLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Практический раздел");
        setLocation(new java.awt.Point(550, 150));

        resultQueryTextArea.setColumns(20);
        resultQueryTextArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        resultQueryTextArea.setRows(5);
        resultQueryTextArea.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                resultQueryTextAreaPropertyChange(evt);
            }
        });
        resultQueryScrollPane.setViewportView(resultQueryTextArea);

        QueryTextArea.setColumns(20);
        QueryTextArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        QueryTextArea.setRows(5);
        QueryScrollPane.setViewportView(QueryTextArea);

        createTableButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        createTableButton.setText("Создать базу данных");
        createTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createTableButtonActionPerformed(evt);
            }
        });

        useQueryButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        useQueryButton.setText("Применить запрос");
        useQueryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useQueryButtonActionPerformed(evt);
            }
        });

        backButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        backButton.setText("<< Назад");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        outputLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(resultQueryScrollPane)
            .addComponent(QueryScrollPane)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(createTableButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(outputLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(useQueryButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resultQueryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(QueryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(outputLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(createTableButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(useQueryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resultQueryTextAreaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_resultQueryTextAreaPropertyChange
        resultQueryTextArea.setEditable(false);
    }//GEN-LAST:event_resultQueryTextAreaPropertyChange

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.dispose();
        FirstAppPageForm backToChoise = new FirstAppPageForm();
        backToChoise.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void createTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTableButtonActionPerformed
        //Connect();
        Conn conn = new Conn();
        conn.Connect(outputLabel);
    }//GEN-LAST:event_createTableButtonActionPerformed

    private void useQueryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useQueryButtonActionPerformed
        Query query = new Query();
        query.UseQuery(resultQueryTextArea, QueryTextArea, outputLabel);
    }//GEN-LAST:event_useQueryButtonActionPerformed

    
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
            java.util.logging.Logger.getLogger(PracticeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PracticeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PracticeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PracticeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PracticeForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane QueryScrollPane;
    private static javax.swing.JTextArea QueryTextArea;
    private javax.swing.JButton backButton;
    private javax.swing.JButton createTableButton;
    private static javax.swing.JLabel outputLabel;
    private javax.swing.JScrollPane resultQueryScrollPane;
    private static javax.swing.JTextArea resultQueryTextArea;
    private javax.swing.JButton useQueryButton;
    // End of variables declaration//GEN-END:variables
}
