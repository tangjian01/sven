package sven.console.test;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/2/18 17:08
 * @description：
 * @version:
 * @see
 */
public class console {
    public static void main(String[] args) {
        ThreadDemo demo= new ThreadDemo();
        try {
            demo.runSync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
