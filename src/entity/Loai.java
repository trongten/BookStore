package entity;

import java.util.Objects;

public class Loai {
	private String tenloai;
	private String maloai;
	public Loai() {
		// TODO Auto-generated constructor stub
	}
	public Loai(String tenloai, String maloai) {
		super();
		this.tenloai = tenloai;
		this.maloai = maloai;
	}
	public Loai( String maloai) {
		
		
		this.maloai = maloai;
	}
	public String getTenloai() {
		return tenloai;
	}
	public void setTenloai(String tenloai) {
		this.tenloai = tenloai;
	}
	public String getMaloai() {
		return maloai;
	}
	public void setMaloai(String maloai) {
		this.maloai = maloai;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maloai);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loai other = (Loai) obj;
		return Objects.equals(maloai, other.maloai);
	}
	@Override
	public String toString() {
		return "Loai [tenloai=" + tenloai + ", maloai=" + maloai + "]";
	}
	
}
