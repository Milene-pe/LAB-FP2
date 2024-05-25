// Labor 04
// Author: Milene Paccheco Esquinarila


import java.util.HashMap;
import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, String> courses = new HashMap<>();

        System.out.println("Welcome to the course code generator.");

        fillCourses(courses, sc);
        queryCourse(courses, sc);

        sc.close();
    }

    public static void fillCourses(HashMap<String, String> courses, Scanner sc) {
        String response;
        do {
            System.out.print("\nEnter the course name: ");
            String name = sc.nextLine();
            String code = generateCode(name, courses);
            courses.put(code, name);

            System.out.print("\nDo you want to add another course? (y/n): ");
            response = sc.nextLine();
        } while (response.equalsIgnoreCase("y"));
    }

    public static String generateCode(String courseName, HashMap<String, String> courses) {
        String code = courseName.substring(0, 2).toUpperCase();

        if (courses.containsKey(code)) {
            code += courseName.substring(courseName.length() - 1, courseName.length());
        }

        return code;
    }

   public static void queryCourse(HashMap<String, String> courses, Scanner sc) {
    String response;
    do {
        System.out.print("\nEnter the code of the course you want to query: ");
        String code = sc.next();
        if (courses.containsKey(code)) {
            System.out.println("\nThe course corresponding to code " + code + " is: " + courses.get(code));
        } else {
            System.out.println("\nNo course found with code " + code);
        }

        System.out.print("\nDo you want to query another course? (y/n): ");
        response = sc.next();
    } while (response.equalsIgnoreCase("y"));
  }
}