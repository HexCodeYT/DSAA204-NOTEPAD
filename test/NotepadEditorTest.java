import editor.NotepadEditor;
import search.KMPSearch;

public class NotepadEditorTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {

        testInsert();
        testBackspace();
        testDelete();
        testCursorMovement();
        testUndoRedo();
        testSearch();

        System.out.println();
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }

    private static void assertEquals(String expected, String actual, String testName) {
        if (expected.equals(actual)) {
            passed++;
            System.out.println("PASS: " + testName);
        } else {
            failed++;
            System.out.println("FAIL: " + testName);
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);
        }
    }

    private static void testInsert() {
        NotepadEditor editor = new NotepadEditor();

        editor.insert('H');
        editor.insert('i');

        assertEquals("Hi", editor.getText(), "Insert Characters");
    }

    private static void testBackspace() {
        NotepadEditor editor = new NotepadEditor();

        editor.insert('A');
        editor.insert('B');
        editor.backspace();

        assertEquals("A", editor.getText(), "Backspace");
    }

    private static void testDelete() {
        NotepadEditor editor = new NotepadEditor();

        editor.insert('A');
        editor.insert('B');
        editor.insert('C');

        editor.moveLeft();
        editor.delete();

        assertEquals("AB", editor.getText(), "Delete Key");
    }

    private static void testCursorMovement() {
        NotepadEditor editor = new NotepadEditor();

        editor.insert('A');
        editor.insert('B');
        editor.insert('C');

        editor.moveLeft();
        editor.insert('X');

        assertEquals("ABXC", editor.getText(), "Cursor Movement");
    }

    private static void testUndoRedo() {
        NotepadEditor editor = new NotepadEditor();

        editor.insert('A');
        editor.insert('B');

        editor.undo();

        assertEquals("A", editor.getText(), "Undo");

        editor.redo();

        assertEquals("AB", editor.getText(), "Redo");
    }

    private static void testSearch() {
        String text = "hello world hello";

        boolean success =
                KMPSearch.search(text, "hello").size() == 2;

        if (success) {
            passed++;
            System.out.println("PASS: KMP Search");
        } else {
            failed++;
            System.out.println("FAIL: KMP Search");
        }
    }
}