import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {




    public static void main(String[] args) throws FileNotFoundException {
        List<String> passportsStr = initPassports();
        int count = countValidPassports(passportsStr);
        System.out.println(count);

        List<Passport> passports = getPassportsFromStrings(passportsStr);
        int countValid = countTrueValidPassports(passports);
        System.out.println(countValid);

    }

    private static int countTrueValidPassports(List<Passport> passports) {
        int count = 0;
        for ( Passport passport : passports) {
            if( passport.isValidPassword() ) {
                ++count;
            }
        }
        return count;
    }

    private static List<Passport> getPassportsFromStrings(final List<String> passportStrs) {
        List<Passport> passports = new ArrayList<>();
        for ( String passportStr: passportStrs ) {
            passports.add(new Passport(passportStr));
        }
        return passports;
    }

    private static List<String> initPassports() throws FileNotFoundException {
        File input = new File("p:\\aoc\\advent_of_code4\\src\\input.txt");
        List<String> passports = new ArrayList<>();
        Scanner reader;
        reader = new Scanner(input);
        StringBuilder passport = new StringBuilder();
        while( reader.hasNext() ) {
            String nextLine = reader.nextLine();
            passport.append(nextLine).append(" ");
            if( nextLine.isEmpty() ) {
                passports.add(passport.toString());
                passport = new StringBuilder();
            }
        }
        passports.add(passport.toString());
        return passports;
    }

    private static int countValidPassports(final List<String> passportsStr ) {
        int count = 0;
        for ( String passport: passportsStr ) {
            if(isValidPassport(passport)){
                ++count;
            }
        }
        return count;
    }

    private static boolean isValidPassport(final String passport) {
        for ( String requiredField: Passport.REQUIREDFIELDS ) {
            if( !passport.contains(requiredField + ":") ) {
                return false;
            }
        }
        return true;
    }

}
