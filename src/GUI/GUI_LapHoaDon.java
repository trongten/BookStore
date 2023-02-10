package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Others.MyButton;
import Others.createHoaDon;
import dao.CTHoaDonDao;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.SanPhamDao;
import entity.CTHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;

/**
 * Giao diện lập hóa đơn
 * 
 * @author Nguyễn Phạm Công Nhật - Phan Võ Trọng - Võ Phước Lưu
 *
 */
public class GUI_LapHoaDon extends JPanel implements TableModelListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel txtTongtien;
	private JLabel txtNgaytao;
	private JTextField txtTensanpham;
	private JTextField txtChatlieu;
	private JTextField txtNhacungcap;
	private JTextField txtXuatxu;
	private JTextField txtNhaxuatban;
	private JSpinner txtSotrang;
	private MyButton btnChuyen;
	private static JTable tblDSSanPham;
	private JTable tblDSCTHoaDon;
	private JTextField txtMaSP;
	private JTextField txtTacGia;
	private static DefaultTableModel dataModel;
	private DefaultTableModel dataModelChitietHD;
	private JSpinner txtSoLuong;
	private MyButton btnInVLu;
	private Date date;
	private String mahoadontam = "";
	int a = 1;
	int stt = 0;
	private JTextField txtSoDienThoaiKhachHang;

	private int soluongcu = 0;
	private int soluongmoi = 0;
	private ArrayList<CTHoaDon> dscthd;

	private String mahd = "";

	private JLabel txtMaHD;

	protected double thue = 0;

	protected double discount = 0;// discount khi người mua có trong danh sách khách hàng

	private int count;// tạo sự kiện double click
	private JTextField txtTienKhachHang;
	private JTextField txtTienThua;

	protected String tencuakhachhang;
	private JTextField txtTienKhachDua;

	private JLabel txtTienThoi;

	/**
	 * Create the panel.
	 */

	@SuppressWarnings({ "serial", "removal" })
	public GUI_LapHoaDon(NhanVien nv) {
		setLayout(new BorderLayout(0, 0));
		setSize(1399, 718);
		JPanel pnlLapHoaDon = new JPanel();
		add(pnlLapHoaDon, BorderLayout.CENTER);
		pnlLapHoaDon.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.75);
		splitPane_1.setBackground(new Color(100, 149, 237));
		pnlLapHoaDon.add(splitPane_1);

		JPanel pnl_NhapHoaDon = new JPanel();
		pnl_NhapHoaDon.setMinimumSize(new Dimension(300, 300));
		pnl_NhapHoaDon.setMaximumSize(new Dimension(375, 375));
		pnl_NhapHoaDon.setBackground(Color.WHITE);
		splitPane_1.setLeftComponent(pnl_NhapHoaDon);
		pnl_NhapHoaDon.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_6.setBackground(new Color(224, 255, 255));
		pnl_NhapHoaDon.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new GridLayout(0, 1, 10, 0));

		JPanel panel_17 = new JPanel();
		panel_6.add(panel_17);
		panel_17.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_3.setBackground(Color.WHITE);
		// panel_6.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		panel_17.add(panel_3);

		JLabel lblNewLabel_14 = new JLabel("Thuế VAT (5%):");
		lblNewLabel_14.setOpaque(true);
		lblNewLabel_14.setBackground(Color.WHITE);
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_3.add(lblNewLabel_14);

		JLabel txtThue = new JLabel("0.0");
		txtThue.setOpaque(true);
		txtThue.setBackground(Color.WHITE);
		txtThue.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtThue.setForeground(Color.RED);
		panel_3.add(txtThue);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_3.add(panel_2);

		JCheckBox chkDiscount = new JCheckBox("Discout (2.5%):");
		chkDiscount.setBorder(null);
		chkDiscount.setBackground(Color.WHITE);
		chkDiscount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		// Sự kiện nhấn vào checkbox discount
		chkDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double tongtien = Double.parseDouble(Tinhtongtien(tblDSCTHoaDon));
				if (chkDiscount.isSelected()) {
					tongtien = tongtien - discount + thue;
					txtTongtien.setText(new DecimalFormat("###,###,###").format((int) Math.floor(tongtien)));
				} else {
					tongtien = tongtien + thue;
					txtTongtien.setText(new DecimalFormat("###,###,###").format((int) Math.floor(tongtien)));
				}
				NumberFormat numberFormatter = new DecimalFormat("###,###,###");
				double tienkhach = 0.0;
				try {
					tienkhach = Double.valueOf(txtTienKhachDua.getText());
				} catch (Exception er) {
					String a = txtTienKhachDua.getText();
					String b = new String();
					for (int j = 0; j < a.split(",").length; j++) {
						b = b + a.split(",")[j];
					}
					tienkhach = Double.valueOf(b);
				}
				String formattedNumber = numberFormatter.format(tienkhach);
				txtTienKhachDua.setText(formattedNumber);
				String a = txtTongtien.getText();
				String b = new String();
				for (int j = 0; j < a.split(",").length; j++) {
					b = b + a.split(",")[j];
				}
				double tongtienmoi = (int) Math.floor(Double.valueOf(b));
				String c = txtTienKhachDua.getText();
				String d = new String();
				for (int j = 0; j < c.split(",").length; j++) {
					d = d + c.split(",")[j];
				}

				double tienkhachdua = 0.0;
				try {
					tienkhachdua = Double.valueOf(d);
				} catch (NumberFormatException er) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập tiền là số, không được nhập chữ. VD:12,000",
							"Thông báo", JOptionPane.ERROR_MESSAGE);
					txtTienKhachDua.grabFocus();
					txtTienKhachDua.requestFocus();
					txtTienKhachDua.setText("0");
				}
				double tienthua = tienkhachdua - tongtienmoi;
				txtTienThoi
						.setText(new DecimalFormat("###,###,###").format((int) Math.floor(Double.valueOf(tienthua))));
				System.out.println(tienthua);
				if (tienthua < 0) {
					txtTienThoi.setForeground(Color.red);
				} else {
					txtTienThoi.setForeground(new Color(34, 139, 34));
				}
			}
		});
		panel_2.add(chkDiscount);

		JLabel txtDiscount = new JLabel("0.0");
		txtDiscount.setOpaque(true);
		txtDiscount.setBackground(Color.WHITE);
		txtDiscount.setForeground(new Color(34, 139, 34));
		panel_3.add(txtDiscount);

		JLabel lblTngTin = new JLabel("Tổng tiền: ");
		lblTngTin.setOpaque(true);
		lblTngTin.setBackground(Color.WHITE);
		panel_3.add(lblTngTin);
		lblTngTin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTngTin.setFont(new Font("Segoe UI", Font.BOLD, 20));

		txtTongtien = new JLabel();
		txtTongtien.setOpaque(true);
		txtTongtien.setBackground(Color.WHITE);
		txtTongtien.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				try {
					thue = Double.parseDouble(Tinhtongtien(tblDSCTHoaDon)) * 0.05;// thuế VAT 5%
					discount = Double.parseDouble(Tinhtongtien(tblDSCTHoaDon)) * 0.025;// discount khi là khách hàng

				} catch (Exception e) {
					thue = 0;
					discount = 0;
				}
				txtThue.setText("+" + String.valueOf(thue));
				txtDiscount.setText("-" + String.valueOf(discount));

			}
		});
		txtTongtien.setText("0.0");
		txtTongtien.setFont(new Font("Segoe UI", Font.BOLD, 20));
		long millis = System.currentTimeMillis();
		date = new Date(millis);
		panel_3.add(txtTongtien);

		JPanel panel_18 = new JPanel();
		panel_18.setBorder(null);
		panel_17.add(panel_18);
		panel_18.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_19 = new JPanel();
		panel_18.add(panel_19);
		panel_19.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel_18 = new JLabel("Tiền của khách hàng đưa: ");
		lblNewLabel_18.setOpaque(true);
		lblNewLabel_18.setBackground(Color.WHITE);
		panel_19.add(lblNewLabel_18);

		txtTienKhachDua = new JTextField();
		txtTienKhachDua.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTienKhachDua.getText().equals("Nhập số tiền khách đưa")) {
					txtTienKhachDua.setText("");
					txtTienKhachDua.setForeground(Color.BLACK);
					txtTienThoi.setText("0");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTienKhachDua.getText().isEmpty()) {
					txtTienKhachDua.setForeground(Color.GRAY);
					txtTienKhachDua.setText("Nhập số tiền khách đưa");
					txtTienThoi.setText("0");
				}
			}
		});
		txtTienKhachDua.setToolTipText("Nhập tiền khách đưa vào dây");
		txtTienKhachDua.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			}
		});
		txtTienKhachDua.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				NumberFormat numberFormatter = new DecimalFormat("###,###,###");
				double tienkhach = 0.0;
				try {
					tienkhach = Double.valueOf(txtTienKhachDua.getText());
				} catch (Exception er) {
					String a = txtTienKhachDua.getText();
					String b = new String();
					for (int j = 0; j < a.split(",").length; j++) {
						b = b + a.split(",")[j];
					}
					tienkhach = Double.valueOf(b);
				}
				String formattedNumber = numberFormatter.format(tienkhach);
				txtTienKhachDua.setText(formattedNumber);
				String a = txtTongtien.getText();
				String b = new String();
				for (int j = 0; j < a.split(",").length; j++) {
					b = b + a.split(",")[j];
				}
				double tongtien = (int) Math.floor(Double.valueOf(b));
				String c = txtTienKhachDua.getText();
				String d = new String();
				for (int j = 0; j < c.split(",").length; j++) {
					d = d + c.split(",")[j];
				}

				double tienkhachdua = 0.0;
				try {
					tienkhachdua = Double.valueOf(d);
				} catch (NumberFormatException er) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập tiền là số, không được nhập chữ. VD:12,000",
							"Thông báo", JOptionPane.ERROR_MESSAGE);
					txtTienKhachDua.grabFocus();
					txtTienKhachDua.requestFocus();
					txtTienKhachDua.setText("0");
				}
				double tienthua = tienkhachdua - tongtien;
				txtTienThoi
						.setText(new DecimalFormat("###,###,###").format((int) Math.floor(Double.valueOf(tienthua))));
				System.out.println(tienthua);
				if (tienthua < 0) {
					txtTienThoi.setForeground(Color.red);
				} else {
					txtTienThoi.setForeground(new Color(34, 139, 34));
				}
			}
		});
		panel_19.add(txtTienKhachDua);

		txtTienKhachDua.setText("Nhập số tiền khách đưa");
		txtTienKhachDua.setColumns(10);

		JPanel panel_20 = new JPanel();
		panel_18.add(panel_20);
		panel_20.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel_19 = new JLabel("Tiền thừa của khách hàng:");
		lblNewLabel_19.setOpaque(true);
		lblNewLabel_19.setBackground(Color.WHITE);
		panel_20.add(lblNewLabel_19);

		txtTienThoi = new JLabel("0");
		txtTienThoi.setFont(new Font("Dialog", Font.BOLD, 18));
		txtTienThoi.setOpaque(true);
		txtTienThoi.setBackground(Color.WHITE);
		panel_20.add(txtTienThoi);
		HoaDonDao hddao = new HoaDonDao();
		ArrayList<HoaDon> hdcuoi = hddao.layDsHoaDon();
		if (hdcuoi.size() != 0) {
			mahoadontam = hdcuoi.get(hdcuoi.size() - 1).getMahoadon()
					.substring(hdcuoi.get(hdcuoi.size() - 1).getMahoadon().length() - 3);
			stt = Integer.parseInt(mahoadontam);
		} else {
			stt = 0;
		}
		mahd = "HD";
		if (stt < 10) {
			mahd = mahd + "00" + stt;
		}
		if (stt >= 10 && stt < 100) {
			mahd = mahd + "0" + stt;
		}
		if (stt >= 100 && stt < 1000) {
			mahd = mahd + stt;
		}

		JPanel panel = new JPanel();
		panel_6.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_13 = new JPanel();
		panel_13.setBackground(Color.WHITE);
		panel.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));

		btnInVLu = new MyButton("In và lưu hóa đơn");
		panel_13.add(btnInVLu);
		btnInVLu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (dscthd.size() != 0) {
					stt++;
					String checkma = mahd.substring(mahd.length() - 3);
					if (stt == Integer.parseInt(checkma)) {
						int i = stt + 1;
						mahd = "HD";
						if (i < 10) {
							mahd = mahd + "00" + i;
						}
						if (i >= 10 && i < 100) {
							mahd = mahd + "0" + i;
						}
						if (i >= 100 && i < 1000) {
							mahd = mahd + i;
						}
					} else {
						int i = stt;
						mahd = "HD";
						if (i < 10) {
							mahd = mahd + "00" + i;
						}
						if (i >= 10 && i < 100) {
							mahd = mahd + "0" + i;
						}
						if (i >= 100 && i < 1000) {
							mahd = mahd + i;
						}
					}
					Timestamp ngaylaphoadon = new Timestamp(System.currentTimeMillis());
					String c = txtTongtien.getText();
					String b = new String();
					for (int i = 0; i < c.split(",").length; i++) {
						b = b + c.split(",")[i];
					}
					double tongtien = Double.parseDouble(b);
					System.out.println("Tổng tiền hóa đơn :" + tongtien);
					HoaDon hoadon = new HoaDon();
					HoaDonDao hddao = new HoaDonDao();
					KhachHangDao khdao = new KhachHangDao();
					KhachHang khachhang = null;
					ArrayList<KhachHang> dskh = khdao.layDsKhachHang();
					CTHoaDonDao cthddao = new CTHoaDonDao();
					boolean check = false;// flag kiểm tra txtSoDienThoaiKhachHang
					String sdtKH = txtSoDienThoaiKhachHang.getText().trim();

					if (sdtKH.equals(""))// khi số điện thoại không nhập
						check = true;
					else if (sdtKH.length() > 0 && sdtKH.length() < 10)// khi số điện thoại có nhập nhưng không hợp lệ
						check = false;
					else // trường hợp khác
						for (KhachHang kh : dskh) {
							if (sdtKH.equals(kh.getSodienthoai()))// khi nhập số điện thoại có trong danh sách
							{
								khachhang = kh;
								check = true;
								break;
							} else// khi nhập số điện thoại hợp lệ
							if (!sdtKH.equals("") && sdtKH.length() == 10 && kh == dskh.get(dskh.size() - 1)) {
								int hoi = JOptionPane.showConfirmDialog(pnl_NhapHoaDon,
										"Khách hàng chưa có bạn có muốn thêm?", "Thông báo", JOptionPane.YES_NO_OPTION);
								if (hoi == JOptionPane.YES_OPTION) {
									tencuakhachhang = JOptionPane.showInputDialog("Vui lòng nhập tên khách hàng: ");

									KhachHang khcuoi = dskh.get(dskh.size() - 1);
									String checkmakh = khcuoi.getMakh().substring(khcuoi.getMakh().length() - 3);
									int sttkh = Integer.parseInt(checkmakh);
									int a = sttkh + 1;
									String makhmoi = "KH";
									if (a < 10) {
										makhmoi = makhmoi + "00" + a;
									}
									if (a >= 10 && a < 100) {
										makhmoi = makhmoi + "0" + a;
									}
									if (a >= 100 && a < 1000) {
										makhmoi = makhmoi + a;
									}

									KhachHang khmoi = new KhachHang(makhmoi, tencuakhachhang, sdtKH);

									new KhachHangDao().themKH(khmoi);
									khachhang = khmoi;
									check = true;
									capnhatDSSPCuaHeThong();
								}
								break;
							}
						}
					if (!check)
						JOptionPane.showMessageDialog(pnl_NhapHoaDon,
								"Số điện thoại không hợp lệ. Vui lòng nhập số điện thoại thực VD:0331234567",
								"Thông báo", JOptionPane.ERROR_MESSAGE);
					else if (khachhang != null)// khách hàng khác rỗng
					{
						hoadon = new HoaDon(mahd, ngaylaphoadon, tongtien, nv, khachhang);

						hddao.themHoaDon(hoadon);
						for (CTHoaDon cthd : dscthd) {
							cthd.setHd(hoadon);
							cthddao.themCTHoaDon(cthd);
						}
						String newSTTHD = mahd.substring(mahd.length() - 3);

						int i = Integer.parseInt(newSTTHD) + 1;
						String mahdmoi = "HD";
						if (i < 10) {
							mahdmoi = mahdmoi + "00" + i;
						}
						if (i >= 10 && i < 100) {
							mahdmoi = mahdmoi + "0" + i;
						}
						if (i >= 100 && i < 1000) {
							mahdmoi = mahdmoi + i;
						}
						txtMaHD.setText(mahdmoi);
						JOptionPane.showMessageDialog(pnl_NhapHoaDon, "Thanh toán thành công");
						capnhatDSSPCuaHeThong();
						dscthd = new ArrayList<CTHoaDon>();
						GUI_DSHoaDon.deleteTableDSHD();
						// in hóa đơn
						try {
							new createHoaDon(new HoaDonDao().timHoaDon(mahd), tblDSCTHoaDon);
						} catch (Exception err) {
							System.err.println(err);
						}
						GUI_DSHoaDon.updateTableDSHDTheoThoiGian(
								new HoaDonDao().layDsHoaDonTheoThoiGian(0, nv.getManhanvien()));
						deleteTableDSCTHoaDon();

					} else {
						hoadon = new HoaDon(mahd, ngaylaphoadon, tongtien, nv, khachhang);
						hddao.themHoaDonKhongKhachHang(hoadon);
						for (CTHoaDon cthd : dscthd) {
							cthd.setHd(hoadon);
							cthddao.themCTHoaDon(cthd);
						}
						String newSTTHD = mahd.substring(mahd.length() - 3);

						int i = Integer.parseInt(newSTTHD) + 1;
						String mahdmoi = "HD";
						if (i < 10) {
							mahdmoi = mahdmoi + "00" + i;
						}
						if (i >= 10 && i < 100) {
							mahdmoi = mahdmoi + "0" + i;
						}
						if (i >= 100 && i < 1000) {
							mahdmoi = mahdmoi + i;
						}

						txtMaHD.setText(mahdmoi);
						JOptionPane.showMessageDialog(pnl_NhapHoaDon, "Thanh toán thành công");

						capnhatDSSPCuaHeThong();
						dscthd = new ArrayList<CTHoaDon>();
						GUI_DSHoaDon.deleteTableDSHD();
						// in hóa đơn
						try {
							new createHoaDon(new HoaDonDao().timHoaDon(mahd), tblDSCTHoaDon);
						} catch (Exception err) {
							System.err.println(err);
						}
						GUI_DSHoaDon.updateTableDSHDTheoThoiGian(
								new HoaDonDao().layDsHoaDonTheoThoiGian(0, nv.getManhanvien()));
						deleteTableDSCTHoaDon();
					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Chưa có sản phẩm nào trong hóa đơn, không thể thanh toán! Vui lòng thêm..", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnInVLu.setRadius(0);
		btnInVLu.setForeground(Color.WHITE);
		btnInVLu.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnInVLu.setColorOver(new Color(34, 139, 34));
		btnInVLu.setColorClick(new Color(0, 100, 0));
		btnInVLu.setColor(new Color(60, 179, 113));
		btnInVLu.setBorder(null);
		btnInVLu.setBackground(new Color(60, 179, 113));
		btnInVLu.setFocusPainted(false);

		MyButton btnXoaRong = new MyButton("Xóa rỗng");
		panel_13.add(btnXoaRong, BorderLayout.EAST);
		btnXoaRong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int y = dataModelChitietHD.getRowCount();

				System.out.println("size " + y);
				if (y > 0) {
					for (int r = 0; r < y; r++) {

						String masp = dscthd.get(r).getSp().getMasanpham();
						SanPham sp = new SanPhamDao().timSanPham(masp);
						SanPhamDao spdao = new SanPhamDao();
						int spm = dscthd.get(r).getSoluongsanpham();
						sp.setSoluong(sp.getSoluong() + spm);

						System.out.println("Số lượng mới của sản phẩm " + masp + " sau khi xóa:" + sp.getSoluong());
						spdao.suaSanPham(sp);
						capnhatDSSPCuaHeThong();
						dataModelChitietHD.removeRow(0);
					}
					dscthd = new ArrayList<CTHoaDon>();
					txtTongtien.setText(Tinhtongtien(tblDSCTHoaDon));
					txtChatlieu.setText("");
					txtMaSP.setText("");
					txtNhacungcap.setText("");
					txtNhaxuatban.setText("");
					txtSoLuong.setValue(1);
					txtSotrang.setValue(0);
					txtNhaxuatban.setText("");
					txtTacGia.setText("");
					txtTensanpham.setText("");
					txtXuatxu.setText("");
					deleteTableDSSanPham();// xóa danh sách sản phẩm
				} else {
					JOptionPane.showMessageDialog(null, "Hóa đơn chưa có sản phẩm nào! Vui lòng thêm", "Thông báo",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnXoaRong.setBackground(Color.WHITE);
		btnXoaRong.setFont(new Font("Tahoma", Font.PLAIN, 16));

		Component verticalStrut_1 = Box.createVerticalStrut(29);
		panel_13.add(verticalStrut_1, BorderLayout.SOUTH);

		Component verticalStrut_2 = Box.createVerticalStrut(13);
		panel_13.add(verticalStrut_2, BorderLayout.NORTH);

		// Panel máy tính
		JPanel panel_14 = new JPanel();
		panel_14.setBackground(Color.WHITE);
		// panel.add(panel_14);
		panel_14.setLayout(new BorderLayout(0, 0));

		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.WHITE);
		panel_14.add(panel_15);
		panel_15.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_16 = new JLabel("Tiền khách hàng đưa:");
		lblNewLabel_16.setFont(new Font("Segoe UI", Font.BOLD, 15));
		panel_15.add(lblNewLabel_16);

		txtTienKhachHang = new JTextField();
		txtTienKhachHang.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_15.add(txtTienKhachHang);
		txtTienKhachHang.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("Tiền thối:");
		lblNewLabel_17.setFont(new Font("Segoe UI", Font.BOLD, 15));
		panel_15.add(lblNewLabel_17);

		txtTienThua = new JTextField();
		txtTienThua.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_15.add(txtTienThua);
		txtTienThua.setColumns(15);

		JPanel panel_16 = new JPanel();
		panel_16.setAlignmentY(Component.TOP_ALIGNMENT);
		@SuppressWarnings("unused")
		FlowLayout flowLayout_1 = (FlowLayout) panel_16.getLayout();
		panel_14.add(panel_16, BorderLayout.EAST);

		JButton btnNewButton = new JButton("Tính tiền:");
		btnNewButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnNewButton.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_16.add(btnNewButton);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_14.add(horizontalStrut_1, BorderLayout.WEST);

		JPanel panel_1 = new JPanel();
		pnl_NhapHoaDon.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_1.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("Hóa đơn");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(25, 25, 112));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblNewLabel_1.setBackground(new Color(224, 255, 255));
		panel_4.add(lblNewLabel_1, BorderLayout.NORTH);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_4.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new GridLayout(0, 2, 20, 5));

		JLabel lblNewLabel_11 = new JLabel("Mã hóa đơn");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_7.add(lblNewLabel_11);

		txtMaHD = new JLabel("");
		String newSTTHD = mahd.substring(mahd.length() - 3);

		int i = Integer.parseInt(newSTTHD) + 1;
		String mahdmoi = "HD";
		if (i < 10) {
			mahdmoi = mahdmoi + "00" + i;
		}
		if (i >= 10 && i < 100) {
			mahdmoi = mahdmoi + "0" + i;
		}
		if (i >= 100 && i < 1000) {
			mahdmoi = mahdmoi + i;
		}
		txtMaHD.setText(mahdmoi);
		panel_7.add(txtMaHD);

		JLabel lblSodienthoaikhachhang = new JLabel("Số điện thoại khách hàng:");
		lblSodienthoaikhachhang.setFont(new Font("Segoe UI", Font.BOLD, 15));
		panel_7.add(lblSodienthoaikhachhang);

		txtSoDienThoaiKhachHang = new JTextField();

		txtSoDienThoaiKhachHang.setBackground(new Color(255, 255, 255));

		panel_7.add(txtSoDienThoaiKhachHang);

		JLabel lblNewLabel_4 = new JLabel("Ngày tạo:");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		panel_7.add(lblNewLabel_4);

		txtNgaytao = new JLabel();
		txtNgaytao.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txtNgaytao.setOpaque(false);

		txtNgaytao.setText(String.valueOf(date));
		panel_7.add(txtNgaytao);

		JSplitPane spltpnlCTHoaDon = new JSplitPane();
		spltpnlCTHoaDon.setBorder(null);
		spltpnlCTHoaDon.setOpaque(false);
		spltpnlCTHoaDon.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(spltpnlCTHoaDon, BorderLayout.CENTER);

		String[] ttSanPham = { "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Thành tiền" };
		JScrollPane scrpnl_danhsachchitiethoadon = new JScrollPane(
				tblDSCTHoaDon = new JTable(dataModelChitietHD = new DefaultTableModel(ttSanPham, 0) {
					boolean[] canEdit = new boolean[] { false, false, true, false };

					public boolean isCellEditable(int row, int column) {
						return canEdit[column];
					}
				}), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblDSCTHoaDon.setAutoCreateRowSorter(true);
		tblDSCTHoaDon.setRowHeight(30);
		tblDSCTHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		count = 0;
		tblDSCTHoaDon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				count++;
				tblDSSanPham.clearSelection();
				int r = tblDSCTHoaDon.getSelectedRow();
				soluongcu = Integer.parseInt(dataModelChitietHD.getValueAt(r, 2).toString());
				System.out.println("số lượng cũ :" + soluongcu);
				if (count == 2)
					count = 0;
			}

		});

		dataModelChitietHD.addTableModelListener(this);

		scrpnl_danhsachchitiethoadon.setOpaque(false);
		scrpnl_danhsachchitiethoadon.setBorder(null);
		scrpnl_danhsachchitiethoadon.setBackground(new Color(100, 149, 237));
		scrpnl_danhsachchitiethoadon.setAutoscrolls(true);
		spltpnlCTHoaDon.setRightComponent(scrpnl_danhsachchitiethoadon);

		scrpnl_danhsachchitiethoadon.setViewportView(tblDSCTHoaDon);

		JLabel lblNewLabel_3 = new JLabel("Chi tiết hóa đơn:");
		lblNewLabel_3.setBorder(null);
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		spltpnlCTHoaDon.setLeftComponent(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 15));

		Component horizontalStrut_4 = Box.createHorizontalStrut(17);
		pnl_NhapHoaDon.add(horizontalStrut_4, BorderLayout.WEST);

		Component horizontalStrut_5 = Box.createHorizontalStrut(19);
		pnl_NhapHoaDon.add(horizontalStrut_5, BorderLayout.EAST);

		JPanel pnl_DanhSachHoaDon = new JPanel();
		pnl_DanhSachHoaDon.setBackground(Color.WHITE);
		splitPane_1.setRightComponent(pnl_DanhSachHoaDon);
		pnl_DanhSachHoaDon.setLayout(new BorderLayout(0, 0));

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		pnl_DanhSachHoaDon.add(panel_8, BorderLayout.NORTH);
		panel_8.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_7 = new JLabel("Tìm kiếm sản phẩm");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setForeground(new Color(25, 25, 112));
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.BOLD, 25));
		panel_8.add(lblNewLabel_7, BorderLayout.NORTH);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		panel_8.add(panel_9, BorderLayout.EAST);
		panel_9.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_9.add(horizontalStrut_2);

		MyButton btnTim = new MyButton("Tìm");
		btnTim.setIcon(new ImageIcon(GUI_LapHoaDon.class.getResource("/images/timsanpham.png")));
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Xác nhận commit spinner textbox
				try {
					txtSotrang.commitEdit();
				} catch (ParseException e1) {
					JComponent editor = txtSotrang.getEditor();
					if (editor instanceof DefaultEditor) {
						((DefaultEditor) editor).getTextField().setValue(txtSotrang.getValue());
					}
				}
				String masp = txtMaSP.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtMaSP.getText().trim() + "%";
				String tensp = txtTensanpham.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtTensanpham.getText().trim() + "%";
				String nhaxb = txtNhaxuatban.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtNhaxuatban.getText().trim() + "%";
				String nhacc = txtNhacungcap.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtNhacungcap.getText().trim() + "%";
				String sotrang = txtSotrang.getValue().toString().trim();
				if (sotrang.equals("0")) {
					sotrang = "%";
				} else
					sotrang = "%" + txtSotrang.getValue().toString().trim() + "%";
				String tacgia = txtTacGia.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtTacGia.getText().trim() + "%";
				String chatlieu = txtChatlieu.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtChatlieu.getText().trim() + "%";
				String xuatxu = txtXuatxu.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtXuatxu.getText().trim() + "%";

				SanPhamDao spdao = new SanPhamDao();
				ArrayList<SanPham> list = spdao.timSanPhamNangCaoConHang(masp, tensp, nhaxb, nhacc, sotrang, tacgia,
						chatlieu, xuatxu);
				System.out.println(list.toString());
				deleteTableDSSanPham();
				updateTableDSSanPhamTim(list);
			}
		});
		panel_9.add(btnTim);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel_9.add(horizontalStrut_3);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_8.add(panel_5, BorderLayout.CENTER);
		panel_5.setLayout(new GridLayout(0, 4, 15, 25));

		JLabel lblNewLabel_10 = new JLabel("Mã sản phẩm:");
		lblNewLabel_10.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_5.add(lblNewLabel_10);

		txtMaSP = new JTextField();
		panel_5.add(txtMaSP);
		txtMaSP.setColumns(10);

		JLabel lblNewLabel = new JLabel("Tên sản phẩm:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_5.add(lblNewLabel);

		txtTensanpham = new JTextField();
		txtTensanpham.setColumns(10);
		panel_5.add(txtTensanpham);

		JLabel lblNewLabel_5 = new JLabel("Chất liệu:");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_5.add(lblNewLabel_5);

		txtChatlieu = new JTextField();
		txtChatlieu.setColumns(10);
		panel_5.add(txtChatlieu);

		JLabel lblNewLabel_8 = new JLabel("Nhà cung cấp:");
		lblNewLabel_8.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_5.add(lblNewLabel_8);

		txtNhacungcap = new JTextField();
		txtNhacungcap.setColumns(10);
		panel_5.add(txtNhacungcap);

		JLabel lblNewLabel_12 = new JLabel("Xuất xứ:");
		lblNewLabel_12.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_5.add(lblNewLabel_12);

		txtXuatxu = new JTextField();
		txtXuatxu.setColumns(10);
		panel_5.add(txtXuatxu);

		JLabel lblNewLabel_9 = new JLabel("Nhà xuất bản:");
		lblNewLabel_9.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_5.add(lblNewLabel_9);

		txtNhaxuatban = new JTextField();
		txtNhaxuatban.setColumns(10);
		panel_5.add(txtNhaxuatban);

		JLabel lblNewLabel_13 = new JLabel("Số trang:");
		lblNewLabel_13.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_5.add(lblNewLabel_13);

		txtSotrang = new JSpinner();
		panel_5.add(txtSotrang);

		JLabel lblNewLabel_6 = new JLabel("Tác giả:");
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_5.add(lblNewLabel_6);

		txtTacGia = new JTextField();
		panel_5.add(txtTacGia);
		txtTacGia.setColumns(10);

		JSplitPane spltpnlSanPham = new JSplitPane();
		spltpnlSanPham.setBorder(null);
		spltpnlSanPham.setOpaque(false);
		spltpnlSanPham.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pnl_DanhSachHoaDon.add(spltpnlSanPham, BorderLayout.CENTER);

		String[] title = { "Mã sản phẩm", "Tên sản phẩm", "Giá đơn vị", "Số lượng" };
		JScrollPane scrpnl_danhsachsanpham = new JScrollPane(
				tblDSSanPham = new JTable(dataModel = new DefaultTableModel(title, 0) {
					boolean[] canEdit = new boolean[] { false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return canEdit[column];
					}
				}), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblDSSanPham.setAutoCreateRowSorter(true);
		tblDSSanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDSSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tblDSSanPham.setRowHeight(30);
		scrpnl_danhsachsanpham.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		spltpnlSanPham.setRightComponent(scrpnl_danhsachsanpham);
		tblDSSanPham.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				tblDSCTHoaDon.clearSelection();
			}
		});

		scrpnl_danhsachsanpham.setViewportView(tblDSSanPham);

		JLabel lblNewLabel_2 = new JLabel("Danh Sách Sản Phẩm");
		lblNewLabel_2.setBorder(new MatteBorder(1, 1, 0, 1, (Color) new Color(0, 0, 0)));
		spltpnlSanPham.setLeftComponent(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(25, 25, 112));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 25));

		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(0, 0, 0)));
		panel_10.setBackground(Color.WHITE);
		pnl_DanhSachHoaDon.add(panel_10, BorderLayout.WEST);
		panel_10.setLayout(new GridLayout(8, 1, 15, 15));

		JPanel panel_11 = new JPanel();
		panel_11.setOpaque(false);
		panel_11.setBorder(null);
		panel_10.add(panel_11);
		panel_11.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblSLngCn = new JLabel("Số lượng sản phấm nhập:");
		lblSLngCn.setHorizontalAlignment(SwingConstants.LEFT);
		lblSLngCn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panel_11.add(lblSLngCn);

		JPanel panel_12 = new JPanel();
		panel_11.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));

		txtSoLuong = new JSpinner();
		panel_12.add(txtSoLuong, BorderLayout.CENTER);
		txtSoLuong.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));

		JLabel lblNewLabel_15 = new JLabel("");
		lblNewLabel_15.setOpaque(true);
		lblNewLabel_15.setBackground(Color.WHITE);
		lblNewLabel_15.setToolTipText("Chú ý: Số lượng sản phẩm nếu bé hơn hay là 0 thì sẽ được tính là 1");
		lblNewLabel_15.setIcon(new ImageIcon(GUI_LapHoaDon.class.getResource("/images/question.png")));
		panel_12.add(lblNewLabel_15, BorderLayout.EAST);
		dscthd = new ArrayList<CTHoaDon>();
		btnChuyen = new MyButton("Chuyển");
		btnChuyen.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {

				try {
					txtSoLuong.commitEdit();
				} catch (ParseException e1) {
					JComponent editor = txtSoLuong.getEditor();
					if (editor instanceof DefaultEditor) {
						((DefaultEditor) editor).getTextField().setValue(txtSoLuong.getValue());
					}
				}
				int i = stt;
				Object obj = e.getSource();

				if (obj.equals(btnChuyen)) {
					mahd = "HD";
					// tạo mã hóa đơn tự động
					if (i < 10) {
						mahd = mahd + "00" + i;
					}
					if (i >= 10 && i < 100) {
						mahd = mahd + "0" + i;
					}
					if (i >= 100 && i < 1000) {
						mahd = mahd + i;
					}
					System.out.println("Mã hóa đơn: " + mahd + i);
					if (kiemtra() == true) {
						int r = tblDSSanPham.getSelectedRow();
						if (r == -1) {
						} else {
							int soluong = Integer.parseInt(txtSoLuong.getValue().toString());
							String a = tblDSSanPham.getValueAt(r, 2).toString();
							String b = new String();
							for (int j = 0; j < a.split(",").length; j++) {
								b = b + a.split(",")[j];
							}
							double giadonvi = (int) Math.floor(Double.valueOf(b));
							double thanhtien = soluong * giadonvi;
							String masp = tblDSSanPham.getValueAt(r, 0).toString();

							thue = Double.parseDouble(Tinhtongtien(tblDSCTHoaDon)) * 0.05;
							txtTongtien.setText(new DecimalFormat("###,###,###")
									.format((int) Math.floor(Double.parseDouble(Tinhtongtien(tblDSCTHoaDon)) + thue)));
							System.out.println("Mã sản phẩm :" + masp);
							SanPham sp = new SanPhamDao().timSanPham(masp);
							SanPhamDao spdao = new SanPhamDao();

							CTHoaDon ctHoaDon = new CTHoaDon((int) txtSoLuong.getValue(), new HoaDon(mahd), sp);
							// cập nhật số lượng
							if (sp.getSoluong() >= (int) txtSoLuong.getValue() && (int) txtSoLuong.getValue() > 0) {
								// Sản phẩm trùng
								int ktTrung = 0;
								for (CTHoaDon cthd : dscthd) {
									System.out.print(cthd.getHd().getMahoadon());
									if (cthd.getSp().getMasanpham().equalsIgnoreCase(masp)) {
										ktTrung++;
										try {
											cthd.setSoluongsanpham(
													cthd.getSoluongsanpham() + (int) txtSoLuong.getValue());
											sp.setSoluong(sp.getSoluong() - (int) txtSoLuong.getValue());
											spdao.suaSanPham(sp);
											capnhatDSSPCuaHeThong();
										} catch (Exception e1) {
											e1.printStackTrace();
										}

										for (CTHoaDon ct : dscthd) {
											dataModelChitietHD.removeRow(0);
										}

										for (CTHoaDon ct : dscthd) {
											SanPhamDao spddao = new SanPhamDao();
											dataModelChitietHD.addRow(new Object[] { ct.getSp().getMasanpham(),
													spdao.timSanPham(ct.getSp().getMasanpham()).getTensanpham(),
													ct.getSoluongsanpham(),
													new DecimalFormat("###,###,###")
															.format((int) Math.floor(ct.getSoluongsanpham()
																	* spdao.timSanPham(ct.getSp().getMasanpham())
																			.getGiadonvi())) });
										}
										txtTongtien.setText(new DecimalFormat("###,###,###").format((int) Math
												.floor(Double.parseDouble(Tinhtongtien(tblDSCTHoaDon)) + thue)));
										System.out.println("Chi Tiet Hoa Don Trung: " + cthd.toString());

									}
								}
								// Sản phẩm không trùng
								if (ktTrung == 0) {
									dataModelChitietHD.addRow(new Object[] { tblDSSanPham.getValueAt(r, 0).toString(),
											tblDSSanPham.getValueAt(r, 1).toString(), soluong,
											new DecimalFormat("###,###,###").format((int) Math.floor(thanhtien)) });
									txtTongtien.setText(new DecimalFormat("###,###,###").format(
											(int) Math.floor(Double.parseDouble(Tinhtongtien(tblDSCTHoaDon)) + thue)));
									sp.setSoluong(sp.getSoluong() - (int) txtSoLuong.getValue());
									spdao.suaSanPham(sp);
									capnhatDSSPCuaHeThong();
									dscthd.add(ctHoaDon);
								}

							} else
								JOptionPane.showMessageDialog(btnChuyen,
										"Số lượng sản phẩm " + sp.getMasanpham() + " phải lớn hơn 0!", "Thông báo",
										JOptionPane.ERROR_MESSAGE);
						}

					}
					int r2 = tblDSCTHoaDon.getSelectedRow();
					// Xóa (chuyển ngược lại bảng sản phẩm)
					if (r2 == -1) {
					} else {
						tblDSSanPham.requestFocus(false);
						int hoi = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa dòng này", "Thông báo",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (hoi == JOptionPane.YES_OPTION) {
							String masp = (String) tblDSCTHoaDon.getValueAt(r2, 0);
							SanPham sp = new SanPhamDao().timSanPham(masp);
							SanPhamDao spdao = new SanPhamDao();
							sp.setSoluong(
									sp.getSoluong() + Integer.parseInt(tblDSCTHoaDon.getValueAt(r2, 2).toString()));
							System.out.println("Số lượng mới của sản phẩm " + masp + " sau khi xóa:" + sp.getSoluong());

							spdao.suaSanPham(sp);
							dscthd.remove(r2);
							capnhatDSSPCuaHeThong();

							dataModelChitietHD.removeRow(tblDSCTHoaDon.getSelectedRow());

							thue = Double.parseDouble(Tinhtongtien(tblDSCTHoaDon)) * 0.05;
						}

					}

					txtTongtien.setText(new DecimalFormat("###,###,###")
							.format((int) Math.floor(Double.parseDouble(Tinhtongtien(tblDSCTHoaDon)) + thue)));

				}
			}

		});
		btnChuyen.setIcon(null);
		btnChuyen.setColorClick(new Color(255, 102, 0));
		btnChuyen.setBorderColor(new Color(255, 153, 0));

		panel_10.add(btnChuyen);
		btnChuyen.setRadius(0);
		btnChuyen.setColorOver(new Color(255, 153, 0));
		btnChuyen.setColor(new Color(255, 255, 102));
		btnChuyen.setForeground(new Color(255, 102, 102));
		btnChuyen.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnChuyen.setBorder(null);
		btnChuyen.setBackground(new Color(255, 204, 0));

		Component horizontalStrut = Box.createHorizontalStrut(31);
		pnl_DanhSachHoaDon.add(horizontalStrut, BorderLayout.EAST);

		Component verticalStrut = Box.createVerticalStrut(29);
		pnl_DanhSachHoaDon.add(verticalStrut, BorderLayout.SOUTH);
	}

	/**
	 * Cập nhật danh sách sản phẩm
	 * 
	 * @return
	 */
	ArrayList<SanPham> updateTableDSSanPhamSauKhiChuyen() {
		String masp = txtMaSP.getText().trim().equalsIgnoreCase("") ? "%" : "%" + txtMaSP.getText().trim() + "%";
		String tensp = txtTensanpham.getText().trim().equalsIgnoreCase("") ? "%"
				: "%" + txtTensanpham.getText().trim() + "%";
		String nhaxb = txtNhaxuatban.getText().trim().equalsIgnoreCase("") ? "%"
				: "%" + txtNhaxuatban.getText().trim() + "%";
		String nhacc = txtNhacungcap.getText().trim().equalsIgnoreCase("") ? "%"
				: "%" + txtNhacungcap.getText().trim() + "%";
		String sotrang = txtSotrang.getValue().toString().trim();
		if (sotrang.equals("0")) {
			sotrang = "%";
		} else
			sotrang = "%" + txtSotrang.getValue().toString().trim() + "%";
		String tacgia = txtTacGia.getText().trim().equalsIgnoreCase("") ? "%" : "%" + txtTacGia.getText().trim() + "%";
		String chatlieu = txtChatlieu.getText().trim().equalsIgnoreCase("") ? "%"
				: "%" + txtChatlieu.getText().trim() + "%";
		String xuatxu = txtXuatxu.getText().trim().equalsIgnoreCase("") ? "%" : "%" + txtXuatxu.getText().trim() + "%";

		SanPhamDao spdao = new SanPhamDao();
		ArrayList<SanPham> list = spdao.timSanPhamNangCaoConHang(masp, tensp, nhaxb, nhacc, sotrang, tacgia, chatlieu,
				xuatxu);
		return list;
	}

	/**
	 * Cập nhật danh sách gồm sản phẩm sau khi chuyển
	 */
	void capnhatDSSPCuaHeThong() {
		deleteTableDSSanPham();

		updateTableDSSanPhamTim(updateTableDSSanPhamSauKhiChuyen());

		GUI_DSSanPham.deleteTableDSSanPham();
		GUI_DSSanPham.updateTableDSSanPham();
		if (GUI_CNSanPham.dataModelSach != null && GUI_CNSanPham.dataModelVPP != null) {
			GUI_CNSanPham.deleteTableSach();
			GUI_CNSanPham.updateTableSach();
			GUI_CNSanPham.deleteTableVPP();
			GUI_CNSanPham.updateTableVPP();
		}

		GUI_DSHoaDon.deleteTableDSHD();

		GUI_DSKhachHang.deleteTableDSKhachHang();
		GUI_DSKhachHang.updateTableDSKhachHang();
		GUI_CNKhachHang.deleteTableCNKH();
		GUI_CNKhachHang.updateTableCNKH();

		ArrayList<HoaDon> dspd = null;
		try {
			dspd = new HoaDonDao().layDsHoaDonTheoThoiGian(0, GUIChinh_NhanVien.nv.getManhanvien());
		} catch (Exception err) {
			dspd = new HoaDonDao().layDsHoaDonTheoThoiGian(0, GUIChinh_QuanLy.nv.getManhanvien());
		}
		GUI_DSHoaDon.updateTableDSHDTheoThoiGian(dspd);
	}

	/**
	 * Cập nhật danh sách sản phẩm sau khi tìm
	 * 
	 * @param dssp
	 */
	public static void updateTableDSSanPhamTim(ArrayList<SanPham> dssp) {
		for (SanPham i : dssp) {
			dataModel.addRow(new Object[] { i.getMasanpham(), i.getTensanpham(),
					new DecimalFormat("###,###,###").format((int) Math.floor(i.getGiadonvi())), i.getSoluong() });
		}
	}

	/**
	 * Xóa toàn bộ danh sách sản phẩm
	 */
	public static void deleteTableDSSanPham() {
		int dem = tblDSSanPham.getRowCount();
		for (int i = 0; i < dem; i++) {
			dataModel.removeRow(0);
			;
		}

	}

	/**
	 * Xóa toàn bộ danh sách chi tiết hóa đơn
	 */
	public void deleteTableDSCTHoaDon() {
		int dem = tblDSCTHoaDon.getRowCount();
		for (int i = 0; i < dem; i++) {
			dataModelChitietHD.removeRow(0);

		}

	}

	/**
	 * Tính tổng tiền của bảng tbl
	 * 
	 * @param tbl
	 * @return
	 */
	public String Tinhtongtien(JTable tbl) {
		double tongtien = 0.0;
		for (int i = 0; i < tbl.getRowCount(); i++) {
			String a = tbl.getValueAt(i, 3).toString();
			String b = new String();
			for (int j = 0; j < a.split(",").length; j++) {
				b = b + a.split(",")[j];
			}
			tongtien += (int) Math.floor(Double.valueOf(b));
		}

		return String.valueOf((int) Math.floor(tongtien));
	}

	/**
	 * Kiểm tra số lượng
	 * 
	 * @return
	 */
	public boolean kiemtra() {
		String soluongmua = txtSoLuong.getValue().toString().trim();

		if (soluongmua.length() > 0) {
			try {
				int x = Integer.parseInt(soluongmua);
				if (x <= 0) {
					JOptionPane.showMessageDialog(null, "Số lượng mua phải lớn hơn 0. Ví dụ: 1,2,3..", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Phải nhập dữ liệu số. Ví dụ: 1,2,3..", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập vào số lượng", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;

	}

	/**
	 * Thay đổi số lượng trực tiếp trên bảng chi tiết hóa đơn
	 */
	@Override
	public void tableChanged(TableModelEvent e) {

		if (e.getColumn() == 2) {
			int r = e.getFirstRow();

			soluongmoi = Integer.parseInt(dataModelChitietHD.getValueAt(r, 2).toString());
			if (soluongmoi <= 0) {
				JOptionPane.showMessageDialog(null, "Số lượng không được bé hơn hoặc bằng 0!");
				soluongmoi = soluongcu;
				dataModelChitietHD.setValueAt(soluongmoi, r, 2);
			}
			SanPham sp = new SanPhamDao().timSanPham(dataModelChitietHD.getValueAt(r, 0).toString());

			if (soluongcu > soluongmoi) {
				int chenhlech = soluongcu - soluongmoi;
				sp.setSoluong(sp.getSoluong() + chenhlech);
				dataModelChitietHD.setValueAt(
						new DecimalFormat("###,###,###").format((int) Math.floor(soluongmoi * sp.getGiadonvi())), r, 3);
				SanPhamDao spdao = new SanPhamDao();
				spdao.suaSanPham(sp);
				txtTongtien.setText(new DecimalFormat("###,###,###")
						.format((int) Math.floor(Double.valueOf(Tinhtongtien(tblDSCTHoaDon)))));
				System.out.println("Số lượng sản phẩm " + sp.getMasanpham() + " mới :" + sp.getSoluong());
				capnhatDSSPCuaHeThong();
				tblDSCTHoaDon.requestFocus(false);

			} else if (soluongcu < soluongmoi) {
				int chenhlech = soluongmoi - soluongcu;
				sp.setSoluong(sp.getSoluong() - chenhlech);
				dataModelChitietHD.setValueAt(
						new DecimalFormat("###,###,###").format((int) Math.floor(soluongmoi * sp.getGiadonvi())), r, 3);
				SanPhamDao spdao = new SanPhamDao();
				spdao.suaSanPham(sp);
				txtTongtien.setText(new DecimalFormat("###,###,###")
						.format((int) Math.floor(Double.valueOf(Tinhtongtien(tblDSCTHoaDon)))));
				capnhatDSSPCuaHeThong();
				tblDSCTHoaDon.requestFocus(false);

			} else if (soluongcu == soluongmoi) {
				sp.setSoluong(sp.getSoluong());
			}

		}

	}

}
