# University Library Management System – BBM104 PA1

*Spring 2025 *

This project was developed as part of the **BBM104 – Introduction to Programming Laboratory II** course at Hacettepe University.  
It simulates a university library system where users interact with various library items under different borrowing rules. The project was implemented using Java 8 and follows object-oriented programming principles such as encapsulation and inheritance.

The assignment focuses on modeling real-life constraints like user roles, item restrictions, overdue penalties, and borrowing limitations.

---

## 🪄 Features

- Object-oriented class hierarchy with `LibraryItem` and `User` base classes
- Inheritance-based structure for `Book`, `Magazine`, `DVD`, and user types (`Student`, `AcademicMember`, `Guest`)
- Borrowing restrictions based on user type and item type (e.g., guests cannot borrow rare items)
- Penalty system: overdue returns automatically add monetary penalties
- Users must pay if penalties ≥ $6 before borrowing again
- Full command parsing system: `borrow`, `return`, `pay`, `displayUsers`, `displayItems`
- All inputs/outputs handled via `.txt` files (strict formatting required)

---

## ⚙️ How to Compile & Run

> Requires Java **8**

```bash
javac Main.java
java Main items.txt users.txt commands.txt output.txt
```

- `items.txt`: Contains information about books, magazines, DVDs
- `users.txt`: Contains student, academic, and guest user info
- `commands.txt`: Borrowing, returning, payment and display instructions
- `output.txt`: The result file to be written

> ⚠️ Note: The output depends on the current system date (`LocalDate.now()`), so actual results may vary when run on different days.

---

## 📂 I/O Example

Sample command input (from `commands.txt`):

```
borrow,1001,1001,10/03/2025
return,1001,1001
displayUsers
displayItems
```

Output is written to the file specified as the fourth command-line argument.

---

## 🧬 Object-Oriented Design

This project implements key OOP concepts:

- **Encapsulation**: All fields are private with appropriate accessors
- **Inheritance**: Specialized item and user types extend common base classes
- **Polymorphism**: Command handling and borrowing logic use overridden behavior
- **Separation of concerns**: IO, command processing, and logic are modularized

---

## ⭐ Notes

This project helped reinforce my understanding of OOP design in a real-world scenario.  
It was also a valuable exercise in clean code, command parsing, and penalty-based logic structures — all while meeting strict formatting and platform requirements.
