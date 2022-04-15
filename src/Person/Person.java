package Person;

import java.lang.IllegalStateException;

public class Person {
    protected String firstname;
    protected String lastname;

    public Person() {}
    public Person(String firstname, String lastname)
            throws IllegalStateException {
        setFirstname(firstname);
        setLastname(lastname);
    }

    public void setFirstname(String firstname)
            throws IllegalStateException {
        // null allowed to be able to have only lastname or only firstname
        if(firstname == null) {
            return;
        }
        NameValidation(firstname);
        this.firstname = firstname;
    }

    public void setLastname(String lastname)
            throws IllegalStateException {
        // null allowed to be able to have only lastname or only firstname
        if(lastname == null)
            return;
        NameValidation(lastname);
        this.lastname = lastname;
    }

    public String getFirstname() { return firstname; }
    public String getLastname()  { return lastname; }

    public static void NameValidation(String name)
            throws IllegalStateException {

        if(name == null)
            return;
        if(name.length() < 2 || name.length() > 20)
            throw new IllegalStateException("lastname must have at least " +
                    "2 character and at most 20 character");
    }

}
