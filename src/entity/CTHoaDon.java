package entity;

public class CTHoaDon {
	private double thanhtien;
	private int soluongsanpham;
	private SanPham sp;
	private HoaDon hd;
	
	
	public CTHoaDon() {
		// TODO Auto-generated constructor stub
	}
	

	public CTHoaDon(int soluongsanpham,HoaDon hd, SanPham sp) {
		
		this.soluongsanpham = soluongsanpham;
		this.sp = sp;
		this.hd=hd;
		this.thanhtien = soluongsanpham*sp.getGiadonvi();
	}
	
	public CTHoaDon(Double thanhtien,int soluongsanpham,HoaDon hd, SanPham sp) {
		
		this.soluongsanpham = soluongsanpham;
		this.sp = sp;
		this.hd=hd;
		this.thanhtien=thanhtien;
	}


	@Override
	public String toString() {
		return "CTHoaDon [thanhtien=" + thanhtien + ", soluongsanpham=" + soluongsanpham + ", sp=" + sp + ", hd=" + hd
				+ "]";
	}


	public double getThanhtien() {
		return thanhtien;
	}


	public void setThanhtien(double thanhtien) {
		this.thanhtien = thanhtien;
	}


	public int getSoluongsanpham() {
		return soluongsanpham;
	}


	public void setSoluongsanpham(int soluongsanpham) throws Exception {
		if(soluongsanpham >0)
		this.soluongsanpham = soluongsanpham;
		else
			throw new Exception("Số lượng sản phẩm phải lớn hơn không");
	}


	public SanPham getSp() {
		return sp;
	}


	public void setSp(SanPham sp) {
		this.sp = sp;
	}


	public HoaDon getHd() {
		return hd;
	}


	public void setHd(HoaDon hd) {
		this.hd = hd;
	}
}
