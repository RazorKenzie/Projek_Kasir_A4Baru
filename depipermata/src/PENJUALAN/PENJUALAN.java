
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PENJUALAN;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.io.File;
import java.awt.Desktop;
import java.util.List;
import java.util.Date;
import ACCOUNT.ACCOUNT;
import HALUT.HALAMANUTAMA;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import KONEKSI.konek;
import static KONEKSI.konek.getConnection;
import LAPORAN.LAPORAN;
import PEMBELIAN.PEMBELIAN;
import STOK.STOK;
import SUPPLIER.SUPPLIER;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Types;
import com.fazecast.jSerialComm.SerialPort;
import java.io.OutputStream;
import com.itextpdf.text.Document;

import com.fazecast.jSerialComm.SerialPort;
import javax.swing.*;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.awt.Desktop;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.Date;
import java.io.File;
import java.awt.Desktop;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.*;
import java.io.FileOutputStream;
import java.io.File;
import java.awt.Desktop;
import java.util.List;
import java.util.Date;



/**
 *
 * @author devipermata
 */
public class PENJUALAN extends javax.swing.JFrame {
 private Connection conn;
  private JTextField rfidInput;
    private boolean isMember;
    private double discountRate;
    private JTextField totalHargaField;
    private DefaultTableModel model;
    private String idMember;
    private boolean isProcessingRFID = false;
    private List<ItemTransaksi> keranjang = new ArrayList<>();
    private StringBuilder barcode = new StringBuilder();


   public PENJUALAN() {
    initComponents();
    conn = konek.getConnection();

    model = new DefaultTableModel(); // Inisialisasi awal model
    tabelpenjualan.setModel(model);  // Hubungkan model ke tabel

    getData(); // Setelah model diset
    id();


    }
    private void getData() {
    // Definisi nama kolom
    String[] columnNames = {
        "ID Transaksi", 
        "Tanggal Jual",    
        "ID Barang",       
        "Nama Barang",
        "Jenis Barang",
        "Nama Member",
        "Harga Jual (Rp)", 
        "Jumlah",          
        "Total Penjualan (Rp)"
    };

    // Set model tabel
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    tabelpenjualan.setModel(model);
    model.setRowCount(0);

    // Objek JDBC
    PreparedStatement st = null;
    ResultSet rs = null;

    try {
        // Query dengan INNER JOIN ke tabel barang & member
        String sql = "SELECT tj.id_transaksi_jual, tj.harga_jual, tj.jumlah, "
                   + "tj.tgl_jual, tj.id_barang, b.jenis_barang, b.nama_barang, "
                   + "tj.idMember, m.nama_member "
                   + "FROM transaksi_jual tj "
                   + "INNER JOIN barang b ON tj.id_barang = b.id_barang "
                   + "INNER JOIN member m ON tj.idMember = m.idMember"; // JOIN ke tabel member

        st = conn.prepareStatement(sql);
        rs = st.executeQuery();
        // Format angka dalam Rupiah
//        hgcytdxyjcjh
        NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        // Perulangan untuk membaca hasil query
        while (rs.next()) {
            String id_transaksi_jual = rs.getString("id_transaksi_jual");
            int harga_jual = rs.getInt("harga_jual");
            int jumlah = rs.getInt("jumlah");
            double total_jual = harga_jual * jumlah;
            Date tgl_jual = rs.getDate("tgl_jual");
            String id_barang = rs.getString("id_barang");
            String nama_barang = rs.getString("nama_barang");
            String jenis_barang = rs.getString("jenis_barang");
            String nama_member = rs.getString("nama_member"); // Ambil Nama Member

            // Format harga & total penjualan dalam Rupiah
            String hargaJualRp = rupiahFormat.format(harga_jual);
            String totalJualRp = rupiahFormat.format(total_jual);

            // Tambahkan data ke dalam tabel
            Object[] rowData = {
                id_transaksi_jual, 
                tgl_jual, 
                id_barang, 
                nama_barang, 
                jenis_barang, 
                nama_member,  // Tambahkan Nama Member
                hargaJualRp, 
                jumlah, 
                totalJualRp
            };
            model.addRow(rowData);
        }

        // Jika tabel kosong, tampilkan pesan
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tidak ada data transaksi ditemukan.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (SQLException e) {
        Logger.getLogger(PENJUALAN.class.getName()).log(Level.SEVERE, "SQL Error saat mengambil data", e);
    } catch (Exception e) {
        Logger.getLogger(PENJUALAN.class.getName()).log(Level.SEVERE, null, e);
    } finally {
        // Pastikan resources tertutup
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
        } catch (SQLException e) {
            Logger.getLogger(PENJUALAN.class.getName()).log(Level.SEVERE, "Kesalahan saat menutup koneksi", e);
        }
    }
}

    public PENJUALAN(Connection conn, JTextField totalHargaField, JTable tabel, JTextField rfidInput) {
    this.conn = conn;
    this.totalHargaField = totalHargaField;
    this.tabelpenjualan = tabel;
    this.rfidInput = rfidInput;
    DefaultTableModel model = (DefaultTableModel) tabelpenjualan.getModel();
    model.setRowCount(0); 
   

    // Tambahkan listener untuk mendeteksi input otomatis dari RFID
    rfidInput.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            processRFID();
        }
        @Override
        public void removeUpdate(DocumentEvent e) { }
        @Override
        public void changedUpdate(DocumentEvent e) { }
    });
    }
private void processRFID() {
    if (isProcessingRFID) return;
    isProcessingRFID = true;

    idMember = rfidInput.getText().trim();
    if (!idMember.isEmpty()) {
        checkMember(idMember);
    }
    
    rfidInput.setText(""); // Kosongkan input setelah pemrosesan
    isProcessingRFID = false;
}

