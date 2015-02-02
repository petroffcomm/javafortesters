package com.example.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.UserData;

public class AccountHelper extends BaseHelperWeb {

	public AccountHelper(ApplicationManager manager) {
		super(manager);
		
	}

	public void signup(UserData user) {
		openRelativeUrl("/signup_page.php");
		type(By.name("username"), user.login);
		type(By.name("email"), user.email);
		click(By.xpath("//input[@type='submit'][@value='Signup']"));
		
		WebElement errorMsg = findElement(By.cssSelector("table.width50 tbody tr td p"));
		if (errorMsg != null){
			throw new RuntimeException(errorMsg.getText());
		}
		
		pause(15000);
		
		MailMessage msg = manager.getMailHelper().getNewMail(user.login, user.password);
		openAbsoluteUrl(msg.getConfirmationLink());
		
		type(By.xpath("//input[@name='password']"), user.password);
		type(By.xpath("//input[@name='password_confirm']"), user.password);
		click(By.xpath("//input[@class='button'][@type='submit']"));
	}

	public void login(UserData user) {
		openRelativeUrl("");
		type(By.name("username"), user.login);
		type(By.name("password"), user.password);
		click(By.xpath("//input[@class='button'][@type='submit']"));
	}
	
	public boolean isLoginSuccessful(UserData user) {
		login(user);
		
		try{
			WebElement loginLabel = findElement(By.cssSelector("td.login-info-left span"));
			return loginLabel.getText().equals(user.login);
		}catch(Exception e){
			return false;
		}
	}

	public String loggedUsername() {
		WebElement loginLabel = findElement(By.cssSelector("td.login-info-left span"));
		return loginLabel.getText();
	}

	public void doMantisLogout() {
		click(By.xpath("//a[contains(@href,'logout_page.php')]"));		
	}

}
