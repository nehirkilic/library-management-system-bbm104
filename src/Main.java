/**
 * Entry point of the Library Management System.
 * It initializes the system by calling the main controller class.
 */
public class Main {

    /**
     * Main method that triggers the execution of the system.
     *
     * @param args command-line arguments including file paths
     */
    public static void main(String[] args) {
        new LibraryManagementSystem().run(args);
    }
}
