package level_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <b>주차 요금 계산</b>
 * https://school.programmers.co.kr/learn/courses/30/lessons/92341
 * 
 * @author hrlim
 * @version 수정 2023.01.01
 */
public class Q92341 {

	public static class Car implements Comparable<Car> {
		// 차량번호
		String carSerialNum;
		// 입차 시간
		int inTime = -1;
		// 누적 이용 시간
		double useTime;

		@Override
		public int compareTo(Car o) {
			return this.carSerialNum.compareTo(o.carSerialNum);
		}
	}

	public static void main(String[] args) {
		int[] fees = { 1, 461, 1, 10 };
		String[] records = { "00:00 1234 IN" };

		System.out.println(Arrays.toString(solution(fees, records)));
	}

	/**
	 * 주차장의 요금표와 차량이 들어오고(입차) 나간(출차) 기록이 주어졌을 때, 차량별로 주차 요금을 계산하여 반환하는 메소드 요금표(기본시간,
	 * 기본요금, 단위시간, 단위요금)
	 * 
	 * @param fees    요금표
	 * @param records
	 * @return
	 */
	public static int[] solution(int[] fees, String[] records) {
		List<Car> cars = new ArrayList<>();

		int defaultTime = fees[0];
		int defaultFee = fees[1];
		int unitTime = fees[2];
		int unitFee = fees[3];

		for (String record : records) {
			Car car = null;

			String[] data = record.split(" ");
			String[] time = data[0].split(":");

			int hour = Integer.parseInt(time[0]);
			int minute = Integer.parseInt(time[1]);
			String carSerialNum = data[1];
			String inOut = data[2];

			int idx = -1;
			for (int i = 0; i < cars.size(); i++) {
				if (cars.get(i).carSerialNum.equals(carSerialNum)) {
					car = cars.remove(i);
					idx = i;
					break;
				}
			}

			if (idx == -1) {
				car = new Car();
				car.carSerialNum = carSerialNum;
				idx = cars.size();
			}

			if (inOut.equals("IN")) {
				car.inTime = hour * 60 + minute;
			} else {
				car.useTime += hour * 60 + minute - car.inTime;
				car.inTime = -1;
			}

			cars.add(idx, car);
		}

		// 차량 번호 오름차순 
		Collections.sort(cars);
		int[] result = new int[cars.size()];

		for (int i = 0; i < cars.size(); i++) {
			Car car = cars.get(i);
			
			// 입차만 한 경우 
			if (car.inTime != -1) {
				car.useTime += 23 * 60 + 59 - car.inTime;
			}

			if (car.useTime <= defaultTime) {
				result[i] = defaultFee;
			} else {
				result[i] = defaultFee + (int) Math.ceil((car.useTime - defaultTime) / unitTime) * unitFee;
			}
		}

		return result;
	}

}
