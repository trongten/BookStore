package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Others.MyButton;
import dao.KhachHangDao;
import entity.KhachHang;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ScrollPaneConstants;
/**
 * Giao diện lấy khách hàng
 * @author Phan Võ Trọng
 *
 */
public class GUI_LayKH extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private static DefaultTableModel dataModel;
	private JTable tblDSKhachHang;
	private JTextField txtTim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GUI_LayKH dialog = new GUI_LayKH();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GUI_LayKH() {
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 856, 571);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		String[] title = { "Mã khách hàng", "Tên khách hàng", "Số điện thoại" };
		contentPanel.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane(
				tblDSKhachHang = new JTable(dataModel = new DefaultTableModel(title, 0)),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblDSKhachHang.setAutoCreateRowSorter(true);
		tblDSKhachHang.setRowHeight(30);
		tblDSKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDSKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPanel.add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPanel.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Danh Sách Khách Hàng");
		lblNewLabel.setForeground(new Color(25, 25, 112));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblNewLabel.setBackground(Color.WHITE);
		panel_1.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel.add(panel_2, BorderLayout.SOUTH);

		txtTim = new JTextField();
		txtTim.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtTim.getText().equals("Tìm khách hàng...")) {
					txtTim.setText("");
					txtTim.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtTim.getText().isEmpty()) {
					txtTim.setForeground(Color.GRAY);
					txtTim.setText("Tìm khách hàng...");
				}

			}
		});
		panel_2.add(txtTim);
		txtTim.setColumns(40);

		MyButton btnTìm = new MyButton("Tìm");
		btnTìm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhachHangDao khdao = new KhachHangDao();

				ArrayList<KhachHang> list = khdao.timKhachHangTheoTuKhoa(txtTim.getText());
				deleteTableDSKhachHang();
				updateTableTimDSKhachHang(list);

			}
		});
		btnTìm.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_2.add(btnTìm);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.WHITE);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		MyButton okButton = new MyButton("Chọn");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = tblDSKhachHang.getSelectedRow();
				GUI_LapPhieuDat.setThongTinKhachHang(dataModel.getValueAt(row, 0).toString(),
						dataModel.getValueAt(row, 1).toString(), dataModel.getValueAt(row, 2).toString());

				setVisible(false);

			}
		});
		okButton.setBackground(Color.WHITE);
		okButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		MyButton cancelButton = new MyButton("Hủy");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		cancelButton.setBackground(Color.WHITE);
		cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		//updateTableDSKhachHang();
	}

	public static void deleteTableDSKhachHang() {

		int dem = dataModel.getRowCount();
		for (int i = 0; i < dem; i++) {
			dataModel.removeRow(0);
		}
	}

	public static void updateTableTimDSKhachHang(ArrayList<KhachHang> list) {
		ArrayList<KhachHang> dskh = list;
		for (KhachHang i : dskh) {
			dataModel.addRow(new Object[] { i.getMakh(), i.getTenkhachhang(), i.getSodienthoai() });
		}
	}

	public static void updateTableDSKhachHang() {
		KhachHangDao khdao = new KhachHangDao();
		ArrayList<KhachHang> dskh = khdao.layDsKhachHang();
		for (KhachHang i : dskh) {
			dataModel.addRow(new Object[] { i.getMakh(), i.getTenkhachhang(), i.getSodienthoai() });
		}

	}
}
