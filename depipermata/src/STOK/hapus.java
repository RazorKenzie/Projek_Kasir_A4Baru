/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package STOK;

import ACCOUNT.ACCOUNT;
import HALUT.HALAMANUTAMA;
import KONEKSI.konek;
import LAPORAN.LAPORAN;
import PEMBELIAN.PEMBELIAN;
import PENJUALAN.PENJUALAN;
import SUPPLIER.SUPPLIER;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author devipermata
 */
public class hapus extends javax.swing.JFrame {
private Connection conn;
    public hapus() {
        initComponents();
        conn = konek.getConnection();
        getData();
    }

    private void getData() {
    DefaultTableModel model = (DefaultTableModel) tabelstok.getModel(); 
    model.setRowCount(0);  // Menghapus semua data dalam tabel
    model.setColumnIdentifiers(new Object[] { "ID Barang", "Jenis Barang", "Nama Barang", "Stok", "Harga Jual", "Harga Beli", "ID PENJUAL" });

    try {
        // Query untuk mengambil data terbaru berdasarkan tanggal transaksi beli dan jual
        String sql = "SELECT b.id_barang, b.jenis_barang, b.nama_barang, b.stok, "
                   + "(SELECT tb.harga_beli "
                   + " FROM transaksi_beli tb "
                   + " WHERE tb.id_barang = b.id_barang "
                   + " ORDER BY tb.tgl_beli DESC LIMIT 1) AS harga_beli, "
                   + "(SELECT tj.harga_jual "
                   + " FROM transaksi_jual tj "
                   + " WHERE tj.id_barang = b.id_barang "
                   + " ORDER BY tj.tgl_jual DESC LIMIT 1) AS harga_jual, "
                   + "p.id_penjual "
                   + "FROM barang b "
                   + "JOIN penjual p ON p.id_penjual = b.id_penjual"; // Menambahkan join dengan tabel penjual

        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            String id_barang = rs.getString("id_barang");
            String jenis_barang = rs.getString("jenis_barang");
            String nama_barang = rs.getString("nama_barang");
            int stok = rs.getInt("stok");
            int hargaBeli = rs.getInt("harga_beli");
            int hargaJual = rs.getInt("harga_jual");
            String idPenjual = rs.getString("id_penjual"); // Mengambil ID Penjual

            // Menambahkan data ke dalam model tabel
            Object[] rowData = {id_barang, jenis_barang, nama_barang, stok, hargaJual, hargaBeli, idPenjual};
            model.addRow(rowData);
        }

        rs.close();
        st.close();

    } catch (Exception e) {
        Logger.getLogger(STOK.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelstok = new javax.swing.JTable();
        e = new javax.swing.JTextField();
        d = new javax.swing.JTextField();
        c = new javax.swing.JTextField();
        b = new javax.swing.JTextField();
        cari = new javax.swing.JTextField();
        g = new javax.swing.JTextField();
        a = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        btnubah = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelstok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID BARANG", "NAMA BARANG", "STOK", "HARGA BELI", "HARGA JUAL", "ID PENJUAL"
            }
        ));
        tabelstok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelstokMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelstok);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 640, 550));

        e.setBackground(new java.awt.Color (0,0,0,0));
        e.setBorder(null);
        getContentPane().add(e, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 570, 260, 30));

        d.setBackground(new java.awt.Color (0,0,0,0));
        d.setBorder(null);
        getContentPane().add(d, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 490, 260, 20));

        c.setBackground(new java.awt.Color (0,0,0,0));
        c.setBorder(null);
        getContentPane().add(c, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 260, 20));

        b.setBackground(new java.awt.Color (0,0,0,0));
        b.setBorder(null);
        b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bActionPerformed(evt);
            }
        });
        getContentPane().add(b, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 260, 30));

        cari.setBackground(new java.awt.Color (0,0,0,0));
        cari.setBorder(null);
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        getContentPane().add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 50, 180, -1));

        g.setBackground(new java.awt.Color(0,0,0,0));
        g.setBorder(null);
        getContentPane().add(g, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 260, 30));

        a.setBackground(new java.awt.Color (0,0,0,0));
        a.setBorder(null);
        getContentPane().add(a, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 260, 30));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 650, -1, -1));

        btnubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/Salinan APK DEPI.jpg"))); // NOI18N
        getContentPane().add(btnubah, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 630, 190, 50));

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, -1, 50));

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 80, 60));

        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 80, 60));

        jButton7.setText("jButton7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 80, 50));

        jButton8.setText("jButton8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 90, 50));

        jButton9.setText("jButton9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 50));

        jButton10.setText("jButton10");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 673, -1, 40));

        jButton11.setText("jButton11");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 720, -1, 30));

        jButton12.setText("jButton12");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(905, 43, 30, 30));

        jButton13.setText("jButton13");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 700, 80, 80));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabelstokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelstokMouseClicked
      int selectedRow = tabelstok.getSelectedRow(); // Mendapatkan baris yang dipilih
    if (selectedRow != -1) {
        // Mengambil nilai dari kolom yang diklik
        String id_barang = tabelstok.getValueAt(selectedRow, 0).toString();  // ID Barang
        String jenis_barang = tabelstok.getValueAt(selectedRow, 1).toString();  // Jenis Barang
        String nama_barang = tabelstok.getValueAt(selectedRow, 2).toString();  // Nama Barang
        int stok = Integer.parseInt(tabelstok.getValueAt(selectedRow, 3).toString());  // Stok
        int harga_jual = Integer.parseInt(tabelstok.getValueAt(selectedRow, 4).toString());  // Harga Jual
        int harga_beli = Integer.parseInt(tabelstok.getValueAt(selectedRow, 5).toString());  // Harga Beli
        String id_penjual = tabelstok.getValueAt(selectedRow, 6).toString();  // ID Penjual
        
        // Menampilkan nilai di JTextField (variabel yang sesuai dengan nama Anda)
        a.setText(id_barang);  // ID Barang
        g.setText(jenis_barang);  // Jenis Barang (variabel g digunakan untuk menampilkan Jenis Barang)
        b.setText(nama_barang);  // Nama Barang
        c.setText(String.valueOf(stok));  // Stok
        d.setText(String.valueOf(harga_beli));  // Harga Beli
        e.setText(String.valueOf(harga_jual));  // Harga Jual
    }
    }//GEN-LAST:event_tabelstokMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String id_barang = a.getText();
    
    if (id_barang.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus!");
        return; 
    }

    try {
        String sql = "DELETE FROM barang WHERE id_barang = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, id_barang);
        
        int result = pst.executeUpdate();
        
        if (result > 0) {
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            DefaultTableModel model = (DefaultTableModel) tabelstok.getModel();
            int selectedRow = tabelstok.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow); 
            }
            a.setText(""); 
            b.setText(""); 
            c.setText("");  
            d.setText(""); 
            e.setText("");  
            g.setText("");  
        } else {
            JOptionPane.showMessageDialog(null, "Gagal menghapus data!");
        }
        
        pst.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
          new HALAMANUTAMA().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
         STOK stk = new STOK();
        stk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
         PENJUALAN pjln = new PENJUALAN();
       pjln.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        PEMBELIAN pb = new PEMBELIAN();
        pb.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         LAPORAN lp = new LAPORAN();
       lp.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       SUPPLIER sp = new SUPPLIER();
       sp.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        ACCOUNT acc = new ACCOUNT();
       acc.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
         dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
       String keyword = cari.getText().trim();  // Mendapatkan kata kunci dari searchField
    if (!keyword.isEmpty()) {
        searchData(keyword);
    } else {
        getData();  // Jika tidak ada kata kunci, tampilkan semua data
    }
}

