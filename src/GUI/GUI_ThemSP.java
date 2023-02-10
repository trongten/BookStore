package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Others.MyButton;
import dao.SanPhamDao;
import entity.Loai;
import entity.SanPham;

public class GUI_ThemSP extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSpinner txtSoLuongSach;
	private JSpinner txtSoLuong;
	private JTextField txtTenSanPham;
	private JTextField txtNhaCungCap;
	private JTextField txtXuatXu;
	private JTextField txtChatLieu;
	private JSpinner txtGiaNhap;
	private JTextField txtTenSach;
	private JTextField txtNhaXuatBan;
	private JTextField txtTenTacGia;
	private JTextField txtSoTrang;
	private JTextField txtGiaNhapSach;
	private JLabel lblAnhSach, lblAnhSanPham;
	private JFileChooser chooser;
	private JFileChooser chooserS;
	int a = 1;
	@SuppressWarnings("unused")
	private String anhsp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_ThemSP window = new GUI_ThemSP();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_ThemSP() {
		setLocationRelativeTo(null);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("removal")
	private void initialize() {
		// setSize(900,600);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 899, 517);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);

		JTabbedPane tabThemSP = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabThemSP);

		JPanel pnlVPP = new JPanel();
		pnlVPP.setBackground(Color.WHITE);
		pnlVPP.setToolTipText("");
		tabThemSP.addTab("V\u0103n ph\u00F2ng ph\u1EA9m", null, pnlVPP, null);
		pnlVPP.setLayout(null);

		SanPhamDao spdao = new SanPhamDao();
		ArrayList<SanPham> spcuoi = spdao.layDsSanPham();
		String masanpham = spcuoi.get(spcuoi.size() - 1).getMasanpham()
				.substring(spcuoi.get(spcuoi.size() - 1).getMasanpham().length() - 3);
		int stt = Integer.parseInt(masanpham) + 1;
		System.out.println("số lượng phần tử trong danh sách: " + masanpham + "\n mã cần ra: " + stt);

		MyButton btnThemSP = new MyButton("Th\u00EAm");
		btnThemSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					txtGiaNhap.commitEdit();
				} catch (ParseException e1) {
					JComponent editor = txtGiaNhap.getEditor();
					if (editor instanceof DefaultEditor) {
						((DefaultEditor) editor).getTextField().setValue(txtGiaNhap.getValue());
					}
				}

				SanPhamDao spdao = new SanPhamDao();
				int i = stt;
				if (kiemTraNhap()) {
					String masp = "SP";
					if (i < 10) {
						masp = masp + "00" + i;
					}
					if (i >= 10 && i < 100) {
						masp = masp + "0" + i;
					}
					if (i >= 100 && i < 1000) {
						masp = masp + i;
					}
					String tensp = txtTenSanPham.getText();
					String nhacc = txtNhaCungCap.getText();
					String xuatxu = txtXuatXu.getText();
					String chatlieu = txtChatLieu.getText();
					int soluong = Integer.parseInt(txtSoLuong.getValue().toString());
					double gianhap = Double.parseDouble(txtGiaNhap.getValue().toString());
					String link = chooser.getSelectedFile().getName();
					String anhsp = "images\\sanpham\\" + link;

					SanPham sp = new SanPham(masp, tensp, soluong, gianhap, anhsp, new Loai("VANP"));
					sp.setNhacungcap(nhacc);
					sp.setChatlieu(chatlieu);
					sp.setXuatxu(xuatxu);

					spdao.themVanPhongPham(sp);

					GUI_CNSanPham.deleteTableVPP();
					GUI_CNSanPham.deleteTableSach();
					GUI_CNSanPham.updateTableVPP();
					GUI_CNSanPham.updateTableSach();

					GUI_DSSanPham.deleteTableDSSanPham();
					GUI_DSSanPham.updateTableDSSanPham();
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Thêm văn phòng phẩm thành công!");
				}
			}
		});
		btnThemSP.setBorder(null);
		btnThemSP.setColor(new Color(144, 238, 144));
		btnThemSP.setColorClick(new Color(0, 100, 0));
		btnThemSP.setBorderColor(new Color(0, 128, 0));
		btnThemSP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnThemSP.setIcon(new ImageIcon(GUI_ThemSP.class.getResource("/images/Overthem.png")));
				btnThemSP.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnThemSP.setIcon(new ImageIcon(GUI_ThemSP.class.getResource("/images/them.png")));
				btnThemSP.setForeground(Color.black);
			}
		});
		btnThemSP.setIcon(new ImageIcon(GUI_ThemSP.class.getResource("/images/them.png")));
		btnThemSP.setColorOver(new Color(76, 175, 80));
		btnThemSP.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnThemSP.setBounds(394, 341, 140, 40);
		pnlVPP.add(btnThemSP);

		MyButton btnThoat = new MyButton("Thoát");
		btnThoat.setForeground(new Color(255, 255, 255));
		btnThoat.setBackground(new Color(255, 69, 0));
		btnThoat.setColor(new Color(255, 69, 0));
		btnThoat.setColorClick(new Color(128, 0, 0));
		btnThoat.setColorOver(new Color(255, 0, 0));
		btnThoat.setBorderColor(Color.RED);
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnThoat.setBorder(null);
		btnThoat.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnThoat.setBounds(694, 341, 140, 40);
		pnlVPP.add(btnThoat);

		lblAnhSanPham = new JLabel("");
		lblAnhSanPham.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAnhSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnhSanPham.setBounds(25, 25, 250, 250);
		pnlVPP.add(lblAnhSanPham);
		lblAnhSanPham.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		MyButton btnChonAnhVPP = new MyButton("Chọn ảnh sp:");
		btnChonAnhVPP.setBorder(null);
		btnChonAnhVPP.setText("Chọn ảnh sp");
		btnChonAnhVPP.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnChonAnhVPP) {
					chooser = new JFileChooser(new File(System.getProperty("user.home") + "\\Downloads")); // Downloads
																											// Directory
																											// as
																											// default
					chooser.setDialogTitle("Chọn ảnh");
					chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					// FileDialog f = new FileDialog();
					chooser.setAcceptAllFileFilterUsed(true);
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
							"Ảnh sản phẩm (*.jpg,*.png,*.jpeg,*.gif)", "jpg", "png", "jpeg", "gif");
					chooser.setFileFilter(filter);
					if (chooser.showOpenDialog(btnChonAnhVPP) == JFileChooser.APPROVE_OPTION) {
						String fileID = chooser.getSelectedFile().getPath();// đường dẫn file ở đây
						lblAnhSanPham.setIcon(new ImageIcon(fileID));
					}
				}
			}
		});

		btnChonAnhVPP.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnChonAnhVPP.setBounds(135, 290, 140, 40);
		pnlVPP.add(btnChonAnhVPP);

		JLabel lblSoLuong_1 = new JLabel("Số lượng:");
		lblSoLuong_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSoLuong_1.setBounds(25, 335, 110, 24);
		pnlVPP.add(lblSoLuong_1);

		txtSoLuong = new JSpinner();
		txtSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoLuong.setBounds(135, 336, 140, 24);
		pnlVPP.add(txtSoLuong);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(320, 25, 515, 306);
		pnlVPP.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 15, 30));

		JLabel lblTenSP = new JLabel("Tên sản phẩm:");
		lblTenSP.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTenSP.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel_1.add(lblTenSP);

		txtTenSanPham = new JTextField();
		txtTenSanPham.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenSanPham.setColumns(10);
		panel_1.add(txtTenSanPham);

		JLabel lblNCC = new JLabel("Nhà cung cấp:");
		lblNCC.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNCC.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel_1.add(lblNCC);

		txtNhaCungCap = new JTextField();
		txtNhaCungCap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNhaCungCap.setColumns(10);
		panel_1.add(txtNhaCungCap);

		JLabel lblXutX = new JLabel("Xuất xứ:");
		lblXutX.setHorizontalAlignment(SwingConstants.RIGHT);
		lblXutX.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel_1.add(lblXutX);

		txtXuatXu = new JTextField();
		txtXuatXu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtXuatXu.setColumns(10);
		panel_1.add(txtXuatXu);

		JLabel lblChatlieu = new JLabel("Chất liệu:");
		lblChatlieu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChatlieu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel_1.add(lblChatlieu);

		txtChatLieu = new JTextField();
		txtChatLieu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtChatLieu.setColumns(10);
		panel_1.add(txtChatLieu);

		JLabel lblGianhap = new JLabel("Giá nhập:");
		lblGianhap.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGianhap.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel_1.add(lblGianhap);

		txtGiaNhap = new JSpinner();
		txtGiaNhap.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1000)));
		txtGiaNhap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_1.add(txtGiaNhap);

		MyButton btnXoaRongVPP = new MyButton("Thoát");
		btnXoaRongVPP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTenSanPham.setText("");
				txtNhaCungCap.setText("");
				txtXuatXu.setText("");
				txtChatLieu.setText("");
				lblAnhSanPham.setIcon(null);
				txtGiaNhap.setValue(0);
				txtSoLuong.setValue(0);
			}
		});
		btnXoaRongVPP.setText("Xóa rỗng");
		btnXoaRongVPP.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnXoaRongVPP.setBorder(null);
		btnXoaRongVPP.setBounds(544, 341, 140, 40);
		pnlVPP.add(btnXoaRongVPP);

		JPanel pnlSach = new JPanel();
		pnlSach.setBackground(Color.WHITE);
		pnlSach.setForeground(new Color(224, 255, 255));
		tabThemSP.addTab("S\u00E1ch", null, pnlSach, null);
		pnlSach.setLayout(null);

		MyButton btnThemSach = new MyButton("Th\u00EAm");
		btnThemSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SanPhamDao spdao = new SanPhamDao();
				int i = stt;
				System.out.println(i);
				if (kiemTraNhapSach()) {

					String masach = "SP";
					if (i < 10) {
						masach = masach + "00" + i;
					}
					if (i >= 10 && i < 100) {
						masach = masach + "0" + i;
					}
					if (i >= 100 && i < 1000) {
						masach = masach + i;
					}
					String tensp = txtTenSach.getText();
					String nhaxb = txtNhaXuatBan.getText();
					int sotrang = Integer.parseInt(txtSoTrang.getText());
					String tg = txtTenTacGia.getText();
					int soluong = Integer.parseInt(txtSoLuongSach.getValue().toString());
					double gianhap = Double.parseDouble(txtGiaNhapSach.getText());
					String link = chooserS.getSelectedFile().getName();
					String anhsp = "images\\sanpham\\" + link;
					System.out.println(anhsp);
					SanPham sp = new SanPham(masach, tensp, soluong, gianhap, anhsp, new Loai("SACH"));
					sp.setNhaxuatban(nhaxb);
					sp.setSotrang(sotrang);
					sp.setTentacgia(tg);

					spdao.themSach(sp);

					GUI_CNSanPham.deleteTableVPP();
					GUI_CNSanPham.deleteTableSach();
					GUI_CNSanPham.updateTableVPP();
					GUI_CNSanPham.updateTableSach();
					GUI_DSSanPham.deleteTableDSSanPham();
					GUI_DSSanPham.updateTableDSSanPham();

					setVisible(false);
					JOptionPane.showMessageDialog(null, "Thêm Sách thành công!");
				}
			}
		});

		btnThemSach.setColor(new Color(152, 251, 152));
		btnThemSach.setColorClick(new Color(0, 128, 0));
		btnThemSach.setBorderColor(new Color(0, 128, 0));
		btnThemSach.setBorder(null);
		btnThemSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnThemSach.setIcon(new ImageIcon(GUI_ThemSP.class.getResource("/images/Overthem.png")));
				btnThemSach.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnThemSach.setIcon(new ImageIcon(GUI_ThemSP.class.getResource("/images/them.png")));
				btnThemSach.setForeground(Color.black);
			}
		});
		btnThemSach.setIcon(new ImageIcon(GUI_ThemSP.class.getResource("/images/them.png")));
		btnThemSach.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnThemSach.setColorOver(new Color(76, 175, 80));
		btnThemSach.setBounds(394, 344, 140, 40);
		pnlSach.add(btnThemSach);

		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setHorizontalAlignment(SwingConstants.LEFT);
		lblSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSoLuong.setBounds(25, 335, 110, 24);
		pnlSach.add(lblSoLuong);

		txtSoLuongSach = new JSpinner();
		txtSoLuongSach.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoLuongSach.setBounds(126, 335, 149, 24);
		pnlSach.add(txtSoLuongSach);

		lblAnhSach = new JLabel("");
		lblAnhSach.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblAnhSach.setBounds(25, 25, 250, 250);
		pnlSach.add(lblAnhSach);

		MyButton btnChonAnhSach = new MyButton("Chọn ảnh sp:");
		btnChonAnhSach.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnChonAnhSach) {
					chooserS = new JFileChooser(new File(System.getProperty("user.home") + "\\Downloads")); // Downloads
																											// default
					chooserS.setDialogTitle("Chọn ảnh");
					chooserS.setFileSelectionMode(JFileChooser.FILES_ONLY);
					chooserS.setAcceptAllFileFilterUsed(true);
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Ảnh sản phẩm (*.jpg,*.png,*.gif)",
							"jpg", "png", "gif");
					chooserS.setFileFilter(filter);
					if (chooserS.showOpenDialog(btnChonAnhVPP) == JFileChooser.APPROVE_OPTION) {
						String anhsp = chooserS.getSelectedFile().getPath();
						lblAnhSach.setIcon(new ImageIcon(anhsp));
					}
				}

			}
		});
		btnChonAnhSach.setBorder(null);
		btnChonAnhSach.setText("Chọn ảnh sách");
		btnChonAnhSach.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnChonAnhSach.setBounds(126, 290, 149, 40);
		pnlSach.add(btnChonAnhSach);

		MyButton btnThoatSach = new MyButton("Thoát");
		btnThoatSach.setBorderColor(new Color(255, 0, 0));
		btnThoatSach.setColorClick(new Color(178, 34, 34));
		btnThoatSach.setColorOver(new Color(255, 0, 0));
		btnThoatSach.setColor(new Color(255, 69, 0));
		btnThoatSach.setForeground(new Color(255, 255, 255));
		btnThoatSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnThoatSach.setBorder(null);
		btnThoatSach.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnThoatSach.setBounds(694, 344, 140, 40);
		pnlSach.add(btnThoatSach);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(320, 25, 515, 309);
		pnlSach.add(panel_2);
		panel_2.setLayout(new GridLayout(5, 2, 15, 30));

		JLabel lblTenSach = new JLabel("Tên sách:");
		lblTenSach.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTenSach.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel_2.add(lblTenSach);

		txtTenSach = new JTextField();
		txtTenSach.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenSach.setColumns(10);
		panel_2.add(txtTenSach);

		JLabel lblNXB = new JLabel("Nhà xuất bản:");
		lblNXB.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNXB.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel_2.add(lblNXB);

		txtNhaXuatBan = new JTextField();
		txtNhaXuatBan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNhaXuatBan.setColumns(10);
		panel_2.add(txtNhaXuatBan);

		JLabel lblTentacgia = new JLabel("Tên tác giả:");
		lblTentacgia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTentacgia.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel_2.add(lblTentacgia);

		txtTenTacGia = new JTextField();
		txtTenTacGia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenTacGia.setColumns(10);
		panel_2.add(txtTenTacGia);

		JLabel lblSotrang = new JLabel("Số trang:");
		lblSotrang.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSotrang.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel_2.add(lblSotrang);

		txtSoTrang = new JTextField();
		txtSoTrang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoTrang.setColumns(10);
		panel_2.add(txtSoTrang);

		JLabel lblGianhap_1 = new JLabel("Giá nhập:");
		lblGianhap_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGianhap_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel_2.add(lblGianhap_1);

		txtGiaNhapSach = new JTextField();
		txtGiaNhapSach.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtGiaNhapSach.setColumns(10);
		panel_2.add(txtGiaNhapSach);

		MyButton btnXoaRongSach = new MyButton("Thoát");
		btnXoaRongSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTenSach.setText("");
				txtNhaXuatBan.setText("");
				txtSoTrang.setText("");
				txtTenTacGia.setText("");
				lblAnhSach.setIcon(null);
				txtGiaNhapSach.setText("");
				txtSoLuongSach.setValue(0);
			}
		});
		btnXoaRongSach.setText("Xóa rỗng");
		btnXoaRongSach.setFont(new Font("Segoe UI", Font.BOLD, 17));
		btnXoaRongSach.setBorder(null);
		btnXoaRongSach.setBounds(544, 344, 140, 40);
		pnlSach.add(btnXoaRongSach);

		JLabel lblThemSP = new JLabel("TH\u00CAM S\u1EA2N PH\u1EA8M");
		lblThemSP.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblThemSP.setVerticalAlignment(SwingConstants.BOTTOM);
		lblThemSP.setForeground(new Color(0, 0, 128));
		lblThemSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblThemSP.setFont(new Font("Segoe UI", Font.BOLD, 35));
		getContentPane().add(lblThemSP, BorderLayout.NORTH);
	}

	/**
	 * Kiểm tra ràng buộc
	 * 
	 * @return
	 */
	public boolean kiemTraNhap() {

		if (txtTenSanPham.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập tên văn phòng phẩm!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!txtTenSanPham.getText().trim().matches(".+")) {
			JOptionPane.showMessageDialog(null, "Lỗi tên văn phòng phẩm!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (txtNhaCungCap.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập nhà cung cấp!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!txtNhaCungCap.getText().trim().matches(".+")) {
			JOptionPane.showMessageDialog(null, "Lỗi nhà cung cấp !","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (txtXuatXu.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Xuất xứ không được rỗng!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!txtXuatXu.getText().trim().matches(".+")) {
			JOptionPane.showMessageDialog(null, "Lỗi xuất xứ !","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (txtChatLieu.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chất liệu không được rỗng!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!txtChatLieu.getText().trim().matches(".+")) {
			JOptionPane.showMessageDialog(null, "Chất liệu không hợp lệ","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		String soluong = txtSoLuong.getValue().toString().trim();

		if (soluong.length() > 0) {
			try {
				int x = Integer.parseInt(soluong);
				if (x < 0) {
					JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải lớn hơn 0. Ví dụ: 1,2,3..","Thông báo",JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải là chữ số","Thông báo",JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập vào số lượng","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			anhsp = chooser.getSelectedFile().getPath();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Chưa chọn ảnh!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	/**
	 * Kiểm tra ràng buộc thêm sách
	 * 
	 * @return
	 */
	public boolean kiemTraNhapSach() {

		if (txtTenSach.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập tên sách!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!txtTenSach.getText().trim().matches(".+")) {
			JOptionPane.showMessageDialog(null, "Lỗi tên văn sách!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (txtNhaXuatBan.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập nhà xuất bản!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!txtNhaXuatBan.getText().trim().matches(".+")) {
			JOptionPane.showMessageDialog(null, "Lỗi nhà xuất bản !","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (txtSoTrang.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập số trang!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!txtSoTrang.getText().trim().matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Số trang phải lớn hơn 0 và là chữ số. Ví dụ:10,15,17...","Thông báo",JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (txtTenTacGia.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập tác giả!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!txtTenTacGia.getText().trim().matches(".+")) {
			JOptionPane.showMessageDialog(null, "Tác giả không hợp lệ!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		String soluongsach = txtSoLuongSach.getValue().toString().trim();

		if (soluongsach.length() > 0) {
			try {
				int x = Integer.parseInt(soluongsach);
				if (x < 0) {
					JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải lớn hơn 0. Ví dụ: 1,2,3..","Thông báo",JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải là chữ số","Thông báo",JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập vào số lượng","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (txtGiaNhapSach.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập giá đơn nhập!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!txtGiaNhapSach.getText().trim().matches("[0-9.]+")) {
			JOptionPane.showMessageDialog(null, "Giá nhập phải lớn hơn 0 và là chữ số. Ví dụ: 1000,2000,3000,...","Thông báo",JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		try {
			anhsp = chooserS.getSelectedFile().getPath();

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Chưa chọn ảnh!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}
}
