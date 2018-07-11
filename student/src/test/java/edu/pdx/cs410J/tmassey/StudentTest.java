package edu.pdx.cs410J.tmassey;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.fail;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class StudentTest
{

  @Test
  public void studentNamedPatIsNamedPat() {
    String name = "Pat";
    var pat = new Student(name, new ArrayList<>(),0.0, "Doesn't matter");
    assertThat(pat.getName(), equalTo(name));
  }

  @Test(expected = IllegalArgumentException.class)
  public void gpaMustBeLessThanOrEqualToFourPointZero() {
    createStudentWithGPA(4.1);
  }

  @Test
  public void aFourPointZeroGPAIsPossible() {
    createStudentWithGPA(4.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void gpaMustBeGreaterThanZero() {
    createStudentWithGPA(-1.0);
  }

  @Test
  public void aZeroGpaIsPossible() {
    createStudentWithGPA(0.0);

  }

  @Test
  public void getGPAReturnsExpectedGpa() {
    var jack = new Student("Jack", new ArrayList<>(), 0.0, "doesn't matter");
    assertThat(jack.getGpa(), equalTo(0.0));
  }

  @Test
  public void zeroClassesIsPossible() {
    var pat = new Student("??", new ArrayList<>(),0.0, "Doesn't matter");
    assertThat(pat.getClasses(), equalTo(new ArrayList<>()));
  }

  @Test
  public void twoClassesIsPossible() {
    var classes = new ArrayList<String>();
    classes.add("Geography");
    classes.add("Weight Training");

    var pat = new Student("??", classes,0.0, "Doesn't matter");
    assertThat(pat.getClasses(), equalTo(classes));
  }

  @Test(expected = IllegalArgumentException.class)
  public void twoClassesWithLessThanZeroGpaIsImpossible() {
    var classes = new ArrayList<String>();
    classes.add("Geography");
    classes.add("Weight Training");

    var gpa = -1.0;

    new Student("??", classes, gpa, "Doesn't matter");
  }

  @Test(expected = IllegalArgumentException.class)
  public void nameIsRequiredForStudent() {
    new Student("", new ArrayList<>(), 0.0, "doesn't matter");
  }

  @Test
  public void unspecifiedGenderIsOkay() {
    var jimmy = new Student("Jimmy", new ArrayList<>(), 0.0, "doesn't matter");
    assertThat(jimmy.getGender(), equalTo("doesn't matter"));
  }

  @Test
  public void maleGenderIsOkay() {
    var jimmy = new Student("Jimmy", new ArrayList<>(), 0.0, "male");
    assertThat(jimmy.getGender(), equalTo("male"));
  }

  @Test
  public void femaleGenderIsOkay() {
    var jenny = new Student("Jimmy", new ArrayList<>(), 0.0, "female");
    assertThat(jenny.getGender(), equalTo("female"));
  }

  @Test
  public void changingGenderIsSupported() {
    var jenny = new Student("Jenny", new ArrayList<>(), 0.0, "female");
    assertThat(jenny.getGender(), equalTo("female"));
    assertThat(jenny.setGender("male"), equalTo("male"));
  }

  @Test
  public void studentSaysThisClassIsTooMuchWork() {
    var jimmy = new Student("Jimmy", new ArrayList<>(), 0.0, "doesn't matter");
    assertThat(jimmy.says(), equalTo("This class is too much work"));
  }

  @Test
  public void exampleInputFromAssignmentGeneratesExpectedToStringValue() {
    ArrayList<String> classes = new ArrayList<String>();

    classes.add("Algorithms");
    classes.add("Operating Systems");
    classes.add("Java");

    Student dave = new Student("Dave", classes, 3.64, "male");
    assertThat(dave.toString(), equalTo("Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating Systems and Java. He Says \"This class is too much work\""));
  }

  private void createStudentWithGPA(double gpa) {
    new Student("??", new ArrayList<>(), gpa, "Doesn't Matter");  // when
  }
  /*
   * Interesting test cases for GPA:
   *  GPA must be less than or equal to 4.0
   *  GPA cannot be negative
   */

}