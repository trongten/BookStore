package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Others.MyButton;
import dao.KhachHangDao;
import entity.KhachHang;
import javax.swing.ListSelectionModel;

public class GUI_CNKhachHang extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static DefaultTableModel dataModelCNKH;
	private static JTable tblDSKhachHang;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public GUI_CNKhachHang() {
		setOpaque(false);
		setSize(1517, 700);

		setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel_1.add(panel, BorderLayout.WEST);
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Chức Năng",
				TitledBorder.RIGHT, TitledBorder.TOP, null, null));
		panel.setLayout(new GridLayout(7, 1, 30, 20));

		MyButton btnThemKhachHang = new MyButton("Thêm Nhân viên");
		btnThemKhachHang.setText("Thêm Khách hàng ");
		btnThemKhachHang.setBorder(new CompoundBorder(null, new CompoundBorder()));
		btnThemKhachHang.setColorClick(new Color(60, 179, 113));
		btnThemKhachHang.setColorOver(new Color(154, 205, 50));
		btnThemKhachHang.setBorderColor(new Color(154, 205, 50));
		btnThemKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		btnThemKhachHang.setIcon(new ImageIcon(GUI_CNKhachHang.class.getResource("/images/themnhanvien.png")));
		btnThemKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GUI_ThemKhachHang().setVisible(true);
			}
		});
		btnThemKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		panel.add(btnThemKhachHang);

		MyButton btnSuaTTKhachHang = new MyButton("Sửa khách hàng");
		btnSuaTTKhachHang.setBorder(new CompoundBorder(null, new CompoundBorder()));
		btnSuaTTKhachHang.setColorClick(new Color(255, 204, 0));
		btnSuaTTKhachHang.setColorOver(new Color(255, 255, 102));
		btnSuaTTKhachHang.setBorderColor(new Color(255, 215, 0));
		btnSuaTTKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		btnSuaTTKhachHang.setIcon(new ImageIcon(GUI_CNKhachHang.class.getResource("/images/suathongtin.png")));
		btnSuaTTKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tblDSKhachHang.getSelectedRow() > -1) {
					new GUI_SuaKhachHang().setVisible(true);
					
				}else {
					JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng cần sửa thông tin!");
				}
				
			}
		});
		
		btnSuaTTKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		panel.add(btnSuaTTKhachHang);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBackground(Color.WHITE);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(splitPane, BorderLayout.CENTER);

		JLabel lblNewLabel_2_1 = new JLabel("Danh Sách Khách hàng");
		lblNewLabel_2_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.BOLD, 30));
		splitPane.setLeftComponent(lblNewLabel_2_1);

		String[] title = { "Mã khách hàng", "Tên khách hàng", "Số điện thoại" };
		JScrollPane scrollPane = new JScrollPane(
				tblDSKhachHang = new JTable(dataModelCNKH = new DefaultTableModel(title, 0){
					boolean[] canEdit = new boolean[] { false, false, false };

					public boolean isCellEditable(int row, int column) {
						return canEdit[column];
					}
				}),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblDSKhachHang.setAutoCreateRowSorter(true);
		tblDSKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDSKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setBackground(Color.WHITE);
		splitPane.setRightComponent(scrollPane);
		tblDSKhachHang.setRowHeight(30);
		scrollPane.setViewportView(tblDSKhachHang);

		JLabel lblNewLabel_2 = new JLabel("Cập nhật Khách hàng");
		lblNewLabel_2.setForeground(new Color(25, 25, 112));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 30));
		panel_1.add(lblNewLabel_2, BorderLayout.NORTH);

		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut, BorderLayout.SOUTH);
		updateTableCNKH();
	}

	public static void updateTableCNKH() {
		KhachHangDao khdao = new KhachHangDao();
		ArrayList<KhachHang> dskh = khdao.layDsKhachHang();
		for (KhachHang i : dskh) {
			dataModelCNKH.addRow(new Object[] { i.getMakh(), i.getTenkhachhang(), i.getSodienthoai() });
		}

	}

	public static void deleteTableCNKH() {
		int dem = dataModelCNKH.getRowCount();
		for (int i = 0; i < dem; i++) {
			dataModelCNKH.removeRow(0);
		}
	}

	public static Object[] getSelectedRow() {

		int row = tblDSKhachHang.getSelectedRow();
		if (row != -1) {
			return new Object[] { tblDSKhachHang.getValueAt(row, 0), tblDSKhachHang.getValueAt(row, 1),
					tblDSKhachHang.getValueAt(row, 2) };
		}
		return null;

	}
}