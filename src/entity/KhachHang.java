package entity;

import java.util.Objects;

public class KhachHang {
	private String makh;
	private String tenkhachhang;
	private String sodienthoai;

	public KhachHang() {
		// TODO Auto-generated constructor stub
	}

	public KhachHang(String tenkhachhang, String sodienthoai) {
		super();
		this.tenkhachhang = tenkhachhang;
		this.sodienthoai = sodienthoai;
	}
	
	

	public KhachHang(String makh) {

		this.makh = makh;
	}

	public KhachHang(String makh, String tenkhachhang, String sodienthoai) {
		super();
		this.makh = makh;
		this.tenkhachhang = tenkhachhang;
		this.sodienthoai = sodienthoai;
	}

	public String getMakh() {
		return makh;
	}

	public void setMakh(String makh) {
		this.makh = makh;
	}

	public String getTenkhachhang() {
		return tenkhachhang;
	}

	public void setTenkhachhang(String tenkhachhang) {
		this.tenkhachhang = tenkhachhang;
	}

	public String getSodienthoai() {
		return sodienthoai;
	}

	public void setSodienthoai(String sodienthoai) {
		this.sodienthoai = sodienthoai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(makh);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(makh, other.makh);
	}

	@Override
	public String toString() {
		return "KhachHang [makh=" + makh + ", tenkhachhang=" + tenkhachhang + ", sodienthoai=" + sodienthoai + "]";
	}

}
