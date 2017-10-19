/**  
 * @Title: TestJxl.java
 * @Package com.test.excel
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.test.TestBean;

/**
 * @Description: TODO
 */
public class TestJxl {

	/**
	 * @Description: TODO
	 * @param @param args
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) {
		String xlsPath = "D://Downloads//Temp//temp_jxl.xls";
		createExcel(xlsPath);
		List<TestBean> list = loadExcelInfo(xlsPath);
		updateExcelnfo(xlsPath);
	}

	/**
	 * @Description: TODO
	 * @param
	 * @return void
	 * @throws
	 */
	public static void createExcel(String xlsPath) {
		/*
		 * 获得输出流，该输出流的输出介质是客户端浏览器，创建可写入的Excel工作薄，且内容将写入到输出流，并通过输出流输出给客户端浏览
		 * OutputStream output = response.getOutputStream(); response.reset();
		 * response.setHeader("Content-disposition",
		 * "attachment; filename=temp.xls");
		 * response.setContentType("application/msexcel"); WritableWorkbook wk =
		 * Workbook.createWorkbook(output);
		 */

		File file = new File(xlsPath);
		WritableWorkbook wwb = null;
		try {
			wwb = Workbook.createWorkbook(file);

			// 创建可写入的Excel工作表
			WritableSheet sheet = wwb.createSheet("成绩表", 0);

			// 指定第i+1行的高度
			sheet.setRowView(0, 200);
			// 指定第i+1列的宽度
			sheet.setColumnView(0, 30);
			// 把单元格（column, row）到单元格（column1, row1）进行合并。
			sheet.mergeCells(0, 0, 4, 0);

			// 创建WritableFont 字体对象，参数依次表示黑体、字号12、粗体、非斜体、不带下划线、亮蓝色
			WritableFont titleFont = new WritableFont(WritableFont.createFont("黑体"), 12, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, Colour.LIGHT_BLUE);
			// 创建WritableCellFormat对象，将该对象应用于单元格从而设置单元格的样式
			WritableCellFormat titleFormat = new WritableCellFormat();
			// 设置字体格式
			titleFormat.setFont(titleFont);
			// 设置文本水平居中对齐
			titleFormat.setAlignment(Alignment.CENTRE);
			// 设置文本垂直居中对齐
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			// 设置背景颜色
			titleFormat.setBackground(Colour.GRAY_25);
			// 设置自动换行
			titleFormat.setWrap(true);
			// 添加Label对象，参数依次表示在第一列，第一行，内容，使用的格式
			Label lab_00 = new Label(0, 0, "学员考试成绩一览表", titleFormat);
			// 将定义好的Label对象添加到工作表上，这样工作表的第一列第一行的内容为‘学员考试成绩一览表’并应用了titleFormat定义的样式
			sheet.addCell(lab_00);

			WritableCellFormat cloumnTitleFormat = new WritableCellFormat();
			cloumnTitleFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD, false));
			cloumnTitleFormat.setAlignment(Alignment.CENTRE);
			Label lab_01 = new Label(0, 1, "姓名", cloumnTitleFormat);
			Label lab_11 = new Label(1, 1, "班级", cloumnTitleFormat);
			Label lab_21 = new Label(2, 1, "笔试成绩", cloumnTitleFormat);
			Label lab_31 = new Label(3, 1, "上机成绩", cloumnTitleFormat);
			Label lab_41 = new Label(4, 1, "考试日期", cloumnTitleFormat);
			sheet.addCell(lab_01);
			sheet.addCell(lab_11);
			sheet.addCell(lab_21);
			sheet.addCell(lab_31);
			sheet.addCell(lab_41);

			sheet.addCell(new Label(0, 2, "李明"));
			sheet.addCell(new Label(1, 2, "As178"));
			// 定义数字格式
			NumberFormat nf = new NumberFormat("0.00");
			WritableCellFormat wcf = new WritableCellFormat(nf);
			// 类似于Label对象，区别Label表示文本数据，Number表示数值型数据
			Number numlab_22 = new Number(2, 2, 78, wcf);
			sheet.addCell(numlab_22);

