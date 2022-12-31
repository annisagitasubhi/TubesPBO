package TubesPBO;

import java.util.Scanner;

public class Bolu implements Interface {
        public String kasir;
		public Integer noNota;
		public String namaPembeli;
		public String kodeBolu;
		public String namaBolu;
		public Integer hargaBolu;
		public Integer jumlahBolu;
		public Integer subTotal;
		public Integer disc;
		public Integer totalHarga;
		
		Scanner input = new Scanner(System.in);

		//Method Kasir
		@Override
    	public void Kasir(){
        	kasir = null;
    	}
		
		//Method NoNota
		@Override
		public void NoNota() {
			System.out.print("Inputkan No Nota : ");
			noNota = input.nextInt();
		}

		//Method NamaPembeli
		@Override
		public void NamaPembeli() {
			System.out.print("Inputkan Nama Pembeli : ");
			namaPembeli = input.next();
		}

		//Method KodeBolu
		@Override
		public void KodeBolu() {
			System.out.print("Inputkan Kode Bolu : ");
			kodeBolu = input.next();
		}

		//Method NamaBolu
		@Override
		public void NamaBolu() {
			System.out.print("Inputkan Nama Bolu : ");
			namaBolu = input.next();
		}

		//Method HargaBolu
		@Override
		public void HargaBolu() {
			System.out.print("Inputkan Harga Bolu : ");
			hargaBolu = input.nextInt();	
		}

		//Method JumlahBolu
		@Override
		public void Jumlah() {
			System.out.print("Inputkan Jumlah Bolu : ");
			jumlahBolu = input.nextInt();
		}

		//Method Subtotal
		@Override
		public void SubTotal() {
			subTotal = null;
		}

		//Method Diskon
		@Override
		public void Diskon() {
			disc = null;
		}
		
		//Method TotalHarga
		@Override
		public void TotalHarga() {
			totalHarga = null;
		}
}
