package entity;

public class CTPhieuDatTruoc {
	private int soluongsanpham;
	private double thanhtien;
	private SanPham sp;
	private PhieuDatTruoc pdt;
	
	public CTPhieuDatTruoc() {
		// TODO Auto-generated constructor stub
	}

	public CTPhieuDatTruoc(int soluongsanpham, double thanhtien, SanPham sp, PhieuDatTruoc pdt) {
		super();
		this.soluongsanpham = soluongsanpham;
		this.thanhtien = thanhtien;
		this.sp = sp;
		this.pdt = pdt;
	}
	
	public CTPhieuDatTruoc(int soluongsanpham, PhieuDatTruoc pdt , SanPham sp) {
		super();
		this.soluongsanpham = soluongsanpham;
		this.thanhtien = soluongsanpham*sp.getGiadonvi();
		this.sp = sp;
		this.pdt = pdt;
	}

	public void setThanhtien(double thanhtien) {
		this.thanhtien = thanhtien;
	}



	public PhieuDatTruoc getPdt() {
		return pdt;
	}

	public void setPdt(PhieuDatTruoc pdt) {
		this.pdt = pdt;
	}

	public int getSoluongsanpham() {
		return soluongsanpham;
	}

	public void setSoluongsanpham(int soluongsanpham) {
		this.soluongsanpham = soluongsanpham;
	}

	public double getThanhtien() {
		return thanhtien;
	}

	public SanPham getSp() {
		return sp;
	}

	public void setSp(SanPham sp) {
		this.sp = sp;
	}






	@Override
	public String toString() {
		return "CTPhieuDatTruoc [soluongsanpham=" + soluongsanpham + ", thanhtien=" + thanhtien + ", sp=" + sp
				+ ", pdt=" + pdt + "]";
	}
	
	
	
	
}
