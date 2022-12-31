package TubesPBO;

import java.util.Scanner;
import java.sql.*;
import java.util.HashSet;

public class App {
    static Connection conn;

    public static void main(String[] args) throws Exception {
        boolean pilihan = true;
        String pilih;
        try (Scanner terimaInput = new Scanner(System.in)) {
            Pembelian pembelian = new Pembelian();

            //Pengolahan Database (CRUD)
			String url = "jdbc:mysql://localhost:3306/dbbolu";

            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");
            	conn = DriverManager.getConnection(url,"root","");
            	System.out.println("Class Driver ditemukan");
            	
            	//Collection Framework (HashSet)
            	HashSet<String> ket = new HashSet<String>();
            	System.out.println("\n-------------------------------------------------------");
            	System.out.println("             SELAMAT DATANG");
            	System.out.println("============TOKO BOLU KHANSA=============");
				ket.add("Tanjung Beringin");
				ket.add("Lubuk Sikaping");
				ket.add("Pasaman");
            	System.out.println("Alamat : " + ket);
            	System.out.println("-------------------------------------------------------\n");
            	pembelian.Kasir();

            	//Perulangan
            	while (pilihan) {
            		System.out.println("\n-----------------------------------");
            		System.out.println("Database Transaksi Pembelian Bolu");
            		System.out.println("-----------------------------------");
            		System.out.println("1. Lihat Data Transaksi Pembelian Bolu");
            		System.out.println("2. Tambah Data Transaksi Pembelian Bolu");
            		System.out.println("3. Ubah Data Transaksi Pembelian Bolu");
            		System.out.println("4. Hapus Data Transaksi Pembelian Bolu");
            		System.out.println("5. Cari Data Transaksi Pembelian Bolu");
            		
            		System.out.print("\nPilihan anda (1/2/3/4/5): ");
            		pilih = terimaInput.next();
            		
            		//Percabangan
            		switch (pilih) {
            		case "1":
            			pembelian.viewData();
            			break;
            		case "2":
            			pembelian.insertData();
            			break;
            		case "3":
            			pembelian.updateData();
            			break;
            		case "4":
            			pembelian.deleteData();
            			break;
            		case "5":
            			pembelian.searchData();
            			break;
            		default:
            			System.err.println("\nInput tidak ditemukan\nSilakan pilih [1-5]");
            		}
            		
            		System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
            		pilih = terimaInput.next();
            		pilihan = pilih.equalsIgnoreCase("y");
            	}
            	System.out.println("---------------------------------------------------------------------------\n");
            	System.out.println("Kasir          = " + pembelian.kasir);
            	System.out.println("\n");
            	pembelian.tanggal();
            	pembelian.waktu();
            	System.out.println("\n=======TERIMAKASIH!=======");
            	System.out.println("===SELAMAT MENIKMATI===\n");
            	
            }
            //Exception
            catch(ClassNotFoundException ex) {
            	System.err.println("Driver Error");
            	System.exit(0);
            }
            //Exception
            catch(SQLException e){
            	System.err.println("Tidak berhasil koneksi");
            }
        }
    }
}