private void id() {
    try (PreparedStatement st = conn.prepareStatement("SELECT DISTINCT id_barang, nama_barang FROM barang");
         ResultSet rs = st.executeQuery()) {

        namabarang.removeAllItems();

        while (rs.next()) {
            String id_barang = rs.getString("id_barang");
            String nama_barang = rs.getString("nama_barang");
            String combined = id_barang + " - " + nama_barang;
            namabarang.addItem(combined); // Pastikan JComboBox sudah diinisialisasi
        }
    } catch (SQLException ex) {
        Logger.getLogger(PENJUALAN.class.getName()).log(Level.SEVERE, "Error executing query", ex);
    }
}

    

public class PopupScanTransaksi extends JDialog {
    private JTextField scanBarangField, scanMemberField, jumlahField, uangField, kembalianField;
    private JLabel totalLabel;
    private JDateChooser tanggalChooser;
    private JButton tambahBtn, cetakNotaBtn, simpanBtn, batalBtn;
    private JTable barangTable;
    private DefaultTableModel tableModel;
    private Connection conn;
    private boolean memberScanned = false;
    private String idMember = "";
    private double diskonMember = 0.0;
    private String idTransaksiJual;
    private List<ItemTransaksi> daftarPesanan;

    public PopupScanTransaksi(JFrame parent, Connection conn, JTable tabelpenjualan) {
        super(parent, "Scan Transaksi", true);
        this.conn = conn;
        this.idTransaksiJual = generateIdTransaksiJual();
        this.daftarPesanan = new ArrayList<>();

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(173, 216, 230));

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBackground(new Color(224, 255, 255));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        scanBarangField = new JTextField();
        scanMemberField = new JTextField();
        scanMemberField.addActionListener(e -> scanMember());

        jumlahField = new JTextField();
        tanggalChooser = new JDateChooser();
        tanggalChooser.setDateFormatString("yyyy-MM-dd");

        inputPanel.add(new JLabel("📦 Scan Barcode Barang:", JLabel.RIGHT));
        inputPanel.add(scanBarangField);
        inputPanel.add(new JLabel("🎫 Scan Kartu Member:", JLabel.RIGHT));
        inputPanel.add(scanMemberField);
        inputPanel.add(new JLabel("🔢 Jumlah Barang:", JLabel.RIGHT));
        inputPanel.add(jumlahField);
        inputPanel.add(new JLabel("📅 Tanggal Transaksi:", JLabel.RIGHT));
        inputPanel.add(tanggalChooser);

