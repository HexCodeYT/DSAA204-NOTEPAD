package actions;

import editor.NotepadEditor;

public class MoveAction implements Action {
    private NotepadEditor editor;
    private String direction;

    public MoveAction(NotepadEditor editor, String direction) {
        this.editor = editor;
        this.direction = direction;
    }

    @Override
    public void undo() {
        if (direction.equals("LEFT")) {
            editor.moveRightRaw();
        } else {
            editor.moveLeftRaw();
        }
    }

    @Override
    public void redo() {
        if (direction.equals("LEFT")) {
            editor.moveLeftRaw();
        } else {
            editor.moveRightRaw();
        }
    }
}
