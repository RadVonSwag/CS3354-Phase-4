import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class handles user authentication and user management.
 * 
 * @author Andrew Estes
 * 
 */
public class UserOperations {

    /**
     * Authenticates user against information in Users.dat using a username and
     * password
     * 
     * @param <E>
     * @param username
     * @param password
     * @return authenticated user
     */
    public static <E> User<E> AuthenticateUser(String username, String password) {
        User<E> authenticatedUser = null;
        if ((username.compareToIgnoreCase("admin") == 0)
                && (GetPasswordHash(password).compareToIgnoreCase("58c536ed8facc2c2a293a18a48e3e120") == 0)) {
            authenticatedUser = new User<E>(username, GetPasswordHash(password));
        } else {

            boolean fileExists = DoesExist();
            if (fileExists == false) {
                System.out.println("No user data found. Please sign in with the default admin credentials.");
                return authenticatedUser;
            }

            // Read through list of users from file, stopping when it finds the username
            String line = "";
            try {
                BufferedReader read = new BufferedReader(new FileReader("Users.dat"));
                while ((line = read.readLine()) != null) {
                    if (line.indexOf(username) != -1) {
                        break;
                    }
                }
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // If statement to skip the rest of the code if the username could not be found
            // in the file
            if (line != null) {

                // Read the line that the userdata is stored on into an array
                String[] userInfo = line.split(", ");

                // Comapre user input to what is on file, if the password hashes match, then the
                // user is authenticated
                if ((userInfo[2].equals(username)) && (userInfo[3].equals(GetPasswordHash(password)))) {
                    authenticatedUser = new User<E>(username, GetPasswordHash(password));
                }

                // Set the user's additional data
                if (authenticatedUser != null) {
                    authenticatedUser.setFirstName(userInfo[0]);
                    authenticatedUser.setLastName(userInfo[1]);
                        try {
                            int gender = Integer.parseInt(userInfo[4]);
                            authenticatedUser.setGender(gender);
                        } catch (NumberFormatException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    try {
                        authenticatedUser.setUID(Integer.parseInt(userInfo[6]));
                    } catch (NumberFormatException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        return authenticatedUser;
    }

    /**
     * Adds new user to Users.dat file based on parameters
     * 
     * @param newUser
     *
     */
    public static <E> void CreateNewUser(User<E> newUser) {
        String firstName = newUser.getFirstName();
        String lastName = newUser.getLastName();
        String username = newUser.getUserName();
        String password = newUser.getPassword();
        int gender = newUser.getGender();
        int UID = newUser.getUID();
        int genderPreference = newUser.getGenderPreference();
        String userToAdd = firstName + ", " + lastName + ", " + username + ", " + password + ", " + gender + ", " + genderPreference + ", " + UID;
        String line = "";

        File fileChecker = new File("Users.dat");
        if (!fileChecker.exists()) {
            try {
                fileChecker.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            BufferedReader read = new BufferedReader(new FileReader("Users.dat"));
            while ((line = read.readLine()) != null) {
                if (line.indexOf(username) != -1) {
                    break;
                }

            }
            if ((line != null) && !line.isEmpty()) {
                String[] userInfo = line.split(", ");
                if (username.equals(userInfo[2])) {
                    System.out.println("User with those credentials already exists.\nUser creation failed.");
                    read.close();
                    return;
                }
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter("Users.dat", true);
            fw.append(userToAdd + "\n");
            fw.close();
            System.out.println("User successfully added!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Removes user from Users.dat File based on parameters
     * 
     * @param UID
     */
    public static User RemoveUser(int UID) {
        String UserID = Integer.toString(UID);
        User deletedUser = new User();
        deletedUser.setUID(UID);
        try {
            File originalFile = new File("Users.dat");
            if (!originalFile.exists()) {
                System.out.println("There are no users to remove! You must add users before you can remove them.");
                return null;
            }
            BufferedReader read = new BufferedReader(new FileReader("Users.dat"));

            String line = null;
            String[] userLine;
            while ((line = read.readLine()) != null) {
                userLine = line.split(", ");
                if (userLine[6].equals(UserID)) {
                    break;
                }
            }

            if (line == null) {
                System.out.println("User not found. No users removed.");
                read.close();
                return null;
            }
            File tempFile = new File(originalFile.getAbsolutePath() + ".tmp");
            PrintWriter print = new PrintWriter(new FileWriter(tempFile));
            String lineToRemove = line;

            BufferedReader read2 = new BufferedReader(new FileReader("Users.dat"));
            while ((line = read2.readLine()) != null) {

                if (!line.trim().equals(lineToRemove)) {
                    print.println(line);
                    print.flush();
                }
            }
            print.close();
            read.close();
            read2.close();
            originalFile.delete();
            tempFile.renameTo(originalFile);

            // Check if file still exists Output to console that the file cannot be changed
            if (tempFile.exists()) {
                System.out.println(
                        "The Users file could not be overwritten with new user data. See \"Users.dat.tmp\" for updated user records");
            } else {
                System.out.println("The user has been successfully removed.");
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return deletedUser;
    }

    public static User RemoveUser(String username) {

        User deletedUser = new User();
        try {
            File originalFile = new File("Users.dat");
            if (!originalFile.exists()) {
                System.out.println("There are no users to remove! You must add users before you can remove them.");
                return null;
            }
            BufferedReader read = new BufferedReader(new FileReader("Users.dat"));

            String line = null;
            String[] userLine;
            while ((line = read.readLine()) != null) {
                userLine = line.split(", ");
                if (userLine[1].equals(username)) {
                    break;
                }
            }

            if (line == null) {
                System.out.println("User not found. No users removed.");
                read.close();
                return null;
            }
            File tempFile = new File(originalFile.getAbsolutePath() + ".tmp");
            PrintWriter print = new PrintWriter(new FileWriter(tempFile));
            String lineToRemove = line;

            BufferedReader read2 = new BufferedReader(new FileReader("Users.dat"));
            while ((line = read2.readLine()) != null) {

                if (!line.trim().equals(lineToRemove)) {
                    print.println(line);
                    print.flush();
                }
            }
            print.close();
            read.close();
            read2.close();
            originalFile.delete();
            tempFile.renameTo(originalFile);

            // Check if file still exists Output to console that the file cannot be changed
            if (tempFile.exists()) {
                System.out.println(
                        "The Users file could not be overwritten with new user data. See \"Users.dat.tmp\" for updated user records");
            } else {
                System.out.println("The user has been successfully removed.");
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return deletedUser;
    }

    public static boolean ChangeBio(String Bio)
    {
        return true;
    }

    public static int ChangeStatus(int status)
    {
        return status;
    }

    /**
     * Hashes a string (password) using MD5 encryption
     * 
     * @param password
     * @return hashed password
     */
    public static String GetPasswordHash(String password) {
        String generatedPassword = null;

        try {
            byte[] salt = new byte[] { 12, -12, 65, 61, 2, -6, -90, 12, 4, -7, -87, 2, 34, -102, 3, 115 };

            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Add password bytes to digest
            md.update(salt);
            // Get the hash's bytes
            byte[] bytes = md.digest(password.getBytes());
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedPassword;
    }

    /**
     * Checks if file exists
     * 
     * @param <E>
     * @param newUser
     */
    public static boolean DoesExist() {
        boolean exists = true;
        File checker = new File("Users.dat");
        if (!checker.exists()) {
            exists = false;
        }
        return exists;

    }
}