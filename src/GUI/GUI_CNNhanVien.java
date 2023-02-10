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
import connectDB.Database;
import dao.NhanVienDao;
import dao.TaiKhoanDao;
import entity.NhanVien;
import entity.TaiKhoan;

import javax.swing.ListSelectionModel;

public class GUI_CNNhanVien extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JTable tblDSNhanVien;
	private static DefaultTableModel dataModelCNKH;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public GUI_CNNhanVien() {
		setBackground(Color.WHITE);
		setOpaque(false);
		setSize(1517, 1000);

		setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_2 = new JLabel("Cập nhật Nhân Viên");
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 30));
		add(lblNewLabel_2, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setOpaque(false);
		add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel_1.add(panel, BorderLayout.WEST);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Chức Năng",
				TitledBorder.RIGHT, TitledBorder.TOP, null, null));
		panel.setLayout(new GridLayout(8, 1, 30, 20));

		MyButton btnThemNhanVien = new MyButton("Thêm Nhân viên");
		btnThemNhanVien.setBorder(new CompoundBorder(null, new CompoundBorder()));
		btnThemNhanVien.setColorClick(new Color(60, 179, 113));
		btnThemNhanVien.setColorOver(new Color(154, 205, 50));
		btnThemNhanVien.setBorderColor(new Color(154, 205, 50));
		btnThemNhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		btnThemNhanVien.setIcon(new ImageIcon(GUI_CNNhanVien.class.getResource("/images/themnhanvien.png")));
		btnThemNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblDSNhanVien.getSelectedRow() > -1) {
					NhanVienDao nvdao = new NhanVienDao();
					nvdao.ThemNhanVienDaCo((String) dataModelCNKH.getValueAt(tblDSNhanVien.getSelectedRow(), 0));

					JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
				} else {
					new GUI_ThemNV().setVisible(true);
				}
				GUI_CNNhanVien.deleteTableCNNV();
				GUI_CNNhanVien.updateTableCNNV();
				GUI_DSNhanVien.deleteTableDSNV();
				GUI_DSNhanVien.updateTableDSNV();
			}
		});
		btnThemNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 20));
		panel.add(btnThemNhanVien);

		MyButton btnXoaNhanVien = new MyButton("Xa thải Nhân viên");
		btnXoaNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblDSNhanVien.getSelectedRow() > -1) {
					NhanVienDao nvdao = new NhanVienDao();
					nvdao.xoaNhanVien((String) dataModelCNKH.getValueAt(tblDSNhanVien.getSelectedRow(), 0));
					ArrayList<TaiKhoan> dstk = new ArrayList<>();
					for (TaiKhoan tk: dstk) {
						if(tk.getTaikhoan().toString() == (String)dataModelCNKH.getValueAt(tblDSNhanVien.getSelectedRow(), 0));
						new TaiKhoanDao().suaMatKhau(null);
					}
					GUI_CNNhanVien.deleteTableCNNV();
					GUI_CNNhanVien.updateTableCNNV();
					GUI_DSNhanVien.deleteTableDSNV();
					GUI_DSNhanVien.updateTableDSNV();
					JOptionPane.showMessageDialog(null, "Xa thải nhân viên thành công!");
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên!");
				}
			}
		});
		btnXoaNhanVien.setBorder(new CompoundBorder(null, new CompoundBorder()));
		btnXoaNhanVien.setBorderColor(new Color(255, 0, 0));
		btnXoaNhanVien.setColorOver(new Color(255, 0, 0));
		btnXoaNhanVien.setColorClick(new Color(160, 82, 45));
		btnXoaNhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		btnXoaNhanVien.setIcon(new ImageIcon(GUI_CNNhanVien.class.getResource("/images/xoanhanvien.png")));
		btnXoaNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 20));
		panel.add(btnXoaNhanVien);

		MyButton btnSuaTTNhanVien = new MyButton("Sửa Nhân viên");
		btnSuaTTNhanVien.setBorder(new CompoundBorder(null, new CompoundBorder()));
		btnSuaTTNhanVien.setColorClick(new Color(255, 204, 0));
		btnSuaTTNhanVien.setColorOver(new Color(255, 255, 102));
		btnSuaTTNhanVien.setBorderColor(new Color(255, 215, 0));
		btnSuaTTNhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		btnSuaTTNhanVien.setIcon(new ImageIcon(GUI_CNNhanVien.class.getResource("/images/suathongtin.png")));
		btnSuaTTNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblDSNhanVien.getSelectedRow() > -1) {
					new GUI_SuaNV().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên!");
				}

			}
		});
		btnSuaTTNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 20));
		panel.add(btnSuaTTNhanVien);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel_1.add(splitPane, BorderLayout.CENTER);

		JLabel lblNewLabel_2_1 = new JLabel("Danh Sách Nhân Viên");
		lblNewLabel_2_1.setOpaque(true);
		lblNewLabel_2_1.setBackground(Color.WHITE);
		lblNewLabel_2_1.setForeground(new Color(0, 0, 128));
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("s", Font.BOLD, 30));
		splitPane.setLeftComponent(lblNewLabel_2_1);

		String[] tieudeCNNV = { "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Địa chỉ", "CMND", "Trạng thái làm việc", "Giới tính",
				"Ca", "Chức vụ", "Số điện thoại" };
		JScrollPane scrollPane = new JScrollPane(
				tblDSNhanVien = new JTable(dataModelCNKH = new DefaultTableModel(tieudeCNNV, 0) {
					boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false, false, false,
							false };

					public boolean isCellEditable(int row, int column) {
						return canEdit[column];
					}
				}), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblDSNhanVien.setAutoCreateRowSorter(true);
		scrollPane.setBackground(Color.WHITE);
		tblDSNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDSNhanVien.setRowHeight(30);
		tblDSNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setOpaque(false);
		splitPane.setRightComponent(scrollPane);
		scrollPane.setViewportView(tblDSNhanVien);
		Database.getInstance().connect();
		Component verticalStrut = Box.createVerticalStrut(24);
		add(verticalStrut, BorderLayout.SOUTH);
		updateTableCNNV();
	}

	/*
	 * * Day du lieu SQL len bang Cap Nhat Nhan Vien
	 */
	public static void updateTableCNNV() {
		NhanVienDao nvdao = new NhanVienDao();
		ArrayList<NhanVien> dsnv = nvdao.layDsNhanVien();
		// sửa tiếp
		for (NhanVien i : dsnv) {
			String ca = null;
			if (i.getCa() == 0) {
				ca = "Toàn thời gian";
			} else if (i.getCa() == 1) {
				ca = "1";
			} else {
				ca = "2";
			}
			dataModelCNKH.addRow(new Object[] { i.getManhanvien(), i.getHoten(),
					i.getNgaysinh().toString().split("-")[2] + "-" + i.getNgaysinh().toString().split("-")[1] + "-"
							+ i.getNgaysinh().toString().split("-")[0],
					i.getDiachi(), i.getCmnd(), i.isTrangthailamviec() == false ?  "Đã nghỉ việc":"Đang làm việc" ,
					i.isGioitinh() == true ? "Nam" : "Nữ", ca,
					i.getQuanly().getManhanvien() == null ? "Quản lý" : "Nhân Viên", i.getSdt() });
		}

	}

	public static void deleteTableCNNV() {
		int dem = dataModelCNKH.getRowCount();
		for (int i = 0; i < dem; i++) {
			dataModelCNKH.removeRow(0);
		}

	}

	public static Object[] getSelectedRow() {

		int row = tblDSNhanVien.getSelectedRow();
		if (row != -1) {
			return new Object[] { tblDSNhanVien.getValueAt(row, 0), tblDSNhanVien.getValueAt(row, 1),
					tblDSNhanVien.getValueAt(row, 2), tblDSNhanVien.getValueAt(row, 3),
					tblDSNhanVien.getValueAt(row, 4), tblDSNhanVien.getValueAt(row, 5),
					tblDSNhanVien.getValueAt(row, 6), tblDSNhanVien.getValueAt(row, 7),
					tblDSNhanVien.getValueAt(row, 8), tblDSNhanVien.getValueAt(row, 9) };
		}
		return null;

	}

}
