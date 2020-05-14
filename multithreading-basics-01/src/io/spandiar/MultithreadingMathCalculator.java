package io.spandiar;

import java.util.ArrayList;
import java.util.List;

public class MultithreadingMathCalculator {
	
	public static int INTA;
	public static int INTB;
	
	public static void main(String[] args) {
		
		INTA = Integer.parseInt(args[0]);
		INTB = Integer.parseInt(args[1]);
		
		List<Thread> threadList = new ArrayList<>();
		threadList.add(new Add());
		threadList.add(new Subtract());
		threadList.add(new Multiply());
		threadList.add(new Divide());
		
		for(Thread threadObj: threadList) {
			threadObj.start();
		}
	}
	
	public static class Add extends Thread{
		
		public void run(int a, int b) {
			System.out.println(this.getName() + " : Sum is : " + (a+b));
		}
		
		public void run() {
			this.setName("theAddThread");
			this.run(INTA, INTB);
		}
		
		public void start(int a, int b) {
			this.run(a, b);
		}
		
	}
	
	public static class Subtract extends Thread{
		
		public void run(int a, int b) {
			System.out.println(this.getName() + " : Difference is : " + (a-b));
		}
		
		public void run() {
			this.setName("theSubtractThread");
			this.run(INTA, INTB);
		}
		
		public void start(int a, int b) {
			this.run(a, b);
		}
		
	}
	
	public static class Multiply extends Thread{
		
		public void run(int a, int b) {
			System.out.println(this.getName() + " : Product is : " + (a*b));
		}
		
		public void run() {
			this.setName("theMultiplyThread");
			this.run(INTA, INTB);
		}
		
		public void start(int a, int b) {
			this.run(a, b);
		}
		
	}
	
	public static class Divide extends Thread{
		
		public void run(int a, int b) {
			System.out.println(this.getName() + " : Divide is : " + Float.valueOf(a/b));
		}
		
		public void run() {
			this.setName("theDivideThread");
			this.run(INTA, INTB);
		}
		
		public void start(int a, int b) {
			this.run(a, b);
		}
	}

}
