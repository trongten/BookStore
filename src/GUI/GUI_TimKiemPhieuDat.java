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
import dao.PhieuDatTruocDao;
import entity.PhieuDatTruoc;

public class GUI_TimKiemPhieuDat extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaPD;
	private JTextField txtTenNhanVien;
	@SuppressWarnings("unused")
	private Date date;
	private UtilDateModel dateModel;
	private Properties dateProperties;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl dateNgayNhan;
	private JDatePickerImpl dateNgayLap;
	private UtilDateModel dateNgayNhanModel;

	/**
	 * Create the panel.
	 */
	public GUI_TimKiemPhieuDat() {
		setTitle("Tìm Phiếu đặt");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(230, 230, 250));
		setBackground(new Color(230, 230, 250));
		setAlwaysOnTop(true);setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(401, 342);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 2, 0, 40));

		JLabel lblNewLabel_1 = new JLabel("Mã phiếu đặt :");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_1);

		txtMaPD = new JTextField();
		txtMaPD.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_2.add(txtMaPD);
		txtMaPD.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Tên khách hàng :");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_2);

		txtTenNhanVien = new JTextField();
		txtTenNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_2.add(txtTenNhanVien);
		txtTenNhanVien.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Ngày lập phiếu :");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_4);

		long millis = System.currentTimeMillis();
		date = new Date(millis);
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

		JLabel lblNewLabel_4_1 = new JLabel("Ngày nhận hàng :");
		lblNewLabel_4_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panel_2.add(lblNewLabel_4_1);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel.add(panel_3, BorderLayout.SOUTH);

		MyButton btnTim = new MyButton("Tìm");
		btnTim.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnTim.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String mapd = txtMaPD.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtMaPD.getText().trim() + "%";
				String tennv = txtTenNhanVien.getText().trim().equalsIgnoreCase("") ? "%"
						: "%" + txtTenNhanVien.getText().trim() + "%";
				Date ngaylap = new Date(dateModel.getYear()-1900, dateModel.getMonth(), dateModel.getDay());

				Date ngaynhan = new Date(dateNgayNhanModel.getYear()-1900, dateNgayNhanModel.getMonth(),
						dateNgayNhanModel.getDay());
				if (dateNgayLap.getJFormattedTextField().getValue() == null) {
					ngaylap = null;
				}
				if (dateNgayNhan.getJFormattedTextField().getValue() == null) {
					ngaynhan = null;
				}
				PhieuDatTruocDao pddao = new PhieuDatTruocDao();
				ArrayList<PhieuDatTruoc> list = pddao.timPhieuDatTruocNangCao(mapd, tennv, ngaylap, ngaynhan);
				try {
					GUIChinh_QuanLy.changeLayerPanelDSPD();
				} catch (Exception er) {

					GUIChinh_NhanVien.changeLayerPanelDSPD();
				}
				GUI_DSPhieuDat.deleteTableDSPD();
				GUI_DSPhieuDat.updateTableTimDSPD(list);
			}
		});

		dateNgayNhanModel = new UtilDateModel();
		dateProperties = new Properties();
		dateProperties.put("text.today", "Today");
		dateProperties.put("text.month", "Month");
		dateProperties.put("text.year", "Year");
		datePanel = new JDatePanelImpl(dateNgayNhanModel, dateProperties);
		dateNgayNhan = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		dateNgayNhan.getJFormattedTextField().setBackground(Color.WHITE);
		dateNgayNhan.setBackground(Color.WHITE);
		dateNgayNhan.setOpaque(false);
		panel_2.add(dateNgayNhan);

		panel_3.add(btnTim);

		MyButton btnThoat = new MyButton("Thoát");
		btnThoat.setFont(new Font("Segoe UI", Font.BOLD, 20));
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

		JLabel lblNewLabel = new JLabel("Tìm kiếm Phiếu đặt");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		panel_1.add(lblNewLabel);

	}
}
