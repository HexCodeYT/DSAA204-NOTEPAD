# DSAA204 Notepad

A simplified Notepad application built in Java for the DSAA204 unit.

The main goal of this project was to implement common text editor functionality using data structures and algorithms we learned in the class, rather than relying on Java's built-in text editing capabilities.

## Features of this Application

- Insert characters at the current cursor position
- Backspace support (MacOS: delete key)
- Delete key support (MacOS: n/a)
- Left and right cursor movement
- Undo operations
- Redo operations
- Text search using the Knuth-Morris-Pratt (KMP) algorithm
- Simple Java Swing graphical user interface
- Automated test suite

## Project Structure

```text
src/
├── Main.java
├── editor/
│   └── NotepadEditor.java
├── search/
│   └── KMPSearch.java
├── gui/
│   └── MainFrame.java
└── actions/
    ├── Action.java
    ├── InsertAction.java
    ├── BackspaceAction.java
    ├── DeleteAction.java
    └── MoveAction.java

test/
└── NotepadEditorTest.java
```

## How It Works

The editor is built around two stacks.

```text
Left Stack  | Cursor | Right Stack
```

Characters before the cursor are stored in the left stack, while characters after the cursor are stored in the right stack.

Moving the cursor left transfers a character from the left stack to the right stack.

Moving the cursor right transfers a character from the right stack to the left stack.

This allows cursor movement, insertion and deletion operations to be performed efficiently.

## Undo and Redo

Undo and redo functionality is implemented using additional stacks that store actions performed by the user.

Whenever an operation is performed, it is pushed onto the undo stack.

Undo moves actions to the redo stack.

Redo replays actions from the redo stack and moves them back to the undo stack.

## Search Algorithm

Search functionality uses the Knuth-Morris-Pratt (KMP) string matching algorithm.

We chose KMP because it avoids unnecessary character comparisons and provides efficient pattern searching with a time complexity of:

```text
O(n + m)
```

where:

- n = length of the text
- m = length of the search pattern

## Running the Application

Compile:

```bash
javac -d out src/Main.java src/editor/*.java src/actions/*.java src/search/*.java src/gui/*.java
```

Run:

```bash
java -cp out Main
```

## Running Tests

Compile:

```bash
javac -d out src/editor/*.java src/actions/*.java src/search/*.java test/*.java
```

Run:

```bash
java -cp out NotepadEditorTest
```

## Test Coverage

The automated tests cover:

- Character insertion
- Backspace operations
- Delete key operations
- Cursor movement
- Insertions in the middle of text
- Undo operations
- Redo operations
- Search functionality
- Edge cases for search results