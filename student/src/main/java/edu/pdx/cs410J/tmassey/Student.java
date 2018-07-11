package edu.pdx.cs410J.tmassey;

import edu.pdx.cs410J.lang.Human;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * This class is represents a <code>Student</code>.                                 
 */
public class Student extends Human {

  private final ArrayList<String> classes;
  private final double gpa;
  private final String gender;

  /**
   * Creates a new <code>Student</code>
   *
   * @param name
   *        The student's name
   * @param classes
   *        The names of the classes the student is taking.  A student
   *        may take zero or more classes.
   * @param gpa
   *        The student's grade point average
   * @param gender
   *        The student's gender ("male" or "female", case insensitive)
   */
  public Student(String name, ArrayList<String> classes, double gpa, String gender) {
    super(name);

    this.classes = classes;
    this.gpa = gpa;
    this.gender = setGender(gender);

    if (name.length() < 2) {
      throw new IllegalArgumentException("Name length cannot be less than 2 characters");
    }

    if (gpa > 4.0) {
      throw new IllegalArgumentException("GPA cannot be greater than 4.0");
    }

    if (gpa < 0.0) {
      throw new IllegalArgumentException("GPA cannot be less than 0.0");
    }


  }

  public String setGender(String gender) {

    if (gender.toLowerCase().equals("male") || gender.toLowerCase().equals("female")) {
      return gender;
    } else return "doesn't matter";

  }

  /**
   * All students say "This class is too much work"
   */
  @Override
  public String says() {
    return "This class is too much work";
  }

  /**
   * Returns a <code>String</code> that describes this
   * <code>Student</code>.
   */
  public String toString() {

    var stringOutput = this.classes.stream()
            .map(Object::toString)
            .collect(Collectors.joining(", "));

    return this.getName() + " has a GPA of " + this.getGpa() + " and is taking "
            + this.getClasses().size() + " classes: "
            + stringOutput + ". " + this.printGender() + " Says \"" + this.says() + "\"";
  }

  private String printGender() {
    if(gender.equals("male")) {
      return "He";
    } else if (gender.equals("female")) {
      return "She";
    } else {
      return "They";
    }
  }

  public ArrayList<String> getClasses() {
    return classes;
  }

  public String getGender() {
    return gender;
  }

  public Double getGpa() {
    return gpa;
  }

  /**
   * Main program that parses the command line, creates a
   * <code>Student</code>, and prints a description of the student to
   * standard out by invoking its <code>toString</code> method.
   */
  public static void main(String[] args) {

    var name = !args[0].isEmpty() ? args[0] : "";
    var gender = !args[1].isEmpty() ? args[1] : "";
    var gpa = Double.parseDouble(args[2]);

    var classes = new ArrayList<String>();

    if (args.length > 3) {
      for (int i = 3; i < args.length; i++) {
        classes.add(args[i]);
      }
    }

    var student = new Student(name, classes, gpa, gender);
    System.out.println(student);

    System.exit(1);
  }
}