import editor.NotepadEditor;

public class Main {
    public static void main(String[] args) {
        NotepadEditor editor = new NotepadEditor();

        editor.insert('H');
        editor.insert('e');
        editor.insert('l');
        editor.insert('l');
        editor.insert('o');

        System.out.println(editor.getText());

        editor.moveLeft();
        editor.moveLeft();
        editor.insert('X');

        System.out.println(editor.getText());
        System.out.println("Cursor: " + editor.getCursorPosition());

        editor.backspace();
        System.out.println(editor.getText());

        editor.delete();
        System.out.println(editor.getText());
    }
}