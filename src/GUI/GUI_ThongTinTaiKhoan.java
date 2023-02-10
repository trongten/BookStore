package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Others.MyButton;
import dao.TaiKhoanDao;
import entity.NhanVien;
import entity.TaiKhoan;

public class GUI_ThongTinTaiKhoan extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MyButton btnLuu, btnThoat;
	private JTextField txtMatKhauCu;
	private JTextField txtMatKhauMoi;
	private JTextField txtMatKhauMoiNhapLai;
	private static NhanVien nv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try {
						nv = GUIChinh_QuanLy.nv;
					} catch (Exception e) {
						nv = GUIChinh_NhanVien.nv;
					}
					GUI_ThongTinTaiKhoan frame = new GUI_ThongTinTaiKhoan(nv);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("static-access")
	public GUI_ThongTinTaiKhoan(NhanVien nv) {
		this.nv = nv;
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI_ThongTinTaiKhoan.class.getResource("/images/user.png")));
		setResizable(false);
		setTitle("Thông tin tài khoản");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 645, 484);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(GUI_ThongTinTaiKhoan.class.getResource("/images/userBig.png")));
		lblNewLabel.setBounds(25, 55, 158, 156);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_15 = new JLabel("Thông tin tài khoản:");
		lblNewLabel_15.setForeground(new Color(25, 25, 112));
		lblNewLabel_15.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_15.setBounds(0, 0, 543, 59);
		contentPane.add(lblNewLabel_15);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u0110\u1ED5i m\u1EADt kh\u1EA9u", TitledBorder.TRAILING,
				TitledBorder.TOP, null, null));
		panel_1.setOpaque(false);
		panel_1.setBounds(12, 224, 195, 156);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 3));

		JLabel lblNewLabel_16 = new JLabel("Mật khẩu cũ:");
		lblNewLabel_16.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_1.add(lblNewLabel_16);

		txtMatKhauCu = new JTextField();

		txtMatKhauCu.setText("");
		panel_1.add(txtMatKhauCu);
		txtMatKhauCu.setColumns(15);

		JLabel lblNewLabel_17 = new JLabel("Mật khẩu mới:");
		lblNewLabel_17.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_1.add(lblNewLabel_17);

		txtMatKhauMoi = new JTextField();
		txtMatKhauMoi.setText("");
		panel_1.add(txtMatKhauMoi);
		txtMatKhauMoi.setColumns(15);

		JLabel lblNewLabel_18 = new JLabel("Nhập lại mật khẩu mới:");
		lblNewLabel_18.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_1.add(lblNewLabel_18);

		txtMatKhauMoiNhapLai = new JTextField();
		panel_1.add(txtMatKhauMoiNhapLai);
		txtMatKhauMoiNhapLai.setColumns(15);

		btnLuu = new MyButton("Lưu");
		btnLuu.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnLuu.setForeground(new Color(255, 255, 255));
		btnLuu.setColor(new Color(60, 179, 113));
		btnLuu.setColorClick(new Color(0, 100, 0));
		btnLuu.setBackground(new Color(60, 179, 113));
		btnLuu.setColorOver(new Color(0, 128, 0));
		btnLuu.setBorderColor(new Color(0, 128, 0));
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<TaiKhoan> dstk = new TaiKhoanDao().layDsTaiKhoan();
				TaiKhoan tkht = new TaiKhoan(nv.getManhanvien(), txtMatKhauCu.getText().trim());
				TaiKhoan tkMatKhauMoi = new TaiKhoan(nv.getManhanvien().trim(), txtMatKhauMoi.getText().trim());
				for (int i = 0; i < dstk.size(); i++) {
					if (dstk.get(i).getTaikhoan().trim().equalsIgnoreCase(tkht.getTaikhoan().trim())
							&& dstk.get(i).getMatkhau().trim().equalsIgnoreCase(tkht.getMatkhau().trim())) {
						if (!txtMatKhauMoi.getText().trim().equals(txtMatKhauMoiNhapLai.getText())) {
							JOptionPane.showMessageDialog(null,
									"Mật khẩu mới và mật khẩu mới nhập lại không trùng khớp!", "Thông báo",
									JOptionPane.ERROR_MESSAGE);
							txtMatKhauMoi.requestFocus();
							txtMatKhauMoi.requestFocusInWindow();
						} else {
							boolean kq = new TaiKhoanDao().suaMatKhau(tkMatKhauMoi);
							System.out.println("Tiến hành sửa mật khẩu...");
							if (kq) {
								System.out.println("Thành công!");
								JOptionPane.showMessageDialog(null, "Sửa mật khẩu thành công");

							} else {
								System.out.println("Thất bại");
								JOptionPane.showMessageDialog(null, "Sửa mật khẩu thất bại rồi! checkcode đi bạn ơi");
							}
						}
						break;
					} else if(i==(dstk.size()-1)){
						JOptionPane.showMessageDialog(null, "Mật khẩu cũ không chính xác! Vui lòng kiểm tra lại");
						txtMatKhauCu.requestFocus();
						txtMatKhauCu.requestFocusInWindow();
					}

				}

			}
		});
		btnLuu.setBounds(409, 392, 99, 43);
		contentPane.add(btnLuu);

		btnThoat = new MyButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnThoat.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btnThoat.setBounds(520, 392, 99, 43);
		contentPane.add(btnThoat);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(230, 71, 389, 309);
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel_19 = new JLabel("Tài khoản:");
		lblNewLabel_19.setFont(new Font("Segoe UI", Font.BOLD, 15));
		panel.add(lblNewLabel_19);

		JLabel txtTaiKhoan = new JLabel(nv.getManhanvien());
		txtTaiKhoan.setFont(new Font("Segoe UI", Font.BOLD, 15));
		panel.add(txtTaiKhoan);

		JLabel lblNewLabel_1 = new JLabel("Tên nhân viên:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(lblNewLabel_1);

		JLabel txtHoTen = new JLabel(nv.getHoten());
		txtHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(txtHoTen);

		JLabel lblNewLabel_3 = new JLabel("Mã nhân viên:");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(lblNewLabel_3);

		JLabel txtMa = new JLabel(nv.getManhanvien());
		txtMa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(txtMa);

		JLabel lblNewLabel_5 = new JLabel("Địa chỉ:");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(lblNewLabel_5);

		JLabel txtDiaChi = new JLabel(nv.getDiachi());
		txtDiaChi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(txtDiaChi);

		JLabel lblNewLabel_13 = new JLabel("Giới tính:");
		lblNewLabel_13.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(lblNewLabel_13);

		JLabel txtGioiTinh = new JLabel(nv.isGioitinh() ? "Nam" : "Nữ");
		txtGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(txtGioiTinh);

		JLabel lblNewLabel_7 = new JLabel("Ngày sinh:");
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(lblNewLabel_7);

		JLabel txtNgaySinh = new JLabel(nv.getNgaysinh().toString());
		txtNgaySinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(txtNgaySinh);

		JLabel lblNewLabel_9 = new JLabel("Số chứng minh:");
		lblNewLabel_9.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(lblNewLabel_9);

		JLabel txtSoChungMinh = new JLabel(nv.getCmnd());
		txtSoChungMinh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(txtSoChungMinh);

		JLabel lblNewLabel_11 = new JLabel("Ca:");
		lblNewLabel_11.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(lblNewLabel_11);

		JLabel txtCa = new JLabel(String.valueOf(nv.getCa()));
		txtCa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(txtCa);
	}
}
