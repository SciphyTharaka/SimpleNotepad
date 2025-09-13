import javax.swing.*;
import java.io.File;

public class SimpleNotepad {

    public static void main(String[] args) {
        // Create main frame
        JFrame frame = new JFrame("Simple Notepad");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create text area with scroll pane
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem openFile = new JMenuItem("Open");
        openFile.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    textArea.read(new java.io.FileReader(fileChooser.getSelectedFile()), null);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error opening file: " + ex.getMessage());
                }
            }
        });

        JMenuItem saveFile = new JMenuItem("Save");
        saveFile.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(frame);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fileChooser.getSelectedFile();
                    textArea.write(new java.io.FileWriter(file));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error saving file: " + ex.getMessage());
                }
            }
        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> System.exit(0));

        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.addSeparator(); // Adds a separator line
        fileMenu.add(exit);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");

        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(e -> textArea.cut());

        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(e -> textArea.copy());

        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(e -> textArea.paste());

        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(paste);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");

        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e -> JOptionPane.showMessageDialog(frame,
                "Simple Notepad\nCreated by: Tharaka\nID: 16820",
                "About",
                JOptionPane.INFORMATION_MESSAGE));

        helpMenu.add(about);

        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
}

