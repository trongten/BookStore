package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.Database;
import entity.NhanVien;

/**
 * Lớp DAO Nhân viên
 * 
 * @author Phan Võ Trọng - Võ Phước Lưu
 * 
 */
public class NhanVienDao {
	private ArrayList<NhanVien> list;

	/**
	 * Thêm một nhân viên vào cửa hàng
	 * 
	 * @param p
	 * @return
	 */
	public boolean themNhanVien(NhanVien p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " alter table NhanVien nocheck constraint all "
					+ " alter table TaiKhoan nocheck constraint all  "
					+ " insert into NhanVien values(?,?,?,?,?,?,?,?,?,?) "
					+ " alter table NhanVien check constraint all " + " alter table TaiKhoan check constraint all  ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getManhanvien());
			stmt.setString(2, p.getHoten());
			stmt.setDate(3, (Date) p.getNgaysinh());
			stmt.setString(4, p.getDiachi());
			stmt.setString(5, p.getCmnd());
			stmt.setBoolean(6, p.isTrangthailamviec());
			stmt.setBoolean(7, p.isGioitinh());
			stmt.setInt(8, p.getCa());
			stmt.setString(9, p.getQuanly().getManhanvien());
			stmt.setString(10, p.getSdt());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * Chỉnh thuộc tính của nhân viên thành đã nghỉ việc, và đổi mật khẩu tài khoản
	 * 
	 * @param p
	 * @return
	 */
	public boolean xoaNhanVien(String p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		double random_int = Math.floor(Math.random() * (99999999 - 10000000 + 1) + 10000000);//mật khẩu mã hóa
		int n = 0;
		try {
			String sql = " update NhanVien set trangthailamviec ='False' where manhanvien = ?"
					+ " update TaiKhoan set matkhau = ? where tentaikhoan = ? ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p);
			stmt.setString(2, String.valueOf(random_int));
			stmt.setString(3, p);

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * Thay đổi thuộc tính trạng thái làm việc của nhân viên thành true (Đi làm)
	 * 
	 * @param p
	 * @return
	 */
	public boolean ThemNhanVienDaCo(String p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " update NhanVien set trangthailamviec ='True' where manhanvien = ?"
					+ " update TaiKhoan set matkhau = ? where tentaikhoan = ? ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p);
			stmt.setString(2, String.valueOf(1234));
			stmt.setString(3, p);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * Sửa của thông tin Nhân Viên
	 * 
	 * @param p
	 * @return
	 */
	public boolean suaNhanVien(NhanVien p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " update NhanVien set hoten = ? , ngaysinh = ?, diachi = ?, sochungminh = ?,"
					+ "gioitinh = ?, ca  = ?, maquanly = ?, sodienthoai = ?  where manhanvien = ?";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(9, p.getManhanvien());
			stmt.setString(1, p.getHoten());
			stmt.setDate(2, (Date) p.getNgaysinh());
			stmt.setString(3, p.getDiachi());
			stmt.setString(4, p.getCmnd());

			stmt.setBoolean(5, p.isGioitinh());
			stmt.setInt(6, p.getCa());
			stmt.setString(7, p.getQuanly().getManhanvien());
			stmt.setString(8, p.getSdt());

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * Lấy danh sách nhân viên
	 * 
	 * @return
	 */
	public ArrayList<NhanVien> layDsNhanVien() {
		list = new ArrayList<NhanVien>();
		Database.getInstance();
		Connection con = Database.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from NhanVien");
			while (rs.next()) {

				NhanVien kh = new NhanVien(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4),
						rs.getString(5), rs.getBoolean(6), rs.getBoolean(7), rs.getInt(8),
						new NhanVien(rs.getString(9)), rs.getString(10));

				list.add(kh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Tìm nhân viên theo mã nhân viên
	 * 
	 * @param manv
	 * @return
	 */
	public NhanVien timNhanVien(String manv) {
		Database.getInstance();
		Connection con = Database.getConnection();

		NhanVien nv = null;
		try {
			String sql = " select *  from NhanVien where manhanvien = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, manv);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5),
						rs.getBoolean(6), rs.getBoolean(7), rs.getInt(8), new NhanVien(rs.getString(9)),
						rs.getString(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nv;
	}

	/**
	 * Tìm nhân viên theo tên nhân viên
	 * 
	 * @param tennhanvien
	 * @return
	 */
	public NhanVien timNhanVienTheoTen(String tennhanvien) {
		Database.getInstance();
		Connection con = Database.getConnection();

		NhanVien nv = null;
		try {
			String sql = " select *  from NhanVien where tennhanvien = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, tennhanvien);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				nv = new NhanVien(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5),
						rs.getBoolean(6), rs.getBoolean(7), rs.getInt(8), new NhanVien(rs.getString(9)),
						rs.getString(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return nv;
	}

}
