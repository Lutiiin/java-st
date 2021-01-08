package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactDelition() throws Exception {
    app.getNavigationHelper().goToHomePage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Ivan", "Sidorov", "test@test.ru", "Moscow", "+74950982376"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initModificationContact();
    app.getContactHelper().fillContactForm(new ContactData("Ivanna", "Sidorova", "test@testedit.ru", "St.Peterburg", "+74950982334"));
    app.getContactHelper().submitModificationContact();
    app.getNavigationHelper().goToHomePage();
    app.getSessionHelper().logout();
  }
}
