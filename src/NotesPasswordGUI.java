import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class NotesPasswordGUI extends JFrame {
    private NoteManager noteManager = new NoteManager();
    private PasswordManager passwordManager = new PasswordManager();

    public NotesPasswordGUI() {
        setTitle("Notes & Password Manager");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        // Notes Tab
        JPanel notePanel = new JPanel(new BorderLayout());
        JTextArea noteDisplay = new JTextArea();
        noteDisplay.setEditable(false);
        JButton addNoteBtn = new JButton("Add Note");
        addNoteBtn.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(this, "Enter Title:");
            String content = JOptionPane.showInputDialog(this, "Enter Content:");
            if (title != null && content != null) {
                noteManager.addNote(new Note(title, content));
                JOptionPane.showMessageDialog(this, "Note Added!");
                updateNotes(noteDisplay);
            }
        });
        notePanel.add(new JScrollPane(noteDisplay), BorderLayout.CENTER);
        notePanel.add(addNoteBtn, BorderLayout.SOUTH);

        // Passwords Tab
        JPanel passwordPanel = new JPanel(new BorderLayout());
        JTextArea passwordDisplay = new JTextArea();
        passwordDisplay.setEditable(false);
        JButton addPassBtn = new JButton("Add Password");
        addPassBtn.addActionListener(e -> {
            String site = JOptionPane.showInputDialog(this, "Website:");
            String user = JOptionPane.showInputDialog(this, "Username:");
            String pass = JOptionPane.showInputDialog(this, "Password:");
            if (site != null && user != null && pass != null) {
                passwordManager.addPassword(new Password(site, user, pass));
                JOptionPane.showMessageDialog(this, "Password Saved!");
                updatePasswords(passwordDisplay);
            }
        });
        passwordPanel.add(new JScrollPane(passwordDisplay), BorderLayout.CENTER);
        passwordPanel.add(addPassBtn, BorderLayout.SOUTH);

        // Tabs
        tabs.addTab("Notes", notePanel);
        tabs.addTab("Passwords", passwordPanel);
        add(tabs);

        updateNotes(noteDisplay);
        updatePasswords(passwordDisplay);
    }

    private void updateNotes(JTextArea area) {
        List<Note> notes = noteManager.getAllNotes();
        area.setText("");
        for (Note note : notes) {
            area.append(note.toString() + "\n------------------\n");
        }
    }

    private void updatePasswords(JTextArea area) {
        List<Password> passwords = passwordManager.getAllPasswords();
        area.setText("");
        for (Password p : passwords) {
            area.append(p.toString() + "\n------------------\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NotesPasswordGUI().setVisible(true));
    }
}
