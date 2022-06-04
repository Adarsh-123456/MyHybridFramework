package DriverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import CommonFunctions.FunctionLibrary;
import Constant.AppUtil;
import Utilities.ExcelFileUtil;

public class DriverScript extends AppUtil{
	String inputpath ="D:\\Selenium930Batch\\Hybrid_Framework\\TestInput\\HybridDta.xlsx";
	String outputpath ="D:\\Selenium930Batch\\Hybrid_Framework\\TestOutput\\HybridTResults.xlsx";
	String TCSheet ="MasterTCS";
	String TSSheet ="TestSteps";
	@Test
	public void startTest()throws Throwable
	{
		boolean res = false;
		String tcres="";
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int TCCount =xl.rowCount(TCSheet);
		int TSCount =xl.rowCount(TSSheet);
		Reporter.log(TCCount+"    "+TSCount,true);
		for(int i=1;i<=TCCount;i++)
		{
			String executionstatus=xl.getCellData(TCSheet, i, 2);
			if(executionstatus.equalsIgnoreCase("Y"))
			{
				String tcid =xl.getCellData(TCSheet, i, 0);
				for(int j=1;j<=TSCount;j++)
				{
					String tsid =xl.getCellData(TSSheet, j, 0);
					if(tcid.equalsIgnoreCase(tsid))
					{
						String keyword=xl.getCellData(TSSheet, j, 3);
						if(keyword.equalsIgnoreCase("Admin Login"))
						{
							String para1=xl.getCellData(TSSheet, j, 5);
							String para2=xl.getCellData(TSSheet, j, 6);
								res=FunctionLibrary.verifyLogin(para1, para2);
						}
						else if(keyword.equalsIgnoreCase("New Branch"))
						{
							String para1=xl.getCellData(TSSheet, j, 5);
							String para2=xl.getCellData(TSSheet, j, 6);
							String para3=xl.getCellData(TSSheet, j, 7);
							String para4=xl.getCellData(TSSheet, j, 8);
							String para5=xl.getCellData(TSSheet, j, 9);
							String para6=xl.getCellData(TSSheet, j, 10);
							String para7=xl.getCellData(TSSheet, j, 11);
							String para8=xl.getCellData(TSSheet, j, 12);
							String para9=xl.getCellData(TSSheet, j, 13);
							FunctionLibrary.clickBraches();
							res=FunctionLibrary.verifynewBranch(para1, para2, para3, para4, para5, para6, para7, para8, para9);
						}
						else if(keyword.equalsIgnoreCase("Update branch"))
					{
						String para1=xl.getCellData(TSSheet, j, 5);
						String para2=xl.getCellData(TSSheet, j, 6);
						String para6=xl.getCellData(TSSheet, j, 10);
						FunctionLibrary.clickBraches();
						res=FunctionLibrary.verifybranchUpdation(para1, para2, para6);
					}
					else if(keyword.equalsIgnoreCase("Admin Logout"))
					{
						res=FunctionLibrary.verifyLogout();
					}
						String tsres="";
						if(res)
						{
							//write as pass into TSSheet
							tsres="Pass";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
						}
						else
						{
							//write as Fail into TSSheet
							tsres="Fail";
							xl.setCellData(TSSheet, j, 4, tsres, outputpath);
						}
						tcres=tsres;
				}
			}
				//write as pass/fail into tc sheet
				xl.setCellData(TCSheet, i, 3, tcres, outputpath);
	     }
			else
			{
				//write as blockedin status cell wchich are flagged to N
				xl.setCellData(TCSheet, i, 3, "BLOCKED", outputpath);
			}
		
}
}
}
