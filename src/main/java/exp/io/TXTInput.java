package exp.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import exp.entity.Iris;

public class TXTInput implements ReadFile {

    List<Iris> irisList = new ArrayList<Iris>();
    String plainText;
    BufferedReader br;
    FileReader fr;

    @Override
    public String getPlainTextFromFile(String filepath) {

        try {

            StringBuilder output = new StringBuilder();
            // 文件读取
            this.fr = new FileReader(filepath);
            this.br = new BufferedReader(this.fr);

            String line = "";
            while((line = br.readLine()) != null) output.append(line).append("\n");

            this.plainText = output.toString();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Sorry,File not found", "Found Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Sorry,IO Exception", "IO Error", JOptionPane.ERROR_MESSAGE);
        }

        return this.plainText;
    }

    @Override
    public List<Iris> getResolvedDataFromFile(String filepath) {
        //read txtfile
        try {

            // 文件读取
            this.fr = new FileReader(filepath);
            this.br = new BufferedReader(this.fr);

            String line = null;
            while((line = br.readLine()) != null){
                // 使用正则表达式来选择分隔符
                String[] parts = line.split(",");
                if(parts.length == 5){
                    var iris = new Iris(
                            Double.parseDouble(parts[0]),
                            Double.parseDouble(parts[1]),
                            Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3]),
                            parts[4]
                            );
                    irisList.add(iris);
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Sorry,File not found", "Found Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Sorry,IO Exception", "IO Error", JOptionPane.ERROR_MESSAGE);
        }

        return irisList;
    }
}
