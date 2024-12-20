import java.io.*;
import java.sql.*;
import java.util.*;

public class ExceptionExamples {

    public static void main(String[] args) {
        handleIOException();
        handleFileNotFoundException();
        handleEOFException();
        handleSQLException();
        handleClassNotFoundException();
    }

    // 1. IOException
    public static void handleIOException() {
        try {
            FileInputStream fileInput = new FileInputStream("example.txt");
            fileInput.close(); // Close the stream
            fileInput.read();  // Attempt to read from the closed stream
        } catch (IOException e) {
            System.out.println("IOException caught: " + e.getMessage());
            System.out.println("Tip: Ensure the file stream is open before reading.");
        }
    }

    // 2. FileNotFoundException
    public static void handleFileNotFoundException() {
        try {
            FileInputStream fileInput = new FileInputStream("nonexistent_file.txt");
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException caught: " + e.getMessage());
            System.out.println("Tip: Check the file path and ensure the file exists.");
        }
    }

    // 3. EOFException
    public static void handleEOFException() {
        try {
            // Simulate EOFException by reading beyond a file's content
            FileInputStream fileInput = new FileInputStream("example.txt");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            while (true) {
                objectInput.readObject(); // Continuously read until EOF is reached
            }
        } catch (EOFException e) {
            System.out.println("EOFException caught: End of file reached unexpectedly.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    // 4. SQLException
    public static void handleSQLException() {
        try {
            // connecting to an invalid database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nonexistent_db", "user", "password");
        } catch (SQLException e) {
            System.out.println("SQLException caught: " + e.getMessage());
            System.out.println("Tip: Verify database credentials and the database existence.");
        }
    }

    // 5. ClassNotFoundException
    public static void handleClassNotFoundException() {
        try {
            // loading a non-existent class
            Class.forName("com.nonexistent.ClassName");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException caught: " + e.getMessage());
            System.out.println("Tip: Ensure the class name is correct and in the classpath.");
        }
    }
}
