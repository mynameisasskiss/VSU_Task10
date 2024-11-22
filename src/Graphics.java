import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.Color;

public class Graphics extends JFrame{

    private JButton solveBtn;
    private JTable inputTable;
    private JTextArea outputArea;
    private JPanel pane;
    private JButton minusRow;
    private JButton plusRow;

    public Graphics(){
        super("БесПодобная программма");
        setContentPane(pane);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        setJMenuBar(menuBar);
        setTable(new int[][]{{0,0,0,0,0,0},{0,0,0,0,0,0}});
        inputTable.setGridColor(Color.BLACK);

        solveBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] matrix = getTable();
                List<List<Triangle>> result = Triangle.getFromMatrix(matrix);
                String text = Triangle.getResult(result).replace("}{","}\n\n{").replace("]], [[","]],\n[[");
                outputArea.setText(text);
            }
        });
        plusRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] newRow = new Integer[inputTable.getColumnCount()];
                for (int i = 0; i < inputTable.getColumnCount(); i++)
                    newRow[i] = 0;
                ((DefaultTableModel) inputTable.getModel()).addRow(newRow);
            }
        });
        minusRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((DefaultTableModel) inputTable.getModel()).removeRow(inputTable.getRowCount() - 1);
            }
        });
    }

    private JMenu createFileMenu() {
        // Создание выпадающего меню
        JMenu tableMenu = new JMenu("Таблица");

        // Пункт меню "Сохранить"
        JMenuItem saveItem = new JMenuItem("Сохранить в json");
        // Добавление в меню пункта save
        tableMenu.add(saveItem);
        // callback (метод который будет вызван при нажатии кнопки save)
        saveItem.addActionListener(arg0 -> {
            String filePath = chooseFile();
            if (filePath != null) {
                String outData = outputArea.getText();
                try {
                    FileIO.writeStringToFile(filePath.replace(".txt",".json"), outData);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // Пункт меню "Открыть"
        JMenuItem openItem = new JMenuItem("Открыть из файла");
        tableMenu.add(openItem);
        openItem.addActionListener(arg0 -> {
            String filePath = chooseFile();
            if (filePath != null) {
                try {
                    int[][] matrix = FileIO.readIntMatrixFromFile(filePath);
                    setTable(matrix);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return tableMenu;
    }

    public String chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            return selectedFile.getAbsolutePath();
        }
        return null;
    }

    /*
    Возвращает массив данных в таблице
     */
    public int[][] getTable() {
        int[][] matrix = new int[inputTable.getRowCount()][inputTable.getColumnCount()];
        for (int r = 0; r < inputTable.getRowCount(); r++) {
            for (int c = 0; c < inputTable.getColumnCount(); c++) {
                matrix[r][c] = Integer.parseInt(inputTable.getValueAt(r, c).toString());
            }
        }
        return matrix;
    }

    public void setTable(int[][] matrix) {
        DefaultTableModel dtm = (DefaultTableModel) inputTable.getModel();
        dtm.setRowCount(matrix.length);
        dtm.setColumnCount(matrix[0].length);
        inputTable.setModel(dtm);
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                inputTable.setValueAt(matrix[r][c], r, c);
            }
        }
    }

    public static void main(String[] args) {
        Graphics g = new Graphics();
    }
}