        tambahBtn = new JButton("Tambah Barang");
        cetakNotaBtn = new JButton("Cetak Nota");
        simpanBtn = new JButton("Simpan");
        batalBtn = new JButton("Batal");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(224, 255, 255));
        buttonPanel.add(tambahBtn);
        buttonPanel.add(cetakNotaBtn);
        buttonPanel.add(simpanBtn);
        buttonPanel.add(batalBtn);

        // Table
        tableModel = new DefaultTableModel(new String[]{
                "ID Transaksi", "ID Barang", "Nama Barang", "Jenis", "Jumlah", "Harga", "Total", "Tanggal", "ID Member", "Diskon"}, 0);
        barangTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(barangTable);

        // Menu klik kanan untuk hapus
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem hapusItem = new JMenuItem("Hapus Barang Ini");
        popupMenu.add(hapusItem);
        barangTable.setComponentPopupMenu(popupMenu);
        barangTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                Point point = e.getPoint();
                int row = barangTable.rowAtPoint(point);
                barangTable.setRowSelectionInterval(row, row);
            }
        });
        hapusItem.addActionListener(e -> hapusItemTerpilih());

        // Panel bawah untuk total dan uang
        JPanel bottomPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        totalLabel = new JLabel("Total Bayar: Rp 0");
        uangField = new JTextField();
        kembalianField = new JTextField();
        kembalianField.setEditable(false);

        bottomPanel.add(new JLabel("💵 Bayar:"));
        bottomPanel.add(uangField);
        bottomPanel.add(new JLabel("🔁 Kembali:"));
        bottomPanel.add(kembalianField);
        bottomPanel.add(new JLabel("💰 Total Pembayaran:"));
        bottomPanel.add(totalLabel);

        uangField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                hitungKembalian();
            }
        });

        // Tambah ke layout utama
        add(inputPanel, BorderLayout.NORTH);
        add(tableScroll, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.EAST);

        tambahBtn.addActionListener(e -> tambahPesanan());
        cetakNotaBtn.addActionListener(e -> tampilkanNotaPDF());
        simpanBtn.addActionListener(e -> simpanTransaksi());
        batalBtn.addActionListener(e -> dispose());

        setSize(1000, 500);
        setLocationRelativeTo(parent);
    }

    private String generateIdTransaksiJual() {
        return "TRX" + new SimpleDateFormat("HHss").format(new Date());
    }

    private void scanMember() {
        if (memberScanned) return;

        idMember = scanMemberField.getText().trim();
        if (idMember.isEmpty()) return;

        try {
            String sqlMember = "SELECT idMember, diskon FROM member WHERE idMember = ?";
            PreparedStatement pst = conn.prepareStatement(sqlMember);
            pst.setString(1, idMember);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                memberScanned = true;
                diskonMember = rs.getDouble("diskon");
                scanMemberField.setEditable(false);
                scanMemberField.setBackground(new Color(200, 255, 200));
                JOptionPane.showMessageDialog(this, "Member ditemukan! ID: " + idMember + " Diskon: " + diskonMember + "%", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                scanMemberField.setText("");
            }
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void tambahPesanan() {
        String barcode = scanBarangField.getText().trim();
        String jumlahStr = jumlahField.getText().trim();
        if (barcode.isEmpty() || jumlahStr.isEmpty()) return;

        try {
            int jumlah = Integer.parseInt(jumlahStr);
            String sql = "SELECT id_barang, nama_barang, jenis_barang, harga_jual FROM barang WHERE barcode = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, barcode);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String idBarang = rs.getString("id_barang");
                String namaBarang = rs.getString("nama_barang");
                String jenisBarang = rs.getString("jenis_barang");
                double harga = rs.getDouble("harga_jual");
                double total = harga * jumlah * (1 - diskonMember / 100);

                ItemTransaksi item = new ItemTransaksi(idBarang, namaBarang, jumlah, harga);
                daftarPesanan.add(item);

                tableModel.addRow(new Object[]{
                        idTransaksiJual, idBarang, namaBarang, jenisBarang, jumlah, harga, total,
                        new java.sql.Date(System.currentTimeMillis()), idMember, diskonMember
                });

                scanBarangField.setText("");
                jumlahField.setText("");
                updateTotal();
            }

            rs.close();
            pst.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void hapusItemTerpilih() {
        int row = barangTable.getSelectedRow();
        if (row != -1) {
            daftarPesanan.remove(row);
            tableModel.removeRow(row);
            updateTotal();
        }
    }

    private void updateTotal() {
        double total = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            total += Double.parseDouble(tableModel.getValueAt(i, 6).toString());
        }
        totalLabel.setText("Total Bayar: Rp " + total);
        hitungKembalian();
    }

    private void hitungKembalian() {
        try {
            double total = 0;
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                total += Double.parseDouble(tableModel.getValueAt(i, 6).toString());
            }

            double uang = Double.parseDouble(uangField.getText());
            double kembali = uang - total;
            kembalianField.setText("Rp " + kembali);
        } catch (Exception e) {
            kembalianField.setText("Rp 0");
        }
    }

    private void simpanTransaksi() {
        try {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String sql = "INSERT INTO transaksi_jual (id_transaksi_jual, id_barang, jumlah, harga_jual, total_jual, tgl_jual, idMember) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(sql);

                pst.setString(1, idTransaksiJual);
                pst.setString(2, tableModel.getValueAt(i, 1).toString());
                pst.setInt(3, Integer.parseInt(tableModel.getValueAt(i, 4).toString()));
                pst.setDouble(4, Double.parseDouble(tableModel.getValueAt(i, 5).toString()));
                pst.setDouble(5, Double.parseDouble(tableModel.getValueAt(i, 6).toString()));
                pst.setDate(6, new java.sql.Date(System.currentTimeMillis()));

                if (idMember == null || idMember.trim().isEmpty()) {
                    pst.setNull(7, Types.VARCHAR);
                } else {
                    pst.setString(7, idMember);
                }

                pst.executeUpdate();
                pst.close();
            }

            JOptionPane.showMessageDialog(this, "Transaksi Berhasil Disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void tampilkanNotaPDF() {
        // Generate the PDF Nota
        new PopupLihatPDF(null, daftarPesanan);
    }
}


public class PopupLihatPDF extends JDialog {
    private JTextField uangField;
    private JTextField kembalianField;
    private JLabel totalLabel;
    private double totalPembayaran;

    public PopupLihatPDF(JFrame parent, List<ItemTransaksi> pesanan) {
        super(parent, "Lihat Nota PDF", true);

        // Panel bawah untuk total dan uang
        JPanel bottomPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        totalLabel = new JLabel("Total Bayar: Rp 0");
        uangField = new JTextField();
        kembalianField = new JTextField();
        kembalianField.setEditable(false);

        bottomPanel.add(new JLabel("💵 Bayar:"));
        bottomPanel.add(uangField);
        bottomPanel.add(new JLabel("🔁 Kembali:"));
        bottomPanel.add(kembalianField);
        bottomPanel.add(new JLabel("💰 Total Pembayaran:"));
        bottomPanel.add(totalLabel);

        uangField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                hitungKembalian();
            }
        });

        // Menghitung total pembayaran
        totalPembayaran = 0;
        for (ItemTransaksi item : pesanan) {
            totalPembayaran += item.getJumlah() * item.getHarga();
        }
        totalLabel.setText("Total Bayar: Rp " + String.format("%,.0f", totalPembayaran));

        try {
            // Path file PDF
            String filePath = "/Users/devipermata/Desktop/nota_transaksi.pdf";
            Document doc = new Document(PageSize.A5);
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(filePath));
            doc.open();

            // Logo toko
            Image logo = Image.getInstance("/Users/devipermata/Desktop/logo.png");
            logo.scaleToFit(60, 60);
            logo.setAlignment(Image.ALIGN_LEFT);
            doc.add(logo);

            // Info toko
            Paragraph toko = new Paragraph("MBP MART\nBUDI PRASETYO.\nDusun Tukdadap RT. 12/03 Desa Sukoharjo, Kec. Wilangan Kab. Nganjuk\nHP: 085709456044", new Font(Font.FontFamily.TIMES_ROMAN, 10));
            toko.setAlignment(Element.ALIGN_CENTER);
            doc.add(toko);

            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("NOTA PEMBAYARAN", new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD)));
            doc.add(new Paragraph("Tanggal: " + new Date().toString(), new Font(Font.FontFamily.TIMES_ROMAN, 10)));
            doc.add(new Paragraph(" "));

            // Tabel barang
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            table.setWidths(new float[]{1f, 3f, 2f, 2f});

            addTableHeader(table, "Jumlah", "Nama Barang", "Harga", "Total");

            for (ItemTransaksi item : pesanan) {
                double subTotal = item.getJumlah() * item.getHarga();
                table.addCell(String.valueOf(item.getJumlah()));
                table.addCell(item.getNamaBarang());
                table.addCell("Rp " + String.format("%,.0f", item.getHarga()));
                table.addCell("Rp " + String.format("%,.0f", subTotal));
            }

            doc.add(table);

            // Ringkasan pembayaran (Menambahkan Bayar dan Kembalian ke PDF)
            PdfPTable ringkasan = new PdfPTable(2);
            ringkasan.setWidthPercentage(70);
            ringkasan.setHorizontalAlignment(Element.ALIGN_RIGHT);
            ringkasan.setSpacingBefore(10f);

            ringkasan.addCell(getCell("Total Pembayaran", PdfPCell.ALIGN_LEFT));
            ringkasan.addCell(getCell("Rp " + String.format("%,.0f", totalPembayaran), PdfPCell.ALIGN_RIGHT));

            // Menambahkan jumlah bayar dan kembalian di nota
            double uangBayar = 0;
            double kembalian = 0;
            try {
                uangBayar = Double.parseDouble(uangField.getText().trim());
                kembalian = uangBayar - totalPembayaran;
            } catch (NumberFormatException e) {
                // Jika input kosong atau tidak valid
                uangBayar = 0;
                kembalian = 0;
            }

            ringkasan.addCell(getCell("Jumlah Bayar", PdfPCell.ALIGN_LEFT));
            ringkasan.addCell(getCell("Rp " + String.format("%,.0f", uangBayar), PdfPCell.ALIGN_RIGHT));

            ringkasan.addCell(getCell("Kembalian", PdfPCell.ALIGN_LEFT));
            ringkasan.addCell(getCell("Rp " + String.format("%,.0f", kembalian), PdfPCell.ALIGN_RIGHT));

            doc.add(ringkasan);

            doc.add(new Paragraph("\nTerima Kasih Telah Berbelanja!", new Font(Font.FontFamily.TIMES_ROMAN, 10)));

            doc.close();
            Desktop.getDesktop().open(new File(filePath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTableHeader(PdfPTable table, String... headers) {
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
        }
    }

    private PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text, new Font(Font.FontFamily.TIMES_ROMAN, 10)));
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private void hitungKembalian() {
        try {
            double uangBayar = Double.parseDouble(uangField.getText().trim());
            double kembalian = uangBayar - totalPembayaran;
            kembalianField.setText("Rp " + String.format("%,.0f", kembalian));
        } catch (NumberFormatException e) {
            kembalianField.setText("Rp 0");
        }
    }

    private void printReceipt(List<ItemTransaksi> pesanan) {
        String portName = "/dev/tty.RPP02N";

        SerialPort comPort = SerialPort.getCommPort(portName);
        comPort.setComPortParameters(115200, 8, 1, 0);
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        if (comPort.openPort()) {
            try {
                OutputStream out = comPort.getOutputStream();
                String esc = "\u001B";
                String init = esc + "@";
                String text = init + "NOTA PENJUALAN\nTanggal: " + new Date().toString() + "\n\n";

                out.write(text.getBytes("UTF-8"));

                double total = 0;
                for (ItemTransaksi item : pesanan) {
                    double sub = item.getJumlah() * item.getHarga();
                    String itemText = item.getNamaBarang() + " x" + item.getJumlah() +
                            " = Rp " + sub + "\n";
                    total += sub;
                    out.write(itemText.getBytes("UTF-8"));
                }

                out.write(("Total: Rp " + total + "\n").getBytes("UTF-8"));
                out.write("Terima kasih!\n\n".getBytes("UTF-8"));
                out.flush();
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            } finally {
                comPort.closePort();
            }
        } else {
            System.out.println("❌ Tidak bisa buka port.");
        }
    }
}

