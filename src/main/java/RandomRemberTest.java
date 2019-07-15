import java.io.IOException;
import java.util.Random;

public class RandomRemberTest {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        for(int i=0; i<100; i++) {
            System.out.println(random.nextInt(100) + 1);
        }
    }
}
