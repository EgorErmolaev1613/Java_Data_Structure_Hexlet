package IOstreams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
// Для теста.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Student st = new Student(001,"Viktor");
        Student st1 = new Student(002,"Oleg");
        Student st2 = new Student (003,"Egor");
        Group newGroup = new Group("firstGroup");
        newGroup.addStudentToGroup(st);
        newGroup.addStudentToGroup(st1);
        newGroup.addStudentToGroup(st2);
        GroupFileStorage nw = new GroupFileStorage();
        nw.saveGroupToCSV(newGroup);
        File file = new File ("firstGroup.CSV");
        Group ne = nw.loadGroupFromCSV(file);
        ne.printStudents();

    }
}
