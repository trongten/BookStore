package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Others.MyButton;
import dao.SanPhamDao;
import entity.SanPham;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;

public class GUI_TimKiemSanPham extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaSanPham;
	private JTextField txtTenSanPham;
	private JTextField txtGiaDonViSanPham;
	private JTextField GiaNhapSanPham;
	private JTextField txtChatLieu;
	private JTextField txtXuatXu;
	private JTextField txtMaSach;
	private JTextField txtTenSach;
	private JTextField txtGiaDonViSach;
	private JSpinner txtSoTrang;
	private JTextField TxtTacGia;
	private JTextField TxtNhaXuatBan;

	/**
	 * Create the panel.
	 */
	public GUI_TimKiemSanPham() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(230, 230, 250));
		setBackground(new Color(230, 230, 250));
		setAlwaysOnTop(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(770, 590);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(new Color(0, 0, 128));
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Văn phòng phẩm", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Mã sản phẩm:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(304, 64, 126, 26);
		panel_2.add(lblNewLabel_1);

		txtMaSanPham = new JTextField();
		txtMaSanPham.setColumns(10);
		txtMaSanPham.setBounds(440, 61, 285, 29);
		panel_2.add(txtMaSanPham);

		txtTenSanPham = new JTextField();
		txtTenSanPham.setColumns(10);
		txtTenSanPham.setBounds(440, 114, 285, 29);
		panel_2.add(txtTenSanPham);

		JLabel lblNewLabel_1_1 = new JLabel("Tên sản phẩm:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(304, 117, 126, 26);
		panel_2.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Giá đơn vị:");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(304, 173, 126, 26);
		panel_2.add(lblNewLabel_1_2);

		txtGiaDonViSanPham = new JTextField();
		txtGiaDonViSanPham.setColumns(10);
		txtGiaDonViSanPham.setBounds(440, 170, 285, 29);
		panel_2.add(txtGiaDonViSanPham);

		GiaNhapSanPham = new JTextField();
		GiaNhapSanPham.setColumns(10);
		GiaNhapSanPham.setBounds(440, 231, 285, 29);
		panel_2.add(GiaNhapSanPham);

		JLabel lblNewLabel_1_3 = new JLabel("Giá nhập:");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(304, 234, 126, 26);
		panel_2.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Chất liệu:");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_4.setBounds(304, 298, 126, 26);
		panel_2.add(lblNewLabel_1_4);

		txtChatLieu = new JTextField();
		txtChatLieu.setColumns(10);
		txtChatLieu.setBounds(440, 295, 285, 29);
		panel_2.add(txtChatLieu);

		txtXuatXu = new JTextField();
		txtXuatXu.setColumns(10);
		txtXuatXu.setBounds(440, 362, 285, 29);
		panel_2.add(txtXuatXu);

		JLabel lblNewLabel_1_5 = new JLabel("Xuất xứ:");
		lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_5.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_5.setBounds(304, 365, 126, 26);
		panel_2.add(lblNewLabel_1_5);

		MyButton btnTimVPP = new MyButton("Tìm");
		btnTimVPP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String masp = txtMaSanPham.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtMaSanPham.getText().trim() + "%";
				String tensp = txtTenSanPham.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtTenSanPham.getText().trim() + "%";
				String giadv = txtGiaDonViSanPham.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtGiaDonViSanPham.getText().trim() + "%";
				String gian = GiaNhapSanPham.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + GiaNhapSanPham.getText().trim() + "%";
				String chatlieu = txtChatLieu.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtChatLieu.getText().trim() + "%";
				String xuatxu = txtXuatXu.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtXuatXu.getText().trim() + "%";

				SanPhamDao spdao = new SanPhamDao();
				ArrayList<SanPham> list = spdao.timSanPhamNangCaoVPP(masp, tensp, giadv, gian, chatlieu, xuatxu);
				
				
				if(list.size() > 0) {
					
					
					GUIChinh_QuanLy.changeLayerPanelDSSP();
					GUI_DSSanPham.deleteTableDSSanPham();
					GUI_DSSanPham.updateTableTimDSSanPham(list);
					
					
				}else {
					JOptionPane.showMessageDialog(panel_2 , "Không tìm thấy sản phẩm phù hợp");
				}

				
				
				
			}
		});
		btnTimVPP.setForeground(Color.BLACK);
		btnTimVPP.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTimVPP.setBackground(Color.WHITE);
		btnTimVPP.setBounds(591, 419, 134, 50);
		panel_2.add(btnTimVPP);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(GUI_TimKiemSanPham.class.getResource("/images/timvanphongpham.jpg")));
		lblNewLabel_2.setBounds(0, 0, 294, 480);
		panel_2.add(lblNewLabel_2);
		tabbedPane.setBackgroundAt(0, new Color(255, 255, 255));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Sách", null, panel_3, null);
		panel_3.setLayout(null);

		JLabel lblNewLabel_1_6 = new JLabel("Mã sách:");
		lblNewLabel_1_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_6.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_6.setBounds(307, 64, 126, 26);
		panel_3.add(lblNewLabel_1_6);

		txtMaSach = new JTextField();
		txtMaSach.setColumns(10);
		txtMaSach.setBounds(443, 61, 285, 29);
		panel_3.add(txtMaSach);

		txtTenSach = new JTextField();
		txtTenSach.setColumns(10);
		txtTenSach.setBounds(443, 114, 285, 29);
		panel_3.add(txtTenSach);

		JLabel lblNewLabel_1_1_1 = new JLabel("Tên sách:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(307, 117, 126, 26);
		panel_3.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_2_1 = new JLabel("Giá đơn vị:");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_2_1.setBounds(307, 173, 126, 26);
		panel_3.add(lblNewLabel_1_2_1);

		txtGiaDonViSach = new JTextField();
		txtGiaDonViSach.setColumns(10);
		txtGiaDonViSach.setBounds(443, 170, 285, 29);
		panel_3.add(txtGiaDonViSach);

		txtSoTrang = new JSpinner();
		txtSoTrang.setBounds(443, 231, 285, 29);
		panel_3.add(txtSoTrang);

		JLabel lblNewLabel_1_3_1 = new JLabel("Số trang:");
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_3_1.setBounds(307, 234, 126, 26);
		panel_3.add(lblNewLabel_1_3_1);

		JLabel lblNewLabel_1_4_1 = new JLabel("Tác giả:");
		lblNewLabel_1_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_4_1.setBounds(307, 298, 126, 26);
		panel_3.add(lblNewLabel_1_4_1);

		TxtTacGia = new JTextField();
		TxtTacGia.setColumns(10);
		TxtTacGia.setBounds(443, 295, 285, 29);
		panel_3.add(TxtTacGia);

		TxtNhaXuatBan = new JTextField();
		TxtNhaXuatBan.setColumns(10);
		TxtNhaXuatBan.setBounds(443, 362, 285, 29);
		panel_3.add(TxtNhaXuatBan);

		JLabel lblNewLabel_1_5_1 = new JLabel("Nhà xuất bản:");
		lblNewLabel_1_5_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_5_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1_5_1.setBounds(307, 365, 126, 26);
		panel_3.add(lblNewLabel_1_5_1);

		MyButton btnTimSach = new MyButton("Tìm");
		btnTimSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String masp = txtMaSach.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtMaSach.getText().trim() + "%";
				String tensp = txtTenSach.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtTenSach.getText().trim() + "%";
				String giadv = txtGiaDonViSach.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtGiaDonViSach.getText().trim() + "%";
				String sotrang = txtSoTrang.getValue().toString().trim().equalsIgnoreCase("0") ? "%"
						: "%" + txtSoTrang.getValue().toString().trim() + "%";
				String tentacgia = TxtTacGia.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + TxtTacGia.getText().trim() + "%";
				String nxb = TxtNhaXuatBan.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + TxtNhaXuatBan.getText().trim() + "%";

				SanPhamDao spdao = new SanPhamDao();
				ArrayList<SanPham> list = spdao.timSanPhamNangCaoSach(masp, tensp, giadv, sotrang, tentacgia, nxb);
				if(list.size() > 0) {
				try {
					GUIChinh_QuanLy.changeLayerPanelDSSP();
				} catch (Exception er) {
					GUIChinh_NhanVien.changeLayerPanelDSSP();
				}
				GUI_DSSanPham.deleteTableDSSanPham();
				GUI_DSSanPham.updateTableTimDSSanPham(list);
				}else {
					JOptionPane.showMessageDialog(panel_3, "Không tìm thấy sách phù hợp");
				}
			}
		});
		btnTimSach.setForeground(new Color(0, 0, 0));
		btnTimSach.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTimSach.setBackground(new Color(255, 255, 255));
		btnTimSach.setBounds(602, 421, 126, 48);
		panel_3.add(btnTimSach);

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(GUI_TimKiemSanPham.class.getResource("/images/timsach.jpg")));
		lblNewLabel_3.setBounds(0, 0, 287, 480);
		panel_3.add(lblNewLabel_3);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(null);
		getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Tìm kiếm sản phẩm");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		panel_1.add(lblNewLabel);

	}
}
