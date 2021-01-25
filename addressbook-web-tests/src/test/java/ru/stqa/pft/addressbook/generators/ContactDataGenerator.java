package ru.stqa.pft.addressbook.generators;

import bayern.steinbrecher.jcommander.JCommander;
import bayern.steinbrecher.jcommander.Parameter;
import bayern.steinbrecher.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter (names = "-c", description = "Contact count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        }
        catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("xml")){
            saveAsXml(contacts, new File(file));
        }else{
            System.out.println("Unrecognized format");
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try(Writer writer = new FileWriter(file)){
            writer.write(xml);
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i =0; i < count; i++){
            contacts.add(new ContactData().withFirstName(String.format("%s Иван", i))
                    .withLastName(String.format("%s Сидоров", i))
                    .withHomePhone(String.format("+7999812345%s", i))
                    .withMobilePhone(String.format("+7(989)000000%s", i+1))
                    .withWorkPhone(String.format("+7499-000-00-0%s", i+2))
                    .withEmail(String.format("email_%s@test.ru", i))
                    .withEmail2(String.format("email_%s@test.ru", i+1))
                    .withEmail3(String.format("email_%s@test.ru", i+2))
                    .withAddress(String.format("г. Москва ул. Гоголя д. %s", i))
                    .withAddress2(String.format("г. Москва ул. Пушкина д. %s", i+1)));
        }
        return contacts;
    }
}
