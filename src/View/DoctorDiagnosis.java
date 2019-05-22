package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;;
import Sql.SqlCommand;
import Tools.CreateConn;

/**
 * @program: hospitalMVC
 * @author: Hong Hao
 * @description:: 医生诊断操作界面
 * @create: 2018-11-08 15:45
 */
public class DoctorDiagnosis extends JFrame {
    /*
        private JFrame frame = new JFrame("诊断界面");
    */
    public DefaultTableModel modeList;
    private JMenuBar bar;//头部的菜单栏
    private JTable table;//里面存放的是显示所有药品的一个JTable
    private JTable selectList;//里面存放的是医生选择药品的一个JTable;
    private Container c = this.getContentPane();
    private JTextField search = new JTextField();
    Document dt;
    private JButton addMedicinebt = new JButton("添加药品");//添加药按钮
    private JButton removeMedicinebt = new JButton("删除药品");//删除药品按钮
    private JTextArea sex = new JTextArea(1, 5);//病人性别显示
    private JTextArea type = new JTextArea(1, 5);//病人类别显示
    private JTextArea keshi = new JTextArea(1, 5);//科室
    private JTextArea yisheng = new JTextArea(1, 5);//医生名称
    private JTextArea guoming = new JTextArea(1, 70);
    private JButton zhongyao = new JButton("中药");
    private JButton xiyao = new JButton("西药");
    private JButton jiancha = new JButton("检查治疗");
    String name;//被医生选中的药品名称
    float price;//被医生选中的药品价格


    private JComboBox jsp1, jsp2;
    private JList list;

    //医生操作界面的frame
    DoctorDiagnosis() {
        super("门诊诊断");
        this.setSize(800, 800);
        c.setLayout(new GridLayout());
        init();
        this.setResizable(false);
        this.setVisible(true);


    }

    //医生操作界面(各种组件)
    private void init() {

        JPanel panel = new JPanel();
        FlowLayout flowLayout = new FlowLayout(0);
        flowLayout.setHgap(10);
        flowLayout.setVgap(10);
        panel.setLayout(flowLayout);
//最上面的导航栏
        //菜单栏
        bar = new JMenuBar();
        //一级菜单
        JMenu menu1 = new JMenu("门诊挂号");
        JMenu menu2 = new JMenu("门诊医生");
        JMenu menu3 = new JMenu("出入院管理");
        JMenu menu4 = new JMenu("临床检查");
        JMenu menu5 = new JMenu("系统维护");
        JMenu menu6 = new JMenu("实时报告");
        JMenu menu7 = new JMenu("窗口");
        //门诊医生下的二级菜单
        JMenuItem menuItem1 = new JMenuItem("门诊就诊");
        JMenuItem menuItem2 = new JMenuItem("门诊处方打印");
        menu2.add(menuItem1);
        menu2.add(menuItem2);
        bar.add(menu1);
        bar.add(menu2);
        bar.add(menu3);
        bar.add(menu4);
        bar.add(menu5);
        bar.add(menu6);
        bar.add(menu7);
        this.setJMenuBar(bar);
        this.setVisible(true);

//门诊就诊的标题
        final FlowLayout flowLayout2 = new FlowLayout(FlowLayout.LEFT);
        JPanel jPane5 = new JPanel();
        jPane5.setPreferredSize(new Dimension(400, 20));
        jPane5.setLayout(flowLayout2);
        JLabel t = new JLabel("门诊就诊");
        jPane5.add(t);

        panel.add(jPane5);

//病人信息在医生界面上显示
        JPanel jPanel1 = new JPanel();
        GridLayout grid = new GridLayout(1, 11);
        jPanel1.setLayout(grid);
        panel.add(jPanel1);
        String[] rr = {"呜呜呜", "啥哈哈", "哇大苏打"};
        String[] ptr = {"你是asdasdasdasdasdads"};
        jsp1 = new JComboBox(ptr);
        jsp2 = new JComboBox(ptr);
        jsp1.setPreferredSize(new Dimension(10, 20));
        jsp2.setPreferredSize(new Dimension(10, 20));
        JLabel x = new JLabel("性别", JLabel.CENTER);
        JLabel y = new JLabel("病人类别", JLabel.CENTER);
        JLabel q = new JLabel("科室", JLabel.CENTER);
        JLabel g = new JLabel("医生", JLabel.CENTER);
        JLabel number = new JLabel("卡号", JLabel.CENTER);
        JLabel name = new JLabel("姓名", JLabel.CENTER);
        yisheng.setText("asd");
        sex.setEnabled(false);
        type.setEnabled(false);
        keshi.setEnabled(false);
        yisheng.setEnabled(false);
        jPanel1.add(number);
        jPanel1.add(jsp1);
        jPanel1.add(name);
        jPanel1.add(jsp2);
        jPanel1.add(x);
        jPanel1.add(sex);
        jPanel1.add(y);
        jPanel1.add(type);
        jPanel1.add(q);
        jPanel1.add(keshi);
        jPanel1.add(g);
        jPanel1.add(yisheng);

//病人过敏史的写入
        JPanel jPanel2 = new JPanel();
        flowLayout2.setVgap(0);
        flowLayout2.setHgap(0);
        jPanel2.setLayout(flowLayout2);
        JLabel ww = new JLabel("病人过敏史", JLabel.CENTER);
        ww.setForeground(Color.red);
        jPanel2.add(ww);
        jPanel2.add(guoming);
        panel.add(jPanel2);

//中药，西药，各类检查的选择按钮
        JPanel jPanel3 = new JPanel();
        jPanel3.setLayout(flowLayout2);
        jPanel3.setPreferredSize(new Dimension(800, 30));
        search.setPreferredSize(new Dimension(120, 25));
        dt = search.getDocument();
        jPanel3.add(zhongyao);
        jPanel3.add(xiyao);
        jPanel3.add(jiancha);
        jPanel3.add(search);
        jPanel3.add(addMedicinebt);
        jPanel3.add(removeMedicinebt);
        panel.add(jPanel3);

//所有药品在此界面上显示
        JPanel panel10 = new JPanel();
        panel10.setPreferredSize(new Dimension(700, 310));
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"id", "药品名字", "价格", "数量"});
        Connection conn = CreateConn.GetConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SqlCommand sqlCommand = new SqlCommand();
        String sql = sqlCommand.SelectMedicine();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name1 = rs.getString("name");
                String price = rs.getString("price");
                String num = rs.getString("num");
                model.addRow(new Object[]{id, name1, price, num});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //TODO 最后有时间可以优化界面 例如：鼠标放上去有该位置的信息显示

        TableColumn column = null;
        int colunms = table.getColumnCount();
        for (int i = 0; i < colunms; i++) {
            column = table.getColumnModel().getColumn(i);
            /*将每一列的默认宽度设置为100*/
            column.setPreferredWidth(100);
        }
        /*
         * 设置JTable自动调整列表的状态，此处设置为关闭
         */
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        /*用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看*/
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(400, 300));
        panel10.add(scroll);
        panel.add(panel10);

