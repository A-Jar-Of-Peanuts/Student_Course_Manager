# Student and Course Manager

## Description
This project is a student and course manager. The user
can add any number of courses to the manager, with each course having its individual 
course description and credit worth. Similarly, the user 
is free to add any number of students to keep track of to the student manager. 
You can add courses to each student, as well as their grades for
each course. The program can then calculate the weighted average of
all their courses for the user. The student can also have other characteristics, such
as whether they are international or domestic, their major, and their expected graduation date.

This project can be used by teachers and tutors to keep track of their students'
grades and performance, as well as students themselves to
keep track of their progress. This project is of interest to me
because I am a student, and am pretty concerned with my grades
and courses. This could be a good way to keep track of my performance
and how well I need to do in certain courses in order to get the
grade I want. 
## User Stories

- As a user, I want to be able to add a student to my student manager.
- As a user, I want to be able to add a course to my course manager.
- As a user, I want to be able to add courses to a list of courses a student is taking.
- As a user, I want to be able to calculate the weighted average of any given student. 
- As a user, I want to have the option to save my student and course manager list.
- As a user, I want to have the option to load my student and course manager from file when I start the application.

## Instructions for Grader
- You can generate the first required action related to adding Courses to the Course Manager by clicking on the 
"course" tab, entering a non-empty name String into the name field and an integer into the credit field
and pressing submit. This adds a course to the course manager.
- You can generate the second required action relating to adding Courses to the Course Manager by clicking on the 
"course" tab, entering a name of a course that is currently in the course Manager, and clicking
remove. This removes the course from the course manager.
- You can locate my visual component by clicking on the "save/load" tab. The save
and load buttons have image icons on them.
- You can save the state of my application by clicking on the "save/load" tab, then clicking save.
- You can load the state of my application by clicking on the "save/load" tab and then clicking
on the load button. 

## Phase 4: Task 2
Wed Apr 12 21:36:34 PDT 2023
<br>
Course added
<br>
Wed Apr 12 21:36:35 PDT 2023
<br>
Failed to add course
<br>
Wed Apr 12 21:36:37 PDT 2023
<br>
Course removed


## Phase 4: Task 3
Significant refactoring can be done in the CourseManager and StudentManager and Student classes. 
They both have one very similar method with a lot of duplicate code. The methods 
should behave in similar ways, so if one of the methods break then the others
probably will as well, and I might forget to fix all of the methods and just fix one. Therefore, it is a good idea to refactor these methods. 
The addCourse/addStudent/addCourseGrade methods creates/takes in an object with a name 
(course/student/coursegrade respectively), checks that there are no pre-existing objects with that same name,
and then sorts the named object into an arraylist in lexicographical order, as
well as adds it to a hashmap. The code for all three of these methods are very similar,
only with the actual object they are sorting being different and some other details. Also, CourseManager and StudentManager
have two removeCourse/removeStudent and two getStudent/getCourse methods that are also very similar and can be 
refactored in the same way. I didn't write a removeCourseGrade/getCourseGrade method for Student, but it would 
be nice for the Student class to have as well, and this refactoring will help me achieve that. 

I can make a Named interface with a getName() method, and implement this interface in course/student/coursegrade.
Then, I can make a separate class NamedHandler to handle adding Named objects. This class
will have two field (ArrayList<\Named> and HashMap<String, Named\>), as well as a method called addNamed(Named n), and the code within can be copy and pasted
from one of addCourse/addStudent/addCourseGrade. Also, the removeNamed(String name), removeNamed(int i), getNamed(String name), getNamed(int i)
methods can be implemented here as well, with code copied over from removeCourse/removeStudent/getCourse/getStudent. After that, we can delete 
the arraylist and hashmap fields in Student/StudentManager/CourseManager with a field of 
NamedHandler, and replace the body of all the methods mentioned above to a call of the 
corresponding NamedHandler method. With addCourse/addStudent/addCourseGrade, however
I need to create an instance of a subclass of Named with the given parameter first before
passing it to addNamed (in the case of addCourseGrade, I also need to determine if it's
a CDF or percentageGrade first as well).

For the anonymous classes in SaveLoadPanel, CoursePanel, and PrintLog, I just
drew a line from the nested class to the outer class with a circle with a cross
on the end.

##References
- CPSC 210 teller app
- CPSC 210 jsonserialization demo
- CPSC 210 alarm system
- https://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
- https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
- https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html#grid
- https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
- https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
- https://stackoverflow.com/questions/9093448/how-to-capture-a-jframes-close-button-click-event