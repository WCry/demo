package com.zxp.service;

import com.zxp.ClassB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Component
public class ClassC {
    final ClassB classB;

    @Autowired
    public ClassC(ClassB classB) {this.classB = classB;}

    public String getName() {
        return classB.getName();
    }

    public void setName(String name) {
        classB.setName(name);
    }
}
