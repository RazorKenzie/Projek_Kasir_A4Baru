/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package KONEKSI;

/**
 *
 * @author devipermata
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;



public class konek {
    
   
   
    public static Connection conn;

    public static Connection koneksiDB() {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/sistem_pembukuan_umkm";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Koneksi berhasil");
        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver tidak ditemukan: " + e.getMessage());
                  
        }
        return conn;
    }

    public static Connection getConnection() {
       if (conn == null) {
            try {
                return koneksiDB(); // Memanggil metode koneksiDB jika koneksi belum ada
            } catch (Exception e) {
                Logger.getLogger(konek.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return conn;
    }
}

   
          