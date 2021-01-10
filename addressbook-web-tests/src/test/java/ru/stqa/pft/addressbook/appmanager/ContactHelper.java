package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

    private void returnToHomePage() {
        wd.findElement(By.linkText("home")).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void goToPageContactAdd() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initModificationContact() {
        click(By.xpath("//img[@title = 'Edit']"));
    }

    public void submitModificationContact() {
        click(By.name("update"));
    }

    public void create(ContactData contact) {
        goToPageContactAdd();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void modify(List<ContactData> before, ContactData contact) {
        selectContact(before.size() -1);
        initModificationContact();
        fillContactForm(contact);
        submitModificationContact();
        returnToHomePage();
    }

    public void delete(int index) {
        selectContact(index);
        deleteContact();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("#maintable > tbody > tr[name = 'entry']"));
        for (WebElement element : elements){
            String name = element.findElement(By.cssSelector(":nth-child(3)")).getText();
            String lastName = element.findElement(By.cssSelector(":nth-child(2)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));;
            ContactData contact = new ContactData(id, name, lastName, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
