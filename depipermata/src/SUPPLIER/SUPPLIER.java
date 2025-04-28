/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package SUPPLIER;

import ACCOUNT.ACCOUNT;
import HALUT.HALAMANUTAMA;
import KONEKSI.konek;
import LAPORAN.LAPORAN;
import PEMBELIAN.PEMBELIAN;
import PENJUALAN.PENJUALAN;
import STOK.STOK;
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
public class SUPPLIER extends javax.swing.JFrame {

     private Connection conn;
    public SUPPLIER() {
        initComponents();
        conn = konek.getConnection();
        getData();
    }
    private void getData() {
         DefaultTableModel model = (DefaultTableModel)tabel.getModel(); 
     model.setRowCount(0);
     model.setColumnIdentifiers(new Object[] { "ID Supplier", "Nama Supplier", "Alamat", "No Telp" });
     
     try{
         String sql = "Select * From Supplier";
        PreparedStatement st = conn.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        
        while(rs.next()){
            String id_supplier = rs.getString("id_supplier");
            String nama_supplier = rs.getString("nama_supplier");
            String alamat = rs.getString("alamat");
            String no_telp = rs.getString("no_telp");
           
             
            Object[] rowData = {id_supplier,nama_supplier,alamat,no_telp};
            model.addRow(rowData);

        }
        
        rs.close();
        st.close();
         
     }catch (Exception e){
       Logger.getLogger(SUPPLIER.class.getName()).log(java.util.logging.Level.SEVERE, null, e);  
        
     }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        a = new javax.swing.JTextField();
        b = new javax.swing.JTextField();
        c = new javax.swing.JTextField();
        d = new javax.swing.JTextField();
        search = new javax.swing.JTextField();
        tambahbtn = new javax.swing.JButton();
        hapusbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        cari = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID SUPPLIER", "NAMA SUPPLIER", "ALAMAT SUPPLIER", "NO.TELP SUPPLIER"
            }
        ));
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 650, 550));

        a.setBackground(new java.awt.Color (0,0,0,0));
        a.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        a.setBorder(null);
        getContentPane().add(a, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 260, 20));

        b.setBackground(new java.awt.Color (0,0,0,0));
        b.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        b.setBorder(null);
        getContentPane().add(b, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 260, 30));

        c.setBackground(new java.awt.Color (0,0,0,0));
        c.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        c.setBorder(null);
        c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cActionPerformed(evt);
            }
        });
        getContentPane().add(c, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 407, 260, 30));

        d.setBackground(new java.awt.Color (0,0,0,0));
        d.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        d.setBorder(null);
        getContentPane().add(d, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 480, 260, 30));

        search.setBackground(new java.awt.Color (0,0,0,0));
        search.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        search.setBorder(null);
        getContentPane().add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 50, 190, -1));

        tambahbtn.setText("tambah");
        tambahbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahbtnActionPerformed(evt);
            }
        });
        getContentPane().add(tambahbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 730, -1, -1));

        hapusbtn.setText("hapus");
        hapusbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusbtnActionPerformed(evt);
            }
        });
        getContentPane().add(hapusbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 720, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/16.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 540, 160, 50));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, 50));

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, 50));

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, 40));

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, 50));

        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, 50));

        jButton9.setText("jButton9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 673, -1, 30));

        jButton10.setText("jButton10");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 720, -1, -1));

        cari.setText("jButton11");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        getContentPane().add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 43, -1, 30));

        jButton11.setText("jButton11");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 700, -1, 90));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String id_supplier = a.getText().trim();
    String nama_supplier = b.getText().trim();
    String alamat = c.getText().trim();
    String no_telp = d.getText().trim();

    if (id_supplier.isEmpty() || nama_supplier.isEmpty() || alamat.isEmpty() || no_telp.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Harap lengkapi semua field sebelum mengubah data.");
        return;
    }
    if (!no_telp.matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "Nomor telepon hanya boleh berisi angka.");
        return;
    }
    try {
        String sql = "UPDATE supplier SET nama_supplier = ?, alamat = ?, no_telp = ? WHERE id_supplier = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, nama_supplier);
        st.setString(2, alamat);
        st.setString(3, no_telp);
        st.setString(4, id_supplier);  
        int rowsUpdated = st.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Data berhasil diubah.");
            getData(); 
            resetForm(); 
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada perubahan yang terjadi. Periksa kembali data Anda.");
        }
        st.close();
    } catch (SQLException e) {
        Logger.getLogger(SUPPLIER.class.getName()).log(Level.SEVERE, null, e);
        JOptionPane.showMessageDialog(this, "Error saat mengubah data: " + e.getMessage());
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
      String keyword = search.getText(); // Ambil teks yang dimasukkan di field pencarian

    // Validasi input pencarian
    if (keyword.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Masukkan kata kunci pencarian!");
        return;
    }

    // Buat query pencarian untuk mencari data supplier berdasarkan id_supplier atau nama_supplier
    DefaultTableModel model = (DefaultTableModel)tabel.getModel();
    model.setRowCount(0); // Kosongkan tabel sebelum menampilkan hasil pencarian

    try {
        String sql = "SELECT * FROM Supplier WHERE id_supplier LIKE ? OR nama_supplier LIKE ?";
        PreparedStatement st = conn.prepareStatement(sql);
        String searchPattern = "%" + keyword + "%"; // Membuat pola pencarian
        st.setString(1, searchPattern);
        st.setString(2, searchPattern);
        
        ResultSet rs = st.executeQuery();
        
        while(rs.next()){
            String id_supplier = rs.getString("id_supplier");
            String nama_supplier = rs.getString("nama_supplier");
            String alamat = rs.getString("alamat");
            String no_telp = rs.getString("no_telp");

            Object[] rowData = {id_supplier, nama_supplier, alamat, no_telp};
            model.addRow(rowData);
        }
        
        rs.close();
        st.close();
        
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Data tidak ditemukan!");
        }
        
    } catch (Exception e) {
        Logger.getLogger(SUPPLIER.class.getName()).log(Level.SEVERE, null, e);
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat pencarian: " + e.getMessage());
    }

    }//GEN-LAST:event_cariActionPerformed

    private void cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
         int selectedRow = tabel.getSelectedRow();

                if (selectedRow != -1) {
                    // Get the data from the selected row and set it to text fields
                    String id_supplier = tabel.getValueAt(selectedRow, 0).toString();
                    String nama_supplier = tabel.getValueAt(selectedRow, 1).toString();
                    String alamat = tabel.getValueAt(selectedRow, 2).toString();
                    String no_telp = tabel.getValueAt(selectedRow, 3).toString();

                    // Set data to the text fields
                    a.setText(id_supplier);
                    b.setText(nama_supplier);
                    c.setText(alamat);
                    d.setText(no_telp);
                }
            


    }//GEN-LAST:event_tabelMouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
       SUPPLIER sp = new SUPPLIER();
       sp.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          new HALAMANUTAMA().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        STOK stk = new STOK();
        stk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         PENJUALAN pjln = new PENJUALAN();
       pjln.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       PEMBELIAN pb = new PEMBELIAN();
        pb.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        LAPORAN lp = new LAPORAN();
        lp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        ACCOUNT acc = new ACCOUNT();
       acc.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void hapusbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusbtnActionPerformed
