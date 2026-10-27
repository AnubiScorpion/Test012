public class MovieManager {
    private Movie[] movies;
    private int size;
    private final int limit;

    public MovieManager() {
        this(5);
    }

    public MovieManager(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be positive");
        }
        this.limit = limit;
        this.movies = new Movie[10];
        this.size = 0;
    }

    public void add(Movie movie) {
        if (size == movies.length) {
            Movie[] newMovies = new Movie[movies.length * 2];
            System.arraycopy(movies, 0, newMovies, 0, size);
            movies = newMovies;
        }
        movies[size] = movie;
        size++;
    }

    public Movie[] findAll() {
        Movie[] result = new Movie[size];
        System.arraycopy(movies, 0, result, 0, size);
        return result;
    }

    public Movie[] findLast() {
        return findLast(limit);
    }

    public Movie[] findLast(int count) {
        int resultLength;
        if (size < count) {
            resultLength = size;
        } else {
            resultLength = count;
        }
        Movie[] result = new Movie[resultLength];
        for (int i = 0; i < resultLength; i++) {
            result[i] = movies[size - 1 - i];
        }
        return result;
    }
}