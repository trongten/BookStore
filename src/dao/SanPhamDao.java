package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.Database;
import entity.Loai;
import entity.SanPham;

/**
 * Lớp DAO Sản phẩm
 * 
 * @author Phan Võ Trọng - Nguyễn Phạm Công Nhật
 *
 */
public class SanPhamDao {
	private ArrayList<SanPham> list;

	/**
	 * Thêm sách vào trong cơ sở dữ liệu
	 * 
	 * @param p
	 * @return
	 */
	public boolean themSach(SanPham p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " insert into SanPham values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getMasanpham());
			stmt.setString(2, p.getTensanpham());
			stmt.setInt(3, p.getSoluong());
			stmt.setDouble(4, p.getGiadonvi());
			stmt.setDouble(5, p.getGianhap());
			stmt.setString(6, p.getAnhsanpham());
			stmt.setString(7, null);
			stmt.setString(8, null);
			stmt.setInt(9, p.getSotrang());
			stmt.setString(10, p.getTentacgia());
			stmt.setString(11, p.getNhaxuatban());
			stmt.setString(12, null);
			stmt.setString(13, p.getLoai().getMaloai());

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * Thêm văn phòng phẩm vào trong cơ sở dữ liệu
	 * 
	 * @param p
	 * @return
	 */
	public boolean themVanPhongPham(SanPham p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " insert into SanPham values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getMasanpham());
			stmt.setString(2, p.getTensanpham());
			stmt.setInt(3, p.getSoluong());
			stmt.setDouble(4, p.getGiadonvi());
			stmt.setDouble(5, p.getGianhap());
			stmt.setString(6, p.getAnhsanpham());
			stmt.setString(7, p.getXuatxu());
			stmt.setString(8, p.getChatlieu());
			stmt.setInt(9, 0);
			stmt.setString(10, null);
			stmt.setString(11, null);
			stmt.setString(12, p.getNhacungcap());
			stmt.setString(13, p.getLoai().getMaloai());

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * Xóa Sản phẩm có mã sản phẩm p
	 * 
	 * @param p
	 * @return kq
	 */
	public boolean xoaSanPham(String p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " delete from SanPham where masanpham = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p);

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * Sửa thông tin sản phẩm
	 * 
	 * @param p
	 * @return
	 */
	public boolean suaSanPham(SanPham p) {
		Database.getInstance();
		Connection con = Database.getConnection();
		int n = 0;
		try {
			String sql = " update SanPham set tensanpham = ? , soluong = ?, giadonvi= ? , gianhap = ?, anhsanpham = ? , xuatxu = ?, chatlieu = ?, sotrang = ? , tentacgia = ?, nhaxuatban = ? , nhacungcap = ? , maloai = ? where masanpham = ?";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(13, p.getMasanpham());
			stmt.setString(1, p.getTensanpham());
			stmt.setInt(2, p.getSoluong());
			stmt.setDouble(3, p.getGiadonvi());
			stmt.setDouble(4, p.getGianhap());
			stmt.setString(5, p.getAnhsanpham());
			stmt.setString(6, p.getXuatxu());
			stmt.setString(7, p.getChatlieu());
			stmt.setInt(8, p.getSotrang());
			stmt.setString(9, p.getTentacgia());
			stmt.setString(10, p.getNhaxuatban());
			stmt.setString(11, p.getNhacungcap());
			stmt.setString(12, p.getLoai().getMaloai());

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * Lấy danh sách toàn bộ sản phẩm
	 * 
	 * @return
	 */
	public ArrayList<SanPham> layDsSanPham() {
		list = new ArrayList<SanPham>();
		Database.getInstance();
		Connection con = Database.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from SanPham");
			while (rs.next()) {

				SanPham kh = new SanPham(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4),
						rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9),
						rs.getString(10), rs.getString(11), rs.getString(12), new Loai(rs.getString(13)));

				list.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Lấy danh sách tất cả sản phẩm CÒN HÀNG
	 * 
	 * @return
	 */
	public ArrayList<SanPham> layDsSanPhamConHang() {
		list = new ArrayList<SanPham>();
		Database.getInstance();
		Connection con = Database.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from SanPham where soluong != 0");
			while (rs.next()) {

				SanPham sp = new SanPham(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4),
						rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9),
						rs.getString(10), rs.getString(11), rs.getString(12), new Loai(rs.getString(13)));

				list.add(sp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Lấy danh sách tất cả sản phẩm HẾT HÀNG
	 * 
	 * @return danhsachsanpham
	 */
	public ArrayList<SanPham> layDsSanPhamHetHang() {
		list = new ArrayList<SanPham>();
		Database.getInstance();
		Connection con = Database.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from SanPham where soluong = 0");
			while (rs.next()) {

				SanPham sp = new SanPham(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4),
						rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9),
						rs.getString(10), rs.getString(11), rs.getString(12), new Loai(rs.getString(13)));

				list.add(sp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Lấy danh sách văn phòng phẩm
	 * 
	 * @return
	 */
	public ArrayList<SanPham> layDsSVPP() {
		list = new ArrayList<SanPham>();
		Database.getInstance();
		Connection con = Database.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from SanPham where maloai = 'VANP' ");
			while (rs.next()) {

				SanPham kh = new SanPham(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4),
						rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9),
						rs.getString(10), rs.getString(11), rs.getString(12), new Loai(rs.getString(13)));

				list.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Lấy danh sách sách
	 * 
	 * @return
	 */
	public ArrayList<SanPham> layDsSach() {
		list = new ArrayList<SanPham>();
		Database.getInstance();
		Connection con = Database.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from SanPham where maloai = 'SACH' ");
			while (rs.next()) {

				SanPham kh = new SanPham(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4),
						rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9),
						rs.getString(10), rs.getString(11), rs.getString(12), new Loai(rs.getString(13)));

				list.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Tìm một sản phẩm theo mã sản phẩm đó
	 * 
	 * @param masp
	 * @return
	 */
	public SanPham timSanPham(String masp) {
		Database.getInstance();
		Connection con = Database.getConnection();
		SanPham sp = null;
		try {
			String sql = " select *  from SanPham where masanpham = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, masp);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				sp = new SanPham(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10),
						rs.getString(11), rs.getString(12), new Loai(rs.getString(13)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sp;
	}

	/**
	 * Tìm một sản phẩm theo tất cả thông tin của sản phẩm đó
	 * 
	 * @param masp
	 * @param tensp
	 * @param giadv
	 * @param gian
	 * @param chatlieu
	 * @param xuatxu
	 * @return
	 */
	public ArrayList<SanPham> timSanPhamNangCaoVPP(String masp, String tensp, String giadv, String gian,
			String chatlieu, String xuatxu) {
		list = new ArrayList<SanPham>();
		Database.getInstance();
		Connection con = Database.getConnection();

		SanPham sp = null;
		try {

			String sql = " select * from SanPham where masanpham like ? and tensanpham like ? and giadonvi like ? and gianhap like ? and chatlieu like ? and xuatxu like ? and maloai like 'VANP' ";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, masp);
			stmt.setString(2, tensp);

			stmt.setString(3, giadv);
			stmt.setString(4, gian);
			stmt.setString(5, chatlieu);
			stmt.setString(6, xuatxu);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				sp = new SanPham(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10),
						rs.getString(11), rs.getString(12), new Loai(rs.getString(13)));
				list.add(sp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Tìm sách theo cách thông tin của sách đó
	 * 
	 * @param masp
	 * @param tensp
	 * @param giadv
	 * @param sotrang
	 * @param tentacgia
	 * @param nhaxb
	 * @return
	 */
	public ArrayList<SanPham> timSanPhamNangCaoSach(String masp, String tensp, String giadv, String sotrang,
			String tentacgia, String nhaxb) {
		list = new ArrayList<SanPham>();
		Database.getInstance();
		Connection con = Database.getConnection();

		SanPham sp = null;
		try {

			String sql = " select * from SanPham where  masanpham like ? and tensanpham like ? and giadonvi like ? and sotrang like ? "
					+ "and tentacgia like ? and nhaxuatban like ? and maloai like 'SACH' ";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, masp);
			stmt.setString(2, tensp);
			stmt.setString(3, giadv);
			stmt.setString(4, sotrang);
			stmt.setString(5, tentacgia);
			stmt.setString(6, nhaxb);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				sp = new SanPham(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10),
						rs.getString(11), rs.getString(12), new Loai(rs.getString(13)));
				list.add(sp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Tìm sản phẩm theo các thông tin của sản phẩm đó
	 * 
	 * @param masp
	 * @param tensp
	 * @param nhaxb
	 * @param sotrang
	 * @param tentacgia
	 * @param nhacc
	 * @param chatlieu
	 * @param xuatxu
	 * @return
	 */
	public ArrayList<SanPham> timSanPhamNangCao(String masp, String tensp, String nhaxb, String sotrang,
			String tentacgia, String nhacc, String chatlieu, String xuatxu) {
		list = new ArrayList<SanPham>();
		Database.getInstance();
		Connection con = Database.getConnection();

		SanPham sp = null;
		try {

			String sql = "select * from SanPham "
					+ " where masanpham like ? and tensanpham like ? and (nhaxuatban like ? or nhaxuatban is null) and (sotrang like ? or sotrang is null) "
					+ " and (tentacgia like ? or tentacgia is null) and (nhacungcap like ? or nhacungcap is NULL) and (chatlieu like ? or chatlieu is null) and (xuatxu like ? or xuatxu is null) ";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, masp);
			stmt.setString(2, tensp);
			stmt.setString(3, nhaxb);
			stmt.setString(4, sotrang);
			stmt.setString(5, tentacgia);
			stmt.setString(6, nhacc);
			stmt.setString(7, chatlieu);
			stmt.setString(8, xuatxu);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				sp = new SanPham(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10),
						rs.getString(11), rs.getString(12), new Loai(rs.getString(13)));
				list.add(sp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Tìm sản phẩm CÒN HÀNG theo các thông tin của sản phẩm đó
	 * 
	 * @param masp
	 * @param tensp
	 * @param nhaxb
	 * @param sotrang
	 * @param tentacgia
	 * @param nhacc
	 * @param chatlieu
	 * @param xuatxu
	 * @return
	 */
	public ArrayList<SanPham> timSanPhamNangCaoConHang(String masp, String tensp, String nhaxb, String sotrang,
			String tentacgia, String nhacc, String chatlieu, String xuatxu) {
		list = new ArrayList<SanPham>();
		Database.getInstance();
		Connection con = Database.getConnection();

		SanPham sp = null;
		try {

			String sql = "select * from SanPham "
					+ " where masanpham like ? and tensanpham like ? and (nhaxuatban like ? or nhaxuatban is null) and (sotrang like ? or sotrang is null) "
					+ " and (tentacgia like ? or tentacgia is null) and (nhacungcap like ? or nhacungcap is NULL) and (chatlieu like ? or chatlieu is null) and (xuatxu like ? or xuatxu is null) and soluong != 0";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, masp);
			stmt.setString(2, tensp);
			stmt.setString(3, nhaxb);
			stmt.setString(4, sotrang);
			stmt.setString(5, tentacgia);
			stmt.setString(6, nhacc);
			stmt.setString(7, chatlieu);
			stmt.setString(8, xuatxu);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				sp = new SanPham(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10),
						rs.getString(11), rs.getString(12), new Loai(rs.getString(13)));
				list.add(sp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int capNhatSoLuong(String masp, int soluong) {
		int n = 0;
		Database.getInstance();
		Connection con = Database.getConnection();
		try {

			String sql = "update SanPham set soluong = soluong + ? " + " where masanpham = ? ";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(2, masp);
			stmt.setInt(1, soluong);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}
	/**
	 * Lấy danh sách sản phẩm sắp hết hàng (số lượng <=10)
	 * @return
	 */
	public ArrayList<SanPham> layDsSanPhamSapHetHang() {
		list = new ArrayList<SanPham>();
		Database.getInstance();
		Connection con = Database.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from SanPham where soluong <= 10 and soluong > 0");
			while (rs.next()) {
				SanPham sp = new SanPham(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4),
						rs.getDouble(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9),
						rs.getString(10), rs.getString(11), rs.getString(12), new Loai(rs.getString(13)));

				list.add(sp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
