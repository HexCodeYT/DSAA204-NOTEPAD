import editor.NotepadEditor;
import search.KMPSearch;

public class Main {
    public static void main(String[] args) {
        NotepadEditor editor = new NotepadEditor();

        String input = "hello world hello java";

        for (char c : input.toCharArray()) {
            editor.insert(c);
        }

        System.out.println("Text: " + editor.getText());
        System.out.println("Search 'hello': " + KMPSearch.search(editor.getText(), "hello"));
        System.out.println("Search 'java': " + KMPSearch.search(editor.getText(), "java"));
        System.out.println("Search 'x': " + KMPSearch.search(editor.getText(), "x"));
    }
}