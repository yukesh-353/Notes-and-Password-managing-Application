import java.io.*;
import java.util.*;



public class NoteManager {
    private static final String FILE_NAME = "notes.txt";

    public void addNote(Note note) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(note.getTitle() + "::" + note.getContent() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("::");
                if (parts.length == 2) {
                    notes.add(new Note(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return notes;
    }

    public void deleteAllNotes() {
        try (FileWriter fw = new FileWriter(FILE_NAME)) {
            fw.write(""); // clears the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
