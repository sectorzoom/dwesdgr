package org.example;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        //Cargamos el CSV
        YoutuberService youtubers = new YoutuberService();
        youtubers.cargarYoutubers("C:/Users/david/Documents/2 DAW/Desarrollo Web en Entorno Servidor/Ejercicios/dwesdgr/tema2.practica1/src/main/resources/youtubers.csv");

        // Youtuber con más seguidores
        showYoutuberWithMostFollowers(youtubers);
        // Media de vídeos
        showAverageVideos(youtubers);
        // YouTubers que empezaron en 2013
        showYoutubersStarted2013(youtubers);
        // Top 3 YouTubers por ingresos estimados
        showTop3WithMoreEarns(youtubers);
        // YouTubers agrupados por año
        showYoutubersGroupedByYear(youtubers);

    }

    public static void showYoutuberWithMostFollowers(YoutuberService youtubers) {
        Optional<Youtuber> youtuberConMasSeguidores = youtubers.getYoutuberWithMostFollowers();
        youtuberConMasSeguidores.ifPresent(y -> System.out.println("\nYoutuber con más seguidores: " + y.name()));
    }

    public static void showAverageVideos(YoutuberService youtubers) {
        double mediaVideos = youtubers.getAverageVideos();
        System.out.println("\nMedia de vídeos: " + mediaVideos);
    }

    public static void showYoutubersStarted2013(YoutuberService youtubers) {
        List<Youtuber> youtubers2013 = youtubers.getYoutuberStarted2013();
        System.out.println("\nYouTubers que empezaron en 2013:");
        youtubers2013.forEach(y -> System.out.println(y.name()));
    }

    public static void showTop3WithMoreEarns(YoutuberService youtubers) {
        List<Youtuber> top3Youtubers = youtubers.getTop3WithMoreEarns();
        System.out.println("\nTop 3 YouTubers con mayores ingresos estimados:");
        top3Youtubers.forEach(y -> System.out.println(y.name() + " - Ingresos estimados: " + y.estimatedIncome()));
    }

    public static void showYoutubersGroupedByYear(YoutuberService youtubers) {
        // Obtener los YouTubers agrupados por año
        Map<String, List<Youtuber>> youtubersByYear = youtubers.getYoutubersGroupedByYear();
        System.out.println("\nYoutubers agrupados por año:");
        // Imprimir los YouTubers agrupados por año
        youtubersByYear.forEach((year, youtuberss) -> {
            System.out.println("Año: " + year);
            youtuberss.forEach(y -> System.out.println("  - " + y.name()));
        });

        /*
        for (String year : youtubersByYear.keySet()) {
            System.out.println("Año: " + year);
            List<Youtuber> youtuberss = youtubersByYear.get(year);
            for (Youtuber youtuber : youtuberss) {
                System.out.println("  - " + youtuber.name());
            }
        }
         */
    }
}