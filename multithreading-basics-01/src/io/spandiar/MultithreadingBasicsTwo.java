package io.spandiar;

public class MultithreadingBasicsTwo {

	public static void main(String[] args) {
		
		InheritingThreads someThread = new InheritingThreads();
		someThread.setName("myInheritedThread");
		someThread.start();

	}
	
	private static class InheritingThreads extends Thread{
		
		public void run() {
			System.out.println("I'm inside the run method " + this.currentThread().getName());
		}
		
	}

}
