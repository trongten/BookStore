package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
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
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import Others.MyButton;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.NhanVienDao;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import java.awt.SystemColor;
import javax.swing.ListSelectionModel;

/**
 * Giao Diện thống kê doanh thu theo nhân viên
 * 
 * @author Võ Phước Lưu - Phan Võ Trọng
 *
 */
public class GUI_ThongKeNhanVien extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static DefaultTableModel dataModelDSNV;

	private JTable tblDSHD;
	private static JTable tblDSNhanVien;
	private static JLabel txtTongDoanhThu;
	private static JComboBox<String> cboThang;
	private JComboBox<String> cboNam;
	private static ChartPanel panel_3;
	private JButton btnThongKe;
	private JPanel pnlPlaceHolder;
	private static DefaultTableModel dataModelDSHD;
	private static String thang;
	private static String nam;
	private static JSplitPane splitPane;
	private static double tongDoanhThu;
	private JSplitPane splitPaneDSNV;
	private JPanel pnlDanhSach;
	private JLabel lblDSHD;
	private JLabel lblTongDoanhThu;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public GUI_ThongKeNhanVien() {
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
		pnlDanhSach.setBackground(SystemColor.control);
		splitPane.setLeftComponent(pnlDanhSach);
		pnlDanhSach.setLayout(new GridLayout(2, 1, 0, 0));

		splitPaneDSNV = new JSplitPane();
		splitPaneDSNV.setOrientation(JSplitPane.VERTICAL_SPLIT);
		// Xử lý cho giao diện nhân viên
		try {
			GUIChinh_NhanVien.nv.getManhanvien().toString();
		} catch (Exception e) {
			GUIChinh_QuanLy.nv.getManhanvien().toString();
			pnlDanhSach.add(splitPaneDSNV);
		}

		JLabel lblNewLabel_3 = new JLabel("Danh sách nhân viên");
		lblNewLabel_3.setBackground(new Color(255, 255, 255));
		lblNewLabel_3.setForeground(new Color(25, 25, 112));
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 15));
		splitPaneDSNV.setLeftComponent(lblNewLabel_3);

		JScrollPane scrollPane = new JScrollPane();
		splitPaneDSNV.setRightComponent(scrollPane);

		String[] title = { "Mã nhân viên", "Tên nhân viên" };
		@SuppressWarnings("unused")
		JScrollPane scrollPane_1 = new JScrollPane(
				tblDSNhanVien = new JTable(dataModelDSNV = new DefaultTableModel(title, 0) {
					boolean[] canEdit = new boolean[] { false, false };

					public boolean isCellEditable(int row, int column) {
						return canEdit[column];
					}
				}), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblDSNhanVien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String manv = dataModelDSNV.getValueAt(tblDSNhanVien.getSelectedRow(), 0).toString();
				// thay đổi label cho phù hợp
				if (thang == "'%'" && nam != "'%'")// tháng tất cả và năm ko tất cả
					lblDSHD.setText("Danh sách hóa đơn của " + manv + " đã lập trong " + nam);
				if (thang != "'%'" && nam == "'%'")// tháng ko tất cả và năm tất cả
					lblDSHD.setText(
							"Danh sách hóa đơn của " + manv + " đã lập trong " + thang + " trong tất cả các năm");
				if (thang == "'%'" && nam == "'%'")// tháng tất cả và năm tất cả
					lblDSHD.setText("Danh sách hóa đơn của " + manv + " đã lập trong toàn bộ thời gian");
				if (thang != "'%'" && nam != "'%'")// tháng ko tất cả và năm ko tất cả
					lblDSHD.setText("Danh sách hóa đơn của " + manv + " đã lập trong " + thang + "/" + nam);
				HoaDonDao hddao = new HoaDonDao();
				ArrayList<HoaDon> dshd = hddao.layDsHoaDonTheoKhoangThoiGian(thang, nam);
				ArrayList<HoaDon> dshdnv = new ArrayList<HoaDon>();

				int y = dshd.size();
				for (int i = 0; i < y; i++) {
					if ((dshd.get(i).getNhanvien().getManhanvien().equalsIgnoreCase(manv))) {
						dshdnv.add(dshd.get(i));
					}
				}
				splitPane.remove(panel_3);
				panel_3 = new ChartPanel(createChartNhanVien());
				splitPane.setRightComponent(panel_3);
				tongDoanhThu = 0.0;
				dshdnv.forEach(hd -> {
					tongDoanhThu = tongDoanhThu + hd.getTongtienhoadon();
				});
				lblTongDoanhThu.setText("Tổng doanh thu của " + manv + " :");
				lblTongDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 20));
				txtTongDoanhThu.setText(new DecimalFormat("###,###,###").format(tongDoanhThu) + " đồng");
				tongDoanhThu = 0.0;
				deleteTabelDSHoaDon();
				updateTableDSHoaDon(dshdnv);
			}
		});
		tblDSNhanVien.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tblDSNhanVien.setAutoCreateRowSorter(true);
		tblDSNhanVien.setRowHeight(30);
		tblDSNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		scrollPane.setViewportView(tblDSNhanVien);

		JSplitPane splitPaneDSHD = new JSplitPane();
		splitPaneDSHD.setOrientation(JSplitPane.VERTICAL_SPLIT);

		pnlDanhSach.add(splitPaneDSHD);

		lblDSHD = new JLabel("Danh sách hóa đơn");
		lblDSHD.setOpaque(true);
		lblDSHD.setBackground(new Color(255, 255, 255));
		lblDSHD.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblDSHD.setForeground(new Color(25, 25, 112));
		splitPaneDSHD.setLeftComponent(lblDSHD);

		String[] title2 = { "Mã HD", "Tên Khách hàng", "Số điện thoại", "Ngày Lập", "Tổng tiền" };
		JScrollPane scrollPaneDSHD = new JScrollPane(
				tblDSHD = new JTable(dataModelDSHD = new DefaultTableModel(title2, 0) {
					boolean[] canEdit = new boolean[] { false, false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return canEdit[column];
					}
				}), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblDSHD.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDSHD.setAutoCreateRowSorter(true);
		scrollPaneDSHD.setBackground(Color.WHITE);
		scrollPaneDSHD.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		splitPaneDSHD.setRightComponent(scrollPaneDSHD);

		tblDSHD.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tblDSHD.setRowHeight(30);
		tblDSHD.setFont(new Font("Segoe UI", Font.PLAIN, 15));

		scrollPaneDSHD.setViewportView(tblDSHD);

		pnlPlaceHolder = new JPanel();
		splitPane.setRightComponent(pnlPlaceHolder);
		pnlPlaceHolder.setLayout(new BorderLayout(0, 0));

		JLabel lblPlaceHolderPicture = new JLabel("");
		lblPlaceHolderPicture.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlaceHolderPicture.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlaceHolderPicture.setOpaque(true);
		lblPlaceHolderPicture
				.setIcon(new ImageIcon(GUI_ThongKeNhanVien.class.getResource("/images/thongkebieudo.jpg")));
		pnlPlaceHolder.add(lblPlaceHolderPicture);

		Panel panel_6 = new Panel();
		panel_6.setBackground(Color.WHITE);
		pnl.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel pnlChonThoiGian = new JPanel();
		pnlChonThoiGian.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		pnlChonThoiGian.setOpaque(false);
		pnlChonThoiGian.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Ch\u1ECDn th\u1EDDi gian", TitledBorder.LEADING, TitledBorder.TOP,
				new Font("Segoe UI", Font.PLAIN, 15), new Color(0, 0, 0)));
		panel_6.add(pnlChonThoiGian);

		JLabel lblThng = new JLabel("Tháng");
		lblThng.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		pnlChonThoiGian.add(lblThng);

		cboThang = new JComboBox<String>();
		cboThang.setBackground(Color.WHITE);
		cboThang.setFont(new Font("Segoe UI", Font.BOLD, 15));
		cboThang.setModel(new DefaultComboBoxModel<String>(new String[] { "Tất cả" }));
		pnlChonThoiGian.add(cboThang);

		JLabel lblNm = new JLabel("   năm");
		lblNm.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		pnlChonThoiGian.add(lblNm);

		cboNam = new JComboBox<String>();
		cboNam.setBackground(Color.WHITE);
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
		cboNam.setFont(new Font("Segoe UI", Font.BOLD, 15));
		cboNam.setModel(new DefaultComboBoxModel<String>(new String[] { "Tất cả" }));
		pnlChonThoiGian.add(cboNam);
		// chỉ load lên nhưng năm nào có hóa đơn
		ArrayList<String> dsnam = new HoaDonDao().layDsNamLapHoaDon();
		int size = dsnam.size();
		for (int i = 0; i < size; i++) {
			cboNam.addItem(dsnam.get(i));
		}
		btnThongKe = new MyButton("Thống kê");
		btnThongKe.setBackground(new Color(176, 224, 230));
		btnThongKe.setBorder(new CompoundBorder(null, UIManager.getBorder("EditorPane.border")));
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				thang = cboThang.getSelectedItem().toString() == "Tất cả" ? "'%'"
						: cboThang.getSelectedItem().toString();
				nam = cboNam.getSelectedItem().toString() == "Tất cả" ? "'%'" : cboNam.getSelectedItem().toString();

				HoaDonDao hddao = new HoaDonDao();
				ArrayList<HoaDon> dshd = hddao.layDsHoaDonTheoKhoangThoiGian(thang, nam);
				NhanVienDao nvdao = new NhanVienDao();
				ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
				panel_3 = new ChartPanel(createChart());
				tongDoanhThu = 0.0;

				dshd.forEach(hd -> {
					tongDoanhThu = tongDoanhThu + hd.getTongtienhoadon();
				});// tính tổng doanh thu của hóa đơn lập trong thời gian đó

				txtTongDoanhThu.setText(new DecimalFormat("###,###,###").format(tongDoanhThu) + " đồng");
				tongDoanhThu = 0.0;
				splitPane.remove(pnlPlaceHolder);
				splitPane.setRightComponent(panel_3);
				lblDSHD.setText("Danh sách hóa đơn");
				hddao.layDsMaNVTheoKhoangThoiGian(thang, nam).forEach(n -> {
					dsnv.add(nvdao.timNhanVien(n));
				});
				lblTongDoanhThu.setText("Tổng doanh thu:");
				lblTongDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 25));
				// Phần code dành cho giao diện nhân viên
				try {
					pnlDanhSach.setLayout(new GridLayout(1, 1, 0, 0));
					String manv = GUIChinh_NhanVien.nv.getManhanvien().toString();
					ArrayList<HoaDon> dshdnv = new ArrayList<HoaDon>();
					int y = dshd.size();
					for (int i = 0; i < y; i++) {
						if ((dshd.get(i).getNhanvien().getManhanvien().equalsIgnoreCase(manv))) {
							dshdnv.add(dshd.get(i));
						}
					}
					dshdnv.forEach(hd -> {
						tongDoanhThu = tongDoanhThu + hd.getTongtienhoadon();
					});
					if (thang == "'%'" && nam != "'%'")// tháng tất cả và năm ko tất cả
						lblDSHD.setText("Danh sách hóa đơn của " + manv + " đã lập trong " + nam);
					if (thang != "'%'" && nam == "'%'")// tháng ko tất cả và năm tất cả
						lblDSHD.setText(
								"Danh sách hóa đơn của " + manv + " đã lập trong " + thang + " trong tất cả các năm");
					if (thang == "'%'" && nam == "'%'")// tháng tất cả và năm tất cả
						lblDSHD.setText("Danh sách hóa đơn của " + manv + " đã lập trong toàn bộ thời gian");
					if (thang != "'%'" && nam != "'%'")// tháng ko tất cả và năm ko tất cả
						lblDSHD.setText("Danh sách hóa đơn của " + manv + " đã lập trong " + thang + "/" + nam);

					txtTongDoanhThu.setText(new DecimalFormat("###,###,###").format(tongDoanhThu) + " đồng");
					deleteTabelDSHoaDon();
					updateTableDSHoaDon(dshdnv);
					splitPane.remove(panel_3);
					panel_3 = new ChartPanel(createChartNhanVien());
					splitPane.setRightComponent(panel_3);
					updateGDNV();

				} catch (Exception er) {
					deleteTabelDSNhanVien();
					updateTabelDSNhanVien(dsnv);
					deleteTabelDSHoaDon();
					updateTableDSHoaDon(dshd);
					pnlDanhSach.setLayout(new GridLayout(2, 1, 0, 0));// set lại bố cục cho layout
				}

			}
		});
		btnThongKe.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		pnlChonThoiGian.add(btnThongKe);

		JPanel pntThongKeDoanhThu = new JPanel();
		pntThongKeDoanhThu.setOpaque(false);
		panel_6.add(pntThongKeDoanhThu);
		pntThongKeDoanhThu.setLayout(new GridLayout(0, 2, 0, 0));

		lblTongDoanhThu = new JLabel("Tổng doanh thu:");
		lblTongDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		pntThongKeDoanhThu.add(lblTongDoanhThu);

		txtTongDoanhThu = new JLabel();
		txtTongDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		pntThongKeDoanhThu.add(txtTongDoanhThu);

		JLabel lblTieuDe = new JLabel("Thông kê số lượng hóa đơn của nhân viên");
		lblTieuDe.setOpaque(true);
		lblTieuDe.setBackground(new Color(255, 255, 255));
		lblTieuDe.setForeground(new Color(25, 25, 112));
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 30));
		pnlChart.add(lblTieuDe, BorderLayout.NORTH);

	}

	/**
	 * Xử lý giao diện nhân viên
	 */
	public static void updateGDNV() {
		HoaDonDao hddao = new HoaDonDao();
		ArrayList<HoaDon> dshd = hddao.layDsHoaDonTheoKhoangThoiGian(thang, nam);
		ArrayList<HoaDon> dshdnv = new ArrayList<HoaDon>();
		int y = dshd.size();
		// xử lý giao diện nhân viên
		try {
			for (int i = 0; i < y; i++) {
				if ((dshd.get(i).getNhanvien().getManhanvien()
						.equalsIgnoreCase(GUIChinh_NhanVien.nv.getManhanvien().toString()))) {
					dshdnv.add(dshd.get(i));
				}
			}
		} catch (Exception er) {
			// đúng thì vô đây chạy tiếp thồi :v
		}
		panel_3 = new ChartPanel(createChartNhanVien());
		splitPane.setRightComponent(panel_3);
		tongDoanhThu = 0.0;
		dshdnv.forEach(hd -> {
			tongDoanhThu = tongDoanhThu + hd.getTongtienhoadon();
		});
		txtTongDoanhThu.setText(new DecimalFormat("###,###,###").format(tongDoanhThu) + " đồng");
		tongDoanhThu = 0.0;
		deleteTabelDSHoaDon();
		updateTableDSHoaDon(dshdnv);
	}

	/**
	 * Tạo biểu đồ bình thường
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static JFreeChart createChart() {
		String title = "BIỂU ĐỒ";// thay đổi tiêu đề biểu đồ cho phù hợp
		if (thang == "'%'" && nam != "'%'")// tháng tất cả và năm ko tất cả
			title = "BIỂU ĐỒ DOANH THU CỦA TRONG NĂM " + nam;
		if (thang != "'%'" && nam == "'%'")// tháng ko tất cả và năm tất cả
			title = "BIỂU ĐỒ DOANH THU TRONG THÁNG " + thang + " CỦA TẤT CẢ CÁC NĂM";
		if (thang == "'%'" && nam == "'%'")// tháng tất cả và năm tất cả
			title = "BIỂU ĐỒ DOANH THU TOÀN BỘ THỜI GIAN";
		if (thang != "'%'" && nam != "'%'")// tháng ko tất cả và năm ko tất cả
			title = "BIỂU ĐỒ DOANH THU TRONG THÁNG " + thang + "/" + nam;
		JFreeChart barChart = ChartFactory.createBarChart(title, "Ngày", "Doanh thu", createDataset(),
				PlotOrientation.VERTICAL, false, false, false);
		CategoryItemRenderer renderer = ((CategoryPlot) barChart.getPlot()).getRenderer();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setItemLabelFont(new Font("Segoe UI", Font.BOLD, 15));
		ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
		renderer.setBasePositiveItemLabelPosition(position);

		// set the color (r,g,b) or (r,g,b,testPDF)
		Color color = new Color(79, 129, 189);
		renderer.setSeriesPaint(0, color);
		return barChart;
	}

	/**
	 * Tạo biểu đồ với theo nhân viên
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static JFreeChart createChartNhanVien() {
		String manv;
		int r = tblDSNhanVien.getSelectedRow();
		// xử lý lỗi chưa chọn nhưng phải load
		try {
			manv = GUIChinh_NhanVien.nv.getManhanvien();
		} catch (Exception er) {
			if (r == -1)
				manv = "";
			else
				manv = dataModelDSNV.getValueAt(r, 0).toString();
		}
		String title = "BIỂU ĐỒ";// thay đổi tiêu đề biểu đồ cho phù hợp
		if (thang == "'%'" && nam != "'%'")// tháng tất cả và năm ko tất cả
			title = "BIỂU ĐỒ DOANH THU CỦA " + manv + " TRONG NĂM " + nam;
		if (thang != "'%'" && nam == "'%'")// tháng ko tất cả và năm tất cả
			title = "BIỂU ĐỒ DOANH THU CỦA " + manv + " TRONG THÁNG " + thang + " CỦA TẤT CẢ CÁC NĂM";
		if (thang == "'%'" && nam == "'%'")// tháng tất cả và năm tất cả
			title = "BIỂU ĐỒ DOANH THU CỦA " + manv + " TOÀN BỘ THỜI GIAN";
		if (thang != "'%'" && nam != "'%'")// tháng ko tất cả và năm ko tất cả
			title = "BIỂU ĐỒ DOANH THU CỦA " + manv + " TRONG THÁNG " + thang + "/" + nam;
		JFreeChart barChart = ChartFactory.createBarChart(title, "Ngày", "Doanh thu", createDatasetMaNhanVien(),
				PlotOrientation.VERTICAL, false, false, false);
		//CategoryPlot plot = barChart.getCategoryPlot();
		//BarRenderer renderer = (BarRenderer) plot.getRenderer();
		CategoryItemRenderer renderer = ((CategoryPlot) barChart.getPlot()).getRenderer();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setItemLabelFont(new Font("Segoe UI", Font.BOLD, 15));
		ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
		renderer.setBasePositiveItemLabelPosition(position);
		// set the color (r,g,b) or (r,g,b,testPDF)
		Color color = new Color(79, 129, 189);
		renderer.setSeriesPaint(0, color);
		
		return barChart;
	}

	// tạo dataset
	private static CategoryDataset createDataset() {

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<Double> dth = new HoaDonDao().layDsDoanhThuTheoThoiGian(thang, nam);
		List<String> ngay = new HoaDonDao().layDsNgayCoDoanhThuTheoThoiGian(thang, nam);
		int size = dth.size();
		for (int i = 0; i < size; i++) {
			dataset.addValue(dth.get(i), "Ngày", ngay.get(i));
		}
		return dataset;
	}

	// tạo dataset có mã nhân viên
	private static CategoryDataset createDatasetMaNhanVien() {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int r = tblDSNhanVien.getSelectedRow();

		String manv;
		try {
			manv = dataModelDSNV.getValueAt(r, 0).toString();
		} catch (Exception err) {
			try {
				manv = GUIChinh_NhanVien.nv.getManhanvien().toString();
			} catch (Exception er) {
				manv = "%";
			}
		}
		List<Double> dth = new HoaDonDao().layDsDoanhThuCuaNhanVienTheoThoiGian(manv, thang, nam);
		List<String> ngay = new HoaDonDao().layDsNgayCoDoanhThuCuaNhanVienTheoThoiGian(manv, thang, nam);
		int size = dth.size();
		for (int i = 0; i < size; i++) {
			dataset.addValue(dth.get(i), "Ngày", ngay.get(i));
		}
		return dataset;
	}

	/**
	 * tải bản danh sách nhân viên theo ArrayList<NhanVien>
	 * 
	 * @param dsnv
	 */
	public static void updateTabelDSNhanVien(ArrayList<NhanVien> dsnv) {
		for (NhanVien t : dsnv) {
			dataModelDSNV.addRow(new Object[] { t.getManhanvien(), t.getHoten() });
		}
	}

	/**
	 * Xóa bảng danh sách nhân viên
	 */
	public static void deleteTabelDSNhanVien() {
		int y = dataModelDSNV.getRowCount();
		for (int i = 0; i < y; i++) {
			dataModelDSNV.removeRow(0);
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

	public static void updateTableDSHoaDon(ArrayList<HoaDon> list) {

		for (HoaDon i : list) {
			KhachHangDao khdao = new KhachHangDao();
			KhachHang kh = new KhachHang();
			if (khdao.timKhachHang(i.getKhachhang().getMakh()) == null) {

				dataModelDSHD.addRow(new Object[] { i.getMahoadon(), " ", " ",
						i.getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
								+ i.getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[1] + " "
								+ i.getNgaylaphoadon().toString().split("-")[2].split(" ")[0] + "-"
								+ i.getNgaylaphoadon().toString().split("-")[1] + "-"
								+ i.getNgaylaphoadon().toString().split("-")[0],
						new DecimalFormat("###,###,###").format(i.getTongtienhoadon()) });
			} else {
				kh = khdao.timKhachHang(i.getKhachhang().getMakh());
				dataModelDSHD.addRow(new Object[] { i.getMahoadon(), kh.getTenkhachhang(), kh.getSodienthoai(),
						i.getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[0] + "h"
								+ i.getNgaylaphoadon().toString().split("-")[2].split(" ")[1].split(":")[1] + " "
								+ i.getNgaylaphoadon().toString().split("-")[2].split(" ")[0] + "-"
								+ i.getNgaylaphoadon().toString().split("-")[1] + "-"
								+ i.getNgaylaphoadon().toString().split("-")[0],
						new DecimalFormat("###,###,###").format(i.getTongtienhoadon()) });
			}
		}
	}

}
