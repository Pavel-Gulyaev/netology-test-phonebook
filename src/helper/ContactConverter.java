package helper;

import domain.Contact;
import domain.ContactType;

public class ContactConverter {
    private ContactConverter() {
        //utilsClass
    }
    public static String contactToString(Contact contact) {
        return contact.toString();
    }

    public static Contact stringToContact(String contactString) {
        String[] info = contactString.split(" ");
        try {
            return new Contact(info[0], info[1], info[2], ContactType.valueOf(info[3]));
        } catch (IndexOutOfBoundsException | EnumConstantNotPresentException ie) {
            System.out.println("Wrong data in string " + contactString);
        }
        return null;
    }
}
