import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Passport {

    private              HashMap<String, String> datas = new HashMap<>();
    private static final List<String>            eyeColors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
    public static final String[] REQUIREDFIELDS = new String[] {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

    public Passport(final String rawData) {
        fillData(rawData);
    }

    public void fillData( final String passport ) {
        for ( String dataPart : passport.split(" ") ) {
            if ( !dataPart.isEmpty() && dataPart.contains(":") ) {
                String[] dataKeyValue = dataPart.split(":");
                datas.put(dataKeyValue[0], dataKeyValue[1]);
            }
        }
    }


    private boolean validateNumber(final String year, final int min, final int max){
        return Integer.valueOf(year) >= min && Integer.valueOf(year) <= max;
    }

    private boolean validateRegexp( final String pattern, final String value ) {
        return value.matches(pattern);
    }

    private boolean validateBYR() {
        if( getData("byr") == null ) {
            return false;
        }
        return validateNumber(getData("byr"), 1920, 2002);
    }

    private boolean validateIYR() {
        if( getData("iyr") == null ) {
            return false;
        }
        return validateNumber(getData("iyr"), 2010, 2020);
    }

    private boolean validateEYR() {
        if( getData("eyr") == null ) {
            return false;
        }
        return validateNumber(getData("eyr"), 2020, 2030);
    }

    private boolean validateHGT() {
        String height = getData("hgt");
        if( height == null ) {
            return false;
        }
        String measure = height.substring(height.length() - 2);
        String heightNum = height.substring(0, height.length() - 2);
        if( measure.equals("cm") ) {
            return validateNumber(heightNum, 150, 193);
        } else if( measure.equals("in") ) {
            return validateNumber(heightNum, 59, 76);
        } else {
            return false;
        }
    }

    private boolean validateHCL() {
        if( getData("hcl") == null ) {
            return false;
        }
        return validateRegexp("#[a-f0-9]{6}", getData("hcl"));
    }

    private boolean validateECL() {
        if( getData("ecl") == null ) {
            return false;
        }
        return eyeColors.contains(getData("ecl"));
    }

    private boolean validatePID() {
        if( getData("pid") == null ) {
            return false;
        }
        return validateRegexp("[0-9]{9}", getData("pid"));
    }


    public boolean isValidPassword() {
        boolean isValid = validateBYR();
        isValid &= validateIYR();
        isValid &= validateEYR();
        isValid &= validateHGT();
        isValid &= validateHCL();
        isValid &= validateECL();
        isValid &= validatePID();

        return isValid;
    }

    private String getData( final String key ) {
        if(datas.containsKey(key)) {
            return datas.get(key);
        }
        return null;
    }

}
