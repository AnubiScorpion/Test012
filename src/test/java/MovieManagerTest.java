import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieManagerTest {

    @Test
    public void shouldAddAndFindAll() {
        MovieManager manager = new MovieManager();
        Movie m1 = new Movie("Бладшот", "боевик");
        Movie m2 = new Movie("Вперёд", "мультфильм");
        Movie m3 = new Movie("Отель «Белград»", "комедия");

        manager.add(m1);
        manager.add(m2);
        manager.add(m3);

        assertArrayEquals(new Movie[]{m1, m2, m3}, manager.findAll());
    }

    @Test
    public void shouldReturnEmptyArraysOnEmptyManager() {
        MovieManager manager = new MovieManager();

        assertArrayEquals(new Movie[]{}, manager.findAll());
        assertArrayEquals(new Movie[]{}, manager.findLast());
    }

    @Test
    public void shouldFindLastWithDefaultLimit() {
        MovieManager manager = new MovieManager();
        Movie[] added = new Movie[7];
        for (int i = 0; i < 7; i++) {
            added[i] = new Movie("Film" + i, "genre");
            manager.add(added[i]);
        }

        // последние 5 в обратном порядке: 6, 5, 4, 3, 2
        assertArrayEquals(
                new Movie[]{added[6], added[5], added[4], added[3], added[2]},
                manager.findLast()
        );
    }

    @Test
    public void shouldFindLastWithCustomLimit() {
        MovieManager manager = new MovieManager(3);
        Movie[] added = new Movie[5];
        for (int i = 0; i < 5; i++) {
            added[i] = new Movie("Film" + i, "genre");
            manager.add(added[i]);
        }

        assertArrayEquals(
                new Movie[]{added[4], added[3], added[2]},
                manager.findLast()
        );
    }

    @Test
    public void shouldReturnAllIfFewerThanLimit() {
        MovieManager manager = new MovieManager(10);
        Movie m1 = new Movie("Дженгтльмены", "боевик");
        Movie m2 = new Movie("Человек-невидимка", "ужасы");

        manager.add(m1);
        manager.add(m2);

        assertArrayEquals(new Movie[]{m2, m1}, manager.findLast());
    }

    @Test
    public void shouldReturnAllWhenCountEqualsSize() {
        MovieManager manager = new MovieManager(3);
        Movie m1 = new Movie("A", "g");
        Movie m2 = new Movie("B", "g");
        Movie m3 = new Movie("C", "g");

        manager.add(m1);
        manager.add(m2);
        manager.add(m3);

        assertArrayEquals(new Movie[]{m3, m2, m1}, manager.findLast());
    }
}

