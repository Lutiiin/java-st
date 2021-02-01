package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactData()
              .withFirstName("Ivan").withLastName("Sidorov")
              .withHomePhone("+74956734512").withMobilePhone("+(789)92396534").withWorkPhone("+7499-452-09-45")
              .withEmail("any_one@test.com").withEmail2("any_two@test.com").withEmail3("any_two@test.com")
              .withAddress("Какой-то адрес раз"));
    }
  }

  @Test
  public void testContactDeletion() throws Exception {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    assertThat(app.contact().countContacts(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withoutAdded(deletedContact)));
  }
}
