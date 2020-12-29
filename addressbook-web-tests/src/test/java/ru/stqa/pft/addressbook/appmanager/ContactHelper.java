package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("email"), contactData.getEmail());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
    }

    public void deleteContact() {
        try {
            click(By.xpath("//input[@value='Delete']"));
        } catch (UnhandledAlertException ex) {
        }
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void initModificationContact() {
        click(By.xpath("//img[@title = 'Edit']"));
    }

    public void submitModificationContact() {
        click(By.name("update"));
    }

}
