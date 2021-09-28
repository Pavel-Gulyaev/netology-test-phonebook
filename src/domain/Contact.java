package domain;

public class Contact {
    private ContactType type;
    private String name;
    private String surname;
    private String phone;

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Contact() {
    }

    @Override
    public String toString() {
        return name + ' ' + surname + ' ' + phone + ' ' + type.toString();
    }

    public Contact(String name, String surname, String phone, ContactType type) {
        this.type = type;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }
}
