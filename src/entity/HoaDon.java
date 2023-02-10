package entity;


import java.sql.Timestamp;

import dao.NhanVienDao;
/**
 * Lớp Thực thể của hóa đơn
 * @author Phan Võ Trọng
 *
 */
public class HoaDon {
	private String mahoadon;
	private Timestamp ngaylaphoadon;//Dùng thay kiểu Date
	private double tongtienhoadon;//Tiền hóa đơn sẽ được tính trên giao diện
	private NhanVien nhanvien;
	private KhachHang khachhang;
	
	public HoaDon() {
	}


	public HoaDon(String mahoadon, Timestamp ngaylaphoadon, double tongtienhoadon, NhanVien nhanvien,KhachHang khachhang) {
		super();
		this.mahoadon = mahoadon;
		this.ngaylaphoadon = ngaylaphoadon;
		this.tongtienhoadon = tongtienhoadon;
		this.nhanvien = nhanvien;
		this.khachhang=khachhang;
	}
	
	
	public HoaDon(String mahoadon, Timestamp ngaylaphoadon, double tongtienhoadon) {
		super();
		this.mahoadon = mahoadon;
		this.ngaylaphoadon = ngaylaphoadon;
		this.tongtienhoadon = getTongtienhoadon();
	}

	public KhachHang getKhachhang() {
		return khachhang;
	}


	public void setKhachhang(KhachHang khachhang) {
		this.khachhang = khachhang;
	}


	public HoaDon(String mahoadon) {
		super();
		this.mahoadon = mahoadon;
	}


	public HoaDon(String mahd, String tennhanvien, Timestamp ngayLap) {
		this.mahoadon = mahd;
		this.tongtienhoadon = getTongtienhoadon();
		this.nhanvien = new NhanVienDao().timNhanVienTheoTen(tennhanvien);
	}


	public String getMahoadon() {
		return mahoadon;
	}


	public void setMahoadon(String mahoadon) {
		this.mahoadon = mahoadon;
	}


	public Timestamp getNgaylaphoadon() {
		return ngaylaphoadon;
	}


	public void setNgaylaphoadon(Timestamp ngaylaphoadon) {
		this.ngaylaphoadon = ngaylaphoadon;
	}			


	public double getTongtienhoadon() {
		return tongtienhoadon;
	}


	public void setTongtienhoadon(double tongtienhoadon) {
		this.tongtienhoadon = tongtienhoadon;
	}


	public NhanVien getNhanvien() {
		return nhanvien;
	}


	public void setNhanvien(NhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}


	@Override
	public String toString() {
		return "HoaDon [mahoadon=" + mahoadon + ", ngaylaphoadon=" + ngaylaphoadon + ", tongtienhoadon="
				+ tongtienhoadon + ", nhanvien=" + nhanvien + "]";
	}
	
	
	
}
