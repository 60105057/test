package lab11.ex1;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Section {
	private int sectionNumber;
	private SectionType sectionType;
	private Course course;
	private Professor assignedProfessor;
	private Set<Student> enrolledStudents =new HashSet<Student>();
	
	
	public Section(int sessionNumber, SectionType sectionType, Course course) {
		super();
		this.sectionNumber = sessionNumber;
		this.sectionType = sectionType;
		this.course = course;
	}

	// Method to add a single student
	public void addStudent(Student student) {
		if (student != null && !isStudentEnrolled(student)) {
			enrolledStudents.add(student);
			CourseAdmin.addEnrolledStudent(student);
			System.out.println("Student " + student.getFirstName() + " " + student.getLastName()
					+ " has been successfully enrolled in " + this.course.getCourseName()+" "+this.sectionType +" Section!");
		} else {
			System.out.println("Student you try to add is already enrolled in " + this.course.getCourseName() + " or has a null value!");
		}
	}

	// Method to remove a single student
	public void removeStudent(Student student) {
		if (student != null && isStudentEnrolled(student)) {
			enrolledStudents.remove(student);
			System.out.println("Student " + student.getFirstName() + " " + student.getLastName()
					+ " has been successfully unenrolled from " + this.course.getCourseName() + " course!");
			CourseAdmin.removeEnrolledStudent(student);
		} else {
			System.out.println("Student " + student.getFirstName() + " " + student.getLastName()
					+ " was not enrolled in " + this.course.getCourseName() + "!");
		}
	}
	
	// Overloaded method to add multiple enrolledStudents
	public void addStudent(Student[] newStudents) {
		if (Objects.nonNull(newStudents) && newStudents.length>0)
			for (Student student : newStudents) {
				if (student != null)
					addStudent(student); // Use the single addStudent method to avoid redundancy
			}
		else 
			System.out.println("The list of enrolledStudents is null or empty");
	}
	
	// Method to check if a student is already enrolled
	private boolean isStudentEnrolled(Student student) {
		for (Student s : enrolledStudents) {
			if (s.equals(student)) 
				return true; // Student is already enrolled	
		}
		return false;
	}

	
	// Method to display enrolledStudents and their average grades
		public void displayStudents() {
			String gradesInfo;
			for (Student student : enrolledStudents) {
				double[] grades = student.getGrades();

				// Check if all grades are zero
				boolean allGradesZero = true;
				for (double grade : grades) {
					if (grade != 0) {
						allGradesZero = false;
						break;
					}
				}
				if (grades.length == 0 || allGradesZero) {
					gradesInfo = "Student: " + student.getFirstName() + " " + student.getLastName()
							+ " still didn't receive any grade.";
				} else {
					gradesInfo = "Student: " + student.getFirstName() + " " + student.getLastName() + ", Average Grade: "
							+ student.calculateAverage();
				}
				System.out.println(gradesInfo);
			}
		}
		
		//diplays the gradel level of the enrolledStudents enrolled in the course
		public void displayStudentGradeLevel() {
			System.out.println("Student's Grade Level of the course:" + this.course.getCourseName());
			for (Student student : enrolledStudents)
				System.out.println(
						student.getFirstName() + ", " + student.getLastName() + ", Grade:" + student.getGradeLevel());
		}
	public int getSectionNumber() {
		return sectionNumber;
	}


	public void setSectionNumber(int sessionNumber) {
		this.sectionNumber = sessionNumber;
	}


	public SectionType getSessionType() {
		return sectionType;
	}


	public void setSessionType(SectionType sectionType) {
		this.sectionType = sectionType;
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public Professor getAssignedProfessor() {
		return assignedProfessor;
	}


	public void setAssignedProfessor(Professor assignedProfessor) {
		this.assignedProfessor = assignedProfessor;
	}

	public Set<Student> getEnrolledStudents() {
		return enrolledStudents;
	}
	public void setEnrolledStudents(HashSet<Student> students) {
		this.enrolledStudents = students;
	}

	@Override
	public String toString() {
		return "Section [sectionNumber=" + sectionNumber + ", sectionType=" + sectionType + ", course=" + course.getCourseName() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(course, sectionNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Section other = (Section) obj;
		return Objects.equals(course, other.course) && sectionNumber == other.sectionNumber;
	}

}
