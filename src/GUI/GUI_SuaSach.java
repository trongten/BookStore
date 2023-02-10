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

public class GUI_SuaSach extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmThmSch;
	private JSpinner txtSoLuongSach;
	private MyButton btnChonAnh;
	private JTextField txtTenSach;
	private JTextField txtNhaXuatBan;
	private JTextField txtTenTacGia;
	private JTextField txtSoTrang;
	private JSpinner txtGiaNhapSach;
	private JFileChooser chooser;
	private JLabel lblAnhSanPham;
	private String ma;
	@SuppressWarnings("unused")
	private String anhsp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_SuaSach window = new GUI_SuaSach();
					window.frmThmSch.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_SuaSach() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("removal")
	private void initialize() {
		frmThmSch = new JFrame();
		frmThmSch.setResizable(false);
		frmThmSch.setLocationRelativeTo(null);
		frmThmSch.setTitle("Sửa Thông tin Sách");
		frmThmSch.setBackground(Color.WHITE);
		frmThmSch.getContentPane().setBackground(Color.WHITE);
		frmThmSch.setBounds(100, 100, 995, 601);
		frmThmSch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmThmSch.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel lblThemSP = new JLabel("SỬA THÔNG TIN SÁCH");
		lblThemSP.setForeground(new Color(0, 0, 128));
		lblThemSP.setHorizontalAlignment(SwingConstants.CENTER);
		lblThemSP.setFont(new Font("Segoe UI", Font.BOLD, 37));
		frmThmSch.getContentPane().add(lblThemSP, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		frmThmSch.getContentPane().add(panel);
		panel.setLayout(null);
		lblAnhSanPham = new JLabel("");
		lblAnhSanPham.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAnhSanPham.setHorizontalAlignment(SwingConstants.CENTER);
		MyButton btnSuaSach = new MyButton("Sửa");
		btnSuaSach.setColor(new Color(255, 215, 0));
		btnSuaSach.setForeground(Color.WHITE);
		btnSuaSach.setBackground(new Color(255, 215, 0));
		btnSuaSach.setBorderColor(Color.ORANGE);
		btnSuaSach.setColorOver(new Color(255, 255, 0));
		btnSuaSach.setColorClick(new Color(210, 105, 30));
		btnSuaSach.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					txtGiaNhapSach.commitEdit();
				} catch (ParseException e1) {
					JComponent editor = txtGiaNhapSach.getEditor();
					if (editor instanceof DefaultEditor) {
						((DefaultEditor) editor).getTextField().setValue(txtGiaNhapSach.getValue());
					}
				}

				if (kiemTraNhapSach()) {

					String masach = ma;
					String tensp = txtTenSach.getText();
					String nhaxb = txtNhaXuatBan.getText();
					int sotrang = Integer.parseInt(txtSoTrang.getText());
					String tg = txtTenTacGia.getText();
					int soluong = Integer.parseInt(txtSoLuongSach.getValue().toString());
					double gianhap = Double.parseDouble(txtGiaNhapSach.getValue().toString());
					String anhsp = "";
					String link = "";
					try {
						link = chooser.getSelectedFile().getName();
						anhsp = "images\\sanpham\\" + link;
					} catch (Exception err) {
						link = new SanPhamDao().timSanPham(ma).getAnhsanpham();
						anhsp =link;
					}
					SanPham sp = new SanPham(masach, tensp, soluong, gianhap, anhsp, new Loai("SACH"));
					sp.setNhaxuatban(nhaxb);
					sp.setSotrang(sotrang);
					sp.setTentacgia(tg);

					SanPhamDao spdao = new SanPhamDao();
					spdao.suaSanPham(sp);

					GUI_CNSanPham.deleteTableVPP();
					GUI_CNSanPham.deleteTableSach();
					GUI_CNSanPham.updateTableVPP();
					GUI_CNSanPham.updateTableSach();

					GUI_DSSanPham.deleteTableDSSanPham();
					GUI_DSSanPham.updateTableDSSanPham();
					frmThmSch.setVisible(false);
					JOptionPane.showMessageDialog(null, "Sửa Sách thành công!");
				}

			}
		});
		btnSuaSach.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnSuaSach.setBounds(501, 413, 140, 40);
		panel.add(btnSuaSach);

		MyButton btnThoat = new MyButton("Thoát");
		btnThoat.setColorOver(new Color(255, 0, 0));
		btnThoat.setForeground(new Color(255, 255, 255));
		btnThoat.setColorClick(new Color(178, 34, 34));
		btnThoat.setBorderColor(new Color(255, 0, 0));
		btnThoat.setBackground(new Color(255, 51, 0));
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmThmSch.dispose();
			}
		});
		btnThoat.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnThoat.setBounds(802, 413, 140, 40);
		panel.add(btnThoat);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBounds(229, 413, 247, 40);
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 15));

		JLabel lblSoLuong_1 = new JLabel("Số lượng:");
		lblSoLuong_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoLuong_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		panel_2.add(lblSoLuong_1);
		lblSoLuong_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		txtSoLuongSach = new JSpinner();
		txtSoLuongSach.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		panel_2.add(txtSoLuongSach);
		txtSoLuongSach.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		lblAnhSanPham.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblAnhSanPham.setBounds(21, 21, 455, 355);
		panel.add(lblAnhSanPham);

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setOpaque(false);
		panel_2_1.setBackground(new Color(224, 255, 255));
		panel_2_1.setBounds(481, 21, 467, 355);
		panel.add(panel_2_1);
		panel_2_1.setLayout(new GridLayout(6, 2, 15, 30));

		JLabel lblTenSach = new JLabel("Tên sách:");
		lblTenSach.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTenSach.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel_2_1.add(lblTenSach);

		txtTenSach = new JTextField();
		txtTenSach.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenSach.setColumns(10);
		panel_2_1.add(txtTenSach);

		JLabel lblNXB = new JLabel("Nhà xuất bản:");
		lblNXB.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNXB.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel_2_1.add(lblNXB);

		txtNhaXuatBan = new JTextField();
		txtNhaXuatBan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtNhaXuatBan.setColumns(10);
		panel_2_1.add(txtNhaXuatBan);

		JLabel lblTentacgia = new JLabel("Tên tác giả:");
		lblTentacgia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTentacgia.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel_2_1.add(lblTentacgia);

		txtTenTacGia = new JTextField();
		txtTenTacGia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenTacGia.setColumns(10);
		panel_2_1.add(txtTenTacGia);

		JLabel lblSotrang = new JLabel("Số trang:");
		lblSotrang.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSotrang.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel_2_1.add(lblSotrang);

		txtSoTrang = new JTextField();
		txtSoTrang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSoTrang.setColumns(10);
		panel_2_1.add(txtSoTrang);

		JLabel lblGianhap_1 = new JLabel("Giá nhập:");
		lblGianhap_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGianhap_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel_2_1.add(lblGianhap_1);

		txtGiaNhapSach = new JSpinner();
		txtGiaNhapSach.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1000)));
		txtGiaNhapSach.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_2_1.add(txtGiaNhapSach);

		MyButton btnXoaRong = new MyButton("Xóa rỗng");
		btnXoaRong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTenSach.setText("");
				txtNhaXuatBan.setText("");
				txtSoTrang.setText("");
				txtTenTacGia.setText("");
				lblAnhSanPham.setIcon(null);
				txtGiaNhapSach.setValue(0);
				txtSoLuongSach.setValue(0);
			}
		});
		btnXoaRong.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnXoaRong.setBounds(652, 413, 140, 40);
		panel.add(btnXoaRong);

		btnChonAnh = new MyButton("Chọn ảnh sp:");
		btnChonAnh.setBounds(21, 413, 136, 40);
		panel.add(btnChonAnh);
		btnChonAnh.setText("Chọn ảnh sách:");
		btnChonAnh.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnChonAnh) {
					chooser = new JFileChooser(new File(System.getProperty("user.home") + "\\Downloads")); // Downloads
																											// Directory
																											// // as
																											// default
					chooser.setDialogTitle("Chọn ảnh");
					chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					// FileDialog f = new FileDialog();
					chooser.setAcceptAllFileFilterUsed(true);
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
							"Ảnh sản phẩm (*.jpg,*.png,*.jpeg,*.gif)", "jpg", "png", "jpeg", "gif");
					chooser.setFileFilter(filter);
					if (chooser.showOpenDialog(btnChonAnh) == JFileChooser.APPROVE_OPTION) {
						String link = chooser.getSelectedFile().getPath();
						lblAnhSanPham.setIcon(new ImageIcon(link));
					}
				}
			}
		});
		btnChonAnh.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		setTextSach();

		frmThmSch.setVisible(true);
	}

	@SuppressWarnings("unused")
	public void setTextSach() {
		Object[] r = GUI_CNSanPham.getSelectedRowSach();
		ma = (String) r[0];
		if (r == null) {} else {
			SanPhamDao spdao = new SanPhamDao();
			SanPham sp = spdao.timSanPham(ma);

			txtTenSach.setText((String) r[1]);
			txtNhaXuatBan.setText((String) r[4]);
			txtSoTrang.setText(String.valueOf(r[3]));
			txtTenTacGia.setText((String) r[2]);
			lblAnhSanPham.setIcon(new ImageIcon(GUIChinh_QuanLy.class.getResource("/" + sp.getAnhsanpham())));
			txtGiaNhapSach.setValue(r[7]);
			txtSoLuongSach.setValue(r[5]);

		}
	}

	public boolean kiemTraNhapSach() {

		if (txtTenSach.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập tên sách!");
			return false;
		} else if (!txtTenSach.getText().trim().matches(".+")) {
			JOptionPane.showMessageDialog(null, "Lỗi tên văn sách!");
			return false;
		}

		if (txtNhaXuatBan.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập nhà xuất bản!");
			return false;
		} else if (!txtNhaXuatBan.getText().trim().matches(".+")) {
			JOptionPane.showMessageDialog(null, "Lỗi nhà xuất bản !");
			return false;
		}

		if (txtSoTrang.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập số trang!");
			return false;
		} else if (!txtSoTrang.getText().trim().matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Số trang nhập số !");
			return false;
		}

		if (txtTenTacGia.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập tác giả!");
			return false;
		} else if (!txtTenTacGia.getText().trim().matches(".+")) {
			JOptionPane.showMessageDialog(null, "Lỗi tác giả !");
			return false;
		}

		if ((int) txtSoLuongSach.getValue() == 0) {
			JOptionPane.showMessageDialog(null, "Chưa nhập số lượng!");
			return false;
		}

		if ((double) txtGiaNhapSach.getValue() == 0) {
			JOptionPane.showMessageDialog(null, "Chưa nhập giá đơn nhập!");
			return false;
		}

		return true;
	}
}
