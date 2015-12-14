package chen.usaco.dec2015;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Problem2Solution2 {

	public int getMaxOverSpeed(int[][] speedLimitsArr, int[][] drivingSpeedsArr) {
		RangeSpeed[] speedLimits = toSpeeds(speedLimitsArr);
		RangeSpeed[] drivingSpeeds = toSpeeds(drivingSpeedsArr);

		int[] mileagePoints = new int[speedLimits.length + drivingSpeeds.length];
		int mileagePointsIndex = 0;

		for (RangeSpeed speed : speedLimits) {
			mileagePoints[mileagePointsIndex++] = speed.getBegin();
		}

		for (RangeSpeed speed : drivingSpeeds) {
			mileagePoints[mileagePointsIndex++] = speed.getBegin();
		}

		int maxOverSpeed = 0;
		for (int distance : mileagePoints) {
			int overSpeed = getOverSpeed(distance, speedLimits, drivingSpeeds);
			if (overSpeed > maxOverSpeed) {
				maxOverSpeed = overSpeed;
			}
		}

		return maxOverSpeed;
	}

	private int getOverSpeed(int distance, RangeSpeed[] speedLimit, RangeSpeed[] drivingSpeeds) {
		int speedDiff = getSpeed(distance, drivingSpeeds) - getSpeed(distance, speedLimit);
		return Math.max(speedDiff, 0);
	}

	private int getSpeed(int distance, RangeSpeed[] speeds) {
		for (RangeSpeed speed : speeds) {
			if (speed.isInRange(distance)) {
				return speed.getSpeed();
			}
		}
		throw new IllegalArgumentException();
	}

	private RangeSpeed[] toSpeeds(int[][] speedsArr) {
		RangeSpeed[] result = new RangeSpeed[speedsArr.length];
		result[0] = new RangeSpeed(0, speedsArr[0][0], speedsArr[0][1]);

		for (int i = 1; i < speedsArr.length; i++) {
			result[i] = new RangeSpeed(result[i - 1].getEnd(), result[i - 1].getEnd() + speedsArr[i][0],
					speedsArr[i][1]);
		}

		return result;
	}

	public static void main(String[] args) throws IOException {
		int N, M;
		int[][] speedLimits, drivingSpeeds;

		Scanner scanner = new Scanner(new File("speeding.in"));
		N = scanner.nextInt();
		M = scanner.nextInt();

		speedLimits = new int[N][2];
		for (int i = 0; i < speedLimits.length; i++) {
			speedLimits[i][0] = scanner.nextInt();
			speedLimits[i][1] = scanner.nextInt();
		}

		drivingSpeeds = new int[M][2];
		for (int i = 0; i < drivingSpeeds.length; i++) {
			drivingSpeeds[i][0] = scanner.nextInt();
			drivingSpeeds[i][1] = scanner.nextInt();
		}
		scanner.close();

		int maxOverSpeed = new Problem2().getMaxOverSpeed(speedLimits, drivingSpeeds);

		PrintStream out = new PrintStream(new File("speeding.out"));
		out.println(maxOverSpeed);
		out.close();
	}

	private static class RangeSpeed {
		/**
		 * Distance range
		 */
		private final int begin, end;
		/**
		 * Speed
		 */
		private final int speed;

		public RangeSpeed(int begin, int end, int speed) {
			this.begin = begin;
			this.end = end;
			this.speed = speed;
		}

		public int getBegin() {
			return begin;
		}

		public int getEnd() {
			return end;
		}

		public boolean isInRange(int distance) {
			return distance >= begin && distance < end;
		}

		public int getSpeed() {
			return speed;
		}
	}
}
