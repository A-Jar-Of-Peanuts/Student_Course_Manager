package ui;

import model.*;
import persistence.*;

import java.awt.print.Printable;
import java.util.*;
import java.io.*;

// student manager console app
public class StudentManagerConsole {
    private static final String JSON_STORE = "./data/studentcoursemanager.json";
    private BufferedReader in;
    private StudentManager stManager;
    private CourseManager coManager;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs runSM
    public StudentManagerConsole() throws FileNotFoundException {
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
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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
        System.out.println("Enter \"save\" to save to file.");
        System.out.println("Enter \"load\" to load from file.");
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
            case "save":
                save();
                break;
            case "load":
                load();
                break;
            default:
                throw new IOException();
        }
    }

    // EFFECTS: saves the student manager and course manager to file
    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(stManager, coManager);
            jsonWriter.close();
            System.out.println("Saved!");
        } catch (FileNotFoundException e) {
            System.out.println("unable to save");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the student manager and course manager from file
    private void load() {
        try {
            coManager = jsonReader.readCM();
            stManager = jsonReader.readSM();
            System.out.println("loaded!");
        } catch (IOException e) {
            System.out.println("unable to load");
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

    // EFFECTS: adds a grade to the given student
    public void addGrade(Student s) throws IOException {
        System.out.println("Enter any course from the course manager to add to the student.");
        showCourses();
        try {
            Course c = coManager.getCourse(in.readLine());
            System.out.println("What percent did the student get in this course?");
            int percentage = Integer.parseInt(in.readLine());
            System.out.println("Is this a Credit/D/Fail course? (Y/N)");
            String command = in.readLine();
            if (command.equals("Y") && s.addCourseGrade(c, percentage, true)) {
                System.out.println("Grade added successfully!");
            } else if (command.equals("N") && s.addCourseGrade(c, percentage, false)) {
                System.out.println("Grade added successfully!");
            } else {
                throw new IOException();
            }
        } catch (Exception e) {
            System.out.println("That wasn't a valid input! Press \"t\" to try again, any other key to return to menu.");
            if (in.readLine().equals("t")) {
                addGrade(s);
            }
        }
    }

    // EFFECTS: shows the information of a specific student in the student manager
    public void showStudent() throws IOException {
        System.out.println("Enter the name of the student that you want to see.");
        try {
            String input = in.readLine();
            Student s = stManager.getStudent(input);
            System.out.println("Name: " + s.getName() + "\nStatus: "
                    + s.getStatus() + "\nMajor: " + s.getMajor() + "\nGrad Date: " + s.getGradDate() + "\nGrades: ");
            for (int i = 0; i < s.getCourseGrade().size(); i++) {
                System.out.println(s.getCourseGrade().get(i).getName() + ": "
                        + s.getCourseGrade().get(i).getGrade());
            }
            System.out.println("GPA: " + s.getGPA() + "\nWould you like to add a course grade to this student? (Y/N)");
            input = in.readLine();
            if (input.equals("Y")) {
                addGrade(s);
            } else if (!input.equals("N")) {
                throw new IOException();
            }
        } catch (Exception e) {
            System.out.println("That wasn't a valid input! Press \"t\" to try again, any other key to return to menu.");
            if (in.readLine().equals("t")) {
                showStudent();
            }
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
    public void addStudent() throws IOException {
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
