import java.io.BufferedReader;
import java.io.FileReader;

public class Day2Part2 {
    public static void main(String[] args) {
        String fileData = "day2data.txt";
        int sumOfGameIds = readGameData(fileData);
        System.out.println("Sum of the power of game ids: " + sumOfGameIds);
    }

    public static int readGameData(String fileData) {
        int sum = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileData))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String gameData = parts[1].trim();
                int[] maxCubes = findPowerOfGameIds(gameData);
                sum += maxCubes[0] * maxCubes[1] * maxCubes[2];
                
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }

    public static int[] findPowerOfGameIds(String data) {
        String[] draws = data.split(";");
        int[] maxCubes = new int[]{0, 0, 0};

        for (String draw : draws) {
            int[] cubes = new int[]{0, 0, 0};
            String[] colors = draw.split(",");
            for (String color : colors) {
                color = color.trim();
                if (!color.isEmpty()) {
                    String[] game = color.split(" ");
                    int total = Integer.parseInt(game[0]);
                    String cubeColor = game[1];
                    switch (cubeColor) {
                        case "red":
                            cubes[0] += total;
                            break;
                        case "blue":
                            cubes[1] += total;
                            break;
                        case "green":
                            cubes[2] += total;
                            break;
                    }
                }
            }

            for (int i = 0; i < cubes.length; i++) {
                maxCubes[i] = Math.max(maxCubes[i], cubes[i]);
            }
        }

        return maxCubes;
    }
}
