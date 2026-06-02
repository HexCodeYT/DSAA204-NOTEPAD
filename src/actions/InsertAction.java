package actions;

import editor.NotepadEditor;

public class InsertAction implements Action {
    private NotepadEditor editor;
    private char character;

    public InsertAction(NotepadEditor editor, char character) {
        this.editor = editor;
        this.character = character;
    }

    @Override
    public void undo() {
        editor.backspaceRaw();
    }

    @Override
    public void redo() {
        editor.insertRaw(character);
    }
}
