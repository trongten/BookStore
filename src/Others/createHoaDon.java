package Others;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;

import javax.swing.JTable;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;

import dao.KhachHangDao;
import dao.NhanVienDao;
import entity.HoaDon;
import entity.KhachHang;
import javax.print.attribute.standard.MediaTray;
import javax.print.attribute.standard.SheetCollate;
import javax.print.attribute.standard.Sides;

import com.gnostice.pdfone.PdfDocument;
import com.gnostice.pdfone.PdfPageSize;
import com.gnostice.pdfone.PdfPrinter;

/**
 * Tạo hóa đơn
 * 
 * @author Võ Phước Lưu, Phan Võ Trọng
 *
 */
public class createHoaDon {
	// tạo source ký tự có dấu
	private static final char[] SOURCE_CHARACTERS = { 'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ',
			'Ù', 'Ú', 'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ă', 'ă',
			'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ',
			'ẫ', 'Ậ', 'ậ', 'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế',
			'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ',
			'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ',
			'Ừ', 'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự', };

	private static final char[] DESTINATION_CHARACTERS = { 'A', 'A', 'A', 'A', 'E', 'E', 'E', 'I', 'I', 'O', 'O', 'O',
			'O', 'U', 'U', 'Y', 'a', 'a', 'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y', 'A',
			'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a',
			'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
			'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
			'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U',
			'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', };

	/**
	 * Loại bỏ dấu của từ
	 * 
	 * @param ch
	 * @return
	 */
	public static char removeAccent(char ch) {
		int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
		if (index >= 0) {
			ch = DESTINATION_CHARACTERS[index];
		}
		return ch;
	}

	/**
	 * Loại bỏ dấu của chuỗi
	 * 
	 * @param str
	 * @return
	 */
	public static String removeAccent(String str) {
		StringBuilder sb = new StringBuilder(str);
		for (int i = 0; i < sb.length(); i++) {
			sb.setCharAt(i, removeAccent(sb.charAt(i)));
		}
		return sb.toString();
	}

