package application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieList {
    private List<Movie> movies;
    private String filePath;

    public MovieList(String filePath) {
        movies = new ArrayList<>();
        this.filePath = filePath;
        loadMoviesFromCSV();
    }

    public void loadMoviesFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Başlığı atla

            while ((line = reader.readLine()) != null) {
                String[] movieData = line.split(",");
                if (movieData.length >= 6) {
                    String name = movieData[0].replaceAll("\"", "");
                    String year = movieData[1].replaceAll("\"", "");
                    String genre = movieData[2].replaceAll("\"", "");
                    String origin = movieData[3].replaceAll("\"", "");
                    String director = movieData[4].replaceAll("\"", "");
                    String star = movieData[5].replaceAll("\"", "");
                    Movie movie = new Movie(name, year, genre, origin, director, star);
                    movies.add(movie);
                }
            }
         
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Movie findMovieByName(String name) {
        for (Movie movie : movies) {
            if (movie.getName().equalsIgnoreCase(name)) {
                return movie;
            }
        }
        return null;
    }
}
