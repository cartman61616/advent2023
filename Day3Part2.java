import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day3Part2 {
    public static void main(String[] args) throws IOException {
        String filePath = "day3data.txt";
        Path path = Paths.get(filePath);
        List<String> partLines = Files.readAllLines(path);
        int gearNumber = gearNumber(partLines);
        System.out.println("Gear number: " + gearNumber);
    }

    public static boolean isSymbol(Character symbol) {
        return !(Character.isDigit(symbol) || symbol == '.');
    }

    public static boolean indexCheck(int i, int j, int rows) {
        return 0 <= i && i < rows && 0 <= j && j < rows;
    }

    public static int gearNumber(List<String> lines) {
        int rows = lines.size();

        List<List<List<Integer>>> numsOfSymbol = new ArrayList<>(rows);

        for (int i = 0; i < rows; i++) {
            numsOfSymbol.add(new ArrayList<>(rows));
            for (int j = 0; j < rows; j++) {
                numsOfSymbol.get(i).add(new ArrayList<>());
            }
        }

        int value = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < rows; col++) {
                if (Character.isDigit(lines.get(row).charAt(col))) {
                    int left = col;

                    int digit = 0;
                    while (col < rows && Character.isDigit(lines.get(row).charAt(col))) {
                        digit = digit * 10 + (lines.get(row).charAt(col++) - '0');
                    }

                    int right = col - 1;

                    for (int i = left - 1; i < right + 2; i++) {
                        for (int j = row - 1; j < row + 2; j++) {
                            if (indexCheck(i, j, rows) && isSymbol(lines.get(j).charAt(i))) {
                                numsOfSymbol.get(j).get(i).add(digit);
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (lines.get(i).charAt(j) == '*' &&
                        numsOfSymbol.get(i).get(j).size() == 2) {
                    value += numsOfSymbol.get(i).get(j).get(0) * numsOfSymbol.get(i).get(j).get(1);
                }
            }
        }
        return value;
    }
}