package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData("Ivan", "Sidorov"));
    }
  }

  @Test
  public void testContactModification() throws Exception {
    List<ContactData> before = app.contact().list();
    int index = before.size() -1;
    int id = before.get(index).getId();
    ContactData contact = new ContactData(id,"Ivanna", "Sidorova");
    app.contact().modify(id, index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> ById = (Comparator<ContactData>) (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(after, before);
    app.getSessionHelper().logout();
  }
}
