# Personal Management System

A Java console application for managing university personnel records including students, faculty, and staff. Built as a final project demonstrating object-oriented programming principles.

## Features
- Add and manage student, faculty, and staff records
- Input validation with ID format enforcement (e.g. AB1234) and duplicate checking
- Print tuition invoices for students with GPA-based discount logic
- Print faculty and staff information by ID
- Delete personnel records
- Generate a report.txt file on exit, listing students sorted by GPA in descending order

## Sample Menu
Enter the information of a faculty
Enter the information of a student
Print tuition invoice for a student
Print faculty information
Enter the information of a staff member
Print the information of a staff member
Delete a person
Exit Program

## Concepts Used
- Object-oriented design with abstract base class (Person) and subclasses (Student, Faculty, Staff)
- Polymorphism and inheritance
- Input validation using regex and while loops
- File I/O with BufferedWriter to generate a report
- Java Collections (ArrayList, Comparator, sorting)
- Java Date/Time API for report timestamps

## How to Run
javac FinalProject.java
java FinalProject

## Course
Object-Oriented Programming (COP 3330) – University of Central Florida
