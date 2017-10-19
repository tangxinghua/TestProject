/**  
 * @Title: TestPoi.java
 * @Package com.test.excel
 * @Description: TODO
 * @author tangxinghua tangxh@live.com
 * @version V1.0  
 */
package com.test.excel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.test.TestBean;
import com.test.TestBean2;

/**
 * @Description: TODO
 */
public class TestPoi<T> {

	/**
	 * @Description: TODO
	 * @param @param args
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) {
		String xlsPath = "D://Downloads//Temp//temp_poi.xls";
		createExcel(xlsPath);
		// List<TestBean> list = loadExcelInfo(xlsPath);
		// updateExcelnfo(xlsPath);

		TestPoi<TestBean2> ex = new TestPoi<TestBean2>();
		String[] headers = { "图书编号", "图书名称", "图书作者", "图书价格", "图书ISBN", "图书出版社", "封面图片" };
		List<TestBean2> dataset = new ArrayList<TestBean2>();
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
					"C://Users//tangxh//Pictures//Saved Pictures//1.jpg"));
			byte[] buf = new byte[bis.available()];
			while ((bis.read(buf)) != -1) {
				//
			}
			dataset.add(new TestBean2(1, "jsp", "leno", 300.33f, "1234567", "清华出版社", buf));
			dataset.add(new TestBean2(2, "java编程思想", "brucl", 300.33f, "1234567", "阳光出版社", buf));
			dataset.add(new TestBean2(3, "DOM艺术", "lenotang", 300.33f, "1234567", "清华出版社", buf));
			dataset.add(new TestBean2(4, "c++经典", "leno", 400.33f, "1234567", "清华出版社", buf));
			dataset.add(new TestBean2(5, "c#入门", "leno", 300.33f, "1234567", "汤春秀出版社", buf));

			OutputStream out = new FileOutputStream("D://Downloads//Temp//temp_poi2.xls");
			ex.exportExcel("测试POI导出EXCEL文档", headers, dataset, out, "yyyy-MM-dd");
			out.close();
			System.out.println("excel导出成功！");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @Description: TODO
	 * @param @param xlsPath
	 * @return void
	 * @throws
	 */
	@SuppressWarnings("deprecation")
	public static void createExcel(String xlsPath) {
		// 创建HSSFWorkbook对象(excel的文档对象)
		HSSFWorkbook wkb = new HSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wkb.createSheet("成绩表");
		// 设置缺省列高
		sheet.setDefaultRowHeightInPoints(20);
		// 设置缺省列宽
		sheet.setDefaultColumnWidth((short) 20);

		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1 = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row1.createCell(0);
		// 设置指定列的列宽，256 * 50这种写法是因为width参数单位是单个字符的256分之一
		sheet.setColumnWidth(cell.getColumnIndex(), 256 * 50);
		// 设置行高为60px;
		row1.setHeightInPoints(60);
		// 设置单元格内容
		cell.setCellValue("学员考试成绩一览表");

		// 设置样式
		HSSFCellStyle cellStyle = wkb.createCellStyle();
		// 设置单元格的横向和纵向对齐方式，具体参数就不列了，参考HSSFCellStyle
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		/*
		 * 设置单元格的填充方式，以及前景颜色和背景颜色 三点注意： 1.如果需要前景颜色或背景颜色，一定要指定填充方式，两者顺序无所谓；
		 * 2.如果同时存在前景颜色和背景颜色，前景颜色的设置要写在前面； 3.前景颜色不是字体颜色。
		 */
		// 设置填充方式(填充图案)
		cellStyle.setFillPattern(HSSFCellStyle.DIAMONDS);
		// 设置前景色
		cellStyle.setFillForegroundColor(HSSFColor.RED.index);
		// 设置背景颜色
		cellStyle.setFillBackgroundColor(HSSFColor.LIGHT_YELLOW.index);
		// 设置单元格底部的边框及其样式和颜色
		// 这里仅设置了底边边框，左边框、右边框和顶边框同理可设
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_SLANTED_DASH_DOT);
		cellStyle.setBottomBorderColor(HSSFColor.DARK_RED.index);
		// 设置日期型数据的显示样式
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
		// 将样式应用于单元格
		cell.setCellStyle(cellStyle);
		// 将样式应用到行，但有些样式只对单元格起作用
		// row1.setRowStyle(cellStyle);

