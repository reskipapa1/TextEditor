package texteditor;

import java.util.Stack;

public class TextEditor {
    private String currentText;
    private final Stack<String> undoStack;
    private final Stack<String> redoStack;

    // Konstruktor
    public TextEditor() {
        currentText = "";
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    // Fungsi untuk menampilkan semua teks yang ditulis
    public void show() {
        System.out.println("Current Text: " + currentText);
    }

    // Fungsi untuk menambahkan teks ke dalam teks editor
    public void write(String newText) {
        // Menyimpan currentText ke undo stack sebelum mengubah teks
        undoStack.push(currentText);
        redoStack.clear();  // Menghapus redo stack karena ada perubahan baru
        currentText += newText;
        System.out.println("Added text: " + newText);
    }

    // Fungsi untuk mengembalikan isi teks ke versi sebelumnya (undo)
    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentText);  // Simpan teks saat ini ke redo stack
            currentText = undoStack.pop();  // Kembalikan teks dari undo stack
            System.out.println("Undo performed.");
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    // Fungsi untuk memulihkan teks yang di-undo sebelumnya (redo)
    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentText);  // Simpan teks saat ini ke undo stack
            currentText = redoStack.pop();  // Pulihkan teks dari redo stack
            System.out.println("Redo performed.");
        } else {
            System.out.println("Nothing to redo.");
        }
    }

    // Main method untuk simulasi
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.write("Hello");
        editor.show();

        editor.write(", World!");
        editor.show();

        editor.undo();
        editor.show();

        editor.redo();
        editor.show();

        editor.undo();
        editor.write(", Java!");
        editor.show();
    }
}