	/**
	 * Tạo hóa đơn từ đối tượng HoaDon và bảng chi tiết hóa đơn
	 * 
	 * @param hd
	 * @param cthd
	 * @throws IOException
	 */
	public createHoaDon(HoaDon hd, JTable cthd) throws IOException {
		Document document = new Document(PageSize.A6, 5f, 5f, 5f, 5f);
		float lineSpacing;
		lineSpacing = 10f;
		Font bold = new Font(Font.COURIER, 8f, Font.BOLD);
		Font normal = new Font(Font.COURIER, 8f, Font.NORMAL);
		Font small = new Font(Font.COURIER, 7f, Font.NORMAL);
		String source = "hoadon//HoaDon" + hd.getMahoadon().toString() + ".pdf";
		try {
			PdfWriter.getInstance(document, new FileOutputStream(source));

			document.open();

			Paragraph tieude = new Paragraph(new Phrase(lineSpacing, "Nha Sach TNL", bold));
			Paragraph mota = new Paragraph("181/29,Duong 3/2 Ngo 11 Quan 10,Thanh pho Ho Chi Minh\n", small);
			tieude.setAlignment(Element.ALIGN_CENTER);
			mota.setAlignment(Element.ALIGN_CENTER);
			document.add(tieude);
			document.add(mota);

			document.add(new Chunk(new LineSeparator()));
			document.add(Chunk.NEWLINE);

			Paragraph tieudephieu = new Paragraph(new Phrase(lineSpacing, "PHIEU THANH TOAN", bold));
			tieudephieu.setAlignment(Element.ALIGN_CENTER);
			document.add(new Paragraph(tieudephieu));
			document.add(Chunk.NEWLINE);

			List list = new List(List.UNORDERED);
			list.add(new ListItem("Ma hoa don:" + hd.getMahoadon(), normal));
			list.add(new ListItem("Ngay lap: " + hd.getNgaylaphoadon(), normal));
			list.add(new ListItem(
					"Thu ngan: "
							+ removeAccent(new NhanVienDao().timNhanVien(hd.getNhanvien().getManhanvien()).getHoten()),
					normal));

			document.add(list);

			document.add(Chunk.NEWLINE);

			try {
				System.out.println("Hóa đơn có khách hàng");
				KhachHang kh = new KhachHangDao().timKhachHang(hd.getKhachhang().getMakh().toString());
				document.add(new Paragraph("Ten khach hang: " + removeAccent(kh.getTenkhachhang()), normal));
				document.add(new Paragraph("SDT: " + kh.getSodienthoai(), normal));

			} catch (Exception e) {
				System.out.println("Hóa đơn không có khách hàng");
			}

			document.add(new Chunk(new LineSeparator()));

			document.add(new Paragraph(
					new Phrase(10f, "THONG TIN HOA DON\n", FontFactory.getFont(FontFactory.COURIER_BOLD, 10f))));

			PdfPTable table = new PdfPTable(4);

			table.setWidthPercentage(100f);

			PdfPCell header1 = new PdfPCell(new Paragraph("Ma San Pham", normal));
			PdfPCell header2 = new PdfPCell(new Paragraph("Ten San Pham", normal));
			PdfPCell header3 = new PdfPCell(new Paragraph("So luong", normal));
			PdfPCell header4 = new PdfPCell(new Paragraph("Thanh tien", normal));
			header1.setBorder(Rectangle.NO_BORDER);
			header2.setBorder(Rectangle.NO_BORDER);
			header3.setBorder(Rectangle.NO_BORDER);
			header3.setHorizontalAlignment(Element.ALIGN_CENTER);
			header4.setBorder(Rectangle.NO_BORDER);

			table.addCell(header1);
			table.addCell(header2);
			table.addCell(header3);
			table.addCell(header4);

			for (int i = 0; i < cthd.getRowCount(); i++) {
				PdfPCell masp = new PdfPCell(new Paragraph(cthd.getValueAt(i, 0).toString(), normal));
				PdfPCell tensp = new PdfPCell(new Paragraph(removeAccent(cthd.getValueAt(i, 1).toString()), normal));
				PdfPCell soluong = new PdfPCell(new Paragraph(cthd.getValueAt(i, 2).toString(), normal));
				PdfPCell thanhtien = new PdfPCell(new Paragraph(cthd.getValueAt(i, 3).toString(), normal));
				masp.setBorder(Rectangle.NO_BORDER);
				tensp.setBorder(Rectangle.NO_BORDER);
				soluong.setBorder(Rectangle.NO_BORDER);
				soluong.setHorizontalAlignment(Element.ALIGN_CENTER);
				thanhtien.setBorder(Rectangle.NO_BORDER);

				table.addCell(masp);
				table.addCell(tensp);
				table.addCell(soluong);
				table.addCell(thanhtien);
			}
			// Thêm data vào bảng.
			document.add(table);
			document.add(Chunk.NEWLINE);
			try {
				String discount = String
						.valueOf(new DecimalFormat("###,###,###").format(hd.getTongtienhoadon() * 0.025));
				new KhachHangDao().timKhachHang(hd.getKhachhang().getMakh().toString());
				document.add(new Paragraph("Discount (Member): " + discount, normal));

			} catch (Exception e) {
				// TODO: handle exception
			}
			String thue = String.valueOf(new DecimalFormat("###,###,###").format(hd.getTongtienhoadon() * 0.05));
			document.add(new Paragraph("Thue VAT: " + thue, normal));
			String tongtien = String
					.valueOf(new DecimalFormat("###,###,###").format((int) Math.floor(hd.getTongtienhoadon())));
			document.add(new Paragraph("Tong tien hoa don: " + tongtien, bold));
			new readNumber();
			String chu = readNumber.soThanhChu(String.valueOf((int) Math.floor(hd.getTongtienhoadon())));
			document.add(new Paragraph("So tien bang chu: " + removeAccent(chu) + " dong", normal));
			// đóng file
			document.close();
			print(source);

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// in hóa đơn ra giấy bằng printer
	void print(String source) {
		PdfDocument d = new PdfDocument();
		try {
			// Tải hóa đơn
			d.load(source);

			// Tạo máy in
			PdfPrinter printer = new PdfPrinter();

			// chỉ định hóa đơn để in ra
			printer.setDocument(d);

			// Chọn tên máy in
			printer.setSelectedPrinterName(
					// Name of first printer
					printer.getAvailablePrinterNames()[0]);

			// Set margins
			printer.setPageMargins(
					// Left, top, right, bottom margins
					new double[] { 1, 0.5, 1, 0.5 },
					// đơn vị đo
					PdfPrinter.MU_INCHES);

			// Thiết lập size giấy
			printer.setPageSize(PdfPageSize.A6);
			// Thiết lập size Chiều
			printer.setOrientation(PdfPrinter.Orientation_PORTRAIT);
			// Thiết lập size số lượng trang
			printer.setPageRange("1-8");
			// Thiết lập số lượng bản in
			printer.setCopies(1);
			// Specify scaling
			printer.setPageScale(PdfPrinter.SCALE_REDUCE_TO_PRINTER_MARGINS);
			// Specify how page of different copies need to be collated
			printer.setPrintSheetCollate(SheetCollate.COLLATED);
			// Specify paper bin
			printer.setPrintMediaTray(MediaTray.SIDE);
			// Cài đặt thứ tự in
			printer.setReverse(true);
			// Specify which sides of paper need to be printed on
			printer.setPrintSides(Sides.TWO_SIDED_SHORT_EDGE);

			// Hiển thị Print dialog
			printer.showCrossPlatformPrintDialog();
		} catch (Exception ex1) {
			System.out.println(ex1.getMessage());
		} finally {
			try {
				// Release I/O resources
				d.close();
			} catch (Exception ex2) {
				System.out.println(ex2.getMessage());
			}
		}
	}
}