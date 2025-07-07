import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The main coordinator class of the library system.
 * Responsible for reading input files, parsing data into objects,
 * and initializing the command processor.
 */
public class LibraryManagementSystem {

    /**
     * Coordinates the overall execution of the library system by
     * loading data and delegating commands to the CommandProcessor.
     *
     * @param args command-line arguments: items file, users file, commands file, and output file
     */
    public void run(String[] args) {

        String itemsFile = args[0];
        String usersFile = args[1];
        String commandsFile = args[2];
        String outputFile = args[3];
        try {
            new PrintWriter(outputFile).close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<String[]> items = TextFileHandler.readTxt(itemsFile);
        List<String[]> users = TextFileHandler.readTxt(usersFile);
        List<String[]> commands = TextFileHandler.readTxt(commandsFile);

        Map<Integer, LibraryItem> itemMap = new HashMap<>();
        for (String[] itemRow : items) {
            LibraryItem item = ItemParser.parse(itemRow);
            itemMap.put(item.id, item);
        }

        Map<Integer, User> userMap = new HashMap<>();
        for (String[] userRow : users) {
            User user = UserParser.parse(userRow);
            userMap.put(user.id, user);
        }

        CommandProcessor processor = new CommandProcessor(itemMap, userMap, outputFile);

        for (String[] command : commands) {
            processor.processCommand(command);
        }

    }
}