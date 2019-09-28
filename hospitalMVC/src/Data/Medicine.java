package Data;

/**
 * @program: hospital
 * @author: Dong Ping
 * @description:: 存放药品的信息
 * @create: 2018-11-17 13:14
 */
public class Medicine {
    public String id;
    public String name;
    public String price;
    public String num;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String toString() {
        return id + " " + name + " " + price + " " + num;
    }
}
