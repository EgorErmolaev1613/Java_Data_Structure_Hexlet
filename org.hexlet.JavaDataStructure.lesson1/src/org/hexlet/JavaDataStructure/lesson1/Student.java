//В этом практическом задании Вам дано два класса:
//
//        Student - класс пустышка
//        University - класс, который хранит в себе массив студентов
//        Задача! Сделать класс University "перечисляемым" (Iterable) по типу Student
//        (Iterable), чтобы объекты этого класса можно было использовать в цикле for-each.

package org.hexlet.JavaDataStructure.lesson1;

import java.util.Iterator;

public class Student implements Iterable <Student> {

    @Override
    public Iterator<Student> iterator() {
        return null;
    }
}