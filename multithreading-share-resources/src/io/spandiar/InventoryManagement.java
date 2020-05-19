package io.spandiar;

public class InventoryManagement {
	
	private Inventory inventory;

	public static void main(String[] args) {
		
		Inventory invMgnt = new Inventory();
		
		Thread incInv = new IncrementInventory(invMgnt);
		Thread decInv = new DecrementInventory(invMgnt);
		
		incInv.start();
		decInv.start();
		
		try {
			incInv.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			decInv.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Current Inventory Count is: " + invMgnt.getInventoryCount());

	}
	
	private static class IncrementInventory extends Thread {
		
		private Inventory inventory;

		public IncrementInventory(Inventory inventory) {
			super();
			this.inventory = inventory;
		}
		
		@Override
		public void run() {
			for(int i=0; i<=4000; i++) {
				inventory.incrementInventoryCount();
			}
		}
	}
	
	private static class DecrementInventory extends Thread {
		
		private Inventory inventory;

		public DecrementInventory(Inventory inventory) {
			super();
			this.inventory = inventory;
		}
		
		@Override
		public void run() {
			for(int i=0; i<=3000; i++) {
				inventory.decrementInventoryCount();
			}
		}
		
	}
	
	private static class Inventory {
		
		private int inventoryCount;

		public int getInventoryCount() {
			return inventoryCount;
		}

		public void setInventoryCount(int inventoryCount) {
			this.inventoryCount = inventoryCount;
		}
		
		public void incrementInventoryCount() {
			inventoryCount++;
		}
		
		public void decrementInventoryCount() {
			inventoryCount--;
		}
		
	}

}