		// 设置字体样式
		HSSFFont fontStyle = wkb.createFont();
		// 设置字体样式
		fontStyle.setFontName("宋体");
		// 设置字体高度
		fontStyle.setFontHeightInPoints((short) 20);
		// 设置字体颜色
		fontStyle.setColor(HSSFColor.BLUE.index);
		// 设置粗体
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 设置斜体
		fontStyle.setItalic(true);
		// 设置下划线
		fontStyle.setUnderline(HSSFFont.U_SINGLE);
		// 将字体对象赋值给单元格样式对象
		cellStyle.setFont(fontStyle);
		// 将单元格样式应用于单元格
		cell.setCellStyle(cellStyle);
		// 将样式应用到行，但有时样式会被单元格覆盖掉
		// row1.setRowStyle(cellStyle);

		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("leno");
		// 将注释应用于单元格
		cell.setCellComment(comment);

		// 生成一个样式
		HSSFCellStyle style = wkb.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = wkb.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);

		// 生成并设置另一个样式
		HSSFCellStyle style2 = wkb.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = wkb.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 在sheet里创建第二行
		HSSFRow row2 = sheet.createRow(1);
		// 创建单元格并设置单元格内容
		row2.createCell(0).setCellValue("姓名");
		row2.createCell(1).setCellValue("班级");
		row2.createCell(2).setCellValue("笔试成绩");
		row2.createCell(3).setCellValue("机试成绩");
		// 设置样式
		HSSFCellStyle rowStyle = wkb.createCellStyle();
		// 将字体对象赋值给样式对象
		rowStyle.setFont(fontStyle);

		// 在sheet里创建第三行
		HSSFRow row3 = sheet.createRow(2);
		row3.createCell(0).setCellValue("李明");
		row3.createCell(1).setCellValue("As178");
		row3.createCell(2).setCellValue(87);
		row3.createCell(3).setCellValue(78);

		// 输出Excel文件
		/*
		 * OutputStream output = response.getOutputStream(); response.reset();
		 * response.setHeader("Content-disposition",
		 * "attachment; filename=details.xls");
		 * response.setContentType("application/msexcel"); wkb.write(output);
		 * output.close();
		 */
		File file = new File(xlsPath);
		try {
			wkb.write(file);
		} catch (IOException e) {
			e.printStackTrace();
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
		FileInputStream fileIn = null;
		Workbook wb = null;
		try {
			fileIn = new FileInputStream(xlsPath);
			// 根据指定的文件输入流导入Excel从而产生Workbook对象
			wb = new HSSFWorkbook(fileIn);
			// 获取Excel文档中的第一个表单
			Sheet sheet = wb.getSheetAt(0);
			// 对Sheet中的每一行进行迭代
			for (Row r : sheet) {
				// 如果当前行的行号（从0开始）未达到2（第三行）则从新循环
				if (r.getRowNum() < 1) {
					continue;
				}
				// 创建实体类
				TestBean bean = new TestBean();
				// 取出当前行第1个单元格数据，并封装在info实体stuName属性上
				bean.setStuName(r.getCell(0).getStringCellValue());
				bean.setClassName(r.getCell(1).getStringCellValue());
				bean.setW_score(r.getCell(2).getNumericCellValue());
				bean.setR_score(r.getCell(3).getNumericCellValue());
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != fileIn) {
				try {
					fileIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
		// TODO Auto-generated method stub

	}

	/**
	 * @Description: 利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param pattern
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 * @return void
	 * @throws
	 */
	@SuppressWarnings("deprecation")
	private void exportExcel(String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("leno");

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}

		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					if (value instanceof Boolean) {
						boolean bValue = (Boolean) value;
						textValue = "男";
						if (!bValue) {
							textValue = "女";
						}
					} else if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
					} else if (value instanceof byte[]) {
						// 有图片时，设置行高为60px;
						row.setHeightInPoints(60);
						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(i, (short) (35.7 * 80));
						// sheet.autoSizeColumn(i);
						byte[] bsValue = (byte[]) value;
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6,
								index);
						anchor.setAnchorType(2);
						patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					} else {
						// 其它数据类型都当作字符串简单处理
						textValue = value.toString();
					}
					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(textValue);
							HSSFFont font3 = workbook.createFont();
							font3.setColor(HSSFColor.BLUE.index);
							richString.applyFont(font3);
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}

		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
