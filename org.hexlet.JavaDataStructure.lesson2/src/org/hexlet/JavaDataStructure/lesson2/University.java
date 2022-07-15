//В этом практическом задании Вам необходимо создать итераторы для двух классов:
//
//        University
//        Group
//        Эти два класса имеют два разных итератора.
//        Класс University использует нестатический (non-static) вложенный класс для реализации итератора,
//        соответственно, итератор имеет доступ ко всем полям объекта, который создает этот итератор.
//
//        Класс Group использует итератор, который является статическим классом и,
//        соответственно, сам итератор не имеет доступа к полям объекта Group.
//
//        Оба итератора должны корректно реализовывать API интерфейса Iterator.
//        В частности, обратите внимание какое исключение бросает метод next в соответствии
//        с официальной документацией.
//
//        Ваша реализация должна в точности следовать документации,
//        а соответственно бросать корректное исключение в необходимых случаях
package org.hexlet.JavaDataStructure.lesson2;

import org.hexlet.JavaDataStructure.lesson1.Student; // импорт класса Student из прошлого задания.

import java.util.Iterator;
import java.util.NoSuchElementException;

public class University implements Iterable<Student> {

    private final Student[] students;

    public University(final Student[] students) {
        this.students = students;
    }

    public final Iterator<Student> iterator() {
        return new StudentsIterator();
    }

    private class StudentsIterator implements Iterator<Student> { // тот эе класс и реализация из прошлого занятия
        // BEGIN (write your solution here)
        private int  index = 0;
        @Override
        public boolean hasNext() {

            return University.this.students.length > index;
        }

        @Override
        public Student next() {
            if (this.hasNext() == false) {
                throw new NoSuchElementException();
            }
            return University.this.students[index++];
        }
        // END
    }
}
