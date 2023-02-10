package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import Others.MyButton;
import connectDB.Database;
import dao.NhanVienDao;
import dao.TaiKhoanDao;
import entity.NhanVien;
import entity.TaiKhoan;

/**
 * Giao diện đăng nhập
 * 
 * @author Võ Phước Lưu
 *
 */
public class GUI_DangNhap {
	protected static JFrame frmngNhp;
	private JTextField txtTaiKhoan;
	private JPasswordField txtMatKhau;
	private int i = 3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					new GUI_DangNhap();
					GUI_DangNhap.frmngNhp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					GUI_DangNhap.frmngNhp.setLocationRelativeTo(null);
					GUI_DangNhap.frmngNhp.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_DangNhap() {
		Database.getInstance().connect();

		initialize();
		txtTaiKhoan.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTaiKhoan.getText().equals("Nhập tài khoản")) {
					txtTaiKhoan.setText("");
					txtTaiKhoan.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTaiKhoan.getText().isEmpty()) {
					txtTaiKhoan.setForeground(Color.GRAY);
					txtTaiKhoan.setText("Nhập tài khoản");
				}
			}
		});

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmngNhp = new JFrame();
		frmngNhp.setResizable(false);
		frmngNhp.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtMatKhau.setText("");
			}
		});
		frmngNhp.setIconImage(Toolkit.getDefaultToolkit().getImage(GUI_DangNhap.class.getResource("/images/icon.png")));
		frmngNhp.setTitle("\u0110\u0103ng Nh\u1EADp");
		frmngNhp.getContentPane().setBackground(new Color(0, 0, 128));
		frmngNhp.setBounds(100, 100, 1000, 503);
		frmngNhp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmngNhp.getContentPane().setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(GUI_DangNhap.class.getResource("/images/pic_login.jpg")));
		lblNewLabel_2.setBounds(0, 0, 632, 466);
		frmngNhp.getContentPane().add(lblNewLabel_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(630, 0, 356, 466);
		frmngNhp.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(36, 116, 290, 177);
		panel_1.add(panel);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("T\u00E0i kho\u1EA3n");
		lblNewLabel.setBounds(0, 0, 290, 44);
		lblNewLabel.setBackground(Color.WHITE);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel.setForeground(new Color(25, 25, 112));

		txtTaiKhoan = new JTextField();
		lblNewLabel.setLabelFor(txtTaiKhoan);
		txtTaiKhoan.setBounds(0, 44, 290, 44);
		panel.add(txtTaiKhoan);
		txtTaiKhoan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTaiKhoan.setText("NV002");
		txtTaiKhoan.setForeground(Color.BLACK);
		txtTaiKhoan.setToolTipText("Nhập tài khoản");
		txtTaiKhoan.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("M\u1EADt kh\u1EA9u");
		lblNewLabel_1.setBounds(0, 88, 290, 44);
		lblNewLabel_1.setBackground(Color.WHITE);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel_1.setForeground(new Color(25, 25, 112));

		txtMatKhau = new JPasswordField("1234");
		lblNewLabel_1.setLabelFor(txtMatKhau);
		txtMatKhau.setBounds(0, 132, 255, 44);
		panel.add(txtMatKhau);
		txtMatKhau.setForeground(new Color(0, 0, 128));
		txtMatKhau.setToolTipText("Nhập mật khẩu");

		JToggleButton btnShowHidePassword = new JToggleButton("");
		btnShowHidePassword.setToolTipText("Hiện/ẩn mật khẩu");
		btnShowHidePassword.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShowHidePassword.setOpaque(true);
		btnShowHidePassword.setBackground(Color.WHITE);
		btnShowHidePassword.setBounds(255, 132, 35, 44);
		panel.add(btnShowHidePassword);
		UIManager.put("ToggleButton.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
		btnShowHidePassword.setSelectedIcon(new ImageIcon(GUI_DangNhap.class.getResource("/images/view.png")));
		btnShowHidePassword.setIcon(new ImageIcon(GUI_DangNhap.class.getResource("/images/hidden.png")));
		btnShowHidePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnShowHidePassword.isSelected()) {
					UIManager.put("ToggleButton.select", Color.WHITE);
					SwingUtilities.updateComponentTreeUI(btnShowHidePassword);
				}
				if (txtMatKhau.getEchoChar() != '\u0000') {
					txtMatKhau.setEchoChar('\u0000');
				} else {
					txtMatKhau.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
				}
			}
		});

		MyButton btnDangNhap = new MyButton("\u0110\u0103ng Nh\u1EADp");
		btnDangNhap.setBounds(36, 303, 140, 50);
		panel_1.add(btnDangNhap);
		btnDangNhap.setBorder(null);
		btnDangNhap.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				btnDangNhap.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnDangNhap.setForeground(Color.black);
			}
		});
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GUIChinh_QuanLy windowQL = null;
				GUIChinh_NhanVien windoNV = null;
				boolean kiemtradangnhap = false;
				while (!kiemtradangnhap) {
					try {
						TaiKhoanDao tkdao = new TaiKhoanDao();
						ArrayList<TaiKhoan> dstk = tkdao.layDsTaiKhoan();

						TaiKhoan tkdn = new TaiKhoan(txtTaiKhoan.getText(), String.valueOf(txtMatKhau.getPassword()));
						System.out.println("Tài khoản đang đăng nhập: " + txtTaiKhoan.getText());

						for (TaiKhoan tk : dstk) {
							if (tk.getTaikhoan().trim().equalsIgnoreCase(tkdn.getTaikhoan().trim())
									&& tk.getMatkhau().trim().equalsIgnoreCase(tkdn.getMatkhau().trim())) {
								NhanVienDao nvdao = new NhanVienDao();
								NhanVien nv = nvdao.timNhanVien(tk.getTaikhoan());
								if (nv.getQuanly().getManhanvien() == null) {
									kiemtradangnhap = true;
									JDialog dlg = new JDialog(windowQL, "Đang tải giao diện...", true);
									dlg.getContentPane().add(BorderLayout.CENTER, new JLabel("Đang tải..."));
									dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
									dlg.setSize(300, 100);
									dlg.setLocationRelativeTo(null);

									Runnable run = new Runnable() {
										@Override
										public void run() {
											dlg.setVisible(true);
										}
									};

									Thread t = new Thread(run) {
										@Override
										public void run() {
											dlg.setVisible(true);
										}
									};

									t.start();

									windowQL = new GUIChinh_QuanLy(nv);
									windowQL.setLocationRelativeTo(null);
									GUI_DangNhap.frmngNhp.setVisible(false);
									dlg.setVisible(false);
									System.out.println("Quản lý đăng nhập thành công!");

								} else {
									kiemtradangnhap = true;
									JDialog dlg = new JDialog(windoNV, "Đang tải giao diện...", true);
									dlg.getContentPane().add(BorderLayout.CENTER, new JLabel("Đang tải..."));
									dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
									dlg.setSize(300, 100);
									dlg.setLocationRelativeTo(null);

									Thread t = new Thread(new Runnable() {
										@Override
										public void run() {
											dlg.setVisible(true);
										}
									}) {
										@Override
										public void start() {
											dlg.getContentPane().add(BorderLayout.CENTER, new JLabel("Đang tải..."));
										}
									};
									t.start();
									windoNV = new GUIChinh_NhanVien(nv);
									windoNV.setLocationRelativeTo(null);
									dlg.setVisible(false);
									GUI_DangNhap.frmngNhp.setVisible(false);
									System.out.println("Nhân viên đăng nhập thành công!");
								}
							}
						}
						if (String.valueOf(txtMatKhau.getPassword()).equals("")) {
							JOptionPane.showMessageDialog(null, "Chưa nhập mật khẩu!");
							kiemtradangnhap = true;
						} else if (!kiemtradangnhap) {
							i--;
							JOptionPane.showMessageDialog(null, "Đăng nhập thất bại! Bạn còn " + i + " lần đăng nhập",
									"Thông báo", JOptionPane.ERROR_MESSAGE);
							System.out.println("Từ chối đăng nhập :" + tkdn.getTaikhoan());
							kiemtradangnhap = true;
						}
						if (i == 0) {
							System.exit(0);
						}

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnDangNhap.setFont(new Font("Segoe UI", Font.BOLD, 15));

		MyButton btnThoat = new MyButton("Thoát");
		btnThoat.setBounds(186, 303, 140, 50);
		panel_1.add(btnThoat);
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnThoat.setBorder(null);
		btnThoat.setFont(new Font("Segoe UI", Font.BOLD, 15));

		JLabel lblngNhp = new JLabel("Đăng Nhập");
		lblngNhp.setLabelFor(panel_1);
		lblngNhp.setBounds(10, 20, 331, 76);
		panel_1.add(lblngNhp);
		lblngNhp.setHorizontalAlignment(SwingConstants.CENTER);
		lblngNhp.setForeground(new Color(0, 0, 128));
		lblngNhp.setFont(new Font("Segoe UI", Font.BOLD, 25));

		JEditorPane txtrNuQunTi = new JEditorPane();
		txtrNuQunTi.setBorder(null);
		txtrNuQunTi.setEditable(false);
		txtrNuQunTi.setContentType("text/html");
		txtrNuQunTi.setText("<html>" + "<head>" + "<style>" + "p{font-family: Segoe UI; font-size:15;color:red}"
				+ "</style>" + "</head>" + "<body>"
				+ "<p><u><i>(*)Nếu quên mật khẩu xin vui lòng gặp quản lý để cấp mật khẩu mới</i></u></p>" + "</body>"
				+ "</html>");// đống này chỉ để tạo chữ nghiên thôi á
		txtrNuQunTi.setBounds(36, 369, 289, 87);
		panel_1.add(txtrNuQunTi);
		btnDangNhap.requestFocus();
		btnDangNhap.requestFocusInWindow();
	}
}
