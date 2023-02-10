package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Others.MyButton;
import dao.CTHoaDonDao;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.NhanVienDao;
import dao.SanPhamDao;
import entity.CTHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;

/**
 * Giao diện danh sách hóa đơn
 * 
 * @author Võ Phước Lưu - Phan Võ Trọng - Nguyễn Phạm Công Nhật
 *
 */
public class GUI_DSHoaDon extends JPanel {
	private static final long serialVersionUID = 1L;
	private static JTable table;
	private JLabel txtMaHD;
	private JLabel txtNgayLapHD;
	private JTable tblCTHoaDon;
	private JLabel txtTenNV;
	private JLabel txtMaNV;
	private JLabel txtTongTien;
	private JComboBox<String> cboNhanVien;
	private JComboBox<String> comboBox;
	private JLabel lblSoLuongHD;
	private static DefaultTableModel dataModelChitietHD;
	private static DefaultTableModel dataModelDSHD;

	/**
	 * Create the panel.
	 */

	@SuppressWarnings("serial")
	public GUI_DSHoaDon() {
		setBackground(new Color(255, 255, 255));
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setTitle("Danh sách Nhân viên");
		frm.getContentPane().setBackground(new Color(255, 255, 255));
		frm.setBackground(new Color(255, 255, 255));
		frm.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		frm.getContentPane().add(this);
		setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBackground(new Color(255, 255, 255));
		add(splitPane);

		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(800, 10));
		panel.setMaximumSize(new Dimension(700, 32767));
		panel.setBackground(Color.WHITE);
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setBackground(new Color(255, 255, 255));
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane_1, BorderLayout.CENTER);
		String[] title = { "Mã HD", "Tên Khách hàng", "Số điện thoại", "Ngày Lập", "Tổng tiền" };

		JScrollPane scrollPane = new JScrollPane(table = new JTable(dataModelDSHD = new DefaultTableModel(title, 0) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return canEdit[column];
			}
		}));
		table.setAutoCreateRowSorter(true);
		lblSoLuongHD = new JLabel(
				String.valueOf(table.getRowCount()) == null ? "0" : String.valueOf(table.getRowCount()));
		scrollPane.setOpaque(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setRowHeight(30);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setText();
			}
		});
		scrollPane.setBackground(new Color(255, 255, 255));
		splitPane_1.setRightComponent(scrollPane);

		table.setBackground(new Color(255, 255, 255));
		table.getColumnModel().getColumn(2).setPreferredWidth(91);
		scrollPane.setViewportView(table);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		splitPane_1.setLeftComponent(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_2 = new JLabel("Danh Sách Hóa đơn");
		lblNewLabel_2.setMinimumSize(new Dimension(700, 13));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setMaximumSize(new Dimension(700, 13));
		lblNewLabel_2.setForeground(new Color(25, 25, 112));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel_2.setBackground(Color.WHITE);
		panel_4.add(lblNewLabel_2);
		JPanel pnlSideLeft = new JPanel();
		pnlSideLeft.setPreferredSize(new Dimension(150, 10));
		pnlSideLeft.setBackground(Color.WHITE);
		
		MyButton btnTaiLai = new MyButton("Tải lại");
		pnlSideLeft.add(btnTaiLai);
		btnTaiLai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnTaiLai.setForeground(Color.WHITE);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnTaiLai.setForeground(Color.black);
			}
		});
		btnTaiLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HoaDonDao hddao = new HoaDonDao();
				ArrayList<HoaDon> dshd = null;
				if (comboBox.getSelectedIndex() == 0) {
					try {
						dshd = hddao.layDsHoaDonTheoThoiGian(0, GUIChinh_NhanVien.nv.getManhanvien());
					} catch (Exception er) {
						dshd = hddao.layDsHoaDonTheoThoiGian(0, cboNhanVien.getSelectedItem().toString());
					}
				} else if (comboBox.getSelectedIndex() == 1) {
					try {
						dshd = hddao.layDsHoaDonTheoThoiGian(7, GUIChinh_NhanVien.nv.getManhanvien());
					} catch (Exception er) {
						dshd = hddao.layDsHoaDonTheoThoiGian(7, cboNhanVien.getSelectedItem().toString());
					}
				} else if (comboBox.getSelectedIndex() == 2) {
					try {
						dshd = hddao.layDsHoaDonTheoThoiGian(30, GUIChinh_NhanVien.nv.getManhanvien());
					} catch (Exception er) {
						dshd = hddao.layDsHoaDonTheoThoiGian(30, cboNhanVien.getSelectedItem().toString());
					}
				} else if (comboBox.getSelectedIndex() == 3) {
					try {
						dshd = hddao.layDsHoaDon(GUIChinh_NhanVien.nv.getManhanvien());
					} catch (Exception er) {
						dshd = hddao.layDsHoaDon(cboNhanVien.getSelectedItem().toString());
					}
				}
				try {
					GUIChinh_QuanLy.nv.getManhanvien();
					if (cboNhanVien.getSelectedIndex() == -1 || cboNhanVien.getSelectedIndex() == 0) {
						if (comboBox.getSelectedIndex() == 3)
							dshd = hddao.layDsHoaDon();
						else if (comboBox.getSelectedIndex() == 0)
							dshd = hddao.layDsHoaDonTheoThoiGian(0);
						else if (comboBox.getSelectedIndex() == 1)
							dshd = hddao.layDsHoaDonTheoThoiGian(7);
						else if (comboBox.getSelectedIndex() == 2)
							dshd = hddao.layDsHoaDonTheoThoiGian(30);
					}
				} catch (Exception er) {

				}
				deleteTableDSHD();
				updateTableDSHDTheoThoiGian(dshd);
				lblSoLuongHD.setText(
						String.valueOf(table.getRowCount()) == null ? "0" : String.valueOf(table.getRowCount()));
			}
		});
		btnTaiLai.setText("Tải lại");
		btnTaiLai.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnTaiLai.setIcon(new ImageIcon(GUI_DSHoaDon.class.getResource("/images/tailai.png")));
		//panel_4.add(btnTaiLai, BorderLayout.WEST);
		
		JPanel pnlLoc = new JPanel();
		pnlLoc.setBorder(new TitledBorder(null, "L\u1ECDc", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlLoc.setBackground(Color.WHITE);
		pnlSideLeft.add(pnlLoc);
		//panel_4.add(pnlLoc, BorderLayout.EAST);
		NhanVienDao nvDao = new NhanVienDao();
		cboNhanVien = new JComboBox<String>();
		cboNhanVien.setModel(new DefaultComboBoxModel<String>(new String[] { "Tất cả nhân viên" }));
		ArrayList<NhanVien> listnv = nvDao.layDsNhanVien();
		for (NhanVien nv : listnv) {
			cboNhanVien.addItem(nv.getManhanvien());
		}
		comboBox = new JComboBox<String>();
		comboBox.setBackground(Color.WHITE);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HoaDonDao hddao = new HoaDonDao();
				ArrayList<HoaDon> dshd = new ArrayList<>();
				if (comboBox.getSelectedIndex() == 0) {
					try {
						dshd = hddao.layDsHoaDonTheoThoiGian(0, GUIChinh_NhanVien.nv.getManhanvien());
					} catch (Exception er) {
						if (cboNhanVien.getSelectedIndex() == 0)
							dshd = hddao.layDsHoaDonTheoThoiGian(0);
						else
						dshd = hddao.layDsHoaDonTheoThoiGian(0, cboNhanVien.getSelectedItem().toString());
					}
				} else if (comboBox.getSelectedIndex() == 1) {
					try {
						dshd = hddao.layDsHoaDonTheoThoiGian(7, GUIChinh_NhanVien.nv.getManhanvien());
					} catch (Exception er) {
						if (cboNhanVien.getSelectedIndex() == 0)
							dshd = hddao.layDsHoaDonTheoThoiGian(7);
						else
						dshd = hddao.layDsHoaDonTheoThoiGian(7, cboNhanVien.getSelectedItem().toString());
					}
				} else if (comboBox.getSelectedIndex() == 2) {
					try {
						dshd = hddao.layDsHoaDonTheoThoiGian(30, GUIChinh_NhanVien.nv.getManhanvien());
					} catch (Exception er) {
						if (cboNhanVien.getSelectedIndex() == 0)
							dshd = hddao.layDsHoaDonTheoThoiGian(30);
						else
						dshd = hddao.layDsHoaDonTheoThoiGian(30, cboNhanVien.getSelectedItem().toString());
					}
				} else if (comboBox.getSelectedIndex() == 3) {
					try {
						dshd = hddao.layDsHoaDon(GUIChinh_NhanVien.nv.getManhanvien());
					} catch (Exception er) {
						
						dshd = hddao.layDsHoaDon(cboNhanVien.getSelectedItem().toString());
					}
				}
				//Xử lý giao diện quản lý
				try {
					GUIChinh_QuanLy.nv.getManhanvien();
					if (cboNhanVien.getSelectedIndex() == -1 || cboNhanVien.getSelectedIndex() == 0) {
						if (comboBox.getSelectedIndex() == 3)
							dshd = hddao.layDsHoaDon();
						else if (comboBox.getSelectedIndex() == 0)
							dshd = hddao.layDsHoaDonTheoThoiGian(0);
						else if (comboBox.getSelectedIndex() == 1)
							dshd = hddao.layDsHoaDonTheoThoiGian(7);
						else if (comboBox.getSelectedIndex() == 2)
							dshd = hddao.layDsHoaDonTheoThoiGian(30);
					}
				} catch (Exception er) {

				}
				deleteTableDSHD();
				updateTableDSHDTheoThoiGian(dshd);
				lblSoLuongHD.setText(
						String.valueOf(table.getRowCount()) == null ? "0" : String.valueOf(table.getRowCount()));
			}
		});

		cboNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HoaDonDao hddao = new HoaDonDao();
				ArrayList<HoaDon> dshd = null;
				if (comboBox.getSelectedIndex() == 0) {
					dshd = hddao.layDsHoaDonTheoThoiGian(0, cboNhanVien.getSelectedItem().toString());
				} else if (comboBox.getSelectedIndex() == 1) {
					dshd = hddao.layDsHoaDonTheoThoiGian(7, cboNhanVien.getSelectedItem().toString());
				} else if (comboBox.getSelectedIndex() == 2) {
					dshd = hddao.layDsHoaDonTheoThoiGian(30, cboNhanVien.getSelectedItem().toString());
				} else if (comboBox.getSelectedIndex() == 3) {
					dshd = hddao.layDsHoaDon(cboNhanVien.getSelectedItem().toString());
				}
				if (cboNhanVien.getSelectedIndex() == -1 || cboNhanVien.getSelectedIndex() == 0) {
					if (comboBox.getSelectedIndex() == 3)
						dshd = hddao.layDsHoaDon();
					else if (comboBox.getSelectedIndex() == 0)
						dshd = hddao.layDsHoaDonTheoThoiGian(0);
					else if (comboBox.getSelectedIndex() == 1)
						dshd = hddao.layDsHoaDonTheoThoiGian(7);
					else if (comboBox.getSelectedIndex() == 2)
						dshd = hddao.layDsHoaDonTheoThoiGian(30);
				}
				deleteTableDSHD();
				updateTableDSHDTheoThoiGian(dshd);
				lblSoLuongHD.setText(
						String.valueOf(table.getRowCount()) == null ? "0" : String.valueOf(table.getRowCount()));
			}

		});
		try {
			GUIChinh_NhanVien.nv.toString();
		} catch (Exception e) {
			GUIChinh_QuanLy.nv.toString();
			pnlLoc.setLayout(new GridLayout(0,1,0,10));
			pnlLoc.add(cboNhanVien);
		}
		comboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Hôm nay", "7 ngày trước", "30 ngày trước", "Toàn bộ" }));
		comboBox.setSelectedIndex(0);
		pnlLoc.add(comboBox);

		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_7, BorderLayout.SOUTH);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setPreferredSize(new Dimension(140, 0));
		panel_7.add(horizontalStrut_2);

		JLabel lblNewLabel_5 = new JLabel("Số lượng hóa đơn:");
		panel_7.add(lblNewLabel_5);

		panel_7.add(lblSoLuongHD);
		
		
		panel.add(pnlSideLeft, BorderLayout.WEST);

		JPanel pnlTTSanPham = new JPanel();

		pnlTTSanPham.setBackground(new Color(255, 255, 255));
		splitPane.setRightComponent(pnlTTSanPham);
		pnlTTSanPham.setLayout(new BorderLayout(0, 0));
		pnlTTSanPham.setMaximumSize(new Dimension(300, 1080));

		JLabel lblNewLabel = new JLabel("Thông tin Hóa đơn");
		lblNewLabel.setForeground(new Color(25, 25, 112));
		lblNewLabel.setMaximumSize(new Dimension(400, 13));
		lblNewLabel.setMinimumSize(new Dimension(400, 13));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlTTSanPham.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		pnlTTSanPham.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_2 = new JSplitPane();
		splitPane_2.setBorder(null);
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_3.add(splitPane_2);

		String[] titleCTHD = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Số lượng", "Thành tiền" };
		JScrollPane scrollPane_1 = new JScrollPane(
				tblCTHoaDon = new JTable(dataModelChitietHD = new DefaultTableModel(titleCTHD, 0) {
					boolean[] canEdit = new boolean[] { false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return canEdit[column];
					}
				}), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setOpaque(true);
		scrollPane_1.setBackground(Color.WHITE);
		tblCTHoaDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCTHoaDon.setRowHeight(30);
		tblCTHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		splitPane_2.setRightComponent(scrollPane_1);

		scrollPane_1.setViewportView(tblCTHoaDon);

		JPanel pnlThongTinSP = new JPanel();
		splitPane_2.setLeftComponent(pnlThongTinSP);
		pnlThongTinSP.setMaximumSize(new Dimension(450, 32767));
		pnlThongTinSP.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.setLayout(new GridLayout(5, 2, 15, 20));

		JLabel lblMaSP = new JLabel("Mã hóa đơn:");
		lblMaSP.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblMaSP.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(lblMaSP);

		txtMaHD = new JLabel();
		txtMaHD.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtMaHD.setText("unknown");
		txtMaHD.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(txtMaHD);

		JLabel lblNewLabel_7 = new JLabel("Mã nhân viên:");
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.BOLD, 15));
		pnlThongTinSP.add(lblNewLabel_7);

		txtMaNV = new JLabel();
		txtMaNV.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtMaNV.setText("unknown");
		pnlThongTinSP.add(txtMaNV);

		JLabel lblNewLabel_6 = new JLabel("Tên nhân viên");
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.BOLD, 15));
		pnlThongTinSP.add(lblNewLabel_6);

		txtTenNV = new JLabel();
		txtTenNV.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtTenNV.setText("unknown");
		pnlThongTinSP.add(txtTenNV);

		JLabel lblNewLabel_3 = new JLabel("Ngày lập hóa đơn:");
		pnlThongTinSP.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_3.setBackground(new Color(255, 255, 255));

		txtNgayLapHD = new JLabel();
		txtNgayLapHD.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtNgayLapHD.setText("unknown");
		pnlThongTinSP.add(txtNgayLapHD);
		txtNgayLapHD.setBackground(new Color(255, 255, 255));

		JLabel lblNewLabel_1 = new JLabel("Chi tiết hóa đơn");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
		pnlThongTinSP.add(lblNewLabel_1);

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel_4 = new JLabel("Tổng tiền:");
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel_4.setBackground(Color.WHITE);
		panel_5.add(lblNewLabel_4);

		txtTongTien = new JLabel();
		txtTongTien.setBackground(Color.WHITE);
		txtTongTien.setOpaque(true);
		txtTongTien.setFont(new Font("Segoe UI", Font.BOLD, 20));
		panel_5.add(txtTongTien);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBackground(new Color(255, 255, 255));
		pnlTTSanPham.add(verticalStrut, BorderLayout.SOUTH);

		Component horizontalStrut = Box.createHorizontalStrut(17);
		horizontalStrut.setBackground(new Color(255, 255, 255));
		pnlTTSanPham.add(horizontalStrut, BorderLayout.EAST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(16);
		horizontalStrut_1.setBackground(new Color(255, 255, 255));
		pnlTTSanPham.add(horizontalStrut_1, BorderLayout.WEST);

		ArrayList<HoaDon> dshd = null;
		try {
			dshd = new HoaDonDao().layDsHoaDonTheoThoiGian(0, GUIChinh_NhanVien.nv.getManhanvien());
		} catch (Exception e) {
			cboNhanVien.setSelectedIndex(1);
			dshd = new HoaDonDao().layDsHoaDonTheoThoiGian(0, GUIChinh_QuanLy.nv.getManhanvien());
		}
		deleteTableDSHD();
		updateTableDSHDTheoThoiGian(dshd);
	}

	public static void updateTableCTHD() {
		CTHoaDonDao ctdao = new CTHoaDonDao();

		HoaDonDao hddao = new HoaDonDao();
		int r = table.getSelectedRow();
		if (r == -1) {

		} else {
			HoaDon hd = hddao.timHoaDon((String) table.getValueAt(r, 0));
			ArrayList<CTHoaDon> dshd = ctdao.layDsCTHoaDonTheoHoaDon(hd.getMahoadon());
			for (CTHoaDon i : dshd) {
				SanPham sp = new SanPhamDao().timSanPham(i.getSp().getMasanpham());
				dataModelChitietHD.addRow(new Object[] { i.getSp().getMasanpham(), sp.getTensanpham(),
						i.getSoluongsanpham(), new DecimalFormat("###,###,###").format(i.getThanhtien()) });
			}
		}
	}

	public static void deleteTableCTHD() {
		int dem = dataModelChitietHD.getRowCount();
		for (int i = 0; i < dem; i++) {
			dataModelChitietHD.removeRow(0);
			;
		}
	}

	public static void deleteTableDSHD() {
		int dem = dataModelDSHD.getRowCount();
		for (int i = 0; i < dem; i++) {
			dataModelDSHD.removeRow(0);
			;
		}
	}

	public static void updateTableDSHDTheoThoiGian(ArrayList<HoaDon> dshd) {
		for (HoaDon i : dshd) {
			KhachHangDao khdao = new KhachHangDao();
			KhachHang kh = new KhachHang();
			if (khdao.timKhachHang(i.getKhachhang().getMakh()) == null) {
				dataModelDSHD.addRow(new Object[] { i.getMahoadon(), "", "",
						i.getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
								+ i.getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[1] + " "
								+ i.getNgaylaphoadon().toString().split("-")[2].split(" ")[0] + "-"
								+ i.getNgaylaphoadon().toString().split("-")[1] + "-"
								+ i.getNgaylaphoadon().toString().split("-")[0],
						new DecimalFormat("###,###,###").format(i.getTongtienhoadon()) });
			} else {
				kh = khdao.timKhachHang(i.getKhachhang().getMakh());
				dataModelDSHD.addRow(new Object[] { i.getMahoadon(), kh.getTenkhachhang(), kh.getSodienthoai(),
						i.getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
								+ i.getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[1] + " "
								+ i.getNgaylaphoadon().toString().split("-")[2].split(" ")[0] + "-"
								+ i.getNgaylaphoadon().toString().split("-")[1] + "-"
								+ i.getNgaylaphoadon().toString().split("-")[0],
						new DecimalFormat("###,###,###").format(i.getTongtienhoadon()) });
			}
		}

	}

	public static void updateTableDSHD() {
		HoaDonDao hddao = new HoaDonDao();
		ArrayList<HoaDon> dshd = hddao.layDsHoaDon();
		// "Mã Phiếu","Tên Khách hàng","Số điện thoại", "Ngày đặt","Tổng tiền"

		for (HoaDon i : dshd) {
			KhachHangDao khdao = new KhachHangDao();
			KhachHang kh = new KhachHang();
			if (khdao.timKhachHang(i.getKhachhang().getMakh()) == null) {
				dataModelDSHD.addRow(new Object[] { i.getMahoadon(), "", "",
						i.getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
								+ i.getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[1] + " "
								+ i.getNgaylaphoadon().toString().split("-")[2].split(" ")[0] + "-"
								+ i.getNgaylaphoadon().toString().split("-")[1] + "-"
								+ i.getNgaylaphoadon().toString().split("-")[0],
						new DecimalFormat("###,###,###").format(i.getTongtienhoadon()) });
			} else {
				kh = khdao.timKhachHang(i.getKhachhang().getMakh());
				dataModelDSHD.addRow(new Object[] { i.getMahoadon(), kh.getTenkhachhang(), kh.getSodienthoai(),
						i.getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
								+ i.getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[1] + " "
								+ i.getNgaylaphoadon().toString().split("-")[2].split(" ")[0] + "-"
								+ i.getNgaylaphoadon().toString().split("-")[1] + "-"
								+ i.getNgaylaphoadon().toString().split("-")[0],
						new DecimalFormat("###,###,###").format(i.getTongtienhoadon()) });
			}
		}

	}

	public static void updateTableTimDSHoaDon(ArrayList<HoaDon> list) {
		ArrayList<HoaDon> dshd = list;

		for (HoaDon i : dshd) {
			KhachHangDao khdao = new KhachHangDao();
			KhachHang kh = new KhachHang();
			if (khdao.timKhachHang(i.getKhachhang().getMakh()) == null) {

				dataModelDSHD.addRow(new Object[] { i.getMahoadon(), "", "",
						i.getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
								+ i.getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[1] + " "
								+ i.getNgaylaphoadon().toString().split("-")[2].split(" ")[0] + "-"
								+ i.getNgaylaphoadon().toString().split("-")[1] + "-"
								+ i.getNgaylaphoadon().toString().split("-")[0],
						new DecimalFormat("###,###,###").format(i.getTongtienhoadon()) });
			} else {
				kh = khdao.timKhachHang(i.getKhachhang().getMakh());
				dataModelDSHD.addRow(new Object[] { i.getMahoadon(), kh.getTenkhachhang(), kh.getSodienthoai(),
						i.getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
								+ i.getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[1] + " "
								+ i.getNgaylaphoadon().toString().split("-")[2].split(" ")[0] + "-"
								+ i.getNgaylaphoadon().toString().split("-")[1] + "-"
								+ i.getNgaylaphoadon().toString().split("-")[0],
						new DecimalFormat("###,###,###").format(i.getTongtienhoadon()) });
			}
		}
	}

	public void setText() {

		int row = table.getSelectedRow();
		if (row != -1) {
			NhanVienDao nvdao = new NhanVienDao();
			HoaDonDao hddao = new HoaDonDao();
			HoaDon hd = hddao.timHoaDon((String) dataModelDSHD.getValueAt(row, 0));
			NhanVien nv = nvdao.timNhanVien(hd.getNhanvien().getManhanvien());

			txtMaHD.setText(hd.getMahoadon());
			txtTenNV.setText(nv.getHoten());
			txtMaNV.setText(nv.getManhanvien());
			txtNgayLapHD.setText(dataModelDSHD.getValueAt(row, 3).toString());
			txtTongTien.setText(dataModelDSHD.getValueAt(row, 4).toString());

			deleteTableCTHD();
			updateTableCTHD();
		}

	}
}
