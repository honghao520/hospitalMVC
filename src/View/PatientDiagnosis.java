package View;

import Data.Name;
import Tools.Registered;
import Tools.CreateConn;
import Tools.txtExport;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import javax.swing.*;

/**
 * @program: XMLClient
 * @description: 测试
 * @author: Dong ping
 * @create: 2018-08-03 11:10
 */
public class PatientDiagnosis extends JFrame {
    PatientDiagnosis() {
        init();
        this.setSize(600, 400);
    }

    public static void main(String[] args) {
        PatientDiagnosis patientDiagnosis = new PatientDiagnosis();
        patientDiagnosis.setVisible(true);
        patientDiagnosis.setResizable(false);
    }

    public void main() {

    }

    public void init() {
        CreateConn.SelectName selectName = new CreateConn.SelectName();
        Registered registered = new Registered();
        String user = null;
        user = selectName.SelectName(Name.name) + "你好！";
        jt = new JTextArea(user);
        j2 = new JButton("打印");
        j2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    txtExport.creatTxtFile(Name.name);
                    txtExport.writeTxtFile(j9.getText());
                    JOptionPane.showMessageDialog(null,"打印成功！");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        j4 = new JPanel();
        String[] str = {"内科", "外科", "中医"};
        j5 = new JComboBox(str);
        j5.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String info = j5.getSelectedItem().toString();
                    j6.setText(info);
                }
            }
        });
        j6 = new JTextField();
        j7 = new JButton("确定");
        j7.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (j6.getText().equals("内科")) {
                    registered.info("medical_department", Name.name);
                    String id = (String) registered.ill_info(Name.name).get(0);
                    String name = (String) registered.ill_info(Name.name).get(1);
                    String sex = (String) registered.ill_info(Name.name).get(2);
                    String num = registered.registered(Name.name,"medical_department");
                    j9.setText("账号：" + id + "\n" + "姓名：" + name + "\n" + "性别：" + sex + "\n" + "科室：内科" + "\n" + "号码："+num);
                } else if (j6.getText().equals("外科")) {
                    registered.info("surgical_department", Name.name);
                    String id = (String) registered.ill_info(Name.name).get(0);
                    String name = (String) registered.ill_info(Name.name).get(1);
                    String sex = (String) registered.ill_info(Name.name).get(2);
                    String num = registered.registered(Name.name,"surgical_department");
                    j9.setText("账号：" + id + "\n" + "姓名：" + name + "\n" + "性别：" + sex + "\n" + "科室：内科" + "\n" + "号码："+num);
                } else if (j6.getText().equals("中医")) {
                    registered.info("chinese_medicine_department", Name.name);
                    String id = (String) registered.ill_info(Name.name).get(0);
                    String name = (String) registered.ill_info(Name.name).get(1);
                    String sex = (String) registered.ill_info(Name.name).get(2);
                    String num = registered.registered(Name.name,"chinese_medicine_department");
                    j9.setText("账号：" + id + "\n" + "姓名：" + name + "\n" + "性别：" + sex + "\n" + "科室：内科" + "\n" + "号码："+num);
                } else {
                    System.out.println("失败");
                }
            }
        });
        j8 = new JLabel();
        j9 = new JTextArea();
        j9.setBackground(Color.PINK);
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.add(jt);//把组件添加进jframe
        this.add(j2);
        this.add(j4);
        this.add(j5);
        this.add(j6);
        this.add(j7);
        this.add(j8);
        this.add(j9);
        GridBagConstraints s = new GridBagConstraints();//定义一个GridBagConstraints，
        //是用来控制添加进的组件的显示位置
        s.fill = GridBagConstraints.BOTH;
        //该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        //NONE：不调整组件大小。
        //HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
        //VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
        //BOTH：使组件完全填满其显示区域。
        s.gridwidth = 1;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s.weighty = 0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        layout.setConstraints(jt, s);//设置组件
        s.gridwidth = 1;
        s.weightx = 0;
        s.weighty = 0;
        layout.setConstraints(j2, s);
        s.gridwidth = 0;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 0;//不能为1，j4是占了4个格，并且可以横向拉伸，
        //但是如果为1，后面行的列的格也会跟着拉伸,导致j7所在的列也可以拉伸
        //所以应该是跟着j6进行拉伸
        s.weighty = 0;
        layout.setConstraints(j4, s);
        s.gridwidth = 2;
        s.weightx = 0;
        s.weighty = 0;
        layout.setConstraints(j5, s);
        s.gridwidth = 4;
        s.weightx = 1;
        s.weighty = 0;
        layout.setConstraints(j6, s);
        s.gridwidth = 0;
        s.weightx = 0;
        s.weighty = 0;
        layout.setConstraints(j7, s);
        s.gridwidth = 2;
        s.weightx = 0;
        s.weighty = 1;
        layout.setConstraints(j8, s);
        s.gridwidth = 5;
        s.weightx = 0;
        s.weighty = 1;
        layout.setConstraints(j9, s);
    }

    JTextArea jt;
    /*JButton j1;*/
    JButton j2;
    JPanel j4;
    JComboBox j5;
    JTextField j6;
    JButton j7;
    JLabel j8;
    JTextArea j9;
}