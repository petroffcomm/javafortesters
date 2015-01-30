package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.example.fw.AccountHelper;
import com.example.fw.JamesHelper;
import com.example.fw.User;

public class SignupTest extends TestBase {
	
	private JamesHelper james;
	private AccountHelper acctHelper;
	
	User user = new User().setLogin("testuser")
			  .setEmail("testuser@localhost.localdomain")
			  .setPassword("testpassword");
	
	@BeforeClass
	public void initShortcuts(){
		james = app.getJamesHelper();
		acctHelper = app.getAccountHelper();
	}
	
	@BeforeClass(dependsOnMethods = {"initShortcuts"})
	public void createMailUser(){
		if (!james.doesUserExist(user.login)){
			james.createUser(user.login, user.password);
		}
	}
	
	@AfterClass
	public void deleteMailUser(){
		if (james.doesUserExist(user.login)){
			james.deleteUser(user.login);
		}
	}
	
	@Test
	public void newUserShouldSignUp(){
		acctHelper.signup(user);
		acctHelper.login(user);
		assertThat(acctHelper.loggedUsername(), equalTo(user.login));
	}

	@Test(dependsOnMethods = {"newUserShouldSignUp"})
	public void existingUserShouldnotSignUp(){
		try{
			acctHelper.signup(user);
		} catch(Exception e){
			assertThat(e.getMessage(), containsString("That username is already being used"));
			return;
		}
		fail("Existing user signed up.");
	}

}
