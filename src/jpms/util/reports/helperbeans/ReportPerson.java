package jpms.util.reports.helperbeans;

import java.util.List;

/**
 *
 * @author m.elz
 */
public class ReportPerson {

    private final String group;
    private final String firstname;
    private final String lastname;
    private final String age;
    private final String birthday;
    private final String dateOfEnters;
    private final String street;
    private final String zipcode;
    private final String city;
    private final String voice;
    private final String functionRole;
    private final String honor;

    private final List<ReportContact> contacts;

    public ReportPerson(String group, String firstname, String lastname, String age, String birthday, String dateOfEnters, String street, String zipcode, String city, String voice, String functionRole, String honor, List<ReportContact> contacts) {
        this.group = group;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.birthday = birthday;
        this.dateOfEnters = dateOfEnters;
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.voice = voice;
        this.functionRole = functionRole;
        this.honor = honor;
        this.contacts = contacts;
    }

    public String getGroup() {
        return group;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAge() {
        return age;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getDateOfEnters() {
        return dateOfEnters;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getVoice() {
        return voice;
    }

    public String getFunctionRole() {
        return functionRole;
    }

    public String getHonor() {
        return honor;
    }

    public List<ReportContact> getContacts() {
        return contacts;
    }

}
