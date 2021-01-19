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
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i =0; i < count; i++){
            contacts.add(new ContactData().withFirstName(String.format("%s Иван", i))
                    .withLastName(String.format("%s Сидоров", i))
                    .withEmail(String.format("email%s@test.ru", i))
                    .withHomePhone(String.format("+7999812345%s", i))
                    .withAddress(String.format("г. Москва ул. Гоголя д. %s", i)));
        }
        return contacts;
    }
}
