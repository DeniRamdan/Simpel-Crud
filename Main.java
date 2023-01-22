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

    
    public static void main(String[] args) {

        try {
            // register driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (!conn.isClosed()) {
                showMenu();
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void showMenu() {
        System.out.println("\n========= MENU UTAMA =========");
        System.out.println("1. Insert Data");
        System.out.println("2. Show Data");
        System.out.println("3. Edit Data");
        System.out.println("4. Delete Data");
        System.out.println("0. Keluar");
        System.out.println("");
        System.out.print("PILIHAN> ");

        try {
            int pilihan = Integer.parseInt(input.readLine());

            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    insertAnggota();
                    break;
                case 2:
                    showData();
                    break;
                case 3:
                    updateAnggota();
                    break;
                case 4:
                    deleteAnggota();
                    break;
                default:
                    System.out.println("Pilihan salah!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // METHOD TAMPILKAN DATA //
    static void showData() {

        String sql = "SELECT * FROM tbanggota";

        try {
            rs = stmt.executeQuery(sql);

            System.out.println("+--------------------------------+");
            System.out.println("|    DATA BUKU DI PERPUSTAKAAN   |");
            System.out.println("+--------------------------------+");

            while (rs.next()) {
                String No_Anggota = rs.getString("No_Anggota");
                String Nama_Anggota = rs.getString("Nama_Anggota");
                String Alamat = rs.getString("Alamat");
                String Telepon = rs.getString("Telepon");

                System.out.println(String.format("|%s|--|%s|--|%s|--|%s|", No_Anggota, Nama_Anggota, Alamat, Telepon));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // METHOD INSERT//
    public static void insertAnggota() {
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
    static void updateAnggota() {
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
    static void deleteAnggota() {
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
}