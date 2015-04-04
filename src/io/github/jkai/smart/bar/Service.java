package io.github.jkai.smart.bar;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Service extends UnicastRemoteObject implements
		CommunicationInterface {

	private GpioPinDigitalInput pin0 = null;
	private GpioPinDigitalOutput pin1 = null;
	private GpioPinDigitalOutput pin2 = null;
	private GpioPinDigitalOutput pin3 = null;
	private GpioPinDigitalOutput pin4 = null;
	private int flowCounter;
	private GpioController gpio = null;
	private CupDetector cupChecker;

	private static final long serialVersionUID = 1L;

	public Service(int port) throws RemoteException {
		super(port);
		flowCounter = 0;
		gpio = GpioFactory.getInstance();
		pin0 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00,
				PinPullResistance.PULL_DOWN);
		pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "pump#1");
		pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "pump#2");
		pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_12, "pump#3");
		pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, "valve");
		// this.cupChecker = new CupDetector();
	}

	@Override
	public void pinOn(int pinNo) throws RemoteException {
		switch (pinNo) {
		case 1:
			pinNo = 1;
			pump1On();
			break;
		case 2:
			pinNo = 2;
			pump2On();
			break;
		case 3:
			pinNo = 3;
			pump3On();
			break;
		case 4:
			pinNo = 4;
			valveOn();
			break;
		default:
			shutOff();
			break;
		}
	}

	@Override
	public void pinOff(int pinNo) throws RemoteException {
		switch (pinNo) {
		case 1:
			pinNo = 1;
			pump1Off();
			break;
		case 2:
			pinNo = 2;
			pump2Off();
			break;
		case 3:
			pinNo = 3;
			pump3Off();
			break;
		case 4:
			pinNo = 4;
			valveOff();
			break;
		default:
			shutOff();
			break;
		}
	}

	@Override
	public void shutOff() throws RemoteException {
		pump1Off();
		pump2Off();
		pump3Off();
	}

	@Override
	public double getFlowTime(double volume) throws RemoteException {
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
			if (currentTime >= (startTime + 500)) {
				double flow_rate_per_hour = 3.75 * 1000 * flowCounter;
				flow_rate = flow_rate_per_hour / 3600;
				flowCounter = 0;
				break;
			}
		}
		double time = volume / flow_rate;
		if (time <= 0) {
			return 2;
		} else {
			System.out.print(time);
			return volume / flow_rate;
		}

	}

	private void countUp() {
		flowCounter++;
	}

	@Override
	public void test() throws RemoteException {
		System.out.println("Good\n");

	}

	private void pump1On() {
		pin1.high();
	}

	private void pump2On() {
		pin2.high();
	}

	private void pump3On() {
		pin3.high();
	}

	private void valveOn() {
		pin4.high();
	}

	private void pump1Off() {
		pin1.low();
	}

	private void pump2Off() {
		pin2.low();
	}

	private void pump3Off() {
		pin3.low();
	}

	private void valveOff() {
		pin4.low();
	}

	@Override
	public boolean cupIsPresent() throws RemoteException {
		return true;
		// return cupChecker.checkForCup();
	}

	@Override
	public void calibration() throws RemoteException {
		cupChecker.calibrate();
	}

}
