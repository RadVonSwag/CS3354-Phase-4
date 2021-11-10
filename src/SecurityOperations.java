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
 * @author Andrew Estes
 * @NetID ace190002
 * InventoryManagementSecurity
 */
public class SecurityOperations {

    /**
     * Authenticates user against information in Users.dat using a username and password
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

            File checker = new File("Users.dat");
            if (!checker.exists())
            {
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

                // Set the user's First name and Last name
                if (authenticatedUser != null) {
                    authenticatedUser.setFirstName(userInfo[0]);
                    authenticatedUser.setLastName(userInfo[1]);
                }
            }
        }
        return authenticatedUser;
    }

    /**
     * Checks if file exists
     * @param <E>
     * @param newUser
     */
    public static boolean DoesExist()
    {
        boolean exists = true;
        File checker = new File("Users.dat");
        if (!checker.exists()){
            exists = false;
        }
        return exists;
        
    }

/**
 * Adds new user to Users.dat file based on parameters
 * @param <E>
 * @param newUser
 */
    public static <E> void AddNewUser(User<E> newUser) {
        String firstName = newUser.getFirstName();
        String lastName = newUser.getLastName();
        String username = newUser.getUserName();
        String password = newUser.getPassword();
        int gender = newUser.getGender();
        String userToAdd = firstName + ", " + lastName + ", " + username + ", " + password + ", " + gender;
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
            fw.append(userToAdd+ "\n");
            fw.close();
            System.out.println("User successfully added!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Removes user from Users.dat File based on parameters
     * @param userName
     */
    public static void RemoveUser(String userName) {
        try {

            File originalFile = new File("Users.dat");
            if (!originalFile.exists()) {
                System.out.println("There are no users to remove! You must add users before you can remove them.");
                return;
            }
            BufferedReader read = new BufferedReader(new FileReader("Users.dat"));

            String line = null;
            String[] userLine;
            while ((line = read.readLine()) != null) {
                userLine = line.split(", ");
                if (userLine[2].equals(userName)) {
                    break;
                }
            }

            if (line == null) {
                System.out.println("Username not found. No users removed.");
                read.close();
                return;
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
    }

    /**
     * Changes the hashed password stored in Users.dat to a new hash based on parameters
     * @param username
     * @param currentPassword
     * @param newPassword
     */
    public static void ChangePassword(String username, String currentPassword, String newPassword) {
        File fileChecker = new File("Users.dat");
        if (!fileChecker.exists()) {
            System.out
                    .println("There are no users on record. You must add users before you can change their password.");
            return;
        }

        String line = "";
        try {
            BufferedReader read = new BufferedReader(new FileReader("Users.dat"));
            while ((line = read.readLine()) != null) {
                if (line.indexOf(username) != -1) {
                    break;
                }
            }
            read.close();
            if (line == null) {
                System.out.println("User could not be found. Nothing has been changed.");
                return;
            }

            String[] userInfo = line.split(", ");
            if (!GetPasswordHash(currentPassword).equals(userInfo[3])) {
                System.out.println("Current Password incorrect. Nothing has been changed.");
                return;
            } else if (GetPasswordHash(currentPassword).equals(userInfo[3])) {
                userInfo[3] = GetPasswordHash(newPassword);
            }

            String updatedUser = userInfo[0] + ", " + userInfo[1] + ", " + userInfo[2] + ", " + userInfo[3] + ", "
                    + userInfo[4];
            File originalFile = new File("Users.dat");
            File tempFile = new File(originalFile.getAbsolutePath() + ".tmp");
            PrintWriter print = new PrintWriter(new FileWriter(tempFile));
            String oldUserData = line;

            BufferedReader read2 = new BufferedReader(new FileReader("Users.dat"));
            while ((line = read2.readLine()) != null) {

                if (!line.trim().equals(oldUserData)) {
                    print.println(line);
                    print.flush();
                }
            }
            print.println(updatedUser);
            print.close();
            read2.close();
            originalFile.delete();
            tempFile.renameTo(originalFile);
            if (tempFile.exists()) {
                System.out.println(username
                        + "\'s password has been changed successfully, however, the Users file could not be overwritten with new user data. See \"Users.dat.tmp\" for updated user records");
            } else {
                System.out.println(username + "\'s password has been successfully changed");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hashes a string (password) using MD5 encryption
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
}