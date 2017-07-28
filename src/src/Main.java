package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Data;
import model.DataNormalized;
import model.DistanceObject;

public class Main {

	static Data min = new Data("min", 652, 718, 43, 87, 1.95f, 52, 55);
	static Data max = new Data("max", 652, 718, 43, 87, 1.95f, 52, 55);

	public static void main(String[] args) throws Exception {
		List<Data> dataList = new ArrayList<>();
		dataList.add(new Data("Consummate Consumers", 652, 718, 43, 87, 1.95f,
				52, 55));
		dataList.add(
				new Data("Risky Revenue", 643, 570, 34, 55, 2.11f, 45, 49));
		dataList.add(
				new Data("Business Builders", 49, 739, 48, 78, 1.97f, 52, 50));
		dataList.add(new Data("Balance Bombs", 59, 570, 32, 40, 2.13f, 41, 46));

		List<DistanceObject> distances1 = calculateDistances(dataList);
		printFirstFourData(distances1);

		Data client = readClientFromFile("clientData.txt");
		System.out.println("\nNew client:" + client.toString());
		dataList.add(client);
		List<DistanceObject> distances2 = calculateDistances(dataList);
		printFiveData(distances2, client.getSegmentName());
	}

	private static void printFirstFourData(List<DistanceObject> distances) {
		// printAllDistances(distances);

		DistanceObject mostSimilar = distances.get(0);
		for (DistanceObject d : distances) {
			if (d.getDistance() < mostSimilar.getDistance()) {
				mostSimilar = d;
			}
		}
		System.out.println("Most similar pair of four original segments: \n"
				+ mostSimilar.toString());
	}

	private static void printFiveData(List<DistanceObject> distances,
			String clientName) {
		// printAllDistances(distances);

		distances = filterOnlyClientDistances(distances, clientName);
		DistanceObject mostSimilarToClient = distances.get(0);
		for (DistanceObject d : distances) {
			if (d.getDistance() < mostSimilarToClient.getDistance()) {
				mostSimilarToClient = d;
			}
		}
		System.out.println(
				"Pair with the client-from-file and most similar original segment: \n"
						+ mostSimilarToClient);
	}

	// prints all distance objects
	// useful for debugging/verifying
	private static void printAllDistances(List<DistanceObject> distances) {
		System.out.println("Distances between all objects:");
		distances.forEach(d -> System.out.println(d.toString()));
	}

	// take list of distanceObjects and a segmentName, return list of
	// distanceObjects that contain this name
	private static List<DistanceObject> filterOnlyClientDistances(
			List<DistanceObject> distances, String name) {
		List<DistanceObject> filtered = new ArrayList<>();
		for (DistanceObject d : distances) {
			if (containsClient(d, name)) {
				filtered.add(d);
			}
		}
		return filtered;
	}

	private static Boolean containsClient(DistanceObject dist, String name) {
		return name.equals(dist.getDataOne()) || name.equals(dist.getDataTwo());
	}

	// take list of Datas, return list of DistanceObjects between all objects in
	// list
	private static List<DistanceObject> calculateDistances(
			List<Data> dataList) {
		recalculateMinMax(dataList);
		List<DataNormalized> normData = dataList.stream()
				.map(data -> normalizeData(data, min, max))
				.collect(Collectors.toList());

		List<DistanceObject> distances = new ArrayList<>();
		for (int i = 0; i < dataList.size(); i++) {
			for (int j = i + 1; j < dataList.size(); j++) {
				distances
						.add(performDistance(normData.get(i), normData.get(j)));
			}
		}
		return distances;

	}

	// recalculate global min and max items
	// min -> all fields are minimum values of field over all items in dataList,
	// segmentName is "min"
	// max -> all fields are maximum values of field over all items in dataList,
	// segmentName is "max"
	private static void recalculateMinMax(List<Data> dataList) {
		min = new Data("min", dataList.get(0).getAverageRevenue(),
				dataList.get(0).getRiskScore(), dataList.get(0).getAge(),
				dataList.get(0).getResidence(), dataList.get(0).getChildren(),
				dataList.get(0).getIncome(), dataList.get(0).getPercentMale());
		max = new Data("max", dataList.get(0).getAverageRevenue(),
				dataList.get(0).getRiskScore(), dataList.get(0).getAge(),
				dataList.get(0).getResidence(), dataList.get(0).getChildren(),
				dataList.get(0).getIncome(), dataList.get(0).getPercentMale());

		dataList.forEach(item -> {
			min.setAge(Math.min(min.getAge(), item.getAge()));
			min.setAverageRevenue(Math.min(min.getAverageRevenue(),
					item.getAverageRevenue()));
			min.setChildren(Math.min(min.getChildren(), item.getChildren()));
			min.setIncome(Math.min(min.getIncome(), item.getIncome()));
			min.setPercentMale(
					Math.min(min.getPercentMale(), item.getPercentMale()));
			min.setResidence(Math.min(min.getResidence(), item.getResidence()));
			min.setRiskScore(Math.min(min.getRiskScore(), item.getRiskScore()));

			max.setAge(Math.max(max.getAge(), item.getAge()));
			max.setAverageRevenue(Math.max(max.getAverageRevenue(),
					item.getAverageRevenue()));
			max.setChildren(Math.max(max.getChildren(), item.getChildren()));
			max.setIncome(Math.max(max.getIncome(), item.getIncome()));
			max.setPercentMale(
					Math.max(max.getPercentMale(), item.getPercentMale()));
			max.setResidence(
					Math.max(max.getResidence(), item.getPercentMale()));
			max.setRiskScore(Math.max(max.getRiskScore(), item.getRiskScore()));
		});
	}

