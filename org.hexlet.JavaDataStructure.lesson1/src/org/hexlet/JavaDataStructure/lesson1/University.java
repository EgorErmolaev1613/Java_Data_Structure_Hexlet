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
        private class StudentIterator implements Iterator <Student> {  /*Второй вариант решения - создание собственного Итератор,
                                                                           в случае собственной реализации в методе выше наобходимо вернуть новый итератор
                                                                            public Iterator<Student> iterator() {

                                                                                           return  StudentIterator();
                                                                                                                        }*/
            private int  index = 0;
            @Override
            public boolean hasNext() {
                return University.this.students.length > index;
            }

            @Override
            public Student next() {
                return University.this.students[index++];
            }
        }
    }
