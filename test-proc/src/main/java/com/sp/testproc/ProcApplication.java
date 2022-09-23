package com.sp.testproc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.sp.testproc","com.zhongan.experiment"})
public class ProcApplication {


    public static void main(String[] args) {
        SpringApplication.run(ProcApplication.class, args);
        System.out.println("Load");
    }
}
