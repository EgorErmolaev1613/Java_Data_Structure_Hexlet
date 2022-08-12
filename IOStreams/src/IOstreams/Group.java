package IOstreams;

import java.util.ArrayList;
import java.util.List;
//Реализуйте отдельный класс GroupFileStorage в котором будет 2 следующих метода:
//1. saveGroupToCSV (Group gr) - запись группы в CVS файл
//2. loadGroupFromCSV(File fl) -  вычитка и возврат группы из файла

//Для пояснения - группа состоит из имени и List Студентов, в свою очередь у каждого студена есть ID и Имя.
public class Group {
    String name;
    List<Student> students = new ArrayList<>();

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudentToGroup (Student newStudent){
        students.add(newStudent);
    }
    public void printStudents (){
        for (Student st : students){
            System.out.println(st.toString());
        }
    }
    public int sizeOfGroup () {
        return students.size();
    }

}
