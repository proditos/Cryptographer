package main;

import java.awt.*;
import java.io.File;
import javax.swing.*;

/**
 * @author Vladislav Konovalov
 */
public final class Gui extends JFrame {
    private static final String PROGRAM_TITLE = "Cryptographer";
    private static final Font MAIN_FONT = new Font("Arial", Font.PLAIN, 20);
    private static final Font SECONDARY_FONT = new Font("Arial", Font.PLAIN, 16);

    private final JLabel keyLabel = new JLabel("Key:");
    private final JLabel fileLabel = new JLabel("File:");
    private final JLabel fileNameLabel = new JLabel("No file selected");
    private final JLabel resultOrInputLabel = new JLabel("Result or input:");
    private final JTextField keyTextField = new JTextField();
    private final JFileChooser fileChooser = new JFileChooser();
    private final JButton chooseFileButton = new JButton(FileService.getFolderIcon());
    private final JTextArea resultOrInputTextArea = new JTextArea();
    private final JButton codeButton = new JButton("code");
    private final JButton decodeButton = new JButton("decode");

    public Gui() throws HeadlessException {
        super(PROGRAM_TITLE);
        this.setBounds(0, 0, 500, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(FileService.getApplicationImage());

        Container container = this.getContentPane();
        initContainer(container);
    }

    private void initContainer(Container container) {
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        keyLabel.setFont(MAIN_FONT);
        container.add(keyLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        keyTextField.setFont(MAIN_FONT);
        container.add(keyTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        fileLabel.setFont(MAIN_FONT);
        container.add(fileLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        container.add(chooseFileButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        fileNameLabel.setFont(MAIN_FONT);
        container.add(fileNameLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        resultOrInputLabel.setFont(MAIN_FONT);
        container.add(resultOrInputLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        resultOrInputTextArea.setFont(SECONDARY_FONT);
        container.add(resultOrInputTextArea, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        codeButton.setFont(MAIN_FONT);
        container.add(codeButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        decodeButton.setFont(MAIN_FONT);
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
            fileChooser.setFileFilter(Coder.getFilter());
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