			sheet.addCell(new Number(3, 2, 87, new WritableCellFormat(new NumberFormat("#.##"))));

			// 定义日期格式
			DateFormat df = new DateFormat("yyyy-MM-dd hh:mm:ss");
			// 创建WritableCellFormat对象
			WritableCellFormat datewcf = new WritableCellFormat(df);
			// 类似于Label对象，区别Label表示文本数据，DateTime表示日期型数据
			DateTime dtLab_42 = new DateTime(4, 2, new Date(), datewcf);
			sheet.addCell(dtLab_42);

			// 将定义的工作表输出到之前指定的介质中
			wwb.write();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 操作完成时，关闭对象，释放占用的内存空间
			if (null != wwb) {
				try {
					wwb.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @Description: TODO
	 * @param @param xlsPath
	 * @param @return
	 * @return List<TestBean>
	 * @throws
	 */
	public static List<TestBean> loadExcelInfo(String xlsPath) {
		List<TestBean> list = new ArrayList<TestBean>();

		FileInputStream fis = null;
		Workbook wk = null;
		try {
			// 导入已存在的Excel文件，获得只读的工作薄对象
			fis = new FileInputStream(xlsPath);
			wk = Workbook.getWorkbook(fis);
			// 获取第一张Sheet表
			Sheet sheet = wk.getSheet(0);
			// 获取总行数
			int rowNum = sheet.getRows();
			// 从数据行开始迭代每一行
			for (int i = 2; i < rowNum; i++) {
				TestBean bean = new TestBean();
				// getCell(column,row)，表示取得指定列指定行的单元格（Cell）
				// getContents()获取单元格的内容，返回字符串数据。适用于字符型数据的单元格
				// 使用实体类封装单元格数据
				bean.setStuName(sheet.getCell(0, i).getContents());
				bean.setClassName(sheet.getCell(1, i).getContents());
				// 判断单元格的类型，单元格主要类型LABEL、NUMBER、DATE
				if (sheet.getCell(2, i).getType() == CellType.NUMBER) {

					// 转化为数值型单元格
					NumberCell numCell = (NumberCell) sheet.getCell(2, i);
					// NumberCell的getValue()方法取得单元格的数值型数据
					bean.setW_score(numCell.getValue());

				}
				if (sheet.getCell(3, i).getType() == CellType.NUMBER) {
					NumberCell numCell = (NumberCell) sheet.getCell(3, i);
					bean.setR_score(numCell.getValue());
				}

				if (sheet.getCell(4, i).getType() == CellType.DATE) {
					DateCell dateCell = (DateCell) sheet.getCell(4, i);
					// DateCell的getDate()方法取得单元格的日期型数据
					bean.setDate(dateCell.getDate());
				}
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != wk) {
				wk.close();
			}
		}
		return list;
	}

	/**
	 * @Description: TODO
	 * @param @param xlsPath
	 * @return void
	 * @throws
	 */
	public static void updateExcelnfo(String xlsPath) {
		File file = new File(xlsPath);
		Workbook wk = null;
		WritableWorkbook wwb = null;
		try {
			// 导入已存在的Excel文件，获得只读的工作薄对象
			wk = Workbook.getWorkbook(file);
			// 根据只读的工作薄对象（wk）创建可写入的Excel工作薄对象
			wwb = Workbook.createWorkbook(file, wk);
			// 读取第一张工作表
			WritableSheet sheet = wwb.getSheet(0);
			// 获得要编辑的单元格对象
			WritableCell cell = sheet.getWritableCell(1, 2);
			// 判断单元格的类型, 做出相应的转化
			if (cell.getType() == CellType.LABEL) {
				Label lable = (Label) cell;
				// 修改单元格的内容
				lable.setString("As179");
			}
			wwb.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != wk) {
				wk.close();
			}
			if (null != wwb) {
				try {
					wwb.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
