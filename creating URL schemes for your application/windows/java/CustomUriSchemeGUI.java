import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomUriSchemeGUI extends JFrame {
    private JTextField schemeField;
    private JTextField pathField;
    private JButton addButton;

    public CustomUriSchemeGUI() {
        setTitle("Custom URI Scheme Creator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel schemeLabel = new JLabel("URI Scheme:");
        schemeLabel.setBounds(10, 20, 80, 25);
        panel.add(schemeLabel);

        schemeField = new JTextField(20);
        schemeField.setBounds(100, 20, 250, 25);
        panel.add(schemeField);

        JLabel pathLabel = new JLabel("Executable Path:");
        pathLabel.setBounds(10, 60, 100, 25);
        panel.add(pathLabel);

        pathField = new JTextField(20);
        pathField.setBounds(120, 60, 230, 25);
        panel.add(pathField);

        addButton = new JButton("Add Scheme");
        addButton.setBounds(130, 100, 120, 25);
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomUriScheme(schemeField.getText(), pathField.getText());
            }
        });

        add(panel);
    }

    private void addCustomUriScheme(String scheme, String path) {
        try {
            String[] commands = {
                "reg", "add", "HKEY_CLASSES_ROOT\\" + scheme,
                "/ve", "/t", "REG_SZ", "/d", "URL: " + scheme + " protocol",
                "/f"
            };

            ProcessBuilder pb = new ProcessBuilder(commands);
            pb.redirectErrorStream(true);
            Process p = pb.start();
            p.waitFor();

            // Additional registry commands to add the shell, open, and command keys
            String[] commands2 = {
                "reg", "add", "HKEY_CLASSES_ROOT\\" + scheme + "\\shell",
                "/f"
            };
            pb = new ProcessBuilder(commands2);
            pb.redirectErrorStream(true);
            p = pb.start();
            p.waitFor();

            String[] commands3 = {
                "reg", "add", "HKEY_CLASSES_ROOT\\" + scheme + "\\shell\\open",
                "/f"
            };
            pb = new ProcessBuilder(commands3);
            pb.redirectErrorStream(true);
            p = pb.start();
            p.waitFor();

            String[] commands4 = {
                "reg", "add", "HKEY_CLASSES_ROOT\\" + scheme + "\\shell\\open\\command",
                "/ve", "/t", "REG_SZ", "/d", "\"" + path + "\" \"%1\"",
                "/f"
            };
            pb = new ProcessBuilder(commands4);
            pb.redirectErrorStream(true);
            p = pb.start();
            p.waitFor();

            JOptionPane.showMessageDialog(this, "URI scheme added successfully!");
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to add URI scheme.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CustomUriSchemeGUI().setVisible(true);
            }
        });
    }
}
