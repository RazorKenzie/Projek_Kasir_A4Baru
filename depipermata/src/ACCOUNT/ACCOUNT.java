/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ACCOUNT;

import HALUT.HALAMANUTAMA;
import LAPORAN.LAPORAN;
import PEMBELIAN.PEMBELIAN;
import PENJUALAN.PENJUALAN;
import STOK.STOK;
import KONEKSI.konek;
import static KONEKSI.konek.conn;
import LOGIN.LOGINUMKM;
import SUPPLIER.SUPPLIER;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;


/**
 *
 * @author devipermata
 */
public class ACCOUNT extends javax.swing.JFrame {

    public ACCOUNT() {
        initComponents();
        conn = konek.getConnection();
        getData();
        
            }
     private void getData() {
    String sql = "SELECT p.id_penjual, p.nama_penjual, p.alamat, p.no_telp, p.email, a.username, a.password "
                 + "FROM Penjual p "
                 + "JOIN akun a ON a.id_penjual = p.id_penjual";

    try {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            String idPenjual = rs.getString("id_penjual");
            String namaPenjual = rs.getString("nama_penjual");
            String alamat = rs.getString("alamat");
            String noTelp = rs.getString("no_telp");
            String email = rs.getString("email");
            String username = rs.getString("username");
            String password = rs.getString("password");

            a.setText(idPenjual);
            b.setText(namaPenjual);
            c.setText(alamat);
            d.setText(noTelp);
            e.setText(email);
            f.setText(username);
            g.setText(password);
        }

        rs.close();
        stmt.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Kesalahan saat memuat data: " + ex.getMessage());
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

        a = new javax.swing.JLabel();
        b = new javax.swing.JLabel();
        c = new javax.swing.JLabel();
        d = new javax.swing.JLabel();
        e = new javax.swing.JLabel();
        f = new javax.swing.JLabel();
        g = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        ubah = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        a.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        getContentPane().add(a, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 350, 140, 30));

        b.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        b.setText("jLabel2");
        getContentPane().add(b, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 260, 380, 20));

        c.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        c.setText("jLabel3");
        getContentPane().add(c, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, 310, -1));

        d.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        d.setText("jLabel4");
        getContentPane().add(d, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 550, 480, 30));

        e.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        e.setText("jLabel1");
        getContentPane().add(e, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 400, 300, 30));

        f.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        f.setText("jLabel2");
        getContentPane().add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 460, 250, 20));

        g.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        g.setText("jLabel3");
        getContentPane().add(g, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 500, 300, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/20.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, -1));

        ubah.setText("jButton3");
        ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahActionPerformed(evt);
            }
        });
        getContentPane().add(ubah, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 660, 170, 40));

        logout.setText("jButton4");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        getContentPane().add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 660, 170, 40));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, 40));

        jButton8.setText("jButton8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 713, -1, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         new HALAMANUTAMA().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
int confirm = JOptionPane.showConfirmDialog(this, 
                    "Apakah Anda yakin ingin logout?", 
                    "Logout", 
                    JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        this.dispose();

        LOGINUMKM loginPage = new LOGINUMKM();
        loginPage.setVisible(true);
    }
    }//GEN-LAST:event_logoutActionPerformed

    private void ubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahActionPerformed
       String idPenjual = JOptionPane.showInputDialog(this, "Masukkan ID Penjual:", a.getText());
    String namaPenjual = JOptionPane.showInputDialog(this, "Masukkan Nama Penjual:", b.getText());
    String alamat = JOptionPane.showInputDialog(this, "Masukkan Alamat:", c.getText());
    String noTelp = JOptionPane.showInputDialog(this, "Masukkan No. Telepon:", d.getText());
    String email = JOptionPane.showInputDialog(this, "Masukkan Email:", e.getText());
    String username = JOptionPane.showInputDialog(this, "Masukkan Username:", f.getText());
    String password = JOptionPane.showInputDialog(this, "Masukkan Password:", g.getText());

    // Perbarui label dengan data baru
    if (idPenjual != null) a.setText(idPenjual);
    if (namaPenjual != null) b.setText(namaPenjual);
    if (alamat != null) c.setText(alamat);
    if (noTelp != null) d.setText(noTelp);
    if (email != null) e.setText(email);
    if (username != null) f.setText(username);
    if (password != null) g.setText(password);

    JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!");

    }//GEN-LAST:event_ubahActionPerformed

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
            java.util.logging.Logger.getLogger(ACCOUNT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ACCOUNT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ACCOUNT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ACCOUNT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ACCOUNT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel a;
    private javax.swing.JLabel b;
    private javax.swing.JLabel c;
    private javax.swing.JLabel d;
    private javax.swing.JLabel e;
    private javax.swing.JLabel f;
    private javax.swing.JLabel g;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton logout;
    private javax.swing.JButton ubah;
    // End of variables declaration//GEN-END:variables

    
}