package io.spandiar;
import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

    public static int A = 5;
    public static int B = 10;
    private List<Runnable> runnables;

    /* 
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        this.runnables = tasks;
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
    	for(Runnable runnable : this.runnables) {
    		
    		Thread thread = new Thread(runnable);
    		thread.start();
    	}
    }
    
    public static void main(String[] args) {
    	
    	List<Runnable> runnables = new ArrayList<>();
    	Thread add = new Thread(new Runnable() {
			@Override
			public void run() {
				
				System.out.println("Sum is: " + (A+B));
			}
    	});
    	
    	Thread multiply = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Product is: " + (A*B));
			}
    	});
    	
    	add.setName("theAddThread");
    	multiply.setName("theMultiplyThread");
    	
    	runnables.add(add);
    	runnables.add(multiply);
    	
    	MultiExecutor mExecutor = new MultiExecutor(runnables);
    	mExecutor.executeAll();
		
	}


}