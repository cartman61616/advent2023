import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day3Part1 {

    public static void main(String[] args) throws IOException {
        String filePath = "day3data.txt";
        Path path = Paths.get(filePath);
        List<String> partLines = Files.readAllLines(path);
        int partNumber = partNumber(partLines);
        System.out.println("Part number: " + partNumber);
    }
    

    public static boolean isSymbol(Character symbol) {
        return !(Character.isDigit(symbol) || symbol == '.');
    }

    public static boolean indexCheck(int i, int j, int rows) {
        return 0 <= i && i < rows && 0 <= j && j < rows;
      }    

    private static int partNumber(List<String> partLines) {
        int rows = partLines.size();

        boolean[][] visited = new boolean[rows][rows];

        int partNumber = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (Character.isDigit(partLines.get(i).charAt(j))) {
                    int left = j;
                    int value = 0;
                    while (j < rows && Character.isDigit(partLines.get(i).charAt(j))) {
                        value = value * 10 + (partLines.get(i).charAt(j++) - '0');
                    }

                    int right = j - 1;
                    boolean isPart = false;

                    for (int k = left -1; k < right + 2; k++) {
                        for (int l = i - 1; l < i + 2; l++) {
                            boolean isIndex = indexCheck(l, k, rows) && isSymbol(partLines.get(l).charAt(k));

                            isPart = isPart | isIndex;

                            if (isIndex) {
                                visited[l][k] = true;
                            }
                        }
                    }

                    if (isPart) {
                        partNumber += value;
                    }
                }
            }
        }

        return partNumber;
    }
}
