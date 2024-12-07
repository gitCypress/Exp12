package exp.io;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataWrite implements WriteFile {

    FileWriter fw;
    BufferedWriter bw;

    @Override
    public void writeToFile(String filepath, String text){

        try {
            fw = new FileWriter(filepath);
            bw = new BufferedWriter(fw);

            bw.write(text);
            JOptionPane.showMessageDialog(null, "File saved successfully!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Fatal error!");
                e.printStackTrace();
            }
            try {
                fw.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Fatal error!");
                e.printStackTrace();
            }
        }
    }

}
