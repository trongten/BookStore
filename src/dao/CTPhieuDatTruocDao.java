package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.Database;
import entity.CTPhieuDatTruoc;
import entity.PhieuDatTruoc;
import entity.SanPham;

public class CTPhieuDatTruocDao {

	private ArrayList<CTPhieuDatTruoc> list;

	/**
	 * Thêm Chi tiết phiếu đặt trước vào trong cơ sỡ dữ liệu
	 * 
	 * @param p
	 * @return
	 */
	public boolean themCTPhieuDatTruoc(CTPhieuDatTruoc p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " insert into CTPhieuDatTruoc values(?,?,?,?)";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getPdt().getMaphieudat());
			stmt.setDouble(2, p.getThanhtien());
			stmt.setString(3, p.getSp().getMasanpham());
			stmt.setInt(4, p.getSoluongsanpham());

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * Xóa chi tiêt phiếu đặt trước
	 * 
	 * @param mahd
	 * @param masp
	 * @return
	 */
	public boolean xoaCTPhieuDatTruoc(String mahd, String masp) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " delete from CTPhieuDatTruoc where maphieudat = ? and masanpham = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mahd);
			stmt.setString(1, masp);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * Xóa toàn bộ chi tiết phiểu đặt trước có mã là @param mahd
	 * 
	 * @param mahd
	 * @return kết quả
	 */
	public boolean xoaAllCTPhieuDatTruoc(String mahd) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " delete from CTPhieuDatTruoc where maphieudat = ? ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mahd);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * Sửa phiếu đặt trước
	 * 
	 * @param p
	 * @return kết quả
	 */
	public boolean suaCTPhieuDatTruoc(CTPhieuDatTruoc p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " update CTPhieuDatTruoc set thanhtien = ? , soluongsanpham = ? where where maphieudat = ? and masanpham = ? ";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setDouble(1, p.getThanhtien());
			stmt.setInt(2, p.getSoluongsanpham());
			stmt.setString(3, p.getPdt().getMaphieudat());
			stmt.setString(4, p.getSp().getMasanpham());

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * Lấy danh sách chi tiết phiếu đặt trước
	 * 
	 * @return danhsach
	 */
	public ArrayList<CTPhieuDatTruoc> layDsCTPhieuDatTruoc() {
		list = new ArrayList<CTPhieuDatTruoc>();
		Database.getInstance();
		Connection con = Database.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from CTPhieuDatTruoc");
			while (rs.next()) {

				CTPhieuDatTruoc kh = new CTPhieuDatTruoc(rs.getInt(4), rs.getDouble(2), new SanPham(rs.getString(3)),
						new PhieuDatTruoc(rs.getString(1)));

				list.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Lấy danh sách phiếu đặt trước theo maphieudat
	 * 
	 * @param maphieudat
	 * @return danhsach
	 */
	public ArrayList<CTPhieuDatTruoc> layDsCTPhieuDatTruocTheoPhieuDat(String maphieudat) {
		list = new ArrayList<CTPhieuDatTruoc>();
		Database.getInstance();
		Connection con = Database.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from CTPhieuDatTruoc c join PhieuDatTruoc h\n"
					+ "on c.maphieudat = h.maphieudat\n" + "where h.maphieudat = '" + maphieudat + "'");
			while (rs.next()) {

				CTPhieuDatTruoc pd = new CTPhieuDatTruoc(rs.getInt(4), rs.getDouble(2), new SanPham(rs.getString(3)),
						new PhieuDatTruoc(rs.getString(5)));
				list.add(pd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
