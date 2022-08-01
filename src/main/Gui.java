package main;

import main.encoder.*;
import java.awt.*;
import java.io.File;
import javax.swing.*;

/**
 * @author Vladislav Konovalov
 */
public final class Gui extends JFrame {
    private static final Encoder ENCODER = EncoderFactory.getEncoder(EncoderType.XOR);
    private static final String PROGRAM_TITLE = "Cryptographer";
    private static final Font PRIMARY_FONT = new Font("Arial", Font.PLAIN, 20);
    private static final Font SECONDARY_FONT = new Font("Arial", Font.PLAIN, 14);
    private static final Color RED = new Color(180, 0, 0);
    private static final Color GREEN = new Color(0, 180, 0);

    private final JLabel keyLabel = new JLabel("Key:");
    private final JLabel fileLabel = new JLabel("File:");
    private final JLabel fileStatusLabel = new JLabel("No file selected");
    private final JLabel resultOrInputLabel = new JLabel("Result or input:");
    private final JButton chooseFileButton = new JButton(" Select file", FileService.getFolderIcon());
    private final JButton codeButton = new JButton("Encode");
    private final JButton decodeButton = new JButton("Decode");
    private final JTextField keyTextField = new JTextField();
    private final JTextArea resultOrInputTextArea = new JTextArea(17, 25);
    private final JFileChooser fileChooser = new JFileChooser();
    private final JScrollPane scrollPane = new JScrollPane(resultOrInputTextArea);

    public Gui() throws HeadlessException {
        super(PROGRAM_TITLE);
        this.setBounds(600, 200, 400, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(FileService.getApplicationImage());

        Container container = this.getContentPane();
        initContainer(container);
    }

    private void initContainer(Container container) {
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.LINE_START;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        keyLabel.setFont(PRIMARY_FONT);
        constraints.insets = new Insets(13, 20, 0, 20);
        container.add(keyLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 20, 10, 20);
        keyTextField.setFont(PRIMARY_FONT);
        keyTextField.setMargin(new Insets(6, 6, 6, 6));
        container.add(keyTextField, constraints);
        constraints.fill = GridBagConstraints.NONE;

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 20, 0, 20);
        fileLabel.setFont(PRIMARY_FONT);
        container.add(fileLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(0, 20, 10, 10);
        chooseFileButton.setFont(PRIMARY_FONT);
        container.add(chooseFileButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = new Insets(0, 0, 10, 20);
        constraints.anchor = GridBagConstraints.LINE_END;
        fileStatusLabel.setFont(PRIMARY_FONT);
        fileStatusLabel.setForeground(RED);
        container.add(fileStatusLabel, constraints);
        constraints.anchor = GridBagConstraints.LINE_START;

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0, 20, 0, 20);
        resultOrInputLabel.setFont(PRIMARY_FONT);
        container.add(resultOrInputLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 20, 10, 20);
        resultOrInputTextArea.setFont(SECONDARY_FONT);
        resultOrInputTextArea.setMargin(new Insets(5, 5, 5, 5));
        container.add(scrollPane, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(0, 20, 16, 5);
        codeButton.setFont(PRIMARY_FONT);
        codeButton.setPreferredSize(new Dimension(167, 40));
        container.add(codeButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.insets = new Insets(0, 5, 16, 20);
        constraints.anchor = GridBagConstraints.LINE_END;
        decodeButton.setFont(PRIMARY_FONT);
        decodeButton.setPreferredSize(new Dimension(167, 40));
        container.add(decodeButton, constraints);

        codeButton.addActionListener(e -> {
            String input = resultOrInputTextArea.getText();
            String key = keyTextField.getText();
            String result = ENCODER.encode(input, key);
            FileService.writeToFile(result.getBytes());
        });

        decodeButton.addActionListener(e -> {
            File selectedFile = fileChooser.getSelectedFile();
            String fullFileName = (selectedFile == null) ? (null) : (selectedFile.getAbsolutePath());
            String input = new String(FileService.readFromFile(fullFileName));
            String key = keyTextField.getText();
            String result = ENCODER.decode(input, key);
            resultOrInputTextArea.setText(result);
        });

        chooseFileButton.addActionListener(e -> {
            fileChooser.setDialogTitle("Select encrypted file");
            fileChooser.setFileFilter(FileService.getFileFilter());
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                fileStatusLabel.setText("File is selected");
                fileStatusLabel.setForeground(GREEN);
            } else {
                fileChooser.setSelectedFile(null);
                fileStatusLabel.setText("No file selected");
                fileStatusLabel.setForeground(RED);
            }
        });
    }
}
