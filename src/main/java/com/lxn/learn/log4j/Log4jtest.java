package com.lxn.learn.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jtest {
    private static final Logger log = LoggerFactory.getLogger(Log4jtest.class);
    public static void main(String[] args) {
        log.info("lalala");
    }
}
