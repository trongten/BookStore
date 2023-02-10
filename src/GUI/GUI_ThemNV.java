package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Others.DateLabelFormatter;
import Others.MyButton;
import dao.NhanVienDao;
import dao.TaiKhoanDao;
import entity.NhanVien;
import entity.TaiKhoan;
/**
 * Giao Diện thêm nhân viên
 * @author Phan Võ Trọng
 *
 */
public class GUI_ThemNV extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTennhanvien;
	private JTextField txtCmnd;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField txtDiachi;
	private JTextField txtSdt;
	private UtilDateModel dateModel;
	private Properties dateProperties;
	private JDatePanelImpl datePanel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton radNam;
	private JRadioButton radNu;
	// private int testPDF = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new GUI_ThemNV().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_ThemNV() {
		setResizable(false);
		setLocationRelativeTo(null);
		setMaximumSize(new Dimension(550, 600));
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI_ThemNV.class.getResource("/images/themnhanvien.png")));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Th\u00EAm nh\u00E2n vi\u00EAn");
		setSize(522, 602);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		MyButton btnThemNV = new MyButton("L\u01B0u");
		getContentPane().setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Th\u00EAm Nh\u00E2n Vi\u00EAn");
		lblNewLabel_5.setBounds(0, 0, 530, 38);
		lblNewLabel_5.setForeground(new Color(0, 0, 128));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 28));
		getContentPane().add(lblNewLabel_5);
		// thanh ngày giờ
		dateModel = new UtilDateModel();
		dateProperties = new Properties();
		dateProperties.put("text.today", "Today");
		dateProperties.put("text.month", "Month");
		dateProperties.put("text.year", "Year");
		datePanel = new JDatePanelImpl(dateModel, dateProperties);

		JPanel panel = new JPanel();
		panel.setBounds(34, 38, 446, 450);
		panel.setBackground(Color.WHITE);
		panel.setOpaque(false);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(8, 2, 15, 30));

		JLabel lblNewLabel = new JLabel("Tên người xin việc:");
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		txtTennhanvien = new JTextField();

		panel.add(txtTennhanvien);
		txtTennhanvien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTennhanvien.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("S\u1ED1 CMND:");
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		txtCmnd = new JTextField();

		panel.add(txtCmnd);
		txtCmnd.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtCmnd.setColumns(10);

		lblNewLabel_3 = new JLabel("Ng\u00E0y sinh:");
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JDatePickerImpl dateNgaySinh = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		dateNgaySinh.setBackground(Color.WHITE);

		panel.add(dateNgaySinh);

		dateNgaySinh.getJFormattedTextField().setEditable(true);
		dateNgaySinh.getJFormattedTextField().setBackground(SystemColor.textHighlightText);

		JLabel lblNewLabel_3_1 = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i:");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel_3_1);
		lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		txtSdt = new JTextField();

		panel.add(txtSdt);
		txtSdt.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSdt.setColumns(10);

		lblNewLabel_2 = new JLabel("\u0110\u1ECBa ch\u1EC9:");
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		txtDiachi = new JTextField();

		panel.add(txtDiachi);
		txtDiachi.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtDiachi.setColumns(10);

		lblNewLabel_4 = new JLabel("Gi\u1EDBi t\u00EDnh:");
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel.add(panel_1);

		radNam = new JRadioButton("Nam");
		radNam.setOpaque(false);
		panel_1.add(radNam);
		radNam.setBackground(new Color(230, 230, 250));
		buttonGroup.add(radNam);
		radNam.setSelected(true);
		radNam.setHorizontalAlignment(SwingConstants.CENTER);

		radNam.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		radNu = new JRadioButton("N\u1EEF");
		radNu.setOpaque(false);
		panel_1.add(radNu);
		radNu.setBackground(new Color(230, 230, 250));
		buttonGroup.add(radNu);
		radNu.setHorizontalAlignment(SwingConstants.CENTER);
		radNu.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		JLabel lblNewLabel_6 = new JLabel("Ca:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel.add(lblNewLabel_6);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_4);

		JComboBox<String> cboCa = new JComboBox<String>();
		cboCa.setBackground(Color.WHITE);
		cboCa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cboCa.setModel(new DefaultComboBoxModel<String>(new String[] { "Toàn thời gian", "1", "2" }));
		panel_4.add(cboCa);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(34, 498, 446, 48);
		panel_2.setOpaque(false);
		getContentPane().add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 3, 5, 0));
		MyButton btnLuu = new MyButton("L\u01B0u");

		NhanVienDao nvdao = new NhanVienDao();
		ArrayList<NhanVien> nvcuoi = nvdao.layDsNhanVien();
		String manv = nvcuoi.get(nvcuoi.size() - 1).getManhanvien()
				.substring(nvcuoi.get(nvcuoi.size() - 1).getManhanvien().length() - 3);
		int stt = Integer.parseInt(manv.trim()) + 1;
		System.out.println("số lượng phần tử trong danh sách: " + manv + "\n mã cần ra: " + stt);

		btnLuu.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				NhanVienDao nvdao = new NhanVienDao();
				int i = stt;

				if (kiemTraNhap()) {
					String manv = "NV";
					if (i < 10) {
						manv = manv + "00" + i;
					}
					if (i > 10 && i < 100) {
						manv = manv + "0" + i;
					}
					if (i > 100 && i < 1000) {
						manv = manv + i;
					}

					String hoten = vietHoaChuDau(txtTennhanvien.getText());
					Date ngaysinh = new Date(dateModel.getYear() - 1900, dateModel.getMonth(), dateModel.getDay());
					String diachi = txtDiachi.getText();
					String cmnd = txtCmnd.getText().trim();
					boolean chucvu = false;
					boolean gioitinh = radNam.isSelected();
					NhanVien ql = new NhanVien("NV001");
					String sdt = txtSdt.getText().trim();
					String caLam = (String) cboCa.getSelectedItem();
					int ca = -1;
					if (caLam.equalsIgnoreCase("Toàn thời gian")) {
						ca = 0;
					}
					if (caLam.equalsIgnoreCase("1")) {
						ca = 1;
					}
					if (caLam.equalsIgnoreCase("2")) {
						ca = 2;
					}

					NhanVien nv = new NhanVien(manv, hoten, ngaysinh, diachi, cmnd, chucvu, gioitinh, ca, ql, sdt);
					nvdao.themNhanVien(nv);
					TaiKhoanDao tkdao = new TaiKhoanDao();
					TaiKhoan tk = new TaiKhoan(manv, "1234");
					tkdao.themTaiKhoan(tk);
					GUI_CNNhanVien.deleteTableCNNV();
					GUI_CNNhanVien.updateTableCNNV();
					GUI_DSNhanVien.deleteTableDSNV();
					GUI_DSNhanVien.updateTableDSNV();
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Thêm người xin việc thành công!");
				}
			}
		});
		panel_3.add(btnLuu);
		btnLuu.setText(" Lưu ");
		btnLuu.setForeground(new Color(255, 255, 255));
		btnLuu.setBorder(null);
		btnLuu.setColor(new Color(60, 179, 113));
		btnLuu.setBackground(new Color(60, 179, 113));
		btnLuu.setBorderColor(new Color(0, 100, 0));
		btnLuu.setColorOver(new Color(0, 100, 0));
		btnLuu.setColorClick(new Color(67, 102, 62));
		btnThemNV = btnLuu;
		btnThemNV.setFont(new Font("Segoe UI", Font.BOLD, 20));

		MyButton btnNewButton = new MyButton("Xóa rỗng");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtTennhanvien.setText("");
				txtCmnd.setText("");
				txtSdt.setText("");
				txtDiachi.setText("");
				dateModel.setValue(null);
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		panel_3.add(btnNewButton);

		MyButton btnThoat = new MyButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_3.add(btnThoat);
		btnThoat.setText(" Thoát ");
		btnThoat.setBorderColor(new Color(139, 0, 0));
		btnThoat.setColorOver(new Color(178, 34, 34));
		btnThoat.setColor(new Color(255, 69, 0));
		btnThoat.setForeground(Color.WHITE);
		btnThoat.setBorder(null);
		btnThoat.setFont(new Font("Segoe UI", Font.BOLD, 20));

	}

	@SuppressWarnings("deprecation")
	public boolean kiemTraNhap() {
		if (txtTennhanvien.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập tên người xin việc!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (txtCmnd.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập chứng minh nhân dân của người xin việc!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!txtCmnd.getText().trim().matches("([0-9]{9}([0-9]{3})?)")) {
			JOptionPane.showMessageDialog(null, "Chứng minh nhân dân của người xin việc là 9 hoặc 12 chữ số !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (txtSdt.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập số điện thoại của người xin việc!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!txtSdt.getText().trim().matches(
				"^(033|034|035|036|037|038|039|056|058|059|070|076|077|078|079|081|082|083|084|085)[0-9]{7}")) {
			JOptionPane.showMessageDialog(null, "Số điện thoại của người xin việc phải thuộc các đầu số hiện hành. Ví Dụ:033,07..!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (txtDiachi.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập địa chỉ của người xin việc!","Thông báo",JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		@SuppressWarnings("unused")
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);

		Date a = date;
		Date b = new Date(dateModel.getYear() - 1900, dateModel.getMonth(), dateModel.getDay());

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(b);
		c2.setTime(a);

		long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);

		if ((noDay / 365) < 18) {
			JOptionPane.showMessageDialog(null, "Người xin việc chưa đủ 18 tuổi !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
			return false;

		}
		return true;
	}

	public String vietHoaChuDau(String chuoi) {
		String[] arr = chuoi.split(" ");
		String str = "";
		for (String x : arr) {
			str = str + (x.substring(0, 1).toUpperCase() + x.substring(1));
			str = str + " ";
		}
		return str;
	}

}
