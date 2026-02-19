import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
public class FinalProject {
private static final Scanner scanner = new Scanner(System.in);
private static final List<Person> persons = new ArrayList<>();
public static void main(String[] args) {
System.out.println("Welcome to my Personal Management Program");
while (true) {
System.out.println("\nChoose one of the options:");
System.out.println("1. Enter the information of a faculty");
System.out.println("2. Enter the information of a student");
System.out.println("3. Print tuition invoice for a student");
System.out.println("4. Print faculty information");
System.out.println("5. Enter the information of a staff member");
System.out.println("6. Print the information of a staff member");
System.out.println("7. Delete a person");
System.out.println("8. Exit Program");
int choice = scanner.nextInt();
scanner.nextLine();
switch (choice) {
case 1:
EnterFacultyInfo();
break;
case 2:
EnterStudentInfo();
break;
case 3:
printStudentTuitionInvoice();
break;
case 4:
PrintFacultyInfo();
break;
case 5:
EnterStaffInfo();
break;
case 6:
printStaffInfo();
break;
case 7:
deletePerson();
break;
case 8:
createReport();
System.out.println("\nGoodbye!");
return;
default:
System.out.println("Invalid selection. Please try again.");
}
}
}
private static void EnterStudentInfo() {
System.out.println("Enter the student info:");
System.out.print("Name of Student: ");
String name = scanner.nextLine();
String id;
while (true) {
System.out.print("ID: ");
id = scanner.nextLine();
if (!id.matches("[a-zA-Z]{2}\\d{4}")) {
System.out.println("Invalid ID format. Must be
LetterLetterDigitDigitDigitDigit");
} else if (isIdDuplicate(id)) {
System.out.println("ID already exists. Please Enter a different
ID.");
} else {
break;
}
}
System.out.print("GPA: ");
double gpa = scanner.nextDouble();
System.out.print("Credit hours: ");
int creditHours = scanner.nextInt();
persons.add(new Student(name, id, gpa, creditHours));
System.out.println("Student added!");
}
private static void EnterFacultyInfo() {
System.out.println("Enter the faculty info:");
System.out.print("Name of the faculty: ");
String name = scanner.nextLine();
String id;
while (true) {
System.out.print("ID: ");
id = scanner.nextLine();
if (!id.matches("[a-zA-Z]{2}\\d{4}")) {
System.out.println("Invalid ID format. Must be
LetterLetterDigitDigitDigitDigit");
} else if (isIdDuplicate(id)) {
System.out.println("ID already exists. Please Enter a different
ID.");
} else {
break;
}
}
String department;
while (true) {
System.out.print("Department (Mathematics, Engineering, English): ");
department = scanner.nextLine().toLowerCase();
if (department.equals("mathematics") ||
department.equals("engineering") || department.equals("english")) {
break;
} else {
System.out.println("Invalid department. Please Enter Mathematics,
Engineering, or English.");
}
}
String rank;
while (true) {
System.out.print("Rank (Professor, Adjunct): ");
rank = scanner.nextLine();
if (rank.equalsIgnoreCase("Professor") ||
rank.equalsIgnoreCase("Adjunct")) {
break;
} else {
System.out.println("Invalid rank. Please Enter Professor or
Adjunct.");
}
}
persons.add(new Faculty(name, id, department, rank));
System.out.println("Faculty added!");
}
private static void EnterStaffInfo() {
System.out.println("Enter the staff member info:");
System.out.print("Name of the staff member: ");
String name = scanner.nextLine();
String id;
while (true) {
System.out.print("ID: ");
id = scanner.nextLine();
if (!id.matches("[a-zA-Z]{2}\\d{4}")) {
System.out.println("Invalid ID format. Must be
LetterLetterDigitDigitDigitDigit");
} else if (isIdDuplicate(id)) {
System.out.println("ID already exists. Please Enter a different
ID.");
} else {
break;
}
}
String department;
while (true) {
System.out.print("Department (Mathematics, Engineering, English): ");
department = scanner.nextLine().toLowerCase();
if (department.equals("mathematics") ||
department.equals("engineering") || department.equals("english")) {
break;
} else {
System.out.println("Invalid department. Please Enter Mathematics,
Engineering, or English.");
}
}
String status;
while (true) {
System.out.print("Status (P for Part Time, F for Full Time): ");
status = scanner.nextLine().toUpperCase();
if (status.equals("P") || status.equals("F")) {
break;
} else {
System.out.println("Invalid status. Please Enter P for Part Time or
F for Full Time.");
}
}
persons.add(new Staff(name, id, department, status));
System.out.println("Staff member added!");
}
private static boolean isIdDuplicate(String id) {
for (Person person : persons) {
if (person.getId().equals(id)) {
return true;
}
}
return false;
}
private static void printStudentTuitionInvoice() {
System.out.print("Enter the student’s ID: ");
String studentId = scanner.nextLine();
boolean found = false;
for (Person person : persons) {
if (person instanceof Student && person.getId().equals(studentId)) {
((Student) person).print();
found = true;
break;
}
}
if (!found) {
System.out.println("No student's matched!");
}
}
private static void PrintFacultyInfo() {
System.out.print("Enter the Faculty’s ID: ");
String facultyId = scanner.nextLine();
boolean found = false;
for (Person person : persons) {
if (person instanceof Faculty && person.getId().equals(facultyId)) {
((Faculty) person).print();
found = true;
break;
}
}
if (!found) {
System.out.println("No faculty member matched!");
}
}
private static void printStaffInfo() {
System.out.print("Enter the Staff’s ID: ");
String staffId = scanner.nextLine();
boolean found = false;
for (Person person : persons) {
if (person instanceof Staff && person.getId().equals(staffId)) {
((Staff) person).print();
found = true;
break;
}
}
if (!found) {
System.out.println("No staff member matched!");
}
}
private static void deletePerson() {
System.out.print("Enter the ID of the person to delete: ");
String id = scanner.nextLine();
boolean removed = persons.removeIf(person -> person.getId().equals(id));
if (!removed) {
System.out.println("Sorry, no such person exists.");
}
}
private static void createReport() {
try (BufferedWriter w = new BufferedWriter(new FileWriter("report.txt"))) {
LocalDate currentDate = LocalDate.now();
DateTimeFormatter formatter =
DateTimeFormatter.ofPattern("dd/MM/yyyy");
w.write("Report created on " + currentDate.format(formatter));
w.newLine();
List<Student> students = new ArrayList<>();
for (Person person : persons) {
if (person instanceof Student) {
students.add((Student) person);
}
}
students.sort(Comparator.comparingDouble(Student::getGpa).reversed());
w.newLine();
w.write("Students (Sorted by GPA in descending order)");
w.newLine();
w.write("-----------");
w.newLine();
int counter = 1;
for (Student student : students) {
w.write(counter + ". " + student.getFullName());
w.newLine();
w.write("ID: " + student.getId());
w.newLine();
w.write("GPA: " + student.getGpa());
w.newLine();
w.write("Credit hours: " + student.getCreditHours());
w.newLine();
counter++;
}
System.out.println("Report created and saved on your hard drive!");
} catch (IOException e) {
e.printStackTrace();
}
}
}
abstract class Person {
private String fullName;
private String id;
public Person(String fullName, String id) {
this.fullName = fullName;
this.id = id;
}
public String getFullName() {
return fullName;
}
public void setFullName(String fullName) {
this.fullName = fullName;
}
public String getId() {
return id;
}
public void setId(String id) {
this.id = id;
}
public abstract void print();
}
class Student extends Person {
private double gpa;
private int creditHours;
public Student(String fullName, String id, double gpa, int creditHours) {
super(fullName, id);
this.gpa = gpa;
this.creditHours = creditHours;
}
public double getGpa() {
return gpa;
}
public void setGpa(double gpa) {
this.gpa = gpa;
}
public int getCreditHours() {
return creditHours;
}
public void setCreditHours(int creditHours) {
this.creditHours = creditHours;
}
public void print() {
System.out.println("Printing tuition invoice for student...");
System.out.println("------------------------------------");
System.out.println(getFullName() + " " + getId());
System.out.println("Credit Hours: " + creditHours + " ($236.45/credit
hour)");
double fee = creditHours * 236.45 + 52;
double discount = (gpa >= 3.85) ? 0.25 * fee : 0;
System.out.println("Fees: $52");
System.out.println("Total payment (after discount): $" + (fee - discount) +
" ($" + discount + " discount applied)");
System.out.println("------------------------------------");
}
}
class Faculty extends Person {
private String department;
private String rank;
public Faculty(String fullName, String id, String department, String rank) {
super(fullName, id);
this.department = department;
this.rank = rank;
}
public String getDepartment() {
return department;
}
public void setDepartment(String department) {
this.department = department;
}
public String getRank() {
return rank;
}
public void setRank(String rank) {
this.rank = rank;
}
public void print() {
System.out.println("Printing faculty information...");
System.out.println("------------------------------------");
System.out.println(getFullName() + " " + getId());
System.out.println(department + " Department, " + rank);
System.out.println("------------------------------------");
}
}
class Staff extends Person {
private String department;
private String status;
public Staff(String fullName, String id, String department, String status) {
super(fullName, id);
this.department = department;
this.status = status;
}
public String getDepartment() {
return department;
}
public void setDepartment(String department) {
this.department = department;
}
public String getStatus() {
return status;
}
public void setStatus(String status) {
this.status = status;
}
public void print() {
System.out.println("Printing staff information...");
System.out.println("------------------------------------");
System.out.println(getFullName() + " " + getId());
System.out.println(department + " Department, " + status);
System.out.println("------------------------------------");
}
}
