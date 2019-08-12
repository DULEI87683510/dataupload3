package hyc.upload.dataupload.ThreadPoolExecutor;

/**
 *@ClassName MyRunable
 *@Description TODO
 *@Author DL
 *@Date 2019/8/9 16:46    
 *@Version 1.0
 */
public class MyRunable implements Runnable {

    private int taskNum;


    public MyRunable(int num) {

        this.taskNum = num;

    }


    @Override

    public void run() {



        try {
            System.out.println("ThreadName:"+Thread.currentThread().getName()+"正在执行task " + taskNum);
            Thread.sleep(10000);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        System.out.println("task " + taskNum + "执行完毕");

    }
}
