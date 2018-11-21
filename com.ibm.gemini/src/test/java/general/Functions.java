package general;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Functions {
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row = null;
	public static XSSFCell cell = null;
	public static String[][] excelData = null;
	static int colCount=0;
	static int rowCount=0;
	public static String path = System.getProperty("user.dir");
	public static int lastRow ;
	static String[][] excelData2;
	//Reading Excel File and get the data
		public static String[][] excel_Files(String sheetname) throws Exception {

			try {
				String FilePath =path+ "/src/test/resources/dataSource/Test data.xlsx";
				FileInputStream finputStream = new FileInputStream(new File(FilePath)); // in put file path

				workbook = new XSSFWorkbook(finputStream); // reading work book
				sheet = workbook.getSheet(sheetname); // reading work sheet
				colCount = sheet.getRow(0).getPhysicalNumberOfCells(); //get coloumns count

				//System.out.println("Columns"+ colCount);

				rowCount = sheet.getPhysicalNumberOfRows(); // get rows count
				lastRow=rowCount;
				//System.out.println("Rows"+ rowCount);

				excelData = new String[rowCount][colCount]; // two dim array for data storing

				for(int Nrow = 0; Nrow<rowCount; Nrow++) {
					row = sheet.getRow(Nrow);
					for(int Ncolumn =0; Ncolumn<colCount ; Ncolumn++) {
						cell = sheet.getRow(Nrow).getCell(Ncolumn);
						DataFormatter df = new DataFormatter(); // data formater for accepting null values in the sheet
						excelData[Nrow][Ncolumn] = df.formatCellValue(cell);
					}
				}
			}catch(Exception e) {}

			return excelData;
		}

		//read excel sheet based on sheetName
		public static void readExcel(String sheetName) {

			try {
				excel_Files(sheetName);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(" Excel data reading failed...");
			}
		}

}