//药方详情的JLabel
        JPanel de = new JPanel();
        de.setPreferredSize(new Dimension(700, 20));
        JLabel drug = new JLabel("药方详情");
        de.add(drug);
        panel.add(de);

//医生给病人的药单
        JPanel druglist = new JPanel();
        druglist.setPreferredSize(new Dimension(700, 200));
        modeList = new DefaultTableModel();
        //TODO 最后有时间可以优化界面 例如：鼠标放上去有该位置的信息显示
        modeList.setColumnIdentifiers(new Object[]{"药品名字", "价格", "数量", "总价"});
        /*list.addRow(select);*/
        selectList = new JTable(modeList) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TableColumn columnn = null;
        int colunms1 = selectList.getColumnCount();
        for (int i = 0; i < colunms1; i++) {
            columnn = selectList.getColumnModel().getColumn(i);
            /*将每一列的默认宽度设置为100*/
            columnn.setPreferredWidth(100);
        }
        /*
         * 设置JTable自动调整列表的状态，此处设置为关闭
         */
        selectList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        /*用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看*/
        JScrollPane scroll1 = new JScrollPane(selectList);
        scroll1.setPreferredSize(new Dimension(400, 190));
        druglist.add(scroll1);
        panel.add(druglist);

//放入c
        c.add(panel);

//各类按钮的监听事件
        dealwith();

    }

    public void setMedicine(Object[] medicine) {//ok
        modeList.addRow(medicine);
        //TODO 加一个insert into的方法
    }

    private void dealwith() {
        //点击添加药品按钮，弹出AddMedicine的界面
        addMedicinebt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = table.getSelectedRow();//选中行的信息
                name = (String) table.getValueAt(count, 1);
                price = Float.parseFloat(table.getValueAt(count, 2).toString());
                new AddMedicine(DoctorDiagnosis.this).setVisible(true);
                System.out.println(price);
            }
        });
        //删除药品
        removeMedicinebt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int removeCount = selectList.getSelectedRow();
                modeList.removeRow(removeCount);
            }
        });
        //搜索药品
        dt.addDocumentListener(new javax.swing.event.DocumentListener(){
            public void insertUpdate(DocumentEvent e) {
                System.out.println("insertUpdate");
            }

            public void removeUpdate(DocumentEvent e) {
                System.out.println("removeUpdate");
            }


            public void changedUpdate(DocumentEvent e) {
                System.out.println("changedUpdate");
            }
        });
    }

    public static void main(String[] args) {
        new DoctorDiagnosis();
    }


}

