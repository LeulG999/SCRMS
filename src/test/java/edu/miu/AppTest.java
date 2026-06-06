package edu.miu;

import edu.miu.domain.AcademicLevel;
import edu.miu.domain.Course;
import edu.miu.domain.Student;
import edu.miu.repository.CourseRepository;
import edu.miu.repository.InMemoryCourseRepository;
import edu.miu.repository.InMemoryStudentRepository;
import edu.miu.repository.StudentRepository;
import edu.miu.service.RegistrationService;
import edu.miu.util.JsonUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

public class AppTest extends TestCase {
    public AppTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testAcademicLevelRules() {
        assertEquals(AcademicLevel.GOLD, new Student("S-1", "A", "Gold", 3.5, 90).getAcademicLevel());
        assertEquals(AcademicLevel.SILVER, new Student("S-2", "A", "Silver", 3.0, 45).getAcademicLevel());
        assertEquals(AcademicLevel.BRONZE, new Student("S-3", "A", "Bronze", 2.0, 15).getAcademicLevel());
        assertEquals(AcademicLevel.REGULAR, new Student("S-4", "A", "Regular", 1.9, 14).getAcademicLevel());
    }

    public void testRegistrationUpdatesStudentAndCourse() {
        Student student = new Student("S-1", "A", "Student", 3.0, 45);
        Course course = new Course("C-1", "CS425", "Software Engineering", 4);

        student.registerCourse(course);

        assertEquals(1, student.getRegisteredCourses().size());
        assertEquals(1, course.getEnrolledStudents().size());
    }

    public void testJsonIncludesEnrollmentCount() {
        Student student = new Student("S-1", "A", "Student", 3.0, 45);
        Course course = new Course("C-1", "CS425", "Software Engineering", 4);
        student.registerCourse(course);

        String json = JsonUtil.studentsToJson(List.of(student), 1);

        assertTrue(json.contains("\"universityEnrollmentCount\": 1"));
    }

    public void testServiceReturnsSortedStudentsAndEnrollmentCount() {
        StudentRepository studentRepository = new InMemoryStudentRepository();
        CourseRepository courseRepository = new InMemoryCourseRepository();
        addTestData(studentRepository, courseRepository);
        RegistrationService service = new RegistrationService(studentRepository, courseRepository);

        assertEquals("S-100", service.getAllStudentsSortedByGpaDescending().get(0).getStudentId());
        assertEquals(7, service.getUniversityEnrollmentCount());
    }

    public void testServiceFiltersGoldStudents() {
        StudentRepository studentRepository = new InMemoryStudentRepository();
        CourseRepository courseRepository = new InMemoryCourseRepository();
        addTestData(studentRepository, courseRepository);
        RegistrationService service = new RegistrationService(studentRepository, courseRepository);

        List<Student> goldStudents = service.getGoldStudentsSortedByGpaDescending();

        assertEquals(2, goldStudents.size());
        assertEquals(AcademicLevel.GOLD, goldStudents.get(0).getAcademicLevel());
        assertEquals(AcademicLevel.GOLD, goldStudents.get(1).getAcademicLevel());
    }

    private void addTestData(StudentRepository studentRepository, CourseRepository courseRepository) {
        Course softwareEngineering = new Course("C-100", "CS425", "Software Engineering", 4);
        Course programmingPractices = new Course("C-200", "CS401", "Modern Programming Practices", 4);
        Course enterpriseArchitecture = new Course("C-300", "CS544", "Enterprise Architecture", 4);

        courseRepository.save(softwareEngineering);
        courseRepository.save(programmingPractices);
        courseRepository.save(enterpriseArchitecture);

        Student abel = new Student("S-100", "Abel", "Tesfaye", 3.8, 96);
        Student sara = new Student("S-200", "Sara", "Mekonnen", 3.2, 52);
        Student noah = new Student("S-300", "Noah", "Williams", 2.6, 22);
        Student lina = new Student("S-400", "Lina", "Ahmed", 3.7, 110);

        abel.registerCourse(softwareEngineering);
        abel.registerCourse(enterpriseArchitecture);
        sara.registerCourse(programmingPractices);
        noah.registerCourse(softwareEngineering);
        lina.registerCourse(softwareEngineering);
        lina.registerCourse(programmingPractices);
        lina.registerCourse(enterpriseArchitecture);

        studentRepository.save(abel);
        studentRepository.save(sara);
        studentRepository.save(noah);
        studentRepository.save(lina);
    }
}
