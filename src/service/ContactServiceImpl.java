package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import domain.Contact;
import helper.ContactConverter;

public class ContactServiceImpl implements ContactService {
    private final FileService fileService = new FileServiceImpl();
    private List<Contact> contacts = new ArrayList<>();
    @Override
    public void loadContactsFromFile() {
        List<String> data = fileService.loadData();
        contacts = data.stream()
                .map(ContactConverter::stringToContact)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public void saveAllContactsToFile() {
        List<String> data = contacts.stream()
                .map(ContactConverter::contactToString)
                .collect(Collectors.toList());
        fileService.saveData(data);
    }

    @Override
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }

    @Override
    public List<Contact> findContact(String filter) {
        return contacts.stream()
                .filter(contact -> contactNameAndSurnameFilter(filter, contact))
                .collect(Collectors.toList());
    }

    @Override
    public List<Contact> getAllContacts() {
        return contacts;
    }

    private boolean contactNameAndSurnameFilter(String filter, Contact contact) {
        return contact.getName().contains(filter) || contact.getSurname().contains(filter);
    }
}
