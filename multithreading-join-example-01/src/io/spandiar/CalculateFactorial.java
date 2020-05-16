package io.spandiar;

import java.math.BigInteger;

public class CalculateFactorial extends Thread {
	
	private boolean isFinished;
	private BigInteger result;
	private Long input;
	
	public CalculateFactorial() {
		super();
	}

	public CalculateFactorial(Long input) {
		super();
		this.isFinished = false;
		this.input = input;
	}
	
	@Override
	public void run() {
		this.result = factorialCalculator(this.input);
		this.isFinished = true;
	}
	
	public BigInteger factorialCalculator(Long input) {
		BigInteger tempResult = BigInteger.ONE;
		
		for(Long i = input; i>0; i--) {
			tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
		}
		return tempResult;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public BigInteger getResult() {
		return result;
	}

	public Long getInput() {
		return input;
	}

}
