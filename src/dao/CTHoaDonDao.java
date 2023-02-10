package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;
import entity.CTHoaDon;
import entity.HoaDon;
import entity.SanPham;

public class CTHoaDonDao {
	private ArrayList<CTHoaDon> list;

	/**
	 * 
	 * thêm CTHoaDon
	 */
	public boolean themCTHoaDon(CTHoaDon p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " insert into CTHoaDon values(?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDouble(1, p.getThanhtien());
			stmt.setInt(2, p.getSoluongsanpham());
			stmt.setString(3, p.getHd().getMahoadon());
			stmt.setString(4, p.getSp().getMasanpham());

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * xóa CTHoaDon
	 */
	public boolean xoaCTHoaDon(String mahd, String masp) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " delete from CTHoaDon where mahoadon = ? and masanpham = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mahd);
			stmt.setString(2, masp);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public boolean xoaAllCTHoaDon(String mahd) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = "delete from CTHoaDon where mahoadon = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mahd);

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * sửa CTHoaDon
	 */
	public boolean suaCTHoaDon(CTHoaDon p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " update CTHoaDon set thanhtien = ? , soluongsanpham = ? where where mahoadon = ? and masanpham = ? ";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setDouble(1, p.getThanhtien());
			stmt.setInt(2, p.getSoluongsanpham());
			stmt.setString(3, p.getHd().getMahoadon());
			stmt.setString(4, p.getSp().getMasanpham());

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/*
	 * lấy danh sách tất cả các CTHoaDon
	 */
	public ArrayList<CTHoaDon> layDsCTHoaDon() {
		list = new ArrayList<CTHoaDon>();
		Database.getInstance();
		Connection con = Database.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from CTHoaDon");
			while (rs.next()) {

				CTHoaDon kh = new CTHoaDon(rs.getDouble(1), rs.getInt(2), new HoaDon(rs.getString(3)),
						new SanPham(rs.getString(4)));

				list.add(kh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<CTHoaDon> layDsCTHoaDonTheoHoaDon(String mahoadon) {
		list = new ArrayList<CTHoaDon>();
		Database.getInstance();
		Connection con = Database.getConnection();
		try {
			String sql = " select *  from CTHoaDon where mahoadon = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mahoadon);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				CTHoaDon hd = new CTHoaDon(rs.getDouble(1), rs.getInt(2), new HoaDon(rs.getString(3)),
						new SanPham(rs.getString(4)));

				list.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public CTHoaDon timCTHoaDon(String mahd, String masp) {
		Database.getInstance();
		Connection con = Database.getConnection();
		CTHoaDon hd = null;

		try {
			String sql = " select *  from CTHoaDon where mahoadon = ? and masanpham= ? ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mahd);
			stmt.setString(2, masp);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				hd = new CTHoaDon(rs.getDouble(1), rs.getInt(2), new HoaDon(rs.getString(3)),
						new SanPham(rs.getString(4)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hd;
	}

	// ===== 10h29 12/8/2021 PM

	/**
	 * Lấy danh sách số lượng sản phẩm theo thời gian tháng và năm
	 * 
	 * @param thang
	 * @param nam
	 * @return
	 */
	public List<Double> layDsSoLuongSanPhamTheoTheoThoiGian(String thang, String nam) {
		Database.getInstance();
		Connection con = Database.getConnection();
		List<Double> dssp = new ArrayList<Double>();

		try {
			Statement stmt = con.createStatement();
			String sql = "select sum(CTHoaDon.soluongsanpham) from CTHoaDon join HoaDon on CTHoaDon.mahoadon=HoaDon.mahoadon\n"
					+ "where datepart(mm,ngaylaphoadon) like " + thang + " and datepart(yyyy,ngaylaphoadon) like " + nam
					+ "\n"
					+ "group by masanpham,datepart(day,ngaylaphoadon),datepart(mm,ngaylaphoadon), datepart(yyyy,ngaylaphoadon)\n"
					+ "order by sum(CTHoaDon.soluongsanpham) desc";

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				dssp.add(rs.getDouble(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dssp;
	}

	/**
	 * Lấy danh sách sản phẩm đã bán theo thời gian
	 * 
	 * @param thang
	 * @param nam
	 * @return
	 */
	public List<String> layDsSanPhamDaBanTheoThoiGian(String thang, String nam) {
		Database.getInstance();
		Connection con = Database.getConnection();
		List<String> dssp = new ArrayList<String>();

		try {
			Statement stmt = con.createStatement();
			String sql = "select tensanpham from CTHoaDon join HoaDon on CTHoaDon.mahoadon=HoaDon.mahoadon join SanPham on SanPham.masanpham = CTHoaDon.masanpham\n"
					+ "where datepart(mm,ngaylaphoadon) like " + thang + " and datepart(yyyy,ngaylaphoadon) like " + nam
					+ "\n"
					+ "group by CTHoaDon.masanpham,datepart(day,ngaylaphoadon),datepart(mm,ngaylaphoadon), datepart(yyyy,ngaylaphoadon), tensanpham\n"
					+ "order by sum(CTHoaDon.soluongsanpham) desc\n";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				dssp.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dssp;
	}

	public List<Double> layDsDoanhThuCuaSanPhamTheoThoiGian(String thang, String nam) {
		Database.getInstance();
		Connection con = Database.getConnection();
		List<Double> dssp = new ArrayList<Double>();

		try {
			Statement stmt = con.createStatement();
			String sql = "select sum(thanhtien) from CTHoaDon join HoaDon on CTHoaDon.mahoadon=HoaDon.mahoadon\n"
					+ "where datepart(mm,ngaylaphoadon) like " + thang + " and datepart(yyyy,ngaylaphoadon) like " + nam
					+ "\n"
					+ "group by masanpham,datepart(day,ngaylaphoadon),datepart(mm,ngaylaphoadon), datepart(yyyy,ngaylaphoadon)\n"
					+ "order by sum(CTHoaDon.soluongsanpham) desc";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				dssp.add(rs.getDouble(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dssp;
	}

	/**
	 * Lấy danh sách thông tin gồm mã, tên, số lượng bán ra của sản phẩm
	 * 
	 * @param thang
	 * @param nam
	 * @return
	 */
	public ArrayList<SanPham> layDSThongTinSanPhamDaBanTheoThoiGian(String thang, String nam) {
		Database.getInstance();
		Connection con = Database.getConnection();
		ArrayList<SanPham> dssp = new ArrayList<SanPham>();

		try {
			Statement stmt = con.createStatement();
			String sql = " select CTHoaDon.masanpham,tensanpham,sum(CTHoaDon.soluongsanpham) from CTHoaDon join HoaDon on CTHoaDon.mahoadon=HoaDon.mahoadon join SanPham on SanPham.masanpham = CTHoaDon.masanpham \n"
					+ " where datepart(mm,ngaylaphoadon) like " + thang + " and datepart(yyyy,ngaylaphoadon) like " + nam
					+ " group by CTHoaDon.masanpham, tensanpham\n"
					+ " order by sum(CTHoaDon.soluongsanpham) desc";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				dssp.add(new SanPham(rs.getString(1), rs.getString(2), rs.getInt(3)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dssp;
	}

}
