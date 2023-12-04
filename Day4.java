import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day4 {
    public static void main(String[] args) {
        var lines = readLines("day4data.txt");
        var cards = lines.stream().map(Card::new).toList();

        // Part 1
        cards.stream().map(c -> c.cardPoints).reduce(Integer::sum).ifPresent(System.out::println);

        // Part 2
        var totalPile = new ArrayList<Card>();
        cards.forEach(c -> processCard(c, cards, totalPile));
        System.out.println("Part 2: " + totalPile.size());
    }

    static void processCard(Card card, List<Card> originalCards, List<Card> pileOfCards) {
        pileOfCards.add(card);

        for (int i = card.cardNumber; i < card.myWinningNumbers.size() + card.cardNumber; i++) {
            processCard(originalCards.get(i), originalCards, pileOfCards);
        }
    }

    static class Card {

        int cardNumber;
        List<Integer> winningNumbers;
        List<Integer> myNumbers;
        List<Integer> myWinningNumbers;
        int cardPoints = 0;

        public Card(String input) {
            var parts = input.split(":");
            this.cardNumber = Integer.parseInt(parts[0].trim().split("\\s+")[1]);

            var numbersGroups = parts[1].trim().split("\\|");
            winningNumbers = Arrays.stream(numbersGroups[0].trim().split("\\s+"))
                    .map(Integer::parseInt)
                    .toList();

            myNumbers = Arrays.stream(numbersGroups[1].trim().split("\\s+"))
                    .map(Integer::parseInt)
                    .toList();

            myWinningNumbers = myNumbers.stream()
                    .filter(winningNumbers::contains)
                    .toList();

            if (!myWinningNumbers.isEmpty()) {
                cardPoints = (int) Math.pow(2, myWinningNumbers.size() - 1);
            }
        }
    }

    public static List<String> readLines(String filename) {
        List<String> lines = null;

        try {
            URI fileUri = Day4.class.getResource(filename).toURI();
            lines = Files.readAllLines(Paths.get(fileUri));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lines;
    }
}
