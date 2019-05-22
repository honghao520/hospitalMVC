package Model;

/**
 * @program: hospitalMVC
 * @author: Hong Hao
 * @description:: 医生选中的药品添加
 * @create: 2018-11-25 18:42
 */
public class SelectMedicine {
    String name;
    float price;
    int num;
    float sum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }
}

