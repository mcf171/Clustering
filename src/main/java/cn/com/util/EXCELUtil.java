package cn.com.util;

import java.io.File;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class EXCELUtil

{

	public static void saveAsEXCEL(List<Object[]> results, int index, String fileName, int start) {

		try {

			WritableWorkbook book = Workbook.createWorkbook(new File("result/" + fileName + index + ".xls"));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet(" 第一页 ", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test
			int rowNumber = 1;
			int i = start;
			for (; i < results.size(); i++) {
				Object[] result = results.get(i);

				if(result.length == 2) {
					Label label1 = new Label(1, rowNumber, "" + (Integer) result[0]);
					Label label2 = new Label(2, rowNumber, "" + (Double) result[1]);
					sheet.addCell(label2);
					sheet.addCell(label1);
				}else{
					Label label1 = new Label(1, rowNumber, "" + (Double) result[0]);
					Label label2 = new Label(2, rowNumber, "" + (Integer) result[1]);
					Label label3 = new Label(3, rowNumber, "" + (Double) result[2]);
					sheet.addCell(label2);
					sheet.addCell(label3);
					sheet.addCell(label1);
				}
				

				// 将定义好的单元格添加到工作表中

				/**/ /*
						 * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义
						 * 单元格位置是第二列，第一行，值为789.123
						 */
				
				rowNumber++;

				if (rowNumber >= 60000)
					break;

			}
			// 写入数据并关闭文件
			book.write();
			book.close();
			if (i < results.size()) {
				saveAsEXCEL(results, index + 1, fileName, i);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}