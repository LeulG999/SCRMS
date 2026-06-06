package edu.miu.presentation;

import edu.miu.domain.Course;
import edu.miu.domain.Student;
import edu.miu.repository.CourseRepository;
import edu.miu.repository.InMemoryCourseRepository;
import edu.miu.repository.InMemoryStudentRepository;
import edu.miu.repository.StudentRepository;
import edu.miu.service.RegistrationService;
import edu.miu.util.JsonUtil;

import java.util.Scanner;

public class ScrmsCliApplication {
    private final RegistrationService registrationService;

    public ScrmsCliApplication(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public static void main(String[] args) {
        StudentRepository studentRepository = new InMemoryStudentRepository();
        CourseRepository courseRepository = new InMemoryCourseRepository();
        loadSampleData(studentRepository, courseRepository);

        RegistrationService registrationService = new RegistrationService(studentRepository, courseRepository);
        new ScrmsCliApplication(registrationService).run();
    }

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMenu();
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1" -> System.out.println(JsonUtil.studentsToJson(
                            registrationService.getAllStudentsSortedByGpaDescending(),
                            registrationService.getUniversityEnrollmentCount()));
                    case "2" -> System.out.println(JsonUtil.studentsToJson(
                            registrationService.getGoldStudentsSortedByGpaDescending(),
                            registrationService.getUniversityEnrollmentCount()));
                    case "3" -> System.out.println(JsonUtil.coursesToJson(
                            registrationService.getAllCoursesSortedByEnrollmentDescending()));
                    case "0" -> running = false;
                    default -> System.out.println("Invalid choice. Please select 1, 2, 3, or 0.");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Student Course Registration Management System");
        System.out.println("1. Display all students as JSON");
        System.out.println("2. Display Gold students as JSON");
        System.out.println("3. Display all courses as JSON");
        System.out.println("0. Exit");
        System.out.print("Select option: ");
    }

    private static void loadSampleData(StudentRepository studentRepository, CourseRepository courseRepository) {
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
