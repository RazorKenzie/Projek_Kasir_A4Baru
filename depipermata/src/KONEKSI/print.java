/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package KONEKSI;

import com.fazecast.jSerialComm.SerialPort;
import java.io.OutputStream;

public class print {
    public static void main(String[] args) {
       String portName = "/dev/tty.RPP02N"; // coba ganti-ganti port

        SerialPort comPort = SerialPort.getCommPort(portName);
        comPort.setComPortParameters(115200, 8, 1, 0); // ubah baudrate ke 115200
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        if (comPort.openPort()) {
            System.out.println("✅ Port terbuka: " + portName);

            try {
                OutputStream out = comPort.getOutputStream();

                // ESC/POS init + cetak + 3 baris kosong
                String esc = "\u001B";
                String init = esc + "@";
                String text = init + "Halo dari Mac\r\n\r\n\r\n";
                out.write(text.getBytes("UTF-8"));

                out.flush();
                System.out.println("✅ Data terkirim.");
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
