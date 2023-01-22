package Com.Crud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/koprasi";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    // METHOD TAMPILKAN DATA //
    static void TampilkanData() {

        String sql = "SELECT * FROM tbanggota";

        try {
            rs = stmt.executeQuery(sql);

            System.out.println("==================== DATA KOPERASAI ====================");

            while (rs.next()) {
                String No_Anggota = rs.getString("No_Anggota");
                String Nama_Anggota = rs.getString("Nama_Anggota");
                String Alamat = rs.getString("Alamat");
                String Telepon = rs.getString("Telepon");

                System.out.println(String.format("|%s|     |%s|     |%s|     |%s|", No_Anggota, Nama_Anggota, Alamat, Telepon));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // METHOD INSERT//
    public static void MasukanData() {
        try {
            // ambil input dari user

            System.out.print("No Anggota   : ");
            String No_Anggota = input.readLine();
            System.out.print("Nama Anggota : ");
            String Nama_Anggota = input.readLine().trim();
            System.out.print("Aalamat      : ");
            String Alamat = input.readLine().trim();
            System.out.print("Telepon      : ");
            String Telepon = input.readLine();

            // query simpan
            String sql = "INSERT INTO tbanggota (No_Anggota,Nama_Anggota, Alamat,Telepon) VALUES('%s','%s', '%s','%s')";
            sql = String.format(sql, No_Anggota, Nama_Anggota, Alamat, Telepon);

            // simpan Anggota
            stmt.execute(sql);
            System.out.println("Data berhasil d insert... ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // METHOD UPDATE //
    static void EditData() {
        try {

            // ambil input dari user
            System.out.print("ID yang mau diedit: ");
            String No_Anggota = (input.readLine().trim());
            System.out.print("Nama_Anggota: ");
            String Nama_Anggota = input.readLine().trim();
            System.out.print("Alamat: ");
            String Alamat = input.readLine().trim();

            // query update
            String sql = "UPDATE tbanggota SET Nama_Anggota='%s', Alamat='%s' WHERE No_Anggota=%s";
            sql = String.format(sql, Nama_Anggota, Alamat, No_Anggota);

            // update data buku
            stmt.execute(sql);
            System.out.println("Data Berhasil di update...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // METHOD DELETE//
    static void HapusData() {
        try {

            // ambil input dari user
            System.out.print("ID yang mau dihapus: ");
            int No_Anggota = Integer.parseInt(input.readLine());

            // buat query hapus
            String sql = String.format("DELETE FROM tbanggota WHERE No_Anggota=%d", No_Anggota);

            // hapus data
            stmt.execute(sql);

            System.out.println("Data telah terhapus...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            // register driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (!conn.isClosed()) {
                Menu();
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void Menu() {
        System.out.println("\n=== PEMROGRAMAN BERORIENTASI OBJECT ===");
        System.out.println("=======================================");
        System.out.println("||         1. Masukan Data           ||");        
        System.out.println("||         2. Tampilkan Data         ||");
        System.out.println("||         3. Edit Data              ||");
        System.out.println("||         4. Hapus Data             ||");
        System.out.println("||         0. Keluar                 ||");
        System.out.println("=======================================");
        System.out.println("");
        System.out.print("Masukan Pilihan Anda : ");

        try {
            int pilihan = Integer.parseInt(input.readLine());

            if (pilihan == 1) {
                MasukanData();
            } else if (pilihan == 2) {
                TampilkanData();
            } else if (pilihan == 3) {
                EditData();
            } else if (pilihan == 4) {
                HapusData();
            } else if (pilihan == 0) {
                System.exit(0);
            } else {
                System.out.println("Pilihan salah. Silahkan masukan ulang pilihan anda!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
