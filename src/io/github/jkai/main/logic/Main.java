package io.github.jkai.main.logic;

public class Main {

	public static void main(String[] args) {
		final GpioControl gc = new GpioControl();
		try {
			gc.test1();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
