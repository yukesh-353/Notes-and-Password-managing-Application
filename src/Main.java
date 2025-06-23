import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final NoteManager noteManager = new NoteManager();
    private static final PasswordManager passwordManager = new PasswordManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Notes and Password Manager =====");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Delete All Notes");
            System.out.println("4. Add Password");
            System.out.println("5. View Passwords");
            System.out.println("6. Delete All Passwords");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> addNote();
                case 2 -> viewNotes();
                case 3 -> noteManager.deleteAllNotes();
                case 4 -> addPassword();
                case 5 -> viewPasswords();
                case 6 -> passwordManager.deleteAllPasswords();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void addNote() {
        System.out.print("Enter note title: ");
        String title = scanner.nextLine();
        System.out.print("Enter note content: ");
        String content = scanner.nextLine();
        noteManager.addNote(new Note(title, content));
        System.out.println("Note added!");
    }

    private static void viewNotes() {
        List<Note> notes = noteManager.getAllNotes();
        if (notes.isEmpty()) {
            System.out.println("No notes found.");
        } else {
            for (Note note : notes) {
                System.out.println("-------------------------");
                System.out.println(note);
            }
        }
    }

    private static void addPassword() {
        System.out.print("Enter website: ");
        String website = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        passwordManager.addPassword(new Password(website, username, password));
        System.out.println("Password saved!");
    }

    private static void viewPasswords() {
        List<Password> passwords = passwordManager.getAllPasswords();
        if (passwords.isEmpty()) {
            System.out.println("No passwords found.");
        } else {
            for (Password p : passwords) {
                System.out.println("-------------------------");
                System.out.println(p);
            }
        }
    }
}