public class ItemTransaksi {
    private String idBarang;
    private String namaBarang;
    private int jumlah;
    private double harga;

    // Constructor
    public ItemTransaksi(String idBarang, String namaBarang, int jumlah, double harga) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.jumlah = jumlah;
        this.harga = harga;
    }

    // Getter dan Setter
    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    // Menghitung total harga
    public double getTotal() {
        return jumlah * harga;
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

        search = new javax.swing.JTextField();
        a = new javax.swing.JTextField();
        c = new javax.swing.JTextField();
        e = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelpenjualan = new javax.swing.JTable();
        harga = new javax.swing.JTextField();
        j = new javax.swing.JTextField();
        namabarang = new javax.swing.JComboBox<>();
        member = new javax.swing.JTextField();
        back = new javax.swing.JLabel();
        tambahbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        hapustn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        CETAK = new javax.swing.JButton();
        cari = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search.setBackground(new java.awt.Color (0,0,0,0));
        search.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        search.setBorder(null);
        getContentPane().add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 38, 200, 40));

        a.setBackground(new java.awt.Color (0,0,0,0));
        a.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        a.setBorder(null);
        a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aActionPerformed(evt);
            }
        });
        getContentPane().add(a, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 250, 20));

        c.setBackground(new java.awt.Color(0,0,0,0)
        );
        c.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        c.setBorder(null);
        c.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cActionPerformed(evt);
            }
        });
        getContentPane().add(c, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 260, 20));

        e.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        getContentPane().add(e, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 260, 30));

        tabelpenjualan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9"
            }
        ));
        tabelpenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelpenjualanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelpenjualan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 650, 540));

        harga.setBackground(new java.awt.Color(0,0,0,0));
        harga.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        harga.setBorder(null);
        harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaActionPerformed(evt);
            }
        });
        getContentPane().add(harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 458, 260, 30));

        j.setBackground(new java.awt.Color(0,0,0,0));
        j.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        j.setBorder(null);
        j.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jActionPerformed(evt);
            }
        });
        getContentPane().add(j, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 620, 260, 20));

        namabarang.setBackground(new java.awt.Color(0,0,0,0)
        );
        namabarang.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        namabarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        namabarang.setBorder(null);
        getContentPane().add(namabarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 260, 30));

        member.setBackground(new java.awt.Color(0,0,0,0));
        member.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        member.setBorder(null);
        member.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberActionPerformed(evt);
            }
        });
        getContentPane().add(member, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 260, 20));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PENJUALAN/APK DEPI.jpg"))); // NOI18N
        back.setText("jLabel2");
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        tambahbtn.setText("TAMBAH");
        tambahbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahbtnActionPerformed(evt);
            }
        });
        getContentPane().add(tambahbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 723, 160, 40));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        hapustn.setText("HAPUS");
        hapustn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapustnActionPerformed(evt);
            }
        });
        getContentPane().add(hapustn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 720, 160, 40));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, 50));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 80, 40));

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, 50));

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, 60));

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, -1, 50));

        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, -1, 50));

        jButton7.setText("jButton7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 680, -1, 30));

        jButton8.setText("jButton8");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 720, -1, -1));

        jButton12.setText("jButton12");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 730, -1, 50));

        jButton9.setText("jButton9");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 720, 170, 40));

        CETAK.setText("CETAK");
        CETAK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CETAKActionPerformed(evt);
            }
        });
        getContentPane().add(CETAK, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 720, 160, 40));

        cari.setText("jButton14");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        getContentPane().add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 30, 70, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         new HALAMANUTAMA().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       PENJUALAN pjln = new PENJUALAN();
       pjln.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
    String idTransaksi = a.getText().trim(); 
    java.util.Date tanggalJual = e.getDate(); 
    String idBarang = namabarang.getSelectedItem().toString().split(" - ")[0]; 
    String jumlahStr = c.getText().trim();  
    String hargaStr = harga.getText().trim(); 

    if (idTransaksi.isEmpty() || tanggalJual == null || idBarang.isEmpty() || 
        jumlahStr.isEmpty() || hargaStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int jumlah = 0;
    int hargaJual = 0;  
    
    try {
        jumlah = Integer.parseInt(jumlahStr); 
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Jumlah harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        hargaJual = Integer.parseInt(hargaStr.replace("Rp", "").replace(",", "").trim()); 
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    java.sql.Date sqlTanggalJual = new java.sql.Date(tanggalJual.getTime());

    double totalPenjualan = hargaJual * jumlah; 

    String totalFormatted = String.format("%.0f", totalPenjualan); 

    String sql = "UPDATE transaksi_jual SET tgl_jual = ?, id_barang = ?, harga_jual = ?, jumlah = ?, total_jual = ? "
               + "WHERE id_transaksi_jual = ?";

    try (PreparedStatement st = conn.prepareStatement(sql)) {
        st.setDate(1, sqlTanggalJual); 
        st.setString(2, idBarang); 
        st.setInt(3, hargaJual); 
        st.setInt(4, jumlah); 
        st.setDouble(5, totalPenjualan); 
        st.setString(6, idTransaksi); 

        int rowsUpdated = st.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Transaksi berhasil diperbarui!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            getData(); 
        } else {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui transaksi.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        j.setText(totalFormatted); 
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error saat memperbarui data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } 
    }//GEN-LAST:event_jButton9ActionPerformed

   

    

   
    private void tabelpenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelpenjualanMouseClicked
    int selectedRow = tabelpenjualan.getSelectedRow();

    if (selectedRow != -1) {
        // Ambil data dari kolom sesuai urutan tabel
        String idTransaksi = tabelpenjualan.getValueAt(selectedRow, 0).toString(); // ID Transaksi
        String tglTransaksi = tabelpenjualan.getValueAt(selectedRow, 1).toString(); // Tanggal
        String idBarang = tabelpenjualan.getValueAt(selectedRow, 2).toString();     // ID Barang
        String idMember = tabelpenjualan.getValueAt(selectedRow, 5).toString();     // ID Member
        // Di dalam tabelpenjualanMouseClicked
        String hargaStr = tabelpenjualan.getValueAt(selectedRow, 6).toString(); // Harga Jual
        hargaStr = hargaStr.replace("Rp", "").replace(".", "").trim();
        if (hargaStr.contains(",")) {
        hargaStr = hargaStr.split(",")[0]; // Ambil angka sebelum koma
}


        String jumlahStr = tabelpenjualan.getValueAt(selectedRow, 7).toString();    // Jumlah

        // Set ke input field
        a.setText(idTransaksi); // ID Transaksi
        e.setDate(java.sql.Date.valueOf(tglTransaksi)); // Tanggal (pakai JDateChooser)
        member.setText(idMember); // ID Member
        harga.setText(hargaStr); // Set harga ke text field        
        c.setText(jumlahStr); // Jumlah

        // Cari dan set item di ComboBox namabarang berdasarkan ID Barang
        for (int i = 0; i < namabarang.getItemCount(); i++) {
            String item = namabarang.getItemAt(i); // Misal: "2 - CLEO"
            String idItem = item.split(" - ")[0];  // Ambil ID sebelum " - "
            if (idItem.equals(idBarang)) {
                namabarang.setSelectedIndex(i); // Pilih item yang cocok
                break;
            }
        }

        try {
            // Ubah harga dan jumlah ke angka
            int hargaJual = Integer.parseInt(hargaStr.replace("Rp", "").replace(".", "").trim());
            int jumlahBarang = Integer.parseInt(jumlahStr);

            // Hitung total
            double total = hargaJual * jumlahBarang;

            // Format ke Rupiah
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
            String totalFormatted = currencyFormat.format(total);

            // Tampilkan total beli
            j.setText(totalFormatted);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Data harga atau jumlah tidak valid.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_tabelpenjualanMouseClicked
 
    

    private void cariBarang(String barcode) {
        try {
            String sql = "SELECT id_barang, nama_barang, harga FROM barang WHERE barcode = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, barcode);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                String kode = rs.getString("id_barang");
                String nama = rs.getString("nama_barang");
                double hargaJual = rs.getDouble("harga_jual");
                
                // Update ComboBox
                if (namabarang.getItemCount() == 0) {
                    namabarang.addItem(nama);
                }
                namabarang.setSelectedItem(nama);

                // Update harga
                harga.setText(String.valueOf(hargaJual));

                // Cek apakah barang sudah ada di tabel transaksi
                boolean barangAda = false;
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).equals(kode)) {
                        int jumlah = (int) model.getValueAt(i, 3) + 1;
                        model.setValueAt(jumlah, i, 3);
                        barangAda = true;
                        break;
                    }
                }

                // Jika belum ada, tambahkan ke tabel
                if (!barangAda) {
                    model.addRow(new Object[]{kode, nama, harga, 1});
                }
            } else {
                JOptionPane.showMessageDialog(this, "Barang tidak ditemukan!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       STOK stk = new STOK();
        stk.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       PEMBELIAN pb = new PEMBELIAN();
        pb.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

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

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
         ACCOUNT acc = new ACCOUNT();
       acc.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
    }//GEN-LAST:event_cariActionPerformed

    private void aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        PENJUALAN pjln = new PENJUALAN();
       pjln.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jActionPerformed

    private void cActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cActionPerformed
       String jumlahStr = c.getText().trim();  // Ambil nilai jumlah
String hargaJualStr = harga.getText().trim();  // Ambil nilai harga beli

// Validasi input
if (jumlahStr.isEmpty() || hargaJualStr.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Jumlah dan harga beli harus diisi.");
    return;
}

try {
    // Mengonversi input jumlah dan harga beli ke tipe data double
    int jumlah = Integer.parseInt(jumlahStr);
    double hargaJual = Double.parseDouble(hargaJualStr.replace("Rp ", "").replace(",", "").trim()); // Menghapus simbol "Rp" dan koma jika ada

    // Menghitung total beli (jumlah * harga beli)
    double totalJual = hargaJual * jumlah;

    // Format total jual dengan simbol Rp dan pemisah ribuan
    String totalFormatted = String.format("Rp %.2f", totalJual);

    // Menampilkan total beli di label atau textfield tertentu (misalnya totalHarga)
    j.setText(totalFormatted); // Misalnya, menampilkan total di field total

} catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(this, "Input jumlah atau harga tidak valid.");
}

    }//GEN-LAST:event_cActionPerformed

    private void hargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaActionPerformed

    private void tambahbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahbtnActionPerformed
