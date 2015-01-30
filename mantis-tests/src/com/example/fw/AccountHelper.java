package com.example.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AccountHelper extends BaseHelperWeb {

	public AccountHelper(ApplicationManager manager) {
		super(manager);
		
	}

	public void signup(User user) {
		openRelativeUrl("/signup_page.php");
		//click(By.cssSelector("span.bracket-link"));
		type(By.name("username"), user.login);
		type(By.name("email"), user.email);
		click(By.xpath("//input[@type='submit'][@value='Signup']"));
		
		WebElement errorMsg = findElement(By.cssSelector("table.width50 tbody tr td p"));
		if (errorMsg != null){
			throw new RuntimeException(errorMsg.getText());
		}
		
		pause(30000);
		
		MailMessage msg = manager.getMailHelper().getNewMail(user.login, user.password);
		openAbsoluteUrl(msg.getConfirmationLink());
		
		type(By.xpath("//input[@name='password']"), user.password);
		type(By.xpath("//input[@name='password_confirm']"), user.password);
		click(By.xpath("//input[@class='button'][@type='submit']"));
	}

	public void login(User user) {
		openRelativeUrl("");
		type(By.name("username"), user.login);
		type(By.name("password"), user.password);
		click(By.xpath("//input[@class='button'][@type='submit']"));
	}

	public String loggedUsername() {
		WebElement loginLabel = findElement(By.cssSelector("td.login-info-left span"));
		return loginLabel.getText();
	}

}
