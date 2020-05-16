package io.spandiar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hacker {
	
	static final int MAX_LIMIT = 5000;
	static final int COUNTDOWN = 10;


	public static void main(String[] args) {
		
		Random random = new Random();
		Vault vault = new Vault(random.nextInt(MAX_LIMIT));
		
		List<Thread> hackerListObj = new ArrayList<>();
		hackerListObj.add(new AscendingValutBreaker(vault, "ascendingVaultBreaker"));
		hackerListObj.add(new DescendingValutBreaker(vault, "descendingVaultBreaker"));
		hackerListObj.add(new Interpol("Interpol"));
		
		for(Thread obj: hackerListObj) {
			obj.start();
		}
		
	}

	private static class Vault {

		private int password;

		public Vault(int password) {
			super();
			this.password = password;
		}

		public boolean crackVault(int password) {
			try {
				Thread.sleep(6);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return this.password == password;
		}

	}


	private static abstract class VaultBreaker extends Thread {

		protected Vault vault;

		public VaultBreaker(Vault vault) {
			//super();
			this.vault = vault;
			this.setName(this.getClass().getSimpleName());
		}
		
		@Override
		public void start() {
			System.out.println("Started thread " + this.getName());
			super.start();
		}

	}

	private static class AscendingValutBreaker extends VaultBreaker {

		public AscendingValutBreaker(Vault vault, String name) {
			super(vault);
		}
		
		@Override
		public void run() {
			for(int guess=0;guess<=MAX_LIMIT;guess++) {
				if(this.vault.crackVault(guess)) {
					System.out.println(this.getName() + " cracked the vault password " + guess);
					System.exit(0);
				}
			}
		}
	}

	private static class DescendingValutBreaker extends VaultBreaker {

		public DescendingValutBreaker(Vault vault, String name) {
			super(vault);
		}
		
		@Override
		public void run() {
			for(int guess=MAX_LIMIT;guess>=0;guess--) {
				if(this.vault.crackVault(guess)) {
					System.out.println(this.getName() + " cracked the vault password " + guess);
					System.exit(0);
				}
			}
		}

	}
	
	private static class Interpol extends Thread{

		public Interpol(String name) {
			super(name);
		}
		
		@Override
		public void run() {
			
			for(int i=COUNTDOWN; i>=0; i--) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
				System.out.println("Countdown: " + i);
			}
			
			System.out.println(this.getName() + " spoils hackers");
			System.exit(0);
			
		}
		
	}

}
