import editor.NotepadEditor;
import search.KMPSearch;

import java.util.List;

public class NotepadEditorTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {
        testInsertCharacters();
        testBackspace();
        testDeleteKey();
        testCursorMovement();
        testInsertInMiddle();
        testUndoInsert();
        testRedoInsert();
        testUndoBackspace();
        testUndoDelete();
        testUndoCursorMovement();
        testRedoClearedAfterNewAction();
        testKmpSingleMatch();
        testKmpMultipleMatches();
        testKmpNoMatch();
        testKmpEmptyPattern();

        System.out.println();
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }

    private static void assertEquals(String expected, String actual, String testName) {
        if (expected.equals(actual)) {
            pass(testName);
        } else {
            fail(testName, expected, actual);
        }
    }

    private static void assertEquals(int expected, int actual, String testName) {
        if (expected == actual) {
            pass(testName);
        } else {
            fail(testName, String.valueOf(expected), String.valueOf(actual));
        }
    }

    private static void assertListEquals(String expected, List<Integer> actual, String testName) {
        if (expected.equals(actual.toString())) {
            pass(testName);
        } else {
            fail(testName, expected, actual.toString());
        }
    }

    private static void pass(String testName) {
        passed++;
        System.out.println("PASS: " + testName);
    }

    private static void fail(String testName, String expected, String actual) {
        failed++;
        System.out.println("FAIL: " + testName);
        System.out.println("Expected: " + expected);
        System.out.println("Actual:   " + actual);
    }

    private static NotepadEditor editorWithText(String text) {
        NotepadEditor editor = new NotepadEditor();

        for (char c : text.toCharArray()) {
            editor.insert(c);
        }

        return editor;
    }

    private static void testInsertCharacters() {
        NotepadEditor editor = editorWithText("Hello");
        assertEquals("Hello", editor.getText(), "Insert Characters");
    }

    private static void testBackspace() {
        NotepadEditor editor = editorWithText("ABC");
        editor.backspace();
        assertEquals("AB", editor.getText(), "Backspace");
    }

    private static void testDeleteKey() {
        NotepadEditor editor = editorWithText("ABC");
        editor.moveLeft();
        editor.delete();
        assertEquals("AB", editor.getText(), "Delete Key");
    }

    private static void testCursorMovement() {
        NotepadEditor editor = editorWithText("ABC");
        editor.moveLeft();
        editor.moveLeft();
        assertEquals(1, editor.getCursorPosition(), "Cursor Movement");
    }

    private static void testInsertInMiddle() {
        NotepadEditor editor = editorWithText("HelloWorld");

        for (int i = 0; i < 5; i++) {
            editor.moveLeft();
        }

        editor.insert(' ');

        assertEquals("Hello World", editor.getText(), "Insert In Middle");
    }

    private static void testUndoInsert() {
        NotepadEditor editor = editorWithText("AB");
        editor.undo();
        assertEquals("A", editor.getText(), "Undo Insert");
    }

    private static void testRedoInsert() {
        NotepadEditor editor = editorWithText("AB");
        editor.undo();
        editor.redo();
        assertEquals("AB", editor.getText(), "Redo Insert");
    }

    private static void testUndoBackspace() {
        NotepadEditor editor = editorWithText("ABC");
        editor.backspace();
        editor.undo();
        assertEquals("ABC", editor.getText(), "Undo Backspace");
    }

    private static void testUndoDelete() {
        NotepadEditor editor = editorWithText("ABC");
        editor.moveLeft();
        editor.delete();
        editor.undo();
        assertEquals("ABC", editor.getText(), "Undo Delete");
    }

    private static void testUndoCursorMovement() {
        NotepadEditor editor = editorWithText("ABC");
        editor.moveLeft();
        editor.undo();
        assertEquals(3, editor.getCursorPosition(), "Undo Cursor Movement");
    }

    private static void testRedoClearedAfterNewAction() {
        NotepadEditor editor = editorWithText("AB");

        editor.undo();
        editor.insert('C');
        editor.redo();

        assertEquals("AC", editor.getText(), "Redo Cleared After New Action");
    }

    private static void testKmpSingleMatch() {
        assertListEquals("[6]", KMPSearch.search("hello world", "world"), "KMP Single Match");
    }

    private static void testKmpMultipleMatches() {
        assertListEquals("[0, 12]", KMPSearch.search("hello world hello", "hello"), "KMP Multiple Matches");
    }

    private static void testKmpNoMatch() {
        assertListEquals("[]", KMPSearch.search("hello world", "java"), "KMP No Match");
    }

    private static void testKmpEmptyPattern() {
        assertListEquals("[]", KMPSearch.search("hello world", ""), "KMP Empty Pattern");
    }
}