package io.spandiar;

import java.util.Random;

// Class demonstrating thread deadlocks

public class SouthernRailways {

	public static void main(String[] args) {

		GetGreenSignal getGreenSignal = new GetGreenSignal();
		Thread chennaiToBangalore = new ChennaiToBangalore(getGreenSignal);
		chennaiToBangalore.setName("chennaiToBangalore");
		Thread bangaloreToChennai = new BangaloreToChennai(getGreenSignal);
		bangaloreToChennai.setName("bangaloreToChennai");

		chennaiToBangalore.start();
		bangaloreToChennai.start();
	}

	private static class ChennaiToBangalore extends Thread {

		private GetGreenSignal signal;

		public ChennaiToBangalore(GetGreenSignal signal) {
			super();
			this.signal = signal;
		}

		@Override
		public void run() {
			Random random = new Random();
			
			while (true) {
				try {
					Thread.sleep(random.nextInt(5));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				getSignal();
			}
		}

		public void getSignal() {
			signal.lockChennaiToBangalore();
		}

	}

	private static class BangaloreToChennai extends Thread {

		private GetGreenSignal signal;

		public BangaloreToChennai(GetGreenSignal signal) {
			super();
			this.signal = signal;
		}

		@Override
		public void run() {
			Random random = new Random();
			while (true) {
				try {
					Thread.sleep(random.nextInt(10));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				getSignal();
			}
		}

		public void getSignal() {
			signal.lockBangaloreToChennai();
		}

	}

	private static class GetGreenSignal {

		private Object AmburToVaniyampadi;
		private Object VaniyampadiToAmbur;

		public GetGreenSignal() {
			super();
			AmburToVaniyampadi = new Object();
			VaniyampadiToAmbur = new Object();
		}

		public void lockBangaloreToChennai() {

			synchronized (AmburToVaniyampadi) {
				System.out.println("Locked AmburToVaniyampadi: " + Thread.currentThread().getName());

				synchronized (VaniyampadiToAmbur) {
					System.out.println("Locked VaniyampadiToAmbur: " + Thread.currentThread().getName());
				}
			}
		}

		public void lockChennaiToBangalore() {

			synchronized (AmburToVaniyampadi) {
				System.out.println("Locked AmburToVaniyampadi: " + Thread.currentThread().getName());

				synchronized (VaniyampadiToAmbur) {
					System.out.println("Locked VaniyampadiToAmbur: " + Thread.currentThread().getName());
				}
			}
		}
	}

}
