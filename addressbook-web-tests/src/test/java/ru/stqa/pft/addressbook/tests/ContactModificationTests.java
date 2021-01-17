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
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData()
              .withFirstName("Ivan").withLastName("Sidorov")
              .withHomePhone("+74956734512").withMobilePhone("+(789)92396534").withWorkPhone("+7499-452-09-45")
              .withEmail("any_one@test.com").withEmail2("any_two@test.com")
              .withAddress("Какой-то адрес раз"));
    }
  }

  @Test
  public void testContactModification() throws Exception {
    Contacts before = app.contact().all();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifyContact.getId())
            .withFirstName("Ivanna").withLastName("Sidorova")
            .withHomePhone("+74950000000").withMobilePhone("+(789)0000000").withWorkPhone("+7499-000-00-00")
            .withEmail("edit_any_one@test.com").withEmail2("edit_any_two@test.com")
            .withAddress("Какой-то отредактированный адрес раз");
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertThat(app.contact().count(), equalTo(before.size()));
    assertThat(after, equalTo(before.withoutAdded(modifyContact).withAdded(contact)));
  }
}
