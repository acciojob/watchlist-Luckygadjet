package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    MovieService movieservices;

    // Add-Movie_API
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody() Movie movie)
    {
        movieservices.addMovie(movie);
        return new ResponseEntity<>("new movie added Successfully:", HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String > addDirector(@RequestBody() Director director)
    {
        movieservices.addDirector(director);
        return new ResponseEntity<>("new Director Added Successfully:",HttpStatus.CREATED);

    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movie,@RequestParam("director") String director)
    {
        movieservices.createMovieDirectorPair(movie,director);
        return new ResponseEntity<>("new Movie - Director Pair Successfully Added: ",HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name)
    {
        Movie movie = movieservices.findMovie(name);
        return new ResponseEntity<>(movie,HttpStatus.CREATED);

    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String name)
    {
        Director director = movieservices.findDirector(name);
        return new ResponseEntity<>(director,HttpStatus.CREATED);

    }
    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String director)
    {
        List<String> movies = movieservices.findListofMoviesofDirector(director);
        return new ResponseEntity<>(movies, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies()
    {
        List<String> allMovies = movieservices.getAllMovies();
        return new ResponseEntity<>(allMovies,HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String > deleteDirectorByName(@RequestParam() String director)
    {
        movieservices.deletedirector(director);
        return new ResponseEntity<>(director+ " Director Deleted Successfully: ",HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors()
    {
        movieservices.deletealldirectorsandtheirmovies();
        return new ResponseEntity<>("Successfully Deleted All Directors and their Movies",HttpStatus.CREATED);
    }


}
