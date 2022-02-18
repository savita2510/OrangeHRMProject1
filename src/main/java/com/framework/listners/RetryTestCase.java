package com.framework.listners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTestCase implements IRetryAnalyzer {

	private int count = 0;
	private static int maxRetryCount = 1;

	@Override
	public boolean retry(ITestResult result) {
		if (!result.isSuccess()) {
			if (count < maxRetryCount) {
				count++;
				result.setStatus(result.FAILURE);
				return true;
			}
		} else {
			result.setStatus(result.SUCCESS);
		}

		return false;
	}

	// This class will be used to maintain all methods related to testng listners to
	// re-run the failed test cases

}
