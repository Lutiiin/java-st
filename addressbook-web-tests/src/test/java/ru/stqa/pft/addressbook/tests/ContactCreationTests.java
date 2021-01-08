package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().createContact(new ContactData("Ivan", "Sidorov", "test@test.ru", "Moscow", "+74950982376"));
    app.getSessionHelper().logout();
  }
}
