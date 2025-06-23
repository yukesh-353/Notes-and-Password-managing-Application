import java.io.*;
import java.util.*;

public class PasswordManager {
    private static final String FILE_NAME = "passwords.txt";

    public void addPassword(Password password) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            String encryptedPass = EncryptionUtil.encrypt(password.getPassword());
            fw.write(password.getWebsite() + "::" + password.getUsername() + "::" + encryptedPass + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Password> getAllPasswords() {
        List<Password> passwords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length == 3) {
                    String decryptedPass = EncryptionUtil.decrypt(parts[2]);
                    passwords.add(new Password(parts[0], parts[1], decryptedPass));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return passwords;
    }

    public void deleteAllPasswords() {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            fw.write(""); // clears the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
