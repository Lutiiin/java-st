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
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactData()
              .withFirstName("Ivan").withLastName("Sidorov")
              .withHomePhone("+74956734512").withMobilePhone("+(789)92396534").withWorkPhone("+7499-452-09-45")
              .withEmail("any_one@test.com").withEmail2("any_two@test.com").withEmail3("any_two@test.com")
              .withAddress("Какой-то адрес раз").withAddress2("Какой-то адрес два"));
    }
  }

  @Test
  public void testContactModification() throws Exception {
    Contacts before = app.db().contacts();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifyContact.getId())
            .withFirstName("Ivanna").withLastName("Sidorova")
            .withHomePhone("+74950000000").withMobilePhone("+7(989)0000000").withWorkPhone("+7499-000-00-00")
            .withEmail("edit_one@test.com").withEmail2("edit_two@test.com").withEmail3("edit_three@test.com")
            .withAddress("Какой-то отредактированный адрес раз").withAddress2("Какой-то отредактированный адрес два");
    app.goTo().homePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withAdded(contact).withoutAdded(modifyContact)));
  }
}
