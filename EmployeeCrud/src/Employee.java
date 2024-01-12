import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Employee {

    UUID id;
    String name;
    String skills;
    int age;
    double salary;
    LocalDate joiningDate;

    Employee(String name, String skills, int age, double salary, LocalDate joiningDate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.skills = skills;
        this.age = age;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }

	public Employee() {
		super();
		this.id = id;
		this.name = name;
		this.skills = skills;
		this.age = age;
		this.salary = salary;
		this.joiningDate = joiningDate;
	}


	public String getName() {
        return name;
    }

    public String getSkills() {
        return skills;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getJoiningdate() {
        return joiningDate;
    }

    public void setName(String newName) {
        this.name = newName;
    }
    
    public void setSkills(String setSkills) {
        this.skills = setSkills;
    }
    
    public void setAge(int setAge) {
        this.age = setAge;
    }
    
    public void setSalary(double employeeSalary) {
        this.salary = employeeSalary;
    }
    public void setJoiningDate(LocalDate setJoiningDate) {
        this.joiningDate = setJoiningDate;
    }


    @Override
    public String toString() {
        return ("Name=" + name + ", Skills=" + skills + ", Age=" + age
                + ", Salary=" + salary + ", Joiningdate=" + joiningDate);
    }

    public static int validateIntegerInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return -1;
        }
    }

    public static LocalDate validateDateInput(String dateInput) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        parser.setLenient(false);
        Date inputDate = null;
        boolean valid = false;

        while (!valid) {
            try {
                inputDate = parser.parse(dateInput);
                if (inputDate.after(new Date())) {
                    System.out.println("The date should not be a future date. Please try again.");
                    System.out.println("Enter The EmployeeDate (YYYY-MM-DD): ");
                    dateInput = new Scanner(System.in).nextLine();
                } else {
                    valid = true;
                }
            } catch (ParseException e) {
                System.out.println("Invalid date/format. Please try again.");
                System.out.println("Enter The EmployeeDate (YYYY-MM-DD): ");
                dateInput = new Scanner(System.in).nextLine();
            }
        }

        return LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
