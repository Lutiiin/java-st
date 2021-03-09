package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testRegistration() throws InterruptedException, IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("usermail%s@localhost.localdomain", now);
        String user = String.format("username%s", now);
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2,10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
        String password = "password";
        app.registration().finish(confirmationLink, password);
        Assert.assertTrue(app.newSession().login(user,password));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
