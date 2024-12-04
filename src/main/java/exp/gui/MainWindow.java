package exp.gui;

import javax.swing.*;

public class MainWindow {
    private JPanel rootPanel;
    private JTabbedPane tabs;
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JPanel fileContent;
    private JPanel fileTable;
    private JPanel searchType;
    private JTextField textField1;
    private JButton searchButton;
    private JTable SearchAnswer;
    private JTabbedPane ContentTab;
    private JLabel ClassNameInputTips;
    private JTable ContentTable;
    private JScrollPane ScrollBase1;
    private JScrollPane ScrollBase2;
    private JMenu file;
    private JMenuItem open, save;

    public MainWindow(){

        this.file = new JMenu("File");
        this.open = new JMenuItem("Open");
        this.save = new JMenuItem("Save");

        this.menuBar.add(this.file);
        this.file.add(open);
        this.file.add(save);

        var openListener = new OpenAction(textArea, ContentTable);
        this.open.addActionListener(openListener);

        // 文本框初始化语句
        this.textArea.setText("Go to File->Open to select a data file.");
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("IrisDataTool");
        frame.setContentPane(new MainWindow().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}
