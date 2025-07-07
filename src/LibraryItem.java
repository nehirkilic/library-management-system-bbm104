/**
 * Abstract class representing a generic item in the library.
 * All specific item types (Book, Magazine, DVD) extend this class.
 */
public abstract class LibraryItem {
    protected int id;
    protected String title;
    protected String type; // normal, referenced, rare or limited


    /**
     * Constructs a LibraryItem with given attributes.
     *
     * @param id    the unique ID of the item
     * @param title the name of the item
     * @param type  the type of the item (normal, referenced, etc.)
     */
    public LibraryItem(int id, String title, String type) {
        this.id = id;
        this.title = title;
        this.type = type;
    }

    /**
     * Returns item-specific information depending on its type.
     *
     * @return item details as a string
     */
    public abstract String getItemInfo();
}

/**
 * Represents a Book item in the library.
 */
class Book extends LibraryItem {
    protected String author;
    protected String category;


    /**
     * Constructs a Book object.
     *
     * @param id       the ID of the book
     * @param title    the name of the book
     * @param author   the author of the book
     * @param category the genre of the book
     * @param type     the type of the book (normal, rare, etc.)
     */
    public Book(int id, String title, String author, String category, String type) {
        super(id, title, type);
        this.author = author;
        this.category = category;

    }

    @Override
    public String getItemInfo() {
        return "Author: " + author + " Genre: " + category;
    }
}


/**
 * Represents a Magazine item in the library.
 */
class Magazine extends LibraryItem {
    protected String publisher;
    protected String category;

    /**
     * Constructs a Magazine object.
     *
     * @param id        the ID of the magazine
     * @param title     the name of the magazine
     * @param publisher the publisher name
     * @param category  the category of the magazine
     * @param type      the type of the magazine (normal, limited, etc.)
     */
    public Magazine(int id, String title, String publisher, String category, String type) {
        super(id, title, type);
        this.publisher = publisher;
        this.category = category;

    }

    @Override
    public String getItemInfo() {
        return "Publisher: " + publisher + " Category: " + category;
    }
}


/**
 * Represents a DVD item in the library.
 */
class DVD extends LibraryItem {
    protected String director;
    protected String category;
    protected String runtime;


    /**
     * Constructs a DVD object.
     *
     * @param id       the ID of the DVD
     * @param title    the name of the DVD
     * @param director the director of the DVD
     * @param category the category of the DVD
     * @param runtime  the runtime of the DVD
     * @param type     the type of the DVD (normal, rare, etc.)
     */
    public DVD(int id, String title, String director, String category, String runtime, String type) {
        super(id, title, type);
        this.director = director;
        this.category = category;
        this.runtime = runtime;

    }

    @Override
    public String getItemInfo() {
        return "Director: " + director + " Category: " + category + " Runtime: " + runtime;
    }
}


/**
 * Utility class for converting raw input data into specific LibraryItem objects.
 * Determines item type based on the first element in the input array:
 * "B" for Book, "M" for Magazine, "D" for DVD.
 */
class ItemParser {

    /**
     * Parses the provided array into a corresponding LibraryItem object.
     * The first element of the array determines the item type.
     *
     * @param parts a string array representing an item, typically from a line split by commas
     * @return a Book, Magazine, or DVD object; or null if the type is unrecognized
     */
    public static LibraryItem parse(String[] parts) {
        switch (parts[0]) {
            case "B": {
                int id = Integer.parseInt(parts[1]);
                String title = parts[2];
                String author = parts[3];
                String category = parts[4];
                String type = parts[5];

                return new Book(id, title, author, category, type);
            }
            case "M": {
                int id = Integer.parseInt(parts[1]);
                String title = parts[2];
                String publisher = parts[3];
                String category = parts[4];
                String type = parts[5];

                return new Magazine(id, title, publisher, category, type);
            }
            case "D": {
                int id = Integer.parseInt(parts[1]);
                String title = parts[2];
                String director = parts[3];
                String category = parts[4];
                String runtime = parts[5];
                String type = parts[6];

                return new DVD(id, title, director, category, runtime, type);
            }
        }
        return null;
    }

}