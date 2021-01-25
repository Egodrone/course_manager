package se.lexicon.course_manager_assignment.model;



import se.lexicon.course_manager_assignment.data.sequencers.CourseSequencer;
import java.time.LocalDate;
import java.util.*;



public class Course {

    private int id;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private Collection<Student> students = new HashSet<>();



    public Course() {
       //this.id = CourseSequencer.nextCourseId();
    }



    public Course(String courseName, LocalDate startDate, int weekDuration, Collection<Student> students) {
        //this.id = CourseSequencer.nextCourseId();
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
        this.students = students;
    }



    public Course(String courseName, LocalDate startDate, int weekDuration) {
        //this.id = CourseSequencer.nextCourseId();
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
        //this.students = students;
    }



    public Course(int id, String courseName, LocalDate startDate, int weekDuration, Collection<Student> students) {
        this.id = id;
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
        this.students = students;
    }


    public Course(int id, String courseName, LocalDate startDate, int weekDuration) {
        this.id = id;
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
    }



    public int getId() {
        return id;
    }



    public String getCourseName() {
        return courseName;
    }



    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }



    public LocalDate getStartDate() {
        return startDate;
    }



    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }



    public int getWeekDuration() {
        return weekDuration;
    }



    public void setWeekDuration(int weekDuration) {
        this.weekDuration = weekDuration;
    }



    public Collection<Student> getStudents() {
        return students;
    }



    public void setStudents(Collection<Student> students) {
        this.students = students;
    }



    public boolean enrollStudent(Student student) {
        boolean status = false;

        if (student == null) {
            throw new IllegalArgumentException(" Student object is null ");
        } else if (student.getId() <= 0) {
            throw new IllegalArgumentException(" Student id is not valid ");
        }
        if (student.getId() > 0) {
            students.add(student);
            status = true;
        }

        return status;
    }



    public boolean unenrollStudent(Student student) {
        boolean isDelete = false;
        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {
            Student result = iterator.next();
            if (result.equals(student)) {
                iterator.remove();
                isDelete = true;
            }
        }

        return isDelete;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && weekDuration == course.weekDuration && Objects.equals(courseName, course.courseName) && Objects.equals(startDate, course.startDate) && Objects.equals(students, course.students);
    }



    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, startDate, weekDuration, students);
    }



    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", weekDuration=" + weekDuration +
                ", students=" + students +
                '}';
    }



}
