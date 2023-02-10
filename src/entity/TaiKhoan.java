package entity;

import java.util.Objects;

public class TaiKhoan {
	private String taikhoan;
	private String matkhau;
	private NhanVien nv;
	
	public TaiKhoan() {
		// TODO Auto-generated constructor stub
	}
	
	public TaiKhoan(String taikhoan, String matkhau) {
		super();
		this.taikhoan = taikhoan;
		this.matkhau = matkhau;
	}
	
	public TaiKhoan(String taikhoan, String matkhau, NhanVien nv) {
		super();
		this.taikhoan = taikhoan;
		this.matkhau = matkhau;
		this.nv = nv;
	}



	public String getTaikhoan() {
		return taikhoan;
	}
	public void setTaikhoan(String taikhoan) {
		this.taikhoan = taikhoan;
	}
	public String getMatkhau() {
		return matkhau;
	}
	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}
	public NhanVien getNv() {
		return nv;
	}
	public void setNv(NhanVien nv) {
		this.nv = nv;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(taikhoan);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(taikhoan, other.taikhoan);
	}

	@Override
	public String toString() {
		return "TaiKhoan [taikhoan=" + taikhoan + ", matkhau=" + matkhau + ", nv=" + nv + "]";
	}
	
}
