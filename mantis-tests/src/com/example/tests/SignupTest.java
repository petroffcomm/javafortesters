package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

import com.example.fw.AccountHelper;
import com.example.fw.HibernateHelper;
import com.example.fw.JamesHelper;

public class SignupTest extends TestBase {
	
	private JamesHelper james;
	private AccountHelper acctHelper;
	private HibernateHelper mantisDbHelper;
	
	UserData user = new UserData().setLogin("testuser")
			  .setEmail("testuser@localhost.localdomain")
			  .setPassword("testpassword");
	UserData user2 = new UserData().setLogin("testuser2")
			  .setEmail("testuser@localhost.localdomain")
			  .setPassword("testpassword");
	
	@BeforeClass
	public void initShortcuts(){
		james = app.getJamesHelper();
		acctHelper = app.getAccountHelper();
		mantisDbHelper = app.getHibernateHelper();
	}
	
	@BeforeClass(dependsOnMethods = {"initShortcuts"})
	public void createMailUser(){
		if (!james.doesUserExist(user.login)){
			james.createUser(user.login, user.password);
		}
	}
	
	@BeforeClass(dependsOnMethods = {"initShortcuts"})
	public void cleanMantisUser(){
		if (mantisDbHelper.doesUserExists(user.login)){
			mantisDbHelper.deleteUser(user.login);
		}
	}
	
	@Test(groups = {"needLogout"})
	public void newUserShouldSignUp(){
		try{
			acctHelper.signup(user);
			assertTrue(acctHelper.isLoginSuccessful(user));
		}finally{
			acctHelper.doMantisLogout();
		}
	}

	@Test(dependsOnMethods = {"newUserShouldSignUp"})
	public void existingUserShouldnotSignUp(){
		try{
			acctHelper.signup(user);
		} catch(Exception e){
			assertThat(e.getMessage(), containsString("That username is already being used"));
			return;
		}
		//This will trigger if "catch"-section will not trigger.
		fail("Existing user signed up.");
	}
	
	@AfterClass
	public void deleteMailUser(){
		if (james.doesUserExist(user.login)){
			james.deleteUser(user.login);
		}
	}

}
