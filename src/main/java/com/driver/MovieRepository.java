package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class MovieRepository {

    HashMap<String ,Movie> movies = new HashMap<>();
    HashMap<String,Director> directors = new HashMap<>();

    HashMap<String,List<String>> dirmovpair = new HashMap<>();

    public MovieRepository(HashMap<String, Movie> movies, HashMap<String, Director> directors, HashMap<String, List<String>> dirmovpair) {
        this.movies = movies;
        this.directors = directors;
        this.dirmovpair = dirmovpair;
    }

    public void saveMovie(Movie movie) {
        movies.put(movie.getName(),movie);
    }

    public void saveDirector(Director director) {
        directors.put(director.getName(),director);
    }

    public String movie_Director_pair(String movie, String director) {

        if(!movies.containsKey(movie) || director.contains(director) == false)
        {
            return "movies or Director is n ot present in thier respective dataBases";
        }
        else
        {
           if(dirmovpair.containsKey(director) == true)
           {
               dirmovpair.get(director).add(movie);
           }
           else {
               List<String> l = new ArrayList<>();
               l.add(movie);
               dirmovpair.put(director,l);
           }
        }
        return "Movie Director Piar Added Successfully";

    }

    public Movie getMovie(String name) {
        if(movies.containsKey(name) == false)
        {
            return null;
        }

        return movies.get(name);
    }

    public Director getDirector(String name) {
        if(directors.containsKey(name) ==false) return null;
        return directors.get(name);
    }

    public List<String> getListofmovies(String director) {
        List<String> l = new ArrayList<>();
        if(dirmovpair.containsKey(director))
        {
            l = dirmovpair.get(director);
        }
        return l;
    }

    public List<String> getallmovies() {
//        List<String > l = new ArrayList<>();
//        for(Map.Entry<String, Movie> m:movies.entrySet())
//        {
//            l.add(m.toString());
//        }
//        return l;
        return new ArrayList<>(movies.keySet());
    }

    public String removedirector(String director) {
        List<String> movielist = new ArrayList<>();
        if(dirmovpair.containsKey(director))
        {
            movielist = dirmovpair.get(director);
            for(String m : movielist)
            {
                if(movies.containsKey(m))
                {
                    movies.remove(m);
                }
            }
        }

        if(dirmovpair.containsKey(director))
        {
            dirmovpair.remove(director);
        }
        if(directors.containsKey(director))
        {
            directors.remove(director);
        }

        return "Director and its movies all are deleted fro  records";
    }

    public void removealldirectors() {
        HashSet<String> moviesSet = new HashSet<String>();

        //directorMap = new HashMap<>();

        for(String director: dirmovpair.keySet()){
            for(String movie: dirmovpair.get(director)){
                moviesSet.add(movie);
            }
        }

        for(String movie: moviesSet){
            if(movies.containsKey(movie)){
                movies.remove(movie);
            }
        }
        dirmovpair.clear();
        directors.clear();
    }
}
