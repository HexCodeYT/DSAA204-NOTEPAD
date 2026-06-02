package actions;

import editor.NotepadEditor;

public class BackspaceAction implements Action {
    private NotepadEditor editor;
    private char removedCharacter;

    public BackspaceAction(NotepadEditor editor, char removedCharacter) {
        this.editor = editor;
        this.removedCharacter = removedCharacter;
    }

    @Override
    public void undo() {
        editor.restoreBackspace(removedCharacter);
    }

    @Override
    public void redo() {
        editor.backspaceRaw();
    }
}
