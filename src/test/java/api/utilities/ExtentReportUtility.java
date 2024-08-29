package api.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportUtility implements ITestListener{


		public ExtentSparkReporter sparkReporter;  //Ui of the report
		public ExtentReports extent;               //populate comman info on report
		public ExtentTest test;                    //creating tc entires in the report and update status of the test methods

        String repName;

		public void onStart(ITestContext Testcontext) {


			/*
			 * SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.DD.HH.mm.ss");
			 *
			 * Date dt=new Date(); String currentdateTimestamp=df.format(dt);
			 */


			String timeStamp=new SimpleDateFormat("yyyy.MM.DD.HH.mm.ss").format(new Date());

			repName="Test-Report-"+timeStamp+".html";
			sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);

			sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/report/firstreport.html");
			sparkReporter.config().setDocumentTitle("RestAssuredAutomationreport"); //report title
			sparkReporter.config().setReportName("Functional Testing");   //name of the report
			sparkReporter.config().setTheme(Theme.STANDARD);


			extent=new ExtentReports();
			extent.attachReporter(sparkReporter);


			extent.setSystemInfo("Application","opencart");
			extent.setSystemInfo("Module","Admin");
			extent.setSystemInfo("Sub-Module","Customers");
			extent.setSystemInfo("User Name",System.getProperty("User Name"));
			extent.setSystemInfo("Environment","QA");

			String os=Testcontext.getCurrentXmlTest().getParameter("os");
			extent.setSystemInfo("Operating System", os);

			String osystem=Testcontext.getCurrentXmlTest().getParameter("os");
			extent.setSystemInfo("Operating System", osystem);

			String browser=Testcontext.getCurrentXmlTest().getParameter("browser");
			extent.setSystemInfo("browser", os);

			List<String>includedGroups=Testcontext.getCurrentXmlTest().getIncludedGroups();
			if(!includedGroups.isEmpty())
			{
				extent.setSystemInfo("Groups", includedGroups.toString());
			}

		}



		public void onTestSuccess(ITestResult result)
		{


			test=extent.createTest(result.getName()); //create new entry in the report
			test.log(com.aventstack.extentreports.Status.PASS,"Test case passed is:"+result.getName());  //update status

		}



		public void onTestFailure(ITestResult result) {

			test=extent.createTest(result.getName());
			test.log(com.aventstack.extentreports.Status.FAIL,"Test case failed is:"+result.getName());
			test.log(com.aventstack.extentreports.Status.FAIL,"Test case failed cause is:"+result.getThrowable());

			/*
			 * try { String Imgpath = new BaseClass().captureScreen(result.getName());
			 * test.addScreenCaptureFromPath(Imgpath); } catch (IOException e) {
			 * e.printStackTrace(); }
			 */


		}



		public void onTestSkipped(ITestResult result) {


			test=extent.createTest(result.getName());
			test.log(com.aventstack.extentreports.Status.SKIP,"Test case Skip is:"+result.getName());

		}
		public void onFinish(ITestContext context) {


			extent.flush();


			String pathofextentreport=System.getProperty("user.dir")+"\\reports"+repName;

			File extentreport=new File(pathofextentreport);

			try {
				Desktop.getDesktop().browse(extentreport.toURI());
			  }
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}



		public void onTestStart(ITestResult result) {
			// TODO Auto-generated method stub

		}



		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			// TODO Auto-generated method stub

		}
}
