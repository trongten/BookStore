package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import connectDB.Database;
import entity.NhanVien;

/**
 * Giao diện chính nhân viên
 * @author Võ Phước Lưu - Phan Võ Trọng - Nguyễn Phạm Công Nhật
 *
 */
public class GUIChinh_NhanVien extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmChinhNhanVien;
	private JPanel pnl_NutChucNang;
	private JPanel pnl_GiaoDienPhu;
	private static JLayeredPane layeredPane;
	private JPanel pnlLapHoaDon;
	private static JPanel pnlLapPhieuDat;
	private static JPanel pnlCNKhachHang;
	private static JPanel pnlDSHoaDon;
	private static JPanel pnlDSPhieuDat;
	private static JPanel pnlDSSanPham, pnlDSKhachHang;
	private JLabel lblDongHo;
	private JPanel pnlThongKeNhanVien;
	private JPanel pnlSideMenu;
	private JPanel pnlMain;
	static NhanVien nv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIChinh_NhanVien window = new GUIChinh_NhanVien();
					window.frmChinhNhanVien.setVisible(true);
					window.frmChinhNhanVien.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public GUIChinh_NhanVien() throws Exception {
		initialize();
	}

	@SuppressWarnings("static-access")
	public GUIChinh_NhanVien(NhanVien nv) throws Exception {
		this.nv = nv;
		initialize();
	}

	int x = 0;
	int a = 0;
	boolean mo = false;

	int w;
	int h;

	/**
	 * Cài đặt side menu
	 * 
	 */
	void menuAnimation(JPanel panel) {

		w = frmChinhNhanVien.getWidth();
		h = frmChinhNhanVien.getHeight();
		if (x == 210) {

			mo = false;
			panel.setSize(210, h);
			Thread th = new Thread() {
				@Override
				public void run() {
					try {

						for (int i = 210; i >= 0; i = i - 4) {
							Thread.sleep(1);
							panel.setSize(i, h);

							pnl_GiaoDienPhu.setBounds(i, 45, w - i, h);// di chuyển panel về x + i, từ trái qua phải
							layeredPane.setSize(w + 210, h);

							pnlDSHoaDon.setSize(w + 210, h);
							pnlDSSanPham.setSize(w + 210, h);
							pnlLapHoaDon.setSize(w + 210, h);
							pnlMain.setSize(w + 210, h);
							a++;
						}
						pnlSideMenu.setVisible(false);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
			};
			th.start();
			x = 0;
		} else if (x == 0) {
			mo = true;
			panel.setSize(x, h);
			Thread th = new Thread() {
				@Override
				public void run() {
					pnlSideMenu.setVisible(true);
					try {
						for (int i = 0; i <= x; i = i + 4) {
							Thread.sleep(1);
							panel.setSize(i, h);
							pnl_GiaoDienPhu.setBounds(i, 45, w - i, h);// di chuyển panel về x + i, từ phải qua trái
							layeredPane.setSize(w + 210, h);
							pnlDSHoaDon.setSize(w + 210, h);
							pnlDSSanPham.setSize(w + 210, h);
							pnlLapHoaDon.setSize(w + 210, h);
							pnlMain.setSize(w + 210, h);
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
			};
			th.start();
			x = 210;
		}
	}

	// Tạo đồng hồ
	Timer t = new Timer();
	TimerTask task = new TimerTask() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
		DateTimeFormatter df2 = DateTimeFormatter.ofPattern("HH mm ss dd/MM/yyyy");

		public void run() {
			if (LocalDateTime.now().getSecond() % 2 == 0)
				lblDongHo.setText(LocalDateTime.now().format(df) + "    ");
			else
				lblDongHo.setText(LocalDateTime.now().format(df2) + "    ");
		}
	};
	private JMenuItem mntmThongKeDoanhThu;
	private JMenuItem mntmThongKeSanPham;
	private JMenu mnThongKe;
	private JMenuItem mntmTimKiemKhachHang;
	private JMenuItem mntmXemDSKhachHang;
	private JMenuItem mntmCNKH;
	private JPanel pnlThongKeSanPham;
	private JMenu mnSanPham;
	private JMenu mnHoaDon;
	private JMenu mnPhieuDatTruoc;
	private JMenu mnKhachHang;
	private JMenu mnHeThong;
	private JMenuItem mntmThongTinTaiKhoan;
	private JMenuItem mntmLapPhieuDatTruoc;
	private JMenuItem mntmXemDSPhieuDat;
	private JMenuItem mntmTimKiemHD;
	private JMenuItem mntmCapNhatHoaDon;
	private JMenuItem mntmXemDSHD;
	private JMenuItem mntmXemDSSP;
	private JMenuItem mntmTimKimPhieuDat;
	private JMenuItem mntmTimKiemSP;

	public void start() {
		t.scheduleAtFixedRate(task, 1000, 1000);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() throws Exception {
		frmChinhNhanVien = new JFrame();
		frmChinhNhanVien.addWindowListener(new WindowAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void windowClosed(WindowEvent e) {
				new GUI_DangNhap().frmngNhp.setVisible(true);
			}
		});

		frmChinhNhanVien.setBackground(Color.WHITE);
		frmChinhNhanVien.setIconImage(
				Toolkit.getDefaultToolkit().getImage(GUIChinh_NhanVien.class.getResource("/images/icon.png")));
		frmChinhNhanVien.getContentPane().setBackground(new Color(230, 230, 250));
		frmChinhNhanVien.setTitle("Phần mềm Quản lý Nhà Sách TNL");
		w = frmChinhNhanVien.getWidth();
		h = frmChinhNhanVien.getHeight();
		frmChinhNhanVien.setBounds(100, 100, 1611, 830);
		frmChinhNhanVien.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmChinhNhanVien.setVisible(true);

		frmChinhNhanVien.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmChinhNhanVien.getContentPane().setLayout(new BorderLayout(0, 0));
		pnlSideMenu = new JPanel();
		pnlSideMenu.setBackground(Color.WHITE);
		pnlSideMenu.setBorder(null);

		// **
		frmChinhNhanVien.getContentPane().add(pnlSideMenu, BorderLayout.WEST);
		pnlSideMenu.setVisible(false);
		pnlSideMenu.setLayout(new BorderLayout(0, 0));

		pnl_NutChucNang = new JPanel();
		pnl_NutChucNang.setBackground(new Color(255, 255, 255));
		pnlSideMenu.add(pnl_NutChucNang, BorderLayout.CENTER);
		pnl_NutChucNang.setForeground(Color.WHITE);
		pnl_NutChucNang.setBorder(null);

		pnl_NutChucNang.setLayout(new GridLayout(0, 1, 0, 0));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 250, 250));
		menuBar.setBorder(null);
		menuBar.setLayout(new GridLayout(8, 1));
		pnl_NutChucNang.add(menuBar);

		mnSanPham = new JMenu("Sản Phẩm");
		mnSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnSanPham.setFocusable(false);
		mnSanPham.setOpaque(true);
		mnSanPham.setBackground(new Color(255, 255, 255));
		mnSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		mnSanPham.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/sanpham.png")));
		mnSanPham.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnSanPham);

		mntmXemDSSP = new JMenuItem("Xem Danh Sách Sản Phẩm");
		mntmXemDSSP.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/danhsachsanpham.png")));
		mntmXemDSSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnSanPham.setBackground(new Color(129, 139, 233));
				changeLayerPanel(layeredPane, pnlDSSanPham);
			}
		});
		mntmXemDSSP.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmXemDSSP.setPreferredSize(new Dimension(243, 50));
		mnSanPham.add(mntmXemDSSP);

		mntmTimKiemSP = new JMenuItem("Tra sản phẩm");
		mntmTimKiemSP.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/timsanpham.png")));
		mntmTimKiemSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnSanPham.setBackground(new Color(129, 139, 233));
				new GUI_TimKiemSanPham().setVisible(true);
			}
		});
		mntmTimKiemSP.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmTimKiemSP.setPreferredSize(new Dimension(243, 50));
		mnSanPham.add(mntmTimKiemSP);

		mnHoaDon = new JMenu("Hóa Đơn");
		mnHoaDon.setOpaque(true);
		mnHoaDon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnHoaDon.setFocusable(false);
		mnHoaDon.setPreferredSize(new Dimension(209, 21));
		mnHoaDon.setBackground(Color.WHITE);
		mnHoaDon.setHorizontalAlignment(SwingConstants.CENTER);
		mnHoaDon.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/hoadon.png")));
		mnHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnHoaDon);

		mntmXemDSHD = new JMenuItem("Xem Danh sách Hóa đơn");
		mntmXemDSHD.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/danhsachhoadon.png")));
		mntmXemDSHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnHoaDon.setBackground(new Color(129, 139, 233));
				changeLayerPanel(layeredPane, pnlDSHoaDon);
			}
		});

		mntmCapNhatHoaDon = new JMenuItem("Lập Hóa Đơn");
		mntmCapNhatHoaDon.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/suathongtin.png")));
		mntmCapNhatHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnHoaDon.setBackground(new Color(129, 139, 233));
				changeLayerPanel(layeredPane, pnlLapHoaDon);
			}
		});
		mntmCapNhatHoaDon.setPreferredSize(new Dimension(243, 50));
		mntmCapNhatHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnHoaDon.add(mntmCapNhatHoaDon);
		mntmXemDSHD.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmXemDSHD.setPreferredSize(new Dimension(243, 50));
		mnHoaDon.add(mntmXemDSHD);

		mntmTimKiemHD = new JMenuItem("Tra Hóa đơn");
		mntmTimKiemHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnHoaDon.setBackground(new Color(129, 139, 233));
				new GUI_TimKiemHoaDon().setVisible(true);
			}
		});
		mntmTimKiemHD.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/timkiemhoadon.png")));
		mntmTimKiemHD.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmTimKiemHD.setPreferredSize(new Dimension(243, 50));
		mnHoaDon.add(mntmTimKiemHD);

		mnPhieuDatTruoc = new JMenu("Phiếu Đặt Trước");
		mnPhieuDatTruoc.setBackground(new Color(255, 255, 255));
		mnPhieuDatTruoc.setOpaque(true);
		mnPhieuDatTruoc.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/phieudattruoc.png")));
		mnPhieuDatTruoc.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnPhieuDatTruoc);

		mntmXemDSPhieuDat = new JMenuItem("Xem Danh sách Phiếu Đặt");
		mntmXemDSPhieuDat.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/danhsachhoadon.png")));
		mntmXemDSPhieuDat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnPhieuDatTruoc.setBackground(new Color(129, 139, 233));
				changeLayerPanel(layeredPane, pnlDSPhieuDat);
			}
		});

		mntmLapPhieuDatTruoc = new JMenuItem("Lập Phiếu đặt trước");
		mntmLapPhieuDatTruoc.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/suathongtin.png")));
		mntmLapPhieuDatTruoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnPhieuDatTruoc.setBackground(new Color(129, 139, 233));
				changeLayerPanel(layeredPane, pnlLapPhieuDat);
			}
		});
		mntmLapPhieuDatTruoc.setPreferredSize(new Dimension(243, 50));
		mntmLapPhieuDatTruoc.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnPhieuDatTruoc.add(mntmLapPhieuDatTruoc);
		mntmXemDSPhieuDat.setPreferredSize(new Dimension(243, 50));
		mntmXemDSPhieuDat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnPhieuDatTruoc.add(mntmXemDSPhieuDat);

		mntmTimKimPhieuDat = new JMenuItem("Tra Phiếu đặt");
		mntmTimKimPhieuDat.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/timkiemhoadon.png")));
		mntmTimKimPhieuDat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnPhieuDatTruoc.setBackground(new Color(129, 139, 233));
				new GUI_TimKiemPhieuDat().setVisible(true);
			}
		});
		mntmTimKimPhieuDat.setPreferredSize(new Dimension(243, 50));
		mntmTimKimPhieuDat.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnPhieuDatTruoc.add(mntmTimKimPhieuDat);

		mnKhachHang = new JMenu("Khách hàng");
		mnKhachHang.setBackground(new Color(255, 255, 255));
		mnKhachHang.setOpaque(true);
		mnKhachHang.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/khachhang.png")));
		mnKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnKhachHang);

		mntmCNKH = new JMenuItem("Cập nhật khách hàng");
		mntmCNKH.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/capnhatnhanvien.png")));
		mntmCNKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnKhachHang.setBackground(new Color(129, 139, 233));
				changeLayerPanel(layeredPane, pnlCNKhachHang);
			}
		});
		mntmCNKH.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmCNKH.setPreferredSize(new Dimension(243, 50));
		mnKhachHang.add(mntmCNKH);

		mntmXemDSKhachHang = new JMenuItem("Xem danh sách KH");
		mntmXemDSKhachHang.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/danhsachnhanvien.png")));
		mntmXemDSKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnKhachHang.setBackground(new Color(129, 139, 233));
				changeLayerPanel(layeredPane, pnlDSKhachHang);
			}
		});
		mntmXemDSKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmXemDSKhachHang.setPreferredSize(new Dimension(243, 50));
		mnKhachHang.add(mntmXemDSKhachHang);

		mntmTimKiemKhachHang = new JMenuItem("Tra Khách hàng");
		mntmTimKiemKhachHang.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/timkhachhang.png")));
		mntmTimKiemKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnKhachHang.setBackground(new Color(129, 139, 233));
				new GUI_TimKiemKhachHang().setVisible(true);
			}
		});
		mntmTimKiemKhachHang.setPreferredSize(new Dimension(243, 50));
		mntmTimKiemKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnKhachHang.add(mntmTimKiemKhachHang);

		mnThongKe = new JMenu("Báo Cáo");
		mnThongKe.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/baocao.png")));
		mnThongKe.setOpaque(true);
		mnThongKe.setHorizontalAlignment(SwingConstants.CENTER);
		mnThongKe.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnThongKe.setFocusable(false);
		mnThongKe.setBackground(Color.WHITE);
		menuBar.add(mnThongKe);

		mntmThongKeSanPham = new JMenuItem("Thống kê Sản phẩm");
		mntmThongKeSanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnThongKe.setBackground(new Color(129, 139, 233));
				changeLayerPanel(layeredPane, pnlThongKeSanPham);
			}
		});
		mntmThongKeSanPham.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/thongkesanpham.png")));
		mntmThongKeSanPham.setPreferredSize(new Dimension(243, 50));
		mntmThongKeSanPham.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnThongKe.add(mntmThongKeSanPham);

		mntmThongKeDoanhThu = new JMenuItem("Thống kê doanh thu");
		mntmThongKeDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnThongKe.setBackground(new Color(129, 139, 233));
				changeLayerPanel(layeredPane, pnlThongKeNhanVien);
			}

		});
		mntmThongKeDoanhThu.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/thongkedoanhthu.png")));
		mntmThongKeDoanhThu.setPreferredSize(new Dimension(243, 50));
		mntmThongKeDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnThongKe.add(mntmThongKeDoanhThu);

		mnHeThong = new JMenu("Hệ thống");
		mnHeThong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnHeThong.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/system.png")));
		mnHeThong.setOpaque(true);
		mnHeThong.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnHeThong.setBackground(Color.WHITE);
		menuBar.add(mnHeThong);

		mntmThongTinTaiKhoan = new JMenuItem("Thông tin tài khoản");
		mntmThongTinTaiKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnHeThong.setBackground(new Color(129, 139, 233));
				new GUI_ThongTinTaiKhoan(nv).setVisible(true);
			}
		});
		mntmThongTinTaiKhoan.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/nhanvien.png")));
		mntmThongTinTaiKhoan.setPreferredSize(new Dimension(243, 50));
		mntmThongTinTaiKhoan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnHeThong.add(mntmThongTinTaiKhoan);

		JMenuItem mntmDangXuat = new JMenuItem("Đăng xuất");
		mntmDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mnHeThong.setBackground(new Color(129, 139, 233));
				nv = null;//đăng xuất hẳn nhân viên ra
				frmChinhNhanVien.dispose();
			}
		});
		mntmDangXuat.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/dangxuat.png")));
		mntmDangXuat.setPreferredSize(new Dimension(243, 50));
		mntmDangXuat.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnHeThong.add(mntmDangXuat);

		Component verticalStrut = Box.createVerticalStrut(184);
		menuBar.add(verticalStrut);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(225, 10));
		panel_1.setBorder(new TitledBorder(null, "Th\u00F4ng tin ng\u01B0\u1EDDi d\u00F9ng:", TitledBorder.TRAILING,

				TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.WHITE);
		menuBar.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/user.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2);

		JTextArea txtrUsersManvTn = new JTextArea();
		txtrUsersManvTn.setWrapStyleWord(true);
		String cv = nv.getQuanly().getManhanvien() == null ? "Quản lý" : "Nhân viên";
		String str = "Users: " + nv.getManhanvien() + "\r\nChức vụ: " + cv + "\r";
		txtrUsersManvTn.setText(str);
		txtrUsersManvTn.setLineWrap(true);
		txtrUsersManvTn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtrUsersManvTn.setEditable(false);
		panel_1.add(txtrUsersManvTn);
		JPanel pnlTop = new JPanel();
		pnlTop.setBackground(new Color(230, 230, 250));
		pnlTop.setBorder(null);
		frmChinhNhanVien.getContentPane().add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(new GridLayout(0, 3, 0, 0));

		JPanel pnlMuc = new JPanel();
		pnlMuc.setOpaque(false);
		pnlTop.add(pnlMuc);
		pnlMuc.setLayout(new GridLayout(0, 4, 0, 0));

		JLabel lblMenu = new JLabel("Mục");
		pnlMuc.add(lblMenu);
		lblMenu.setOpaque(true);
		lblMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMenu.setToolTipText("Mục các chức năng");
		lblMenu.setForeground(new Color(105, 105, 105));
		lblMenu.setFont(new Font("Dialog", Font.BOLD, 20));
		lblMenu.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/menu.png")));
		lblMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuAnimation(pnlSideMenu);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblMenu.setForeground(Color.black);
				lblMenu.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/listSelected.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblMenu.setForeground(new Color(105, 105, 105));
				lblMenu.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/menu.png")));
			}
		});
		lblMenu.setHorizontalAlignment(SwingConstants.LEFT);
		lblMenu.setBackground(new Color(230, 230, 250));

		Label label = new Label("Nhà Sách TNL");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetFocus();
				changeLayerPanel(layeredPane, pnlMain);
			}
		});
		label.setForeground(new Color(25, 25, 112));
		label.setFont(new Font("Dialog", Font.BOLD, 30));
		label.setAlignment(Label.CENTER);
		pnlTop.add(label);

		start();// chạy đồng hồ
		lblDongHo = new JLabel("lblDongHo");
		lblDongHo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDongHo.setFont(new Font("Consolas", Font.ITALIC, 20));
		pnlTop.add(lblDongHo);

		pnl_GiaoDienPhu = new JPanel();
		pnl_GiaoDienPhu.setBackground(new Color(239, 245, 255));
		pnl_GiaoDienPhu.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmChinhNhanVien.getContentPane().add(pnl_GiaoDienPhu);
		pnl_GiaoDienPhu.setLayout(new BorderLayout(0, 0));

		layeredPane = new JLayeredPane();
		pnl_GiaoDienPhu.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		pnlThongKeNhanVien = new GUI_ThongKeNhanVien();
		pnlThongKeNhanVien.setOpaque(false);
		layeredPane.add(pnlThongKeNhanVien, "name_428905752252500");

		pnlThongKeSanPham = new GUI_ThongKeSanPham();
		pnlThongKeSanPham.setOpaque(false);
		layeredPane.add(pnlThongKeSanPham, "name_88345048513600");

		pnlDSSanPham = new GUI_DSSanPham();
		pnlDSSanPham.setOpaque(false);
		layeredPane.add(pnlDSSanPham, "name_439645861831100");

		pnlDSHoaDon = new GUI_DSHoaDon();
		pnlDSHoaDon.setOpaque(false);
		layeredPane.add(pnlDSHoaDon, "name_439647990475100");

		pnlLapHoaDon = new GUI_LapHoaDon(nv);
		pnlLapHoaDon.setOpaque(false);
		layeredPane.add(pnlLapHoaDon, "name_511681171475200");

		pnlMain = new JPanel();
		pnlMain.setOpaque(false);

		pnlLapPhieuDat = new GUI_LapPhieuDat(nv);
		pnlLapPhieuDat.setOpaque(false);
		layeredPane.add(pnlLapPhieuDat, "name_131934326565700");

		layeredPane.removeAll();
		layeredPane.add(pnlMain, "name_29930844268500");
		pnlMain.setLayout(new GridLayout(0, 2, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBackground(new Color(211, 211, 211));
		pnlMain.add(scrollPane);

		JTextArea txtaHelp = new JTextArea();
		scrollPane.setViewportView(txtaHelp);
		txtaHelp.setWrapStyleWord(true);
		txtaHelp.setText(
				"Chọn \"Mục\" phía bên trái để sử dụng\r\n\r\nNút \"Sản Phẩm\":\r\n+ Để thực hiện tìm, xem danh sách sản phẩm.\r\n\r\nNút \"Hóa Đơn\":\r\n+ Để thực hiện tìm, lập hóa đơn, xem danh sách hóa đơn.\r\n\r\nNút \"Phiếu đặt trước\":\r\n+ Để thực hiện tìm, cập nhật thông tin phiếu đặt trước, xem danh sách phiếu đặt trước.\r\n \r\nNút \"Thống kê\":\r\n+ Để thực hiện thống kê sản phẩm, doanh thu.\r\n\r\nNút \"Khách hàng\":\r\n+ Để thực hiện cập nhật thông tin khách hàng, xem danh khách hàng.");
		txtaHelp.setLineWrap(true);
		txtaHelp.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtaHelp.setEditable(false);
		txtaHelp.setColumns(5);
		txtaHelp.setBackground(new Color(239, 245, 255));
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GUIChinh_NhanVien.class.getResource("/images/main-nhanvien.gif")));
		pnlMain.add(lblNewLabel);

		pnlDSPhieuDat = new GUI_DSPhieuDat();
		pnlDSPhieuDat.setOpaque(false);
		layeredPane.add(pnlDSPhieuDat, "name_132290958892700");

		pnlCNKhachHang = new GUI_CNKhachHang();
		pnlCNKhachHang.setOpaque(false);
		layeredPane.add(pnlCNKhachHang, "name_132424411345800");

		pnlDSKhachHang = new GUI_DSKhachHang();
		pnlDSKhachHang.setOpaque(false);
		layeredPane.add(pnlDSKhachHang, "name_132949448706600");

		JPanel panel = new JPanel();
		frmChinhNhanVien.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		Database.getInstance().connect();
	}

	void changeLayerPanel(JLayeredPane layeredPane, JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public static void changeLayerPanelDSSP() {
		layeredPane.removeAll();
		layeredPane.add(pnlDSSanPham);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public static void changeLayerPanelDSHD() {
		layeredPane.removeAll();
		layeredPane.add(pnlDSHoaDon);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public static void changeLayerPanelDSKH() {
		layeredPane.removeAll();
		layeredPane.add(pnlDSKhachHang);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public static void changeLayerPanelCNKH() {
		layeredPane.removeAll();
		layeredPane.add(pnlCNKhachHang);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public static void changeLayerPanelLapPhieuDat() {
		layeredPane.removeAll();
		layeredPane.add(pnlLapPhieuDat);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public static void changeLayerPanelDSPD() {
		layeredPane.removeAll();
		layeredPane.add(pnlDSPhieuDat);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	public void resetFocus() {
		mnSanPham.setBackground(Color.WHITE);
		mnHoaDon.setBackground(Color.WHITE);
		mnPhieuDatTruoc.setBackground(Color.WHITE);
		mnThongKe.setBackground(Color.WHITE);
		mnKhachHang.setBackground(Color.WHITE);
		mnHeThong.setBackground(Color.WHITE);
	}
}
