package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import javax.mail.MessagingException;
import java.io.IOException;

public class AdministrationHelper extends HelperBase{

    public AdministrationHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        click(By.cssSelector("input[value = 'Войти']"));
        type(By.name("password"), email);
        click(By.cssSelector("input[value = 'Войти']"));
    }

    public void resetPass(String username) throws InterruptedException, MessagingException, IOException {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        click(By.xpath(String.format("//a[text() = '%s']", username)));
        click(By.cssSelector("input[value = 'Сбросить пароль']"));
        click(By.linkText("Продолжить"));
        click(By.xpath(String.format("//a[text() = '%s']", username)));
        click(By.cssSelector("input[value = 'Сбросить пароль']"));
    }

    public void changePass(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type = 'submit']"));
    }
}
