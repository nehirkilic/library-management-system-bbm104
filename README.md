# University Library Management System – BBM104 PA1
*Spring 2025 🌼🍃*

This project was developed as part of the **BBM104 – Introduction to Programming Laboratory II** course at Hacettepe University.  
It simulates a real-world university library where users (Students, Academics, Guests) interact with various item types (Books, Magazines, DVDs), each governed by borrowing rules, penalties, and restrictions.

---
## 🪄 Features

- Hierarchical class structure using abstract base classes:
  - `LibraryItem → Book / Magazine / DVD`
  - `User → Student / AcademicMember / GuestUser`
- Borrowing constraints depending on user type and item type
- Late return penalties (e.g., $2 for each overdue item)
- Automatic borrowing restrictions when penalty ≥ $6
- Parses input from structured text files: `items.txt`, `users.txt`, `commands.txt`
- Outputs results to `output.txt`
- Real-time penalty calculation using `LocalDate.now()` for overdue detection
- Fully object-oriented and modularized system
- JavaDoc comments were written throughout the source code to improve clarity and maintainability.

---
## 🌀 OOP Design Principles

This system was designed to apply core object-oriented programming principles:

- ✅ **Encapsulation**
- ✅ **Inheritance**
- ✅ **Polymorphism**
- ✅ **Modularity and Separation of Concerns**

---

## 📦 Folder Structure

```
/library_management_system
├── input/                        # Sample input files used for testing
│   ├── items.txt
│   ├── users.txt
│   ├── commands1.txt
│   ├── commands2.txt
│   ├── ...
│   └── commands9.txt
├── src/
│   ├── Main.java
│   ├── LibraryManagementSystem.java
│   ├── CommandProcessor.java
│   ├── TextFileHandler.java
│   ├── LibraryItem.java         // Contains: LibraryItem (abstract), Book, Magazine, DVD, ItemParser
│   └── User.java                // Contains: User (abstract), Student, AcademicMember, GuestUser, UserParser
├── output.txt                   # Output generated after execution
├── README.md                    # Project documentation
└── .gitignore                   # Git version control rules

```
> **Note:** Due to assignment file limitations, multiple related classes are grouped within a single `.java` file for both item and user hierarchies.

---

## 📜 Sample Input / Output

The following are shortened excerpts from example input and output files.  
Output content may vary depending on the system date, as the program runs in real-time.

### items.txt
```
B,1007,The Da Vinci Code,Dan Brown,Mystery,limited  
M,2002,The New Yorker,Condé Nast,News,normal  
D,3001,Forrest Gump,Robert Zemeckis,Drama,142 min,normal
```

### users.txt
```
S,Liam Carter,1001,555-1234,Computer Science,Engineering,1  
A,Dr. Mia Flores,2003,555-6789,Mathematics,Science,Assistant Professor  
G,Chloe Simmons,3002,555-8901,Researcher
```

### commands.txt
```
borrow,2003,2002,04/05/2025  
pay,2003  
return,2003,3001  
displayUsers  
displayItems
```

### output.txt
```
Dr. Mia Flores successfully borrowed! The New Yorker  
Dr. Mia Flores has paid penalty  
...

------ User Information for 2003 ------
Name: Assistant Professor Dr. Mia Flores  
Phone: 555-6789  
Penalty: 2$

------ Item Information for 2002 ------
ID: 2002
Name: The New Yorker
Status: Borrowed
Borrowed by: Dr. Mia Flores
```
---
## 💻 How to Compile & Run

> Requires **Java 8**  
> Make sure the input files are located in the `/input` file.

From the terminal:

```bash
javac *.java
java Main input/items.txt input/users.txt input/commands.txt output.txt

```

- `items.txt`: Contains information about books, magazines, DVDs
- `users.txt`: Contains student, academic, and guest user definitions
- `commands.txt`: Contains actions like borrow, return, and pay
- `output.txt`: Program output written to this file


---
## ⭐ Notes

This was my **first Java project** implementing object-oriented design in a complete system.  
It taught me how to:

- structure class hierarchies
- manage file-based inputs and outputs
- apply real-world rules like borrowing constraints and penalties
- implement real-time logic based on the current system date

I also got familiar with writing **JavaDoc** comments, which helped me document the code more clearly and professionally.
