import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


/**
 * Abstract class representing a generic user of the library system.
 * Each user has a name, ID, phone number, a map of borrowed items and their borrow dates, and a penalty amount.
 */
public abstract class User {
    protected String name;
    protected int id;
    protected String phone_number;
    Map<LibraryItem, LocalDate> borrowedItems;
    int penalty;


    /**
     * Constructs a user with basic information.
     *
     * @param name         user's name
     * @param id           user's unique ID
     * @param phone_number user's phone number
     */
    public User(String name, int id, String phone_number) {
        this.name = name;
        this.id = id;
        this.phone_number = phone_number;
        this.borrowedItems = new HashMap<>();
        this.penalty = 0;
    }

    /**
     * Returns the maximum number of items this user type can borrow.
     */
    public abstract int getMaxItems();

    /**
     * Returns the maximum number of days this user type can keep an item before it's considered overdue.
     */
    public abstract int getOverdueLimit();

    /**
     * Determines whether the user can borrow the specified item type.
     *
     * @param item the item to check
     * @return true if allowed, false otherwise
     */
    public abstract boolean canBorrowItem(LibraryItem item);

    /**
     * Returns formatted user information for display.
     *
     * @return user details as a string
     */
    public abstract String getUserInfo();
}

/**
 * Represents a student user in the library system.
 */
class Student extends User {
    String department;
    String faculty;
    String grade;


    /**
     * Constructs a Student user.
     *
     * @param name         name of the student
     * @param id           ID of the student
     * @param phone_number phone number
     * @param department   department name
     * @param faculty      faculty name
     * @param grade        year of study
     */
    public Student(String name, int id, String phone_number, String department, String faculty, String grade) {
        super(name, id, phone_number);
        this.department = department;
        this.faculty = faculty;
        this.grade = grade;
    }

    @Override
    public int getMaxItems() {
        return 5;
    }

    @Override
    public int getOverdueLimit() {
        return 30;
    }

    @Override
    public boolean canBorrowItem(LibraryItem item) {
        return !item.type.equals("reference");
    }

    @Override
    public String getUserInfo() {
        return "Name: " + name + " Phone: " + phone_number +
                "\nFaculty: " + faculty + " Department: " + department + " Grade: " + grade + "th";
    }
}


/**
 * Represents an academic member in the library system.
 */
class AcademicMember extends User {
    String department;
    String faculty;
    String title;


    /**
     * Constructs an AcademicMember user.
     *
     * @param name         name of the academic
     * @param id           ID of the academic
     * @param phone_number phone number
     * @param department   department name
     * @param faculty      faculty name
     * @param title        academic title (e.g., Dr., Prof.)
     */
    public AcademicMember(String name, int id, String phone_number, String department, String faculty, String title) {
        super(name, id, phone_number);
        this.department = department;
        this.faculty = faculty;
        this.title = title;
    }

    @Override
    public int getMaxItems() {
        return 3;
    }

    @Override
    public int getOverdueLimit() {
        return 15;
    }

    @Override
    public boolean canBorrowItem(LibraryItem item) {
        return true;
    }

    @Override
    public String getUserInfo() {
        return "Name: " + title + " " + name + " Phone: " + phone_number +
                "\nFaculty: " + faculty + " Department: " + department;
    }
}


/**
 * Represents a guest user in the library system.
 */
class GuestUser extends User {
    String occupation;


    /**
     * Constructs a GuestUser.
     *
     * @param name         name of the guest
     * @param id           ID of the guest
     * @param phone_number phone number
     * @param occupation   guest's occupation
     */
    public GuestUser(String name, int id, String phone_number, String occupation) {
        super(name, id, phone_number);
        this.occupation = occupation;
    }

    @Override
    public int getMaxItems() {
        return 1;
    }

    @Override
    public int getOverdueLimit() {
        return 7;
    }

    @Override
    public boolean canBorrowItem(LibraryItem item) {
        return !item.type.equals("rare") && !item.type.equals("limited");
    }

    @Override
    public String getUserInfo() {
        return "Name: " + name + " Phone: " + phone_number +
                "\nOccupation: " + occupation;
    }
}


/**
 * Utility class for converting raw input data into specific User objects.
 * Determines user type based on the first element in the input array:
 * "S" for Student, "A" for AcademicMember, "G" for GuestUser.
 */
class UserParser {


    /**
     * Parses the provided array into a corresponding User object.
     * The first element of the array indicates the user type.
     *
     * @param parts a string array representing a user, typically from a line split by commas
     * @return a Student, AcademicMember, or GuestUser object; or null if the type is unrecognized
     */
    public static User parse(String[] parts) {
        switch (parts[0]) {
            case "S": {
                String name = parts[1];
                int id = Integer.parseInt(parts[2]);
                String phone_number = parts[3];
                String department = parts[4];
                String faculty = parts[5];
                String grade = parts[6];

                return new Student(name, id, phone_number, department, faculty, grade);
            }
            case "A": {
                String name = parts[1];
                int id = Integer.parseInt(parts[2]);
                String phone_number = parts[3];
                String department = parts[4];
                String faculty = parts[5];
                String title = parts[6];

                return new AcademicMember(name, id, phone_number, department, faculty, title);
            }
            case "G": {
                String name = parts[1];
                int id = Integer.parseInt(parts[2]);
                String phone_number = parts[3];
                String occupation = parts[4];

                return new GuestUser(name, id, phone_number, occupation);
            }
        }
        return null;
    }
}

