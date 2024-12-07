package exp.gui;

import exp.entity.FileData;

import javax.swing.*;


public class MainWindow {
    private JPanel rootPanel;
    private JTabbedPane tabs;
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JPanel fileContent;
    private JPanel searchType;
    private JTextField searchPrompt;
    private JButton searchButton;
    private JTable searchAnswer;
    private JTabbedPane ContentTab;
    private JLabel ClassNameInputTips;
    private JTable ContentTable;
    private JScrollPane ScrollBase1;
    private JScrollPane ScrollBase2;
    private JScrollPane sAScrollPane;
    private JTabbedPane Search;
    private JPanel SearchByText;
    private JComboBox<String> classNameComboBox;
    private JButton searchButtonCB;
    private JPanel SearchByCombobox;
    private JTable searchAnswerCB;
    private JLabel BCBLabel;
    private JScrollPane SBCBScrollPane;

    public MainWindow(){

        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");

        this.menuBar.add(file);
        file.add(open);
        file.add(save);

        var rdStorage = new FileData();  // 共享数据类

        var openListener = new OpenAction(textArea, ContentTable, rdStorage, classNameComboBox);
        open.addActionListener(openListener);

        var searchListener = new SearchAction(searchPrompt, searchAnswer, rdStorage);
        this.searchButton.addActionListener(searchListener);

        var searchListenerCB = new SearchAction(searchAnswerCB, rdStorage, classNameComboBox);
        this.searchButtonCB.addActionListener(searchListenerCB);

        var saveListener = new SaveAction(textArea);
        save.addActionListener(saveListener);

        // 文本框初始化语句
        this.textArea.setText("Go to File->Open to select a data file.");

        JFrame frame = new JFrame("IrisDataTool");
        frame.setContentPane(this.rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("IrisDataTool");
        frame.setContentPane(new MainWindow().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}
