package main;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

//Một người chơi sẽ có tài khoản, người chơi sẽ có quyền đặt cược ít hơn hoặc bằng số tiền họ đang có
//Luật chơi:
/*Có 3 viên xúc xắc. Mỗi lần gieo sẽ ra một kết quả
 * Nếu tổng  = 3 hoặc 18 => Cái ăn hết, người chơi thua
 * Nếu tổng từ 4-10 => Bên xỉu
 * Nếu tổng từ 11-17 => Bên tài
 * */
public class TaiXiu {
	public static void main(String[] args) {
		double account = 5000;
		Locale lc = new Locale("vi", "VN");// Chọn format theo nước VN
		NumberFormat numf = NumberFormat.getCurrencyInstance(lc); //format tiền theo vn
		int luaChon = 0 ; 
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("----------Lua chon----------");
			System.out.println("Chon 1 de tiep tuc choi");
			System.out.println("Chon phim bat ki de thoat");
			while(true) {
				  try {
					  luaChon = sc.nextInt();
				    break;

				  } catch (Exception e) {
				    System.err.print("Vui long nhap so 1 de vao choi");
				    
				    // Xóa dữ liệu còn lại trong buffer
				    sc.nextLine(); 
				  }
				}
			
			if(luaChon == 1) {
				System.out.println("****BAT DAU CHOI****");
				// Đặt cược
				double datCuoc =0;
				do {
					System.out.println("Tai khoan cua ban: "+numf.format(account)+ "; Ban muon cuoc bao nhieu ? ");
					datCuoc = sc.nextDouble();
				} while (datCuoc < 0 || datCuoc > account );
				// chọn tài xỉu
				int luaChonTaiXiu = 0;
				do {
					System.out.println("Chon 1 <=> tai hoac 2<=> xiu");
					luaChonTaiXiu = sc.nextInt();
				} while (luaChonTaiXiu != 1 && luaChonTaiXiu != 2 );
				
				
			// Tung xúc xắc
				Random xucXac1 = new Random();
				Random xucXac2 = new Random();
				Random xucXac3 = new Random();
				
				int gt1 = xucXac1.nextInt(5)+1;
				int gt2 = xucXac2.nextInt(5)+1;
				int gt3 = xucXac3.nextInt(5)+1;
				int tong = gt1 + gt2 + gt3 ;
				
				
				//Tính toán kết quả
				System.out.println("Ket qua: "+gt1+" - "+gt2+" - "+gt3);
				if(tong == 3 || tong ==18) {
					account -= datCuoc;
					System.out.println("Tong la "+tong+", nha cai win");
					System.out.println("So du la: "+numf.format(account));
				}
				else if(tong >=4 && tong <=10){
					System.out.println("Tong la "+tong+"=> Xiu");
					if(luaChon == 2) {
						account += datCuoc;
						System.out.println("Ban thang");
						System.out.println("So du la: "+numf.format(account));
					}
					else {
						account -= datCuoc;
						System.out.println("Ban thua");
						System.out.println("So du la: "+numf.format(account));
					}
				}
				else {
					System.out.println("Tong la "+tong+"=> Tai");
					if(luaChon == 1) {
						account += datCuoc;
						System.out.println("Ban thang");
						System.out.println("So du la: "+numf.format(account));
					}
					else {
						account -= datCuoc;
						System.out.println("Ban thua");
						System.out.println("So du la: "+numf.format(account));
					}
				}
			}
			
		} while (luaChon == 1);	
		System.out.println("Ban da thoat, cam on ban da ghe qua");
	}
}