private void searchData(String keyword) {
    DefaultTableModel model = (DefaultTableModel) tabelstok.getModel();
    model.setRowCount(0);  // Reset tabel

    try {
        String sql = "SELECT * FROM barang WHERE id_barang LIKE ? OR nama_barang LIKE ?"; // SQL query untuk mencari berdasarkan id_barang atau nama_barang
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + keyword + "%");  // Pencarian berdasarkan id_barang
        stmt.setString(2, "%" + keyword + "%");  // Pencarian berdasarkan nama_barang
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String id_barang = rs.getString("id_barang");
            String nama_barang = rs.getString("nama_barang");
            int stok = rs.getInt("stok");
            int harga_jual = rs.getInt("harga_jual");
            int harga_beli = rs.getInt("harga_beli");
            String id_penjual = rs.getString("id_penjual");

            Object[] rowData = {id_barang, nama_barang, stok, harga_jual, harga_beli, id_penjual};
            model.addRow(rowData);
        }

        rs.close();
        stmt.close();

    } catch (Exception e) {
        Logger.getLogger(STOK.class.getName()).log(Level.SEVERE, null, e);
        JOptionPane.showMessageDialog(this, "Error saat mencari data.");
    }
    }//GEN-LAST:event_cariActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        String keyword = cari.getText().trim();  // Mendapatkan kata kunci dari searchField
    if (!keyword.isEmpty()) {
        searchData(keyword);
    } else {
        getData();  // Jika tidak ada kata kunci, tampilkan semua data
    }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        STOK stk = new STOK();
        stk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      String idBarang = a.getText().trim(); // Ambil ID barang dari TextField a

    if (idBarang.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Pilih dulu barang yang ingin diubah barcode-nya.");
        return;
    }

    // Tampilkan input dialog untuk barcode baru
    String newBarcode = JOptionPane.showInputDialog(this, "Masukkan barcode baru:");

    if (newBarcode == null) {
        // User klik Cancel
        return;
    }
    newBarcode = newBarcode.trim();

    if (newBarcode.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Barcode baru tidak boleh kosong.");
        return;
    }

    try {
        String sql = "UPDATE barang SET barcode = ? WHERE id_barang = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, newBarcode);
            st.setString(2, idBarang);
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Barcode berhasil diubah.");
                getData(); // Refresh tabel kalau perlu
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengubah barcode. Barang tidak ditemukan.");
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage());
    }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(hapus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(hapus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(hapus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(hapus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new hapus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField a;
    private javax.swing.JTextField b;
    private javax.swing.JLabel btnubah;
    private javax.swing.JTextField c;
    private javax.swing.JTextField cari;
    private javax.swing.JTextField d;
    private javax.swing.JTextField e;
    private javax.swing.JTextField g;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelstok;
    // End of variables declaration//GEN-END:variables

    private void resetForm() {
    }

    
}
