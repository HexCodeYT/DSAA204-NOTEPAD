package gui;

import editor.NotepadEditor;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.*;
import search.KMPSearch;

public class MainFrame extends JFrame {

    private final NotepadEditor editor;

    private JTextArea textArea;
    private JTextField searchField;
    private JLabel cursorLabel;
    private JLabel searchResultLabel;
    private JLabel statusLabel;

    public MainFrame() {
        editor = new NotepadEditor();

        setTitle("DSAA204 Notepad - Stack Based Text Editor");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buildUI();
        refreshText();
    }

    private void buildUI() {
        setLayout(new BorderLayout(10, 10));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.getCaret().setVisible(true);
        textArea.setFocusable(true);
        textArea.getCaret().setVisible(false);
        textArea.getCaret().setSelectionVisible(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(4, 1, 5, 5));

        JPanel cursorPanel = new JPanel();

        JButton leftButton = new JButton("←");
        leftButton.addActionListener(e -> {
            editor.moveLeft();
            refreshText();
        });

        JButton rightButton = new JButton("→");
        rightButton.addActionListener(e -> {
            editor.moveRight();
            refreshText();
        });

        JButton backspaceButton = new JButton("Backspace");
        backspaceButton.addActionListener(e -> {
            editor.backspace();
            refreshText();
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            editor.delete();
            refreshText();
        });

        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> {
            editor.undo();
            refreshText();
        });

        JButton redoButton = new JButton("Redo");
        redoButton.addActionListener(e -> {
            editor.redo();
            refreshText();
        });

        cursorLabel = new JLabel("Cursor: 0");

        cursorPanel.add(leftButton);
        cursorPanel.add(rightButton);
        cursorPanel.add(backspaceButton);
        cursorPanel.add(deleteButton);
        cursorPanel.add(undoButton);
        cursorPanel.add(redoButton);
        cursorPanel.add(cursorLabel);

        bottomPanel.add(cursorPanel);

        JPanel searchPanel = new JPanel();

        searchPanel.add(new JLabel("Search:"));

        searchField = new JTextField(20);
        searchPanel.add(searchField);

        JButton searchButton = new JButton("Find");
        searchButton.addActionListener(e -> searchText());
        searchPanel.add(searchButton);

        searchResultLabel = new JLabel("Result: ");
        searchPanel.add(searchResultLabel);

        bottomPanel.add(searchPanel);

        JPanel helpPanel = new JPanel();
        helpPanel.add(new JLabel("Type directly in the editor. Use ←/→, Backspace, Delete, Ctrl+Z, Ctrl+Y."));
        bottomPanel.add(helpPanel);

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Characters: 0 | Cursor: 0");
        statusPanel.add(statusLabel);
        bottomPanel.add(statusPanel);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void handleKeyPress(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
            editor.undo();
            refreshText();
            e.consume();
            return;
        }

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y) {
            editor.redo();
            refreshText();
            e.consume();
            return;
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                editor.moveLeft();
                break;

            case KeyEvent.VK_RIGHT:
                editor.moveRight();
                break;

            case KeyEvent.VK_BACK_SPACE:
                editor.backspace();
                break;

            case KeyEvent.VK_DELETE:
                editor.delete();
                break;

            case KeyEvent.VK_ENTER:
                editor.insert('\n');
                break;

            case KeyEvent.VK_TAB:
                editor.insert('\t');
                break;

            default:
                if (e.isActionKey()
                        || e.isControlDown()
                        || e.isAltDown()
                        || e.isMetaDown()) {
                    break;
                }

                char c = e.getKeyChar();

                if (c != KeyEvent.CHAR_UNDEFINED
                        && !Character.isISOControl(c)) {
                    editor.insert(c);
                }

                break;
        }

        refreshText();
        e.consume();
    }

    private void searchText() {
        String pattern = searchField.getText();

        if (pattern == null || pattern.isEmpty()) {
            searchResultLabel.setText("Result: enter search text");
            textArea.requestFocusInWindow();
            return;
        }

        List<Integer> results = KMPSearch.search(editor.getText(), pattern);

        if (results.isEmpty()) {
            searchResultLabel.setText("Result: not found");
        } else {
            searchResultLabel.setText("Result: found at " + results);
        }

        textArea.requestFocusInWindow();
    }

    private void refreshText() {
        String text = editor.getText();
        int cursor = editor.getCursorPosition();

        textArea.setText(text);

        try {
            textArea.setCaretPosition(
                    Math.min(cursor, textArea.getDocument().getLength())
            );
        } catch (Exception ignored) {
        }

        cursorLabel.setText("Cursor: " + cursor);
        statusLabel.setText("Characters: " + text.length() + " | Cursor: " + cursor);
        textArea.requestFocusInWindow();
    }
}
