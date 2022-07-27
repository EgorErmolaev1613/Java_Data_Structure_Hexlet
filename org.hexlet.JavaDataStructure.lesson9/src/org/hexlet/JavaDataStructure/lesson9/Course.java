package org.hexlet.JavaDataStructure.lesson9;
//Итак, как думаю Вы уже догадались,
//        cегодня Вам предстоит реализовать "hashCode" метод для нашего примера из прошлого задания.
//        Единственное, мы немного поменяли модель Course, а точнее поменяли тип поля uuid на String.
//
//        Метод hashCode класса Course должен работать следующим образом:
//
//        если uuid равно null - возвращает 0;
//        если uuid пустая строка - возвращает 0;
//        в других случаях нужно суммировать все characters в uuid.
//        Это и будет хеш, в данном случае.
//        Напомню, что тип char - это не более чем число, кодирующее символ.
//        Что касается класса Session,
//        то в его случае мы должны в качестве hashCode возвращать результат работы метода
//        hashCode у поля startDate(тип Date имеет свой hashCode).
import java.util.Date;
import java.util.List;

public class Course {

    private final String uuid;

    private final String name;

    private final List<Session> sessions;

    public Course(final String uuid, final String name, final List<Session> sessions) {
        this.uuid = uuid;
        this.name = name;
        this.sessions = sessions;
    }

    public final String getUuid() {
        return uuid;
    }

    public final String getName() {
        return name;
    }

    public final List<Session> getSessions() {
        return sessions;
    }

    @Override
    public final boolean equals(final Object object) {
        if (!(object instanceof Course)) {
            return false;
        }

        final Course that = (Course) object;
        return that.getUuid().equals(this.getUuid());
    }

    @Override
    public final int hashCode() {
        // BEGIN (write your solution here)
        int hash = 0;
        if (uuid == null || uuid.isEmpty()){
            return 0;
        }else {
           char HashCharArray []= uuid.toCharArray();
           for (int i = 0; i < HashCharArray.length; i++){
               hash += HashCharArray[i];
           }
        }return hash;
        // END
    }

    public class Session {

        private final Date startDate;

        public Session(final Date startDate) {
            this.startDate = startDate;
        }

        public final Date getStartDate() {
            return this.startDate;
        }

        public final Course getCourse() {
            return Course.this;
        }

        @Override
        public final boolean equals(final Object object) {
            if (!(object instanceof Session)) {
                return false;
            }

            final Session that = (Session) object;
            if (!that.getStartDate().equals(this.getStartDate())) {
                return false;
            }

            return that.getCourse().equals(this.getCourse());
        }

        @Override
        public final int hashCode() {
            // BEGIN (write your solution here)
            int hash = 0;
            return startDate.hashCode();
            // END
        }
    }
}