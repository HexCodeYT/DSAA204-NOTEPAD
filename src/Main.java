import editor.NotepadEditor;

public class Main {
    public static void main(String[] args) {
        NotepadEditor editor = new NotepadEditor();

        editor.insert('H');
        editor.insert('e');
        editor.insert('l');
        editor.insert('l');
        editor.insert('o');

        System.out.println("Start: " + editor.getText());

        editor.moveLeft();
        editor.moveLeft();
        editor.insert('X');

        System.out.println("After insert: " + editor.getText());

        editor.undo();
        System.out.println("After undo: " + editor.getText());

        editor.redo();
        System.out.println("After redo: " + editor.getText());

        editor.backspace();
        System.out.println("After backspace: " + editor.getText());

        editor.undo();
        System.out.println("Undo backspace: " + editor.getText());

        editor.delete();
        System.out.println("After delete: " + editor.getText());

        editor.undo();
        System.out.println("Undo delete: " + editor.getText());
    }
}