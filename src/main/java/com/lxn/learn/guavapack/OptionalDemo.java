package com.lxn.learn.guavapack;

import com.google.common.base.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Student student = null;
        Optional<Student> possibleNull = Optional.of(student);
        Student student1 = possibleNull.get();
        System.out.println(student1);
    }
    public static class Student { }
}
