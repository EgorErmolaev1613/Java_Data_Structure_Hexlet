package org.hexlet.JavaDataStructure.lesson2;

import org.hexlet.JavaDataStructure.lesson1.Student;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Group implements Iterable<Student> {

    private Student[] students;

    public Group(final Student[] students) {
        this.students = students;
    }

    public final Iterator<Student> iterator() {
        return new StudentsIterator(students);
    }

    private static class StudentsIterator implements Iterator<Student> {
        // BEGIN (write your solution here)
        private int index =0;

        private final Student [] students;

        private StudentsIterator(Student[] students) {
            this.students = students;
        }

        @Override
        public boolean hasNext() {

            return this.students.length >index;
        }

        @Override
        public Student next() {
            if (this.hasNext() == false) {
                throw new NoSuchElementException();
            }
            return this.students [index++];
        }


        // END
    }
}
