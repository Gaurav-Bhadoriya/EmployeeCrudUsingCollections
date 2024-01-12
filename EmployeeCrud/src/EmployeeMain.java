import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class EmployeeMain {

    static List<Employee> employeeList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    insert();
                    break;
                case 2:
                    display();
                    break;
                case 3:
                    sort();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    update();
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    public static void displayMenu() {
        System.out.println("\n1. Insert");
        System.out.println("2. Display");
        System.out.println("3. Sort");
        System.out.println("4. Delete");
        System.out.println("5. Update");
        System.out.println("0. Exit");
        System.out.println("Enter your choice: ");
    }

    public static void insert() {
        Employee employee = new Employee();
        employeeList.add(getDataFromUser(employee));
        System.out.println("Record inserted successfully.");
    }

    private static void display() {
        if (employeeList.isEmpty()) {
            System.out.println("No data found.");
        } else {
            System.out.println("Id   Name   Skills   Age   Salary   Joining date");
            int count = 0;
            for (Employee employee : employeeList) {
                count++;
                System.out.println(count + "    " + employee.toString());
            }
        }
    }

    private static void sort() {
        if (employeeList.isEmpty()) {
            System.out.println("No data to sort.");
        } else {
            Collections.sort(employeeList, Comparator.comparingInt(Employee::getAge));
            System.out.println("Records sorted by Age:");
            display();
        }
    }

    private static void delete() {
        if (employeeList.isEmpty()) {
            System.out.println("No data to delete.");
        } else {
            System.out.println("Enter the record index you want to delete:");
            int indexToDelete = validateIntegerInput(scanner.next());
            if (isValidIndex(indexToDelete)) {
                employeeList.remove(indexToDelete - 1);
                System.out.println("Record deleted successfully.");
                display();
            } else {
                System.out.println("Invalid index. Record not found.");
            }
        }
    }

    private static void update() {
        if (employeeList.isEmpty()) {
            System.out.println("No data to update.");
        } else {
            System.out.println("Enter the record index you want to update:");
            int indexToUpdate = validateIntegerInput(scanner.next());
            if (isValidIndex(indexToUpdate)) {
                Employee recordToUpdate = employeeList.get(indexToUpdate - 1);
                employeeList.set(indexToUpdate - 1, getDataFromUser(recordToUpdate));
                System.out.println("Record updated successfully.");
                display();
            } else {
                System.out.println("Invalid index. Record not found.");
            }
        }
    }

    private static Employee getDataFromUser(Employee employee) {
        System.out.println("Enter Employee Name: ");
        employee.setName(scanner.next());
        System.out.println("Enter Employee Skills: ");
        employee.setSkills(scanner.next());

        int employeeAge;
        do {
            System.out.println("Enter Employee Age: ");
            employeeAge = validateIntegerInput(scanner.next());
        } while (!isValidAge(employeeAge));
        employee.setAge(employeeAge);

        double employeeSalary;
        do {
            System.out.println("Enter Employee Salary: ");
            employeeSalary = validateDoubleInput(scanner.next());
        } while (!isValidSalary(employeeSalary));
        employee.setSalary(employeeSalary);

        System.out.println("Enter Employee Joining date (YYYY-MM-DD): ");
        employee.setJoiningDate(validateDateInput(scanner.next()));

        return employee;
    }

    private static int validateIntegerInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return -1;
        }
    }

    private static double validateDoubleInput(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return -1;
        }
    }

    public static LocalDate validateDateInput(String dateInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean valid = false;
        LocalDate parsedDate = null;

        while (!valid) {
            try {
                parsedDate = LocalDate.parse(dateInput, formatter);
                valid = true;
            } catch (Exception e) {
                System.out.println("Invalid date/format. Please try again.");
                System.out.println("Enter The EmployeeDate (YYYY-MM-DD): ");
                dateInput = new Scanner(System.in).nextLine();
            }
        }

        return parsedDate;
    }

    private static boolean isValidIndex(int index) {
        return index >= 1 && index <= employeeList.size();
    }

    private static boolean isValidAge(int age) {
        return age > 18 && age < 100;
    }

    private static boolean isValidSalary(double salary) {
        return salary > 0;
    }
}