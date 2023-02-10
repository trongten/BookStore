package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.Database;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

/**
 * Lớp DAO của hóa đơn
 * 
 * @author Phan Võ Trọng
 *
 */
public class HoaDonDao {
	private ArrayList<HoaDon> list;
	
	/**
	 * thêm HoaDon
	 * 
	 * @return ketqua
	 */
	public boolean themHoaDon(HoaDon p) {
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			String sql = " insert into HoaDon values(?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getMahoadon());
			stmt.setTimestamp(3, p.getNgaylaphoadon());
			stmt.setDouble(2, p.getTongtienhoadon());
			stmt.setString(4, p.getNhanvien().getManhanvien());
			stmt.setString(5, p.getKhachhang().getMakh());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * Thêm một hóa đơn không có khách hàng
	 * 
	 * @param p
	 * @return
	 */
	public boolean themHoaDonKhongKhachHang(HoaDon p) {
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			String sql = " insert into HoaDon values(?,?,?,?,null)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getMahoadon());
			stmt.setTimestamp(3, p.getNgaylaphoadon());
			stmt.setDouble(2, p.getTongtienhoadon());
			stmt.setString(4, p.getNhanvien().getManhanvien());

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * xóa HoaDon
	 */
	public boolean xoaHoaDon(String p) {
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			String sql = " delete from HoaDon where mahoadon = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, p);

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * sửa HoaDon
	 */
	public boolean suaHoaDon(HoaDon p) {
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			String sql = " update HoaDon set  tongtienhoadon = ? , ngaylaphoadon = ?, manhanvien= ? , makhachhang = ? where mahoadon = ?";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(5, p.getMahoadon());
			stmt.setTimestamp(2, p.getNgaylaphoadon());
			stmt.setDouble(1, p.getTongtienhoadon());
			stmt.setString(3, p.getNhanvien().getManhanvien());
			stmt.setString(4, p.getKhachhang().getMakh());
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * Sửa hóa đơn
	 * 
	 * @param ma
	 * @param tien
	 * @return ketqua
	 */
	public boolean suaTienHoaDon(String ma, Double tien) {
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		int n = 0;
		try {
			String sql = " update HoaDon set  tongtienhoadon = ? where mahoadon = ?";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setString(2, ma);
			stmt.setDouble(1, tien);

			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	/**
	 * lấy danh sách tất cả các HoaDon theo nhân viên lập
	 * 
	 * @return dshd
	 */
	public ArrayList<HoaDon> layDsHoaDon(String manhanvien) {
		list = new ArrayList<HoaDon>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from HoaDon where manhanvien =" + "'" + manhanvien + "'");
			while (rs.next()) {

				HoaDon kh = new HoaDon(rs.getString(1), rs.getTimestamp(3), rs.getDouble(2),
						new NhanVien(rs.getString(4)), new KhachHang(rs.getString(5)));

				list.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Lấy toàn bộ danh sách hóa đơn
	 * 
	 * @return danhsachhoadon
	 */
	public ArrayList<HoaDon> layDsHoaDon() {
		list = new ArrayList<HoaDon>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from HoaDon");
			while (rs.next()) {

				HoaDon kh = new HoaDon(rs.getString(1), rs.getTimestamp(3), rs.getDouble(2),
						new NhanVien(rs.getString(4)), new KhachHang(rs.getString(5)));

				list.add(kh);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Tìm hóa đơn theo mã
	 * 
	 * @param mahd
	 * @return hoadon
	 */
	public HoaDon timHoaDon(String mahd) {
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();

		HoaDon hd = null;
		try {
			String sql = "select * from HoaDon where mahoadon = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mahd);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				hd = new HoaDon(rs.getString(1), rs.getTimestamp(3), rs.getDouble(2), new NhanVien(rs.getString(4)),
						new KhachHang(rs.getString(5)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hd;
	}

	/**
	 * Tìm kiếm hóa đơn theo nhiều thông tin
	 * 
	 * @param mahd
	 * @param tennv
	 * @param ngaylap
	 * @return
	 */
	public ArrayList<HoaDon> timHoaDonNangCao(String mahd, String tennv, Date ngaylap) {

		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		list = new ArrayList<HoaDon>();
		HoaDon hd = null;
		try {

			String sql = "select * from HoaDon hd join NhanVien nv on hd.manhanvien = nv.manhanvien where hd.mahoadon like ? and hoten like ? and hd.ngaylaphoadon like ?";

			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mahd);
			stmt.setString(2, tennv);
			stmt.setString(3, ngaylap == null ? "%" : "%" + ngaylap.toString().trim() + "%");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				hd = new HoaDon(rs.getString(1), rs.getTimestamp(3), rs.getDouble(2), new NhanVien(rs.getString(4)),
						new KhachHang(rs.getString(5)));
				list.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * Lấy danh sách hóa đơn theo ngày của một nhân viên
	 * 
	 * @param ngay
	 * @param manhanvien
	 * @return
	 */
	public ArrayList<HoaDon> layDsHoaDonTheoThoiGian(int ngay, String manhanvien) {
		list = new ArrayList<HoaDon>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from HoaDon where datediff(day,ngaylaphoadon,getdate()) <= "
					+ ngay + " and manhanvien = '" + manhanvien + "'");
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString(1), rs.getTimestamp(3), rs.getDouble(2),
						new NhanVien(rs.getString(4)), new KhachHang(rs.getString(5)));

				list.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<HoaDon> layDsHoaDonTheoThoiGian(int ngay) {
		list = new ArrayList<HoaDon>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("Select * from HoaDon where datediff(day,ngaylaphoadon,getdate()) <= " + ngay);
			while (rs.next()) {
				HoaDon hd = new HoaDon(rs.getString(1), rs.getTimestamp(3), rs.getDouble(2),
						new NhanVien(rs.getString(4)), new KhachHang(rs.getString(5)));

				list.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<String> layDsHoaDonCuaNhanVien() {
		ArrayList<String> ds = new ArrayList<String>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select manhanvien from HoaDon group by manhanvien");
			while (rs.next()) {
				ds.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	public List<Double> layDsSoLuongHoaDonCuaNhanVien() {
		List<Double> ds = new ArrayList<Double>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select count(manhanvien) from HoaDon group by manhanvien");
			while (rs.next()) {
				ds.add((double) rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	/**
	 * Lấy danh sách tổng doanh thu của một nhân viên
	 * 
	 * @return
	 */
	public List<Double> layDsDoanhThuHoaDonCuaNhanVien() {
		List<Double> ds = new ArrayList<Double>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select sum(tongtienhoadon) from HoaDon group by manhanvien");
			while (rs.next()) {
				ds.add((double) rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	/**
	 * Lấy danh sách ngày có doanh thu của một nhân viên theo tháng/năm
	 * 
	 * @param manv
	 * @param thang
	 * @param nam
	 * @return danh sách <String> của ngày
	 */
	public List<String> layDsNgayCoDoanhThuCuaNhanVienTheoThoiGian(String manv, String thang, String nam) {
		List<String> ds = new ArrayList<String>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select datepart(dd,ngaylaphoadon),datepart(month,ngaylaphoadon),datepart(yyyy,ngaylaphoadon) from HoaDon \n"
							+ "where datepart(month,ngaylaphoadon) like " + thang
							+ " and datepart(yyyy,ngaylaphoadon) like " + nam + " and manhanvien='" + manv + "'\n"
							+ "group by datepart(dd,ngaylaphoadon),datepart(month,ngaylaphoadon),datepart(yyyy,ngaylaphoadon) \n");
			while (rs.next()) {
				ds.add(rs.getString(1) + "/" + rs.getString(2) + "/" + rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	/**
	 * Lấy danh sách ngày có doanh thu của một nhân viên theo tháng/năm
	 * 
	 * @param manv
	 * @param thang
	 * @param nam
	 * @return danh sách <String> của ngày
	 */
	public List<String> layDsNgayCoDoanhThuTheoThoiGian(String thang, String nam) {
		List<String> ds = new ArrayList<String>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select datepart(dd,ngaylaphoadon),datepart(month,ngaylaphoadon),datepart(yyyy,ngaylaphoadon) from HoaDon \n"
							+ "where datepart(month,ngaylaphoadon) like " + thang
							+ " and datepart(yyyy,ngaylaphoadon) like " + nam + ""
							+ "group by datepart(dd,ngaylaphoadon),datepart(month,ngaylaphoadon),datepart(yyyy,ngaylaphoadon) \n");
			while (rs.next()) {
				ds.add(rs.getString(1) + "/" + rs.getString(2) + "/" + rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	/**
	 * Lấy danh sách doanh thu của một nhân viên theo tháng/năm
	 * 
	 * @param manv
	 * @param thang
	 * @param nam
	 * @return
	 */
	public List<Double> layDsDoanhThuCuaNhanVienTheoThoiGian(String manv, String thang, String nam) {
		List<Double> ds = new ArrayList<Double>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select sum(tongtienhoadon) from HoaDon \n"
					+ "where datepart(month,ngaylaphoadon) like " + thang + " and datepart(yyyy,ngaylaphoadon) like "
					+ nam + " and manhanvien='" + manv + "'\n"
					+ "group by datepart(dd,ngaylaphoadon),datepart(month,ngaylaphoadon),datepart(yyyy,ngaylaphoadon) \n");
			while (rs.next()) {
				ds.add(rs.getDouble(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	/**
	 * Lấy danh sách doanh thu theo tháng/năm
	 * 
	 * @param thang
	 * @param nam
	 * @return
	 */
	public List<Double> layDsDoanhThuTheoThoiGian(String thang, String nam) {
		List<Double> ds = new ArrayList<Double>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select sum(tongtienhoadon) from HoaDon \n"
					+ "where datepart(month,ngaylaphoadon) like " + thang + " and datepart(yyyy,ngaylaphoadon) like "
					+ nam + ""
					+ "group by datepart(dd,ngaylaphoadon),datepart(month,ngaylaphoadon),datepart(yyyy,ngaylaphoadon) \n");
			while (rs.next()) {
				ds.add(rs.getDouble(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	/**
	 * Lấy danh sách hóa đơn theo khoảng thời gian tháng.
	 * 
	 * @param thang
	 * @param nam
	 * @return
	 */
	public ArrayList<HoaDon> layDsHoaDonTheoKhoangThoiGian(String thang, String nam) {
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		HoaDon hd = null;
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM HoaDon WHERE datepart(month,ngaylaphoadon) like " + thang
					+ "   and datepart(year,ngaylaphoadon) like " + nam);
			while (rs.next()) {
				hd = new HoaDon(rs.getString(1), rs.getTimestamp(3), rs.getDouble(2), new NhanVien(rs.getString(4)),
						new KhachHang(rs.getString(5)));
				ds.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	/**
	 * lấy tất cả các mã nhân viên theo các hóa đơn theo tháng năm.
	 * 
	 * @param thang
	 * @param nam
	 * @return
	 */
	public ArrayList<String> layDsMaNVTheoKhoangThoiGian(String thang, String nam) {
		ArrayList<String> ds = new ArrayList<String>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT manhanvien FROM HoaDon WHERE datepart(month,ngaylaphoadon) like "
					+ thang + "   and datepart(year,ngaylaphoadon) like " + nam + " group by manhanvien");
			while (rs.next()) {
				ds.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	/**
	 * Lấy danh sách hóa đơn theo sản phẩm theo
	 * 
	 * @return
	 */
	public ArrayList<HoaDon> layDsHoaDonCuaSanPhamTheoThoiGian(String masanpham, String thang, String nam) {
		ArrayList<HoaDon> ds = new ArrayList<HoaDon>();
		HoaDon hd = null;
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select hd.mahoadon "
					+ " from CTHoaDon ct join SanPham sp on ct.masanpham = sp.masanpham join HoaDon hd on ct.mahoadon = hd.mahoadon "
					+ " WHERE datepart(month,ngaylaphoadon) like " + thang + " and datepart(year,ngaylaphoadon) like "
					+ nam + " and ct.masanpham = '" + masanpham + "'");
			while (rs.next()) {
				if (rs.getString(1) != null) {
					hd = new HoaDonDao().timHoaDon(rs.getString(1));
					ds.add(hd);
				} else {
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	// Test
	/**
	 * 
	 * @param masanpham
	 * @param thang
	 * @param nam
	 * @return
	 */
	public ArrayList<Integer> layDsSoLuongSanPhamBanRaTheoThoiGian(String masanpham, String thang, String nam) {
		ArrayList<Integer> ds = new ArrayList<Integer>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select soluongsanpham "
					+ " from CTHoaDon ct join SanPham sp on ct.masanpham = sp.masanpham join HoaDon hd on ct.mahoadon = hd.mahoadon "
					+ " WHERE datepart(month,ngaylaphoadon) like " + thang + " and datepart(year,ngaylaphoadon) like "
					+ nam + " and ct.masanpham = '" + masanpham + "'");
			while (rs.next()) {
				ds.add(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	/**
	 * Lấy danh sách năm lập hóa đơn (Phục vụ cho thống kê sản phẩm)
	 * 
	 * @return
	 */
	public ArrayList<String> layDsNamLapHoaDon() {
		ArrayList<String> ds = new ArrayList<String>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(
					"select datepart(yyyy,ngaylaphoadon) from HoaDon\n" + "group by datepart(yyyy,ngaylaphoadon)");
			while (rs.next()) {
				ds.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	/**
	 * Lấy danh sách tháng lập hóa đơn theo năm(Phục vụ cho thống kê sản phẩm)
	 * 
	 * @return
	 */
	public ArrayList<String> layDsThangLapHoaDon(String nam) {
		ArrayList<String> ds = new ArrayList<String>();
		@SuppressWarnings("static-access")
		Connection con = Database.getInstance().getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select datepart(mm,ngaylaphoadon) from HoaDon\n"
					+ "where datepart(yyyy,ngaylaphoadon) like " + nam + " group by datepart(mm,ngaylaphoadon)");
			while (rs.next()) {
				ds.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
}
