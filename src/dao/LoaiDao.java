package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.Database;
import entity.Loai;

/**
 * Lớp DAO của Loại sản phẩm
 * @author Phan Võ Trọng
 *
 */
public class LoaiDao {
	private ArrayList<Loai> list;
	
	/**
	 * lấy danh sách tất cả các loai
	 */
	public ArrayList<Loai> layDsLoai(){
		list = new ArrayList<Loai>();
		Database.getInstance();
		Connection con = Database.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from LoaiSanPham");
			while(rs.next()) {
				
				Loai kh = new Loai(rs.getString(1),rs.getString(2));
				
				list.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
