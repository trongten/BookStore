package entity;

import java.sql.Timestamp;

public class PhieuDatTruoc {


	private String maphieudat;
	private Timestamp ngaylap;
	private Timestamp ngaynhanhang;
	private double tongtienphieudat;
	private NhanVien nv;
	private KhachHang kh;
	private String trangthai;
	
	
	

	@Override
	public String toString() {
		return "PhieuDatTruoc [maphieudat=" + maphieudat + ", ngaylap=" + ngaylap + ", ngaynhanhang=" + ngaynhanhang
				+ ", tongtienphieudat=" + tongtienphieudat + ", nv=" + nv + ", kh=" + kh + ", trangthai=" + trangthai + "]";
	}

	public String getMaphieudat() {
		return maphieudat;
	}

	public void setMaphieudat(String maphieudat) {
		this.maphieudat = maphieudat;
	}

	public Timestamp getNgaylap() {
		return ngaylap;
	}

	public void setNgaylap(Timestamp ngaylap) {
		this.ngaylap = ngaylap;
	}

	public Timestamp getNgaynhanhang() {
		return ngaynhanhang;
	}

	public void setNgaynhanhang(Timestamp ngaynhanhang) {
		this.ngaynhanhang = ngaynhanhang;
	}

	public double getTongtienphieudat() {
		return tongtienphieudat;
	}

	public void settongtienphieudat(double tongtienphieudat) {
		this.tongtienphieudat = tongtienphieudat;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

	public KhachHang getKh() {
		return kh;
	}

	public void setKh(KhachHang kh) {
		this.kh = kh;
	}

	public PhieuDatTruoc(String maphieudat, Timestamp ngaylap, Timestamp ngaynhanhang, double tongtienphieudat) {
		super();
		this.maphieudat = maphieudat;
		this.ngaylap = ngaylap;
		this.ngaynhanhang = ngaynhanhang;
		this.tongtienphieudat = tongtienphieudat;
	}

	
	
	public PhieuDatTruoc() {
		
	}

	public PhieuDatTruoc(String maphieudat, Timestamp ngaylap, Timestamp ngaynhanhang, double tongtienphieudat, NhanVien nv,
			KhachHang kh) {
		super();
		this.maphieudat = maphieudat;
		this.ngaylap = ngaylap;
		this.ngaynhanhang = ngaynhanhang;
		this.tongtienphieudat = tongtienphieudat;
		this.nv = nv;
		this.kh = kh;
		this.trangthai = "Chưa nhận hàng";
	}
	
	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public PhieuDatTruoc(String maphieudat, Timestamp ngaylapPhieuDatTruoc, Timestamp ngaynhanhang2, double tongtienphieudat, NhanVien nv,
			KhachHang kh,String trangthai) {
		super();
		this.maphieudat = maphieudat;
		this.ngaylap = ngaylapPhieuDatTruoc;
		this.ngaynhanhang = ngaynhanhang2;
		this.tongtienphieudat = tongtienphieudat;
		this.nv = nv;
		this.kh = kh;
		this.trangthai = trangthai;
	}
	
	public PhieuDatTruoc(String maphieudat) {
		super();
		this.maphieudat = maphieudat;
		
	}
	
	
	
}
