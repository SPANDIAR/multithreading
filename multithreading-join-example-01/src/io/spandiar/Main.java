package io.spandiar;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	
	static boolean RUNPARALLEL = false;

	public static void main(String[] args) {
		
		List<Long> findFactorial = Arrays.asList(4772L, 2225L, 3327L, 5448L);
		
		if(RUNPARALLEL) {
			Instant start = Instant.now();
			List<CalculateFactorial> factorialObjs = new ArrayList<>();
			for(Long input: findFactorial) {
				factorialObjs.add(new CalculateFactorial(input));
			}
			for(CalculateFactorial obj: factorialObjs) {
				obj.start();
			}
			for(CalculateFactorial obj: factorialObjs) {
				try {
					obj.join(15);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for(CalculateFactorial obj: factorialObjs) {
				if(obj.isFinished()) {
					System.out.println("Factorial of " + obj.getInput() + " is :" + obj.getResult());
				} else {
					System.out.println("Factorial of " + obj.getInput() + " is currently underway");
				}
			}
			Instant finish = Instant.now();
			System.out.println("Parallel Duration is: " + Duration.between(start, finish).toMillis());
			
		} else {
			Instant start = Instant.now();
			CalculateFactorial serialCalculator = new CalculateFactorial();
			for(Long input: findFactorial) {
				BigInteger result = serialCalculator.factorialCalculator(input);
				System.out.println("Factorial of " + input + " is :" + result);
			}
			Instant finish = Instant.now();
			System.out.println("Parallel Duration is: " + Duration.between(start, finish).toMillis());
		}
	}
}
