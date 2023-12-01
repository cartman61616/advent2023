import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1Part1 {
    public static void main(String[] args) {
        String filePath = "tester.txt"; // Replace with your file path
        List<Integer> listOfNums = new ArrayList<>();
        Integer total = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String firstInteger = extractFirstInteger(line);
                String lastInteger = extractLastInteger(line);
                
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
        }
        System.out.println(listOfNums.size());
        System.out.println("Total: " + total);
    }
    
    private static String extractFirstInteger(String line) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(line);
        
        if (matcher.find()) {
            return matcher.group();
        }
        
        return null;
    }
    
    private static String extractLastInteger(String line) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(line);
        String lastInteger = null;
        
        while (matcher.find()) {
            lastInteger = matcher.group();
        }
        
        return lastInteger;
    }
    
    private static int formTwoDigitNumber(int firstNumber, int lastNumber) {
        int twoDigitNumber = (firstNumber % 10) * 10 + (lastNumber % 10);
        return twoDigitNumber;
    }
}
