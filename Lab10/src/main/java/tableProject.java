import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class tableProject {
    private JTable table1;
    private JPanel panel1;
    private JButton loadFile;
    private JLabel label1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private int columnIndex = 0;

    public tableProject() {
        JFrame frame = new JFrame("CSV Table Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(panel1);


        loadFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    loadCSVData(filePath);
                }
            }
        });

        textField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }
        });

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the selectedColumnIndex based on the selected item
                columnIndex = comboBox1.getSelectedIndex();
                filterTable();
            }
        });

        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    private void loadCSVData(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String[] header = {"Animal", "ID", "Name", "Age", "Weight", "Breed", "Color"};

            String line;
            DefaultTableModel tableModel = new DefaultTableModel();

            comboBox1.addItem("Animal");
            comboBox1.addItem("ID");
            comboBox1.addItem("Name");
            comboBox1.addItem("Age");
            comboBox1.addItem("Weight");
            comboBox1.addItem("Breed");
            comboBox1.addItem("Color");
            tableModel.addColumn("Animal");
            tableModel.addColumn("ID");
            tableModel.addColumn("Name");
            tableModel.addColumn("Age");
            tableModel.addColumn("Weight");
            tableModel.addColumn("Breed");
            tableModel.addColumn("Color");

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }

            table1.setModel(tableModel);
            reader.close();

            //createFilterTextFields(header);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading CSV file.");
        }
    }

    private void filterTable() {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table1.setRowSorter(sorter);



        String filterText = textField1.getText();
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filterText, columnIndex));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new tableProject();
        });
    }
}
