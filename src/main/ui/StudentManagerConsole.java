package ui;

import model.*;

import java.util.*;
import java.io.*;

// student manager console app
public class StudentManagerConsole {
    BufferedReader in;
    StudentManager stManager;
    CourseManager coManager;

    // EFFECTS: runs runSM
    public StudentManagerConsole() {
        try {
            runSM();
        } catch (Exception e) {
            System.out.println("Oops! Something went wrong!");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new student manager and a new course manager, initializes
    // in
    public void init() {
        stManager = new StudentManager();
        coManager = new CourseManager();
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    // MODIFIES: this
    // EFFECTS: processes user input.
    public void runSM() throws IOException {
        boolean running = true;
        init();

        String command;

        while (running) {
            displayMainMenu();
            try {
                command = in.readLine();
                if (command.equals("exit")) {
                    running = false;
                } else {
                    processCommands(command);
                }
            } catch (IOException e) {
                System.out.println("Oops! Something was wrong with your input!");
            }
        }

    }

    // EFFECTS: displays menu options to user
    public void displayMainMenu() {
        System.out.println("Enter \"student\" to access and modify students.");
        System.out.println("Enter \"course\" to access and modify courses.");
        System.out.println("Enter \"exit\" to exit.");
    }

    // EFFECTS: processes user commands
    public void processCommands(String command) throws IOException {
        switch (command) {
            case "student":
                processStudent();
                break;
            case "course":
                processCourse();
                break;
            default:
                throw new IOException();
        }
    }

    // EFFECTS: processes user commands in student menu
    public void processStudent() throws IOException {
        System.out.println("Enter \"display all\" to see all the students\nEnter \"display\" to see one student");
        System.out.println("Enter \"add\" to add a student\nEnter \"remove\" to remove a student");
        System.out.println("Enter \"return\" to return to the main menu");
        switch (in.readLine()) {
            case "display":
                showStudent();
                break;
            case "display all":
                showStudents();
                break;
            case "add":
                addStudent();
                break;
            case "remove":
                removeStudent();
                break;
            case "return":
                return;
            default:
                System.out.println("That isn't a valid command!");
        }
        processStudent();
    }

    // EFFECTS: shows the information of a specific student in the student manager
    public void showStudent() throws IOException {
        System.out.println("Enter the name of the student that you want to see.");
        try {
            String input = in.readLine();
            Student s = stManager.getStudent(input);
            System.out.println("Name: " + s.getName() + "\\nStatus: "
                    + s.getStatus() + "\nMajor: " + s.getMajor() + "\nGrad Date: " + s.getGradDate() + "\nGrades: ");
            for (int i = 0; i < s.getCourseGrade().size(); i++) {
                System.out.println(s.getCourseGrade().get(i).getName() + ": "
                        + s.getCourseGrade().get(i).getPercentage());
            }
            System.out.println("GPA: " + s.getGPA() + "\nWould you like to add a course grade to this student? (Y/N)");
            if (in.readLine().equals("Y")) {
                addCourse();
            }
        } catch (Exception e) {
            System.out.println("That wasn't a valid input! Please try again.");
            showStudent();
        }
    }

    // EFFECTS: removes a student if the student exists in the student manager
    public void removeStudent() throws IOException {
        System.out.println("Enter the name of the student that you want to remove.");
        try {
            String input = in.readLine();
            stManager.removeStudent(input);
        } catch (Exception e) {
            System.out.println("That wasn't a valid input! Please try again.");
            removeStudent();
        }
    }

    // EFFECTS: adds a student if the student name doesn't already exist in the student manager
    public void addStudent() throws IOException, IndexOutOfBoundsException {
        System.out.println("Enter student's \"name, international/domestic, major, graduation date\" "
                + "in that order and format.");
        try {
            String[] input = in.readLine().split(", ");
            if (!stManager.addStudent(input[0], input[1], input[2], Integer.parseInt(input[3]))) {
                System.out.println("Sorry! That student already exists! Try a different name.");
            } else {
                System.out.println("Student successfully added!");
            }
        } catch (Exception e) {
            System.out.println("That wasn't a valid input! Please try again.");
            addStudent();
        }
    }

    // EFFECTS: shows a list of students in the student manager, or tells the user there are no
    // students
    public void showStudents() {
        if (stManager.getLength() == 0) {
            System.out.println("Looks like there are no students in the student manager!");
        } else {
            System.out.println("Here's a list of students in the student manager: ");
            for (int i = 0; i < stManager.getLength(); i++) {
                System.out.println(stManager.getStudent(i).getName());
            }
        }
    }

    // EFFECTS: processes user commands in course menu
    public void processCourse() throws IOException {
        System.out.println("Enter \"display all\" to see all the courses");
        System.out.println("Enter \"add\" to add a course\nEnter \"remove\" to remove a course");
        System.out.println("Enter \"return\" to return to the main menu");
        switch (in.readLine()) {
            case "display all":
                showCourses();
                break;
            case "add":
                addCourse();
                break;
            case "remove":
                removeCourse();
                break;
            case "return":
                return;
            default:
                System.out.println("That isn't a valid command!");
        }
        processCourse();
    }

    // EFFECTS: removes a course if the course exists in the course manager
    public void removeCourse() throws IOException {
        System.out.println("Enter the name of the course that you want to remove.");
        try {
            String input = in.readLine();
            coManager.removeCourse(input);
        } catch (Exception e) {
            System.out.println("That wasn't a valid input! Please try again.");
            removeCourse();
        }
    }

    // EFFECTS: adds a course if the course name doesn't already exist in the course manager
    public void addCourse() throws IOException {
        System.out.println("Enter course's \"name, credit\" "
                + "in that order and format.");
        try {
            String[] input = in.readLine().split(", ");
            if (!coManager.addCourse(input[0], Integer.parseInt(input[1]))) {
                System.out.println("Sorry! That course already exists! Try a different name.");
            } else {
                System.out.println("Course successfully added!");
            }
        } catch (Exception e) {
            System.out.println("That wasn't a valid input! Please try again.");
            addCourse();
        }
    }

    // EFFECTS: shows a list of course in the course manager, or tells the user there are no
    // courses
    public void showCourses() {
        if (coManager.getLength() == 0) {
            System.out.println("Looks like there are no courses in the course manager!");
        } else {
            System.out.println("Here's a list of courses in the course manager: ");
            for (int i = 0; i < coManager.getLength(); i++) {
                System.out.println(coManager.getCourse(i).getName());
            }
        }
    }
}
