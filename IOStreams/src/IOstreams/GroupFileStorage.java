package IOstreams;

import java.io.*;
import java.util.Scanner;

public class GroupFileStorage {


    public void saveGroupToCSV(Group gr) {
        String str = gr.getName() + ".CSV";
        File fileCSV = new File(str);
        try (OutputStream os = new FileOutputStream(fileCSV)) {
            for (int i = 0; i < gr.sizeOfGroup(); i++) {
                String studentIDandName = gr.students.get(i).getId() + "\n"
                        + gr.students.get(i).getFirstName() + "\n";
                byte[] bytes = studentIDandName.getBytes();
                os.write(bytes);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Group loadGroupFromCSV(File fl) throws FileNotFoundException {
        Group newGroup = new Group(fl.getName());
        try (FileReader reader = new FileReader(fl)) {
            Scanner cr = new Scanner(reader);
            while (cr.hasNextLine()){
                Student newStudent = new Student(0,null);
                newStudent.setId(Integer.parseInt(cr.nextLine()));
                newStudent.setFirstName(cr.nextLine());
                newGroup.addStudentToGroup(newStudent);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newGroup;
    }
}
