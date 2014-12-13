package com.example.tests;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase{
  @Test
  public void testNonEmptyGroupCreation() throws Exception {
    GroupData group = new GroupData();
    group.name = "group name 1";
    group.header = "header 1";
    group.footer = "footer 1";

    app.getNavigationHelper().openMainPage();
    app.getNavigationHelper().gotoGroupsPage();
	app.getGroupHelper().initGroupCreating();
	app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupsPage();
  }
  
  @Test
  public void testEmptyGroupCreation() throws Exception {
    GroupData group = new GroupData();
    
    app.getNavigationHelper().openMainPage();
    app.getNavigationHelper().gotoGroupsPage();
	app.getGroupHelper().initGroupCreating();
	app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupsPage();
  }
}
