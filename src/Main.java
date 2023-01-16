import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ParallelDataElement {
    Integer value;
    Integer result;

    public ParallelDataElement(Integer value) {
        this.value = value;
    }
}

class ParallelData {
    ArrayList<ParallelDataElement> data;

    ParallelData() {
        data = new ArrayList<>();
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> dataLength = Arrays.asList(10000, 100000, 1000000, 10000000, 100000000);
        for (Integer currentLength : dataLength) {
            ParallelData parallelData = new ParallelData();
            for (int i = 1; i <= currentLength; i++) {
                ParallelDataElement parallelDataElement = new ParallelDataElement(i);
                parallelData.data.add(parallelDataElement);
            }
            System.out.println("Размер данных: " + String.valueOf(currentLength));
            long time = System.currentTimeMillis();
            parallelData.data.stream().parallel().forEach(n -> {
                n.result = n.value + 1;
                n.result = n.result - 1;
            });
            System.out.println("Прошло мсек в параллельной обработке " + (System.currentTimeMillis() - time));

            parallelData = new ParallelData();
            for (int i = 1; i <= currentLength; i++) {
                ParallelDataElement parallelDataElement = new ParallelDataElement(i);
                parallelData.data.add(parallelDataElement);
            }
            time = System.currentTimeMillis();
            parallelData.data.stream().forEach(n -> {
                n.result = n.value + 1;
                n.result = n.result - 1;
            });
            System.out.println("Прошло мсек в последовательной обработке " + (System.currentTimeMillis() - time));
        }
    }
}
