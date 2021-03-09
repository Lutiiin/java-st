package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ChangePassTest extends TestBase{

    @Test
    public void testChangePass() throws IOException, MessagingException, InterruptedException {
        Users allUsers = app.db().users();
        UserData user = allUsers.iterator().next();
        String username = user.getUsername();
        String email = user.getEmail();
        app.administration().login("administrator", "root");
        app.mail().start();
        app.administration().resetPass(username);
        List<MailMessage> mailMessages = app.mail().waitForMail(2,10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
        app.mail().stop();
        String newPass = "Thfj7*(JD";
        app.administration().changePass(confirmationLink, newPass);
        HttpSession session = app.newSession();
        Assert.assertTrue(session.login(username, newPass));
        Assert.assertTrue(session.isLoggedInAs(username));
    }
}
