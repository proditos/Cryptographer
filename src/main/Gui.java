package main;

import java.awt.*;
import javax.swing.*;

/**
 * @author Vladislav Konovalov
 */
public final class Gui extends JFrame {
    private static final String RESOURCES_ROOT = "src/main/resources/";
    private static final String FOLDER_ICON_PATH = RESOURCES_ROOT + "folder_icon.png";
    private static final String APP_ICON_PATH = RESOURCES_ROOT + "icon.png";
    private static final FileFilter FILTER = new FileNameExtensionFilter("Binary files", "bin");

    private final JLabel keyLabel = new JLabel("Key:");
    private final JLabel fileLabel = new JLabel("File:");
    private final JLabel fileNameLabel = new JLabel("No file selected");
    private final JLabel resultOrInputLabel = new JLabel("Result or input:");
    private final JTextField keyTextField = new JTextField();
    private final JFileChooser fileChooser = new JFileChooser();
    private final JTextArea resultOrInputTextArea = new JTextArea();
    private final JButton codeButton = new JButton("code");
    private final JButton decodeButton = new JButton("decode");

    public Gui() throws HeadlessException {
        super("Cryptographer");
        this.setBounds(0, 0, 500, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(FileService.getImage(APP_ICON_PATH));

        Container container = this.getContentPane();
        init(container);
    }

    private void init(Container container) {
        container.setLayout(new GridLayout(7, 1));

        codeButton.addActionListener(e -> {
            String input = resultOrInputTextArea.getText();
            String key = keyTextField.getText();
            Coder.codeIntoFile(input, key);
        });

        decodeButton.addActionListener(e -> {
            String fullFileName = fileChooser.getSelectedFile().getAbsolutePath();
            String key = keyTextField.getText();
            String result = Coder.decodeFromFile(fullFileName, key);
            resultOrInputTextArea.setText(result);
        });

        container.add(keyLabel);
        container.add(keyTextField);
        container.add(fileLabel);
        container.add(fileChooser);
        container.add(fileNameLabel);
        container.add(resultOrInputLabel);
        container.add(resultOrInputTextArea);
        container.add(codeButton);
        container.add(decodeButton);
    }
}
