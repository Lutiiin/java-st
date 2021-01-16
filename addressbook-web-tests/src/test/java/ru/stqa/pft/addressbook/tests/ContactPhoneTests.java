package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstName("Ivan").withLastName("Sidorov")
                    .withHomePhone("+74956734512").withMobilePhone("+(789)92396534").withWorkPhone("+7499-452-09-45")
                    .withEmail("any_one@test.com").withEmail2("any_two@test.com")
                    .withAddress("Какой-то адрес раз").withAddress2("Какой-то адрес два"));
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getEmail(), equalTo(mergeEmail(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .stream().filter((s) -> ! equals(""))
                .map(this::cleanedPhone)
                .collect(Collectors.joining("\n"));
    }

    private String mergeEmail(ContactData contact) {
        return Arrays.asList(contact.getEmail())
                .stream().filter((s) -> ! equals(""))
                .map(this::cleanedData)
                .collect(Collectors.joining("\n"));
    }

    private String mergeAddress(ContactData contact) {
        return Arrays.asList(contact.getAddress())
                .stream().filter((s) -> ! equals(""))
                .map(this::cleanedData)
                .collect(Collectors.joining("\n"));
    }

    public String cleanedPhone (String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }

    public String cleanedData (String data){
        return data.replaceAll("\\s", " ");
    }
}
