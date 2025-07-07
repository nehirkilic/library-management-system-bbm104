import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


/**
 * This class handles the execution of commands in the library system,
 * such as borrowing and returning items, processing payments, and displaying user/item information.
 */
public class CommandProcessor {
    Map<Integer, LibraryItem> items;
    Map<Integer, User> users;
    String outputFile;
    private LocalDate lastProcessedBorrowDate = null;

    /**
     * Constructs a CommandProcessor with item and user maps and the output file path.
     *
     * @param items      map of library items by ID
     * @param users      map of users by ID
     * @param outputFile path of the output file to write system responses
     */
    public CommandProcessor(Map<Integer, LibraryItem> items, Map<Integer, User> users, String outputFile) {
        this.items = items;
        this.users = users;
        this.outputFile = outputFile;
    }

    /**
     * Determines the type of command and calls the related method to process it.
     *
     * @param parts an array representing the components of the command
     */
    public void processCommand(String[] parts) {
        switch (parts[0]) {
            case "borrow":
                processBorrow(parts);
                break;
            case "return":
                processReturn(parts);
                break;
            case "pay":
                processPay(parts);
                break;
            case "displayUsers":
                processDisplayUsers();
                break;
            case "displayItems":
                processDisplayItems();
                break;
        }
    }

    /**
     * Handles the 'borrow' command: checks conditions, applies penalties, and adds item to user.
     *
     * @param parts command parts: [borrow, userId, itemId, borrowDate]
     */
    private void processBorrow(String[] parts) {
        int userId = Integer.parseInt(parts[1]);
        int itemId = Integer.parseInt(parts[2]);
        String borrowDate = parts[3];

        User user = users.get(userId);
        LibraryItem item = items.get(itemId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate currentBorrowDate = LocalDate.parse(borrowDate, formatter);
        LocalDate today = LocalDate.now();


        if (lastProcessedBorrowDate != null && currentBorrowDate.isBefore(lastProcessedBorrowDate)) {
            TextFileHandler.writeLine(outputFile, "Borrow dates must be sequential.");
            System.exit(1);
        }
        lastProcessedBorrowDate = currentBorrowDate;


        List<User> allUsers = new ArrayList<>(users.values());

        for (User u : allUsers) {
            List<LibraryItem> overdueItems = new ArrayList<>();
            for (Map.Entry<LibraryItem, LocalDate> entry : u.borrowedItems.entrySet()) {
                long daysBetween = ChronoUnit.DAYS.between(entry.getValue(), today) + 1;
                if (daysBetween > u.getOverdueLimit()) {
                    overdueItems.add(entry.getKey());
                    u.penalty += 2;
                }
            }
            for (LibraryItem itemToRemove : overdueItems) {
                u.borrowedItems.remove(itemToRemove);
            }
        }


        if (user.borrowedItems.size() >= user.getMaxItems()) {
            TextFileHandler.writeLine(outputFile,
                    user.name + " cannot borrow " + item.title + ", since the borrow limit has been reached!");
            return;
        }

        for (User otherUser : users.values()) {
            if (otherUser.borrowedItems.containsKey(item)) {
                TextFileHandler.writeLine(outputFile,
                        user.name + " cannot borrow " + item.title + ", it is not available!");
                return;
            }
        }


        if (user.penalty >= 6) {
            TextFileHandler.writeLine(outputFile,
                    user.name + " cannot borrow " + item.title + ", you must first pay the penalty amount! " + user.penalty + "$"
            );
            return;
        }


        if (!user.canBorrowItem(item)) {
            TextFileHandler.writeLine(outputFile,
                    user.name + " cannot borrow " + item.type + " item!");
            return;
        }

        user.borrowedItems.put(item, currentBorrowDate);
        TextFileHandler.writeLine(outputFile,
                user.name + " successfully borrowed! " + item.title);

    }

    /**
     * Handles the 'return' command: removes the item from the user's borrowed list.
     *
     * @param parts command parts: [return, userId, itemId]
     */
    private void processReturn(String[] parts) {
        int userId = Integer.parseInt(parts[1]);
        int itemId = Integer.parseInt(parts[2]);

        User user = users.get(userId);
        LibraryItem item = items.get(itemId);

        user.borrowedItems.remove(item);
        TextFileHandler.writeLine(outputFile,
                user.name + " successfully returned " + item.title);
    }

    /**
     * Handles the 'pay' command: decreases user's penalty by a fixed amount.
     *
     * @param parts command parts: [pay, userId]
     */
    private void processPay(String[] parts) {
        int userId = Integer.parseInt(parts[1]);
        User user = users.get(userId);

        user.penalty = 0;
        TextFileHandler.writeLine(outputFile,
                user.name + " has paid penalty");

    }

    /**
     * Writes all user information to the output file, sorted by user ID.
     */
    private void processDisplayUsers() {
        List<Integer> sortedUserIds = new ArrayList<>(users.keySet());
        Collections.sort(sortedUserIds);

        TextFileHandler.writeLine(outputFile, " ");
        for (Integer ids : sortedUserIds) {
            User user = users.get(ids);
            TextFileHandler.writeLine(outputFile, " ");
            TextFileHandler.writeLine(outputFile, "------ User Information for " + user.id + " ------");
            TextFileHandler.writeLine(outputFile, user.getUserInfo());
            if (user.penalty > 0) {
                TextFileHandler.writeLine(outputFile, "Penalty: " + user.penalty + "$");
            }
        }
    }

    /**
     * Writes all item information and their current status to the output file.
     */
    private void processDisplayItems() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<Integer> sortedItemIds = new ArrayList<>(items.keySet());
        Collections.sort(sortedItemIds);

        TextFileHandler.writeLine(outputFile, " ");
        for (Integer ids : sortedItemIds) {
            LibraryItem item = items.get(ids);

            String status = "Available";
            String borrowedUser = null;
            LocalDate borrowedDate = null;

            for (User user : users.values()) {
                if (user.borrowedItems.containsKey(item)) {
                    status = "Borrowed";
                    borrowedUser = user.name;
                    borrowedDate = user.borrowedItems.get(item);
                    break;
                }
            }

            TextFileHandler.writeLine(outputFile, " ");
            TextFileHandler.writeLine(outputFile, "------ Item Information for " + item.id + " ------");
            String line = "ID: " + item.id + " Name: " + item.title + " Status: " + status;
            if (status.equals("Borrowed")) {
                line += " Borrowed Date: " + borrowedDate.format(formatter) + " Borrowed by: " + borrowedUser;
            }
            TextFileHandler.writeLine(outputFile, line);
            TextFileHandler.writeLine(outputFile, item.getItemInfo());
        }
    }


}