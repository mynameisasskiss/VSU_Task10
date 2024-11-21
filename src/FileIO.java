import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileIO {

    public static int[][] readIntMatrixFromFile(String fileName) throws IOException {
        List<int[]> rows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            while (line != null) {
                rows.add(Arrays.stream(line.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray());
                line = reader.readLine();
            }
        }

        int[][] a = rows.isEmpty() ? new int[0][0] : new int[rows.size()][];
        rows.toArray(a);
        return a;
    }

    public static void writeStringToFile(String fileName, String str) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(str);
        }
    }
}
