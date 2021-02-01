package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactInGroupTests extends TestBase{


  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0){
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstName("Ivan").withLastName("Sidorov")
              .withHomePhone("+74956734512").withMobilePhone("+(789)92396534").withWorkPhone("+7499-452-09-45")
              .withEmail("any_one@test.com").withEmail2("any_two@test.com").withEmail3("any_two@test.com")
              .withAddress("Какой-то адрес раз"));
    }
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testAddContactInGroup() throws Exception {
    String id =  String.valueOf(app.db().groups().iterator().next().getId());
    Contacts before = app.db().contactsInGroup(id);
    Contacts contactsNotInGroup = app.db().contacts().withoutContactInGroup(before);
    if (contactsNotInGroup.size() != 0){
      ContactData addedContact = contactsNotInGroup.iterator().next();
      app.goTo().homePage();
      app.contact().addToGroup(addedContact);
      assertThat(app.contact().countContactsInGroup(id), equalTo(before.size() + 1));
      Contacts after = app.db().contactsInGroup(id);
      assertThat(after, equalTo(before.withAdded(addedContact)));
    } else {
      System.out.println("Все контакты добавлены в группу!");
    }
  }
}