//    String idTransaksiJual = a.getText().trim(); // Ambil ID Transaksi dari input
//    java.util.Date tanggalJual = e.getDate(); // Ambil dari JDateChooser
//    String barangTerpilih = namabarang.getSelectedItem().toString(); // Ambil dari ComboBox
//    String idBarang = barangTerpilih.split(" - ")[0]; // ID Barang
//    String namaBarang = barangTerpilih.split(" - ")[1]; // Nama Barang
//    String namaMember = member.getText().trim(); // Ambil Nama Member dari inputan
//    String hargaStr = harga.getText().trim();
//    String jumlahStr = c.getText().trim();
//    String diskonStr = "0"; // Default diskon
//
//    // Validasi input tidak boleh kosong
//    if (idTransaksiJual.isEmpty() || tanggalJual == null || idBarang.isEmpty() || namaMember.isEmpty() || hargaStr.isEmpty() || jumlahStr.isEmpty()) {
//        JOptionPane.showMessageDialog(this, "Semua kolom harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    }
//
//    // Konversi data jumlah dan harga
//    int jumlah;
//    double hargaJual;
//    try {
//        jumlah = Integer.parseInt(jumlahStr);
//        hargaJual = Double.parseDouble(hargaStr);
//    } catch (NumberFormatException ex) {
//        JOptionPane.showMessageDialog(this, "Jumlah dan Harga harus angka!", "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    }
//
//    // Validasi jumlah dan harga tidak boleh kurang dari atau sama dengan 0
//    if (jumlah <= 0 || hargaJual <= 0) {
//        JOptionPane.showMessageDialog(this, "Jumlah dan Harga harus lebih dari 0!", "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    }
//
//    // Ambil data tambahan: Jenis Barang dan ID Member dari Nama Member
//    String jenisBarang = "";
//    String idMember = ""; // ID Member akan dicari berdasarkan Nama Member
//    PreparedStatement pst = null;
//    ResultSet rs = null;
//
//    try {
//        // Ambil jenis_barang dari tabel barang
//        String sqlBarang = "SELECT jenis_barang FROM barang WHERE id_barang = ?";
//        pst = conn.prepareStatement(sqlBarang);
//        pst.setString(1, idBarang);
//        rs = pst.executeQuery();
//        if (rs.next()) {
//            jenisBarang = rs.getString("jenis_barang");
//        } else {
//            JOptionPane.showMessageDialog(this, "Barang tidak ditemukan di database!", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        rs.close();
//        pst.close();
//
//        // Ambil ID member dan diskon berdasarkan nama_member
//        String sqlMember = "SELECT idMember, diskon FROM member WHERE nama_member = ?";
//        pst = conn.prepareStatement(sqlMember);
//        pst.setString(1, namaMember);
//        rs = pst.executeQuery();
//        if (rs.next()) {
//            idMember = rs.getString("idMember"); // Dapatkan ID Member
//            diskonStr = rs.getString("diskon");  // Ambil diskon yang benar
//        } else {
//            JOptionPane.showMessageDialog(this, "Nama Member tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        rs.close();
//        pst.close();
//    } catch (SQLException ex) {
//        Logger.getLogger(PENJUALAN.class.getName()).log(Level.SEVERE, "SQL Error fetching data", ex);
//        JOptionPane.showMessageDialog(this, "Gagal mengambil data barang atau member.", "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    } finally {
//        try {
//            if (rs != null) rs.close();
//            if (pst != null) pst.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(PENJUALAN.class.getName()).log(Level.SEVERE, "Error closing resources", ex);
//        }
//    }
//
//    // Konversi diskon ke angka
//    double diskon = 0;
//    try {
//        diskon = Double.parseDouble(diskonStr);
//    } catch (NumberFormatException ex) {
//        JOptionPane.showMessageDialog(this, "Format diskon tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
//        return;
//    }
//
//    // Hitung total harga barang ini
//    double totalPenjualan = hargaJual * jumlah;
//    double totalSetelahDiskon = totalPenjualan - (totalPenjualan * (diskon / 100));
//
//    // Simpan data ke database
//    PreparedStatement st = null;
//    try {
//        String sqlInsert = "INSERT INTO transaksi_jual (id_transaksi_jual, tgl_jual, id_barang, idMember, harga_jual, jumlah, total_jual) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        st = conn.prepareStatement(sqlInsert);
//        st.setString(1, idTransaksiJual); // Gunakan ID Transaksi yang dimasukkan pengguna
//        st.setDate(2, new java.sql.Date(tanggalJual.getTime())); // Konversi ke format SQL
//        st.setString(3, idBarang);
//        st.setString(4, idMember);
//        st.setDouble(5, hargaJual);
//        st.setInt(6, jumlah);
//        st.setDouble(7, totalSetelahDiskon); // Diperbaiki dari setDouble(8, ...) ke setDouble(7, ...)
//
//        int rowsInserted = st.executeUpdate();
//        if (rowsInserted > 0) {
//            JOptionPane.showMessageDialog(this, "Transaksi berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
//
//            // Tambahkan barang ke tabel GUI (tanpa diskon)
//            DecimalFormat df = new DecimalFormat("#,###");
//            model.addRow(new Object[]{
//                idTransaksiJual, // Tampilkan ID Transaksi yang dimasukkan pengguna
//                new java.sql.Date(tanggalJual.getTime()), 
//                idBarang, 
//                namaBarang,
//                jenisBarang,
//                namaMember, // Tampilkan nama member
//                "Rp " + df.format(hargaJual),
//                jumlah,
//                "Rp " + df.format(totalSetelahDiskon) // Hanya tampilkan total akhir
//            });
//
//            // Update total beli setelah transaksi berhasil
//            updateTotal();
//            
//            // Reset input setelah transaksi
//            a.setText(""); // Kosongkan input ID Transaksi
//            e.setDate(null);
//            namabarang.setSelectedIndex(0);
//            member.setText(""); // Kosongkan input nama member
//            harga.setText("");
//            c.setText("");
//        } else {
//            JOptionPane.showMessageDialog(this, "Gagal menambahkan transaksi.", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    } catch (SQLException ex) {
//        JOptionPane.showMessageDialog(this, "Kesalahan SQL: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//    } finally {
//        try {
//            if (st != null) st.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(PENJUALAN.class.getName()).log(Level.SEVERE, "Error closing resources", ex);
//        }
//    }
    tambahbtn.addActionListener(e -> {
        JFrame parentFrame = null;
   PopupScanTransaksi popup = new PopupScanTransaksi(this, conn, tabelpenjualan); 
        popup.setVisible(true);  

});
    }//GEN-LAST:event_tambahbtnActionPerformed

    

    

    private void hapustnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapustnActionPerformed
       String idTransaksiJual = a.getText().trim();

    if (idTransaksiJual.isEmpty()) {
        JOptionPane.showMessageDialog(this, "ID Transaksi Jual harus diberikan untuk menghapus transaksi.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus transaksi ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return; 
    }

    String sql = "DELETE FROM transaksi_jual WHERE id_transaksi_jual = ?";

    try (PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setString(1, idTransaksiJual);
        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Transaksi berhasil dihapus!");
            
            getData();  

            resetForm();  
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menghapus transaksi. Periksa apakah ID ada.");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error database: " + ex.getMessage());
    }
    }//GEN-LAST:event_hapustnActionPerformed

    private void CETAKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CETAKActionPerformed
     int row = tabelpenjualan.getSelectedRow(); 

    if (row != -1) { // Pastikan ada baris yang dipilih
        // Mengambil data transaksi untuk dicetak
        String idTransaksi = (String) tabelpenjualan.getValueAt(row, 0);
        
        // Mengambil tanggal dari objek java.sql.Date dan mengonversinya ke String
        String tanggal = "";
        Object tanggalObj = tabelpenjualan.getValueAt(row, 1);
        if (tanggalObj instanceof Date) {
            Date tanggalSQL = (Date) tanggalObj;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            tanggal = sdf.format(tanggalSQL); // Mengonversi ke String
        } else {
            tanggal = tanggalObj.toString(); // Jika sudah berupa String
        }

        String namaBarang = (String) tabelpenjualan.getValueAt(row, 3);
        String hargaJual = String.valueOf(tabelpenjualan.getValueAt(row, 4));
        String jumlah = String.valueOf(tabelpenjualan.getValueAt(row, 5));
        String total = String.valueOf(tabelpenjualan.getValueAt(row, 6));

        try {
            // Menemukan port serial printer (misalnya COM1, atau sesuaikan dengan port Anda)
            SerialPort serialPort = SerialPort.getCommPort("/dev/tty.RPP02N");  // Ganti dengan port yang sesuai
            serialPort.setBaudRate(9600); // Sesuaikan dengan pengaturan printer Anda
            serialPort.openPort(); // Membuka port serial

            // Mengirim data nota ke printer
            OutputStream out = serialPort.getOutputStream();
            String nota = "-----------------------------\n"
                        + "GO BOOK - Nota Penjualan\n"
                        + "ID Transaksi: " + idTransaksi + "\n"
                        + "Tanggal: " + tanggal + "\n"
                        + "Nama Barang: " + namaBarang + "\n"
                        + "Harga: Rp" + hargaJual + "\n"
                        + "Jumlah: " + jumlah + "\n"
                        + "Total: Rp" + total + "\n"
                        + "-----------------------------\n"
                        + "Terima kasih atas pembelian Anda!\n";

            out.write(nota.getBytes()); // Mengirimkan data sebagai byte array
            out.flush(); // Memastikan data terkirim

            // Menutup koneksi dengan printer
            serialPort.closePort();

            JOptionPane.showMessageDialog(null, "Nota berhasil dicetak.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Gagal mencetak nota: " + ex.getMessage());
        }
    } else {
        // Jika tidak ada baris yang dipilih, tampilkan pesan
        JOptionPane.showMessageDialog(null, "Pilih transaksi terlebih dahulu.");
    }

    }//GEN-LAST:event_CETAKActionPerformed

    private void memberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberActionPerformed
    String rfid = member.getText().trim(); // Ambil ID dari field input

    if (rfid.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Silakan scan kartu RFID terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Panggil metode checkMember untuk mengecek di database
    checkMember(rfid);
    }//GEN-LAST:event_memberActionPerformed

