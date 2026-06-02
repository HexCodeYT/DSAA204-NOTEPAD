package editor;

import java.util.Stack;
import actions.Action;

public class NotepadEditor {
    private Stack<Character> leftStack;
    private Stack<Character> rightStack;

    private Stack<Action> undoStack;
    private Stack<Action> redoStack;

    public NotepadEditor() {
        leftStack = new Stack<>();
        rightStack = new Stack<>();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void insert(char c) {
        insertRaw(c);
        undoStack.push(new actions.InsertAction(this, c));
        redoStack.clear();
    }

    public void backspace() {
        if (!leftStack.isEmpty()) {
            char removed = leftStack.pop();
            undoStack.push(new actions.BackspaceAction(this, removed));
            redoStack.clear();
        }
    }

    public void delete() {
        if (!rightStack.isEmpty()) {
            char removed = rightStack.pop();
            undoStack.push(new actions.DeleteAction(this, removed));
            redoStack.clear();
        }
    }

    public void moveLeft() {
        if (!leftStack.isEmpty()) {
            moveLeftRaw();
            undoStack.push(new actions.MoveAction(this, "LEFT"));
            redoStack.clear();
        }
    }

    public void moveRight() {
        if (!rightStack.isEmpty()) {
            moveRightRaw();
            undoStack.push(new actions.MoveAction(this, "RIGHT"));
            redoStack.clear();
        }
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Action action = undoStack.pop();
            action.undo();
            redoStack.push(action);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Action action = redoStack.pop();
            action.redo();
            undoStack.push(action);
        }
    }

    public void insertRaw(char c) {
        leftStack.push(c);
    }

    public void backspaceRaw() {
        if (!leftStack.isEmpty()) {
            leftStack.pop();
        }
    }

    public void deleteRaw() {
        if (!rightStack.isEmpty()) {
            rightStack.pop();
        }
    }

    public void moveLeftRaw() {
        if (!leftStack.isEmpty()) {
            rightStack.push(leftStack.pop());
        }
    }

    public void moveRightRaw() {
        if (!rightStack.isEmpty()) {
            leftStack.push(rightStack.pop());
        }
    }

    public void restoreBackspace(char c) {
        leftStack.push(c);
    }

    public void restoreDelete(char c) {
        rightStack.push(c);
    }

    public String getText() {
        StringBuilder result = new StringBuilder();

        for (Character c : leftStack) {
            result.append(c);
        }

        for (int i = rightStack.size() - 1; i >= 0; i--) {
            result.append(rightStack.get(i));
        }

        return result.toString();
    }

    public int getCursorPosition() {
        return leftStack.size();
    }

    public void clear() {
        leftStack.clear();
        rightStack.clear();
        undoStack.clear();
        redoStack.clear();
    }
}