import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieManagerTest {

    @Test
    public void testAddAndFindAll() {
        MovieManager manager = new MovieManager();
        Movie m1 = new Movie("Бладшот", "боевик");
        Movie m2 = new Movie("Вперёд", "мультфильм");
        Movie m3 = new Movie("Отель «Белград»", "комедия");

        manager.add(m1);
        manager.add(m2);
        manager.add(m3);

        Movie[] all = manager.findAll();
        assertEquals(3, all.length);
        assertEquals(m1, all[0]);
        assertEquals(m2, all[1]);
        assertEquals(m3, all[2]);
    }

    @Test
    public void testFindLastDefaultLimit() {
        MovieManager manager = new MovieManager();
        for (int i = 0; i < 7; i++) {
            manager.add(new Movie("Film" + i, "genre"));
        }

        Movie[] last = manager.findLast();
        assertEquals(5, last.length);
        assertEquals("Film6", last[0].getTitle());
        assertEquals("Film2", last[4].getTitle());
    }

    @Test
    public void testFindLastCustomLimit() {
        MovieManager manager = new MovieManager(3);
        for (int i = 0; i < 5; i++) {
            manager.add(new Movie("Film" + i, "genre"));
        }

        Movie[] last = manager.findLast();
        assertEquals(3, last.length);
        assertEquals("Film4", last[0].getTitle());
        assertEquals("Film2", last[2].getTitle());
    }

    @Test
    public void testFindLastLessThanLimit() {
        MovieManager manager = new MovieManager(10);
        manager.add(new Movie("Film1", "genre"));
        manager.add(new Movie("Film2", "genre"));

        Movie[] last = manager.findLast();
        assertEquals(2, last.length);
        assertEquals("Film2", last[0].getTitle());
        assertEquals("Film1", last[1].getTitle());
    }

    @Test
    public void testEmptyManagerFindAllAndFindLast() {
        MovieManager manager = new MovieManager();
        assertEquals(0, manager.findAll().length);
        assertEquals(0, manager.findLast().length);
    }

    @Test
    public void testExactlyLimit() {
        MovieManager manager = new MovieManager(3);
        manager.add(new Movie("A", "g"));
        manager.add(new Movie("B", "g"));
        manager.add(new Movie("C", "g"));

        Movie[] last = manager.findLast();
        assertEquals(3, last.length);
        assertEquals("C", last[0].getTitle());
    }
}
