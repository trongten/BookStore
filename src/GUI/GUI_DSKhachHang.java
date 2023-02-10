package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Others.MyButton;
import dao.KhachHangDao;
import entity.KhachHang;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

public class GUI_DSKhachHang extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tblDSKhachHang;
	private static DefaultTableModel dataModel;

	/**
	 * Create the panel.
	 */

	@SuppressWarnings("serial")
	public GUI_DSKhachHang() {
		setBackground(new Color(255, 255, 255));
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setTitle("Danh sách khách hàng");
		frm.getContentPane().setBackground(new Color(255, 255, 255));
		frm.setBackground(new Color(255, 255, 255));
		frm.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
		frm.getContentPane().add(this);
		setLayout(new BorderLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBackground(new Color(255, 255, 255));
		add(splitPane);
		String[] title = { "Mã khách hàng", "Tên khách hàng", "Số điện thoại" };
		JScrollPane scrollPane = new JScrollPane(
				tblDSKhachHang = new JTable(dataModel = new DefaultTableModel(title, 0) {
					boolean[] canEdit = new boolean[] { false, false, false };

					public boolean isCellEditable(int row, int column) {
						return canEdit[column];
					}
				}), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblDSKhachHang.setAutoCreateRowSorter(true);
		tblDSKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDSKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setBackground(Color.WHITE);
		splitPane.setRightComponent(scrollPane);
		tblDSKhachHang.setRowHeight(30);
		scrollPane.setViewportView(tblDSKhachHang);

		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));

		MyButton btnTaiLai = new MyButton("Tải lại");
		btnTaiLai.setIcon(new ImageIcon(GUI_DSKhachHang.class.getResource("/images/tailai.png")));
		btnTaiLai.setText("   Tải lại   ");
		btnTaiLai.setBorder(null);
		btnTaiLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteTableDSKhachHang();
				updateTableDSKhachHang();
			}
		});
		panel.add(btnTaiLai, BorderLayout.WEST);

		JLabel lblDSKhachHang = new JLabel("Danh Sách Khách hàng");
		lblDSKhachHang.setMaximumSize(new Dimension(700, 13));
		lblDSKhachHang.setHorizontalAlignment(SwingConstants.CENTER);
		lblDSKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblDSKhachHang.setBackground(Color.WHITE);
		panel.add(lblDSKhachHang);
		updateTableDSKhachHang();
	}

	public static void deleteTableDSKhachHang() {

		int dem = dataModel.getRowCount();
		for (int i = 0; i < dem; i++) {
			dataModel.removeRow(0);
		}
	}

	public static void updateTableTimDSKhachHang(ArrayList<KhachHang> list) {
		ArrayList<KhachHang> dskh = list;
		for (KhachHang i : dskh) {
			dataModel.addRow(new Object[] { i.getMakh(), i.getTenkhachhang(), i.getSodienthoai() });
		}
	}

	public static void updateTableDSKhachHang() {
		KhachHangDao khdao = new KhachHangDao();
		ArrayList<KhachHang> dskh = khdao.layDsKhachHang();
		for (KhachHang i : dskh) {
			dataModel.addRow(new Object[] { i.getMakh(), i.getTenkhachhang(), i.getSodienthoai() });
		}

	}
}
