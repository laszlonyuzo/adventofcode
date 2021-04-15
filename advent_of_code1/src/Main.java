import java.io.File;
    import java.io.FileNotFoundException;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;

public class Main {

    private static List<Integer> inputNums = new ArrayList<>();

    public static void main(String[] args) {
        one();
        two();

    }

    private static void two() {
        Scanner reader = getScanner();
        inputNums = new ArrayList<>();
        while( reader.hasNext() ) {
            inputNums.add(reader.nextInt());
        }
        System.out.println(checkNumbers());
    }

    private static void one() {
        Scanner reader = getScanner();
        Integer num, result;
        while( reader.hasNext() ) {
            num = reader.nextInt();
            result = checkNumber(num);
            if ( result != null ) {
                System.out.println(result);
                break;
            } else {
                inputNums.add(num);
            }
        }

    }

    private static Scanner getScanner() {
        File input = new File("p:\\aoc\\Advent_of_code1\\src\\input.txt");
        Scanner reader = null;
        inputNums = new ArrayList<>();
        try {
            reader = new Scanner(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return reader;
    }

    private static Integer checkNumber( final Integer newNumber ) {
        int i = 0;
        while( i < inputNums.size() ) {
            if( inputNums.get(i) + newNumber == 2020 ) {
                return inputNums.get(i) * newNumber;
            }
            ++i;
        }
        return null;
    }


    private static Integer checkNumbers() {
        for( int i = 0 ; i < inputNums.size() ; ++i ) {
            for( int j = 0 ; j < inputNums.size() ; ++j ) {
                for( int k = 0 ; k < inputNums.size() ; ++k ) {
                    if( inputNums.get(i) + inputNums.get(j) + inputNums.get(k) == 2020 ) {
                        return inputNums.get(i) * inputNums.get(j) * inputNums.get(k);
                    }
                }
            }
        }
        return null;
    }
}
