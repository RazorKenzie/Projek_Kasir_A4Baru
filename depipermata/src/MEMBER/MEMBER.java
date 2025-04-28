/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package MEMBER;

import KONEKSI.konek;
import com.mysql.cj.xdevapi.Statement;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;




/**
 *
 * @author devipermata
 */
public class MEMBER extends javax.swing.JFrame {

    
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel model;
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private static final double DISKON_DEFAULT = 0.10; // 10%



    public MEMBER() {
        initComponents();
        conn = konek.getConnection(); // Menggunakan koneksi dari class Konek
        loadTable();
    }
    
    private void loadTable() {
        String[] columnNames = {"ID Member", "Nama Member", "Diskon"};
        model = new DefaultTableModel(columnNames, 0);
        tblMember.setModel(model);
        try {
            pst = conn.prepareStatement("SELECT * FROM member ORDER BY idMember");
            rs = pst.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("idMember"),
                    rs.getString("nama_member"),
                    decimalFormat.format(DISKON_DEFAULT) // Selalu tampilkan 10%
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mengambil data: " + ex.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tambahbtn = new javax.swing.JButton();
        ubahbtn = new javax.swing.JButton();
        hapusbtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMember = new javax.swing.JTable();
        idMember = new javax.swing.JTextField();
        nama_member = new javax.swing.JTextField();
        panel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tambahbtn.setText("tambah");
        tambahbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahbtnActionPerformed(evt);
            }
        });
        getContentPane().add(tambahbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, -1, -1));

        ubahbtn.setText("ubah");
        ubahbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahbtnActionPerformed(evt);
            }
        });
        getContentPane().add(ubahbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 500, -1, -1));

        hapusbtn.setText("hapus");
        hapusbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusbtnActionPerformed(evt);
            }
        });
        getContentPane().add(hapusbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 560, -1, -1));

        tblMember.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblMember);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, -1, -1));

        idMember.setText("jTextField1");
        idMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idMemberActionPerformed(evt);
            }
        });
        getContentPane().add(idMember, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 260, 30));

        nama_member.setText("jTextField2");
        getContentPane().add(nama_member, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 260, 40));

        panel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/APK DEPI (1).png"))); // NOI18N
        panel.setText("jLabel1");
        getContentPane().add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tambahbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahbtnActionPerformed
     String id = idMember.getText().trim();
    String nama = nama_member.getText().trim();
    
    if (id.isEmpty() || nama.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Kolom ID Member dan Nama Member tidak boleh kosong");
        return;
    }
    
    try {
        pst = conn.prepareStatement("INSERT INTO member (idMember, nama_member, diskon) VALUES (?, ?, ?)");
        pst.setString(1, id);
        pst.setString(2, nama);
        pst.setDouble(3, DISKON_DEFAULT); // Set diskon selalu 10%
        pst.executeUpdate();
        loadTable();
        JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Gagal menambahkan data: " + ex.getMessage());
    }
    }//GEN-LAST:event_tambahbtnActionPerformed

    private void ubahbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahbtnActionPerformed
     String id = idMember.getText().trim();
String nama = nama_member.getText().trim();

if (id.isEmpty() || nama.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Kolom ID Member dan Nama Member tidak boleh kosong");
    return;
}

try {
    pst = conn.prepareStatement("UPDATE member SET nama_member = ? WHERE idMember = ?");
    pst.setString(1, nama);
    pst.setString(2, id);
    int rowsUpdated = pst.executeUpdate();
    
    if (rowsUpdated > 0) {
        loadTable();
        JOptionPane.showMessageDialog(this, "Data berhasil diubah");
    } else {
        JOptionPane.showMessageDialog(this, "ID Member tidak ditemukan");
    }
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, "Gagal mengubah data: " + ex.getMessage());
}
    }//GEN-LAST:event_ubahbtnActionPerformed

    private void hapusbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusbtnActionPerformed
    String id = idMember.getText().trim();

if (id.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Kolom ID Member tidak boleh kosong");
    return;
}

int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
if (confirm == JOptionPane.YES_OPTION) {
    try {
        pst = conn.prepareStatement("DELETE FROM member WHERE idMember = ?");
        pst.setString(1, id);
        int rowsDeleted = pst.executeUpdate();
        
        if (rowsDeleted > 0) {
            loadTable();
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus");
        } else {
            JOptionPane.showMessageDialog(this, "ID Member tidak ditemukan");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + ex.getMessage());
    }
}
    }//GEN-LAST:event_hapusbtnActionPerformed

    private void idMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idMemberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idMemberActionPerformed

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
            java.util.logging.Logger.getLogger(MEMBER.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MEMBER.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MEMBER.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MEMBER.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MEMBER().setVisible(true);
            }
        });
    }
 private void scanRFID(String rfid) {
    try {
        // Cek apakah RFID sudah ada dalam database
        pst = conn.prepareStatement("SELECT * FROM member WHERE idMember = ?");
        pst.setString(1, rfid);
        rs = pst.executeQuery();

        if (rs.next()) {
            // Jika RFID ditemukan, tampilkan data ke field
            idMember.setText(rs.getString("idMember"));
            nama_member.setText(rs.getString("nama_member"));
            panel.setText(decimalFormat.format(DISKON_DEFAULT)); // Diskon tetap 10%

            JOptionPane.showMessageDialog(this, "Member ditemukan: " + rs.getString("nama_member"));

            // Perbarui tabel agar data terlihat
            loadTable();
        } else {
            // Jika tidak ditemukan, isi field dengan nomor RFID & minta user input nama
            idMember.setText(rfid);
            nama_member.setText(""); // Kosongkan nama untuk diisi manual

            JOptionPane.showMessageDialog(this, "RFID belum terdaftar. Silakan masukkan nama lalu simpan.");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Gagal membaca data: " + ex.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menutup koneksi: " + e.getMessage());
        }
    }
}






 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hapusbtn;
    private javax.swing.JTextField idMember;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nama_member;
    private javax.swing.JLabel panel;
    private javax.swing.JButton tambahbtn;
    private javax.swing.JTable tblMember;
    private javax.swing.JButton ubahbtn;
    // End of variables declaration//GEN-END:variables
}
