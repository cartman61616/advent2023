import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class Day1Part2 {
    public static void main(String[] args) {
        String filePath = "day1data.txt"; // Replace with your file path
        List<Integer> listOfNums = new ArrayList<>();
        Integer total = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String firstInteger = findFirstNumber(line);
                String lastInteger = findLastNumber(line);
                
                if (firstInteger != null && lastInteger != null) {
                    int firstNumber = Integer.parseInt(firstInteger);
                    int lastNumber = Integer.parseInt(lastInteger);
                    int twoDigitNumber = formTwoDigitNumber(firstNumber, lastNumber);
                    listOfNums.add(twoDigitNumber);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int num : listOfNums) {
            total += num;
            System.err.println(num);
        }
        System.out.println("Total: " + total);
    }

    private static String findLastNumber(String input) {
        Pattern pattern = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine|\\d)");
        Matcher matcher = pattern.matcher(input);
        String lastNumber = null;
        while (matcher.find()) {
            String match = matcher.group();
            lastNumber = String.valueOf(letterToDigit(match));
              if(Integer.parseInt(lastNumber) == -1) {
                lastNumber = match;
            }  

        }
        return lastNumber;
    }

    private static String findFirstNumber(String input) {
        Pattern pattern = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine|\\d)");
        Matcher matcher = pattern.matcher(input);
        String number = null;
        while (matcher.find()) {
            String match = matcher.group();
            number = letterToDigit(match).toString(); 
            if(Integer.parseInt(number) == -1) {
                number = match;
            }  
            return number;
        }
        return null;
    }

    private static Integer letterToDigit(String letters) {
        int num = -1;
        if (letters.contains("one")) {
            num = 1;
        } else if (letters.contains("two")) {
            num = 2;
        } else if (letters.contains("three")) {
            num = 3;
        } else if (letters.contains("four")) {
            num = 4;
        } else if (letters.contains("five")) {
            num = 5;
        } else if (letters.contains("six")) {
            num = 6;
        } else if (letters.contains("seven")) {
            num = 7;
        } else if (letters.contains("eight")) {
            num = 8;
        } else if (letters.contains("nine")) {
            num = 9;
        }
        return num;
    }
    private static int formTwoDigitNumber(int firstNumber, int lastNumber) {
        int twoDigitNumber = (firstNumber % 10) * 10 + (lastNumber % 10);
        return twoDigitNumber;
    }
}