// ✅ Cek RFID di database dan hitung diskon
private void checkMember(String rfid) {
    String sql = "SELECT nama_member, diskon FROM member WHERE idMember = ?";
    try (PreparedStatement st = conn.prepareStatement(sql)) {
        st.setString(1, rfid);
        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            // Jika RFID ditemukan di database sebagai member
            isMember = true;
            discountRate = rs.getDouble("diskon") / 100; // Ambil diskon dari database
            this.idMember = rfid;
            String namaMember = rs.getString("nama_member"); // Ambil nama member

            // Tampilkan Nama Member di field, bukan ID
            member.setText(namaMember);

            JOptionPane.showMessageDialog(this, "Member ditemukan! Nama: " + namaMember + "\nDiskon: " + (discountRate * 100) + "%");
        } else {
            // Jika bukan member, reset data diskon
            isMember = false;
            this.idMember = null;
            discountRate = 0;

            // Tetap tampilkan ID yang di-scan di pesan
            member.setText("Bukan Member");

            JOptionPane.showMessageDialog(this, "RFID " + rfid + " tidak terdaftar sebagai member. Tidak ada diskon.");
        }

        // **Pastikan tabel diperbarui dan total jual dihitung ulang setelah cek member**
        updateTable();
        updateTotalJualFromField();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}


