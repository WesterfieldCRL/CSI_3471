import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
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
            tableModel.addColumn("Delete");
            tableModel.addColumn("Edit");

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }



            table1.setModel(tableModel);
            reader.close();

            //Prevent edits so they can be done via "special buttons!"
            table1.setDefaultEditor(Object.class, null);

            table1.getColumn("Delete").setCellRenderer(new ButtonRenderer());
            table1.getColumn("Delete").setCellEditor(
                    new ButtonEditor(new JCheckBox(), 1));

            table1.getColumn("Edit").setCellRenderer(new ButtonRenderer());
            table1.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox(), 2));

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading CSV file.");
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;

        private String label;

        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox, int option) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            if (option == 1) {
                button.addActionListener(e -> {
                    int selectedRow = table1.getSelectedRow();
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();
                    if (selectedRow != -1) {
                        model.removeRow(selectedRow);
                    }
                    fireEditingStopped();
                });
            }
            else if (option == 2)
            {
                button.addActionListener(e -> {
                    int selectedRow = table1.getSelectedRow();
                    int selectedColumn = table1.getSelectedColumn();
                    DefaultTableModel model = (DefaultTableModel) table1.getModel();

                    String[] currData = new String[selectedColumn];

                    for (int i = 0; i < selectedColumn - 2; i++)
                    {
                        currData[i] = (String) model.getValueAt(selectedRow, i);
                    }

                    String[] data = getRowData(currData);

                    for (String temp : data)
                    {
                        System.out.println(temp);
                    }

                    //model.setValueAt("yo mama", selectedRow, 0);

                    fireEditingStopped();
                });
            }
        }

        public String[] getRowData(String[] inputData)
        {
            ArrayList<JTextField> inputFields = new ArrayList<>();
            JFrame newFrame = new JFrame();
            newFrame.setTitle("Input Form");
            newFrame.setSize(300, 200);
            newFrame.setLayout(new GridLayout(0, 2));

            for (String initialValue : inputData) {
                JLabel label = new JLabel("Enter string:");
                newFrame.add(label);

                JTextField inputField = new JTextField(20);
                inputField.setText(initialValue);
                newFrame.add(inputField);

                inputFields.add(inputField);
            }

            String[] outputData = new String[inputFields.size()];

            JButton submitButton = new JButton("Submit");
            newFrame.add(submitButton);

            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ArrayList<String> inputValues = new ArrayList<>();
                    for (JTextField field : inputFields) {
                        String inputValue = field.getText();
                        inputValues.add(inputValue);
                    }

                    for (int i = 0; i < inputValues.size(); i++) {
                        outputData[i] = inputValues.get(i);
                    }

                    newFrame.dispose();
                }
            });

            newFrame.setVisible(true);

            return outputData;
        }

        /*public class StringArrayInputForm extends JFrame {
            private ArrayList<JTextField> inputFields;

            public StringArrayInputForm(String[] initialValues) {
                inputFields = new ArrayList<>();

                setTitle("String Array Input Form");
                setSize(300, 200);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
                setLayout(new GridLayout(0, 2));

                for (String initialValue : initialValues) {
                    JLabel label = new JLabel("Enter string:");
                    add(label);

                    JTextField inputField = new JTextField(20);
                    inputField.setText(initialValue);
                    add(inputField);

                    inputFields.add(inputField);
                }

                JButton submitButton = new JButton("Submit");
                add(submitButton);

                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ArrayList<String> inputValues = new ArrayList<>();
                        for (JTextField field : inputFields) {
                            String inputValue = field.getText();
                            inputValues.add(inputValue);
                        }

                        // You can process the inputValues list here
                        for (String value : inputValues) {
                            // Process each string value as needed
                            System.out.println(value);
                        }
                    }
                });

                setVisible(true);
            }
        }*/

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                //thing
            }
            isPushed = false;
            return label;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
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
