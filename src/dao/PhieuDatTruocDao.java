package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.Database;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatTruoc;

/**
 * Lớp DAO của Phiếu đặt trước
 * 
 * @author Phan Võ Trọng - Võ Phước Lưu
 *
 */
public class PhieuDatTruocDao {
	private ArrayList<PhieuDatTruoc> list;

	/**
	 * Thêm phiếu đặt trước
	 * 
	 * @param p
	 * @return ketqua
	 */
	public boolean themPhieuDatTruoc(PhieuDatTruoc p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " insert into PhieuDatTruoc values(?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getMaphieudat());
			stmt.setTimestamp(2, p.getNgaylap());
			stmt.setTimestamp(3, p.getNgaynhanhang());
			stmt.setDouble(4, p.getTongtienphieudat());
			stmt.setString(5, p.getNv().getManhanvien());
			stmt.setString(6, p.getKh().getMakh());
			stmt.setString(7, p.getTrangthai());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * Xóa phiếu đặt trước
	 * 
	 * @param p
	 * @return kết quả
	 */
	public boolean xoaPhieuDatTruoc(String p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " alter Table PhieuDatTruoc nocheck constraint all\n"
					+ "alter Table CTPhieuDatTruoc nocheck constraint all "
					+ "delete from PhieuDatTruoc where maphieudat = ? "
					+ "alter Table PhieuDatTruoc check constraint all\n"
					+ "alter Table CTPhieuDatTruoc check constraint all";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p);

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * Sửa thông tin của một phiếu đặt trước
	 * 
	 * @param p
	 * @return
	 */
	public boolean suaPhieuDatTruoc(PhieuDatTruoc p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " update PhieuDatTruoc set ngaynhanhang = ? , manhanvien = ? , makhachhang = ? , trangthai = ?  where maphieudat = ?";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(5, p.getMaphieudat());
			stmt.setTimestamp(1, p.getNgaynhanhang());
			stmt.setString(2, p.getNv().getManhanvien());
			stmt.setString(3, p.getKh().getMakh());
			stmt.setString(4, p.getTrangthai());

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * Sửa tổng tiền của một phiếu đặt trước
	 * 
	 * @param ma
	 * @param tien
	 * @return
	 */
	public boolean suaTienPhieuDatTruoc(String ma, Double tien) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " update PhieuDatTruoc set tongtienphieudat = ? where maphieudat = ? ";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setDouble(1, tien);
			stmt.setString(2, ma);

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * lấy danh sách tất cả các PhieuDatTruoc
	 * 
	 * @return
	 */
	public ArrayList<PhieuDatTruoc> layDsPhieuDatTruoc() {
		list = new ArrayList<PhieuDatTruoc>();
		Database.getInstance();
		Connection con = Database.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from PhieuDatTruoc");
			while (rs.next()) {

				PhieuDatTruoc kh = new PhieuDatTruoc(rs.getString(1), rs.getTimestamp(2), rs.getTimestamp(3),
						rs.getDouble(4), new NhanVien(rs.getString(5)), new KhachHang(rs.getString(6)),
						rs.getString(7));

				list.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * lấy danh sách tất cả các PhieuDatTruoc chưa nhận hàng
	 * 
	 * @return
	 */
	public ArrayList<PhieuDatTruoc> layDsPhieuDatTruocChuaNhanHang() {
		list = new ArrayList<PhieuDatTruoc>();
		Database.getInstance();
		Connection con = Database.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("Select * from PhieuDatTruoc where datediff(MINUTE,getDate(),ngaynhanhang) >= 0");
			while (rs.next()) {

				PhieuDatTruoc kh = new PhieuDatTruoc(rs.getString(1), rs.getTimestamp(2), rs.getTimestamp(3),
						rs.getDouble(4), new NhanVien(rs.getString(5)), new KhachHang(rs.getString(6)),
						rs.getString(7));

				list.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * lấy danh sách tất cả các PhieuDatTruoc đã nhận hàng
	 * 
	 * @return
	 */
	public ArrayList<PhieuDatTruoc> layDsPhieuDatTruocDaNhanHang() {
		list = new ArrayList<PhieuDatTruoc>();
		Database.getInstance();
		Connection con = Database.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("Select * from PhieuDatTruoc where datediff(MINUTE,getDate(),ngaynhanhang) < 0");
			while (rs.next()) {

				PhieuDatTruoc kh = new PhieuDatTruoc(rs.getString(1), rs.getTimestamp(2), rs.getTimestamp(3),
						rs.getDouble(4), new NhanVien(rs.getString(5)), new KhachHang(rs.getString(6)),
						rs.getString(7));

				list.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Tìm phiếu đặt trước theo hóa đơn
	 * 
	 * @param mahd
	 * @return
	 */
	public PhieuDatTruoc timPhieuDatTruoc(String mahd) {
		Database.getInstance();
		Connection con = Database.getConnection();

		PhieuDatTruoc hd = null;
		try {
			String sql = "select * from PhieuDatTruoc where maphieudat = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mahd);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				hd = new PhieuDatTruoc(rs.getString(1), rs.getTimestamp(2), rs.getTimestamp(3), rs.getDouble(4),
						new NhanVien(rs.getString(5)), new KhachHang(rs.getString(6)), rs.getString(7));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hd;
	}

	/**
	 * Tìm kiếm phiếu đặt trước theo các trường mã phiếu đăt, tên nhân viên, ngày
	 * lập phiếu và ngày nhận
	 * 
	 * @param mapd
	 * @param tennv
	 * @param ngaylap
	 * @param ngaynhan
	 * @return danh sách phiếu đặt tìm thấy
	 */
	public ArrayList<PhieuDatTruoc> timPhieuDatTruocNangCao(String mapd, String tennv, Date ngaylap, Date ngaynhan) {
		list = new ArrayList<PhieuDatTruoc>();
		Database.getInstance();
		Connection con = Database.getConnection();
		try {

			String sql = "select * from PhieuDatTruoc pdt join NhanVien nv on pdt.manhanvien = nv.manhanvien where maphieudat like ? and hoten like  ? and ngaylap like ? and ngaynhanhang like ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mapd);
			stmt.setString(2, tennv);
			stmt.setString(3, ngaylap == null ? "%" : "%" + ngaylap.toString().trim() + "%");
			stmt.setString(4, ngaynhan == null ? "%" : "%" + ngaynhan.toString().trim() + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				PhieuDatTruoc pd = new PhieuDatTruoc(rs.getString(1), rs.getTimestamp(2), rs.getTimestamp(3),
						rs.getDouble(4), new NhanVien(rs.getString(5)), new KhachHang(rs.getString(6)),
						rs.getString(7));

				list.add(pd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Lấy danh sách phiếu đặt theo ngày của một nhân viên
	 * 
	 * @param ngay
	 * @param manhanvien
	 * @return
	 */
	public ArrayList<PhieuDatTruoc> layDsPhieuDatTheoThoiGian(int ngay, String manhanvien) {
		list = new ArrayList<PhieuDatTruoc>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from PhieuDatTruoc where datediff(day,ngaylap,getdate()) <= "
					+ ngay + " and manhanvien = '" + manhanvien + "'");
			while (rs.next()) {
				PhieuDatTruoc kh = new PhieuDatTruoc(rs.getString(1), rs.getTimestamp(2), rs.getTimestamp(3),
						rs.getDouble(4), new NhanVien(rs.getString(5)), new KhachHang(rs.getString(6)),
						rs.getString(7));
				list.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Lấy danh sách phiếu đặt theo mã nhân viên
	 * 
	 * @param manhanvien
	 * @return
	 */
	public ArrayList<PhieuDatTruoc> layDsPhieuDat(String manhanvien) {
		list = new ArrayList<PhieuDatTruoc>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from PhieuDatTruoc where manhanvien = " + "'" + manhanvien + "'");
			while (rs.next()) {

				PhieuDatTruoc hd = new PhieuDatTruoc(rs.getString(1), rs.getTimestamp(2), rs.getTimestamp(3),
						rs.getDouble(4), new NhanVien(rs.getString(5)), new KhachHang(rs.getString(6)),
						rs.getString(7));

				list.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Lấy danh sách phiếu đặt theo thời gian (ngay)
	 * 
	 * @param ngay
	 * @return
	 */
	public ArrayList<PhieuDatTruoc> layDsPhieuDatTheoThoiGian(int ngay) {
		list = new ArrayList<PhieuDatTruoc>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from PhieuDatTruoc where datediff(day,ngaylap,getdate()) <= "+ ngay);
			while (rs.next()) {
				PhieuDatTruoc kh = new PhieuDatTruoc(rs.getString(1), rs.getTimestamp(2), rs.getTimestamp(3),
						rs.getDouble(4), new NhanVien(rs.getString(5)), new KhachHang(rs.getString(6)),
						rs.getString(7));
				list.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
