package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public void addMovie(Movie movie) {
        movieRepository.saveMovie(movie);

    }

    public void addDirector(Director director) {
        movieRepository.saveDirector(director);
    }

    public String createMovieDirectorPair(String movie, String director) {
       return movieRepository.movie_Director_pair(movie,director);
    }

    public Movie findMovie(String name) {
        return movieRepository.getMovie(name);
    }

    public Director findDirector(String name) {
        return movieRepository.getDirector(name);
    }

    public List<String> findListofMoviesofDirector(String director) {
        return movieRepository.getListofmovies(director);
    }


    public List<String> getAllMovies() {
        return movieRepository.getallmovies();
    }

    public String deletedirector(String director) {
         return movieRepository.removedirector(director);
    }

    public void deletealldirectorsandtheirmovies() {
        movieRepository.removealldirectors();
    }
}
