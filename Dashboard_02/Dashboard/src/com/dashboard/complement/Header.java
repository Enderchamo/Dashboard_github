/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.dashboard.complement;

import java.awt.event.ActionListener;


public class Header extends javax.swing.JPanel {

    
    public Header() {
        initComponents();
    }
    
    public void addMenuEvent(ActionListener event){
        
        cmdMenu.addActionListener(event);
    
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdMenu = new com.dashboard.swing.Button();

        setBackground(new java.awt.Color(114, 138, 147));

        cmdMenu.setBackground(new java.awt.Color(114, 138, 147));
        cmdMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/dashboard/icon/menu.png"))); // NOI18N
        cmdMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(569, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmdMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMenuActionPerformed
        
    }//GEN-LAST:event_cmdMenuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.dashboard.swing.Button cmdMenu;
    // End of variables declaration//GEN-END:variables
}
