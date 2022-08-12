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
        String result = "";
        String [] stud = new String [10];
        try (FileReader reader = new FileReader(fl)) {
            Scanner cr = new Scanner(reader);
            for (;cr.hasNextLine();){
            result += cr.nextLine()+System.lineSeparator();
            stud = result.split(";");
            Student st = new Student(0,null);
            st.setId(Integer.parseInt(stud[0]));
            st.setFirstName(stud[1]);
            newGroup.addStudentToGroup(st);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newGroup;
    }
    public void saveGroupToCSV2(Group gr) {
        String str = gr.getName() + ".csv";
        File fileCSV = new File(str);
        try (PrintWriter pw = new PrintWriter(fileCSV)) {
            for(int i = 0; i < gr.sizeOfGroup();i++){
                pw.println(gr.students.get(i).getId()+ ";"+
                        gr.students.get(i).getFirstName());
            }

            } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    }


