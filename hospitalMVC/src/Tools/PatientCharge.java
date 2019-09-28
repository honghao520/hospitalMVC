package Tools;

import java.io.*;
import java.util.Vector;

/**
 * @program: hospitalMVC
 * @author: Hong Hao
 * @description::病人的收费单
 * @create: 2018-12-04 18:21
 */
public class PatientCharge {
    private static String path = "E:\\IdeaProjects\\hospitalMVC\\src\\charge\\";
    private static String filenameTemp;


    /**
     * 创建文件
     *
     * @throws IOException
     */
    public static boolean creatTxtFile(String name) throws IOException {
        boolean flag = false;
        filenameTemp = path + name +"的收费单"+ ".txt";

        File filename = new File(filenameTemp);

        if (!filename.exists()) {
            filename.createNewFile();
            flag = true;
        }

        return flag;
    }

    /**
     * 写文件
     *
     * @param dataVector 新内容
     * @throws IOException
     */
    public static boolean writeTxtFile(Vector<Vector> dataVector,Float allprice) throws IOException {
        // 先读取原有文件内容，然后进行写入操作
        boolean flag = false;
        String medicinelist = "药品清单:"+"\r\n";
        String medicine = "";
        for(int i = 0;i<dataVector.size();i++){
            String filein = "         "+dataVector.get(i)+"\r\n";
            medicine = medicine +filein;
        }
        String aFloat = "                                       总价:    " + allprice;
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;

        try {
            // 文件路径
            File file = new File(filenameTemp);
            // 将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            StringBuffer buf = new StringBuffer();

            // 保存该文件原有的内容
            for (int j = 1; (temp = br.readLine()) != null; j++) {
                buf = buf.append(temp);
                // System.getProperty("line.separator")
                // 行与行之间的分隔符 相当于“\n”
                buf = buf.append(System.getProperty("line.separator"));
            }
            buf.append(medicinelist);
            buf.append(medicine);
            buf.append(aFloat);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            flag = true;
        } catch (IOException e1) {
            // TODO 自动生成 catch 块
            throw e1;
        } finally {
            if (pw != null) {
                pw.close();
            }

            if (fos != null) {
                fos.close();
            }

            if (br != null) {
                br.close();
            }

            if (isr != null) {
                isr.close();
            }

            if (fis != null) {
                fis.close();
            }
        }

        return flag;
    }


}

