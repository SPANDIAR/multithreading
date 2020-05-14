package io.spandiar;

public class MultithreadingBasicsOne {

	public static void main(String[] args) {

		Thread myThread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Inside my parallel thread " + Thread.currentThread().getName());
			}
		});

		myThread.start();

		System.out.println("Before going to sleep " + Thread.currentThread().getName());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("after waking up " + Thread.currentThread().getName());

	}

}
