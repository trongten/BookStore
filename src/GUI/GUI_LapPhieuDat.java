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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Others.DateLabelFormatter;
import Others.MyButton;
import dao.CTPhieuDatTruocDao;
import dao.PhieuDatTruocDao;
import dao.SanPhamDao;
import entity.CTPhieuDatTruoc;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatTruoc;
import entity.SanPham;

/**
 * Giao diện lập phiếu đặt
 * 
 * @author Phan Võ Trọng - Võ Phước Lưu - Nguyễn Phạm Công Nhật
 *
 */
public class GUI_LapPhieuDat extends JPanel implements TableModelListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static JLabel txtTongtien;
	private JTextField txtTensanpham;
	private JTextField txtChatlieu;
	private JTextField txtNhacungcap;
	private JTextField txtXuatxu;
	private JTextField txtNhaxuatban;
	private JSpinner txtSotrang;
	private MyButton btnChuyen;
	private static JTable tblDSSanPham;
	private static JTable tblDSCTPhieuDat;
	private JTextField txtMaSP;
	private JTextField txtTacGia;
	private static DefaultTableModel dataModel;
	private static DefaultTableModel dataModelChitietPD;
	private JSpinner txtSoLuong;
	@SuppressWarnings("unused")
	private Date date;
	int a = 1;
	int stt = 0;

	private int soluongcu = 0;
	private int soluongmoi = 0;
	private static ArrayList<CTPhieuDatTruoc> dsctpd;

	private String mapd = "";

	private static UtilDateModel dateModel;

	private Properties dateProperties;

	private JDatePanelImpl datePanel;

	private static JLabel txtmakhachhang;

	private JLabel lblNewLabel_6_1;

	private String maphieudattam = "";

	private static JLabel txtsdt;

	private static JLabel txttenkhachhang;
	private JSpinner txtGio;
	private JSpinner txtPhut;

	protected double thue;

	protected double discount;// discount khi người mua có trong danh sách khách hàng

	private int count;// tạo sự kiện double click

	private JLabel txtMaPD;

	/**
	 * Create the panel.
	 */

	@SuppressWarnings({ "serial", "removal" })
	public GUI_LapPhieuDat(NhanVien nv) {
		setLayout(new BorderLayout(0, 0));
		setSize(1399, 718);
		JPanel pnlLapPhieuDatTruoc = new JPanel();
		add(pnlLapPhieuDatTruoc, BorderLayout.CENTER);
		pnlLapPhieuDatTruoc.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setBackground(new Color(100, 149, 237));
		pnlLapPhieuDatTruoc.add(splitPane_1);

		JPanel pnl_NhapPhieuDatTruoc = new JPanel();
		pnl_NhapPhieuDatTruoc.setMinimumSize(new Dimension(500, 300));
		pnl_NhapPhieuDatTruoc.setMaximumSize(new Dimension(700, 375));
		pnl_NhapPhieuDatTruoc.setBackground(Color.WHITE);
		splitPane_1.setLeftComponent(pnl_NhapPhieuDatTruoc);
		pnl_NhapPhieuDatTruoc.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_6.setBackground(new Color(224, 255, 255));
		pnl_NhapPhieuDatTruoc.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new GridLayout(0, 1, 10, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_3.setBackground(Color.WHITE);
		panel_6.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel_17 = new JLabel("Thuế VAT(5%):");
		lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_17.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_3.add(lblNewLabel_17);

		JLabel txtThue = new JLabel("0.0");
		txtThue.setForeground(Color.RED);
		panel_3.add(txtThue);

		JLabel lblNewLabel_15 = new JLabel("Discount(2.5%):");
		lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_3.add(lblNewLabel_15);

		JLabel txtDiscount = new JLabel("0.0");
		txtDiscount.setForeground(new Color(34, 139, 34));
		panel_3.add(txtDiscount);

		JLabel lblTngTin = new JLabel("Tổng tiền: ");
		panel_3.add(lblTngTin);
		lblTngTin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTngTin.setFont(new Font("Segoe UI", Font.BOLD, 20));

		txtTongtien = new JLabel();
		// Tổng tiền thay đổi
		txtTongtien.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				try {
					// Bảng chi tiết phiếu đặt có nhiều hơn 0 sản phẩm
					thue = Double.parseDouble(Tinhtongtien(tblDSCTPhieuDat)) * 0.05;
					discount = Double.parseDouble(Tinhtongtien(tblDSCTPhieuDat)) * 0.025;
				} catch (Exception e) {
					// Bảng chi tiết phiếu đặt là rỗng
					thue = 0;
					discount = 0;
				}
				txtThue.setText("+" + String.valueOf(thue));
				txtDiscount.setText("-" + String.valueOf(discount));
			}
		});
		txtTongtien.setText("0.0");
		txtTongtien.setFont(new Font("Tahoma", Font.BOLD, 20));

		panel_3.add(txtTongtien);
		// bắt đầu tự động sinh mã
		PhieuDatTruocDao pddao = new PhieuDatTruocDao();
		ArrayList<PhieuDatTruoc> pdcuoi = pddao.layDsPhieuDatTruoc();
		if (pdcuoi.size() != 0) {
			maphieudattam = pdcuoi.get(pdcuoi.size() - 1).getMaphieudat()
					.substring(pdcuoi.get(pdcuoi.size() - 1).getMaphieudat().length() - 3);
			stt = Integer.parseInt(maphieudattam);
		} else {
			stt = 0;
		}
		mapd = "PD";
		if (stt < 10) {
			mapd = mapd + "00" + stt;
		}
		if (stt >= 10 && stt < 100) {
			mapd = mapd + "0" + stt;
		}
		if (stt >= 100 && stt < 1000) {
			mapd = mapd + stt;
		}
		txtMaPD = new JLabel();
		txtMaPD.setFont(new Font("Segoe UI", Font.BOLD, 15));
		String newSTTPD = mapd.substring(mapd.length() - 3);

		int i = Integer.parseInt(newSTTPD) + 1;
		String mapdmoi = "PD";
		if (i < 10) {
			mapdmoi = mapdmoi + "00" + i;
		}
		if (i >= 10 && i < 100) {
			mapdmoi = mapdmoi + "0" + i;
		}
		if (i >= 100 && i < 1000) {
			mapdmoi = mapdmoi + i;
		}
		// Kết thúc tự động sinh mã
		txtMaPD.setText(mapdmoi);

		JPanel panel_2 = new JPanel();
		panel_6.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		MyButton btnInVLu = new MyButton("In và lưu hóa đơn");
		panel_2.add(btnInVLu);
		btnInVLu.setText("Lưu phiếu đặt");
		btnInVLu.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					txtGio.commitEdit();
					txtPhut.commitEdit();
				} catch (ParseException e1) {
					JComponent editor = txtGio.getEditor();
					if (editor instanceof DefaultEditor) {
						((DefaultEditor) editor).getTextField().setValue(txtGio.getValue());

					}
					JComponent editor2 = txtPhut.getEditor();
					if (editor2 instanceof DefaultEditor) {
						((DefaultEditor) editor2).getTextField().setValue(txtPhut.getValue());
					}
				}
				if (txtmakhachhang.getText().trim().equalsIgnoreCase("unknown")) {
					JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng");
				} else
					if (new Date(dateModel.getYear() - 1900, dateModel.getMonth(), dateModel.getDay(),
						Integer.parseInt(txtGio.getValue().toString()), Integer.parseInt(txtPhut.getValue().toString()),
						0).before(new Date()))
					JOptionPane.showMessageDialog(null,
							"Ngày nhận hàng phải sau ngày đặt hàng ! \nVD: Ngày đặt là 25/11/2021 thì ngày nhận phải là 26,27 hoặc 28 trở về sau/11/2021",
							"Thông báo", JOptionPane.INFORMATION_MESSAGE);
				else if (dsctpd.size() != 0) {
					stt++;

					String checkma = mapd.substring(mapd.length() - 3);
					if (stt == Integer.parseInt(checkma)) {
						int i = stt + 1;
						mapd = "PD";
						if (i < 10) {
							mapd = mapd + "00" + i;
						}
						if (i >= 10 && i < 100) {
							mapd = mapd + "0" + i;
						}
						if (i >= 100 && i < 1000) {
							mapd = mapd + i;
						}
					} else {
						int i = stt;
						mapd = "PD";
						if (i < 10) {
							mapd = mapd + "00" + i;
						}
						if (i >= 10 && i < 100) {
							mapd = mapd + "0" + i;
						}
						if (i >= 100 && i < 1000) {
							mapd = mapd + i;
						}
					}

					Timestamp ngaylapPhieuDatTruoc = new Timestamp(System.currentTimeMillis());
					Timestamp ngaynhanhang = new Timestamp(dateModel.getYear() - 1900, dateModel.getMonth(),
							dateModel.getDay(), Integer.parseInt(txtGio.getValue().toString()),
							Integer.parseInt(txtPhut.getValue().toString()), 0, 0);
					String c = txtTongtien.getText();
					String b = new String();
					for (int i = 0; i < c.split(",").length; i++) {
						b = b + c.split(",")[i];
					}
					double tongtien = Double.parseDouble(b);
					PhieuDatTruocDao pddao = new PhieuDatTruocDao();
					KhachHang khachhang = new KhachHang(txtmakhachhang.getText(), txttenkhachhang.getText(),
							txtsdt.getText());
					CTPhieuDatTruocDao ctpddao = new CTPhieuDatTruocDao();

					PhieuDatTruoc phieudattruoc = new PhieuDatTruoc(mapd, ngaylapPhieuDatTruoc, ngaynhanhang, tongtien,
							nv, khachhang, "Chưa nhận hàng");
					System.out.println(phieudattruoc.toString());

					pddao.themPhieuDatTruoc(phieudattruoc);

					for (CTPhieuDatTruoc ctpd : dsctpd) {
						ctpd.setPdt(phieudattruoc);
						ctpddao.themCTPhieuDatTruoc(ctpd);
					}
					String newSTTPD = mapd.substring(mapd.length() - 3);

					int i = Integer.parseInt(newSTTPD) + 1;
					String mapdmoi = "PD";
					if (i < 10) {
						mapdmoi = mapdmoi + "00" + i;
					}
					if (i >= 10 && i < 100) {
						mapdmoi = mapdmoi + "0" + i;
					}
					if (i >= 100 && i < 1000) {
						mapdmoi = mapdmoi + i;
					}
					txtMaPD.setText(mapdmoi);
					JOptionPane.showMessageDialog(pnl_NhapPhieuDatTruoc, "Đặt trước thành công");

					capnhatDSSPCuaHeThong();
					dsctpd = new ArrayList<CTPhieuDatTruoc>();

					GUI_DSPhieuDat.deleteTableDSPD();
					GUI_DSPhieuDat.updateTableDSPDTheoThoiGian(
							new PhieuDatTruocDao().layDsPhieuDatTheoThoiGian(0, nv.getManhanvien()));

					deleteTableDSCTPhieuDat();

				} else {
					JOptionPane.showMessageDialog(null, "Chưa có sản phẩm nào trong phiếu đặt! Vui lòng thêm",
							"Thông báo", JOptionPane.ERROR_MESSAGE);

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

		MyButton btnXoaRong = new MyButton("Xóa rỗng");
		panel_2.add(btnXoaRong, BorderLayout.EAST);
		btnXoaRong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int y = dataModelChitietPD.getRowCount();
				if (y > 0) {
					for (int r = 0; r < y; r++) {

						String masp = dsctpd.get(r).getSp().getMasanpham();
						SanPham sp = new SanPhamDao().timSanPham(masp);
						SanPhamDao spdao = new SanPhamDao();
						int spm = dsctpd.get(r).getSoluongsanpham();
						sp.setSoluong(sp.getSoluong() + spm);

						spdao.suaSanPham(sp);

						capnhatDSSPCuaHeThong();

						dataModelChitietPD.removeRow(0);
					}
					dsctpd = new ArrayList<CTPhieuDatTruoc>();
					txtTongtien.setText(Tinhtongtien(tblDSCTPhieuDat));
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
				} else {
					JOptionPane.showMessageDialog(null, "Chưa có sản phẩm nào trong phiếu đặt! Vui lòng thêm",
							"Thông báo", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnXoaRong.setBackground(Color.WHITE);
		btnXoaRong.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JPanel panel_1 = new JPanel();
		pnl_NhapPhieuDatTruoc.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_1.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("Phiếu Đặt Trước");
		lblNewLabel_1.setMaximumSize(new Dimension(700, 13));
		lblNewLabel_1.setMinimumSize(new Dimension(700, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(25, 25, 112));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblNewLabel_1.setBackground(new Color(224, 255, 255));
		panel_4.add(lblNewLabel_1, BorderLayout.NORTH);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panel_4.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new GridLayout(0, 3, 0, 0));

		JLabel lblNewLabel_19 = new JLabel("Mã Phiếu Đặt:");
		lblNewLabel_19.setFont(new Font("Segoe UI", Font.BOLD, 15));
		panel_7.add(lblNewLabel_19);

		panel_7.add(txtMaPD);

		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		panel_7.add(horizontalStrut_6);

		JLabel lblNewLabel_14 = new JLabel("Mã khách hàng:");
		lblNewLabel_14.setFont(new Font("Segoe UI", Font.BOLD, 15));
		panel_7.add(lblNewLabel_14);

		txtmakhachhang = new JLabel("unknown");
		txtmakhachhang.setFont(new Font("Segoe UI", Font.BOLD, 15));
		panel_7.add(txtmakhachhang);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(Color.WHITE);
		panel_7.add(panel);

		MyButton btnNewButton_1 = new MyButton("Chọn khách hàng");
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new GUI_LayKH().setVisible(true);

			}
		});

		lblNewLabel_6_1 = new JLabel("Tên khách hàng:");
		lblNewLabel_6_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
		panel_7.add(lblNewLabel_6_1);

		txttenkhachhang = new JLabel();
		txttenkhachhang.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txttenkhachhang.setText("unknown");
		panel_7.add(txttenkhachhang);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_7.add(horizontalStrut_1);
		long millis = System.currentTimeMillis();
		date = new Date(millis);

		dateModel = new UtilDateModel();
		dateProperties = new Properties();
		dateProperties.put("text.today", "Today");
		dateProperties.put("text.month", "Month");
		dateProperties.put("text.year", "Year");
		datePanel = new JDatePanelImpl(dateModel, dateProperties);

		JLabel lblNewLabel_10_1 = new JLabel("Số điện thoại:");
		lblNewLabel_10_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
		panel_7.add(lblNewLabel_10_1);

		txtsdt = new JLabel();
		txtsdt.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txtsdt.setText("uknown");
		panel_7.add(txtsdt);

		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		panel_7.add(horizontalStrut_5);

		JLabel lblNewLabel_11 = new JLabel("Ngày lấy:");
		lblNewLabel_11.setFont(new Font("Segoe UI", Font.BOLD, 15));
		panel_7.add(lblNewLabel_11);

		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.WHITE);
		panel_7.add(panel_12);
		panel_12.setLayout(new GridLayout(0, 5, 0, 0));

		txtGio = new JSpinner();
		txtGio.setModel(new SpinnerNumberModel(new Short((short) 0), new Short((short) 0), new Short((short) 24),
				new Short((short) 1)));
		panel_12.add(txtGio);

		JLabel lblH = new JLabel("giờ");
		lblH.setHorizontalAlignment(SwingConstants.CENTER);
		panel_12.add(lblH);

		txtPhut = new JSpinner();
		txtPhut.setModel(new SpinnerNumberModel(new Short((short) 0), new Short((short) 0), new Short((short) 59),
				new Short((short) 1)));
		panel_12.add(txtPhut);

		JLabel lblNewLabel_4 = new JLabel("phút");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_12.add(lblNewLabel_4);

		JLabel lblNewLabel_16 = new JLabel("");
		lblNewLabel_16.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_16.setToolTipText(
				"Giờ, phút không hợp lệ (bé hơn 0, hay phút quá 60 và giờ quá 24) thì sẽ được tính là 0");
		lblNewLabel_16.setIcon(new ImageIcon(GUI_LapPhieuDat.class.getResource("/images/question.png")));
		panel_12.add(lblNewLabel_16);

		JDatePickerImpl dateNgaySinh = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		dateNgaySinh.setOpaque(false);
		dateNgaySinh.getJFormattedTextField().setOpaque(false);
		panel_7.add(dateNgaySinh);
		dateModel.setValue(new Date(System.currentTimeMillis()));

		JSplitPane spltpnlCTPhieuDatTruoc = new JSplitPane();
		spltpnlCTPhieuDatTruoc.setMinimumSize(new Dimension(700, 23));
		spltpnlCTPhieuDatTruoc.setBorder(null);
		spltpnlCTPhieuDatTruoc.setOpaque(false);
		spltpnlCTPhieuDatTruoc.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(spltpnlCTPhieuDatTruoc, BorderLayout.CENTER);

		String[] ttSanPham = { "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Thành tiền" };
		JScrollPane scrpnl_danhsachchitietPhieuDatTruoc = new JScrollPane(
				tblDSCTPhieuDat = new JTable(dataModelChitietPD = new DefaultTableModel(ttSanPham, 0) {
					boolean[] canEdit = new boolean[] { false, false, true, false };

					public boolean isCellEditable(int row, int column) {
						return canEdit[column];
					}
				}), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblDSCTPhieuDat.setAutoCreateRowSorter(true);
		tblDSCTPhieuDat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tblDSCTPhieuDat.setRowHeight(30);
		tblDSCTPhieuDat.setOpaque(false);
		tblDSCTPhieuDat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		count = 0;
		tblDSCTPhieuDat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				count++;
				tblDSSanPham.clearSelection();
				int r = tblDSCTPhieuDat.getSelectedRow();
				soluongcu = Integer.parseInt(dataModelChitietPD.getValueAt(r, 2).toString());
				System.out.println("số lượng cũ :" + soluongcu);
				if (count == 2)
					count = 0;
			}
		});

		dataModelChitietPD.addTableModelListener(this);

		scrpnl_danhsachchitietPhieuDatTruoc.setOpaque(false);
		scrpnl_danhsachchitietPhieuDatTruoc.setBorder(null);
		scrpnl_danhsachchitietPhieuDatTruoc.setBackground(new Color(100, 149, 237));
		scrpnl_danhsachchitietPhieuDatTruoc.setAutoscrolls(true);
		spltpnlCTPhieuDatTruoc.setRightComponent(scrpnl_danhsachchitietPhieuDatTruoc);

		scrpnl_danhsachchitietPhieuDatTruoc.setViewportView(tblDSCTPhieuDat);

		JLabel lblNewLabel_3 = new JLabel("Chi tiết Phiếu đặt trước:");
		lblNewLabel_3.setBorder(null);
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		spltpnlCTPhieuDatTruoc.setLeftComponent(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 15));

		Component horizontalStrut_4 = Box.createHorizontalStrut(17);
		pnl_NhapPhieuDatTruoc.add(horizontalStrut_4, BorderLayout.WEST);

		Component horizontalStrut_7 = Box.createHorizontalStrut(17);
		pnl_NhapPhieuDatTruoc.add(horizontalStrut_7, BorderLayout.EAST);

		JPanel pnl_DanhSachPhieuDatTruoc = new JPanel();
		pnl_DanhSachPhieuDatTruoc.setBackground(Color.WHITE);
		splitPane_1.setRightComponent(pnl_DanhSachPhieuDatTruoc);
		pnl_DanhSachPhieuDatTruoc.setLayout(new BorderLayout(0, 0));

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		pnl_DanhSachPhieuDatTruoc.add(panel_8, BorderLayout.NORTH);
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
		btnTim.setIcon(new ImageIcon(GUI_LapPhieuDat.class.getResource("/images/timsanpham.png")));
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
		pnl_DanhSachPhieuDatTruoc.add(spltpnlSanPham, BorderLayout.CENTER);

		String[] title = { "Mã sản phẩm", "Tên sản phẩm", "Giá đơn vị", "Số lượng" };
		JScrollPane scrpnl_danhsachsanpham = new JScrollPane(
				tblDSSanPham = new JTable(dataModel = new DefaultTableModel(title, 0) {
					boolean[] canEdit = new boolean[] { false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return canEdit[column];
					}
				}), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblDSSanPham.setAutoCreateRowSorter(true);
		tblDSSanPham.setRowHeight(30);
		tblDSSanPham.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tblDSSanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDSSanPham.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tblDSCTPhieuDat.clearSelection();
			}
		});
		scrpnl_danhsachsanpham.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		spltpnlSanPham.setRightComponent(scrpnl_danhsachsanpham);

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
		pnl_DanhSachPhieuDatTruoc.add(panel_10, BorderLayout.WEST);
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

		JPanel panel_13 = new JPanel();
		panel_11.add(panel_13);
		panel_13.setLayout(new BoxLayout(panel_13, BoxLayout.X_AXIS));

		txtSoLuong = new JSpinner();
		panel_13.add(txtSoLuong);
		txtSoLuong.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));

		JLabel lblNewLabel_18 = new JLabel("");
		lblNewLabel_18.setToolTipText("Số lượng không hợp lệ ( bé hơn hay bằng 0) sẽ được tính là 1");
		lblNewLabel_18.setIcon(new ImageIcon(GUI_LapPhieuDat.class.getResource("/images/question.png")));
		panel_13.add(lblNewLabel_18);
		dsctpd = new ArrayList<CTPhieuDatTruoc>();
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
					// tạo mã phiếu đặt
					mapd = "PD";
					if (i < 10) {
						mapd = mapd + "00" + i;
					}
					if (i >= 10 && i < 100) {
						mapd = mapd + "0" + i;
					}
					if (i >= 100 && i < 1000) {
						mapd = mapd + i;
					}
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

							thue = Double.parseDouble(Tinhtongtien(tblDSCTPhieuDat)) * 0.05;
							txtTongtien.setText(new DecimalFormat("###,###,###").format((int) Math
									.floor(Double.parseDouble(Tinhtongtien(tblDSCTPhieuDat)) + thue - discount)));

							SanPham sp = new SanPhamDao().timSanPham(masp);
							SanPhamDao spdao = new SanPhamDao();

							CTPhieuDatTruoc CTPhieuDatTruoc = new CTPhieuDatTruoc((int) txtSoLuong.getValue(),
									new PhieuDatTruoc(mapd), sp);
							// cập nhật số lượng
							if (sp.getSoluong() >= (int) txtSoLuong.getValue() && (int) txtSoLuong.getValue() > 0) {
								// Sản phẩm trùng
								int ktTrung = 0;
								for (CTPhieuDatTruoc ctpd : dsctpd) {

									if (ctpd.getSp().getMasanpham().equalsIgnoreCase(masp)) {
										ktTrung++;
										try {
											ctpd.setSoluongsanpham(
													ctpd.getSoluongsanpham() + (int) txtSoLuong.getValue());
											sp.setSoluong(sp.getSoluong() - (int) txtSoLuong.getValue());
											spdao.suaSanPham(sp);
											capnhatDSSPCuaHeThong();
										} catch (Exception e1) {
											e1.printStackTrace();
										}
										for (CTPhieuDatTruoc ct : dsctpd) {
											dataModelChitietPD.removeRow(0);
										}
										for (CTPhieuDatTruoc ct : dsctpd) {

											SanPhamDao spddao = new SanPhamDao();
											dataModelChitietPD.addRow(new Object[] { ct.getSp().getMasanpham(),
													spdao.timSanPham(ct.getSp().getMasanpham()).getTensanpham(),
													ct.getSoluongsanpham(),
													new DecimalFormat("###,###,###")
															.format((int) Math.floor(ct.getSoluongsanpham()
																	* spdao.timSanPham(ct.getSp().getMasanpham())
																			.getGiadonvi())) });
										}
										txtTongtien.setText(new DecimalFormat("###,###,###").format((int) Math.floor(
												Double.parseDouble(Tinhtongtien(tblDSCTPhieuDat)) + thue - discount)));
									}
								}
								// Sản phẩm không trùng
								if (ktTrung == 0) {
									dataModelChitietPD.addRow(new Object[] { tblDSSanPham.getValueAt(r, 0).toString(),
											tblDSSanPham.getValueAt(r, 1).toString(), soluong,
											new DecimalFormat("###,###,###").format((int) Math.floor(thanhtien)) });
									txtTongtien.setText(new DecimalFormat("###,###,###").format((int) Math.floor(
											Double.parseDouble(Tinhtongtien(tblDSCTPhieuDat)) + thue - discount)));
									sp.setSoluong(sp.getSoluong() - (int) txtSoLuong.getValue());
									spdao.suaSanPham(sp);
									capnhatDSSPCuaHeThong();
									dsctpd.add(CTPhieuDatTruoc);
								}

							} else
								JOptionPane.showMessageDialog(btnChuyen,
										"Số lượng sản phẩm " + sp.getMasanpham() + " không được bé hơn hoặc là 0",
										"Thông báo", JOptionPane.ERROR_MESSAGE);

						}
					}

					int r2 = tblDSCTPhieuDat.getSelectedRow();
					// Xóa (chuyển ngược lại bảng sản phẩm)
					if (r2 == -1) {
					} else {
						tblDSSanPham.requestFocus(false);
						int hoi = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa dòng này", "Thông báo",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (hoi == JOptionPane.YES_OPTION) {
							String masp = (String) tblDSCTPhieuDat.getValueAt(r2, 0);
							SanPham sp = new SanPhamDao().timSanPham(masp);
							SanPhamDao spdao = new SanPhamDao();
							sp.setSoluong(
									sp.getSoluong() + Integer.parseInt(tblDSCTPhieuDat.getValueAt(r2, 2).toString()));
							spdao.suaSanPham(sp);
							dsctpd.remove(r2);
							capnhatDSSPCuaHeThong();
							dataModelChitietPD.removeRow(tblDSCTPhieuDat.getSelectedRow());
						}

					}

					txtTongtien.setText(new DecimalFormat("###,###,###").format(
							(int) Math.floor(Double.parseDouble(Tinhtongtien(tblDSCTPhieuDat)) + thue - discount)));
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

		Component horizontalStrut = Box.createHorizontalStrut(37);
		pnl_DanhSachPhieuDatTruoc.add(horizontalStrut, BorderLayout.EAST);

		Component verticalStrut = Box.createVerticalStrut(29);
		pnl_DanhSachPhieuDatTruoc.add(verticalStrut, BorderLayout.SOUTH);

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
	 * Cập nhật danh sách sản phẩm, sách, văn phòng phẩm, chi tiết phiếu đặt
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

		ArrayList<PhieuDatTruoc> dspd = null;
		try {
			dspd = new PhieuDatTruocDao().layDsPhieuDatTheoThoiGian(0, GUIChinh_NhanVien.nv.getManhanvien());
		} catch (Exception err) {
			dspd = new PhieuDatTruocDao().layDsPhieuDatTheoThoiGian(0, GUIChinh_QuanLy.nv.getManhanvien());
		}
		GUI_DSPhieuDat.deleteTableDSPD();
		GUI_DSPhieuDat.updateTableDSPDTheoThoiGian(dspd);

		GUI_DSPhieuDat.deleteTableCTPD();
		GUI_DSPhieuDat.updateTableCTPD();
	}

	/**
	 * Xóa toàn bộ danh sách chi tiết Phiếu đặt
	 */
	public void deleteTableDSCTPhieuDat() {
		int dem = tblDSCTPhieuDat.getRowCount();
		for (int i = 0; i < dem; i++) {
			dataModelChitietPD.removeRow(0);
		}
	}

	/**
	 * Cập nhật bảng danh sách sản phẩm
	 */
	public static void updateTableDSSanPham() {
		SanPhamDao spdao = new SanPhamDao();
		ArrayList<SanPham> dssp = spdao.layDsSanPhamConHang();

		for (SanPham i : dssp) {
			dataModel.addRow(new Object[] { i.getMasanpham(), i.getTensanpham(),
					new DecimalFormat("###,###,###").format((int) Math.floor(i.getGiadonvi())), i.getSoluong() });
		}
	}

	/**
	 * Cập nhật bảng danh sách sản phẩm sau khi thực hiện tìm
	 * 
	 * @param dssp
	 */
	public static void updateTableDSSanPhamTim(ArrayList<SanPham> dssp) {
		for (SanPham i : dssp) {
			dataModel.addRow(new Object[] { i.getMasanpham(), i.getTensanpham(),
					new DecimalFormat("###,###,###").format((int) Math.floor(i.getGiadonvi())), i.getSoluong() });
		}
	}

	public static void deleteTableDSSanPham() {
		int dem = tblDSSanPham.getRowCount();
		for (int i = 0; i < dem; i++) {
			dataModel.removeRow(0);
			;
		}

	}

	/**
	 * Tính tổng tiền của table
	 * 
	 * @param tbl
	 * @return
	 */
	public static String Tinhtongtien(JTable tbl) {
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
				if (x < 0) {
					JOptionPane.showMessageDialog(null, "Số lượng đặt trước phải lớn hơn 0. Ví dụ: 1,2,3..",
							"Thông báo", JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Số lượng mua phải là chữ số", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập vào ô số lượng", "Thông báo", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;

	}

	public static void setThongTinKhachHang(String makh, String tenkh, String sdt) {
		txtmakhachhang.setText(makh);
		txttenkhachhang.setText(tenkh);
		txtsdt.setText(sdt);
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		if (e.getColumn() == 2) {
			int r = e.getFirstRow();
			soluongmoi = Integer.parseInt(dataModelChitietPD.getValueAt(r, 2).toString());
			if (soluongmoi <= 0) {
				JOptionPane.showMessageDialog(null, "Số lượng không được bé hơn hoặc bằng 0!");
				soluongmoi = soluongcu;
				dataModelChitietPD.setValueAt(soluongmoi, r, 2);
			}
			SanPham sp = new SanPhamDao().timSanPham(dataModelChitietPD.getValueAt(r, 0).toString());

			if (soluongcu > soluongmoi) {
				int chenhlech = soluongcu - soluongmoi;
				sp.setSoluong(sp.getSoluong() + chenhlech);
				dataModelChitietPD.setValueAt(
						new DecimalFormat("###,###,###").format((int) Math.floor(soluongmoi * sp.getGiadonvi())), r, 3);
				SanPhamDao spdao = new SanPhamDao();
				spdao.suaSanPham(sp);
				txtTongtien.setText(new DecimalFormat("###,###,###")
						.format((int) Math.floor(Double.valueOf(Tinhtongtien(tblDSCTPhieuDat)))));

				capnhatDSSPCuaHeThong();
				tblDSCTPhieuDat.requestFocus(false);

			} else if (soluongcu < soluongmoi) {
				int chenhlech = soluongmoi - soluongcu;
				sp.setSoluong(sp.getSoluong() - chenhlech);
				dataModelChitietPD.setValueAt(
						new DecimalFormat("###,###,###").format((int) Math.floor(soluongmoi * sp.getGiadonvi())), r, 3);
				SanPhamDao spdao = new SanPhamDao();
				spdao.suaSanPham(sp);
				txtTongtien.setText(new DecimalFormat("###,###,###")
						.format((int) Math.floor(Double.valueOf(Tinhtongtien(tblDSCTPhieuDat)))));

				capnhatDSSPCuaHeThong();
				tblDSCTPhieuDat.requestFocus(false);

			} else if (soluongcu == soluongmoi) {
				sp.setSoluong(sp.getSoluong());
			}

		}

	}
}
