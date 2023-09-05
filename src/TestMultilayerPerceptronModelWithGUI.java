import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
//import weka.classifiers.trees.J48;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class TestMultilayerPerceptronModelWithGUI extends JFrame implements ActionListener {
//    private final JTextField buyingField;
//    private final JTextField maintField;
//    private final JTextField doorsField;
//    private final JTextField personsField;
//    private final JTextField lugbootField;
//    private final JTextField safetyField;
	private final JComboBox<String> buyingComboBox;
	private final JComboBox<String> maintComboBox;
	private final JComboBox<String> doorsComboBox;
	private final JComboBox<String> personsComboBox;
	private final JComboBox<String> lugbootComboBox;
	private final JComboBox<String> safetyComboBox;

    private final JLabel resultLabel;
    private final JButton classifyButton;

    public TestMultilayerPerceptronModelWithGUI() throws Exception {
        super("Test MultilayerPerceptron Model");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the GUI components
//        buyingField = new JTextField(5);
//        maintField = new JTextField(5);
//        doorsField = new JTextField(5);
//        personsField = new JTextField(5);
//        lugbootField = new JTextField(5);
//        safetyField = new JTextField(5);
        String[] buyingOptions = {"vhigh", "high", "med", "low"};
        buyingComboBox = new JComboBox<>(buyingOptions);
//        String[] buyingOptions = {"vhigh", "high", "med", "low"};
        maintComboBox = new JComboBox<>(buyingOptions);
        String[] doorsOptions = {"2", "3", "4", "5more"};
        doorsComboBox = new JComboBox<>(doorsOptions);
        String[] personsOptions = {"2", "4", "more"};
        personsComboBox = new JComboBox<>(personsOptions);
        String[] lugbootOptions = {"small", "med", "big"};
        lugbootComboBox = new JComboBox<>(lugbootOptions);
        String[] safetyOptions = {"low", "med", "high"};
        safetyComboBox = new JComboBox<>(safetyOptions);
        resultLabel = new JLabel();
        classifyButton = new JButton("Classify");
        classifyButton.addActionListener(this);

        // Add the GUI components to a panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2));
//        panel.add(new JLabel("buying: "));
//        panel.add(buyingField);
//        panel.add(new JLabel("maint: "));
//        panel.add(maintField);
//        panel.add(new JLabel("doors: "));
//        panel.add(doorsField);
//        panel.add(new JLabel("persons: "));
//        panel.add(personsField);
//        panel.add(new JLabel("lugboot: "));
//        panel.add(lugbootField);
//        panel.add(new JLabel("safetyField: "));
//        panel.add(safetyField);
        panel.add(new JLabel("buying: "));
	    panel.add(buyingComboBox);
	    panel.add(new JLabel("maint: "));
	    panel.add(maintComboBox);
	    panel.add(new JLabel("doors: "));
	    panel.add(doorsComboBox);
	    panel.add(new JLabel("persons: "));
	    panel.add(personsComboBox);
	    panel.add(new JLabel("lugboot: "));
	    panel.add(lugbootComboBox);
	    panel.add(new JLabel("safetyField: "));
	    panel.add(safetyComboBox);
        panel.add(new JLabel(""));
        panel.add(classifyButton);
        panel.add(new JLabel("Result: "));
        panel.add(resultLabel);

        // Add the panel to the frame and show the GUI
        getContentPane().add(panel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        new TestMultilayerPerceptronModelWithGUI();
    }

    public void actionPerformed(ActionEvent event) {
        try {
            // Get the user input values
//            String buying = buyingField.getText();
//            String maint = maintField.getText();
//            String doors = doorsField.getText();
//            String persons = personsField.getText();
//            String lugboot = lugbootField.getText();
//            String safety = safetyField.getText();

         // Load the ARFF dataset
            BufferedReader reader = new BufferedReader(new FileReader("arff/car.arff"));
            Instances data = new Instances(reader);
            reader.close();

            // Set the class index to the last attribute (class)
            data.setClassIndex(data.numAttributes() - 1);

            // Load the J48 model from file
//            J48 model = (J48) weka.core.SerializationHelper.read("model/carmodel.model");
            MultilayerPerceptron model = (MultilayerPerceptron) weka.core.SerializationHelper.read("model/carmodel.model");

            // Create an instance with the user input values
            Instance instance = new DenseInstance(data.numAttributes());
//            instance.setValue(data.attribute("buying"), buying);
//            instance.setValue(data.attribute("maint"), maint);
//            instance.setValue(data.attribute("doors"), doors);
//            instance.setValue(data.attribute("persons"), persons);
//            instance.setValue(data.attribute("lugboot"), lugboot);
//            instance.setValue(data.attribute("safety"), safety);
            
            instance.setValue(data.attribute("buying"), (String) buyingComboBox.getSelectedItem());
            instance.setValue(data.attribute("maint"), (String) maintComboBox.getSelectedItem());
            instance.setValue(data.attribute("doors"), (String) doorsComboBox.getSelectedItem());
            instance.setValue(data.attribute("persons"), (String) personsComboBox.getSelectedItem());
            instance.setValue(data.attribute("lugboot"), (String) lugbootComboBox.getSelectedItem());
            instance.setValue(data.attribute("safety"), (String) safetyComboBox.getSelectedItem());
            instance.setDataset(data);

            // Classify the instance
            double result = model.classifyInstance(instance);

            // Set the result label to the predicted class value
            resultLabel.setText(data.classAttribute().value((int)result));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
