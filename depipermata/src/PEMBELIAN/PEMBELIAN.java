/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PEMBELIAN;

import ACCOUNT.ACCOUNT;
import HALUT.HALAMANUTAMA;
import KONEKSI.konek;
import LAPORAN.LAPORAN;
import PENJUALAN.PENJUALAN;
import STOK.STOK;
import SUPPLIER.SUPPLIER;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author devipermata
 */
public class PEMBELIAN extends javax.swing.JFrame {
private Connection conn;
    public PEMBELIAN() {
        initComponents();
        conn = konek.getConnection();
     
     String[] judul = {"NO FAKTUR", "TGL BELI", "ID SUPPLIER","ID BARANG","NAMA BARANG", "HARGA BARANG",  
                          "STOK", "JUMLAH", "TOTAL BELI"};
    DefaultTableModel model = new DefaultTableModel(judul, 0);
        tabel.setModel(model);
        loadSuppliers();
        getData();
    }
private void getData() {
    DefaultTableModel model = (DefaultTableModel) tabel.getModel();
    model.setRowCount(0);  // Clear the table before loading new data

    // Definisi nama kolom tabel
    String[] columnNames = {
        "No Faktur",
        "Tanggal Beli",
        "ID Supplier",
        "Nama Supplier",
        "ID Barang",
        "Jenis Barang",
        "Nama Barang",
        "Harga Beli",
        "Jumlah",
        "Total Beli"
    };

    // Set nama kolom pada model tabel
    model.setColumnIdentifiers(columnNames);

    // Query untuk mengambil data
    String sql = "SELECT t.No_Faktur, t.tgl_beli, t.jumlah, t.harga_beli, t.total_beli, " +
                 "s.id_supplier, s.nama_supplier, b.id_barang, b.nama_barang, b.jenis_barang " +
                 "FROM transaksi_beli t " +
                 "JOIN barang b ON t.id_barang = b.id_barang " +
                 "JOIN supplier s ON t.id_supplier = s.id_supplier " +
                 "ORDER BY t.No_Faktur, t.tgl_beli, s.id_supplier, s.nama_supplier, " +
                 "b.id_barang, b.jenis_barang, b.nama_barang, t.harga_beli";

    try (PreparedStatement pst = conn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  // Format tanggal
        while (rs.next()) {
            // Ambil data dari ResultSet
            String noFaktur = rs.getString("No_Faktur");
            java.sql.Date tglBeli = rs.getDate("tgl_beli");
            int jumlah = rs.getInt("jumlah");
            int hargaBeli = rs.getInt("harga_beli");
            int totalBeli = rs.getInt("total_beli");
            String idSupplier = rs.getString("id_supplier");
            String namaSupplier = rs.getString("nama_supplier");
            String idBarang = rs.getString("id_barang");
            String namaBarang = rs.getString("nama_barang");
            String jenisBarang = rs.getString("jenis_barang");

            // Format hargaBeli dan totalBeli sebagai mata uang
            String hargaBeliFormatted = "Rp " + String.format("%,d", hargaBeli);
            String totalBeliFormatted = "Rp " + String.format("%,d", totalBeli);

            // Format tanggal pembelian
            String formattedDate = dateFormat.format(tglBeli);

            // Tambahkan data ke tabel
            model.addRow(new Object[]{
                noFaktur,             // No Faktur
                formattedDate,        // Tanggal Beli
                idSupplier,           // ID Supplier
                namaSupplier,         // Nama Supplier
                idBarang,             // ID Barang
                jenisBarang,          // Jenis Barang
                namaBarang,           // Nama Barang
                hargaBeliFormatted,   // Harga Beli
                jumlah,               // Jumlah
                totalBeliFormatted    // Total Beli
            });
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat mengambil data: " + ex.getMessage());
    }
}


  
    private void loadSuppliers() {
    // Query untuk mengambil ID dan Nama Supplier dari database
    String sql = "SELECT id_supplier, nama_supplier FROM supplier";
    
    try (PreparedStatement pst = conn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {
        
        // Kosongkan ComboBox sebelum mengisinya dengan data baru
        supplier.removeAllItems();

        // Menambahkan data supplier ke ComboBox
        while (rs.next()) {
            String idSupplier = rs.getString("id_supplier");
            String namaSupplier = rs.getString("nama_supplier");
            supplier.addItem(namaSupplier);  // Hanya menampilkan nama supplier
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error loading suppliers: " + ex.getMessage());
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

        a = new javax.swing.JTextField();
        c = new javax.swing.JTextField();
        tgl = new com.toedter.calendar.JDateChooser();
        gg = new javax.swing.JTextField();
        harga = new javax.swing.JTextField();
        f = new javax.swing.JTextField();
        j = new javax.swing.JTextField();
        supplier = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        CARI = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        tambahbtn = new javax.swing.JButton();
        hargajual = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        a.setBackground(new java.awt.Color (0,0,0,0));
        a.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        a.setBorder(null);
        a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aActionPerformed(evt);
            }
        });
        getContentPane().add(a, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 250, 20));

        c.setBackground(new java.awt.Color (0,0,0,0));
        c.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        c.setBorder(null);
        c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cActionPerformed(evt);
            }
        });
        getContentPane().add(c, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 590, 270, 20));

        tgl.setBackground(new java.awt.Color (0,0,0,0));
        tgl.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        getContentPane().add(tgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 260, 30));

        gg.setBackground(new java.awt.Color(0,0,0,0));
        gg.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        gg.setBorder(null);
        getContentPane().add(gg, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 270, 30));

        harga.setBackground(new java.awt.Color(0,0,0,0));
        harga.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        harga.setBorder(null);
        harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaActionPerformed(evt);
            }
        });
        getContentPane().add(harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 520, 270, 20));

        f.setBackground(new java.awt.Color(0,0,0,0));
        f.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        f.setBorder(null);
        getContentPane().add(f, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 440, 270, 30));

        j.setBackground(new java.awt.Color (0,0,0,0));
        j.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        j.setBorder(null);
        j.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jActionPerformed(evt);
            }
        });
        getContentPane().add(j, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 660, 260, 30));

        supplier.setBackground(new java.awt.Color(0,0,0,0));
        supplier.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        supplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        supplier.setBorder(null);
        getContentPane().add(supplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 260, -1));

        jTextField1.setBackground(new java.awt.Color (0,0,0,0));
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 50, 190, -1));

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NO.FAKTUR", "TANGGAL BELI", "HARGA BELI", "JUMLAH", "TOTAL BELI", "ID SUPPLIER", "ID BARANG", "Title 8", "Title 9"
            }
        ));
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 142, 650, 540));

        CARI.setBackground(new java.awt.Color (0,0,0,0));
        getContentPane().add(CARI, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 720, 160, 50));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 80, 50));

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 90, 50));

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 90, 40));

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 363, 90, 60));

        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 433, 80, 50));

        jButton9.setText("jButton9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 680, -1, -1));

        jButton10.setText("jButton10");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 720, -1, -1));

        jButton11.setText("jButton11");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 90, 60));

        jButton12.setText("jButton12");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 40, -1, 40));

        jButton13.setText("jButton13");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 730, -1, 60));

        jButton8.setText("jButton8");
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 740, -1, -1));

        tambahbtn.setText("jButton7");
        tambahbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahbtnActionPerformed(evt);
            }
        });
        getContentPane().add(tambahbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 740, -1, -1));

        hargajual.setText("jTextField2");
        getContentPane().add(hargajual, new org.netbeans.lib.awtextra.AbsoluteConstraints(113, 712, 320, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/ubah.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, -1));
        jLabel1.getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String noFaktur = a.getText().trim();
    String namaBarang = f.getText().trim();
    String jenisBarang = gg.getText().trim();
    String jumlahStr = c.getText().trim();
    String hargaStr = harga.getText().trim();

    if (noFaktur.isEmpty() || namaBarang.isEmpty() || jenisBarang.isEmpty() || jumlahStr.isEmpty() || hargaStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi.");
        return;
    }

    try {
        int jumlah = Integer.parseInt(jumlahStr);
        int hargaBeli = Integer.parseInt(hargaStr.replace("Rp ", "").trim());  // Menghapus "Rp" dan mengonversi

        if (jumlah <= 0 || hargaBeli <= 0) {
            JOptionPane.showMessageDialog(this, "Jumlah atau harga tidak valid.");
            return;
        }

        String selectedSupplier = (String) supplier.getSelectedItem();
        String sqlGetSupplierId = "SELECT id_supplier FROM supplier WHERE nama_supplier = ?";
        String idSupplier = "";
        try (PreparedStatement pst = conn.prepareStatement(sqlGetSupplierId)) {
            pst.setString(1, selectedSupplier);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                idSupplier = rs.getString("id_supplier");
            } else {
                JOptionPane.showMessageDialog(this, "Supplier tidak ditemukan.");
                return;
            }
        }

        int totalBeli = hargaBeli * jumlah;

        String sqlUpdateTransaksi = "UPDATE transaksi_beli SET id_supplier = ?, nama_barang = ?, jenis_barang = ?, harga_beli = ?, jumlah = ?, total_beli = ? WHERE No_Faktur = ?";
        try (PreparedStatement pstTransaksi = conn.prepareStatement(sqlUpdateTransaksi)) {
            pstTransaksi.setString(1, idSupplier);
            pstTransaksi.setString(2, namaBarang);
            pstTransaksi.setString(3, jenisBarang);
            pstTransaksi.setInt(4, hargaBeli);
            pstTransaksi.setInt(5, jumlah);
            pstTransaksi.setInt(6, totalBeli);  
            pstTransaksi.setString(7, noFaktur);  

            int rowsAffected = pstTransaksi.executeUpdate();  
            if (rowsAffected > 0) {
                getData();
                JOptionPane.showMessageDialog(this, "Transaksi berhasil diupdate.");
            } else {
                JOptionPane.showMessageDialog(this, "No Faktur tidak ditemukan.");
            }
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat mengupdate data: " + ex.getMessage());
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Input tidak valid: " + ex.getMessage());
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
     int row = tabel.getSelectedRow();  // Get the selected row

    // Pastikan ada baris yang dipilih
    if (row != -1) {
        // Ambil nilai dari tabel
        String noFaktur = tabel.getValueAt(row, 0).toString();
        String tglBeli = tabel.getValueAt(row, 1).toString();
        String idSupplier = tabel.getValueAt(row, 2).toString();
        String idBarang = tabel.getValueAt(row, 4).toString();
        String namaBarang = tabel.getValueAt(row, 6).toString();
        String jenisBarang = tabel.getValueAt(row, 5).toString();
        String hargaBeliStr = tabel.getValueAt(row, 7).toString();
        String jumlahStr = tabel.getValueAt(row, 8).toString();

        // Remove "Rp" dan titik dari hargaBeliStr
        hargaBeliStr = hargaBeliStr.replace("Rp", "").replace(".", "").trim();

        try {
            // Convert harga dan jumlah ke integer
            int hargaBeli = Integer.parseInt(hargaBeliStr);
            int jumlah = Integer.parseInt(jumlahStr.trim());

            // Hitung total beli
            int totalBeli = hargaBeli * jumlah;

            // Format total sebagai mata uang
            String totalFormatted = "Rp " + String.format("%,d", totalBeli);

            // Set values ke field input
            a.setText(noFaktur);  // No Faktur
            f.setText(namaBarang); // Nama Barang
            gg.setText(jenisBarang); // Jenis Barang
            c.setText(jumlahStr);   // Jumlah
            harga.setText(hargaBeliStr);  // Harga Beli
            j.setText(totalFormatted);  // Total

            // Set tanggal pembelian ke JDateChooser (tglBeli)
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date date = sdf.parse(tglBeli);
                tgl.setDate(date);  // Set tanggal pembelian
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Error parsing date: " + ex.getMessage());
            }

        } catch (NumberFormatException ex) {
            // Menangani jika terjadi kesalahan parsing angka
            JOptionPane.showMessageDialog(this, "Error parsing harga or jumlah: " + ex.getMessage());
        } catch (Exception ex) {
            // Menangani kesalahan lainnya
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(this, "Pilih baris terlebih dahulu.");
    }                         
    }//GEN-LAST:event_tabelMouseClicked

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

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        PEMBELIAN pb = new PEMBELIAN();
        pb.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         LAPORAN lp = new LAPORAN();
       lp.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         SUPPLIER sp = new SUPPLIER();
       sp.setVisible(true);
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

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
 String keyword = jTextField1.getText().trim();
    if (!keyword.isEmpty()) {
        searchData(keyword);
    } else {
        JOptionPane.showMessageDialog(this, "Harap masukkan kata kunci pencarian.");
    }

   
    
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
         PEMBELIAN pb = new PEMBELIAN();
        pb.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jActionPerformed

    private void cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cActionPerformed
        String jumlahStr = c.getText().trim();  // Ambil nilai jumlah
    String hargaBeliStr = harga.getText().trim();  // Ambil nilai harga beli

    // Validasi input
    if (jumlahStr.isEmpty() || hargaBeliStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Jumlah dan harga beli harus diisi.");
        return;
    }

    try {
        // Mengonversi input jumlah dan harga beli ke tipe data integer
        int jumlah = Integer.parseInt(jumlahStr);
        int hargaBeli = Integer.parseInt(hargaBeliStr.replace("Rp ", "").trim()); // Menghapus simbol "Rp" jika ada
        
        // Menghitung total beli (jumlah * harga beli)
        int totalBeli = hargaBeli * jumlah;

        // Menampilkan total beli di label atau textfield tertentu (misalnya totalHarga)
        j.setText("Rp " + totalBeli); // Misalnya, menampilkan total di field total

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Input jumlah atau harga tidak valid.");
    }
    }//GEN-LAST:event_cActionPerformed

    private void aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aActionPerformed

    private void hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaActionPerformed

    private void tambahbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahbtnActionPerformed
   String namaBarang = f.getText().trim();
    String jenisBarang = gg.getText().trim();
    String jumlahStr = c.getText().trim();
    String hargaBeliStr = harga.getText().trim();
    String hargaJualStr = hargajual.getText().trim(); // <-- Tambahan input harga jual

    // Validasi input
    if (namaBarang.isEmpty() || jenisBarang.isEmpty() || jumlahStr.isEmpty() || hargaBeliStr.isEmpty() || hargaJualStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi.");
        return;
    }

    try {
        int jumlah = Integer.parseInt(jumlahStr);
        int hargaBeli = Integer.parseInt(hargaBeliStr.replaceAll("[^\\d]", ""));
        int hargaJual = Integer.parseInt(hargaJualStr.replaceAll("[^\\d]", "")); // <-- Parsing harga jual

        if (jumlah <= 0 || hargaBeli <= 0 || hargaJual <= 0) {
            JOptionPane.showMessageDialog(this, "Jumlah, harga beli, atau harga jual tidak valid.");
            return;
        }

        // Mendapatkan ID supplier
        String selectedSupplier = (String) supplier.getSelectedItem();
        String idSupplier = "";
        String sqlGetSupplierId = "SELECT id_supplier FROM supplier WHERE nama_supplier = ?";
        try (PreparedStatement pst = conn.prepareStatement(sqlGetSupplierId)) {
            pst.setString(1, selectedSupplier);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    idSupplier = rs.getString("id_supplier");
                } else {
                    JOptionPane.showMessageDialog(this, "Supplier tidak ditemukan.");
                    return;
                }
            }
        }

        // Mendapatkan ID penjual
        String idPenjual = "";
        String sqlGetPenjualId = "SELECT id_penjual FROM penjual LIMIT 1";
        try (PreparedStatement pstPenjual = conn.prepareStatement(sqlGetPenjualId)) {
            try (ResultSet rs = pstPenjual.executeQuery()) {
                if (rs.next()) {
                    idPenjual = rs.getString("id_penjual");
                } else {
                    JOptionPane.showMessageDialog(this, "Penjual tidak ditemukan.");
                    return;
                }
            }
        }

        // Proses barang
        long idBarang = -1;
        String sqlCheckBarang = "SELECT id_barang, stok, harga_beli, harga_jual FROM barang WHERE nama_barang = ? AND jenis_barang = ?";
        try (PreparedStatement pstCheck = conn.prepareStatement(sqlCheckBarang)) {
            pstCheck.setString(1, namaBarang);
            pstCheck.setString(2, jenisBarang);
            try (ResultSet rs = pstCheck.executeQuery()) {
                if (rs.next()) {
                    idBarang = rs.getLong("id_barang");
                    int stokLama = rs.getInt("stok");
                    int hargaBeliLama = rs.getInt("harga_beli");
                    int hargaJualLama = rs.getInt("harga_jual");

                    // Update harga_beli kalau beda
                    if (hargaBeliLama != hargaBeli) {
                        String sqlUpdateHargaBeli = "UPDATE barang SET harga_beli = ? WHERE id_barang = ?";
                        try (PreparedStatement pstUpdateHargaBeli = conn.prepareStatement(sqlUpdateHargaBeli)) {
                            pstUpdateHargaBeli.setInt(1, hargaBeli);
                            pstUpdateHargaBeli.setLong(2, idBarang);
                            pstUpdateHargaBeli.executeUpdate();
                        }
                    }

                    // Update harga_jual kalau beda
                    if (hargaJualLama != hargaJual) {
                        String sqlUpdateHargaJual = "UPDATE barang SET harga_jual = ? WHERE id_barang = ?";
                        try (PreparedStatement pstUpdateHargaJual = conn.prepareStatement(sqlUpdateHargaJual)) {
                            pstUpdateHargaJual.setInt(1, hargaJual);
                            pstUpdateHargaJual.setLong(2, idBarang);
                            pstUpdateHargaJual.executeUpdate();
                        }
                    }

                    // Update stok
                    String sqlUpdateStok = "UPDATE barang SET stok = stok + ? WHERE id_barang = ?";
                    try (PreparedStatement pstUpdateStok = conn.prepareStatement(sqlUpdateStok)) {
                        pstUpdateStok.setInt(1, jumlah);
                        pstUpdateStok.setLong(2, idBarang);
                        pstUpdateStok.executeUpdate();
                    }
                } else {
                    // Barang belum ada, insert baru
                    String sqlInsertBarang = "INSERT INTO barang (nama_barang, jenis_barang, stok, harga_beli, harga_jual, id_penjual) VALUES (?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstInsert = conn.prepareStatement(sqlInsertBarang, PreparedStatement.RETURN_GENERATED_KEYS)) {
                        pstInsert.setString(1, namaBarang);
                        pstInsert.setString(2, jenisBarang);
                        pstInsert.setInt(3, jumlah);
                        pstInsert.setInt(4, hargaBeli);
                        pstInsert.setInt(5, hargaJual); // <-- Masukkan harga jual
                        pstInsert.setString(6, idPenjual);
                        pstInsert.executeUpdate();

                        try (ResultSet generatedKeys = pstInsert.getGeneratedKeys()) {
                            if (generatedKeys.next()) {
                                idBarang = generatedKeys.getLong(1);
                            } else {
                                throw new SQLException("Gagal mendapatkan ID barang baru.");
                            }
                        }
                    }
                }
            }
        }

        // Periksa apakah transaksi sudah ada
        String sqlCheckTransaksi = "SELECT COUNT(*) FROM transaksi_beli WHERE tgl_beli = ? AND id_supplier = ? AND id_barang = ?";
        try (PreparedStatement pstCheckTransaksi = conn.prepareStatement(sqlCheckTransaksi)) {
            pstCheckTransaksi.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            pstCheckTransaksi.setString(2, idSupplier);
            pstCheckTransaksi.setLong(3, idBarang);

            try (ResultSet rs = pstCheckTransaksi.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this, "Transaksi sudah ada.");
                    return;
                }
            }
        }

        // Tambahkan transaksi baru
        String sqlInsertTransaksi = "INSERT INTO transaksi_beli (tgl_beli, id_supplier, id_barang, nama_barang, jenis_barang, harga_beli, jumlah, total_beli) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstTransaksi = conn.prepareStatement(sqlInsertTransaksi)) {
            pstTransaksi.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            pstTransaksi.setString(2, idSupplier);
            pstTransaksi.setLong(3, idBarang);
            pstTransaksi.setString(4, namaBarang);
            pstTransaksi.setString(5, jenisBarang);
            pstTransaksi.setInt(6, hargaBeli);
            pstTransaksi.setInt(7, jumlah);
            pstTransaksi.setInt(8, hargaBeli * jumlah);
            pstTransaksi.executeUpdate();
        }

        // Bersihkan form dan refresh data
        JOptionPane.showMessageDialog(this, "Transaksi berhasil ditambahkan.");
        f.setText("");
        gg.setText("");
        c.setText("");
        harga.setText("");
        hargajual.setText(""); // <-- Kosongkan harga jual juga
        tgl.setDate(null);
        j.setText("");
        getData();
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Format jumlah, harga beli, atau harga jual tidak valid.");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage());
    }
    }//GEN-LAST:event_tambahbtnActionPerformed
 private void searchData(String keyword) {
    DefaultTableModel model = (DefaultTableModel) tabel.getModel();
    model.setRowCount(0);  // Reset data dalam tabel

    PreparedStatement st = null;
    ResultSet rs = null;

    try {
        // Query pencarian berdasarkan No_Faktur atau Nama Barang
        String sql = "SELECT tb.No_Faktur, tb.tgl_beli, b.harga_beli, tb.id_supplier, "
                   + "b.nama_barang, b.stok, tb.jumlah, tb.id_barang "
                   + "FROM transaksi_beli tb "
                   + "JOIN barang b ON tb.id_barang = b.id_barang "
                   + "WHERE tb.No_Faktur LIKE ? OR b.nama_barang LIKE ?";

        st = conn.prepareStatement(sql);
        st.setString(1, "%" + keyword + "%");  // Mencari No_Faktur yang mengandung keyword
        st.setString(2, "%" + keyword + "%");  // Mencari Nama Barang yang mengandung keyword

        rs = st.executeQuery();

        // Loop untuk memasukkan hasil query ke dalam tabel
        while (rs.next()) {
            String No_Faktur = rs.getString("No_Faktur");
            Date tgl_beli = rs.getDate("tgl_beli");
            int harga_beli = rs.getInt("harga_beli");
            String id_supplier = rs.getString("id_supplier");
            String nama_barang = rs.getString("nama_barang");  // Nama barang
            int stok = rs.getInt("stok");  // Stok barang
            int jumlah = rs.getInt("jumlah");
            String id_barang = rs.getString("id_barang");  // id_barang yang baru ditambahkan

            // Menambahkan data ke dalam tabel
            Object[] rowData = {No_Faktur, tgl_beli, harga_beli, id_supplier, nama_barang, stok, jumlah, id_barang};
            model.addRow(rowData);
        }

        rs.close();
        st.close();

    } catch (SQLException e) {
        Logger.getLogger(PEMBELIAN.class.getName()).log(Level.SEVERE, "SQL Error while searching data", e);
    } catch (Exception e) {
        Logger.getLogger(PEMBELIAN.class.getName()).log(Level.SEVERE, null, e);
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(PEMBELIAN.class.getName()).log(Level.SEVERE, "Error closing resources", e);
        }
    }
}
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
            java.util.logging.Logger.getLogger(PEMBELIAN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PEMBELIAN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PEMBELIAN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PEMBELIAN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PEMBELIAN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CARI;
    private javax.swing.JTextField a;
    private javax.swing.JTextField c;
    private javax.swing.JTextField f;
    private javax.swing.JTextField gg;
    private javax.swing.JTextField harga;
    private javax.swing.JTextField hargajual;
    private javax.swing.JTextField j;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox<String> supplier;
    private javax.swing.JTable tabel;
    private javax.swing.JButton tambahbtn;
    private com.toedter.calendar.JDateChooser tgl;
    // End of variables declaration//GEN-END:variables

   

    private void resetForm() {
      a.setText("");  // Clear noFaktur field
f.setText("");  // Clear namaBarang field
gg.setText(""); // Clear jenisBarang field
c.setText("");  // Clear jumlah field
harga.setText(""); // Clear harga field
j.setText(""); // Clear total field
tgl.setDate(null); // Clear date field (if needed)
    }
}