private void updateTable() {
    model = (DefaultTableModel) tabelpenjualan.getModel();
    double totalJual = 0;

    for (int i = 0; i < model.getRowCount(); i++) {
        Object hargaObj = model.getValueAt(i, 5);
        Object jumlahObj = model.getValueAt(i, 3); // Sesuaikan index jumlah dengan tabel

        if (hargaObj != null && jumlahObj != null) {
            try {
                double hargaJual = Double.parseDouble(hargaObj.toString());
                int jumlah = Integer.parseInt(jumlahObj.toString());

                double totalPenjualan = hargaObj instanceof Number ? 
                    hargaObj instanceof Double ? (double) hargaObj * jumlah : (int) hargaObj * jumlah
                    : 0;

                // Terapkan diskon jika member
                double totalSetelahDiskon = isMember ? totalPenjualan * (1 - discountRate) : totalPenjualan;

                // Debugging: Cek nilai sebelum dan setelah diskon
                System.out.println("Harga Jual: " + hargaJual);
                System.out.println("Jumlah: " + jumlah);
                System.out.println("Total Sebelum Diskon: Rp " + String.format("%,.0f", totalPenjualan));
                System.out.println("Diskon: " + (isMember ? discountRate * 100 : 0) + "%");
                System.out.println("Total Setelah Diskon: Rp " + String.format("%,.0f", totalJual));

                // Update nilai total penjualan di tabel
                model.setValueAt(totalJual, i, 7);
            } catch (Exception e) {
                System.err.println("Error perhitungan diskon: " + e.getMessage());
            }
        }
    }

    // Set total jual setelah diskon
    j.setText("Rp " + String.format("%,.0f", totalJual));
}

