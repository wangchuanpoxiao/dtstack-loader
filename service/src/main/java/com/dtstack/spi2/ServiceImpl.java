package com.dtstack.spi2;

import com.dtstack.core.IStudent;
import com.dtstack.domain.Student;
import com.dtstack.spi.Service;

public class ServiceImpl implements Service {

    public String get(){
        return "hello world";
    }


    public IStudent get2(){
        Student student = new Student("zhangsan", 20, "北京");
        return student;
    }
}
