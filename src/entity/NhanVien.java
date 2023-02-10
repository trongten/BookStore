package entity;


import java.sql.Date;

public class NhanVien {
	private String manhanvien;
	private String hoten;
	private Date ngaysinh;
	private String diachi;
	private String cmnd;
	private boolean trangthailamviec;
	private boolean gioitinh;
	private int ca;
	private NhanVien quanly;
	private String sdt;
	public NhanVien() {
		// TODO Auto-generated constructor stub
	}

	

	public NhanVien(String manhanvien, String hoten, Date ngaysinh, String diachi, String cmnd, boolean chucvu,
			boolean gioitinh, int ca, NhanVien quanly, String sdt) {
		
		this.manhanvien = manhanvien;
		this.hoten = hoten;
		this.ngaysinh = ngaysinh;
		this.diachi = diachi;
		this.cmnd = cmnd;
		this.trangthailamviec = chucvu;
		this.gioitinh = gioitinh;
		this.ca = ca;
		this.quanly = quanly;
		this.sdt = sdt;
	}



	public NhanVien(String manhanvien) {
		super();
		this.manhanvien = manhanvien;
	}



	public NhanVien(String manhanvien, String hoten) {
		this.hoten = hoten;
		this.manhanvien = manhanvien;
	}



	public String getManhanvien() {
		return manhanvien;
	}



	public void setManhanvien(String manhanvien) {
		this.manhanvien = manhanvien;
	}



	public String getHoten() {
		return hoten;
	}



	public void setHoten(String hoten) {
		this.hoten = hoten;
	}



	public Date getNgaysinh() {
		return ngaysinh;
	}



	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}



	public String getDiachi() {
		return diachi;
	}



	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}



	public String getCmnd() {
		return cmnd;
	}



	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}



	public boolean isTrangthailamviec() {
		return trangthailamviec;
	}



	public void setTrangthailamviec(boolean chucvu) {
		this.trangthailamviec = chucvu;
	}



	public boolean isGioitinh() {
		return gioitinh;
	}



	public void setGioitinh(boolean gioitinh) {
		this.gioitinh = gioitinh;
	}



	public int getCa() {
		return ca;
	}



	public void setCa(int ca) {
		this.ca = ca;
	}



	public NhanVien getQuanly() {
		return quanly;
	}



	public void setQuanly(NhanVien quanly) {
		this.quanly = quanly;
	}



	public String getSdt() {
		return sdt;
	}



	public void setSdt(String sdt) {
		this.sdt = sdt;
	}



	@Override
	public String toString() {
		return "NhanVien [manhanvien=" + manhanvien + ", hoten=" + hoten + ", ngaysinh=" + ngaysinh + ", diachi="
				+ diachi + ", cmnd=" + cmnd + ", trangthailamviec=" + trangthailamviec + ", gioitinh=" + gioitinh + ", ca=" + ca
				+ ", quanly=" + quanly + ", sdt=" + sdt + "]";
	}










	







	
	
	
}
