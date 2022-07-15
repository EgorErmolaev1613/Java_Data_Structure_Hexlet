package org.hexlet.JavaDataStructure.lesson1;

    import java.util.Arrays;
    import java.util.Iterator;
    import java.util.List;

    public final class University implements Iterable<Student> {
        private final Student [] students;

        public University(Student[] students) {
            this.students = students;
        }


        @Override
        public Iterator<Student> iterator() {
            final List<Student> studentList = Arrays.asList(students);
            return studentList.iterator();
        }
    }
