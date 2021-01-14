package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
    }

    public void initModificationContactById(int id) {
        wd.findElement(By.xpath("//a[@href=\'edit.php?id=" + id + "\']")).click();
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

    public void modify(int id, int index, ContactData contact) {
        selectContact(index);
        initModificationContactById(id);
        fillContactForm(contact);
        submitModificationContact();
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initModificationContactById(contact.getId());
        fillContactForm(contact);
        submitModificationContact();
        returnToHomePage();
    }

    public void delete(int index) {
        selectContact(index);
        deleteContact();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
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
            contacts.add(new ContactData().withId(id).withFirstName(name).withLastName(lastName));
        }
        return contacts;
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("#maintable > tbody > tr[name = 'entry']"));
        for (WebElement element : elements){
            String name = element.findElement(By.cssSelector(":nth-child(3)")).getText();
            String lastName = element.findElement(By.cssSelector(":nth-child(2)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));;
            contacts.add(new ContactData().withId(id).withFirstName(name).withLastName(lastName));
        }
        return contacts;
    }
}
