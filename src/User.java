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
     * Stores the first name for the User object as a String.
     */
    private String firstName;

    /**
     * Stores the last name for the User object as String.
     */
    private String lastName;

    /**
     * An Array of strings that stores the user's interests
     */
    private String[] interests;

    /**
     * Stores gender preference of user. Should be 0, for male, 1 for female, or 2 for any
     */
    private int genderPreference;

    /**
     * Gender should be 0 for male, 1 for female, 2 for other
     */
    private int gender;

    /**
     * Constructor assigns values to User class's data fields.
     * @param username
     * @param hashedPassword
     * @param isManager
     */
    public User(String username, String hashedPassword)
    {        
        this.username = username;
        this.hashedPassword = hashedPassword;
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
     * Returns gender of user
     * @return gender
     */
    public int getGender() {
        return gender;
    }
    /**
     * Sets the gender for the user
     * @param preference
     * @throws Exception
     */
    public void setGender(int gender) throws Exception
    {
        if (gender != 0 || gender != 1 || gender != 2)
        {
            throw new Exception("Invalid gender!");
        }
        this.gender = gender;
    }

    /**
     * Returns genderPreference
     * @return genderPreference
     */
    public int getGenderPreference()
    {
        return genderPreference;
    }
    /**
     * Sets the gender preference for the user
     * @param preference
     * @throws Exception
     */
    public void setGenderPreference(int preference) throws Exception
    {
        if (preference != 0 || preference != 1 || preference != 2)
        {
            throw new Exception("Invalid gender preference!");
        }
        genderPreference = preference;
    }

    @Override
    public int compareTo(E o) {
        // TODO Auto-generated method stub
        return 0;
    }
}