package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.Database;
import entity.KhachHang;
/**
 * Lớp DAO Nhân Viên
 * @author ADMIN
 *
 */
public class KhachHangDao {

	private ArrayList<KhachHang> list;

	/**
	 * Thêm một khách hàng
	 * @param p
	 * @return
	 */
	public boolean themKH(KhachHang p) {
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			String sql = " insert into KhachHang values(?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getMakh());
			stmt.setString(2, p.getTenkhachhang());
			stmt.setString(3, p.getSodienthoai());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean xoaKH(String p) {
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			String sql = " delete from KhachHang where makhachhang = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p);

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean suaKH(KhachHang p) {
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			String sql = " update KhachHang set tenkhachhang = ?, sodienthoai = ? where makhachhang = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(3, p.getMakh());
			stmt.setString(1, p.getTenkhachhang());
			stmt.setString(2, p.getSodienthoai());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public ArrayList<KhachHang> layDsKhachHang() {
		list = new ArrayList<KhachHang>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from KhachHang");
			while (rs.next()) {

				KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3));

				list.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public KhachHang timKhachHang(String makh) {
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		list = new ArrayList<KhachHang>();
		KhachHang kh = null;
		try {
			String sql = " select *  from KhachHang where makhachhang = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, makh);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return kh;
	}
	
	
	public KhachHang timKhachHangTheoSDT(String sdt) {
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		list = new ArrayList<KhachHang>();
		KhachHang kh = null;
		try {
			String sql = " select *  from KhachHang where sodienthoai = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, sdt);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return kh;
	}

	public ArrayList<KhachHang> timKhachHangNangCao(String makh, String tenkh, String sodienthoai) {
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		list = new ArrayList<KhachHang>();
		KhachHang kh = null;
		try {
			String sql = " select * from KhachHang where makhachhang like ? and tenkhachhang like ? and sodienthoai like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, makh);
			stmt.setString(2, tenkh);
			stmt.setString(3, sodienthoai);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3));
				list.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public ArrayList<KhachHang> timKhachHangTheoTuKhoa(String tukhoa) {
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		list = new ArrayList<KhachHang>();
		KhachHang kh = null;
		try {
			String sql = " select * from KhachHang where makhachhang like ? or tenkhachhang like ? or sodienthoai like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, "%" + tukhoa + "%");
			stmt.setString(2, "%" + tukhoa + "%");
			stmt.setString(3, "%" + tukhoa + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3));
				list.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
