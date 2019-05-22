package View;

import Tools.NumberTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.text.DecimalFormat;

/**
 * @program: hospitalMVC
 * @author: Hong Hao
 * @description:t填加药品数量以及价格显示:
 * @create: 2018-11-19 18:48
 */

public class AddMedicine extends JFrame implements Runnable {
    private JButton ok;//确定按钮
    private JTextField num;//填入药品的数量
    //用来规范保留两位小数
    private DecimalFormat df;
    private JLabel sum;//药品总价
    private Thread thread;
    private JLabel priceL;//药品单价的显示
    private JLabel nameL;//药品名称
    String name;//药品名称
    float price;//药品单价
    int medicineNum;//数量
    Object[] selectmedicine;//存放药品名称，药品数量，药品单价，药品总价的一个数组；


    /**
     * 创建一个添加药品的界面.
     *
     * @param
     */

    public AddMedicine(final DoctorDiagnosis diagnosis) {
        df = new java.text.DecimalFormat("#.00");
        name = diagnosis.name;
        price = diagnosis.price;
        this.setVisible(true);
        this.setBounds(400, 200, 400, 240);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 240);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("名称");
        nameLabel.setBounds(46, 0, 72, 18);
        panel.add(nameLabel);

        nameL = new JLabel(name);
        nameL.setBounds(176, 0, 150, 18);
        panel.add(nameL);

        JLabel priceLabel = new JLabel("单价");
        priceLabel.setBounds(46, 30, 72, 18);
        panel.add(priceLabel);

        priceL = new JLabel(String.valueOf(price));
        priceL.setBounds(176, 30, 72, 18);
        panel.add(priceL);

        JLabel numLabel = new JLabel("数量");
        numLabel.setBounds(46, 70, 72, 18);
        panel.add(numLabel);

        num = new JTextField();
        num.setDocument(new NumberTextField());
        num.setBounds(176, 70, 101, 24);
        panel.add(num);
        num.setColumns(10);

        JLabel sumLabel = new JLabel("总价");
        sumLabel.setBounds(46, 110, 72, 18);
        panel.add(sumLabel);

        sum = new JLabel("0");
        sum.setBounds(176, 110, 167, 18);
        panel.add(sum);
        //开始线程，必须在sum定义之后
        thread = new Thread(this);
        thread.start();

        JButton cancle = new JButton("取消");
        cancle.setBounds(40, 150, 113, 27);
        panel.add(cancle);

        cancle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //终止线程
                thread.stop();
                AddMedicine.this.dispose();
            }
        });

        ok = new JButton("确定");
        ok.setBounds(194, 150, 113, 27);
        panel.add(ok);
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String query = "update product set ";

                thread.stop();
                AddMedicine.this.dispose();

                selectmedicine = new Object[]{name, price, medicineNum, sum.getText()};
                if (medicineNum == 0) {

                } else {
                    diagnosis.setMedicine(selectmedicine);
                }
                ok.disable();
            }
        });


        //监听窗口关闭时间，窗口关闭时终止线程
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                thread.stop();
                AddMedicine.this.dispose();
            }
        });

    }

    @Override
    public void run() {
        while (true) {
            //没有输入的时候总价为药品价格
            try {
                if (num.getText().trim().equals("")) {
                    sum.setText("0");
                    this.repaint();
                } else {
                    sum.setText(df.format(Double.valueOf(priceL.getText().trim()) * Integer.parseInt(num.getText().trim())));
                    medicineNum = Integer.parseInt(num.getText().trim());

                }
                this.repaint();
            } catch (Exception e) {
                /*e.printStackTrace();*/
                // TODO 此处有BUG 给测试发现
            }
        }

    }
}





