package entity;

import java.util.Objects;

public class SanPham {

	private String masanpham;
	private String tensanpham;
	private int soluong;
	private double giadonvi;
	private double gianhap;
	private String anhsanpham;
	private String xuatxu;
	private String chatlieu;
	private int sotrang;
	private String tentacgia;
	private String nhaxuatban;
	private String nhacungcap;
	private Loai loai;

	public SanPham(String masanpham, String tensanpham, int soluong, double giadonvi, double gianhap, String anhsanpham,
			String xuatxu, String chatlieu, int sotrang, String tentacgia, String nhaxuatban, String nhacungcap,
			Loai loai) {
		super();
		this.masanpham = masanpham;
		this.tensanpham = tensanpham;
		this.soluong = soluong;
		this.giadonvi = giadonvi;
		this.gianhap = gianhap;
		this.anhsanpham = anhsanpham;
		this.xuatxu = xuatxu;
		this.chatlieu = chatlieu;
		this.sotrang = sotrang;
		this.tentacgia = tentacgia;
		this.nhaxuatban = nhaxuatban;
		this.nhacungcap = nhacungcap;
		this.loai = loai;
	}
	
	public SanPham(String masanpham, String tensanpham, int soluong, double gianhap, String anhsanpham,Loai loai) {
		super();
		this.masanpham = masanpham;
		this.tensanpham = tensanpham;
		this.soluong = soluong;
		this.giadonvi = gianhap*0.1 + gianhap;
		this.gianhap = gianhap;
		this.anhsanpham = anhsanpham;
		this.loai = loai;
	}

	public SanPham() {
		// TODO Auto-generated constructor stub
	}

	public SanPham(String masanpham) {
		this.masanpham = masanpham;
	}

	
	//== dùng cho danh sách sản phẩm ở bảng Thống kê sản phẩm
	public SanPham(String masanpham, String tensanpham, int soluong) {
		this.masanpham = masanpham;
		this.tensanpham = tensanpham;
		this.soluong = soluong;
		}

	public Loai getLoai() {
		return loai;
	}

	public void setLoai(Loai loai) {
		this.loai = loai;
	}

	public void setGiadonvi(double giadonvi) {
		this.giadonvi = giadonvi;
	}

	public String getMasanpham() {
		return masanpham;
	}

	public void setMasanpham(String masanpham) {
		this.masanpham = masanpham;
	}

	public String getTensanpham() {
		return tensanpham;
	}

	public void setTensanpham(String tensanpham) {
		this.tensanpham = tensanpham;
	}

	public double getGiadonvi() {
		return gianhap * 0.1 + gianhap;
	}

	public double getGianhap() {
		return gianhap;
	}

	public void setGianhap(double gianhap) {
		this.gianhap = gianhap;
	}

	public String getChatlieu() {
		return chatlieu;
	}

	public void setChatlieu(String chatlieu) {
		this.chatlieu = chatlieu;
	}

	public String getNhaxuatban() {
		return nhaxuatban;
	}

	public void setNhaxuatban(String nhaxuatban) {
		this.nhaxuatban = nhaxuatban;
	}

	public int getSotrang() {
		return sotrang;
	}

	public void setSotrang(int sotrang) {
		this.sotrang = sotrang;
	}

	public String getTentacgia() {
		return tentacgia;
	}

	public void setTentacgia(String tentacgia) {
		this.tentacgia = tentacgia;
	}

	public String getXuatxu() {
		return xuatxu;
	}

	public void setXuatxu(String xuatxu) {
		this.xuatxu = xuatxu;
	}

	public String getAnhsanpham() {
		return anhsanpham;
	}

	public void setAnhsanpham(String anhsanpham) {
		this.anhsanpham = anhsanpham;
	}

	public String getNhacungcap() {
		return nhacungcap;
	}

	public void setNhacungcap(String nhacungcap) {
		this.nhacungcap = nhacungcap;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(masanpham);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(masanpham, other.masanpham);
	}

	@Override
	public String toString() {
		return "SanPham [masanpham=" + masanpham + ", tensanpham=" + tensanpham + ", soluong=" + soluong + ", giadonvi="
				+ giadonvi + ", gianhap=" + gianhap + ", anhsanpham=" + anhsanpham + ", xuatxu=" + xuatxu
				+ ", chatlieu=" + chatlieu + ", sotrang=" + sotrang + ", tentacgia=" + tentacgia + ", nhaxuatban="
				+ nhaxuatban + ", nhacungcap=" + nhacungcap + ", loai=" + loai + "]";
	}


}
