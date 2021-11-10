package LinkedInventoryManagement.Security; 

/**
 * User class stores data for user objects
 * 
 * @author Andrew Estes 
 * @NetID ace190002
 * 
 * @param <E>
 */
public class User<E> implements Comparable<E>
{
    /**
     * Stores the username as a string for the User object.
     */
    private String username;

    /**
     * Stores the hashed password as a string for the User object.
     */
    private String hashedPassword;

    /**
     * Stores the User type as a boolean for the User object.
     */
    private boolean isManager;

    /**
     * Stores the first name for the User object as a String.
     */
    private String firstName;

    /**
     * Stores the last name for the User object as String.
     */
    private String lastName;

    /**
     * Constructor assigns values to User class's data fields.
     * @param username
     * @param hashedPassword
     * @param isManager
     */
    public User(String username, String hashedPassword, boolean isManager)
    {        
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.isManager = isManager;
    }

    /**
     * Changes the first name for the User object.
     * @param name
     */
    public void setFirstName(String name) {
        firstName = name;
    }

    /**
     * Returns the first name for the User object.
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Changes the last name for the User object.
     * @param name
     */
    public void setLastName(String name) {
        lastName = name;
    }

    /**
     * Returns the last name for the User object.
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns username for the User object.
     * @return username
     */
    public String getUserName() {
        return username;
    }

    /**
     * Returns the hashed password for the User object.
     * @return hashed password
     */
    public String getPassword() {
        return hashedPassword;
    }

    /**
     * Returns User type as true for Manager, false for employee
     * @return isManager
     */
    public boolean getManagerStatus() {
        return isManager;
    }

    @Override
    public int compareTo(E o) {
        // TODO Auto-generated method stub
        return 0;
    }
}