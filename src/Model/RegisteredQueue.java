package Model;

import Tools.LimitQueue;

import java.util.Scanner;

/**
 * @program: hospital
 * @author: Dong Ping
 * @description:: 挂号队列
 * @create: 2018-11-17 11:54
 */
public class RegisteredQueue {
    private static LimitQueue<String> limitQueue = new LimitQueue<String>(3);

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RegisteredQueue registeredQueue = new RegisteredQueue();
        while (true) {
            String id = sc.next();
            registeredQueue.NextPatient(id);
            for (String s : limitQueue.getQueue()) {
                System.out.println(s);
            }
        }
    }

    /**
     * @description: 点击后队列入队
     */
    public void NextPatient(String id) {
        limitQueue.offer(id);
    }
}
