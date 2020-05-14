package io.spandiar;

public class MultithreadingBasicsOne {

	public static void main(String[] args) {

		Thread myThread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Inside my parallel thread " + Thread.currentThread().getName());
				System.out.println("My priority is " + Thread.currentThread().getPriority());
				//throw new RuntimeException("I'm going rouge");
			}
		}, "myThread");

		myThread.setPriority(Thread.MAX_PRIORITY);
		myThread.start();
		
		myThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("Thread seems to misbehave " + t.getName() + " exception is " + e.getMessage());
			}
		});

		System.out.println("Before going to sleep " + Thread.currentThread().getName());
		System.out.println("after waking up " + Thread.currentThread().getName());

	}

}
