package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path="/Users/subashzara/Desktop/OpenCart/OpenCart001/testData/Opencart_LoginData1.xlsx";//taking xl file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");	
		int totalcols=xlutil.getCellCount("Sheet1",1);
				
		String logindata[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password
		
		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				logindata[i-1][j]= xlutil.getCellData("Sheet1",i, j);  //Array start from [0][0] so -1 	//InExcel [1][0] Zero row will be Names like username email
			}
		}
	return logindata;//returning two dimension array 	"public void verify_loginDDT(String #email(1,0), String #pwd(1,1), String #exp(1,2))" 
				
	}
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
}