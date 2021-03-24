package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName){
        List<String[]> competitorsNameWithResults = readCompetitorsData(fileName);
        return orderByResults(competitorsNameWithResults);
    }

    private List<String[]> readCompetitorsData(String fileName) {
        List<String[]> readInfo = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");
                readInfo.add(info);
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return readInfo;
    }

    private List<String> orderByResults(List<String[]> competitorsList) {
        Map<String, Integer> competitorsResults = new HashMap<>();

        for (String[] competitor : competitorsList) {
            String name = competitor[0];
            int result = (Integer.parseInt(competitor[1])
                    + Integer.parseInt(competitor[2])
                    + Integer.parseInt(competitor[3])
                    + Integer.parseInt(competitor[4])
                    + Integer.parseInt(competitor[5]));
            competitorsResults.put(name, result);
        }

        return competitorsResults
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
