package io.github.jkai.smart.bar;

import java.io.Serializable;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class GpioControl implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	private static GpioPinDigitalInput pin0;
	private static GpioPinDigitalOutput pin1;
	private static GpioPinDigitalOutput pin2;
	private static GpioPinDigitalOutput pin3;
	private static GpioPinDigitalOutput pin4;
	private static int flowCounter;
	private GpioController gpio;

	public GpioControl() {
		flowCounter = 0;
		gpio = GpioFactory.getInstance();
		pin0 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00,
				PinPullResistance.PULL_DOWN);
		pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "pump#1");
		pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "pump#2");
		pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "pump#3");
		pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "valve");
	}

	public void pump1On() {
		pin1.high();
	}

	public void pump2On() {
		pin2.high();
	}

	public void pump3On() {
		pin3.high();
	}

	public void valveOn() {
		pin4.high();
	}

	public void pump1Off() {
		pin1.low();
	}

	public void pump2Off() {
		pin2.low();
	}

	public void pump3Off() {
		pin3.low();
	}

	public void valveOff() {
		pin4.low();
	}

	public void shutOff() {
		gpio.shutdown();
	}

	public double flowRate() {

		long startTime = System.currentTimeMillis();
		long currentTime;
		double flow_rate;

		pin0.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(
					GpioPinDigitalStateChangeEvent event) {
				if (event.getState().isHigh()) {
					countUp();
				}
			}
		});

		for (;;) {
			currentTime = System.currentTimeMillis();
			if (currentTime >= (startTime + 1000)) {
				flow_rate = 7.5 * flowCounter;
				flowCounter = 0;
				break;
			}
		}
		return flow_rate / 3600;

	}

	private void countUp() {
		flowCounter++;
	}
	
	
}
