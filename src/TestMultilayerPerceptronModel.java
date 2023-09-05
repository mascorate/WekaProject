import java.io.BufferedReader;
import java.io.FileReader;
//import weka.classifiers.Evaluation;
//import weka.classifiers.trees.J48;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

import java.util.Scanner;

public class TestMultilayerPerceptronModel {
    public static void main(String[] args) throws Exception {
        // Load the ARFF dataset
        BufferedReader reader = new BufferedReader(new FileReader("arff/car.arff"));
        Instances data = new Instances(reader);
        reader.close();

        // Set the class index to the last attribute (class)
        data.setClassIndex(data.numAttributes() - 1);

        // Load the J48 model from file
//        J48 model = (J48) weka.core.SerializationHelper.read("model/model.model");
        MultilayerPerceptron model = (MultilayerPerceptron) weka.core.SerializationHelper.read("model/carmodel.model");

        // Accept input from user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the values for the attributes:");
        System.out.print("buying: ");
        String buying = scanner.nextLine();
        System.out.print("maint: ");
        String maint = scanner.nextLine();
        System.out.print("doors: ");
        String doors = scanner.nextLine();
        System.out.print("persons: ");
        String persons = scanner.nextLine();
        System.out.print("lugboot: ");
        String lugboot = scanner.nextLine();
        System.out.print("safety: ");
        String safety = scanner.nextLine();
        scanner.close();
        
        // Create a new instance with the input values
        weka.core.Instance instance = new weka.core.DenseInstance(7);
//        instance.setValue(0, buying);
//        instance.setValue(1, maint);
//        instance.setValue(2, doors);
//        instance.setValue(3, persons);
//        instance.setValue(4, lugboot);
//        instance.setValue(5, safety);
        
        instance.setValue(data.attribute("buying"), buying);
        instance.setValue(data.attribute("maint"), maint);
        instance.setValue(data.attribute("doors"), doors);
        instance.setValue(data.attribute("persons"), persons);
        instance.setValue(data.attribute("lugboot"), lugboot);
        instance.setValue(data.attribute("safety"), safety);

        instance.setDataset(data);

        // Classify the instance using the loaded model
        double classIndex = model.classifyInstance(instance);
        String className = data.classAttribute().value((int) classIndex);

        // Print the predicted class
        System.out.println("Predicted class: " + className);
    }
}