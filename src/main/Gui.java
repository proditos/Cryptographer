package main;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    private final JButton chooseFileButton = new JButton(new ImageIcon(FOLDER_ICON_PATH));
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
        initContainer(container);
    }

    private void initContainer(Container container) {
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        container.add(keyLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(keyTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(fileLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        container.add(chooseFileButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        container.add(fileNameLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        container.add(resultOrInputLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        container.add(resultOrInputTextArea, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        container.add(codeButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        container.add(decodeButton, constraints);

        codeButton.addActionListener(e -> {
            String input = resultOrInputTextArea.getText();
            String key = keyTextField.getText();
            Coder.codeIntoFile(input, key);
        });

        decodeButton.addActionListener(e -> {
            File selectedFile = fileChooser.getSelectedFile();
            String fullFileName = (selectedFile == null) ? (null) : (selectedFile.getAbsolutePath());
            String key = keyTextField.getText();
            String result = Coder.decodeFromFile(fullFileName, key);
            resultOrInputTextArea.setText(result);
        });

        chooseFileButton.addActionListener(e -> {
            fileChooser.setDialogTitle("Select encrypted file");
            fileChooser.setFileFilter(FILTER);
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                String fileName = fileChooser.getSelectedFile().getName();
                fileName += " is selected";
                fileNameLabel.setText(fileName);
            } else {
                fileChooser.setSelectedFile(null);
                fileNameLabel.setText("No file selected");
            }
        });
    }
}
