package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase  {

    protected static ApplicationManager app;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }
}
