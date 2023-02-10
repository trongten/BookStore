package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import Others.MyButton;
import dao.KhachHangDao;
import entity.KhachHang;

public class GUI_SuaKhachHang extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTenKhachHang;
	private JTextField txtSdt;
	private UtilDateModel dateModel;
	private Properties dateProperties;
	@SuppressWarnings("unused")
	private JDatePanelImpl datePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new GUI_SuaKhachHang().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI_SuaKhachHang() {setLocationRelativeTo(null);
		setMaximumSize(new Dimension(550, 600));
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI_SuaKhachHang.class.getResource("/images/themnhanvien.png")));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Thêm Khách hàng");
		setSize(513, 332);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		MyButton btnThemNV = new MyButton("L\u01B0u");
		getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_5 = new JLabel("Sửa Khách hàng");
		lblNewLabel_5.setForeground(new Color(0, 0, 128));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 28));
		getContentPane().add(lblNewLabel_5, BorderLayout.NORTH);
		// thanh ngày giờ
		dateModel = new UtilDateModel();
		dateProperties = new Properties();
		dateProperties.put("text.today", "Today");
		dateProperties.put("text.month", "Month");
		dateProperties.put("text.year", "Year");
		datePanel = new JDatePanelImpl(dateModel, dateProperties);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 2, 15, 30));

		JLabel lblNewLabel = new JLabel("Tên khách hàng:");
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setText("Trần Công Nguyên");
		panel.add(txtTenKhachHang);
		txtTenKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtTenKhachHang.setColumns(10);

		JLabel lblNewLabel_3_1 = new JLabel("S\u1ED1 \u0111i\u1EC7n tho\u1EA1i:");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel_3_1);
		lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));

		txtSdt = new JTextField();
		txtSdt.setText("0345789654");
		panel.add(txtSdt);
		txtSdt.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		txtSdt.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(2, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 3, 5, 0));
		MyButton btnLuu = new MyButton("L\u01B0u");
		btnLuu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				KhachHangDao khdao = new KhachHangDao();
				
				
				if (kiemTraNhap()) {
					String makh = GUI_CNKhachHang.getSelectedRow()[0].toString();
					
					String hoten = vietHoaChuDau(txtTenKhachHang.getText());
					String sodienthoai = txtSdt.getText();
					KhachHang kh = new KhachHang(makh, hoten, sodienthoai);
					khdao.suaKH(kh);
					GUI_CNKhachHang.deleteTableCNKH();
					GUI_CNKhachHang.updateTableCNKH();
					GUI_DSKhachHang.deleteTableDSKhachHang();
					GUI_DSKhachHang.updateTableDSKhachHang();
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Sửa thông tin khách hàng thành công!");

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
				
				Component horizontalStrut = Box.createHorizontalStrut(20);
				panel_2.add(horizontalStrut);
				
				Component horizontalStrut_1 = Box.createHorizontalStrut(20);
				getContentPane().add(horizontalStrut_1, BorderLayout.EAST);
				setText();
	}
	
	protected boolean kiemTraNhap() {
		if (txtTenKhachHang.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập tên khách hàng!");
			return false;
		}
		if (txtSdt.getText().trim().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Chưa nhập số điện thoại của người xin việc!");
			return false;
		} else if (!txtSdt.getText().trim().matches("^(033|034|035|036|037|038|039|056|058|059|070|076|077|078|079|081|082|083|084|085)[0-9]{7}")) {
			JOptionPane.showMessageDialog(null, "Số điện thoại của khách hàng không hợp lệ!");
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
	
	
	public void setText() {
		Object[] r = GUI_CNKhachHang.getSelectedRow();
		
		if (r == null) {

			
		} else {
			txtTenKhachHang.setText((String) r[1]);
			txtSdt.setText((String) r[2]);

		}
	}
	
}
