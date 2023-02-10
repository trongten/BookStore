package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
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
import javax.swing.table.DefaultTableModel;

import Others.MyButton;
import dao.NhanVienDao;
import dao.TaiKhoanDao;
import entity.NhanVien;

/**
 * Giao diện danh sách nhân viên
 * 
 * @author Phan Võ Trọng - Võ Phước Lưu - Nguyễn Phạm Công Nhật
 */
public class GUI_DSNhanVien extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel txtMaNV;
	private JLabel txtTenNV;
	private JLabel txtNgaySinh;
	private JLabel txtGioiTinh;
	private JLabel txtSdt;
	private JLabel txtDiaChi;
	private JLabel txtCMND;
	private JLabel txtCa;
	private JLabel txtTrangThai;

	private static DefaultTableModel dataModelDSNV;
	private static JTable tblNhanVien;

	/**
	 * Create the panel.
	 */

	@SuppressWarnings("serial")
	public GUI_DSNhanVien() {
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
		splitPane_1.setBackground(new Color(255, 255, 255));
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		panel.add(splitPane_1, BorderLayout.CENTER);

		JLabel lblNewLabel_2 = new JLabel("Danh Sách Nhân Viên");
		lblNewLabel_2.setForeground(new Color(25, 25, 112));
		lblNewLabel_2.setMaximumSize(new Dimension(700, 13));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 30));
		splitPane_1.setLeftComponent(lblNewLabel_2);

		String[] tieudeCNNV = { "Mã nhân viên", "Tên nhân viên", "Trạng thái làm việc", "Ca", "Chức vụ",
				"Số điện thoại" };
		JScrollPane scrollPane = new JScrollPane(
				tblNhanVien = new JTable(dataModelDSNV = new DefaultTableModel(tieudeCNNV, 0) {
					boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return canEdit[column];
					}
				}), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblNhanVien.setAutoCreateRowSorter(true);
		tblNhanVien.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblNhanVien.setRowHeight(30);
		tblNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tblNhanVien.getColumnModel().getColumn(1).setPreferredWidth(200);
		tblNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setText();
			}
		});
		scrollPane.setBackground(new Color(255, 255, 255));
		splitPane_1.setRightComponent(scrollPane);

		JPanel pnlTTSanPham = new JPanel();

		pnlTTSanPham.setBackground(new Color(255, 255, 255));
		splitPane.setRightComponent(pnlTTSanPham);
		pnlTTSanPham.setLayout(new BorderLayout(0, 0));
		pnlTTSanPham.setMaximumSize(new Dimension(300, 1080));

		JLabel lblNewLabel = new JLabel("Thông tin nhân viên");
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

		JPanel pnlThongTinSP = new JPanel();
		pnlThongTinSP.setMaximumSize(new Dimension(450, 32767));
		pnlThongTinSP.setBackground(new Color(255, 255, 255));
		panel_2.add(pnlThongTinSP);
		pnlThongTinSP.setLayout(new GridLayout(13, 4, 15, 25));

		JLabel lblMaSP = new JLabel("Mã Nhân viên:");
		lblMaSP.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblMaSP.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(lblMaSP);

		txtMaNV = new JLabel();
		txtMaNV.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtMaNV.setText("unknown");
		txtMaNV.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(txtMaNV);

		JLabel lblNewLabel_3 = new JLabel("Tên nhân viên:");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(lblNewLabel_3);

		txtTenNV = new JLabel();
		txtTenNV.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtTenNV.setText("unknown");
		txtTenNV.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(txtTenNV);

		JLabel lblNewLabel_4 = new JLabel("Ngày sinh:");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(lblNewLabel_4);

		txtNgaySinh = new JLabel();
		txtNgaySinh.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtNgaySinh.setText("unknown");
		txtNgaySinh.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(txtNgaySinh);

		JLabel lblNewLabel_1 = new JLabel("Ca làm:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
		pnlThongTinSP.add(lblNewLabel_1);

		txtCa = new JLabel("unknown");
		txtCa.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		pnlThongTinSP.add(txtCa);

		JLabel lblNewLabel_10 = new JLabel("Chứng minh thư:");
		lblNewLabel_10.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_10.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(lblNewLabel_10);

		txtCMND = new JLabel();
		txtCMND.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtCMND.setText("unknown");
		txtCMND.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(txtCMND);

		JLabel lblNewLabel_6 = new JLabel("Số điện thoại:");
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_6.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(lblNewLabel_6);

		txtSdt = new JLabel();
		txtSdt.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtSdt.setText("unknown");
		txtSdt.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(txtSdt);

		JLabel lblNewLabel_7 = new JLabel("Địa chỉ thường trú:");
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_7.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(lblNewLabel_7);

		txtDiaChi = new JLabel();
		txtDiaChi.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtDiaChi.setText("unknown");
		txtDiaChi.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(txtDiaChi);

		JLabel lblNewLabel_5 = new JLabel("Giới tính:");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(lblNewLabel_5);

		txtGioiTinh = new JLabel();
		txtGioiTinh.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtGioiTinh.setText("unknown");
		txtGioiTinh.setBackground(new Color(255, 255, 255));
		pnlThongTinSP.add(txtGioiTinh);

		JLabel lblNewLabel_9 = new JLabel("Trang thái làm việc:");
		lblNewLabel_9.setFont(new Font("Segoe UI", Font.BOLD, 15));
		pnlThongTinSP.add(lblNewLabel_9);

		txtTrangThai = new JLabel("unknown");
		txtTrangThai.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		pnlThongTinSP.add(txtTrangThai);

		Component horizontalStrut = Box.createHorizontalStrut(17);
		horizontalStrut.setBackground(new Color(255, 255, 255));
		pnlTTSanPham.add(horizontalStrut, BorderLayout.EAST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(16);
		horizontalStrut_1.setBackground(new Color(255, 255, 255));
		pnlTTSanPham.add(horizontalStrut_1, BorderLayout.WEST);

		JPanel panel_3 = new JPanel();
		pnlTTSanPham.add(panel_3, BorderLayout.SOUTH);
		panel_3.setBorder(null);
		panel_3.setBackground(new Color(255, 255, 255));

		JLabel lblNewLabel_8 = new JLabel("Reset Mật khẩu:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_3.add(lblNewLabel_8);

		MyButton btnReset = new MyButton("Reset");
		btnReset.setColorClick(new Color(165, 42, 42));
		btnReset.setColorOver(new Color(255, 69, 0));
		btnReset.setBorderColor(new Color(160, 82, 45));
		btnReset.setFocusPainted(false);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// hỏi có reset mật khẩu hay không
				int hoi = JOptionPane.showConfirmDialog(null, "Reset mật khẩu của tài khoản " + txtMaNV.getText() + "?",
						"Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (hoi == JOptionPane.YES_OPTION) {
					if (new TaiKhoanDao().resetMatKhauNhanVien(txtMaNV.getText())) {
						JOptionPane.showMessageDialog(null,
								"Reset mật khẩu của tài khoản " + txtMaNV.getText() + " thành công");
					} else {
						JOptionPane.showMessageDialog(null,
								"Reset mật khẩu của tài khoản " + txtMaNV.getText() + " Thất bại", "Thông báo",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_3.add(btnReset);
		updateTableDSNV();

	}

	/**
	 * Cập nhật table danh sách nhân viên
	 */
	public static void updateTableDSNV() {
		NhanVienDao nvdao = new NhanVienDao();
		ArrayList<NhanVien> dsnv = nvdao.layDsNhanVien();

		for (NhanVien i : dsnv) {
			int ca = i.getCa();
			String caLam = "";
			if (ca == 0) {
				caLam = "Toàn thời gian";
			}
			if (ca == 1) {
				caLam = "1";
			}
			if (ca == 2) {
				caLam = "2";
			}
			dataModelDSNV.addRow(new Object[] { i.getManhanvien(), i.getHoten(),
					i.isTrangthailamviec() == true ? "Đang làm việc" : "Đã nghỉ việc", caLam,
					i.getQuanly().getManhanvien() == null ? "Quản lý" : "Nhân viên", i.getSdt() });
		}

	}

	/**
	 * Xóa table danh sách nhân viên
	 */
	public static void deleteTableDSNV() {
		int dem = dataModelDSNV.getRowCount();
		for (int i = 0; i < dem; i++) {
			dataModelDSNV.removeRow(0);
		}

	}

	/**
	 * Chuyển dữ liệu từ table thành text
	 */
	public void setText() {

		int row = tblNhanVien.getSelectedRow();
		if (row != -1) {
			NhanVien nv = new NhanVienDao().timNhanVien((String) dataModelDSNV.getValueAt(row, 0));
			int ca = nv.getCa();
			String caLam = "";
			if (ca == 0) {
				caLam = "Toàn thời gian";
			}
			if (ca == 1) {
				caLam = "1";
			}
			if (ca == 2) {
				caLam = "2";
			}

			txtMaNV.setText(nv.getManhanvien());
			txtTenNV.setText(nv.getHoten());
			txtGioiTinh.setText(nv.isGioitinh() ? "Nam" : "Nữ");
			txtCMND.setText(nv.getCmnd());
			txtDiaChi.setText(nv.getDiachi());
			txtNgaySinh.setText(nv.getNgaysinh().toString().split("-")[2] + "-"
					+ nv.getNgaysinh().toString().split("-")[1] + "-" + nv.getNgaysinh().toString().split("-")[0]);
			txtCa.setText(caLam);
			txtSdt.setText(nv.getSdt());
			if (nv.isTrangthailamviec()) {
				txtTrangThai.setText("Đang làm việc");
				txtTrangThai.setForeground(Color.green);
			} else {
				txtTrangThai.setText("Đã nghỉ việc");
				txtTrangThai.setForeground(Color.red);
			}

		}

	}

}
