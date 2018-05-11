package anaPaket;

import java.io.IOException;
import java.util.Scanner;

public class OyunMain {

	private int n;
	private boolean devamMi;
	
	public int getN() {
		return n;
	}
	
	public void setN(String n) {
		if(n.equals("3") || n.equals("5") || n.equals("7")) {
			this.n = Integer.parseInt(n);
		}
	}
	
	public boolean devamKontrol(Scanner keyboard) {
		while(true) {
			System.out.println("Devam etmek istiyor musunuz? (e,h)");
			String d = keyboard.nextLine();
			if(d.equalsIgnoreCase("E"))	return true;
			if(d.equalsIgnoreCase("H"))	return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		//tahtayý oluþtur
		//kontrol et
		//dongu	
			//hamle yap
			//kontrol et
		
		Scanner keyboard = new Scanner(System.in);
		OyunMain oyun = new OyunMain();
		
		//oyun biterse yeniden baþlatmak için
		do {
			oyun.n = -1;
			oyun.devamMi = false;
			
			OyunTahtasi tahta=null;
			Oyuncu kullanici=null;
			Oyuncu bilgisayar=null;
			
			String isim=null;
			String alinan = null;
			String harf=null;
			do {
				System.out.println("Oyunu yüklemek istiyor musunuz(E, H): ");
				alinan = keyboard.nextLine();
				
				if(alinan.equalsIgnoreCase("E") ) {
					String yukle = OyunTahtasi.yukle();
					int boyut = Character.getNumericValue(yukle.charAt(0));				
					
					tahta = new OyunTahtasi(boyut);
					for(int i=0, k=1; i<boyut; i++) {
						for(int j=0; j<boyut; j++, k++)
						tahta.setTahtaIndex(i, j, yukle.charAt(k));
					}
					
					char c1 = yukle.charAt(yukle.length()-1);
					char c2 = 'O';
					
					if(c1== 'O') c2 = 'X';
					
					kullanici = new Oyuncu(true, c1);
					bilgisayar = new Oyuncu(false, c2);
				}
				else if (alinan.equalsIgnoreCase("H")) {
					while(oyun.getN() == -1) {
						System.out.println("Lütfen oyun tahtasýnýn boyutunu giriniz(3, 5, 7 olabilir): ");
						oyun.setN(keyboard.nextLine());
					}
					tahta = new OyunTahtasi(oyun.getN());
					
					System.out.println("Lütfen kullanýcýnýn adýný giriniz: ");
					isim = keyboard.nextLine();
					System.out.println("Lütfen kullanýcýnýn harfini giriniz(X/O)(Varsayýlan X): ");
					harf = keyboard.nextLine();
					char ch;
					if(harf.length() == 1 && harf.equalsIgnoreCase("X")) ch = 'X';
					else if(harf.length() == 1 && harf.equalsIgnoreCase("O")) ch = 'O';
					else ch = 'X';
					char ch2 = 'O';
					if(ch =='O') {
						ch2 = 'X';
					}
					
					kullanici = new Oyuncu(true, ch);	//insan oyuncusu
					bilgisayar = new Oyuncu(false, ch2);	//bilgisayar oyuncusu
				}
			}while (!(alinan.equalsIgnoreCase("E") || alinan.equalsIgnoreCase("H"))) ;
			
			
			//ana oyun döngüsü
			tahta.oyunTahtasiniYazdir();
			while(true) {
				tahta.kaydet(kullanici.harf);
				kullanici.insanOyuncuHamlesiKontrol(tahta);
				System.out.println("Yapýlan hamle: " + kullanici.oyuncununHamlesiniAl());
				if(tahta.kazanan()) {
					break;
				}
				if(!tahta.beraberlikKontrol())
					break;
				bilgisayar.bilgisayarHamlesiUret(tahta);
				System.out.println("Bilgisayarýn yaptýðý hamle: " + bilgisayar.oyuncununHamlesiniAl());
				tahta.oyunTahtasiniYazdir();
				if(tahta.kazanan()) {
					break;
				}
				if(!tahta.beraberlikKontrol())
					break;
			}
			tahta.oyunTahtasiniYazdir();
			oyun.devamMi = oyun.devamKontrol(keyboard);
			
		}while(oyun.devamMi == true);
	}
}
