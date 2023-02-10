package GUI;

import java.awt.Color;
import java.awt.Component;
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
import java.util.Calendar;
import java.util.Properties;

import javax.swing.Box;
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
import entity.NhanVien;

public class GUI_SuaNV extends JFrame {

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
	private String manv;
	private JComboBox<String> cboCa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new GUI_SuaNV().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_SuaNV() {
		setResizable(false);
		setMaximumSize(new Dimension(500, 550));
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI_SuaNV.class.getResource("/images/suathongtin.png")));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Sửa thông tin nhân viên");
		setSize(498, 612);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		MyButton btnThemNV = new MyButton("L\u01B0u");
		getContentPane().setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Sửa Nhân Viên");
		lblNewLabel_5.setBounds(0, 0, 484, 38);
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
		panel.setBounds(0, 38, 456, 443);
		panel.setMaximumSize(new Dimension(500, 550));
		panel.setOpaque(false);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(7, 2, 15, 30));

		JLabel lblNewLabel = new JLabel("T\u00EAn nh\u00E2n vi\u00EAn:");
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
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
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
		panel.add(panel_4);
		panel_4.setLayout(null);

		cboCa = new JComboBox<String>();
		cboCa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cboCa.setBounds(5, 5, 126, 22);
		cboCa.setModel(new DefaultComboBoxModel<String>(new String[] { "Toàn thời gian", "1", "2" }));
		panel_4.add(cboCa);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(21, 495, 435, 45);
		panel_2.setOpaque(false);
		getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 10, 15));
		MyButton btnSua = new MyButton("L\u01B0u");
		btnSua.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (kiemTraNhap()) {
					String hoten = txtTennhanvien.getText();
					Date ngaysinh = new Date(dateModel.getYear() - 1900, dateModel.getMonth(), dateModel.getDay());
					String diachi = txtDiachi.getText();
					String cmnd = txtCmnd.getText().trim();
					boolean chucvu = false;
					boolean gioitinh = radNam.isSelected();

					NhanVien ql = new NhanVien("NV001");
					String sdt = txtSdt.getText().trim();
					int ca = 0;
					if (cboCa.getSelectedItem() == "1") {
						ca = 1;
					} else if (cboCa.getSelectedItem() == "2") {
						ca = 2;
					}

					NhanVienDao nvdao = new NhanVienDao();
					NhanVien nv = new NhanVien(manv, hoten, ngaysinh, diachi, cmnd, chucvu, gioitinh, ca, ql, sdt);
					nvdao.suaNhanVien(nv);
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Sửa thông tin nhân viên " + manv + " thành công!","Thông báo",JOptionPane.PLAIN_MESSAGE);
					GUI_CNNhanVien.deleteTableCNNV();
					GUI_CNNhanVien.updateTableCNNV();
					GUI_DSNhanVien.deleteTableDSNV();
					GUI_DSNhanVien.updateTableDSNV();
					GUI_CNNhanVien.tblDSNhanVien.clearSelection();
				}
			}
		});
		panel_3.add(btnSua);
		btnSua.setText("Sửa");
		btnSua.setForeground(new Color(255, 255, 255));
		btnSua.setBorder(null);
		btnSua.setColor(new Color(255, 255, 0));
		btnSua.setBackground(Color.ORANGE);
		btnSua.setBorderColor(new Color(255, 140, 0));
		btnSua.setColorOver(new Color(255, 255, 0));
		btnSua.setColorClick(new Color(210, 105, 30));
		btnThemNV = btnSua;
		btnThemNV.setFont(new Font("Segoe UI", Font.BOLD, 20));

		MyButton btnThoat = new MyButton("Thoát");
		btnThoat.setColorClick(new Color(178, 34, 34));
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

		Component horizontalStrut_1 = Box.createHorizontalStrut(28);
		horizontalStrut_1.setBounds(456, 38, 28, 443);
		getContentPane().add(horizontalStrut_1);
		setText();
	}

	@SuppressWarnings("deprecation")
	public void setText() {
		Object[] r = GUI_CNNhanVien.getSelectedRow();

		if (r == null) {

		} else {
			manv = (String) r[0];
			txtTennhanvien.setText((String) r[1]);
			txtCmnd.setText((String) r[4]);
			txtSdt.setText((String) r[9]);
			txtDiachi.setText((String) r[3]);

			if (r[6] == "Nam") {
				radNam.setSelected(true);
			} else {
				radNu.setSelected(true);
			}

			String date = (String) r[2];
			String[] date1 = date.split("-");

			Date ngaysinh = new Date(Integer.parseInt(date1[2]) - 1900, Integer.parseInt(date1[1]) - 1,
					Integer.parseInt(date1[0]));

			dateModel.setValue(ngaysinh);

			cboCa.setSelectedItem(r[7].toString());

		}
	}

	@SuppressWarnings({ "deprecation", "unused" })
	public boolean kiemTraNhap() {
		if (txtTennhanvien.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập tên nhân viên!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!txtTennhanvien.getText().trim().matches("([A-Z]{1}(\\D*)\s)+([A-Z]{1}\\D*)")) {
			JOptionPane.showMessageDialog(null, "Tên nhân viên viết hoa chữ cái đầu! Ví dụ: Nguyễn Thị Lan","Thông báo",JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (txtCmnd.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập chứng minh nhân dân của nhân viên!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!txtCmnd.getText().trim().matches("([0-9]{9}([0-9]{3})?)")) {
			JOptionPane.showMessageDialog(null, "Chứng minh nhân dân của nhân viên là 9 hoặc 12 chữ số !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (txtSdt.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập số điện thoại của nhân viên!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!txtSdt.getText().trim().matches("[0-9]{10}")) {
			JOptionPane.showMessageDialog(null, "Số điện thoại của nhân viên của nhân viên gồm 10 chữ số !","Thông báo",JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		if (txtDiachi.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập địa chỉ của nhân viên!","Thông báo",JOptionPane.ERROR_MESSAGE);
			return false;
		}

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
			JOptionPane.showMessageDialog(null, "Nhân viên chưa đủ 18 tuổi !");
			return false;

		}
		return true;
	}
}
