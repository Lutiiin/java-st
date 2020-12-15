package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeleteTests extends TestBase {

  @Test
  public void testGroupDelete() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }
}
