package TubesPBO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;
import java.sql.ResultSet;

public class Pembelian extends Bolu {
    static Connection conn;

	//Constuctor Transaksi
	public Pembelian(){
		noNota = 2111522011;
		namaPembeli = "Annisa Gita Subhi";
		kodeBolu = "111";
		namaBolu = "Bolu Coklat";
		hargaBolu = 45000;
		jumlahBolu = 2;
	}

	//Method Kasir
	@Override
    public void Kasir()
    {
        System.out.print("Inputkan Nama Kasir : ");
        kasir = input.nextLine();
        kasir = kasir.toUpperCase();
    }

	//Method SubTotal
	//Proses Matematika
	@Override
	public void SubTotal() {
		subTotal = hargaBolu * jumlahBolu;

	}

	//Method Discount
	//Proses Matematika
	@Override
	public void Diskon() {
		//Percabangan
            if(subTotal >150000 && subTotal <=300000) {
				disc = subTotal*5/100;
			}

			else if (subTotal >300000 && subTotal <=500000) {
				disc = subTotal*10/100;
			}

			else if (subTotal >500000) {
				disc = subTotal*15/100;
			}
                
			else{
				disc = 0;
			}
	}

	//Method TotalHarga
	@Override
	public void TotalHarga() {
			totalHarga = subTotal-disc;
		}

	//Method tanggal
	public void tanggal()
    {
        Date Date = new Date();
        SimpleDateFormat tgl = new SimpleDateFormat("EEEE, dd MMM yyyy");
        System.out.println("Tanggal Transaksi = " + tgl.format(Date));
    }

	//Method waktu
    public void waktu()
    {
        Date Time = new Date();    
        SimpleDateFormat tm = new SimpleDateFormat("HH:mm:ss");  
        System.out.println("Waktu Transaksi   = " + tm.format(Time));
    }

	//Method viewData
	public void viewData() throws SQLException {
		String text1 = "\n===============Daftar Transaksi Bolu===============";
		//Method String
		System.out.println(text1.toUpperCase());

		//Pengolahan Database (CRUD)
		String url = "jdbc:mysql://localhost:3306/dbbolu";
		conn = DriverManager.getConnection(url,"root","");
		
		String sql ="SELECT * FROM penjualan_bolu";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
		
		//Perulangan
		while(result.next()){
			System.out.print("\nNo. Nota		: ");
            System.out.print(result.getInt("no_nota"));
			System.out.print("\nNama Pembeli		: ");
            System.out.print(result.getString("nama_pembeli"));
			System.out.print("\nKode Bolu		: ");
            System.out.print(result.getString("kode_bolu"));
            System.out.print("\nNama Bolu		: ");
            System.out.print(result.getString("nama_bolu"));
            System.out.print("\nHarga Bolu		: ");
            System.out.print(result.getInt("harga_bolu"));
			System.out.print("\nJumlah Bolu		: ");
            System.out.print(result.getInt("jumlah_bolu"));
			System.out.print("\nSubtotal		: ");
            System.out.print(result.getInt("subtotal"));
            System.out.print("\nDiskon			: ");
            System.out.print(result.getInt("diskon"));
			System.out.print("\nTotal pembayaran	: ");
            System.out.print(result.getInt("total_bayar"));
            System.out.print("\n");
		}
	}

	//Method insertData
	public void insertData() throws SQLException{
		String text2 = "\n===Tambah Data Transaksi Pembelian Bolu===";
		//Method String
		System.out.println(text2.toUpperCase());

		//Pengolahan Database (CRUD)
		String url = "jdbc:mysql://localhost:3306/dbbolu";
		conn = DriverManager.getConnection(url,"root","");
		
		Scanner terimaInput = new Scanner (System.in);
		Pembelian pembelian = new Pembelian();
				
		try {
		pembelian.NoNota();
		pembelian.NamaPembeli();
		pembelian.KodeBolu();
        pembelian.NamaBolu();
        pembelian.HargaBolu();
        pembelian.Jumlah();
        pembelian.SubTotal();
        pembelian.Diskon();
        pembelian.TotalHarga();
		
		//Pengolahan Database (CRUD)
		String sql = "INSERT INTO penjualan_bolu (no_nota, nama_pembeli, kode_bolu, nama_bolu, harga_bolu, jumlah_bolu, subtotal, diskon, total_bayar) VALUES ('"+noNota+"','"+namaPembeli+"','"+kodeBolu+"','"+namaBolu+"','"+hargaBolu+"','"+jumlahBolu+"','"+subTotal+"','"+disc+"','"+totalHarga+"')";
					
        Statement statement = conn.createStatement();
        statement.execute(sql);
        System.out.println("Berhasil input data");
	
	    } 
		//Exception
		catch (SQLException e) {
	        System.err.println("Terjadi kesalahan input data");
	    } 
		//Exception
		catch (InputMismatchException e) {
	    	System.err.println("Inputlah dengan angka saja");
	   	}
	}

