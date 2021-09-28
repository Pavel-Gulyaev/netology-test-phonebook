package service;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import domain.Contact;
import domain.ContactType;
import domain.Option;
import helper.ConsoleUtils;

public class PhoneBookServiceImpl implements PhoneBookService {
    private final ContactService contactService = new ContactServiceImpl();
    private Option option;

    @Override
    public void work() {
        initData();
        workUntilExit();
        saveData();
    }

    private void initData() {
        contactService.loadContactsFromFile();
    }

    private void workUntilExit() {
        while (true) {
            ConsoleUtils.printMenu();
            option = ConsoleUtils.takeOption();
            if (Option.EXIT == option) {
                break;
            }
            execute();
        }
    }

    private void saveData() {
        contactService.saveAllContactsToFile();
    }

    private void execute() {
        switch (option) {
            case ADD:
                addContact();
                break;

            case DELETE:
                deleteContact();
                break;

            case SEARCH:
                print(searchContact());
                break;

            case PRINT:
                print(contactService.getAllContacts());
                break;

            case EXIT:
                break;
        }
    }

    private void addContact() {
        Contact contact = createContract();
        contactService.addContact(contact);
    }

    private void deleteContact() {
        if (contactService.getAllContacts().isEmpty()) {
            System.out.println("Список пуст");
            return;
        }
        Contact currentContact = getCurrentContact();
        if (currentContact == null) {
            return;
        }
        tryToDeleteContract(currentContact);
    }

    private void tryToDeleteContract(Contact currentContact) {
        System.out.println("Хотите удалить " + currentContact.getName() + " " + currentContact.getSurname() + " Y/N?");
        String confirm = "";
        while (!confirm.equals("Y") && !confirm.equals("N")) {
            Scanner scanner = new Scanner(System.in);
            confirm = scanner.next().toUpperCase(Locale.ROOT);
        }
        if (confirm.equals("Y")) {
            contactService.deleteContact(currentContact);
        }
    }

    private Contact getCurrentContact() {
        Contact currentContact;
        List<Contact> currentContacts = searchContact();
        if (currentContacts.size() > 1) {
            currentContact = fetchContactFromList(currentContacts);
        } else if (!currentContacts.isEmpty()) {
            currentContact = currentContacts.get(0);
        } else {
            System.out.println("Ничего не найдено");
            return null;
        }
        return currentContact;
    }

    private Contact fetchContactFromList(List<Contact> currentContacts) {
        System.out.println("Найдено более одного контакта. Введите номер желаемого конакта:");
        print(currentContacts);
        int answer = ConsoleUtils.takeIntInInterval(1, currentContacts.size());
        return currentContacts.get(answer - 1);
    }

    private void print(List<Contact> allContacts) {
        if (allContacts.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            allContacts.forEach(contact -> System.out.println(contact.toString()));
        }
    }

    private List<Contact> searchContact() {
        String entry = ConsoleUtils.getInputValue("Введите имя или фамилию");
        return contactService.findContact(entry);
    }

    private Contact createContract() {
        Contact contact = new Contact();
        contact.setName(ConsoleUtils.getInputValue("Введите имя"));
        contact.setSurname(ConsoleUtils.getInputValue("Введите фамилию"));
        contact.setPhone(ConsoleUtils.getInputValue("Введите номер телефона"));
        ConsoleUtils.printTypes();
        ContactType type = ConsoleUtils.takeType();
        contact.setType(type);
        return contact;
    }
}
