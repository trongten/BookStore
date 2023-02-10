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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Others.MyButton;
import Others.createHoaDon;
import dao.CTHoaDonDao;
import dao.CTPhieuDatTruocDao;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.NhanVienDao;
import dao.PhieuDatTruocDao;
import dao.SanPhamDao;
import entity.CTHoaDon;
import entity.CTPhieuDatTruoc;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatTruoc;
import entity.SanPham;

/**
 * Giao diện danh sách phiếu đặt trước
 * 
 * @author Võ Phước Lưu - Phan Võ Trọng - Nguyễn Phạm Công Nhật
 */
public class GUI_DSPhieuDat extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTable tableDSPhieuDat;
	private static JLabel txtMaPhieu;
	private JLabel txtNgayDat;
	private JLabel txtNgayNhan;
	private JTable tblCTPhieuDat;
	private JLabel txtTenKH;
	private JLabel txtSDT;
	private JLabel txtTongTien;
	private static DefaultTableModel dataModelChitietPD;
	private static DefaultTableModel dataModelDSPD;
	private JLabel txtTrangThaiPD;
	private String mahoadontam = "";
	int a = 1;
	int stt = 0;
	private String mahd = "";
	private JComboBox<String> cboNhanVien;
	private JComboBox<String> comboBox;
	private JLabel lblSoluongPhieuDat;
	private MyButton btnHuyPhieuDat;
	private JLabel lblConHan = new JLabel("(0)");
	private JLabel lblSapHetHan = new JLabel("(0)");
	private JLabel lblHetHan = new JLabel("(0)");

	private int soluongphieudatSapHetHan = 0;
	private int soluongphieudatHetHan = 0;

	/**
	 * Create the panel.
	 */

	@SuppressWarnings("serial")
	public GUI_DSPhieuDat() {
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
		panel.setBackground(new Color(255, 255, 255));
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setBackground(Color.WHITE);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane_1, BorderLayout.CENTER);

		String[] title = { "Mã Phiếu", "Tên Khách hàng", "Số điện thoại", "Ngày đặt", "Ngày nhận", "Tổng tiền" };

		JScrollPane scrollPane = new JScrollPane(
				tableDSPhieuDat = new JTable(dataModelDSPD = new DefaultTableModel(title, 0) {
					boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return canEdit[column];
					}
				}) {

					@SuppressWarnings("deprecation")
					@Override
					public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
						Component c = super.prepareRenderer(renderer, row, column);
						if (column == 0) {

							String ngaynhanbang = dataModelDSPD.getValueAt(row, 4).toString();
							ngaynhan = new Date(Integer.parseInt(ngaynhanbang.split("-")[2]) - 1900,
									Integer.parseInt(ngaynhanbang.split("-")[1]) - 1,
									Integer.parseInt(ngaynhanbang.split("-")[0].split(" ")[1]),
									Integer.parseInt(ngaynhanbang.split("-")[0].split(" ")[0].split("h")[0]),
									Integer.parseInt(ngaynhanbang.split("-")[0].split(" ")[0].split("h")[1]), 0);

							float d = ngaynhan.getTime() - new Date(System.currentTimeMillis()).getTime();// ngaylap.getTime();
							ngaychenhlech = d / (1000 * 60 * 60 * 24);

							if (ngaychenhlech > 1) {
								c.setBackground(Color.white);
								c.setForeground(Color.black);
							} else if (ngaychenhlech >= -1 && ngaychenhlech < 1) {
								c.setBackground(Color.yellow);
								c.setForeground(Color.black);
							} else if (ngaychenhlech < -1) {
								c.setBackground(Color.red);
								c.setForeground(Color.white);
							}
						}
						return c;
					}
				});
		tableDSPhieuDat.setAutoCreateRowSorter(true);
		tableDSPhieuDat.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableDSPhieuDat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDSPhieuDat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setMinimumSize(new Dimension(700, 23));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tableDSPhieuDat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setText();
			}
		});

		scrollPane.setBackground(new Color(255, 255, 255));
		splitPane_1.setRightComponent(scrollPane);

		tableDSPhieuDat.setRowHeight(30);
		tableDSPhieuDat.setBackground(new Color(255, 255, 255));
		tableDSPhieuDat.getColumnModel().getColumn(2).setPreferredWidth(91);

		scrollPane.setViewportView(tableDSPhieuDat);

		JPanel panel_10 = new JPanel();
		panel_10.setMinimumSize(new Dimension(700, 10));
		splitPane_1.setLeftComponent(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_2 = new JLabel("Danh Sách Sách Phiếu đặt");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setMinimumSize(new Dimension(700, 13));
		lblNewLabel_2.setMaximumSize(new Dimension(700, 13));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(25, 25, 112));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel_2.setBackground(Color.WHITE);
		panel_10.add(lblNewLabel_2);

		JPanel pnlLoc = new JPanel();
		pnlLoc.setBackground(Color.WHITE);
		pnlLoc.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "L\u1ECDc",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		// panel_10.add(pnlLoc, BorderLayout.EAST);
		comboBox = new JComboBox<String>();
		comboBox.setOpaque(false);
		comboBox.setBackground(Color.WHITE);
		comboBox.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Hôm nay", "3 ngày trước", "7 ngày trước", "Tất cả" }));
		comboBox.setSelectedIndex(0);

		NhanVienDao nvDao = new NhanVienDao();
		cboNhanVien = new JComboBox<String>();
		cboNhanVien.setOpaque(false);
		cboNhanVien.setModel(new DefaultComboBoxModel<String>(new String[] { "Tất cả nhân viên" }));
		ArrayList<NhanVien> listnv = nvDao.layDsNhanVien();
		for (NhanVien nv : listnv) {
			cboNhanVien.addItem(nv.getManhanvien());
		}
		// set chọn vào cboNhanVien là NV001
		try {
			cboNhanVien.setSelectedIndex(1);
		} catch (Exception e) {
			// empty
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhieuDatTruocDao pddao = new PhieuDatTruocDao();
				ArrayList<PhieuDatTruoc> dspd = new ArrayList<PhieuDatTruoc>();
				if (comboBox.getSelectedIndex() == 0) {
					try {
						dspd = pddao.layDsPhieuDatTheoThoiGian(0, GUIChinh_NhanVien.nv.getManhanvien());
					} catch (Exception er) {
						if (cboNhanVien.getSelectedIndex() == 0)
							dspd = pddao.layDsPhieuDatTheoThoiGian(0);
						else
							dspd = pddao.layDsPhieuDatTheoThoiGian(0, cboNhanVien.getSelectedItem().toString());
					}
				} else if (comboBox.getSelectedIndex() == 1) {
					try {
						dspd = pddao.layDsPhieuDatTheoThoiGian(3, GUIChinh_NhanVien.nv.getManhanvien());
					} catch (Exception er) {
						if (cboNhanVien.getSelectedIndex() == 0)
							dspd = pddao.layDsPhieuDatTheoThoiGian(3);
						else
							dspd = pddao.layDsPhieuDatTheoThoiGian(3, cboNhanVien.getSelectedItem().toString());
					}
				} else if (comboBox.getSelectedIndex() == 2) {
					try {
						dspd = pddao.layDsPhieuDatTheoThoiGian(7, GUIChinh_NhanVien.nv.getManhanvien());
					} catch (Exception er) {
						if (cboNhanVien.getSelectedIndex() == 0)
							dspd = pddao.layDsPhieuDatTheoThoiGian(7);
						else
							dspd = pddao.layDsPhieuDatTheoThoiGian(7, cboNhanVien.getSelectedItem().toString());
					}
				} else if (comboBox.getSelectedIndex() == 3) {
					try {
						dspd = pddao.layDsPhieuDat(GUIChinh_NhanVien.nv.getManhanvien());
					} catch (Exception er) {
						dspd = pddao.layDsPhieuDat(cboNhanVien.getSelectedItem().toString());
					}
				}
				// Xử lý giao diện quản lý
				try {
					GUIChinh_QuanLy.nv.getManhanvien();
					if (cboNhanVien.getSelectedIndex() == -1 || cboNhanVien.getSelectedIndex() == 0) {
						if (comboBox.getSelectedIndex() == 3)
							dspd = pddao.layDsPhieuDatTruoc();
						else if (comboBox.getSelectedIndex() == 0)
							dspd = pddao.layDsPhieuDatTheoThoiGian(0);
						else if (comboBox.getSelectedIndex() == 1)
							dspd = pddao.layDsPhieuDatTheoThoiGian(7);
						else if (comboBox.getSelectedIndex() == 2)
							dspd = pddao.layDsPhieuDatTheoThoiGian(30);
					}
				} catch (Exception er) {

				}
				deleteTableDSPD();
				updateTableDSPDTheoThoiGian(dspd);
				lblSoluongPhieuDat.setText(String.valueOf(tableDSPhieuDat.getRowCount()) == null ? "0"
						: String.valueOf(tableDSPhieuDat.getRowCount()));

			}
		});

		cboNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhieuDatTruocDao pddao = new PhieuDatTruocDao();
				ArrayList<PhieuDatTruoc> dspd = null;
				if (comboBox.getSelectedIndex() == 0) {
					dspd = pddao.layDsPhieuDatTheoThoiGian(0, cboNhanVien.getSelectedItem().toString());
				} else if (comboBox.getSelectedIndex() == 1) {
					dspd = pddao.layDsPhieuDatTheoThoiGian(7, cboNhanVien.getSelectedItem().toString());
				} else if (comboBox.getSelectedIndex() == 2) {
					dspd = pddao.layDsPhieuDatTheoThoiGian(30, cboNhanVien.getSelectedItem().toString());
				} else if (comboBox.getSelectedIndex() == 3) {
					dspd = pddao.layDsPhieuDat(cboNhanVien.getSelectedItem().toString());
				}
				if (cboNhanVien.getSelectedIndex() == -1 || cboNhanVien.getSelectedIndex() == 0) {
					if (comboBox.getSelectedIndex() == 3)
						dspd = pddao.layDsPhieuDatTruoc();
					else if (comboBox.getSelectedIndex() == 0)
						dspd = pddao.layDsPhieuDatTheoThoiGian(0);
					else if (comboBox.getSelectedIndex() == 1)
						dspd = pddao.layDsPhieuDatTheoThoiGian(7);
					else if (comboBox.getSelectedIndex() == 2)
						dspd = pddao.layDsPhieuDatTheoThoiGian(30);
				}
				deleteTableDSPD();
				updateTableDSPDTheoThoiGian(dspd);
				lblSoluongPhieuDat.setText(String.valueOf(tableDSPhieuDat.getRowCount()) == null ? "0"
						: String.valueOf(tableDSPhieuDat.getRowCount()));

			}
		});
		try {
			GUIChinh_NhanVien.nv.toString();
		} catch (Exception e) {
			GUIChinh_QuanLy.nv.toString();
			pnlLoc.setLayout(new GridLayout(0, 1, 0, 10));
			pnlLoc.add(cboNhanVien);

		}
		pnlLoc.add(comboBox);

		JPanel panel_13 = new JPanel();
		panel.add(panel_13, BorderLayout.SOUTH);
		panel_13.setLayout(new BoxLayout(panel_13, BoxLayout.X_AXIS));

		JPanel panel_17 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_17.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_13.add(panel_17);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalStrut_4.setPreferredSize(new Dimension(140, 0));
		panel_17.add(horizontalStrut_4);

		JLabel lblNewLabel_8 = new JLabel("Số lượng phiếu đặt:");
		panel_17.add(lblNewLabel_8);
		lblSoluongPhieuDat = new JLabel("");
		panel_17.add(lblSoluongPhieuDat);

		lblSoluongPhieuDat.setText(String.valueOf(tableDSPhieuDat.getRowCount()) == null ? "0"
				: String.valueOf(tableDSPhieuDat.getRowCount()));
		lblSoluongPhieuDat.addPropertyChangeListener(new PropertyChangeListener() {
			private int soluongconhan;

			@SuppressWarnings("deprecation")
			public void propertyChange(PropertyChangeEvent evt) {
				for (int i = 0; i < tableDSPhieuDat.getRowCount(); i++) {
					// HH(h)mm dd-mm-yyyy
					String ngaynhanbang = dataModelDSPD.getValueAt(i, 4).toString();
					ngaynhan = new Date(Integer.parseInt(ngaynhanbang.split("-")[2]) - 1900,
							Integer.parseInt(ngaynhanbang.split("-")[1]) - 1,
							Integer.parseInt(ngaynhanbang.split("-")[0].split(" ")[1]),
							Integer.parseInt(ngaynhanbang.split("-")[0].split(" ")[0].split("h")[0]),
							Integer.parseInt(ngaynhanbang.split("-")[0].split(" ")[0].split("h")[1]), 0);

					float d = ngaynhan.getTime() - new Date(System.currentTimeMillis()).getTime();// ngaylap.getTime();
					ngaychenhlech = d / (1000 * 60 * 60 * 24);

					if (ngaychenhlech > 1) {
						soluongconhan++;
					} else if (ngaychenhlech >= -1 && ngaychenhlech < 1) {
						soluongphieudatSapHetHan++;
					} else if (ngaychenhlech < -1) {
						soluongphieudatHetHan++;
					}
				}
				lblHetHan.setText(String.valueOf(tableDSPhieuDat.getRowCount()) == null ? "(0)"
						: "(" + String.valueOf(soluongphieudatHetHan) + ")");
				lblSapHetHan.setText(String.valueOf(tableDSPhieuDat.getRowCount()) == null ? "(0)"
						: "(" + String.valueOf(soluongphieudatSapHetHan) + ")");
				lblConHan.setText(String.valueOf(tableDSPhieuDat.getRowCount()) == null ? "(0)"
						: "(" + String.valueOf(soluongconhan) + ")");
				soluongphieudatHetHan = 0;
				soluongphieudatSapHetHan = 0;
				soluongconhan = 0;
			}
		});

		JPanel panel_16 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_16.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_13.add(panel_16);

		JPanel panel_11 = new JPanel();
		panel_16.add(panel_11);
		panel_11.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_11.setPreferredSize(new Dimension(30, 20));
		panel_11.setBackground(Color.WHITE);
		panel_16.add(lblConHan);

		JLabel lblNewLabel_9 = new JLabel("Còn hạn");
		panel_16.add(lblNewLabel_9);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_16.add(horizontalStrut_2);

		JPanel panel_14 = new JPanel();
		panel_16.add(panel_14);
		panel_14.setBackground(Color.YELLOW);
		panel_14.setForeground(Color.RED);
		panel_14.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_14.setPreferredSize(new Dimension(30, 20));
		panel_16.add(lblSapHetHan);

		JLabel lblNewLabel_10 = new JLabel("Sắp hết hạn");
		panel_16.add(lblNewLabel_10);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel_16.add(horizontalStrut_3);

		JPanel panel_15 = new JPanel();
		panel_16.add(panel_15);
		panel_15.setBackground(Color.RED);
		panel_15.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_15.setPreferredSize(new Dimension(30, 20));
		panel_16.add(lblHetHan);

		JLabel lblNewLabel_11 = new JLabel("Hết hạn");
		panel_16.add(lblNewLabel_11);

		JPanel pnlTTSanPham = new JPanel();

		pnlTTSanPham.setBackground(new Color(255, 255, 255));
		splitPane.setRightComponent(pnlTTSanPham);
		pnlTTSanPham.setLayout(new BorderLayout(0, 0));
		pnlTTSanPham.setMaximumSize(new Dimension(300, 1080));

		JLabel lblNewLabel = new JLabel("Thông tin phiếu đặt");
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
		splitPane_2.setOpaque(false);
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_3.add(splitPane_2);
		String[] titleCTPD = { "Mã Sản Phẩm", "Tên Sản Phẩm", "Số lượng", "Thành tiền" };
		JScrollPane scrollPane_1 = new JScrollPane(
				tblCTPhieuDat = new JTable(dataModelChitietPD = new DefaultTableModel(titleCTPD, 0) {
					boolean[] canEdit = new boolean[] { false, false, false };

					public boolean isCellEditable(int row, int column) {
						return canEdit[column];
					}
				}), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblCTPhieuDat.setAutoCreateRowSorter(true);
		tblCTPhieuDat.setRowHeight(30);
		tblCTPhieuDat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblCTPhieuDat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane_1.setBackground(Color.WHITE);
		tblCTPhieuDat.setOpaque(false);
		scrollPane_1.setOpaque(false);
		splitPane_2.setRightComponent(scrollPane_1);

		scrollPane_1.setViewportView(tblCTPhieuDat);

		JPanel pnlThongTinSP = new JPanel();
		pnlThongTinSP.setBorder(null);
		splitPane_2.setLeftComponent(pnlThongTinSP);
		pnlThongTinSP.setMaximumSize(new Dimension(450, 32767));
		pnlThongTinSP.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.setLayout(new GridLayout(0, 1, 15, 20));

		JPanel panel_9 = new JPanel();
		panel_9.setOpaque(false);
		pnlThongTinSP.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 2, 15, 0));

		JLabel lblMaSP = new JLabel("Mã phiếu đặt:");
		panel_9.add(lblMaSP);
		lblMaSP.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblMaSP.setBackground(new Color(255, 255, 255));

		txtMaPhieu = new JLabel();
		txtMaPhieu.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtMaPhieu.setText("unknown");
		panel_9.add(txtMaPhieu);
		txtMaPhieu.setBackground(new Color(255, 255, 255));

		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		pnlThongTinSP.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 2, 15, 0));

		JLabel lblNewLabel_6 = new JLabel("Tên khách hàng:");
		panel_8.add(lblNewLabel_6);
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.BOLD, 15));

		txtTenKH = new JLabel();
		txtTenKH.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		txtTenKH.setText("unknown");
		panel_8.add(txtTenKH);

		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		pnlThongTinSP.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 2, 15, 0));

		JLabel lblNewLabel_7 = new JLabel("Số điện thoại:");
		panel_6.add(lblNewLabel_7);
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.BOLD, 15));

		txtSDT = new JLabel();
		txtSDT.setForeground(new Color(0, 0, 0));
		txtSDT.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		panel_6.add(txtSDT);
		txtSDT.setText("unknown");

		JPanel panel_7 = new JPanel();
		panel_7.setOpaque(false);
		pnlThongTinSP.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 15, 0));

		JLabel lblTrangThai = new JLabel("Trạng thái hóa đơn:");
		panel_7.add(lblTrangThai);
		lblTrangThai.setFont(new Font("Segoe UI", Font.BOLD, 15));

		txtTrangThaiPD = new JLabel();
		txtTrangThaiPD.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		txtTrangThaiPD.setText("unknown");
		panel_7.add(txtTrangThaiPD);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		pnlThongTinSP.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 4, 5, 0));

		JLabel lblNewLabel_3 = new JLabel("Ngày đặt:");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 15));
		panel_4.add(lblNewLabel_3);
		lblNewLabel_3.setBackground(new Color(255, 255, 255));

		txtNgayDat = new JLabel();
		txtNgayDat.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		txtNgayDat.setText("unknown");
		panel_4.add(txtNgayDat);
		txtNgayDat.setBackground(new Color(255, 255, 255));

		JLabel lblNewLabel_1 = new JLabel("Ngày nhận hàng:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
		panel_4.add(lblNewLabel_1);

		txtNgayNhan = new JLabel();
		txtNgayNhan.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		txtNgayNhan.setText("unknown");
		panel_4.add(txtNgayNhan);
		txtNgayNhan.setBackground(new Color(255, 255, 255));

		JLabel lblNewLabel_5 = new JLabel("Chi tiết phiếu đặt");
		pnlThongTinSP.add(lblNewLabel_5);
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 15));

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut_1, BorderLayout.NORTH);

		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_3.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel_4 = new JLabel("Tổng tiền:");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_4.setBackground(Color.WHITE);
		panel_5.add(lblNewLabel_4);

		txtTongTien = new JLabel();
		txtTongTien.setFont(new Font("Segoe UI", Font.BOLD, 20));
		txtTongTien.setText("unknown ");
		panel_5.add(txtTongTien);

		Component horizontalStrut = Box.createHorizontalStrut(21);
		horizontalStrut.setBackground(new Color(255, 255, 255));
		pnlTTSanPham.add(horizontalStrut, BorderLayout.EAST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBackground(new Color(255, 255, 255));
		pnlTTSanPham.add(horizontalStrut_1, BorderLayout.WEST);

		JPanel panel_12 = new JPanel();
		pnlTTSanPham.add(panel_12, BorderLayout.SOUTH);
		HoaDonDao pddao = new HoaDonDao();

		ArrayList<HoaDon> hdcuoi = pddao.layDsHoaDon();
		if (hdcuoi.size() != 0) {
			mahoadontam = hdcuoi.get(hdcuoi.size() - 1).getMahoadon()
					.substring(hdcuoi.get(hdcuoi.size() - 1).getMahoadon().length() - 3);
			stt = Integer.parseInt(mahoadontam);
		} else {
			stt = 0;
		}
		mahd = "HD";
		int i = stt;
		if (i < 10) {
			mahd = mahd + "00" + i;
		}
		if (i >= 10 && i < 100) {
			mahd = mahd + "0" + i;
		}
		if (i >= 100 && i < 1000) {
			mahd = mahd + i;
		}

		MyButton txtThanhToan = new MyButton("Thanh Toán");
		txtThanhToan.setColorClick(new Color(0, 128, 0));
		txtThanhToan.setColorOver(new Color(34, 139, 34));
		txtThanhToan.setForeground(new Color(255, 255, 255));
		txtThanhToan.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtThanhToan.setColor(new Color(60, 179, 113));
		txtThanhToan.setBorderColor(new Color(50, 205, 50));
		txtThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				} else if (stt > Integer.parseInt(checkma)) {
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
				} else {
					System.out.print("Ủa sao mã trùng đc stt =HD" + stt + " với mã " + mahd + "mà");
				}

				int r = tableDSPhieuDat.getSelectedRow();
				chuyenPDTsangHD(dataModelDSPD.getValueAt(r, 0).toString(), mahd);
				// in hóa đơn
				try {
					new createHoaDon(new HoaDonDao().timHoaDon(mahd), tblCTPhieuDat);
				} catch (Exception err) {
					System.err.println(err);
				}
				lblSoluongPhieuDat.setText(String.valueOf(tableDSPhieuDat.getRowCount()) == null ? "0"
						: String.valueOf(tableDSPhieuDat.getRowCount()));
				JOptionPane.showMessageDialog(scrollPane, "Thanh toán thành công", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				GUI_DSHoaDon.deleteTableDSHD();
				String ma = "";
				try {
					ma = GUIChinh_NhanVien.nv.getManhanvien();
				} catch (Exception err) {

					ma = GUIChinh_QuanLy.nv.getManhanvien();
				}
				GUI_DSHoaDon.updateTableDSHDTheoThoiGian(new HoaDonDao().layDsHoaDonTheoThoiGian(0, ma));

				ArrayList<PhieuDatTruoc> dspd = null;

				try {
					comboBox.setSelectedIndex(0);
					dspd = new PhieuDatTruocDao().layDsPhieuDatTheoThoiGian(0, GUIChinh_NhanVien.nv.getManhanvien());
				} catch (Exception err) {
					dspd = new PhieuDatTruocDao().layDsPhieuDatTheoThoiGian(0,
							cboNhanVien.getSelectedItem().toString() == "Tất cả nhân viên" ? "NV001"
									: cboNhanVien.getSelectedItem().toString());
				}
				deleteTableDSPD();
				updateTableDSPDTheoThoiGian(dspd);
			}
		});
		panel_12.setLayout(new BorderLayout(0, 0));
		panel_12.add(txtThanhToan);

		btnHuyPhieuDat = new MyButton("Hủy Phiếu");
		btnHuyPhieuDat.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnHuyPhieuDat.setColorOver(new Color(128, 0, 0));
		btnHuyPhieuDat.setColorClick(new Color(128, 0, 0));
		btnHuyPhieuDat.setForeground(new Color(255, 255, 255));
		btnHuyPhieuDat.setColor(new Color(255, 0, 0));
		btnHuyPhieuDat.setBorderColor(new Color(128, 0, 0));
		btnHuyPhieuDat.setRadius(0);
		btnHuyPhieuDat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = tableDSPhieuDat.getSelectedRow();
				if (r > -1) {
					int hoi = JOptionPane.showConfirmDialog(null,
							"Bạn có muốn hủy phiếu đặt " + txtMaPhieu.getText() + " của khách hàng "
									+ txtTenKH.getText() + " ?",
							"Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (hoi == JOptionPane.YES_OPTION) {

						deleteTableCTPD();

						deleteTableCTPDUpdateSoLuong(tableDSPhieuDat.getValueAt(r, 0).toString());
						new PhieuDatTruocDao().xoaPhieuDatTruoc(txtMaPhieu.getText());
						ArrayList<PhieuDatTruoc> dspd = null;
						try {
							comboBox.setSelectedIndex(0);
							dspd = new PhieuDatTruocDao().layDsPhieuDatTheoThoiGian(0,
									GUIChinh_NhanVien.nv.getManhanvien());
						} catch (Exception err) {
							comboBox.setSelectedIndex(0);
							dspd = new PhieuDatTruocDao().layDsPhieuDatTheoThoiGian(0,
									cboNhanVien.getSelectedItem().toString() == "Tất cả nhân viên" ? "NV001"
											: cboNhanVien.getSelectedItem().toString());
						}
						txtMaPhieu.setText("");
						txtTenKH.setText("");
						txtSDT.setText("");
						txtNgayDat.setText("");
						txtNgayNhan.setText("");
						txtTongTien.setText("");
						txtTrangThaiPD.setText("");
						deleteTableCTPD();
						deleteTableDSPD();
						updateTableDSPDTheoThoiGian(dspd);
						JOptionPane.showMessageDialog(scrollPane, "Hủy thành công", "Thông báo",
								JOptionPane.INFORMATION_MESSAGE);
						tableDSPhieuDat.clearSelection();
					}
				} else {
					JOptionPane.showMessageDialog(scrollPane,
							"Chưa có chọn phiếu đặt nào sao hủy ? Hãy chọn một phiếu đặt để thực hiện chức năng này",
							"Thông báo", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_12.add(btnHuyPhieuDat, BorderLayout.EAST);
		ArrayList<PhieuDatTruoc> dspd = null;
		JPanel pnlSideLeft = new JPanel();
		pnlSideLeft.setPreferredSize(new Dimension(150, 10));
		pnlSideLeft.setBackground(Color.WHITE);

		MyButton btnTaiLai = new MyButton("Tải lại");
		pnlSideLeft.add(btnTaiLai);
		btnTaiLai.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTaiLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhieuDatTruocDao hddao = new PhieuDatTruocDao();
				ArrayList<PhieuDatTruoc> dspd = null;
				if (comboBox.getSelectedIndex() == 0) {
					try {
						dspd = hddao.layDsPhieuDatTheoThoiGian(0, GUIChinh_NhanVien.nv.getManhanvien());
					} catch (Exception er) {
						if (cboNhanVien.getSelectedIndex() == 0)
							dspd = hddao.layDsPhieuDatTheoThoiGian(0);
						else
							dspd = hddao.layDsPhieuDatTheoThoiGian(0, cboNhanVien.getSelectedItem().toString());
					}
				} else if (comboBox.getSelectedIndex() == 1) {
					try {
						dspd = hddao.layDsPhieuDatTheoThoiGian(3, GUIChinh_NhanVien.nv.getManhanvien());
					} catch (Exception er) {
						if (cboNhanVien.getSelectedIndex() == 0)
							dspd = hddao.layDsPhieuDatTheoThoiGian(3);
						else
							dspd = hddao.layDsPhieuDatTheoThoiGian(3, cboNhanVien.getSelectedItem().toString());
					}
				} else if (comboBox.getSelectedIndex() == 2) {
					try {
						dspd = hddao.layDsPhieuDatTheoThoiGian(7, GUIChinh_NhanVien.nv.getManhanvien());
					} catch (Exception er) {
						if (cboNhanVien.getSelectedIndex() == 0)
							dspd = hddao.layDsPhieuDatTheoThoiGian(7);
						else
							dspd = hddao.layDsPhieuDatTheoThoiGian(7, cboNhanVien.getSelectedItem().toString());
					}
				} else if (comboBox.getSelectedIndex() == 3) {
					try {
						dspd = hddao.layDsPhieuDat(GUIChinh_NhanVien.nv.getManhanvien());
					} catch (Exception er) {
						dspd = hddao.layDsPhieuDat(cboNhanVien.getSelectedItem().toString());
					}
				}
				try {
					GUIChinh_QuanLy.nv.getManhanvien();
					if (cboNhanVien.getSelectedIndex() == -1 || cboNhanVien.getSelectedIndex() == 0) {
						if (comboBox.getSelectedIndex() == 3)
							dspd = hddao.layDsPhieuDatTruoc();
						else if (comboBox.getSelectedIndex() == 0)
							dspd = hddao.layDsPhieuDatTheoThoiGian(0);
						else if (comboBox.getSelectedIndex() == 1)
							dspd = hddao.layDsPhieuDatTheoThoiGian(3);
						else if (comboBox.getSelectedIndex() == 2)
							dspd = hddao.layDsPhieuDatTheoThoiGian(7);
					}
				} catch (Exception er) {

				}
				deleteTableDSPD();
				updateTableDSPDTheoThoiGian(dspd);
				lblSoluongPhieuDat.setText(String.valueOf(tableDSPhieuDat.getRowCount()) == null ? "0"
						: String.valueOf(tableDSPhieuDat.getRowCount()));

			}
		});
		btnTaiLai.setIcon(new ImageIcon(GUI_DSPhieuDat.class.getResource("/images/tailai.png")));
		pnlSideLeft.add(pnlLoc);
		panel.add(pnlSideLeft, BorderLayout.WEST);

		try {
			comboBox.setSelectedIndex(0);
			dspd = new PhieuDatTruocDao().layDsPhieuDatTheoThoiGian(0, GUIChinh_NhanVien.nv.getManhanvien());
		} catch (Exception err) {
			dspd = new PhieuDatTruocDao().layDsPhieuDatTheoThoiGian(0,
					cboNhanVien.getSelectedItem().toString() == "Tất cả nhân viên" ? "NV001"
							: cboNhanVien.getSelectedItem().toString());
		}
		deleteTableDSPD();
		updateTableDSPDTheoThoiGian(dspd);

	}

	/**
	 * update bảng danh sách phiếu đặt theo thời gian
	 * 
	 * @param dspd
	 */
	public static void updateTableDSPDTheoThoiGian(ArrayList<PhieuDatTruoc> dspd) {

		for (int i = 0; i < dspd.size(); i++) {
			KhachHang kh = new KhachHangDao().timKhachHang(dspd.get(i).getKh().getMakh());

			if (new KhachHangDao().timKhachHang(dspd.get(i).getKh().getMakh()) == null) {
				dataModelDSPD.addRow(new Object[] { dspd.get(i).getMaphieudat(), kh.getTenkhachhang(),
						kh.getSodienthoai(),
						dspd.get(i).getNgaylap().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
								+ dspd.get(i).getNgaylap().toString().split("-")[2].split(" ")[1].split(":")[1] + " "
								+ dspd.get(i).getNgaylap().toString().split("-")[2].split(" ")[0] + "-"
								+ dspd.get(i).getNgaylap().toString().split("-")[1] + "-"
								+ dspd.get(i).getNgaylap().toString().split("-")[0],

						dspd.get(i).getNgaynhanhang().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
								+ dspd.get(i).getNgaynhanhang().toString().split("-")[2].split(" ")[1].split(":")[1]
								+ " " + dspd.get(i).getNgaynhanhang().toString().split("-")[2].split(" ")[0] + "-"
								+ dspd.get(i).getNgaynhanhang().toString().split("-")[1] + "-"
								+ dspd.get(i).getNgaynhanhang().toString().split("-")[0],

						new DecimalFormat("###,###,###").format(dspd.get(i).getTongtienphieudat()) });
			}

			else {
				kh = new KhachHangDao().timKhachHang(dspd.get(i).getKh().getMakh());
				dataModelDSPD.addRow(new Object[] { dspd.get(i).getMaphieudat(), kh.getTenkhachhang(),
						kh.getSodienthoai(),
						dspd.get(i).getNgaylap().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
								+ dspd.get(i).getNgaylap().toString().split("-")[2].split(" ")[1].split(":")[1] + " "
								+ dspd.get(i).getNgaylap().toString().split("-")[2].split(" ")[0] + "-"
								+ dspd.get(i).getNgaylap().toString().split("-")[1] + "-"
								+ dspd.get(i).getNgaylap().toString().split("-")[0],

						dspd.get(i).getNgaynhanhang().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
								+ dspd.get(i).getNgaynhanhang().toString().split("-")[2].split(" ")[1].split(":")[1]
								+ " " + dspd.get(i).getNgaynhanhang().toString().split("-")[2].split(" ")[0] + "-"
								+ dspd.get(i).getNgaynhanhang().toString().split("-")[1] + "-"
								+ dspd.get(i).getNgaynhanhang().toString().split("-")[0],
						new DecimalFormat("###,###,###").format(dspd.get(i).getTongtienphieudat()) });
			}
		}
	}

	/**
	 * Cập nhật bảng danh sách chi tiết phiếu đặt
	 */
	public static void updateTableCTPD() {
		CTPhieuDatTruocDao ctdao = new CTPhieuDatTruocDao();

		PhieuDatTruocDao pddao = new PhieuDatTruocDao();
		int r = tableDSPhieuDat.getSelectedRow();
		if (r == -1) {

		} else {
			PhieuDatTruoc hd = pddao.timPhieuDatTruoc((String) tableDSPhieuDat.getValueAt(r, 0));
			ArrayList<CTPhieuDatTruoc> dspd = ctdao.layDsCTPhieuDatTruocTheoPhieuDat(hd.getMaphieudat());
			for (CTPhieuDatTruoc i : dspd) {
				SanPham sp = new SanPhamDao().timSanPham(i.getSp().getMasanpham());
				dataModelChitietPD.addRow(new Object[] { i.getSp().getMasanpham(), sp.getTensanpham(),
						i.getSoluongsanpham(), new DecimalFormat("###,###,###").format(i.getThanhtien()) });
			}
		}
	}

	/**
	 * Xóa bảng chi tiết phiếu đặt
	 */
	public static void deleteTableCTPD() {
		int dem = dataModelChitietPD.getRowCount();
		for (int i = 0; i < dem; i++) {
			dataModelChitietPD.removeRow(0);
			;
		}
	}

	/**
	 * Xóa chi tiết phiếu đặt theo mã phiếu và update số lượng
	 */
	public static void deleteTableCTPDUpdateSoLuong(String maphieu) {
		SanPhamDao spdao = new SanPhamDao();
		CTPhieuDatTruocDao ctdao = new CTPhieuDatTruocDao();
		PhieuDatTruocDao pddao = new PhieuDatTruocDao();
		PhieuDatTruoc pd = pddao.timPhieuDatTruoc(maphieu);
		ArrayList<CTPhieuDatTruoc> dspd = ctdao.layDsCTPhieuDatTruocTheoPhieuDat(pd.getMaphieudat());
		for (CTPhieuDatTruoc i : dspd) {
			spdao.capNhatSoLuong(i.getSp().getMasanpham(), i.getSoluongsanpham());
		}
	}

	static Date ngaynhan;
	static float ngaychenhlech;

	/**
	 * Cập nhật bảng danh sách phiếu đặt
	 */
	public static void updateTableDSPD() {
		PhieuDatTruocDao pddao = new PhieuDatTruocDao();
		ArrayList<PhieuDatTruoc> dspd = pddao.layDsPhieuDatTruoc();

		for (PhieuDatTruoc i : dspd) {
			KhachHang kh = new KhachHangDao().timKhachHang(i.getKh().getMakh());
			dataModelDSPD.addRow(new Object[] { i.getMaphieudat(), kh.getTenkhachhang(), kh.getSodienthoai(),
					i.getNgaylap().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
							+ i.getNgaylap().toString().split("-")[2].split(" ")[1].split(":")[1] + " "
							+ i.getNgaylap().toString().split("-")[2].split(" ")[0] + "-"
							+ i.getNgaylap().toString().split("-")[1] + "-" + i.getNgaylap().toString().split("-")[0],
					i.getNgaynhanhang().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
							+ i.getNgaynhanhang().toString().split("-")[2].split(" ")[1].split(":")[1] + " "
							+ i.getNgaynhanhang().toString().split("-")[2].split(" ")[0] + "-"
							+ i.getNgaynhanhang().toString().split("-")[1] + "-"
							+ i.getNgaynhanhang().toString().split("-")[0],
					new DecimalFormat("###,###,###").format(i.getTongtienphieudat()) });

		}

	}

	/**
	 * tải dữ liệu lên bảng danh sách phiếu đặt
	 */

	@SuppressWarnings("deprecation")
	public void setText() {

		int row = tableDSPhieuDat.getSelectedRow();
		if (row != -1) {
			KhachHangDao nvdao = new KhachHangDao();
			PhieuDatTruocDao pddao = new PhieuDatTruocDao();
			PhieuDatTruoc pd = pddao.timPhieuDatTruoc((String) dataModelDSPD.getValueAt(row, 0));
			KhachHang nv = nvdao.timKhachHang(pd.getKh().getMakh());

			txtMaPhieu.setText(pd.getMaphieudat());
			txtTenKH.setText(nv.getTenkhachhang());
			txtSDT.setText(nv.getSodienthoai());
			txtNgayDat.setText(dataModelDSPD.getValueAt(row, 3).toString());
			txtNgayNhan.setText(dataModelDSPD.getValueAt(row, 4).toString());
			txtTongTien.setText(dataModelDSPD.getValueAt(row, 5).toString());
			// HH(h)mm dd-mm-yyyy
			String ngaynhanbang = dataModelDSPD.getValueAt(row, 4).toString();
			ngaynhan = new Date(Integer.parseInt(ngaynhanbang.split("-")[2]) - 1900,
					Integer.parseInt(ngaynhanbang.split("-")[1]) - 1,
					Integer.parseInt(ngaynhanbang.split("-")[0].split(" ")[1]),
					Integer.parseInt(ngaynhanbang.split("-")[0].split(" ")[0].split("h")[0]),
					Integer.parseInt(ngaynhanbang.split("-")[0].split(" ")[0].split("h")[1]), 0);

			float d = ngaynhan.getTime() - new Date(System.currentTimeMillis()).getTime();// ngaylap.getTime();
			ngaychenhlech = d / (1000 * 60 * 60 * 24);
			// ngày chênh lệch > 1
			if (ngaychenhlech > 1) {
				if (ngaychenhlech - (int) Math.floor(ngaychenhlech) < (int) Math.floor(ngaychenhlech)) {
					txtTrangThaiPD.setText("Còn hơn " + String.valueOf((int) Math.floor(ngaychenhlech)) + " ngày");
				} else {
					txtTrangThaiPD.setText("Còn " + String.valueOf((int) Math.floor(ngaychenhlech)) + " ngày");
				}
				txtTrangThaiPD.setForeground(new Color(0, 100, 0));
			}

			// ngày chênh lệch => 0 && <= 1
			if (ngaychenhlech > 0 && ngaychenhlech < 1) {
				float giochenhlech = d / (1000 * 60 * 60);
				float phut = d / (1000 * 60);
				// giờ chênh lệch > 0
				if ((long) giochenhlech > 0) {
					txtTrangThaiPD.setText("Còn " + String.valueOf((int) Math.floor(giochenhlech)) + " giờ");
					txtTrangThaiPD.setForeground(Color.orange);
				}
				// giờ chênh lệch = 0
				if ((long) giochenhlech == 0) {
					// phút < 0
					if (phut < 0) {
						txtTrangThaiPD.setText("Quá hạn " + String.valueOf((int) Math.floor(phut)) + " phút");
						txtTrangThaiPD.setForeground(Color.red);
					}

					// phút >= 0
					if (phut >= 0) {

						txtTrangThaiPD.setText("Còn " + String.valueOf((int) Math.floor(phut)) + " phút");
						txtTrangThaiPD.setForeground(Color.orange);
					}
				}
				// giờ chênh lệch < 0
				if ((long) giochenhlech < 0) {
					txtTrangThaiPD.setText("Quá hạn " + String.valueOf((int) Math.floor(phut)) + " phút");
					txtTrangThaiPD.setForeground(Color.red);
				}
			}

			// ngày chênh lệch < 0
			if (ngaychenhlech < 0) {
				float giochenhlech = d / (1000 * 60 * 60);
				float phut = d / (1000 * 60);
				if (Math.abs((long)ngaychenhlech) == 0) {
					if ((long) giochenhlech < 0) {
						if (giochenhlech > -1 && giochenhlech < 0) {
							txtTrangThaiPD
									.setText("Quá hạn " + String.valueOf((int) Math.floor(Math.abs(phut))) + " phút");
							txtTrangThaiPD.setForeground(Color.red);
						} else {
							txtTrangThaiPD.setText(
									"Quá hạn " + String.valueOf((int) Math.floor(Math.abs(giochenhlech))) + " giờ");
							txtTrangThaiPD.setForeground(Color.red);
						}
					}
				} else {
					txtTrangThaiPD.setText(
							"Bị quá hạn " + String.valueOf((int) Math.floor(Math.abs(ngaychenhlech))) + " ngày");

					txtTrangThaiPD.setForeground(Color.RED);
				}
			}
			deleteTableCTPD();
			updateTableCTPD();

		}
	}

	/**
	 * Xóa bảng danh sách phiếu đặt
	 */
	public static void deleteTableDSPD() {
		int dem = dataModelDSPD.getRowCount();
		for (int i = 0; i < dem; i++) {
			dataModelDSPD.removeRow(0);
			;
		}
	}

	/**
	 * Cập nhật bảng danh sách phiếu đặt trươc sau khi thực hiện tìm kiếm
	 * 
	 * @param list
	 */
	public static void updateTableTimDSPD(ArrayList<PhieuDatTruoc> list) {
		ArrayList<PhieuDatTruoc> dspd = list;
		for (PhieuDatTruoc i : dspd) {
			KhachHangDao khdao = new KhachHangDao();
			KhachHang kh = khdao.timKhachHang(i.getKh().getMakh());

			dataModelDSPD.addRow(new Object[] { i.getMaphieudat(), kh.getTenkhachhang(), kh.getSodienthoai(),
					i.getNgaylap().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
							+ i.getNgaylap().toString().split("-")[2].split(" ")[1].split(":")[1] + " "
							+ i.getNgaylap().toString().split("-")[2].split(" ")[0] + "-"
							+ i.getNgaylap().toString().split("-")[1] + "-" + i.getNgaylap().toString().split("-")[0],
					i.getNgaynhanhang().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
							+ i.getNgaylap().toString().split("-")[2].split(" ")[1].split(":")[1] + " "
							+ i.getNgaynhanhang().toString().split("-")[2].split(" ")[0] + "-"
							+ i.getNgaynhanhang().toString().split("-")[1] + "-"
							+ i.getNgaynhanhang().toString().split("-")[0],
					new DecimalFormat("###,###,###").format(i.getTongtienphieudat()) });

		}

	}

	public void chuyenPDTsangHD(String maphieudat, String mahd) {
		ArrayList<CTPhieuDatTruoc> list = null;
		list = new CTPhieuDatTruocDao().layDsCTPhieuDatTruocTheoPhieuDat(maphieudat);
		HoaDon hd = new HoaDon(mahd);
		PhieuDatTruoc pd = new PhieuDatTruocDao().timPhieuDatTruoc(maphieudat);
		hd.setKhachhang(pd.getKh());
		hd.setNgaylaphoadon(pd.getNgaylap());
		hd.setNhanvien(pd.getNv());
		hd.setTongtienhoadon(pd.getTongtienphieudat());
		new HoaDonDao().themHoaDon(hd);
		for (CTPhieuDatTruoc ctpd : list) {

			CTHoaDon ct = new CTHoaDon(ctpd.getThanhtien(), ctpd.getSoluongsanpham(), hd,
					new SanPhamDao().timSanPham(ctpd.getSp().getMasanpham()));
			new CTHoaDonDao().themCTHoaDon(ct);
		}

		new PhieuDatTruocDao().xoaPhieuDatTruoc(maphieudat);
		new CTPhieuDatTruocDao().xoaAllCTPhieuDatTruoc(maphieudat);
	}

}