	//Method updateData
	public void updateData() throws SQLException{
		String text3 = "\n===Ubah Data Transaksi Pembelian Bolu===";
		//Method String
		System.out.println(text3.toUpperCase());

		//Pengolahan Database (CRUD)
		String url = "jdbc:mysql://localhost:3306/dbbolu";
		conn = DriverManager.getConnection(url,"root","");
		
		Scanner terimaInput = new Scanner (System.in);
		Pembelian pembelian = new Pembelian();
		
		try {
            viewData();
            System.out.print("Masukkan No. Nota Pembelian yang akan di ubah atau update : ");
            pembelian.noNota = Integer.parseInt(terimaInput.nextLine());
            
			//Pengolahan Database (CRUD)
            String sql = "SELECT * FROM penjualan_bolu WHERE no_nota = " +pembelian.noNota;
            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            if(result.next()){

				System.out.print("Nama Pembeli ["+result.getString("nama_pembeli")+"]\t: ");
                pembelian.namaPembeli = terimaInput.nextLine();

				System.out.print("Kode Bolu ["+result.getString("kode_bolu")+"]\t: ");
                pembelian.kodeBolu = terimaInput.nextLine();
                
                System.out.print("Nama Bolu ["+result.getString("nama_bolu")+"]\t: ");
                pembelian.namaBolu = terimaInput.nextLine();
                
                System.out.print("Harga Bolu ["+result.getString("harga_bolu")+"]\t: ");
                pembelian.hargaBolu = terimaInput.nextInt();

				System.out.print("Jumlah Bolu ["+result.getString("jumlah_bolu")+"]\t: ");
                pembelian.jumlahBolu = terimaInput.nextInt();

				pembelian.SubTotal();
				pembelian.Diskon();
				pembelian.TotalHarga();
                
				//Pengolahan Database (CRUD)
                sql = "UPDATE penjualan_bolu SET nama_pembeli='"+pembelian.namaPembeli+"',kode_bolu='"+pembelian.kodeBolu+"',nama_bolu='"+pembelian.namaBolu+"',harga_bolu= '"+pembelian.hargaBolu+"',jumlah_bolu='"+pembelian.jumlahBolu+"',subtotal='"+pembelian.subTotal+"',diskon='"+pembelian.disc+"',total_bayar='"+pembelian.totalHarga+"' WHERE no_nota='"+pembelian.noNota+"'";
                //System.out.println(sql);

                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil memperbaharui data Transaksi Pembelian Bolu (No. Nota "+pembelian.noNota+")");
                }
            }
            statement.close();        
        }
		//Exception 
		catch (SQLException e) {
            System.err.println("Terjadi kesalahan dalam mengedit data");
            System.err.println(e.getMessage());
        }
		}

		//Method deleteData
		public void deleteData() throws SQLException {
			String text4 = "\n===Hapus Data Transaksi Pembelian Bolu===";
			System.out.println(text4.toUpperCase());
			
			Scanner terimaInput = new Scanner (System.in);
			
			try{
				viewData();
				System.out.print("Input No. Nota pembelian yang akan Anda Hapus : ");
				Integer noNota= Integer.parseInt(terimaInput.nextLine());
				
				//Pengolahan Database (CRUD)
				String sql = "DELETE FROM penjualan_bolu WHERE no_nota = "+ noNota;
				Statement statement = conn.createStatement();
				//ResultSet result = statement.executeQuery(sql);
				
				if(statement.executeUpdate(sql) > 0){
					System.out.println("Berhasil menghapus data Transaksi Pembelian Bolu (No. Nota "+noNota+")");
				}
		   }
		   //Exception
		   catch(SQLException e){
				System.out.println("Terjadi kesalahan dalam menghapus data bolu");
				}
		}

		//Method searchData
		public void searchData () throws SQLException {
			String text5 = "\n===Cari Data Transaksi Pembelian Bolu===";
			System.out.println(text5.toUpperCase());

			//Pengolahan Database (CRUD)
			String url = "jdbc:mysql://localhost:3306/dbbolu";
			conn = DriverManager.getConnection(url,"root","");
			
			Scanner terimaInput = new Scanner (System.in);
					
			System.out.print("Masukkan Nama Bolu : ");
			
			String keyword = terimaInput.nextLine();
			Statement statement = conn.createStatement();

			//Pengolahan Database (CRUD)
			String sql = "SELECT * FROM penjualan_bolu WHERE nama_bolu LIKE '%"+keyword+"%'";
			ResultSet result = statement.executeQuery(sql); 
         
		//Perulangan
        while(result.next()){
        	System.out.print("\nNo. Nota		: ");
            System.out.print(result.getInt("no_nota"));
			System.out.print("\nNama Pembeli		: ");
            System.out.print(result.getString("nama_pembeli"));
			System.out.print("\nKode Bolu		: ");
            System.out.print(result.getString("kode_bolu"));
            System.out.print("\nNama Bolu		: ");
            System.out.print(result.getString("nama_bolu"));
            System.out.print("\nHarga Bolu		: ");
            System.out.print(result.getInt("harga_bolu"));
			System.out.print("\nJumlah Bolu		: ");
            System.out.print(result.getInt("jumlah_bolu"));
			System.out.print("\nSubtotal 	    : ");
            System.out.print(result.getInt("subtotal"));
            System.out.print("\nDiskon			: ");
            System.out.print(result.getInt("diskon"));
			System.out.print("\nTotal Pembayaran	: ");
            System.out.print(result.getInt("total_bayar"));
            System.out.print("\n");
        }
		}

		//Method display
		public void display(){
			System.out.println("===============NOTA TRANSAKSI PEMBELIAN BOLU===============");
			tanggal();
        	waktu();
        	System.out.println("Nama Kasir        = " + kasir);
        	System.out.println("------------------------------------------------------");

			System.out.println("No Nota           = " + noNota);
			System.out.println("Kode Bolu         = " + kodeBolu);
			System.out.println("Nama Bolu         = " + namaBolu);
			System.out.println("Harga Bolu        = " + hargaBolu);
			System.out.println("Jumlah Bolu       = " + jumlahBolu);
			System.out.println("Subtotal Harga    = " + subTotal);
			System.out.println("Diskon            = " + disc);
			System.out.println("Total Pembayaran  = " + totalHarga);
		}
    
}
