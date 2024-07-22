package orangeHRM.baseClass;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int run = 0;
	int max = 1;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(run<max) 
		{
			run++;
			return true;
		}
		return false;
	}

}