	// take a Data, return normalized Data
	private static DataNormalized normalizeData(Data data, Data min, Data max) {
		DataNormalized normal = new DataNormalized();

		normal.setSegmentName(data.getSegmentName());
		normal.setAge(minMaxNormalize(Float.valueOf(data.getAge().toString()),
				Float.valueOf(min.getAge().toString()),
				Float.valueOf(max.getAge().toString())));
		normal.setAverageRevenue(minMaxNormalize(
				Float.valueOf(data.getAverageRevenue().toString()),
				Float.valueOf(min.getAverageRevenue().toString()),
				Float.valueOf(max.getAverageRevenue().toString())));
		normal.setChildren(minMaxNormalize(data.getChildren(),
				min.getChildren(), max.getChildren()));
		normal.setIncome(
				minMaxNormalize(Float.valueOf(data.getIncome().toString()),
						Float.valueOf(min.getIncome().toString()),
						Float.valueOf(max.getIncome().toString())));
		normal.setPercentMale(
				minMaxNormalize(Float.valueOf(data.getPercentMale().toString()),
						Float.valueOf(min.getPercentMale().toString()),
						Float.valueOf(max.getPercentMale().toString())));
		normal.setResidence(
				minMaxNormalize(Float.valueOf(data.getResidence().toString()),
						Float.valueOf(min.getResidence().toString()),
						Float.valueOf(max.getResidence().toString())));
		normal.setRiskScore(
				minMaxNormalize(Float.valueOf(data.getRiskScore().toString()),
						Float.valueOf(min.getRiskScore().toString()),
						Float.valueOf(max.getRiskScore().toString())));

		return normal;
	}

	// minMaxNormalize a value
	private static Float minMaxNormalize(Float value, Float min, Float max) {
		return (value - min) / (max - min);
	}

	// create a Distanceobject (name of Object 1, name of Object 2, distance
	// between them)
	private static DistanceObject performDistance(DataNormalized one,
			DataNormalized two) {
		return new DistanceObject(one.getSegmentName(), two.getSegmentName(),
				calculateDistance(one, two));
	}

	// calculate the numeric distance between two normalized objects
	private static double calculateDistance(DataNormalized one,
			DataNormalized two) {
		double sum = Math.pow(one.getAverageRevenue() - two.getAverageRevenue(),
				2);
		sum += Math.pow(one.getRiskScore() - two.getRiskScore(), 2);
		sum += Math.pow(one.getAge() - two.getAge(), 2);
		sum += Math.pow(one.getResidence() - two.getResidence(), 2);
		sum += Math.pow(one.getChildren() - two.getChildren(), 2);
		sum += Math.pow(one.getIncome() - two.getIncome(), 2);
		sum += Math.pow(one.getPercentMale() - two.getPercentMale(), 2);

		return Math.sqrt(sum);
	}

	// read client from file into Data object
	private static Data readClientFromFile(String filename) throws Exception {
		Data client = new Data();
		List<String> fileData = readFromFile(filename);

		client.setSegmentName(parseValue(fileData.get(1)));
		client.setAverageRevenue(Integer.valueOf(parseValue(fileData.get(2))));
		client.setRiskScore(Integer.valueOf(parseValue(fileData.get(3))));
		client.setAge(Integer.valueOf(parseValue(fileData.get(4))));
		client.setResidence(Integer.valueOf(parseValue(fileData.get(5))));
		client.setChildren(Float.valueOf(parseValue(fileData.get(6))));
		client.setIncome(Integer.valueOf(parseValue(fileData.get(7))));
		client.setPercentMale(Integer.valueOf(parseValue(fileData.get(8))));

		return client;
	}

	// read actual value from a given row-of-file
	private static String parseValue(String row) {
		return row.split(":")[1].trim();
	}

	// get contents of given file
	private static List<String> readFromFile(String fileName) throws Exception {
		File input = new File(fileName);
		BufferedReader reader = new BufferedReader(new FileReader(input));

		List<String> rows = new ArrayList<String>();

		String row = reader.readLine();
		while (row != null) {
			rows.add(row);
			row = reader.readLine();
		}
		reader.close();

		return rows;
	}
}
