package guavapack;

import com.google.common.base.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Student student = new Student();
        Optional<Student> possibleNull = Optional.of(student);
        Student student1 = possibleNull.get();
        System.out.println("Aa");
    }
    public static class Student { }
}
