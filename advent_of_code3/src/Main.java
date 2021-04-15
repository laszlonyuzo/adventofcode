import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<String> map;

    public static void main(String[] args) throws FileNotFoundException {
        map = new ArrayList<>();
        initMap();

        List<Pair> steps = new LinkedList<>();
        steps.add(new Pair(1,1));
        steps.add(new Pair(1,3));
        steps.add(new Pair(1,5));
        steps.add(new Pair(1,7));
        steps.add(new Pair(2,1));

        double countTrees = 1d;
        for ( Pair step: steps ) {
            countTrees *= countTrees(step.getLineStep(), step.getColumnStep());
        }

        System.out.printf("countTrees: %f\n", countTrees);
    }

    private static int countTrees( final int lineSteps, final int columnSteps ) {
        int realColNum,countTrees = 0;
        int colNum = 0;
        for( int i = 0 ; i < map.size() ; i += lineSteps ) {
            realColNum = getRealColNum( map.get(i), colNum );
            if ( map.get(i).charAt(realColNum) == '#' ) {
                ++countTrees;
            }
            colNum += columnSteps;
        }
        return countTrees;
    }


    private static void initMap() throws FileNotFoundException {
        File input = new File("p:\\aoc\\advent_of_code3\\src\\input.txt");
        Scanner reader;
        reader = new Scanner(input);
        while( reader.hasNext() ) {
            map.add(reader.nextLine());
        }
    }

    private static int getRealColNum( final String line, final int colNum ) {
        int currentColNum = colNum;
        while( line.length() <= currentColNum ) {
            currentColNum -= line.length();
        }
        return currentColNum;
    }
}
