package anaPaket;

import java.util.Random;
import java.util.Scanner;

public class Oyuncu {

	char harf;
	boolean insanMi;
	private String hamle;
	public String ad;
	
	public Oyuncu() {
		harf = 'X';
		insanMi	= true;
	}
	
	public Oyuncu(boolean insanMi) {
		this.insanMi = insanMi;
		if(insanMi == true)
			harf = 'X';
		else
			harf = 'O';
	}
	
	public Oyuncu(boolean insanMi, char kr) {
		this.insanMi = insanMi;
		harf = kr;
	}
	
	char karakteriAl() {
		return harf;
	}
	
	boolean oyuncuTurunuAl() {
		return insanMi;
	}
	
	String oyuncununHamlesiniAl() {
		return hamle;
	}
	
	String insanOyuncuHamlesiKontrol(OyunTahtasi tahta) {
		Scanner keyboard = new Scanner(System.in);
		String x,y;
		/*char h1='A';
		for(int i=0;i<tahta.getBoyut()-1; i++) h1++;*/
		
		do {
			do {
				System.out.println("Hamle sýrasý sizde: ");
				System.out.println("X deðeri için [0," + (tahta.getBoyut()-1) + "] aralýðýnda bir sayý giriniz: ");
				x = keyboard.nextLine();
			}while(!boyutAraligindaMi(x, tahta));
			do {
				System.out.println("Y deðeri için [0," + (tahta.getBoyut()-1) + "] aralýðýnda bir sayý giriniz: ");
				y = keyboard.nextLine();
			}while(!boyutAraligindaMi(y, tahta));
			
			hamle = x+y;
		}while(!tahta.hamleyiYaz(hamle, harf));
		
		return hamle;
	}
	
	boolean boyutAraligindaMi(String s, OyunTahtasi tahta) {
		//Boþ stringse ->return false
		if(!(s.length() == 1))	return false;
		//String sayý deðilse-> return false
		char rakamlar[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		for(int i=0; i<s.length(); i++) {
			boolean var=false;
			for(int j=0; j<rakamlar.length; j++) {
				if( s.charAt(i) == rakamlar[j] )	var=true;
			}
			if(var==false)
				return false;
			var=false;
		}
		
		//Sayýysa
		int sayi = Integer.parseInt(s);
		for(int i=0; i<tahta.getBoyut(); i++) {
			if(i == sayi)
				return true;
		}
		return false;
	}
	
	String bilgisayarHamlesiUret(OyunTahtasi tahta) {
		Random rastgele = new Random();
		do {
			int x = rastgele.nextInt(tahta.getBoyut());
			int y = rastgele.nextInt(tahta.getBoyut());
			hamle = Integer.toString(x) + Integer.toString(y);
		}while(!tahta.hamleyiYaz(hamle, harf));
		
		return hamle;
	}
}
