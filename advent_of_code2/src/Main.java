import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final String regex = "([0-9]+)\\-([0-9]+)\\s([a-z]+)\\:\\s([a-z]+)";

    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("p:\\aoc\\advent_of_code2\\src\\in.txt");
        Scanner reader;
        reader = new Scanner(input);
        String line;
        int counter = 0;
        int counter2 = 0;
        while( reader.hasNext() ) {
            line = reader.nextLine();
            if( isValidLine(line) ) {
                ++counter;
            }
            if( isValidLineSecond(line) ) {
                ++counter2;
            }
        }
        System.out.println(counter);
        System.out.println(counter2);
    }


    private static boolean isValidLine( final String str ) {
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(str);
        final String minNum;
        final String maxNum;
        final String character;
        final String sample;
        if(match.matches()) {
            minNum = match.group(1);
            maxNum = match.group(2);
            character = match.group(3);
            sample = match.group(4);
        } else {
            return false;
        }

        int countOfChar = countMatches(sample, character);

        return countOfChar <= Integer.valueOf(maxNum) && countOfChar >= Integer.valueOf(minNum);
    }

    private static boolean isValidLineSecond( final String str ) {
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(str);
        final String minNum;
        final String maxNum;
        final char character;
        final String sample;
        if(match.matches()) {
            minNum = match.group(1);
            maxNum = match.group(2);
            character = match.group(3).charAt(0);
            sample = match.group(4);
        } else {
            return false;
        }

        return  ( Character.compare(sample.charAt(Integer.valueOf(minNum)-1), character) == 0 ) ^
            ( Character.compare(sample.charAt(Integer.valueOf(maxNum)-1), character) == 0 );

    }


    private static int countMatches( final String sample, final String character ) {
        int lastIndex = 0;
        int count = 0;

        while(lastIndex != -1){
            lastIndex = sample.indexOf(character,lastIndex);
            if(lastIndex != -1){
                count ++;
                lastIndex += character.length();
            }
        }
        return count;
    }

}
