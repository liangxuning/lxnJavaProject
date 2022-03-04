package com.lxn.learn.lombok;

public class TestUser {
    public static void main(String[] args) {
        User build = User.builder().build();
        User user = User.builder().age(1).name("xxx").build();
        System.out.println(user);
    }
}
