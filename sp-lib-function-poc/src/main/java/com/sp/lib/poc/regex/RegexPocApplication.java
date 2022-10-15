package com.sp.lib.poc.regex;

import com.zhongan.experiment.zkConfig.CuratorZookeeperClient;
import org.python.core.PyInteger;
import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SpringBootApplication(scanBasePackages = {"com.sp.lib","com.zhongan.experiment"})
public class RegexPocApplication {


    public static void main(String[] args) {
        SpringApplication.run(RegexPocApplication.class, args);
        /*
        Properties properties = new Properties();
        properties.put("python.home", "D:\\Softwares\\Dev\\Others\\jython");
        properties.put("python.console.encoding", "UTF-8");
        properties.put("python.security.respectJavaAccessibility", "false");
        properties.put("python.import.site", false);

        Properties preProps = System.getProperties();
//        String[] execArgs = {"aa","bb","cc"};
        PythonInterpreter.initialize(preProps, properties, args);

        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.exec("import sys");
//        interpreter.exec("a = \"hello\"");
//        interpreter.exec("print a");

        interpreter.execfile("D:\\Projects\\ZA\\test\\sar.py");
//        PyObject sFunc = interpreter.get("myFunc");
//        if(sFunc != null){
//            sFunc.__call__(new PyString(execArgs[0]),new PyString(execArgs[1]),new PyString(execArgs[2]));
//        }

        //Test func
        PyObject sFunc = interpreter.get("search");
        if(sFunc != null){

            List<String> keys = new ArrayList(){{ add("aa");add("bb"); add("cc");}};
            PyObject result = sFunc.__call__(new PyString("I loveaa testbb cc."), new PyList(keys));
            if(result != null){
                System.out.println(result);
            }
        }

        //Compare performance
        interpreter = new PythonInterpreter();
        interpreter.execfile("D:\\Projects\\ZA\\test\\py-script\\compareJudge.py");

        */


    }
}
