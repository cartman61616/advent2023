import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2Part1 {
        public static void main(String[] args) {
            System.out.println("Sum of game ids: " + parseData());
    }

    private static boolean checkGameData(
        String gameData, int redCubes, int blueCubes, int greenCubes) {
            String[] games = gameData.split(";");

            for(String game : games) {
                int red = 0, blue = 0, green = 0;
                String[] cubes = game.split(",");
                for(String draws : cubes) {
                    String draw = draws.trim();
                    if (!draw.isEmpty()) {
                        String[] parts = draw.split(" ");
                        int cube = Integer.parseInt(parts[0]);
                        String color = parts[1];
                        switch (color) {
                            case "red":
                                red += cube;
                                break;
                            case "blue":
                                blue += cube;
                                break;
                            case "green":
                                green += cube;
                                break;
                        }

                    }
                }

                if (red > redCubes || blue > blueCubes || green > greenCubes) {
                    return false;
                }
            }
            return true;
    }

    private static int parseData() {
        int games = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("day2data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                int gameId = Integer.parseInt(parts[0].split(" ")[1]);
                String gameData = parts[1];
                if (checkGameData(gameData, 12, 14, 13)) {
                    games += gameId;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return games;
    }
}
