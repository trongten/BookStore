package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Others.MyButton;
import dao.SanPhamDao;
import entity.Loai;
import entity.SanPham;

/**
 * Giao Diện sửa thông tin văn phòng phẩm
 * 
 * @author Phan Võ Trọng - Võ Phước Lưu
 *
 */
public class GUI_SuaVanPhongPham extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField txtTenSanPham;
	private JTextField txtNhaCungCap;
	private JTextField txtXuatXu;
	private JTextField txtChatLieu;
	private JSpinner txtGiaNhap;
	private JSpinner txtSoLuong;
	private MyButton btnChonAnh;
	private JLabel lblAnhSanPham;
	private JFileChooser chooser;
	private String ma;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_SuaVanPhongPham window = new GUI_SuaVanPhongPham();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_SuaVanPhongPham() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("removal")
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 977, 601);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel lblThemSP = new JLabel("SỬA THÔNG TIN VĂN PHÒNG PHẨM");
		lblThemSP.setForeground(new Color(0, 0, 128));
		lblThemSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblThemSP.setFont(new Font("Segoe UI", Font.BOLD, 37));
		frame.getContentPane().add(lblThemSP, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		MyButton btnSuaVPP = new MyButton("Sửa");
		btnSuaVPP.setForeground(new Color(255, 255, 255));
		btnSuaVPP.setBorderColor(Color.ORANGE);
		btnSuaVPP.setColor(new Color(255, 215, 0));
		btnSuaVPP.setColorClick(new Color(210, 105, 30));
		btnSuaVPP.setColorOver(new Color(255, 255, 0));
		btnSuaVPP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					txtGiaNhap.commitEdit();
				} catch (ParseException e1) {
					JComponent editor = txtGiaNhap.getEditor();
					if (editor instanceof DefaultEditor) {
						((DefaultEditor) editor).getTextField().setValue(txtGiaNhap.getValue());
					}
				}

				if (kiemTraNhap()) {

					String masp = ma;
					String tensp = txtTenSanPham.getText();
					String nhacc = txtNhaCungCap.getText();
					String xuatxu = txtXuatXu.getText();
					String chatlieu = txtChatLieu.getText();
					int soluong = Integer.parseInt(txtSoLuong.getValue().toString());
					double gianhap = Double.parseDouble(txtGiaNhap.getValue().toString());
					String anhsp = "";
					String link = "";
					try {
						link = chooser.getSelectedFile().getName();
						anhsp = "images\\sanpham\\" + link;
					} catch (Exception err) {
						link = new SanPhamDao().timSanPham(ma).getAnhsanpham();
						anhsp = link;
					}
					SanPham sp = new SanPham(masp, tensp, soluong, gianhap, anhsp, new Loai("VANP"));
					sp.setNhacungcap(nhacc);
					sp.setChatlieu(chatlieu);
					sp.setXuatxu(xuatxu);

					SanPhamDao spdao = new SanPhamDao();
					spdao.suaSanPham(sp);

					GUI_CNSanPham.deleteTableVPP();
					GUI_CNSanPham.deleteTableSach();
					GUI_CNSanPham.updateTableVPP();
					GUI_CNSanPham.updateTableSach();

					GUI_DSSanPham.deleteTableDSSanPham();
					GUI_DSSanPham.updateTableDSSanPham();
					frame.setVisible(false);
					JOptionPane.showMessageDialog(null, "Sửa văn phòng phẩm thành công!");
				}
			}
		});
		btnSuaVPP.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnSuaVPP.setBounds(491, 406, 140, 40);
		panel.add(btnSuaVPP);

		MyButton btnThoat = new MyButton("Thoát");
		btnThoat.setForeground(new Color(255, 255, 255));
		btnThoat.setColor(new Color(255, 69, 0));
		btnThoat.setColorClick(new Color(178, 34, 34));
		btnThoat.setColorOver(new Color(255, 0, 0));
		btnThoat.setBorderColor(Color.RED);
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnThoat.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnThoat.setBounds(791, 406, 140, 40);
		panel.add(btnThoat);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(491, 26, 440, 355);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(6, 2, 15, 30));

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
		txtGiaNhap.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1000)));
		txtGiaNhap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_1.add(txtGiaNhap);

		lblAnhSanPham = new JLabel("");
		lblAnhSanPham.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAnhSanPham.setHorizontalAlignment(SwingConstants.CENTER);

		lblAnhSanPham.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblAnhSanPham.setBounds(26, 26, 455, 355);
		panel.add(lblAnhSanPham);

		MyButton btnXoaRong = new MyButton("Xóa rỗng");
		btnXoaRong.addActionListener(new ActionListener() {
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
		btnXoaRong.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnXoaRong.setBounds(641, 406, 140, 40);
		panel.add(btnXoaRong);

		btnChonAnh = new MyButton("Chọn ảnh sp:");
		btnChonAnh.setBounds(26, 403, 192, 40);
		panel.add(btnChonAnh);
		btnChonAnh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnChonAnh) {
					chooser = new JFileChooser(new File(System.getProperty("user.home") + "\\Downloads"));
					chooser.setDialogTitle("Chọn ảnh");
					chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					chooser.setAcceptAllFileFilterUsed(true);
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
							"Ảnh sản phẩm (*.jpg,*.png,*.jpeg,*.gif)", "jpg", "png", "jpeg", "gif");
					chooser.setFileFilter(filter);
					if (chooser.showOpenDialog(btnChonAnh) == JFileChooser.APPROVE_OPTION) {
						String fileID = chooser.getSelectedFile().getPath();// đường dẫn file ở đây
						lblAnhSanPham.setIcon(new ImageIcon(fileID));
					}
				}
			}
		});
		btnChonAnh.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBounds(251, 406, 230, 37);
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblSoLuong_1 = new JLabel("Số lượng:");
		panel_2.add(lblSoLuong_1);
		lblSoLuong_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoLuong_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		txtSoLuong = new JSpinner();
		txtSoLuong.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		panel_2.add(txtSoLuong);
		txtSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		setTextVPP();

		frame.setVisible(true);
	}

	@SuppressWarnings("unused")
	public void setTextVPP() {
		Object[] r = GUI_CNSanPham.getSelectedRowVPP();
		ma = (String) r[0];
		if (r == null) {
		} else {

			SanPhamDao spdao = new SanPhamDao();
			SanPham sp = spdao.timSanPham(ma);

			txtTenSanPham.setText((String) r[1]);
			txtNhaCungCap.setText((String) r[2]);
			txtXuatXu.setText((String) r[3]);
			txtChatLieu.setText((String) r[4]);
			lblAnhSanPham.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/" + sp.getAnhsanpham())));
			txtGiaNhap.setValue(r[7]);
			txtSoLuong.setValue(r[5]);
		}
	}

	/**
	 * Kiểm tra ràng buộc
	 * 
	 * @return
	 */
	public boolean kiemTraNhap() {

		if (txtTenSanPham.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập tên văn phòng phẩm!");
			return false;
		} else if (!txtTenSanPham.getText().trim().matches(".+")) {
			JOptionPane.showMessageDialog(null, "Lỗi tên văn phòng phẩm!");
			return false;
		}

		if (txtNhaCungCap.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Nhà cung cấp không được trống!");
			return false;
		} else if (!txtNhaCungCap.getText().trim().matches(".+")) {
			JOptionPane.showMessageDialog(null, "Lỗi nhà cung cấp !");
			return false;
		}

		if (txtXuatXu.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Xuất xứ không được để trống!");
			return false;
		} else if (!txtXuatXu.getText().trim().matches(".+")) {
			JOptionPane.showMessageDialog(null, "Lỗi xuất xứ !");
			return false;
		}

		if (txtChatLieu.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chất liệu không được để trống!");
			return false;
		} else if (!txtChatLieu.getText().trim().matches(".+")) {
			JOptionPane.showMessageDialog(null, "Lỗi Chất liệu!");
			return false;
		}

		if ((int) txtSoLuong.getValue() == 0) {
			JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0!");
			return false;
		}

		if (txtGiaNhap.getValue().toString().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập giá đơn nhập!");
			return false;
		} else if (!txtGiaNhap.getValue().toString().trim().matches("[0-9.]+")) {
			JOptionPane.showMessageDialog(null, "Giá nhập không hợp lệ!");
			return false;
		}

		return true;
	}

}
