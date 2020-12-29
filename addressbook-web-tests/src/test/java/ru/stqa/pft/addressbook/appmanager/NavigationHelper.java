package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        click(By.linkText("groups"));
    }

    public void goToHomePage() {
        wd.findElement(By.linkText("home")).click();
    }

    public void goToPageContactAdd() {
        wd.findElement(By.linkText("add new")).click();
    }
}
