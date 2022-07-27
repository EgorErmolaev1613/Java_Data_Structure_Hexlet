package org.hexlet.JavaDataStructure.lesson8;

//В прошлых модулях вы решали довольно сложные задачи и самостоятельно
//        реализовывали сложнейшие структуры данных
//        (один только связный список чего стоит).
//        В этом уроке мы дадим вам немного передохнуть - достаточно простое задание вас ожидает.
//        Само задание является упрощенной версией реальной задачи.
//
//        Была у нас программа, которая показывала предстоящие курсы университета на телефоне.
//        Эта программа, само собой, имела модельки:
//
//        Course - описывает курс, имя, картинку, преподавателя и т.д.;
//        Course.Session - описывает одну сессию курса (например курс Дискретной математики в 2015 году).
//        src/main/java/io/hexlet/Course.java
//        Реализуйте метод сравнения, который бы удовлетворял следующим критериям:
//
//        единственное, что нам важно для курса при сравнении это uuid курса;
//        с сервера может прийти обновленное имя, обложка, что угодно другое
//        и единственное как мы можем сравнить курс с сервера с курсом в локальной БД это его uuid;
//        для сессии сравнение немного сложнее - мы должны сравнивать дату начала сессии и курс,
//        к которому привязана сессия; только если оба эти значения одинаковы мы будем считать,
//        что сессии также одинаковы.
import java.util.List;
import java.util.Date;


public class Course {

    private final Long uuid;

    private final String name;

    private final List<Session> sessions;

    public Course(final Long uuid, final String name, final List<Session> sessions) {
        this.uuid = uuid;
        this.name = name;
        this.sessions = sessions;
    }

    private Long getUuid() {
        return uuid;
    }

    public final String getName() {
        return name;
    }

    public final List<Session> getSessions() {
        return sessions;
    }

    //CHECKSTYLE: stop EqualsHashCode check
    @Override
    public final boolean equals(final Object object) {
        // BEGIN (write your solution here)
        if (!(object instanceof Course)){
            return false;
        }final Course that = (Course) object;
        if (that.uuid.equals(this.uuid)){
            return true;
        }return false;
        // END
    }
    //CHECKSTYLE: resume EqualsHashCode check

    public class Session {

        private final Date startDate;

        Session(final Date startDate) {
            this.startDate = startDate;
        }

        final Date getStartDate() {
            return this.startDate;
        }

        final Course getCourse() {
            return Course.this;
        }

        //CHECKSTYLE: stop EqualsHashCode check
        @Override
        public final boolean equals(final Object object) {
            // BEGIN (write your solution here)
            if (!(object instanceof Course.Session)){
                return false;
            }final Course.Session that = (Session) object;
            if (that.getStartDate().equals(this.getStartDate()) &&
                    (that.getCourse().equals(this.getCourse()))){
                return true;
            }return false;
            }
            // END
        }
        //CHECKSTYLE: resume EqualsHashCode check
    }

