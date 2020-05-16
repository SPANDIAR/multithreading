package io.spandiar;

public class Exercise2 {
	
	public static void main(String [] args) {
        Thread thread = new Thread(new SleepingThread());
        thread.start();
        thread.interrupt();
    }
 
    private static class SleepingThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                	System.out.println("Thread is interrupted");
                	//return;
                	//System.exit(0);
                }
            }
        }
    }

}