int selectedRow= tabel.getSelectedRow();
            if(selectedRow == 1){
                JOptionPane.showMessageDialog(this,"Pilih tabel yang akan di ubah");
                return;
            }
            String id_supplier = tabel.getValueAt(selectedRow, 0).toString();
    
    
    int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus item ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
        try {
            
            String sql = "DELETE FROM Supplier WHERE id_supplier = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id_supplier);
            int rowsDeleted = st.executeUpdate();
            
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus");
                getData();
                resetForm(); 
                
            }
            
            st.close();
        } catch (Exception e) {
            Logger.getLogger(SUPPLIER.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "Error saat menghapus data: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_hapusbtnActionPerformed

    private void tambahbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahbtnActionPerformed
       String id_supplier = a.getText();
        String nama_supplier = b.getText();
        String alamat = c.getText();
        String no_telp = d.getText();;
        
         try {
        String sql = "INSERT INTO supplier (id_supplier, nama_supplier, alamat, no_telp) VALUES (?, ?, ?, ?)";
        PreparedStatement st = conn.prepareStatement(sql);
        
        st.setString(1, id_supplier);
        st.setString(2, nama_supplier);
        st.setString(3, alamat);
        st.setString(4, no_telp);
        
        int rowInserted = st.executeUpdate();
        if (rowInserted > 0) {
            JOptionPane.showMessageDialog(this, "Data Berhasil Ditambahkan");
            resetForm(); 
            getData();    
        }
        
        st.close();  
    } catch (Exception e) {
        Logger.getLogger(SUPPLIER.class.getName()).log(Level.SEVERE, null, e);
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menambahkan data: " + e.getMessage());
    }
    }//GEN-LAST:event_tambahbtnActionPerformed
    
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
            java.util.logging.Logger.getLogger(SUPPLIER.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SUPPLIER.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SUPPLIER.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SUPPLIER.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SUPPLIER().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField a;
    private javax.swing.JTextField b;
    private javax.swing.JTextField c;
    private javax.swing.JButton cari;
    private javax.swing.JTextField d;
    private javax.swing.JButton hapusbtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField search;
    private javax.swing.JTable tabel;
    private javax.swing.JButton tambahbtn;
    // End of variables declaration//GEN-END:variables

    private void resetForm() {
    a.setText("");  // Kosongkan ID Supplier
    b.setText("");  // Kosongkan Nama Supplier
    c.setText("");  // Kosongkan Alamat Supplier
    d.setText("");
    }
}
