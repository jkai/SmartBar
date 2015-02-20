package io.github.jkai.smart.bar.server;

import java.io.Serializable;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class GpioControl implements Serializable,Cloneable{

	private static final long serialVersionUID = 1L;
	private final static String SUCESSFUL_INFO = "Connected to RMI Server\n";
	
	public GpioControl (){
		
	}
	
	public void test1 () throws InterruptedException{
		final GpioController gpio = GpioFactory.getInstance();

		//set up pin#01,02 as output pins
		final GpioPinDigitalOutput pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01,"LED#1");
		final GpioPinDigitalOutput pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02,"LED#2");

		//LED#1 turn on for 3s
		pin1.high();

		Thread.sleep(3000);

		//LED#1 turn off
		pin1.low();

        //LED#2 turn on for 3s
		pin2.high();

		Thread.sleep(5000);

		//LED#2 turn off
		pin2.low();

		//LED#1&2 turn on for 1s

		pin1.high();
		pin2.high();

		Thread.sleep(1000);

		pin1.low();
		pin2.low();

		gpio.shutdown();
	}
	
	public void connected(){
		System.out.println(SUCESSFUL_INFO);
	}
	
}