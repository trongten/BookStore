package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Others.MyButton;
import dao.SanPhamDao;
import entity.SanPham;

public class GUI_DSSanPham extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JLabel txtMaSanPham;
	private JTextArea txtTenSanPham;
	private JLabel txtGiaDonVi;
	private JTextArea txtNhaXuatBan;
	private JTextArea txtChatLieu;
	private JTextArea txtNhaCungCap;
	private JTextArea txtSoTrang;
	private JLabel txtGiaNhap;
	private JTextArea txtTenTacGia;
	private static DefaultTableModel dataModel;
	private JTextArea txtXuatXu;
	private JLabel lblAnhSP;
	private JLabel lblChatLieu, lblNhaCungCap, lblSoTrang, lblTacGia, lblNhaXuatBan, lblXuatXu;
	private JPanel pnlThongTinSP;
	private JPanel panel_1;
	private JLabel lblNewLabel_1;
	private MyButton btnNewButton;
	private JPanel panel_3;

	private JComboBox<Object> comboBox;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel lblNewLabel_2;
	private JLabel lblSoLuongMatHang;
	private JPanel panel_7;
	private JLabel lbl1;
	private JLabel lblHetHang = new JLabel("(0)");
	private JPanel panel_8;
	private JLabel lblSapHetHang = new JLabel("(0)");
	private JLabel lbl;
	private JPanel panel_9;
	private JLabel lblConNhieuHang = new JLabel("(0)");
	private int soluongSanPhamSapHetHang = 0;
	private int soluongSanPhamHetHang = 0;
	private JLabel lblNewLabel_5;

	/**
	 * Create the panel.
	 */

	@SuppressWarnings("serial")
	public GUI_DSSanPham() {
		setBackground(new Color(255, 255, 255));
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setTitle("Danh sách sản phẩm");
		frm.getContentPane().setBackground(new Color(255, 255, 255));
		frm.setBackground(new Color(255, 255, 255));
		frm.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		frm.getContentPane().add(this);
		setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBackground(new Color(255, 255, 255));
		add(splitPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setBackground(new Color(255, 255, 255));
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane_1, BorderLayout.CENTER);

		String[] title = { "Mã sản phẩm", "Tên sản phẩm", "Số lượng" };
		JScrollPane scrollPane = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(title, 0) {
			boolean[] canEdit = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return canEdit[column];
			}
		}) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (column == 0) {
					SanPham sp = new SanPhamDao().timSanPham(getValueAt(row, 0).toString());
					int soluong = sp.getSoluong();
					if (soluong > 0 && soluong <= 10) {
						c.setBackground(Color.yellow);
						c.setForeground(Color.black);
					} else if (soluong == 0) {
						c.setBackground(Color.red);
						c.setForeground(Color.white);
					} else if (soluong > 10) {
						c.setBackground(Color.white);
						c.setForeground(Color.black);
					}
				}
				return c;
			}
		});
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(30);

		scrollPane.setOpaque(false);

		table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setText();
			}
		});
		scrollPane.setBackground(new Color(255, 255, 255));
		splitPane_1.setRightComponent(scrollPane);

		table.setBackground(new Color(255, 255, 255));
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(700);
		scrollPane.setViewportView(table);

		panel_1 = new JPanel();
		splitPane_1.setLeftComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		btnNewButton = new MyButton("Tải lại");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SanPhamDao spdao = new SanPhamDao();
				ArrayList<SanPham> dssp = null;
				if (comboBox.getSelectedIndex() == 1) {
					dssp = spdao.layDsSanPhamConHang();
				} else if (comboBox.getSelectedIndex() == 2) {
					dssp = spdao.layDsSanPhamHetHang();
				} else if (comboBox.getSelectedIndex() == 3) {
					dssp = spdao.layDsSanPhamSapHetHang();
				} else {
					dssp = spdao.layDsSanPham();
				}
				deleteTableDSSanPham();
				for (SanPham i : dssp) {
					dataModel.addRow(new Object[] { i.getMasanpham(), i.getTensanpham(), i.getSoluong() });
				}
				lblSoLuongMatHang.setText(
						String.valueOf(table.getRowCount()) == null ? "0" : String.valueOf(table.getRowCount()));
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setIcon(new ImageIcon(GUI_DSSanPham.class.getResource("/images/tailai.png")));
		panel_1.add(btnNewButton, BorderLayout.WEST);

		lblNewLabel_1 = new JLabel("Danh Sách Sản Phẩm");
		lblNewLabel_1.setMinimumSize(new Dimension(800, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(new Color(25, 25, 112));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel_1.setBackground(Color.WHITE);
		panel_1.add(lblNewLabel_1);

		panel_3 = new JPanel();
		panel_3.setBorder(
				new TitledBorder(null, "S\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_3, BorderLayout.EAST);

		comboBox = new JComboBox<Object>();

		comboBox.setModel(
				new DefaultComboBoxModel<Object>(new String[] { "Tất cả", "Còn hàng", "Hết hàng", "Sắp hết hàng" }));
		comboBox.setSelectedIndex(1);
		panel_3.add(comboBox);

		panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));

		panel_5 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_5);

		lblNewLabel_2 = new JLabel("Số lượng mặt hàng:");
		panel_5.add(lblNewLabel_2);

		lblSoLuongMatHang = new JLabel("");
		panel_5.add(lblSoLuongMatHang);

		panel_6 = new JPanel();
		@SuppressWarnings("unused")
		FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
		panel_4.add(panel_6);

		panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBackground(Color.RED);
		panel_7.setPreferredSize(new Dimension(30, 20));
		panel_6.add(panel_7);

		panel_6.add(lblHetHang);

		lbl1 = new JLabel("Hết hàng");
		panel_6.add(lbl1);

		panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setBackground(Color.YELLOW);
		panel_8.setPreferredSize(new Dimension(30, 20));
		panel_6.add(panel_8);

		panel_6.add(lblSapHetHang);

		lbl = new JLabel("Sắp hết hàng");
		panel_6.add(lbl);

		panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_9.setBackground(Color.WHITE);
		panel_9.setPreferredSize(new Dimension(30, 20));
		panel_6.add(panel_9);

		panel_6.add(lblConNhieuHang);

		lblNewLabel_5 = new JLabel("Còn hàng");
		panel_6.add(lblNewLabel_5);

		JPanel pnlTTSanPham = new JPanel();
		pnlTTSanPham.setBackground(new Color(255, 255, 255));
		splitPane.setRightComponent(pnlTTSanPham);
		pnlTTSanPham.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Thông tin sản phẩm");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		pnlTTSanPham.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		pnlTTSanPham.add(panel_2);
		panel_2.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel pnlAnhSP = new JPanel();
		pnlAnhSP.setBackground(new Color(255, 255, 255));
		panel_2.add(pnlAnhSP);
		pnlAnhSP.setLayout(new BorderLayout(0, 0));

		// 450X350
		lblAnhSP = new JLabel("Vui lòng chọn một sản phẩm");
		lblAnhSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnhSP.setOpaque(true);
		lblAnhSP.setBorder(null);
		lblAnhSP.setBackground(new Color(255, 255, 255));
		pnlAnhSP.add(lblAnhSP);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setPreferredSize(new Dimension(200, 0));
		pnlAnhSP.add(horizontalStrut_2, BorderLayout.WEST);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setPreferredSize(new Dimension(200, 0));
		pnlAnhSP.add(horizontalStrut_3, BorderLayout.EAST);

		pnlThongTinSP = new JPanel();
		pnlThongTinSP.setBackground(new Color(255, 255, 255));
		panel_2.add(pnlThongTinSP);
		pnlThongTinSP.setLayout(new GridLayout(0, 4, 15, 25));

		JLabel lblMaSP = new JLabel("Mã sản phẩm:");
		lblMaSP.setVerticalTextPosition(SwingConstants.TOP);
		lblMaSP.setVerticalAlignment(SwingConstants.TOP);
		lblMaSP.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblMaSP.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(lblMaSP);

		txtMaSanPham = new JLabel();
		txtMaSanPham.setText("unknown");
		txtMaSanPham.setVerticalTextPosition(SwingConstants.TOP);
		txtMaSanPham.setVerticalAlignment(SwingConstants.TOP);
		txtMaSanPham.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtMaSanPham.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(txtMaSanPham);

		JLabel lblNewLabel_3 = new JLabel("Tên sản phẩm:");
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(lblNewLabel_3);

		txtTenSanPham = new JTextArea();
		txtTenSanPham.setText("unknown");
		txtTenSanPham.setEditable(false);
		txtTenSanPham.setForeground(Color.RED);
		txtTenSanPham.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtTenSanPham.setLineWrap(true);
		txtTenSanPham.setWrapStyleWord(true);
		txtTenSanPham.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(txtTenSanPham);
		txtTenSanPham.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Đơn giá:");
		lblNewLabel_4.setVerticalTextPosition(SwingConstants.TOP);
		lblNewLabel_4.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_4.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(lblNewLabel_4);

		txtGiaDonVi = new JLabel();
		txtGiaDonVi.setText("unknown");
		txtGiaDonVi.setVerticalTextPosition(SwingConstants.TOP);
		txtGiaDonVi.setVerticalAlignment(SwingConstants.TOP);
		txtGiaDonVi.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtGiaDonVi.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(txtGiaDonVi);

		JLabel lblNewLabel_10 = new JLabel("Giá nhập:");
		lblNewLabel_10.setVerticalTextPosition(SwingConstants.TOP);
		lblNewLabel_10.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_10.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNewLabel_10.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(lblNewLabel_10);

		txtGiaNhap = new JLabel();
		txtGiaNhap.setText("unknown");
		txtGiaNhap.setVerticalTextPosition(SwingConstants.TOP);
		txtGiaNhap.setVerticalAlignment(SwingConstants.TOP);
		txtGiaNhap.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtGiaNhap.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(txtGiaNhap);

		lblChatLieu = new JLabel("Chất liệu:");
		lblChatLieu.setVerticalTextPosition(SwingConstants.TOP);
		lblChatLieu.setVerticalAlignment(SwingConstants.TOP);
		lblChatLieu.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblChatLieu.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(lblChatLieu);

		txtChatLieu = new JTextArea();
		txtChatLieu.setText("unknown");
		txtChatLieu.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtChatLieu.setEditable(false);
		txtChatLieu.setLineWrap(true);
		txtChatLieu.setWrapStyleWord(true);
		txtChatLieu.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtChatLieu.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(txtChatLieu);

		lblNhaCungCap = new JLabel("Nhà cung cấp:");
		lblNhaCungCap.setVerticalTextPosition(SwingConstants.TOP);
		lblNhaCungCap.setVerticalAlignment(SwingConstants.TOP);
		lblNhaCungCap.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNhaCungCap.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(lblNhaCungCap);

		txtNhaCungCap = new JTextArea();
		txtNhaCungCap.setText("unknown");
		txtNhaCungCap.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtNhaCungCap.setEditable(false);
		txtNhaCungCap.setLineWrap(true);
		txtNhaCungCap.setWrapStyleWord(true);
		txtNhaCungCap.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtNhaCungCap.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(txtNhaCungCap);

		lblSoTrang = new JLabel("Số trang:");
		lblSoTrang.setVerticalTextPosition(SwingConstants.TOP);
		lblSoTrang.setVerticalAlignment(SwingConstants.TOP);
		lblSoTrang.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblSoTrang.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(lblSoTrang);

		txtSoTrang = new JTextArea();
		txtSoTrang.setText("unknown");
		txtSoTrang.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtSoTrang.setEditable(false);
		txtSoTrang.setLineWrap(true);
		txtSoTrang.setWrapStyleWord(true);
		txtSoTrang.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtSoTrang.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(txtSoTrang);

		lblTacGia = new JLabel("Tác giả:");
		lblTacGia.setVerticalTextPosition(SwingConstants.TOP);
		lblTacGia.setVerticalAlignment(SwingConstants.TOP);
		lblTacGia.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblTacGia.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(lblTacGia);

		txtTenTacGia = new JTextArea();
		txtTenTacGia.setText("unknown");
		txtTenTacGia.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtTenTacGia.setEditable(false);
		txtTenTacGia.setLineWrap(true);
		txtTenTacGia.setWrapStyleWord(true);
		txtTenTacGia.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtTenTacGia.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(txtTenTacGia);

		lblNhaXuatBan = new JLabel("Nhà xuất bản:");
		lblNhaXuatBan.setVerticalTextPosition(SwingConstants.TOP);
		lblNhaXuatBan.setVerticalAlignment(SwingConstants.TOP);
		lblNhaXuatBan.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNhaXuatBan.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(lblNhaXuatBan);

		txtNhaXuatBan = new JTextArea();
		txtNhaXuatBan.setText("unknown");
		txtNhaXuatBan.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtNhaXuatBan.setEditable(false);
		txtNhaXuatBan.setLineWrap(true);
		txtNhaXuatBan.setWrapStyleWord(true);
		txtNhaXuatBan.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtNhaXuatBan.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(txtNhaXuatBan);

		lblXuatXu = new JLabel("Xuất xứ:");
		lblXuatXu.setVerticalTextPosition(SwingConstants.TOP);
		lblXuatXu.setVerticalAlignment(SwingConstants.TOP);
		lblXuatXu.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		pnlThongTinSP.add(lblXuatXu);

		txtXuatXu = new JTextArea();
		txtXuatXu.setText("unknown");
		txtXuatXu.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtXuatXu.setEditable(false);
		txtXuatXu.setLineWrap(true);
		txtXuatXu.setWrapStyleWord(true);
		txtXuatXu.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		pnlThongTinSP.add(txtXuatXu);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setBackground(new Color(255, 255, 255));
		pnlTTSanPham.add(verticalStrut, BorderLayout.SOUTH);

		Component horizontalStrut = Box.createHorizontalStrut(17);
		horizontalStrut.setBackground(new Color(255, 255, 255));
		pnlTTSanPham.add(horizontalStrut, BorderLayout.EAST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(17);
		horizontalStrut_1.setBackground(new Color(255, 255, 255));
		pnlTTSanPham.add(horizontalStrut_1, BorderLayout.WEST);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SanPhamDao spdao = new SanPhamDao();
				ArrayList<SanPham> dssp = null;
				if (comboBox.getSelectedIndex() == 1) {
					dssp = spdao.layDsSanPhamConHang();
				} else if (comboBox.getSelectedIndex() == 2) {
					dssp = spdao.layDsSanPhamHetHang();
				} else if (comboBox.getSelectedIndex() == 3) {
					dssp = spdao.layDsSanPhamSapHetHang();
				} else {
					dssp = spdao.layDsSanPham();
				}
				deleteTableDSSanPham();
				for (SanPham i : dssp) {
					dataModel.addRow(new Object[] { i.getMasanpham(), i.getTensanpham(), i.getSoluong() });
				}
				lblSoLuongMatHang.setText(
						String.valueOf(table.getRowCount()) == null ? "0" : String.valueOf(table.getRowCount()));
			}
		});
		lblSoLuongMatHang
				.setText(String.valueOf(table.getRowCount()) == null ? "0" : String.valueOf(table.getRowCount()));
		lblSoLuongMatHang.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				for (int i = 0; i < table.getRowCount(); i++) {
					SanPham sp = new SanPhamDao().timSanPham((String) dataModel.getValueAt(i, 0));
					if (sp.getSoluong() > 10) {

					} else if (sp.getSoluong() > 0 && sp.getSoluong() <= 10) {
						soluongSanPhamSapHetHang++;
					} else if (sp.getSoluong() == 0) {
						soluongSanPhamHetHang++;
					}
				}
				lblHetHang.setText(String.valueOf(table.getRowCount()) == null ? "(0)"
						: "(" + String.valueOf(soluongSanPhamHetHang) + ")");
				lblSapHetHang.setText(String.valueOf(table.getRowCount()) == null ? "(0)"
						: "(" + String.valueOf(soluongSanPhamSapHetHang) + ")");
				lblConNhieuHang.setText(String.valueOf(table.getRowCount()) == null ? "(0)"
						: "(" + String.valueOf(table.getRowCount() - soluongSanPhamHetHang) + ")");
				soluongSanPhamHetHang = 0;
				soluongSanPhamSapHetHang = 0;
			}
		});
		deleteTableDSSanPham();
		ArrayList<SanPham> dssp = new SanPhamDao().layDsSanPhamConHang();
		for (SanPham i : dssp) {
			dataModel.addRow(new Object[] { i.getMasanpham(), i.getTensanpham(), i.getSoluong() });
		}
		lblSoLuongMatHang.setText(
				String.valueOf(table.getRowCount()) == null ? "0" : String.valueOf(table.getRowCount()));
	}

	public static void updateTableDSSanPham() {
		SanPhamDao spdao = new SanPhamDao();
		ArrayList<SanPham> dssp = spdao.layDsSanPham();

		for (SanPham i : dssp) {
			dataModel.addRow(new Object[] { i.getMasanpham(), i.getTensanpham(), i.getSoluong() });
		}

	}

	public static void updateTableTimDSSanPham(ArrayList<SanPham> dssp) {

		for (SanPham i : dssp) {
			dataModel.addRow(new Object[] { i.getMasanpham(), i.getTensanpham(), i.getSoluong() });
		}

	}

	public static void deleteTableDSSanPham() {
		int dem = dataModel.getRowCount();
		for (int i = 0; i < dem; i++) {
			dataModel.removeRow(0);
			;
		}

	}

	/**
	 * Đẩy dữ liệu qua bên thông tin
	 */
	public void setText() {
		int r = -1;
		r = table.getSelectedRow();
		SanPhamDao spdao = new SanPhamDao();
		SanPham sp = spdao.timSanPham((String) dataModel.getValueAt(r, 0));
		if (r > -1) {
			lblAnhSP.setText("");
			txtMaSanPham.setText(sp.getMasanpham());
			if (sp.getLoai().getMaloai().equals("SACH")) {
				pnlThongTinSP.remove(lblNhaCungCap);
				pnlThongTinSP.remove(txtNhaCungCap);

				pnlThongTinSP.remove(lblChatLieu);
				pnlThongTinSP.remove(txtChatLieu);

				pnlThongTinSP.remove(lblXuatXu);
				pnlThongTinSP.remove(txtXuatXu);

				pnlThongTinSP.add(lblSoTrang);
				pnlThongTinSP.add(txtSoTrang);

				pnlThongTinSP.add(lblTacGia);
				pnlThongTinSP.add(txtTenTacGia);

				pnlThongTinSP.add(lblNhaXuatBan);
				pnlThongTinSP.add(txtNhaXuatBan);

			}
			txtSoTrang.setText(String.valueOf(sp.getSotrang()));
			if (sp.getLoai().getMaloai().equals("VANP")) {

				pnlThongTinSP.remove(lblSoTrang);
				pnlThongTinSP.remove(txtSoTrang);

				pnlThongTinSP.remove(lblTacGia);
				pnlThongTinSP.remove(txtTenTacGia);

				pnlThongTinSP.remove(lblNhaXuatBan);
				pnlThongTinSP.remove(txtNhaXuatBan);

				pnlThongTinSP.add(lblNhaCungCap);
				pnlThongTinSP.add(txtNhaCungCap);

				pnlThongTinSP.add(lblChatLieu);
				pnlThongTinSP.add(txtChatLieu);

				pnlThongTinSP.add(lblXuatXu);
				pnlThongTinSP.add(txtXuatXu);

			}
			txtTenSanPham.setText(sp.getTensanpham());
			txtNhaCungCap.setText(sp.getNhacungcap());
			txtXuatXu.setText(sp.getXuatxu());
			txtChatLieu.setText(sp.getChatlieu());
			txtGiaDonVi.setText(String.valueOf(new DecimalFormat("###,###,###").format(sp.getGiadonvi())) + " đồng");
			txtGiaNhap.setText(String.valueOf(new DecimalFormat("###,###,###").format(sp.getGianhap())) + " đồng");
			txtNhaXuatBan.setText(sp.getNhaxuatban());

			txtTenTacGia.setText(sp.getTentacgia());
			lblAnhSP.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("../" + sp.getAnhsanpham())));

		}
	}

}
