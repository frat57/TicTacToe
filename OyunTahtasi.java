package anaPaket;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class OyunTahtasi {
	
	private int boyut;
	private char tahta[][];
	
	public OyunTahtasi() {
		setBoyut(3);
		tahta = new char[getBoyut()][getBoyut()];
		for(int i=0; i<getBoyut(); i++) {
			for(int j=0; j<getBoyut(); j++) {
				tahta[i][j] = ' ';
			}
		}
	}
	
	public OyunTahtasi(int boyut) {
		this.setBoyut(boyut);
		tahta = new char[boyut][boyut];
		for(int i=0; i<getBoyut(); i++) {
			for(int j=0; j<getBoyut(); j++) {
				tahta[i][j] = ' ';
			}
		}
	}
	
	public OyunTahtasi(char tahta[][]) {
		this.tahta = tahta;
	}
	
	public void setTahtaIndex(int i, int j, char c) {
		tahta[i][j] = c;
	}
	
	public void oyunTahtasiniYazdir() {
		int i,j;
		char harf = 'A';
		
		for(i=0;i<getBoyut();i++, harf++) {
			System.out.print(" |"+harf);
		}
		
		System.out.println();
		for(i=0;i<getBoyut();i++) {
			for(j=0;j<getBoyut();j++)
				System.out.print("---");
			System.out.println();
			System.out.print(i);
			
			for(j=0;j<getBoyut();j++)
				System.out.print("|" + tahta[i][j] +" ");
			System.out.println();
		}
	}
	
	char[][] oyunTahtasiniAl() {
		return tahta;
	}
	
	boolean hamleyiYaz(String koordinat, char oyuncu) {
		int x = Integer.parseInt(koordinat.substring(0, 1));
		int y = Integer.parseInt(koordinat.substring(1, 2));
			
		if(tahta[x][y] != ' ') 
			return false;
		
		tahta[x][y] = oyuncu;
		return true;
	}
	
	boolean kazanan() {
		boolean d1=true, d2=true, d3=true, d4=true;
		int sayac1=0, sayac2=0, sayac3=0, sayac4=0;
		
		for(int i=0; i<getBoyut(); i++) {
			for(int j=0; j<getBoyut(); j++) {
				if(tahta[i][j] == 'X') sayac1++;
				if(tahta[j][i] == 'X') sayac2++;
				if(tahta[i][j] == 'O') sayac3++;
				if(tahta[j][i] == 'O') sayac4++;
			}
			if(sayac1== getBoyut() || sayac2==getBoyut()) {
				System.out.println("X oyuncusu kazandý");
				return true;
			}
			if(sayac3== getBoyut() || sayac4==getBoyut()) {
				System.out.println("O oyuncusu kazandý");
				return true;
			}
			sayac1=0;sayac2=0;sayac3=0;sayac4=0;
		}
		
		
		//Çapraz
		for(int i=0; i<getBoyut(); i++) {
				if(tahta[i][i] != 'X') d1=false;
				if(tahta[i][i] != 'O') d3=false;
		}
		for(int i=0, j=getBoyut()-1; i<getBoyut(); i++, j--) {
			if(tahta[i][j] != 'X') d2=false;
			if(tahta[i][j] != 'O') d4=false;
		}
		
		if(d1 || d2) {
			System.out.println("X oyuncusu kazandý");
			return true;
		}
		if(d3 || d4) {
			System.out.println("O oyuncusu kazandý");
			return true;
		}
	
		return false;
	}
	
	boolean beraberlikKontrol() {
		for(int i=0; i<getBoyut(); i++) {
			for(int j=0; j<getBoyut(); j++) {
				if(tahta[i][j] == ' ')
					return true;
			}
		}
		System.out.println("Berabere");
		return false;
	}
	
	void kaydet(char harf) throws IOException {
		FileOutputStream out = null;	
		try {
			out = new FileOutputStream("oyunkayit.txt");
			
			out.write((char) (getBoyut() + '0'));
			for(int i=0; i<getBoyut(); i++)
				for(int j=0; j<getBoyut(); j++)
					out.write(tahta[i][j]);
			out.write(harf);
			
		}catch (Exception e) {
			System.out.println("Hata, Dosyaya yazýlamadý");
		}finally {
			//dosya açýlmýþsa kapat
			if(out != null)	out.close();
		}
	}
	
	static String yukle() throws IOException {
		FileInputStream in = null;
		String yukle = null;
		try {
			in = new FileInputStream("oyunkayit.txt");
			yukle = (char) in.read()+ "";
			int boyut = Integer.parseInt(yukle);
			
			for(int i=0; i<boyut*boyut+1; i++) {
				yukle = yukle + (char) in.read();
			}
			
		}catch (Exception e) {
			System.out.println("Hata, Dosya bulunamadi");
		}finally {
			//dosya açýlmýþsa kapat
			if(in != null)	in.close();
		}
		
		return yukle;
	}

	public int getBoyut() {
		return boyut;
	}

	public void setBoyut(int boyut) {
		this.boyut = boyut;
	}
}
