package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDelitionTests extends TestBase{

  @Test
  public void testContactDelition() throws Exception {
    app.getNavigationHelper().goToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().goToHomePage();
    app.getSessionHelper().logout();
  }
}
