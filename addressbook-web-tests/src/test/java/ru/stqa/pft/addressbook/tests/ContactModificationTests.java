package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData().withFirstName("Ivan").withLastName("Sidorov"));
    }
  }

  @Test
  public void testContactModification() throws Exception {
    Contacts before = app.contact().all();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifyContact.getId()).withFirstName("Ivanna").withLastName("Sidorova");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before.withoutAdded(modifyContact).withAdded(contact)));
    app.getSessionHelper().logout();
  }
}
