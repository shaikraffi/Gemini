package execution;

import org.testng.annotations.Test;

import general.Basic;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Exection extends Basic {
  @Test
  public void Test() {
	  openBrowser();
	  goTO();
	 isElementPresent();
	  clickon();
	 verifyTitle();
	  enterText();
	  
  }
  @BeforeTest
  public void beforeTest() {
	  System.out.println("Test Started -----------------");
	      
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Test Ended -----------------");
  }

}
