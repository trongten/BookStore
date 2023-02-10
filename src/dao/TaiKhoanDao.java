package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.Database;
import entity.TaiKhoan;
/**
 * Lớp DAO tài khoản
 * @author Phan Võ Trọng - Võ Phước Lưu
 *
 */
public class TaiKhoanDao {
	private ArrayList<TaiKhoan> list;
	/**
	 * thêm TaiKhoan
	 * 
	 * @param TaiKhoan return kq
	 */
	public boolean themTaiKhoan(TaiKhoan p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " alter table NhanVien nocheck constraint all "
					+ " alter table TaiKhoan nocheck constraint all  " + " insert into TaiKhoan values(?,?)"
					+ " alter table NhanVien check constraint all " + " alter table TaiKhoan check constraint all  ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getTaikhoan());
			stmt.setString(2, p.getMatkhau());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * xóa TaiKhoan
	 * 
	 * @return kq
	 */
	public boolean xoaTaiKhoan(String p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " delete from TaiKhoan where tentaikhoan = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p);

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * sửa Mật khẩu
	 * 
	 * @return kq
	 */
	public boolean suaMatKhau(TaiKhoan p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " update TaiKhoan set matkhau = ? where tentaikhoan = ?";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(2, p.getTaikhoan());
			stmt.setString(1, p.getMatkhau());

			n = stmt.executeUpdate();
		} catch (Exception e) {
			n=0;
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * lấy danh sách tất cả các TaiKhoan
	 * 
	 * @return danh sách tài khoản
	 */
	public ArrayList<TaiKhoan> layDsTaiKhoan() {
		list = new ArrayList<TaiKhoan>();
		Database.getInstance();
		Connection con = Database.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from TaiKhoan");
			while (rs.next()) {

				TaiKhoan kh = new TaiKhoan(rs.getString(1), rs.getString(2));
				list.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean resetMatKhauNhanVien(String manv) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " update TaiKhoan set matkhau = '1234' where tentaikhoan = ?";//set về mật khẩu mặc định là 1234
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(1, manv);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			n=0;
			e.printStackTrace();
		}
		return n > 0;
	}
}
