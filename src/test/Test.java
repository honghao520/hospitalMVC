package test;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class Test extends JFrame{
    //声明组件
    private JTable table;
    private DefaultTableModel model;
    private JButton deleteButton;
    private JPanel panel;
    public Test() {
// TODO Auto-generated constructor stub
//初始化组件
        panel = new JPanel();
        String[] columnNames = {"编号","用户名","密码"};
        String[][]data={{"1","zhangsan","123456"},{"2","lisi","4567"}};
        model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        deleteButton = new JButton("删除");
        panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table);
//添加组件
        panel.add(scrollPane,BorderLayout.CENTER);
        panel.add(deleteButton,BorderLayout.SOUTH);
        this.add(panel);
//设置窗口的基本属性.
        this.setVisible(true);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//添加事件监听器
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method stub
//获取要删除的行,没有选择是-1
                int row = table.getSelectedColumn();
                if(row == -1){
                    JOptionPane.showMessageDialog(Test.this,"请选择要删除的行!");
                }else{
                    model.removeRow(row-1);
                }
            }
        });
    }
    public static void main(String[] args) {
        new Test();
    }
}
