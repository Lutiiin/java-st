package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;

public class TestBase {

    protected static ApplicationManager app;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
        app.init();
        app.ftp().upload(new File("/Users/alenaegupova/java-st/matis-tests/src/test/resources/config_inc.php"),
                "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }
}
