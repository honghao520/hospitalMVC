package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Control.PatientRegisterControl;
/**
 * @program: hospital
 * @author: Hong Hao
 * @description:: 病人注册
 * @create: 2018-11-05 21:23
 */
public class PatientRegister implements ActionListener{
    private JFrame frame = new JFrame("新农合");
    private Container c = frame.getContentPane();
    private JTextField username = new JTextField();//病人的姓名
    private JTextField userage = new JTextField();//病人的年龄
    private JTextField user1 = new JTextField();//病人的登陆用户名
    private JPasswordField password1 = new JPasswordField();//病人的登陆密码
    private JRadioButton b1 = new JRadioButton("男");
    private JRadioButton b2 = new JRadioButton("女");
    private JRadioButton b3 = new JRadioButton("是");
    private JRadioButton b4 = new JRadioButton("否");
    private String usersex="男";
    private String a="1";
    private String money1 = "100";
    private JTextField useraddress = new JTextField();//病人住址
    private JButton ok = new JButton("确定");
    private JButton cancel =new JButton("取消");

    /**
     * @descroption 病人注册的主界面显示
     */
    public PatientRegister(){
        frame.setSize(400,500);
        c.setLayout(new BorderLayout());
        initfram();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * @descroption 病人注册的主界面的各种组件
     */
    private void initfram(){
        //一个Title
        JPanel title = new JPanel();
        title.setLayout(new FlowLayout());
        title.add(new JLabel("新农合创建表"));
        c.add(title,"North");

        //病人的各种信息填入
        JPanel jPanel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        ButtonGroup group1 = new ButtonGroup();
        group.add(b1);
        group.add(b2);
        group1.add(b3);
        group1.add(b4);
        jPanel.setLayout(null);
        JLabel name = new JLabel("姓名:");
        JLabel user = new JLabel("用户名:");
        JLabel password = new JLabel("密码:");
        JLabel age = new JLabel("出生年月:");
        JLabel sex = new JLabel("性别:");
        JLabel address = new JLabel("住址:");
        JLabel ncms = new JLabel("新农合");
        JLabel money = new JLabel("初始金额:100");
        name.setBounds(120,60,70,20);
        age.setBounds(120,90,70,20);
        sex.setBounds(120,120,70,20);
        address.setBounds(120,150,70,20);
        user.setBounds(120,180,70,20);
        password.setBounds(120,210,70,20);
        ncms.setBounds(120,240,70,20);
        money.setBounds(150,270,100,20);
        username.setBounds(190,60,100,20);
        userage.setBounds(190,90,100,20);
        b1.setBounds(190,120,50,20);
        b2.setBounds(240,120,50,20);
        useraddress.setBounds(190,150,100,20);
        user1.setBounds(190,180,100,20);
        password1.setBounds(190,210,100,20);
        b3.setBounds(190,240,50,20);
        b4.setBounds(240,240,50,20);
        jPanel.add(username);
        jPanel.add(userage);
        jPanel.add(b1);
        jPanel.add(b2);
        jPanel.add(b3);
        jPanel.add(b4);
        jPanel.add(useraddress);
        jPanel.add(user1);
        jPanel.add(password1);
        jPanel.add(money);
        jPanel.add(ncms);
        jPanel.add(name);
        jPanel.add(sex);
        jPanel.add(age);
        jPanel.add(address);
        jPanel.add(user);
        jPanel.add(password);
        c.add(jPanel,"Center");


        //底部确定和取消按钮
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new FlowLayout());
        jPanel1.add(ok);
        jPanel1.add(cancel);
        c.add(jPanel1,"South");

        //点击事件
        PatientRegisterControl createtest = new PatientRegisterControl();
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(null,"确认信息吗？","消息",
                        JOptionPane.OK_CANCEL_OPTION);
                if(n==0){
                    createtest.create(username.getText(),userage.getText(),
                            usersex,useraddress.getText(),user1.getText(),password1.getText(),a,money1);
                    JOptionPane.showMessageDialog(null,"创建成功");
                    frame.dispose();

                }
            }
        });

    }


    /**
     * 选择男女和 是否为新农合的一个重写方法
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            usersex="男";
        }else if(e.getSource()==b2){
            usersex="女";
        }else if(e.getSource()==b3){
            a="1";
        }else if(e.getSource()==b4){
            a="0";
        }
    }
}

