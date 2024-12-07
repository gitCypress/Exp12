package exp.gui;

import exp.entity.FileData;
import exp.entity.Iris;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchAction implements ActionListener {

    private final FileData fileData;
    private final JTable contentTable;
    private JTextField searchPrompt = null;
    private JComboBox<String> comboBox = null;

    public SearchAction(JTextField searchPrompt, JTable contentTable, FileData fileData) {
        this.searchPrompt = searchPrompt;
        this.contentTable = contentTable;
        this.fileData = fileData;
    }

    public SearchAction(JTable contentTable, FileData fileData, JComboBox<String> comboBox) {
        this.contentTable = contentTable;
        this.fileData = fileData;
        this.comboBox = comboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

//        System.out.println("searchAction:" + this.fileData);
//        System.out.println("sA---\n" + this.fileData.resolvedData);

        List<Iris> irisesResolvedInfo = fileData.resolvedData;
        List<Iris> targetIrisesResolvedInfo = new ArrayList<>();

        // 获取查询关键词
        String targetClassNameFuzzy;
        if (Objects.isNull(comboBox)) targetClassNameFuzzy = this.searchPrompt.getText();
        else targetClassNameFuzzy = (String) this.comboBox.getSelectedItem();

        if (OpenAction.isOpen){  // 如果文件内容不为空

            //Table部分显示

            for (var ir : irisesResolvedInfo){
                if (ir.getClassName().contains(targetClassNameFuzzy)){
                    targetIrisesResolvedInfo.add(ir);
                }
            }
            String[] columnNames = {"Sepal Length", "Sepal Width", "Petal Length", "Petal Width", "Class Name"};

            Object[][] data = new Object[targetIrisesResolvedInfo.size()][columnNames.length];
            for (int i = 0; i < targetIrisesResolvedInfo.size(); i++){
                var ir = targetIrisesResolvedInfo.get(i);
                data[i][0] = ir.getSepalLength();
                data[i][1] = ir.getSepalWidth();
                data[i][2] = ir.getPetalLength();
                data[i][3] = ir.getPetalWidth();
                data[i][4] = ir.getClassName();
            }

            DefaultTableModel model = new DefaultTableModel(data,columnNames);
            this.contentTable.setModel(model);

        }
        else{
            JOptionPane.showMessageDialog(null, "Please select a file to proceed.", "Found Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
