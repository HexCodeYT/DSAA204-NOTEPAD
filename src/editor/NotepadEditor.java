package editor;

import java.util.Stack;

public class NotepadEditor {
    private Stack<Character> leftStack;
    private Stack<Character> rightStack;

    public NotepadEditor() {
        leftStack = new Stack<>();
        rightStack = new Stack<>();
    }

    public void insert(char c) {
        leftStack.push(c);
    }

    public void backspace() {
        if (!leftStack.isEmpty()) {
            leftStack.pop();
        }
    }

    public void delete() {
        if (!rightStack.isEmpty()) {
            rightStack.pop();
        }
    }

    public void moveLeft() {
        if (!leftStack.isEmpty()) {
            rightStack.push(leftStack.pop());
        }
    }

    public void moveRight() {
        if (!rightStack.isEmpty()) {
            leftStack.push(rightStack.pop());
        }
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
    }
}