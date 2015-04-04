package io.github.jkai.smart.bar;

import java.io.IOException;

public class CupDetector {
	// Configurables
	private int readDelay = 100; // in milliseconds
	private int bufferSize = 9;// odd number for median
	private static boolean verbose = false;
	private int proxLowThreshold = 9000;
	private int numRejectVals = 10;
	private int minimumCupFactor = 3000;
	private int numCalSamples = 9;

	// Non-configurables
	private FixedBuffer buffer = new FixedBuffer(bufferSize);
	private AdafruitVCNL4000 vcnl;
	private int proxVal;
	private int medianProxVal = -1;
	private int noCupProxVal = -1;

	public CupDetector() {
		vcnl = new AdafruitVCNL4000(0x13);
		this.calibrate();
	}

	public void calibrate()// **ALWAYS CALIBRATE WITH NO CUP**
	{
		int temp;
		// reject the first set of readings
		for (int i = 0; i < numRejectVals; i++) {
			getProx();
		}

		// set a median value with no cup to compare to to check for a cup later
		for (int i = 0; i < numCalSamples; i++) {
			temp = sampleProx();
			if (temp > noCupProxVal) {
				noCupProxVal = temp;
			}
		}

		// set the new threshold closer to the calibrated reading
		proxLowThreshold = (noCupProxVal - minimumCupFactor);

		// if verbose, let the user know calibration is done
		if (verbose) {
			int sum = noCupProxVal + minimumCupFactor;
			System.out.println("Calibration complete.\nUsing " + sum
					+ " for comparison.");
		}
	}

	public boolean checkForCup() {
		// get the median of the readings
		medianProxVal = sampleProx();

		// if verbose, print the median
		if (verbose) {
			System.out.println("Median:    " + medianProxVal);
		}

		// check if median is greater than the sum of the calibrated one and the
		// minimum cup diameter factor
		if (medianProxVal > (noCupProxVal + minimumCupFactor)) {
			if (verbose) {
				System.out.println("Found cup!");
			}
			return true;
		} else {
			if (verbose) {
				System.out.println("No cup found.");
			}
			return false;
		}
	}

	public int sampleProx() {
		// fill buffer with new values
		for (int i = 0; i < bufferSize; i++) {
			getProx();
			// update the buffer with the proximity reading if it's in the
			// threshold
			if (proxVal >= proxLowThreshold) {
				buffer.pushNPop(proxVal);
			} else {
				i--;
			}
		}
		// return the median
		return buffer.getMedian();
	}

	public void getProx() {
		// store proximity value
		try {
			proxVal = vcnl.readProximity();
		} catch (IOException e) {
			// This is so we don't print the error if the connection is bad
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// print proximity reading if verbose
		if (verbose) {
			try {
				System.out.println("Proximity: " + proxVal);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// wait between reads
		try {
			Thread.sleep(readDelay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
