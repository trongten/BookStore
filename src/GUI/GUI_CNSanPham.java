package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Others.MyButton;
import dao.SanPhamDao;
import entity.SanPham;
import javax.swing.ListSelectionModel;

public class GUI_CNSanPham extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTable tblVPP;
	private static JTable tblSach;
	protected static DefaultTableModel dataModelVPP;
	protected static DefaultTableModel dataModelSach;
	private JTabbedPane tabbedPane;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public GUI_CNSanPham() {
		setBackground(Color.WHITE);

		setLayout(new BorderLayout(0, 0));
		setSize(1399, 700);
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(
				new TitledBorder(null, "Ch\u1EE9c n\u0103ng", TitledBorder.RIGHT, TitledBorder.TOP, null, null));
		panel.setOpaque(false);
		add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(7, 1, 30, 20));

		MyButton btnThemSanPham = new MyButton("Thêm Sản Phẩm");
		btnThemSanPham.setBorder(null);
		btnThemSanPham.setIcon(new ImageIcon(GUI_CNSanPham.class.getResource("/images/themsp.png")));
		btnThemSanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GUI_ThemSP().setVisible(true);
			}
		});
		btnThemSanPham.setColorOver(new Color(60, 179, 113));
		btnThemSanPham.setColorClick(new Color(0, 100, 0));
		btnThemSanPham.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnThemSanPham.setBorderColor(new Color(0, 100, 0));
		panel.add(btnThemSanPham);

		MyButton btnXoaSanPham = new MyButton("Xóa Sản Phẩm");
		btnXoaSanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tabbedPane.getSelectedIndex() == 0) {
					if (tblVPP.getSelectedRow() > -1) {
						SanPhamDao spdao = new SanPhamDao();
						spdao.xoaSanPham((String) tblVPP.getValueAt(tblVPP.getSelectedRow(), 0));
						GUI_CNSanPham.deleteTableVPP();
						GUI_CNSanPham.deleteTableSach();
						GUI_CNSanPham.updateTableVPP();
						GUI_CNSanPham.updateTableSach();

						GUI_DSSanPham.deleteTableDSSanPham();
						GUI_DSSanPham.updateTableDSSanPham();
						JOptionPane.showMessageDialog(null, "Xóa thành công");

					} else {
						JOptionPane.showMessageDialog(null, "Chưa chọn văn phòng phẩm cần xóa");
					}

				} else {
					if (tblSach.getSelectedRow() > -1) {
						SanPhamDao spdao = new SanPhamDao();
						spdao.xoaSanPham((String) tblSach.getValueAt(tblSach.getSelectedRow(), 0));
						GUI_CNSanPham.deleteTableVPP();
						GUI_CNSanPham.deleteTableSach();
						GUI_CNSanPham.updateTableVPP();
						GUI_CNSanPham.updateTableSach();

						GUI_DSSanPham.deleteTableDSSanPham();
						GUI_DSSanPham.updateTableDSSanPham();
						JOptionPane.showMessageDialog(null, "Xóa thành công");
					} else {
						JOptionPane.showMessageDialog(null, "Chưa chọn sách cần xóa");
					}
				}
			}
		});
		btnXoaSanPham.setIcon(new ImageIcon(GUI_CNSanPham.class.getResource("/images/xoasanpham.png")));
		btnXoaSanPham.setColorClick(new Color(220, 20, 60));
		btnXoaSanPham.setColorOver(new Color(255, 99, 71));
		btnXoaSanPham.setBorderColor(new Color(255, 0, 0));
		btnXoaSanPham.setBorder(null);
		btnXoaSanPham.setFont(new Font("Segoe UI", Font.BOLD, 20));
		panel.add(btnXoaSanPham);

		MyButton btnSuaTTSanPham = new MyButton("Cập nhật thông tin sản phẩm");
		btnSuaTTSanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (tabbedPane.getSelectedIndex() == 0) {
					if (tblVPP.getSelectedRow() > -1) {
						new GUI_SuaVanPhongPham();
					} else {
						JOptionPane.showMessageDialog(null, "Chưa chọn văn phòng phẩm cần sửa thông tin");
					}

				} else {
					if (tblSach.getSelectedRow() > -1) {
						new GUI_SuaSach();
					} else {
						JOptionPane.showMessageDialog(null, "Chưa chọn sách cần sửa thông tin");
					}

				}

			}
		});
		btnSuaTTSanPham.setColorClick(new Color(255, 165, 0));
		btnSuaTTSanPham.setColorOver(new Color(255, 215, 0));
		btnSuaTTSanPham.setBorderColor(new Color(255, 165, 0));
		btnSuaTTSanPham.setBorder(null);
		btnSuaTTSanPham.setIcon(new ImageIcon(GUI_CNSanPham.class.getResource("/images/suathongtin.png")));
		btnSuaTTSanPham.setText("Sửa thông tin SP");
		btnSuaTTSanPham.setFont(new Font("Segoe UI", Font.BOLD, 20));
		panel.add(btnSuaTTSanPham);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBackground(Color.WHITE);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setOpaque(true);
		tabbedPane.setBackground(Color.WHITE);
		scrollPane.setViewportView(tabbedPane);

		JPanel pnlVPP = new JPanel();
		tabbedPane.addTab("Văn Phòng Phẩm", null, pnlVPP, null);
		pnlVPP.setLayout(new BorderLayout(0, 0));

		JSplitPane spltVPP = new JSplitPane();
		spltVPP.setBackground(Color.WHITE);
		spltVPP.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pnlVPP.add(spltVPP);

		JLabel lblVPP = new JLabel("Danh Sách Văn Phòng Phẩm");
		lblVPP.setOpaque(true);
		lblVPP.setForeground(new Color(25, 25, 112));
		lblVPP.setBackground(Color.WHITE);
		lblVPP.setHorizontalAlignment(SwingConstants.CENTER);
		lblVPP.setFont(new Font("Segoe UI", Font.BOLD, 30));
		spltVPP.setLeftComponent(lblVPP);

		String[] title = { "Mã sản phẩm", "Tên sản phẩm", "Chất liệu", "Xuất xứ", "Nhà sản xuất", "Số lượng",
				"Giá nhập", "Giá đơn vị" };
		JScrollPane scrollPane_1 = new JScrollPane(tblVPP = new JTable(dataModelVPP = new DefaultTableModel(title, 0) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return canEdit[column];
			}
		}), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblVPP.setAutoCreateRowSorter(true);
		tblVPP.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setBackground(Color.WHITE);
		scrollPane_1.setOpaque(true);
		tblVPP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tblVPP.setRowHeight(30);
		spltVPP.setRightComponent(scrollPane_1);

		scrollPane_1.setViewportView(tblVPP);

		JPanel pnlSach = new JPanel();
		pnlSach.setBackground(Color.WHITE);
		tabbedPane.addTab("Sách", null, pnlSach, null);
		pnlSach.setLayout(new BorderLayout(0, 0));

		JSplitPane spltSach = new JSplitPane();
		spltSach.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pnlSach.add(spltSach);

		JLabel lblSach = new JLabel("Danh Sách Sách");
		lblSach.setBackground(Color.WHITE);
		lblSach.setOpaque(true);
		lblSach.setForeground(new Color(25, 25, 112));
		lblSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblSach.setFont(new Font("Segoe UI", Font.BOLD, 30));
		spltSach.setLeftComponent(lblSach);

		String[] title2 = { "Mã sách", "Tên sách", "Tác giả", "Số trang", "Nhà xuất bản", "Số lượng", "Giá nhập",
				"Giá đơn vị" };
		JScrollPane scrollPane_2 = new JScrollPane(
				tblSach = new JTable(dataModelSach = new DefaultTableModel(title2, 0)),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblSach.setAutoCreateRowSorter(true);
		scrollPane_2.setBackground(Color.WHITE);
		tblSach.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblSach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tblSach.setRowHeight(30);
		spltSach.setRightComponent(scrollPane_2);

		scrollPane_2.setViewportView(tblSach);

		JLabel lblNewLabel_2 = new JLabel("Cập nhật thông tin Sản Phẩm");
		lblNewLabel_2.setForeground(new Color(25, 25, 112));
		lblNewLabel_2.setBackground(Color.WHITE);
		splitPane.setLeftComponent(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 30));

		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut, BorderLayout.SOUTH);
		updateTableVPP();
		updateTableSach();
	}

	public static void updateTableVPP() {
		SanPhamDao spdao = new SanPhamDao();
		ArrayList<SanPham> dssp = spdao.layDsSVPP();

		for (SanPham i : dssp) {
			dataModelVPP.addRow(new Object[] { i.getMasanpham(), i.getTensanpham(), i.getChatlieu(), i.getXuatxu(),
					i.getNhacungcap(), i.getSoluong(), new DecimalFormat("###,###,###").format((int) Math.floor(i.getGianhap())), new DecimalFormat("###,###,###").format((int) Math.floor(i.getGiadonvi())) });
		}

	}

	public static void deleteTableVPP() {
		int dem = dataModelVPP.getRowCount();
		for (int i = 0; i < dem; i++) {
			dataModelVPP.removeRow(0);
			;
		}

	}

	public static void updateTableSach() {
		SanPhamDao spdao = new SanPhamDao();
		ArrayList<SanPham> dssp = spdao.layDsSach();

		for (SanPham i : dssp) {
			dataModelSach.addRow(new Object[] { i.getMasanpham(), i.getTensanpham(), i.getTentacgia(), i.getSotrang(),
					i.getNhaxuatban(), i.getSoluong(), new DecimalFormat("###,###,###").format((int) Math.floor(i.getGianhap())), new DecimalFormat("###,###,###").format((int) Math.floor(i.getGiadonvi()))});
		}

	}

	public static void deleteTableSach() {
		int dem = dataModelSach.getRowCount();
		for (int i = 0; i < dem; i++) {
			dataModelSach.removeRow(0);
			;
		}

	}

	public static Object[] getSelectedRowVPP() {

		int row = tblVPP.getSelectedRow();

		if (row != -1) {
			return new Object[] { tblVPP.getValueAt(row, 0), tblVPP.getValueAt(row, 1), tblVPP.getValueAt(row, 2),
					tblVPP.getValueAt(row, 3), tblVPP.getValueAt(row, 4), tblVPP.getValueAt(row, 5),
					tblVPP.getValueAt(row, 6), tblVPP.getValueAt(row, 7) };
		}

		return null;

	}

	public static Object[] getSelectedRowSach() {

		int row = tblSach.getSelectedRow();

		if (row != -1) {
			return new Object[] { tblSach.getValueAt(row, 0), tblSach.getValueAt(row, 1), tblSach.getValueAt(row, 2),
					tblSach.getValueAt(row, 3), tblSach.getValueAt(row, 4), tblSach.getValueAt(row, 5),
					tblSach.getValueAt(row, 6), tblSach.getValueAt(row, 7) };
		}

		return null;

	}

}
