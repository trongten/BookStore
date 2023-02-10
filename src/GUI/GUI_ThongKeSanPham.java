package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import Others.MyButton;
import dao.CTHoaDonDao;
import dao.HoaDonDao;
import dao.KhachHangDao;
import entity.HoaDon;
import entity.KhachHang;
import entity.SanPham;
import javax.swing.ListSelectionModel;

/**
 * Giao Diện thống kê doanh thu theo nhân viên
 * 
 * @author Võ Phước Lưu - Phan Võ Trọng
 *
 */
public class GUI_ThongKeSanPham extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTable tblDSSP;
	private static JComboBox<String> cboThang;
	private JComboBox<String> cboNam;
	private JButton btnThongKe;
	private JPanel pnlPlaceHolder;
	private static DefaultTableModel dataModelDSSP;
	private static String thang;
	private static String nam;
	private static JSplitPane splitPane;
	private JSplitPane splitPaneDSNV;
	private JPanel pnlDanhSach;
	private JLabel lblDSSP;
	private static JComboBox<String> comboBox;
	private JTable tblDSHD;

	private JLabel lblDSHD;

	private static DefaultTableModel dataModelDSHD;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public GUI_ThongKeSanPham() {
		setLayout(new GridLayout(1, 0, 0, 0));

		JPanel pnlChart = new JPanel();
		add(pnlChart);
		pnlChart.setLayout(new BorderLayout(0, 0));

		JPanel pnl = new JPanel();
		pnlChart.add(pnl, BorderLayout.CENTER);
		pnl.setLayout(new BorderLayout(0, 0));

		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.75);
		splitPane.setOneTouchExpandable(true);
		pnl.add(splitPane);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Danh sách nhân viên");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(25, 25, 112));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		panel_1.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("Thống kê");
		panel_2.add(btnNewButton);

		pnlDanhSach = new JPanel();
		splitPane.setLeftComponent(pnlDanhSach);
		pnlDanhSach.setLayout(new GridLayout(2, 1, 0, 0));

		splitPaneDSNV = new JSplitPane();
		splitPaneDSNV.setOrientation(JSplitPane.VERTICAL_SPLIT);
		JLabel lblNewLabel_3 = new JLabel("Danh sách nhân viên");
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		lblNewLabel_3.setForeground(new Color(25, 25, 112));
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 15));
		splitPaneDSNV.setLeftComponent(lblNewLabel_3);

		JScrollPane scrollPane = new JScrollPane();
		splitPaneDSNV.setRightComponent(scrollPane);

		JSplitPane splitPaneDSSP = new JSplitPane();
		splitPaneDSSP.setOrientation(JSplitPane.VERTICAL_SPLIT);

		pnlDanhSach.add(splitPaneDSSP);

		lblDSSP = new JLabel("Danh sách sản phẩm");
		lblDSSP.setOpaque(true);
		lblDSSP.setBackground(Color.WHITE);
		lblDSSP.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblDSSP.setForeground(new Color(25, 25, 112));
		splitPaneDSSP.setLeftComponent(lblDSSP);

		String[] title2 = { "Mã SP", "Tên Sản Phẩm", "Số lượng bán ra" };
		JScrollPane scrollPaneDSSP = new JScrollPane(
				tblDSSP = new JTable(dataModelDSSP = new DefaultTableModel(title2, 0) {
					boolean[] canEdit = new boolean[] { false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return canEdit[column];
					}
				}), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblDSSP.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDSSP.setAutoCreateRowSorter(true);
		scrollPaneDSSP.setBackground(Color.WHITE);
		tblDSSP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int r = tblDSSP.getSelectedRow();
				String masp = tblDSSP.getValueAt(r, 0).toString();
				System.out.println(masp);
				if (thang == "'%'" && nam != "'%'")// tháng tất cả và năm ko tất cả
					lblDSHD.setText("Danh sách hóa đơn có chứa sản phẩm " + masp + " đã lập trong " + nam);
				if (thang != "'%'" && nam == "'%'")// tháng ko tất cả và năm tất cả
					lblDSHD.setText("Danh sách hóa đơn có chứa sản phẩm " + masp + " đã lập trong " + thang
							+ " trong tất cả các năm");
				if (thang == "'%'" && nam == "'%'")// tháng tất cả và năm tất cả
					lblDSHD.setText("Danh sách hóa đơn có chứa sản phẩm " + masp + " đã lập trong toàn bộ thời gian");
				if (thang != "'%'" && nam != "'%'")// tháng ko tất cả và năm ko tất cả
					lblDSHD.setText(
							"Danh sách hóa đơn có chứa sản phẩm " + masp + " đã lập trong " + thang + "/" + nam);

				ArrayList<HoaDon> dshd = new HoaDonDao().layDsHoaDonCuaSanPhamTheoThoiGian(masp, thang, nam);
				deleteTabelDSHoaDon();
				updateTableDSHoaDon(masp, dshd);
			}
		});
		splitPaneDSSP.setRightComponent(scrollPaneDSSP);
		String[] title = { "Mã HD", "Tên Khách hàng", "Số điện thoại", "Ngày Lập", "Số lượng trong hóa đơn" };
		JScrollPane scrollPane_1 = new JScrollPane(
				tblDSHD = new JTable(dataModelDSHD = new DefaultTableModel(title, 0) {
					boolean[] canEdit = new boolean[] { false, false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return canEdit[column];
					}
				}), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblDSHD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDSHD.setAutoCreateRowSorter(true);
		scrollPane_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		tblDSSP.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tblDSSP.setRowHeight(30);
		tblDSSP.setFont(new Font("Tahoma", Font.PLAIN, 15));

		scrollPaneDSSP.setViewportView(tblDSSP);

		JSplitPane splitPaneDSHD = new JSplitPane();
		splitPaneDSHD.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pnlDanhSach.add(splitPaneDSHD);

		lblDSHD = new JLabel("Danh sách hóa đơn");
		lblDSHD.setBackground(Color.WHITE);
		lblDSHD.setOpaque(true);
		lblDSHD.setForeground(new Color(25, 25, 112));
		lblDSHD.setFont(new Font("Segoe UI", Font.BOLD, 15));
		splitPaneDSHD.setLeftComponent(lblDSHD);

		splitPaneDSHD.setRightComponent(scrollPane_1);

		tblDSHD.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tblDSHD.setRowHeight(30);
		tblDSHD.setFont(new Font("Tahoma", Font.PLAIN, 15));

		scrollPane_1.setViewportView(tblDSHD);

		pnlPlaceHolder = new JPanel();
		splitPane.setRightComponent(pnlPlaceHolder);
		pnlPlaceHolder.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setIcon(new ImageIcon(GUI_ThongKeSanPham.class.getResource("/images/thongkebieudo.jpg")));
		pnlPlaceHolder.add(lblNewLabel_4);

		JPanel pnlThongKe = new JPanel();
		pnlThongKe.setBackground(Color.WHITE);
		pnlThongKe.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		pnlThongKe.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Thống kê",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 15), new Color(0, 0, 0)));
		pnl.add(pnlThongKe,BorderLayout.SOUTH);

		JLabel lblNewLabel_2 = new JLabel("Theo");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		pnlThongKe.add(lblNewLabel_2);

		comboBox = new JComboBox<String>();
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Segoe UI", Font.BOLD, 16));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Doanh Thu", "Số Lượng" }));
		pnlThongKe.add(comboBox);

		JLabel lblThng = new JLabel("Tháng");
		lblThng.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlThongKe.add(lblThng);

		cboThang = new JComboBox<String>();
		cboThang.setBackground(Color.WHITE);
		cboThang.setFont(new Font("Segoe UI", Font.BOLD, 16));
		cboThang.setModel(new DefaultComboBoxModel<String>(new String[] { "Tất cả" }));
		pnlThongKe.add(cboThang);

		JLabel lblNm = new JLabel("   năm");
		lblNm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pnlThongKe.add(lblNm);

		cboNam = new JComboBox<String>();
		// chỉ load lên những tháng nào có hóa đơn
		cboNam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nam = cboNam.getSelectedItem().toString() == "Tất cả" ? "'%'"
						: cboNam.getSelectedItem().toString();
				ArrayList<String> dsthang = new HoaDonDao().layDsThangLapHoaDon(nam);
				cboThang.setModel(new DefaultComboBoxModel<String>(new String[] { "Tất cả" }));
				for (int i = 0; i < dsthang.size(); i++)
					cboThang.addItem(dsthang.get(i));
			}
		});
		cboNam.setEditable(true);
		cboNam.setFont(new Font("Segoe UI", Font.BOLD, 16));
		cboNam.setModel(new DefaultComboBoxModel<String>(new String[] { "Tất cả" }));
		pnlThongKe.add(cboNam);
		// chỉ load lên nhưng năm nào có hóa đơn
		ArrayList<String> dsnam = new HoaDonDao().layDsNamLapHoaDon();
		int size = dsnam.size();
		for (int i = 0; i < size; i++) {
			cboNam.addItem(dsnam.get(i));
		}

		btnThongKe = new MyButton("Thống kê");
		btnThongKe.setBackground(new Color(173, 216, 230));
		btnThongKe.setBorder(new CompoundBorder(UIManager.getBorder("EditorPane.border"), null));
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thang = cboThang.getSelectedItem().toString() == "Tất cả" ? "'%'"
						: cboThang.getSelectedItem().toString();
				nam = cboNam.getSelectedItem().toString() == "Tất cả" ? "'%'" : cboNam.getSelectedItem().toString();

				splitPane.remove(pnlPlaceHolder);
				JPanel pnlChart = createChart();
				splitPane.setRightComponent(pnlChart);

				ArrayList<SanPham> dssp = new CTHoaDonDao().layDSThongTinSanPhamDaBanTheoThoiGian(thang, nam);
				deleteTableDSSanPham();
				updateTableDSSanPham(dssp);
			}
		});
		btnThongKe.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		pnlThongKe.add(btnThongKe);

		JLabel lblNewLabel_1 = new JLabel("Thông kê số lượng Sản Phẩm");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setForeground(new Color(25, 25, 112));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 30));
		pnlChart.add(lblNewLabel_1, BorderLayout.NORTH);

	}

	/**
	 * Tạo biểu đồ bình thường
	 * 
	 * @return
	 */
	public static JPanel createChart() {
		String title = "BIỂU ĐỒ ";// thay đổi tiêu đề biểu đồ cho phù hợp
		if (comboBox.getSelectedIndex() == 0) {
			if (thang == "'%'" && nam != "'%'")// tháng tất cả và năm ko tất cả
				title = title + "DOANH THU CỦA TRONG NĂM " + nam;
			if (thang != "'%'" && nam == "'%'")// tháng ko tất cả và năm tất cả
				title = title + "DOANH THU TRONG THÁNG " + thang + " CỦA TẤT CẢ CÁC NĂM";
			if (thang == "'%'" && nam == "'%'")// tháng tất cả và năm tất cả
				title = title + "DOANH THU TOÀN BỘ THỜI GIAN";
			if (thang != "'%'" && nam != "'%'")// tháng ko tất cả và năm ko tất cả
				title = title + "DOANH THU TRONG THÁNG " + thang + "/" + nam;
		} else if (comboBox.getSelectedIndex() == 1) {
			if (thang == "'%'" && nam != "'%'")// tháng tất cả và năm ko tất cả
				title = title + "SỐ LƯỢNG SẢN PHẨM BÁN RA TRONG NĂM " + nam;
			if (thang != "'%'" && nam == "'%'")// tháng ko tất cả và năm tất cả
				title = title + "SỐ LƯỢNG SẢN PHẨM BÁN RA TRONG THÁNG " + thang + " CỦA TẤT CẢ CÁC NĂM";
			if (thang == "'%'" && nam == "'%'")// tháng tất cả và năm tất cả
				title = title + "SỐ LƯỢNG SẢN PHẨM BÁN RA TOÀN BỘ THỜI GIAN";
			if (thang != "'%'" && nam != "'%'")// tháng ko tất cả và năm ko tất cả
				title = title + "SỐ LƯỢNG SẢN PHẨM BÁN RA TRONG THÁNG " + thang + "/" + nam;
		}
		JFreeChart pieChart = ChartFactory.createPieChart(title, createDoanhthuDataSet());// khởi tạo
		if (comboBox.getSelectedIndex() == 0)
			pieChart = ChartFactory.createPieChart(title, createDoanhthuDataSet());
		else if (comboBox.getSelectedIndex() == 1)
			pieChart = ChartFactory.createPieChart(title, createSoLuongDataSet());
		return new ChartPanel(pieChart);
	}

	// tạo dataset của số lượng
	private static PieDataset createSoLuongDataSet() {
		final DefaultPieDataset dataset = new DefaultPieDataset();
		List<Double> soluong = new CTHoaDonDao().layDsSoLuongSanPhamTheoTheoThoiGian(thang, nam);
		List<String> dssp = new CTHoaDonDao().layDsSanPhamDaBanTheoThoiGian(thang, nam);
		int size = soluong.size();
		for (int i = 0; i < size; i++) {
			dataset.setValue(dssp.get(i), soluong.get(i));
		}
		return dataset;
	}

	// tạo dataset của doanh thu
	private static PieDataset createDoanhthuDataSet() {
		final DefaultPieDataset dataset = new DefaultPieDataset();
		List<String> dssp = new CTHoaDonDao().layDsSanPhamDaBanTheoThoiGian(thang, nam);
		List<Double> dth = new CTHoaDonDao().layDsDoanhThuCuaSanPhamTheoThoiGian(thang, nam);
		int size = dth.size();
		for (int i = 0; i < size; i++) {
			dataset.setValue(dssp.get(i), dth.get(i));
		}
		return dataset;
	}

	/**
	 * xóa săn phẩm
	 */
	public static void deleteTableDSSanPham() {
		int dem = dataModelDSSP.getRowCount();
		for (int i = 0; i < dem; i++) {
			dataModelDSSP.removeRow(0);
			;
		}
	}

	public static void updateTableDSSanPham(ArrayList<SanPham> dssp) {
		for (SanPham i : dssp) {
			dataModelDSSP.addRow(new Object[] { i.getMasanpham(), i.getTensanpham(), i.getSoluong() });
		}
	}

	/**
	 * xóa bản danh sách hóa đơn
	 */
	public static void deleteTabelDSHoaDon() {
		int y = dataModelDSHD.getRowCount();
		for (int i = 0; i < y; i++) {
			dataModelDSHD.removeRow(0);
		}
	}

	public static void updateTableDSHoaDon(String masp, ArrayList<HoaDon> i) {

		int size = i.size();
		ArrayList<Integer> soluongsanpham = new HoaDonDao().layDsSoLuongSanPhamBanRaTheoThoiGian(masp, thang, nam);
		for (int y = 0; y < size; y++) {

			KhachHangDao khdao = new KhachHangDao();
			KhachHang kh = new KhachHang();
			if (khdao.timKhachHang(i.get(y).getKhachhang().getMakh()) == null) {
				dataModelDSHD.addRow(new Object[] { i.get(y).getMahoadon(), " ", " ",
						i.get(y).getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
								+ i.get(y).getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[1] + " "
								+ i.get(y).getNgaylaphoadon().toString().split("-")[2].split(" ")[0] + "-"
								+ i.get(y).getNgaylaphoadon().toString().split("-")[1] + "-"
								+ i.get(y).getNgaylaphoadon().toString().split("-")[0],
						soluongsanpham.get(y) });
			} else {
				kh = khdao.timKhachHang(i.get(y).getKhachhang().getMakh());
				dataModelDSHD.addRow(new Object[] { i.get(y).getMahoadon(), kh.getTenkhachhang(), kh.getSodienthoai(),
						i.get(y).getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
								+ i.get(y).getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[1] + " "
								+ i.get(y).getNgaylaphoadon().toString().split("-")[2].split(" ")[0] + "-"
								+ i.get(y).getNgaylaphoadon().toString().split("-")[1] + "-"
								+ i.get(y).getNgaylaphoadon().toString().split("-")[0],
						soluongsanpham.get(y) });
			}
		}
	}

}
