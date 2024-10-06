package org.example;

public record Youtuber(String name, String date, int numVideos, int numFollowers) {
    public double estimatedIncome(){
        return ((double) (numVideos * numFollowers) /2)*0.002;
    }
}
