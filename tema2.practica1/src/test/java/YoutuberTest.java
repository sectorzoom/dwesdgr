import org.example.Youtuber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class YoutuberTest {

    @Test
    void testEstimatedIncomeWithValidValues() {
        Youtuber youtuber = new Youtuber("Pepe", "2020-01-01", 100, 5000);
        double expectedIncome = ((100.0 * 5000) / 2) * 0.002;
        assertEquals(expectedIncome, youtuber.estimatedIncome());
    }

    @Test
    void testEstimatedIncomeWithZeroVideos() {
        Youtuber youtuber = new Youtuber("Pepe", "2020-01-01", 0, 5000);
        double expectedIncome = 0;
        assertEquals(expectedIncome, youtuber.estimatedIncome());
    }

    @Test
    void testEstimatedIncomeWithZeroFollowers() {
        Youtuber youtuber = new Youtuber("Pepe", "2020-01-01", 100, 0);
        double expectedIncome = 0;
        assertEquals(expectedIncome, youtuber.estimatedIncome());
    }

    @Test
    void testEstimatedIncomeWithNegativeValues() {
        Youtuber youtuber = new Youtuber("Pepe", "2020-01-01", -100, 5000);
        double expectedIncome = ((-100.0 * 5000) / 2) * 0.002;
        assertEquals(expectedIncome, youtuber.estimatedIncome());
    }
}
