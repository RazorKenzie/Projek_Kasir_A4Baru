/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package LAPORAN;

/**
 *
 * @author devipermata
 */
import ACCOUNT.ACCOUNT;
import HALUT.HALAMANUTAMA;
import KONEKSI.konek;
import PEMBELIAN.PEMBELIAN;
import PENJUALAN.PENJUALAN;
import STOK.STOK;
import SUPPLIER.SUPPLIER;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;

public class PEMASUKAN extends javax.swing.JFrame {

    private Connection conn;

    {
        initComponents();
        conn = konek.getConnection();

    }

 private void tampilkanLaporan() {
    DefaultTableModel model = (DefaultTableModel) TABEL.getModel();
    model.setRowCount(0); 

    try {
        java.util.Date startDate = start.getDate();
        java.util.Date endDate = end.getDate();
        
        int selectedMonth = bln.getMonth() + 1; 
        int selectedYear = thn.getYear();

        String searchKeyword = CARI.getText().trim();

        String sql;
        PreparedStatement st;

        if (startDate != null && endDate != null) {
            java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

            sql = """
                SELECT tj.id_transaksi_jual, tj.tgl_jual, tj.id_barang, 
                       b.nama_barang, b.stok, b.harga_jual, tj.jumlah, 
                       p.id_penjual, b.jenis_barang
                FROM transaksi_jual tj
                INNER JOIN barang b ON tj.id_barang = b.id_barang
                INNER JOIN penjual p ON b.id_penjual = p.id_penjual
                WHERE tj.tgl_jual BETWEEN ? AND ?
                AND b.nama_barang LIKE ?
                ORDER BY tj.id_transaksi_jual ASC, tj.tgl_jual
            """;
            st = conn.prepareStatement(sql);
            st.setDate(1, sqlStartDate);
            st.setDate(2, sqlEndDate);
            st.setString(3, "%" + searchKeyword + "%");
        } else {
            sql = """
                SELECT tj.id_transaksi_jual, tj.tgl_jual, tj.id_barang, 
                       b.nama_barang, b.stok, b.harga_jual, tj.jumlah, 
                       p.id_penjual, b.jenis_barang
                FROM transaksi_jual tj
                INNER JOIN barang b ON tj.id_barang = b.id_barang
                INNER JOIN penjual p ON b.id_penjual = p.id_penjual
                WHERE MONTH(tj.tgl_jual) = ? AND YEAR(tj.tgl_jual) = ?
                AND b.nama_barang LIKE ?
                ORDER BY tj.id_transaksi_jual ASC, tj.tgl_jual
            """;
            st = conn.prepareStatement(sql);
            st.setInt(1, selectedMonth);
            st.setInt(2, selectedYear);
            st.setString(3, "%" + searchKeyword + "%");
        }

        ResultSet rs = st.executeQuery();
        BigDecimal totalPemasukan = BigDecimal.ZERO;
        DecimalFormat df = new DecimalFormat("#,###.##");

        Object[] columnNames = {"ID Transaksi", "Tanggal Jual", "ID Barang", "Nama Barang", "Jenis Barang", 
                                "Stok", "Harga Jual", "Jumlah", "Total", "ID Penjual"};
        model.setColumnIdentifiers(columnNames);

        while (rs.next()) {
            String idTransaksi = rs.getString("id_transaksi_jual");
            String tanggalJual = rs.getString("tgl_jual");
            String idBarang = rs.getString("id_barang");
            String namaBarang = rs.getString("nama_barang");
            String jenisBarang = rs.getString("jenis_barang");
            BigDecimal hargaJual = rs.getBigDecimal("harga_jual");
            int stok = rs.getInt("stok");
            int jumlah = rs.getInt("jumlah");
            String idPenjual = rs.getString("id_penjual");

            BigDecimal totalJual = hargaJual.multiply(BigDecimal.valueOf(jumlah));
            totalPemasukan = totalPemasukan.add(totalJual);

            Object[] rowData = {
                idTransaksi, tanggalJual, idBarang, namaBarang, jenisBarang, stok, 
                df.format(hargaJual), jumlah, df.format(totalJual), idPenjual
            };
            model.addRow(rowData);
        }

        totallabel.setText("Total Pemasukan: Rp " + df.format(totalPemasukan));
        rs.close();
        st.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error saat memuat data: " + e.getMessage());
        e.printStackTrace();
    }
}






    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PEMASUKAN().setVisible(true);
            }
        });
    }

    private javax.swing.JButton btntampil;
    private javax.swing.JButton btnkembali;
    private javax.swing.JTextField endDateField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable transaksiTable;
    private javax.swing.JTextField startDateField;


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        TABEL = new javax.swing.JTable();
        end = new com.toedter.calendar.JDateChooser();
        CARI = new javax.swing.JTextField();
        start = new com.toedter.calendar.JDateChooser();
        thn = new com.toedter.calendar.JYearChooser();
        bln = new com.toedter.calendar.JMonthChooser();
        totallabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TABEL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13"
            }
        ));
        jScrollPane2.setViewportView(TABEL);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 1020, 310));
        getContentPane().add(end, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 150, -1));

        CARI.setBackground(new java.awt.Color(0,0,0,0));
        CARI.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        CARI.setBorder(null);
        getContentPane().add(CARI, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 50, 180, 20));
        getContentPane().add(start, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 150, -1));
        getContentPane().add(thn, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 120, -1));
        getContentPane().add(bln, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 140, 30));

        totallabel.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        getContentPane().add(totallabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 260, 360, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/19g.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 123, 90, 50));

        jButton1.setBackground(new java.awt.Color(51, 51, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("TAMPILKAN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 660, 190, 40));

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 90, 40));

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 183, 90, 50));

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 90, 50));

        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, -1, 50));

        jButton8.setText("jButton8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 680, -1, -1));

        jButton9.setText("jButton9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 720, -1, 30));

        jButton10.setText("jButton10");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 720, 80, 60));

        jButton7.setText("jButton7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 43, -1, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tampilkanLaporan();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new HALAMANUTAMA().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       PENJUALAN pjln = new PENJUALAN();
       pjln.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        STOK stk = new STOK();
        stk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       PEMBELIAN pb = new PEMBELIAN();
        pb.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        SUPPLIER S = new SUPPLIER();
        S.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       ACCOUNT acc = new ACCOUNT();
       acc.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
    dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        LAPORAN L = new LAPORAN();
        L.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        tampilkanLaporan();
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CARI;
    private javax.swing.JTable TABEL;
    private com.toedter.calendar.JMonthChooser bln;
    private com.toedter.calendar.JDateChooser end;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser start;
    private com.toedter.calendar.JYearChooser thn;
    private javax.swing.JLabel totallabel;
    // End of variables declaration//GEN-END:variables
}
