package net.wincn.report.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.wincn.report.exception.ReportException;

/**
 * 创建excel的类
 * 
 * @project wincn-report
 * @author gefangshuai
 * @email gefangshuai@163.com
 * @createDate 2013-6-24 下午3:32:23
 */
public class ExcelCore {
	/**
	 * 创建Excel
	 * 
	 */
	public static void createExcel(Workbook workTemp, OutputStream out, List<Label> labels) throws Exception {
		WritableWorkbook workbook = null;
		try {
			WorkbookSettings settings = new WorkbookSettings();

			settings.setWriteAccess(null);
			workbook = Workbook.createWorkbook(out, workTemp, settings);

			WritableSheet sheet = workbook.getSheet(0);
			for (Label label : labels) {
				sheet.addCell(label);
			}
			workbook.write();
		} catch (IOException e) {
			throw new ReportException("目标文件无效！", e);
		} finally {
			try {
				workbook.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取格式
	 * 
	 * @param workTemp
	 * @return
	 */
	public static CellFormat getFormats(Workbook workTemp, int c, int r) {
		Sheet sheet = workTemp.getSheet(0);
		CellFormat cellFormat = sheet.getCell(c, r).getCellFormat();// 第二行格式
		return cellFormat;
	}

	public static List<Label> getLabels(List<String[]> data) throws WriteException {
		List<Label> labels = new ArrayList<Label>();

		for (int i = 0; i < data.size(); i++) {
			for (int j = 0; j < data.get(i).length; j++) {
				Label label = new Label(j, i + 3, data.get(i)[j]);

				/* 设置黑色边框 */
				WritableCellFormat format = new WritableCellFormat();
				format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

				label.setCellFormat(format);
				labels.add(label);
			}
		}
		return labels;
	}

	public static void main(String[] args) throws Exception {
		List<String[]> data = new ArrayList<String[]>();

		String[] strs = new String[7];
		strs[0] = ("1");
		strs[1] = ("43434");
		strs[2] = ("汉龙");
		strs[3] = ("这是题名");
		strs[4] = ("20130303");
		strs[5] = ("20");
		strs[6] = ("这是备注信息");

		String[] strs1 = new String[7];
		strs1[0] = ("1");
		strs1[1] = ("43434");
		strs1[2] = ("汉龙");
		strs1[3] = ("这是题名");
		strs1[4] = ("20130303");
		strs1[5] = ("20");
		strs1[6] = ("eee");

		for (int i = 0; i < 100; i++) {
			data.add(strs);
		}

		Workbook workTemp = Workbook.getWorkbook(new File("F:/test/卷内目录.xls"));// 加载模版

		// List<CellFormat> formats = getFormats(workTemp);
		Label label = new Label(0, 1, "2004-S2003-423-1", getFormats(workTemp, 0, 1));
		List<Label> labels = getLabels(data);
		labels.add(label);

		ExcelCore.createExcel(workTemp, new FileOutputStream("F:/test/b.xls"), labels);
	}
}
