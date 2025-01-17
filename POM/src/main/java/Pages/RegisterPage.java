package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utils.CommonMethods;
import utils.ProjectMethods;

public class RegisterPage extends ProjectMethods {
	
	public RegisterPage() {
		PageFactory.initElements(driver,this);
	}
	
	
	@FindBy(how = How.ID, using = "firstname") WebElement firstName_element;
	@FindBy(how = How.ID, using = "lastname") WebElement lastName_element;
	@FindBy(how = How.ID, using = "userName") WebElement userName_element;
	@FindBy(how = How.ID, using = "password") WebElement passWord_element;
	@FindBy(how = How.ID, using = "recaptcha-anchor") WebElement reCaptcha_element;
	@FindBy(how = How.ID, using = "register") WebElement register_element;
	@FindBy(how = How.ID, using = "gotologin") WebElement gotoLogin_element;
	
	
	public RegisterPage enterFirstName(String firstname) {
		
		locateElementValue(firstName_element, firstname);
		return this;
	}
	
	public RegisterPage enterLastName(String lastname) {
		
		locateElementValue(lastName_element, lastname);
		return this;
	}
	
	public RegisterPage enterUserName(String username) {
		
		locateElementValue(userName_element, username);
		return this;
	}
	
	public RegisterPage enterPassword(String passwrd) {
		
		locateElementValue(passWord_element, passwrd);
		return this;
	}
	
	public RegisterPage clickCaptcha() {
		click(reCaptcha_element);
		return this;
		
	}
	
	public RegisterPage clickRegister() {
		click(register_element);
		driver.switchTo().alert().accept();
		return this; 
	}
	
	public LoginPage clickBackToLogin() {
		click(gotoLogin_element);
		return new LoginPage(); 
	}

}
