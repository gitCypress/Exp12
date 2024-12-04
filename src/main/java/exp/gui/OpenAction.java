package exp.gui;

import exp.entity.Iris;
import exp.io.TXTInput;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OpenAction implements ActionListener {

    private JFileChooser chooser;
    private FileNameExtensionFilter DATA_TXT_filter;

    private String irisesInfo;
    private List<Iris> irisesResolvedInfo;

    private JTextArea textOutArea;
    private JTable contentTable;

    public OpenAction(JTextArea textOutArea, JTable contentTable){
        this.textOutArea = textOutArea;
        this.contentTable = contentTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // 初始化文件选择器，并为其绑定文件类型过滤器和默认路径
        this.chooser = new JFileChooser();
        this.DATA_TXT_filter = new FileNameExtensionFilter(
                "Data & TXT Files",
                "data", "txt"
        );
        chooser.setFileFilter(DATA_TXT_filter);
        chooser.setCurrentDirectory(Paths.get(System.getProperty("user.dir"), "res").toFile());

        // 显示文件选择器，null 表示没有父组件；返回值对应用户的不同操作
        int actionVal = chooser.showOpenDialog(null);
        if (actionVal == JFileChooser.APPROVE_OPTION){  // 如果点击了“打开”按钮

            // 文件地址
            String filePath = chooser.getSelectedFile().getAbsolutePath();  // 获取用户打开文件的绝对路径

            // PlainText部分显示
            this.irisesInfo = new TXTInput().getPlainTextFromFile(filePath);  // 使用 txt 读取类处理
            textOutArea.setText(this.irisesInfo);

            //Table部分显示
            this.irisesResolvedInfo = new TXTInput().getResolvedDataFromFile(filePath);
            String[] columnNames = {"Sepal Length", "Sepal Width", "Petal Length", "Petal Width", "Class Name"};

            Object[][] data = new Object[irisesResolvedInfo.size()][columnNames.length];
            for (int i = 0; i < irisesResolvedInfo.size(); i++){
                var ir = irisesResolvedInfo.get(i);
                data[i][0] = ir.getSepalLength();
                data[i][1] = ir.getSepalWidth();
                data[i][2] = ir.getPetalLength();
                data[i][3] = ir.getPetalWidth();
                data[i][4] = ir.getClassName();
            }

            DefaultTableModel model = new DefaultTableModel(data,columnNames);
            this.contentTable.setModel(model);

        }

    }

    public String getIrisesInfo() {
        return irisesInfo;
    }

    public void setIrisesInfo(String irisessInfo) {
        this.irisesInfo = irisessInfo;
    }
}
