package service;

import java.util.List;

import domain.Contact;

public interface ContactService {
    public void loadContactsFromFile();
    public void saveAllContactsToFile();
    public void addContact(Contact contact);
    public void deleteContact(Contact contact);
    public List<Contact> findContact(String filter);
    public List<Contact> getAllContacts();
}
