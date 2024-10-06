package org.example;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class YoutuberService {

    private final List<Youtuber> youtubers = new ArrayList<>();

    public void cargarYoutubers(String archivoCsv) {
        try (CSVReader reader = new CSVReader(new FileReader(archivoCsv))) {
            String[] linea;
            reader.readNext(); // remove header

            while ((linea = reader.readNext()) != null) {
                String name = linea[0];
                String date = linea[1];
                int numVideos = Integer.parseInt(linea[2]);
                int numFollowers = Integer.parseInt(linea[3]);

                Youtuber youtuber = new Youtuber(name, date, numVideos, numFollowers);
                youtubers.add(youtuber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addYoutuber(Youtuber youtuber) {
        if (youtuber != null) {
            youtubers.add(youtuber);
        }
    }

    public Optional<Youtuber> getYoutuberWithMostFollowers(){
        return youtubers.stream()
                .max(Comparator.comparingInt(Youtuber::numFollowers));
    }

    public double getAverageVideos() {
        return youtubers.stream()
                .mapToInt(Youtuber::numVideos)
                .average()
                .orElse(0);
    }

    public List<Youtuber> getYoutuberStarted2013(){
        return youtubers.stream()
                .filter(y -> y.date().contains("2013"))
                .collect(Collectors.toList());
    }

    public List<Youtuber> getTop3WithMoreEarns(){
        return youtubers.stream()
                .sorted((y1, y2) -> Double.compare(y2.estimatedIncome(), y1.estimatedIncome()))
                .limit(3)
                .collect(Collectors.toList());
    }

    public Map<String, List<Youtuber>> getYoutubersGroupedByYear(){
        return youtubers.stream()
                .collect(Collectors.groupingBy(y -> y.date().substring(0, 4)));
    }
}