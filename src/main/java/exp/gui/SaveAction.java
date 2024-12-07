package exp.gui;

import exp.io.DataWrite;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;

public class SaveAction implements ActionListener {

    private JTextArea textArea;

    SaveAction(JTextArea textArea){
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (OpenAction.isOpen){

            JFileChooser fileChooser = new JFileChooser();
            var DATA_TXT_filter = new FileNameExtensionFilter(
                    "Data Files",
                    "data"
            );
            fileChooser.setFileFilter(DATA_TXT_filter);

            int signal = fileChooser.showSaveDialog(null);
            if (signal == JFileChooser.APPROVE_OPTION){

                File selectedFile = fileChooser.getSelectedFile();

                // 为文件添加默认扩展名
                String filePath = selectedFile.getAbsolutePath();
                if(!filePath.endsWith(".data")) filePath = new File(filePath + ".data").getAbsolutePath();

                var areaText = textArea.getText();

                new DataWrite().writeToFile(filePath, areaText);

            }
        }
        else JOptionPane.showMessageDialog(null, "Please select a file to proceed.", "Found Error", JOptionPane.ERROR_MESSAGE);
    }
}