private void updateTotalJualFromField() {
    String hargaText = harga.getText().trim();
    String jumlahText = c.getText().trim();
    
    // Pastikan harga dan jumlah tidak kosong
    if (!hargaText.isEmpty() && !jumlahText.isEmpty()) {
        try {
            // Konversi dari String ke Double dan Integer
            double hargaJualValue = Double.parseDouble(hargaText.replace("Rp", "").replace(".", ""));
            int jumlahValue = Integer.parseInt(jumlahText);

            // Hitung total awal
            double totalJual = hargaJualValue * jumlahValue;

            // Terapkan diskon jika member
            if (isMember) {
                totalJual -= totalJual * discountRate;
            }

            // Debugging: Cek nilai
            System.out.println("Harga: Rp " + hargaJualValue);
            System.out.println("Jumlah: " + jumlahText);
            System.out.println("Diskon: " + (discountRate * 100) + "%");
            System.out.println("Total Setelah Diskon: Rp " + String.format("%,.0f", totalJual));

            // Update nilai total beli di field j
            j.setText("Rp " + String.format("%,.0f", totalJual));
        } catch (NumberFormatException e) {
            System.out.println("Error parsing angka: " + e.getMessage());
            j.setText("Rp 0"); // Set ke 0 jika terjadi kesalahan parsing
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
            java.util.logging.Logger.getLogger(PENJUALAN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PENJUALAN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PENJUALAN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PENJUALAN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PENJUALAN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CETAK;
    private javax.swing.JTextField a;
    private javax.swing.JLabel back;
    private javax.swing.JTextField c;
    private javax.swing.JButton cari;
    private com.toedter.calendar.JDateChooser e;
    private javax.swing.JButton hapustn;
    private javax.swing.JTextField harga;
    private javax.swing.JTextField j;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField member;
    private javax.swing.JComboBox<String> namabarang;
    private javax.swing.JTextField search;
    private javax.swing.JTable tabelpenjualan;
    private javax.swing.JButton tambahbtn;
    // End of variables declaration//GEN-END:variables

    private void resetForm() {
    }
}
