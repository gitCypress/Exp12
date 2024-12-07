package exp.gui;

import exp.entity.FileData;
import exp.io.DataRead;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class OpenAction implements ActionListener {

    private JFileChooser chooser;
    private FileNameExtensionFilter DATA_TXT_filter;

    private String irisesInfo;
    private FileData fileData;

    private JTextArea textOutArea;
    private JTable contentTable;
    private JComboBox<String> comboBox;

    public OpenAction(JTextArea textOutArea, JTable contentTable, FileData fileData, JComboBox<String> comboBox){
        this.textOutArea = textOutArea;
        this.contentTable = contentTable;
        this.fileData = fileData;
        this.comboBox = comboBox;
    }

    public static boolean isOpen = false;

    @Override
    public void actionPerformed(ActionEvent e) {

//        System.out.println("openAction:" + this.fileData);

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
            this.irisesInfo = new DataRead().getPlainTextFromFile(filePath);  // 使用 txt 读取类处理
            textOutArea.setText(this.irisesInfo);

            //Table部分显示
            this.fileData.resolvedData = new DataRead().getResolvedDataFromFile(filePath);
            var irisesResolvedInfo = this.fileData.resolvedData;
            String[] columnNames = {"Sepal Length", "Sepal Width", "Petal Length", "Petal Width", "Class Name"};
            Set<String> classNameSet = new HashSet<>();

            Object[][] data = new Object[irisesResolvedInfo.size()][columnNames.length];
            for (int i = 0; i < irisesResolvedInfo.size(); i++){
                var ir = irisesResolvedInfo.get(i);
                data[i][0] = ir.getSepalLength();
                data[i][1] = ir.getSepalWidth();
                data[i][2] = ir.getPetalLength();
                data[i][3] = ir.getPetalWidth();
                data[i][4] = ir.getClassName();
                classNameSet.add(ir.getClassName());
            }

            for (var str : classNameSet){
                comboBox.addItem(str);
//                System.out.println(str);
            }

            DefaultTableModel model = new DefaultTableModel(data,columnNames);
            this.contentTable.setModel(model);

        }

//        System.out.println("oA---\n" + this.fileData.resolvedData);
    }

    public String getIrisesInfo() {
        return irisesInfo;
    }

    public void setIrisesInfo(String irisessInfo) {
        this.irisesInfo = irisessInfo;
    }
}
