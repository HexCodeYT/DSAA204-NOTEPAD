package actions;

import editor.NotepadEditor;

public class DeleteAction implements Action {
    private NotepadEditor editor;
    private char removedCharacter;

    public DeleteAction(NotepadEditor editor, char removedCharacter) {
        this.editor = editor;
        this.removedCharacter = removedCharacter;
    }

    @Override
    public void undo() {
        editor.restoreDelete(removedCharacter);
    }

    @Override
    public void redo() {
        editor.deleteRaw();
    }
}
