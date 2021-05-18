package ru.vsu.cs.ostroverkhov;

import java.io.PrintStream;
import java.util.Scanner;
import ru.vsu.cs.ostroverkhov.utils.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of file: ");
        String fileName = scanner.nextLine();
        String[][] arr = ArrayUtils.readStringArray2FromFile(fileName);
        assert arr != null;
        Hash dictionary = new Hash();
        try {
            for (String[] strings : arr) {
                dictionary.addElement(strings[0], strings[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String command = " ";
        while (!command.equals("--exit")) {
            System.out.println("Input: ");
            System.out.println("<--size>            //Get the size of dictionary");
            System.out.println("<--add>             //Add the element");
            System.out.println("<--clear>           //Clear the dictionary");
            System.out.println("<--isEmpty>         //Checking for the presence of elements in the dictionary ");
            System.out.println("<--findOnValue>     //Searching elements by value");
            System.out.println("<--removeOnKey>     //Remove elements by key");
            System.out.println("<--removeOnValue>   //Remove elements by value");
            System.out.println("<--print>           //Print the dictionary");
            System.out.println("<--saveInFile>      //Save in file");
            System.out.println("<--exit>            //Exit from the program");
            command = scanner.nextLine();
            if (command.equals("--size")) {
                System.out.println("Size of dictionary: " + dictionary.size());

            } else if (command.equals("--add")) {
                System.out.println("Enter key: ");
                String key = scanner.nextLine();
                System.out.println("Enter value: ");
                String value = scanner.nextLine();
                dictionary.addElement(key, value);
                System.out.println("The element was added ");
            } else if (command.equals("--clear")) {
                dictionary.clear();
                System.out.println("The dictionary cleared ");
            } else if (command.equals("--isEmpty")) {
                if (dictionary.isEmpty()) {
                    System.out.println("The dictionary is empty ");
                } else {
                    System.out.println("The dictionary isn't empty ");
                }

            } else if (command.equals("--findOnValue")) {
                System.out.println("Enter the value: ");
                String value = scanner.nextLine();
                String[] resultingArr = dictionary.findOnValue(value);
                System.out.println("Keys by value: ");
                for (String n : resultingArr) {
                    System.out.println(n);
                }

            } else if (command.equals("--removeOnKey")) {
                System.out.println("Enter the key: ");
                String key = scanner.nextLine();
                dictionary.removeKey(key);
                System.out.println("Elements with this key deleted");

            } else if (command.equals("--removeOnValue")) {
                System.out.println("Enter the value: ");
                String value = scanner.nextLine();
                dictionary.removeValue(value);
                System.out.println("Elements with this value deleted");

            } else if (command.equals("--print")) {
                dictionary.print();
            } else if (command.equals("--saveInFile")) {
                String[][] outputArr = dictionary.convertHashToArray();
                System.out.println("Enter the name of file: ");
                String outputFile = scanner.nextLine();
                PrintStream out = (outputFile != null) ? new PrintStream(outputFile) : System.out;
                for (int i = 0; i < outputArr.length; i++) {
                    out.printf("%s %s\n", outputArr[i][0], outputArr[i][1]);
                }
                out.close();
                System.out.println("File saved successfully");

            } else if (!command.equals("--exit")) {
                System.out.println("Invalid command");
            }
        }
    }
}
