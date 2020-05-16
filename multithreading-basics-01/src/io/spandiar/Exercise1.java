package io.spandiar;

import java.io.IOException;

public class Exercise1 {

	public static final int COUNTDOWN = 10;

	public static void main(String [] args) {
		Thread thread = new Thread(new WaitingForUserInput());
		thread.setName("InputWaitingThread");
		//thread.setDaemon(true); 
		thread.start();

		try {
			Thread.sleep(10000);
			thread.interrupt();
		} catch (InterruptedException e) {
			System.out.println("This thread is interrupted");
			e.printStackTrace();
		}
	}

	private static class WaitingForUserInput implements Runnable {
		@Override
		public void run() {

			Thread countdown = new Thread(new Countdown());
			countdown.setName("countdownThread");
			countdown.start();

			try {
				while (true) {

					if(Thread.currentThread().isInterrupted()) {
						System.out.println("Thread is interrupted");
						return;
					}

					char input = (char) System.in.read();
					if(input == 'q') {
						countdown.interrupt();
						return;
					}
				}
			} catch (IOException e) {
				System.out.println("An exception was caught " + e);
			};
		}
	}

	private static class Countdown implements Runnable{

		@Override
		public void run() {
			System.out.println("Enter q to begin: ");
			for(int i=COUNTDOWN; i>=0; i--) {
//				if(Thread.currentThread().isInterrupted()) {
//					System.exit(0);
//				}
				try {
					Thread.sleep(750);
				} catch (InterruptedException e) {
					//e.printStackTrace();
					System.out.println("Countdown is stopped");
					System.out.println("Entering into system");
					System.exit(0);
				}
				System.out.println("Countdown: " +i);
			}
		}

	}

}
