package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
import dao.TaiKhoanDao;
import entity.NhanVien;
import entity.TaiKhoan;

/**
 * Giao Diện chính của Quản lý
 * 
 * @author Võ Phước Lưu - Phan Võ Trọng - Nguyễn Phạm Công Nhật
 *
 */
public class GUIChinh_QuanLy extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmChinhQuanLy;
	private JPanel pnl_NutChucNang;
	private JPanel pnl_GiaoDienPhu;
	private static JLayeredPane layeredPane;
	private JPanel pnlCNSanPham, pnlCNNhanVien, pnlLapHoaDon;
	private static JPanel pnlLapPhieuDat;
	private static JPanel pnlCNKhachHang;
	private JPanel pnlDSNhanVien;
	private static JPanel pnlDSHoaDon;
	private static JPanel pnlDSPhieuDat;
	private static JPanel pnlDSSanPham, pnlDSKhachHang;
	private JLabel lblDongHo;
	private JPanel pnlThongKeNhanVien;
	private JPanel pnlSideMenu;
	private JPanel pnlMain;
	private JMenu mnNhanVien;
	private JMenuItem mntmCNNhanVien;
	private JMenuItem mntmDSNV;
	private JMenu mnSanPham;
	private JMenuItem mntmCNSanPham;
	private JMenuItem mntmXemDSSP;
	private JMenuItem mntmTimKiemSP;
	private JMenu mnHoaDon;
	private JMenuItem mntmXemDSHD;
	private JMenuItem mntmLapHoaDon;
	private JMenuItem mntmTimKiemHD;
	private JMenu mnPhieuDatTruoc;
	private JMenuItem mntmXemDSPhieuDat;
	private JMenuItem mntmLapPhieuDatTruoc;
	private JMenuItem mntmTimKimPhieuDat;
	private JMenu mnThongKe;
	private JMenuItem mntmThongKeSanPham;
	private JMenuItem mntmThongKeDoanhThu;
	private JMenu mnKhachHang;
	private JMenuItem mntmCNKhachHang;
	private JMenuItem mntmXemDSKhachHang;
	private JMenuItem mntmTimKiemKhachHang;
	private JMenu mnHeThong;
	private JMenuItem mntmThongTinTaiKhoan;
	private JMenuItem mntmDangXuat;
	static NhanVien nv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					GUIChinh_QuanLy window = new GUIChinh_QuanLy();
					window.frmChinhQuanLy.setVisible(true);
					window.frmChinhQuanLy.setLocationRelativeTo(null);

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
	public GUIChinh_QuanLy() throws Exception {
		initialize();
	}

	@SuppressWarnings("static-access")
	public GUIChinh_QuanLy(NhanVien nv) throws Exception {
		this.nv = nv;

		initialize();

	}

	int x = 0;
	int a = 0;
	boolean mo = false;
	int w;
	int h;

	// Cài đặt đóng mở cho menu
	void menuAnimation(JPanel panel) {

		w = frmChinhQuanLy.getWidth();
		h = frmChinhQuanLy.getHeight();
		if (x == 227) {

			mo = false;
			panel.setSize(227, h);
			Thread th = new Thread() {
				@Override
				public void run() {
					try {

						for (int i = 227; i >= 0; i = i - 4) {
							Thread.sleep(1);
							panel.setSize(i, h);

							pnl_GiaoDienPhu.setBounds(i, 45, w - i, h);// di chuyển panel về x + i, từ trái qua phải
							layeredPane.setSize(w + 227, h);

							pnlCNNhanVien.setSize(w + 227, h);
							pnlCNSanPham.setSize(w + 227, h);
							pnlCNNhanVien.setSize(w + 227, h);
							pnlDSHoaDon.setSize(w + 227, h);
							pnlDSSanPham.setSize(w + 227, h);
							pnlDSNhanVien.setSize(w + 227, h);
							pnlLapHoaDon.setSize(w + 227, h);
							pnlMain.setSize(w + 227, h);
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
							layeredPane.setSize(w + 227, h);
							pnlCNNhanVien.setSize(w + 227, h);
							pnlCNSanPham.setSize(w + 227, h);
							pnlCNNhanVien.setSize(w + 227, h);
							pnlDSHoaDon.setSize(w + 227, h);
							pnlDSSanPham.setSize(w + 227, h);
							pnlDSNhanVien.setSize(w + 227, h);
							pnlLapHoaDon.setSize(w + 227, h);
							pnlMain.setSize(w + 227, h);
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
			};
			th.start();
			x = 227;
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
	private JPanel pnlThongKeSanPham;

	public void start() {
		t.scheduleAtFixedRate(task, 1000, 1000);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() throws Exception {

		frmChinhQuanLy = new JFrame();
		frmChinhQuanLy.addWindowListener(new WindowAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void windowClosed(WindowEvent e) {
				new GUI_DangNhap().frmngNhp.setVisible(true);
			}
		});

		frmChinhQuanLy.setBackground(Color.WHITE);
		frmChinhQuanLy.setIconImage(
				Toolkit.getDefaultToolkit().getImage(GUIChinh_QuanLy.class.getResource("/images/icon.png")));
		frmChinhQuanLy.getContentPane().setBackground(new Color(230, 230, 250));
		frmChinhQuanLy.setTitle("Phần mềm Quản lý Nhà Sách TNL");
		w = frmChinhQuanLy.getWidth();
		h = frmChinhQuanLy.getHeight();
		frmChinhQuanLy.setBounds(100, 100, 1611, 830);
		frmChinhQuanLy.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmChinhQuanLy.setVisible(true);
		frmChinhQuanLy.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmChinhQuanLy.getContentPane().setLayout(new BorderLayout(0, 0));

		System.out.println("Khởi tạo giao diện chính...");

		pnlSideMenu = new JPanel();
		pnlSideMenu.setBackground(Color.WHITE);
		pnlSideMenu.setBorder(null);
		frmChinhQuanLy.getContentPane().add(pnlSideMenu, BorderLayout.WEST);
		pnlSideMenu.setVisible(false);
		pnlSideMenu.setLayout(new BorderLayout(0, 0));

		pnl_NutChucNang = new JPanel();
		pnl_NutChucNang.setBackground(new Color(255, 255, 255));
		pnlSideMenu.add(pnl_NutChucNang, BorderLayout.CENTER);
		pnl_NutChucNang.setForeground(Color.WHITE);
		pnl_NutChucNang.setBorder(null);

		pnl_NutChucNang.setLayout(new GridLayout(0, 1, 0, 0));
		System.out.println("Khởi tạo menu");
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 250, 250));
		menuBar.setBorder(null);
		menuBar.setLayout(new GridLayout(8, 1));
		pnl_NutChucNang.add(menuBar);

		mnNhanVien = new JMenu("Nhân Viên");
		mnNhanVien.setOpaque(true);
		mnNhanVien.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnNhanVien.setFocusable(false);
		mnNhanVien.setBackground(Color.WHITE);
		mnNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
		mnNhanVien.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/nhanvien.png")));
		mnNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnNhanVien);

		mntmCNNhanVien = new JMenuItem("New menu item");
		mntmCNNhanVien.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/capnhatnhanvien.png")));
		mntmCNNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmCNNhanVien.setPreferredSize(new Dimension(243, 50));
		mntmCNNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mk;
				try {
					mk = JOptionPane
							.showInputDialog("Vui lòng nhập mật khẩu của tài khoản này để có thể sử dụng chức năng");

					TaiKhoan tkht = new TaiKhoan(nv.getManhanvien(), mk);
					boolean check = false;
					ArrayList<TaiKhoan> dstk = new TaiKhoanDao().layDsTaiKhoan();
					for (int i = 0; i < dstk.size(); i++) {
						if (dstk.get(i).getTaikhoan().trim().equalsIgnoreCase(tkht.getTaikhoan().trim())
								&& dstk.get(i).getMatkhau().trim().equalsIgnoreCase(tkht.getMatkhau().trim())) {
							check = true;
							break;
						} else if (i == (dstk.size() - 1)) {
							check = false;
						}
					}
					if (check) {
						resetFocus();
						mnNhanVien.setBackground(new Color(129, 139, 233));
						changeLayerPanel(layeredPane, pnlCNNhanVien);
					}
					if (mk != null && !check) {
						JOptionPane.showInternalMessageDialog(null, "Mật khẩu không chính xác vui lòng thử lại sau","Thông báo", JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception err) {
					// không tung lỗi
				}
			}

		});
		mntmCNNhanVien.setText("Cập nhật nhân viên");
		mnNhanVien.add(mntmCNNhanVien);

		mntmDSNV = new JMenuItem("Danh sách Nhân viên");
		mntmDSNV.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/danhsachnhanvien.png")));
		mntmDSNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String mk = JOptionPane
							.showInputDialog("Vui lòng nhập mật khẩu của tài khoản này để có thể sử dụng chức năng");
					TaiKhoan tkht = new TaiKhoan(nv.getManhanvien(), mk);
					boolean check = false;
					ArrayList<TaiKhoan> dstk = new TaiKhoanDao().layDsTaiKhoan();
					for (int i = 0; i < dstk.size(); i++) {

						if (dstk.get(i).getTaikhoan().trim().equalsIgnoreCase(tkht.getTaikhoan().trim())
								&& dstk.get(i).getMatkhau().trim().equalsIgnoreCase(tkht.getMatkhau().trim())) {
							check = true;
							break;
						} else if (i == (dstk.size() - 1)) {
							check = false;
						}
					}
					if (check) {
						resetFocus();
						mnNhanVien.setBackground(new Color(129, 139, 233));
						changeLayerPanel(layeredPane, pnlDSNhanVien);
					}
					if (mk != null && !check) {
						JOptionPane.showInternalMessageDialog(null, "Mật khẩu không chính xác vui lòng thử lại sau","Thông báo", JOptionPane.WARNING_MESSAGE);
					}
				} catch (Exception err) {
					// không tung lỗi
				}
			}
		});
		mntmDSNV.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmDSNV.setPreferredSize(new Dimension(243, 50));
		mntmDSNV.setForeground(new Color(0, 0, 0));
		mntmDSNV.setBackground(SystemColor.menu);
		mnNhanVien.add(mntmDSNV);

		mnSanPham = new JMenu("Sản Phẩm");
		mnSanPham.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnSanPham.setFocusable(false);
		mnSanPham.setOpaque(true);
		mnSanPham.setBackground(new Color(255, 255, 255));
		mnSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		mnSanPham.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/sanpham.png")));
		mnSanPham.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnSanPham);

		mntmCNSanPham = new JMenuItem("Cập Nhật Sản Phẩm");
		mntmCNSanPham.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/capnhatsanpham.png")));
		mntmCNSanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnSanPham.setBackground(new Color(129, 139, 233));
				changeLayerPanel(layeredPane, pnlCNSanPham);
			}
		});
		mntmCNSanPham.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmCNSanPham.setPreferredSize(new Dimension(243, 50));
		mnSanPham.add(mntmCNSanPham);

		mntmXemDSSP = new JMenuItem("Xem Danh Sách Sản Phẩm");
		mntmXemDSSP.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/danhsachsanpham.png")));
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
		mntmTimKiemSP.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/timsanpham.png")));
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
		mnHoaDon.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/hoadon.png")));
		mnHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnHoaDon);

		mntmXemDSHD = new JMenuItem("Xem Danh sách Hóa đơn");
		mntmXemDSHD.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/danhsachhoadon.png")));
		mntmXemDSHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnHoaDon.setBackground(new Color(129, 139, 233));
				changeLayerPanel(layeredPane, pnlDSHoaDon);
			}
		});

		mntmLapHoaDon = new JMenuItem("Lập Hóa Đơn");
		mntmLapHoaDon.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/suathongtin.png")));
		mntmLapHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnHoaDon.setBackground(new Color(129, 139, 233));
				changeLayerPanel(layeredPane, pnlLapHoaDon);
			}
		});

		mntmLapHoaDon.setPreferredSize(new Dimension(243, 50));
		mntmLapHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnHoaDon.add(mntmLapHoaDon);
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

		mntmTimKiemHD.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/timkiemhoadon.png")));
		mntmTimKiemHD.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmTimKiemHD.setPreferredSize(new Dimension(243, 50));
		mnHoaDon.add(mntmTimKiemHD);

		mnPhieuDatTruoc = new JMenu("Phiếu Đặt Trước");
		mnPhieuDatTruoc.setBackground(new Color(255, 255, 255));
		mnPhieuDatTruoc.setOpaque(true);
		mnPhieuDatTruoc.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/phieudattruoc.png")));
		mnPhieuDatTruoc.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnPhieuDatTruoc);

		mntmXemDSPhieuDat = new JMenuItem("Xem Danh sách Phiếu Đặt");
		mntmXemDSPhieuDat.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/danhsachhoadon.png")));
		mntmXemDSPhieuDat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnPhieuDatTruoc.setBackground(new Color(129, 139, 233));
				changeLayerPanel(layeredPane, pnlDSPhieuDat);
			}
		});

		mntmLapPhieuDatTruoc = new JMenuItem("Lập Phiếu đặt trước");
		mntmLapPhieuDatTruoc.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/suathongtin.png")));
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
		mntmTimKimPhieuDat.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/timkiemhoadon.png")));
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

		mnThongKe = new JMenu("Báo Cáo");
		mnThongKe.setOpaque(true);
		mnThongKe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnThongKe.setFocusable(false);
		mnThongKe.setBackground(new Color(255, 255, 255));
		mnThongKe.setHorizontalAlignment(SwingConstants.CENTER);
		mnThongKe.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/baocao.png")));
		mnThongKe.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnThongKe);

		mntmThongKeSanPham = new JMenuItem("Thống kê Sản phẩm");
		mntmThongKeSanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnThongKe.setBackground(new Color(129, 139, 233));
				changeLayerPanel(layeredPane, pnlThongKeSanPham);
			}
		});
		mntmThongKeSanPham.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/thongkesanpham.png")));
		mntmThongKeSanPham.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmThongKeSanPham.setPreferredSize(new Dimension(243, 50));
		mnThongKe.add(mntmThongKeSanPham);

		mntmThongKeDoanhThu = new JMenuItem("Thống kê doanh thu");
		mntmThongKeDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnThongKe.setBackground(new Color(129, 139, 233));
				changeLayerPanel(layeredPane, pnlThongKeNhanVien);
			}
		});

		mntmThongKeDoanhThu.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/thongkedoanhthu.png")));
		mntmThongKeDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmThongKeDoanhThu.setPreferredSize(new Dimension(243, 50));
		mnThongKe.add(mntmThongKeDoanhThu);

		mnKhachHang = new JMenu("Khách hàng");
		mnKhachHang.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnKhachHang.setBackground(new Color(255, 255, 255));
		mnKhachHang.setOpaque(true);
		mnKhachHang.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/khachhang.png")));
		mnKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnKhachHang);

		mntmCNKhachHang = new JMenuItem("Cập nhật khách hàng");
		mntmCNKhachHang.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/capnhatnhanvien.png")));
		mntmCNKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnKhachHang.setBackground(new Color(129, 139, 233));
				changeLayerPanel(layeredPane, pnlCNKhachHang);
			}
		});

		mntmCNKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmCNKhachHang.setPreferredSize(new Dimension(243, 50));
		mnKhachHang.add(mntmCNKhachHang);

		mntmXemDSKhachHang = new JMenuItem("Xem danh sách KH");
		mntmXemDSKhachHang.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/danhsachnhanvien.png")));
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
		mntmTimKiemKhachHang.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/timkhachhang.png")));
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

		mnHeThong = new JMenu("Hệ thống");
		mnHeThong.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnHeThong.setOpaque(true);
		mnHeThong.setBackground(new Color(255, 255, 255));
		mnHeThong.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/system.png")));
		mnHeThong.setFont(new Font("Segoe UI", Font.BOLD, 20));
		menuBar.add(mnHeThong);

		mntmThongTinTaiKhoan = new JMenuItem("Thông tin tài khoản");
		mntmThongTinTaiKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetFocus();
				mnHeThong.setBackground(new Color(129, 139, 233));
				new GUI_ThongTinTaiKhoan(nv).setVisible(true);
			}
		});

		mntmThongTinTaiKhoan.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/nhanvien.png")));
		mntmThongTinTaiKhoan.setPreferredSize(new Dimension(243, 50));
		mntmThongTinTaiKhoan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnHeThong.add(mntmThongTinTaiKhoan);

		mntmDangXuat = new JMenuItem("Đăng xuất");
		mntmDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mnHeThong.setBackground(new Color(129, 139, 233));
				nv = null;// đăng xuất hẳn nhân viên ra
				frmChinhQuanLy.dispose();
			}
		});

		mntmDangXuat.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/dangxuat.png")));
		mntmDangXuat.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmDangXuat.setPreferredSize(new Dimension(243, 50));
		mnHeThong.add(mntmDangXuat);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(225, 10));
		panel_1.setBorder(new TitledBorder(null, "Th\u00F4ng tin ng\u01B0\u1EDDi d\u00F9ng:", TitledBorder.TRAILING,
				TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.WHITE);
		menuBar.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/user.png")));
		panel_1.add(lblNewLabel_2);

		JTextArea txtrUsersManvTn = new JTextArea();
		txtrUsersManvTn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtrUsersManvTn.setEditable(false);
		txtrUsersManvTn.setWrapStyleWord(true);
		txtrUsersManvTn.setLineWrap(true);
		panel_1.add(txtrUsersManvTn);
		String cv = nv.getQuanly().getManhanvien() == null ? "Quản lý" : "Nhân viên";
		String str = "Users: " + nv.getManhanvien() + "\r\nChức vụ: " + cv + "\r";
		txtrUsersManvTn.setText(str);
		JPanel pnlTop = new JPanel();
		pnlTop.setBackground(new Color(230, 230, 250));
		pnlTop.setBorder(null);
		frmChinhQuanLy.getContentPane().add(pnlTop, BorderLayout.NORTH);
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
		lblMenu.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/menu.png")));
		lblMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuAnimation(pnlSideMenu);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblMenu.setForeground(Color.black);
				lblMenu.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/listSelected.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblMenu.setForeground(new Color(105, 105, 105));
				lblMenu.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/menu.png")));
			}
		});
		lblMenu.setHorizontalAlignment(SwingConstants.LEFT);
		lblMenu.setBackground(new Color(230, 230, 250));

		Label label = new Label("Nhà Sách TNL");
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

		start();
		lblDongHo = new JLabel("lblDongHo");
		lblDongHo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDongHo.setFont(new Font("Consolas", Font.ITALIC, 20));
		pnlTop.add(lblDongHo);

		pnl_GiaoDienPhu = new JPanel();
		pnl_GiaoDienPhu.setBackground(new Color(239, 245, 255));
		pnl_GiaoDienPhu.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		frmChinhQuanLy.getContentPane().add(pnl_GiaoDienPhu);
		pnl_GiaoDienPhu.setLayout(new BorderLayout(0, 0));

		System.out.println("Khởi tạo các Panel Giao Diện phụ");

		layeredPane = new JLayeredPane();
		pnl_GiaoDienPhu.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		pnlCNNhanVien = new GUI_CNNhanVien();
		pnlCNNhanVien.setOpaque(false);

		pnlCNNhanVien.setBackground(Color.WHITE);
		layeredPane.add(pnlCNNhanVien, "name_428387612793600");

		pnlCNSanPham = new GUI_CNSanPham();
		pnlCNSanPham.setOpaque(false);
		layeredPane.add(pnlCNSanPham, "name_428456852339400");

		pnlThongKeNhanVien = new GUI_ThongKeNhanVien();
		pnlThongKeNhanVien.setOpaque(false);
		layeredPane.add(pnlThongKeNhanVien, "name_428905752252500");

		pnlDSNhanVien = new GUI_DSNhanVien();
		pnlDSNhanVien.setOpaque(false);
		layeredPane.add(pnlDSNhanVien, "name_439643000283900");

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

		pnlDSPhieuDat = new GUI_DSPhieuDat();
		pnlDSPhieuDat.setOpaque(false);
		layeredPane.add(pnlDSPhieuDat, "name_132290958892700");

		pnlCNKhachHang = new GUI_CNKhachHang();
		pnlCNKhachHang.setOpaque(false);
		layeredPane.add(pnlCNKhachHang, "name_132424411345800");

		pnlDSKhachHang = new GUI_DSKhachHang();
		pnlDSKhachHang.setOpaque(false);
		layeredPane.add(pnlDSKhachHang, "name_132949448706600");

		System.out.println("Khởi tạo giao diện phụ hoàn tất");
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
				"Chọn \"Mục\" phía bên trái để sử dụng\r\n \r\nNút \"Nhân Viên\":\r\n+ Để thực hiện cập nhật thông tin nhân viên, xem danh sách.\r\n\r\nNút \"Sản Phẩm\":\r\n+ Để thực hiện tìm, cập nhật thông tin sản phẩm, xem danh sách sản phẩm.\r\n\r\nNút \"Hóa Đơn\":\r\n+ Để thực hiện tìm, lập hóa đơn, xem danh sách hóa đơn.\r\n\r\nNút \"Phiếu đặt trước\":\r\n+ Để thực hiện tìm, cập nhật thông tin phiếu đặt trước, xem danh sách phiếu đặt trước.\r\n \r\nNút \"Thống kê\":\r\n+ Để thực hiện thống kê sản phẩm, doanh thu.\r\n\r\nNút \"Khách hàng\":\r\n+ Để thực hiện cập nhật thông tin khách hàng, xem danh khách hàng.");
		txtaHelp.setLineWrap(true);
		txtaHelp.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtaHelp.setEditable(false);
		txtaHelp.setColumns(5);
		txtaHelp.setBackground(new Color(239, 245, 255));
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/images/pic_quanly.gif")));
		pnlMain.add(lblNewLabel);

		pnlThongKeSanPham = new GUI_ThongKeSanPham();
		layeredPane.add(pnlThongKeSanPham, "name_48786782081900");

		JPanel panel = new JPanel();
		frmChinhQuanLy.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		System.out.println("Tiến hành kết nối database");
		Database.getInstance().connect();
		System.out.println("Kết nối thành công. Khởi tạo giao diện hoàn tất");

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
		mnNhanVien.setBackground(Color.WHITE);
		mnSanPham.setBackground(Color.WHITE);
		mnHoaDon.setBackground(Color.WHITE);
		mnPhieuDatTruoc.setBackground(Color.WHITE);
		mnThongKe.setBackground(Color.WHITE);
		mnKhachHang.setBackground(Color.WHITE);
		mnHeThong.setBackground(Color.WHITE);

	}
}
