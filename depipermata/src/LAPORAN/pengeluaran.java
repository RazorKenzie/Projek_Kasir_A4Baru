/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package LAPORAN;

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
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class pengeluaran extends javax.swing.JFrame {
    private Connection conn;
    
    public pengeluaran() {
        initComponents();
        conn = konek.getConnection();
    
    }

 private void tampilkanLaporan() {
    try {
        DefaultTableModel model = new DefaultTableModel();
        tabel.setModel(model);
        model.setRowCount(0);

        String[] columnNames = {
            "No. Faktur", "Tanggal Beli", "ID Supplier", "Nama Supplier", 
            "ID Barang", "Jenis Barang", "Nama Barang", "Stok", 
            "Harga", "Jumlah", "Total Pembelian", "ID Penjual"
        };
        model.setColumnIdentifiers(columnNames);

        String sql = "";
        PreparedStatement st;
        ResultSet rs;

        java.util.Date startDate = start.getDate();
        java.util.Date endDate = end.getDate();
        int selectedMonth = bln.getMonth() + 1; 
        int selectedYear = thn.getYear();
        String searchKeyword = CARI.getText().trim(); 

        if (startDate != null && endDate != null) {
            sql = """
                SELECT tb.No_Faktur, tb.tgl_beli, tb.id_supplier, s.nama_supplier,
                    tb.id_barang, b.jenis_barang, b.nama_barang, b.stok, b.harga_beli, 
                    tb.jumlah, (tb.harga_beli * tb.jumlah) AS total_beli, 
                    p.id_penjual
                FROM transaksi_beli tb
                INNER JOIN barang b ON tb.id_barang = b.id_barang
                INNER JOIN supplier s ON tb.id_supplier = s.id_supplier
                INNER JOIN penjual p ON b.id_penjual = p.id_penjual
                WHERE tb.tgl_beli BETWEEN ? AND ?
                AND b.nama_barang LIKE ?
                ORDER BY tb.No_Faktur
            """;

            st = conn.prepareStatement(sql);
            st.setDate(1, new java.sql.Date(startDate.getTime()));
            st.setDate(2, new java.sql.Date(endDate.getTime()));
            st.setString(3, "%" + searchKeyword + "%"); // Pencarian berdasarkan nama barang

        } else {
            sql = """
                SELECT tb.No_Faktur, tb.tgl_beli, tb.id_supplier, s.nama_supplier,
                    tb.id_barang, b.jenis_barang, b.nama_barang, b.stok, b.harga_beli, 
                    tb.jumlah, (tb.harga_beli * tb.jumlah) AS total_beli, 
                    p.id_penjual
                FROM transaksi_beli tb
                INNER JOIN barang b ON tb.id_barang = b.id_barang
                INNER JOIN supplier s ON tb.id_supplier = s.id_supplier
                INNER JOIN penjual p ON b.id_penjual = p.id_penjual
                WHERE MONTH(tb.tgl_beli) = ? AND YEAR(tb.tgl_beli) = ?
                AND b.nama_barang LIKE ?
                ORDER BY tb.No_Faktur
            """;

            st = conn.prepareStatement(sql);
            st.setInt(1, selectedMonth);
            st.setInt(2, selectedYear);
            st.setString(3, "%" + searchKeyword + "%"); 
        }

        rs = st.executeQuery();

        BigDecimal totalPembelian = BigDecimal.ZERO;
        DecimalFormat df = new DecimalFormat("#,###.##");

        while (rs.next()) {
            Object[] rowData = {
                rs.getString("No_Faktur"),
                rs.getDate("tgl_beli"),
                rs.getString("id_supplier"),
                rs.getString("nama_supplier"),
                rs.getString("id_barang"),
                rs.getString("jenis_barang"), 
                rs.getString("nama_barang"),
                rs.getInt("stok"),
                df.format(rs.getBigDecimal("harga_beli")),  // Harga
                rs.getInt("jumlah"),
                df.format(rs.getBigDecimal("total_beli")),
                rs.getString("id_penjual") // ID Penjual
            };
            model.addRow(rowData);

            totalPembelian = totalPembelian.add(rs.getBigDecimal("total_beli"));
        }

        total.setText("Total Pembelian: Rp " + df.format(totalPembelian));

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        Logger.getLogger(pengeluaran.class.getName()).log(Level.SEVERE, null, ex);
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
        tabel = new javax.swing.JTable();
        start = new com.toedter.calendar.JDateChooser();
        thn = new com.toedter.calendar.JYearChooser();
        end = new com.toedter.calendar.JDateChooser();
        total = new javax.swing.JLabel();
        bln = new com.toedter.calendar.JMonthChooser();
        CARI = new javax.swing.JTextField();
        TABEL = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12"
            }
        ));
        jScrollPane1.setViewportView(tabel);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 1020, 310));

        start.setBackground(new java.awt.Color(255, 255, 255));
        start.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(start, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 150, -1));
        getContentPane().add(thn, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 240, 100, -1));

        end.setBackground(new java.awt.Color(0, 51, 255));
        end.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(end, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 240, 150, -1));
        getContentPane().add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 250, 210, 20));
        getContentPane().add(bln, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, 180, 30));

        CARI.setBackground(new java.awt.Color(0,0,0,0));
        CARI.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        CARI.setBorder(null);
        getContentPane().add(CARI, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 50, 180, -1));

        TABEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGE/18d.jpg"))); // NOI18N
        TABEL.setText("jLabel1");
        getContentPane().add(TABEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, -1));

        jButton10.setText("TAMPILKAN");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 660, 180, 40));

        jButton11.setText("jButton11");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 720, 70, 70));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 123, 100, 40));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 183, 100, 50));

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 250, 100, 40));

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 303, 100, 50));

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 430, 90, 50));

        jButton8.setText("jButton8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 670, 80, 40));

        jButton9.setText("jButton9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 720, -1, 30));

        jButton12.setText("jButton12");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 90, 60));

        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 50, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        tampilkanLaporan ();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        PEMBELIAN pb = new PEMBELIAN();
        pb.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        STOK stk = new STOK();
        stk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new HALAMANUTAMA().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        PENJUALAN pjln = new PENJUALAN();
       pjln.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        SUPPLIER s = new SUPPLIER();
        s.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
         ACCOUNT acc = new ACCOUNT();
       acc.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
       LAPORAN L = new LAPORAN();
       L.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
       LAPORAN L = new LAPORAN();
       L.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       tampilkanLaporan();
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(pengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pengeluaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pengeluaran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CARI;
    private javax.swing.JLabel TABEL;
    private com.toedter.calendar.JMonthChooser bln;
    private com.toedter.calendar.JDateChooser end;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser start;
    private javax.swing.JTable tabel;
    private com.toedter.calendar.JYearChooser thn;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
