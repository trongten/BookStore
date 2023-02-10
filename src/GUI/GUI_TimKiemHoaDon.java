package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Others.DateLabelFormatter;
import Others.MyButton;
import dao.HoaDonDao;
import entity.HoaDon;
/**
 * 
 * @author Phan Võ Trọng - Võ Phước Lưu
 *
 */
public class GUI_TimKiemHoaDon extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaHD;
	private JTextField txtTenNhanVien;
	private UtilDateModel dateModel;
	private JDatePanelImpl datePanel;
	@SuppressWarnings("unused")
	private Date date;
	private Properties dateProperties;
	JDatePickerImpl dateNgayLap;

	/**
	 * Create the panel.
	 */
	public GUI_TimKiemHoaDon() {
		setTitle("Tìm kiếm hóa đơn");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(230, 230, 250));
		setBackground(Color.WHITE);
		setAlwaysOnTop(true);setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(401, 295);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 2, 0, 40));

		JLabel lblNewLabel_1 = new JLabel("Mã hóa đơn:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_1);

		txtMaHD = new JTextField();
		txtMaHD.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_2.add(txtMaHD);
		txtMaHD.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Tên Nhân viên Lập:");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_2);

		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_2.add(txtTenNhanVien);
		txtTenNhanVien.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Ngày lập hóa đơn:");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_4);

		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel.add(panel_3, BorderLayout.SOUTH);

		MyButton btnTm = new MyButton("Tìm");
		btnTm.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String mahd = txtMaHD.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtMaHD.getText().trim() + "%";
				String tennv = txtTenNhanVien.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtTenNhanVien.getText().trim() + "%";

				Date ngaylap = new Date(dateModel.getYear()-1900, dateModel.getMonth(), dateModel.getDay());
				if (dateNgayLap.getJFormattedTextField().getValue() == null) {
					ngaylap = null;
				}

				System.out.println(ngaylap);
				HoaDonDao hddao = new HoaDonDao();
				ArrayList<HoaDon> list = hddao.timHoaDonNangCao(mahd, tennv, ngaylap);
				try {
					GUIChinh_QuanLy.changeLayerPanelDSHD();
				} catch (Exception er) {
					GUIChinh_NhanVien.changeLayerPanelDSHD();
				}
				GUI_DSHoaDon.deleteTableDSHD();
				GUI_DSHoaDon.updateTableTimDSHoaDon(list);
			}
		});
		panel_3.add(btnTm);

		dateModel = new UtilDateModel();
		dateProperties = new Properties();
		dateProperties.put("text.today", "Today");
		dateProperties.put("text.month", "Month");
		dateProperties.put("text.year", "Year");
		datePanel = new JDatePanelImpl(dateModel, dateProperties);

		dateNgayLap = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		dateNgayLap.getJFormattedTextField().setBackground(Color.WHITE);
		dateNgayLap.setBackground(Color.WHITE);
		dateNgayLap.setOpaque(false);
		panel_2.add(dateNgayLap);

		MyButton btnThoat = new MyButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_3.add(btnThoat);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut, BorderLayout.WEST);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_1, BorderLayout.EAST);

		Component verticalStrut = Box.createVerticalStrut(12);
		panel.add(verticalStrut, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(null);
		getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Tìm kiếm Hóa đơn");
		lblNewLabel.setBorder(null);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		panel_1.add(lblNewLabel);
	}
}
