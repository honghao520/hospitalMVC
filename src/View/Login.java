package View;

/**
 * @program: hospitalMVC
 * @author: Hong Hao
 * @description:: 医生及病人登陆界面
 * @create: 2018-11-23 09:43
 */
import Data.Name;
import Model.LoginSystem;

import javax.swing.*;
import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login{
    private PatientDiagnosis patientDiagnosis;
    private JFrame frame = new JFrame("登录");
    private Container c = frame.getContentPane();
    private JTextField username = new JTextField();//用户登录账号
    private JPasswordField password = new JPasswordField();//用户登录密码
    private JButton ok = new JButton("确定");//确定按钮
    private JButton cancel = new JButton("取消");//取消按钮
    private JLabel zhuce = new JLabel("注册");//注册
    private DoctorDiagnosis diagnosis;//创建一个医生操作界面对象
    JLabel jlb = new JLabel("用户");
    private PatientRegister register;
    JComboBox jcb;
    String selectuser = "医生";

    /**
     * @descroption 一个医生以及病人登陆的一个Frame
     */
    Login(){
        frame.setSize(300,200);
        c.setLayout(new BorderLayout());
        initFrame();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * @description 医生以及病人登陆界面(各种组件的集合)
     */
    private void initFrame(){
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("医生以及病人登陆"));
        c.add(titlePanel,"North");

//登陆账号密码输入
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        JLabel a1 = new JLabel("用户名:");
        a1.setBounds(50,20,50,20);
        JLabel a2 = new JLabel("密  码:");
        a2.setBounds(50,50,50,20);
        fieldPanel.add(a1);
        fieldPanel.add(a2);
        username.setBounds(110,20,120,20);
        password.setBounds(110,50,120,20);

//医生病人选择
        String []where={"医生","病人"};
        jcb =new JComboBox(where);

        jlb.setBounds(50,80,60,20);
        jcb.setBounds(100,80,60,20);
        fieldPanel.add(jlb);
        fieldPanel.add(jcb);

//病人注册
        JLabel label50 = new JLabel("还没有账号?");
        zhuce.setForeground(Color.blue);
        label50.setBounds(170,80,90,20);
        zhuce.setBounds(250,80,30,20);
        fieldPanel.add(label50);
        fieldPanel.add(zhuce);
        fieldPanel.add(username);
        fieldPanel.add(password);
        c.add(fieldPanel,"Center");


//底部按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(ok);
        buttonPanel.add(cancel);
        c.add(buttonPanel,"South");
//点击事件
        dealwhithenvent();

    }
    //事件监听
    private void dealwhithenvent(){
        //登陆点击
        LoginSystem loginSystem = new LoginSystem();

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectuser = (String)jcb.getSelectedItem();
                if(selectuser == "医生") {
                    if (loginSystem.doctor_login(username.getText(), password.getText()) == 1) {
                        JOptionPane.showMessageDialog(null, "登陆成功！");
                        frame.dispose();
                        diagnosis = new DoctorDiagnosis();
                        diagnosis.setResizable(false);
                        diagnosis.setVisible(true);
                    } else if (loginSystem.doctor_login(username.getText(), password.getText()) == 0) {
                        JOptionPane.showMessageDialog(null, "登陆失败");
                    } else {
                        JOptionPane.showMessageDialog(null, "用户名不存在");
                    }
                }else if(selectuser == "病人"){

                    // TODO 病人挂号界面的添加
                    if (loginSystem.patient_login(username.getText(),password.getText()) == 1){
                        Name.name = username.getText();
                        System.out.println(Name.name);
                        JOptionPane.showMessageDialog(null,"登陆成功！");
                        patientDiagnosis = new PatientDiagnosis();
                        patientDiagnosis.setResizable(false);
                        patientDiagnosis.setVisible(true);
                    } else if (loginSystem.patient_login(username.getText(),password.getText()) == 0){
                        JOptionPane.showMessageDialog(null,"登陆失败");
                    } else{
                        JOptionPane.showMessageDialog(null,"用户名不存在");
                    }

                }
            }
        });



        //注册点击事件
        zhuce.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                register = new PatientRegister();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                zhuce.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public static void main(String[] args){

        new Login();
    }
}



