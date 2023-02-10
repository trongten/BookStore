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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Others.MyButton;
import dao.KhachHangDao;
import entity.KhachHang;

public class GUI_TimKiemKhachHang extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaKH;
	private JTextField txtTenKhachHang;
	private final JTextField txtSoDienThoai = new JTextField();

	/**
	 * Create the panel.
	 */
	public GUI_TimKiemKhachHang() {
		setTitle("Tìm kiếm Khách hàng");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(230, 230, 250));
		setBackground(new Color(230, 230, 250));
		setAlwaysOnTop(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(462, 317);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 2, 0, 40));

		JLabel lblNewLabel_1 = new JLabel("Mã khách hàng:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_1);

		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_2.add(txtMaKH);
		txtMaKH.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Tên khách hàng:");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_2);

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_2.add(txtTenKhachHang);
		txtTenKhachHang.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Số điện thoại:");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_4);
		txtSoDienThoai.setText("");
		panel_2.add(txtSoDienThoai);
		txtSoDienThoai.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.SOUTH);

		MyButton btnTm = new MyButton("Tìm");
		btnTm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTm.setBackground(Color.WHITE);
		btnTm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String makh = txtMaKH.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtMaKH.getText().trim() + "%";

				String tenkhachhang = txtTenKhachHang.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtTenKhachHang.getText().trim() + "%";

				String sodienthoai = txtSoDienThoai.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtSoDienThoai.getText().trim() + "%";

				KhachHangDao khdao = new KhachHangDao();
				ArrayList<KhachHang> list = khdao.timKhachHangNangCao(makh, tenkhachhang, sodienthoai);
				try {
					GUIChinh_QuanLy.changeLayerPanelDSKH();
				} catch (Exception er) {
					GUIChinh_NhanVien.changeLayerPanelDSKH();
				}
				GUI_DSKhachHang.deleteTableDSKhachHang();
				GUI_DSKhachHang.updateTableTimDSKhachHang(list);
			}
		});

		panel_3.add(btnTm);

		MyButton btnThoat = new MyButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThoat.setBackground(Color.WHITE);
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_3.add(btnThoat);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut, BorderLayout.WEST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_1, BorderLayout.EAST);

		Component verticalStrut = Box.createVerticalStrut(12);
		panel.add(verticalStrut, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(null);
		getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Tìm kiếm Khách hàng");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		panel_1.add(lblNewLabel);

	}
}
