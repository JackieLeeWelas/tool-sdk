package com.jackie.lee.classloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by lxb on 2019/2/22.
 */
public class MyClassLoader extends ClassLoader{
    private String classpath;

    public MyClassLoader(String classpath,ClassLoader classLoader) {
        super(classLoader);
        this.classpath = classpath;
    }
    public MyClassLoader(String classpath) {
        this.classpath = classpath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classFilePath = null;
        String finalName = name.replace(".", "/");
        classFilePath = classpath + "/" + finalName + ".class";
        Path path = Paths.get(classFilePath);
        if (!Files.exists(path)) {
            return null;
        }
        try {
            byte[] classData =  Files.readAllBytes(path);
            return defineClass(name, classData, 0, classData.length);
        } catch (IOException e) {
            throw new RuntimeException("Can not read class file into byte array");
        }
    }

}
