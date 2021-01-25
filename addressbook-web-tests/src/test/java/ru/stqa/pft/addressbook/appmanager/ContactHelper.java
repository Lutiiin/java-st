package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public int count(){
        return wd.findElements(By.name("selected[]")).size();
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("address"), contactData.getAddress());
        type(By.name("address2"), contactData.getAddress2());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        attach(By.name("photo"), contactData.getPhoto());
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
        contactCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initModificationContactById(contact.getId());
        fillContactForm(contact);
        submitModificationContact();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        contactCache = null;
        returnToHomePage();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows){
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String name = cells.get(2).getText();
            String lastName = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            String allEmail = cells.get(4).getText();
            String address = cells.get(3).getText();
            contactCache.add(new ContactData()
                    .withId(id).withFirstName(name).withLastName(lastName)
                    .withAllPhones(allPhones).withAllEmails(allEmail).withAddress(address));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initModificationContactById(contact.getId());
        contactCache = new Contacts();
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3)
                .withAddress(address);
        }
    }
