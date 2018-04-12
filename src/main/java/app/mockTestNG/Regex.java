package app.mockTestNG;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Regex {

    public static void main(String[] args) {
        System.out.println(new Regex().returnAllNumbersWithAtLeastThreeDigits("cdefg 345 12bbb33 678tt"));
    }



    public String returnAllNumbersWithAtLeastThreeDigits(String input) {

        StringBuilder sb = new StringBuilder();
        int digitCounter = 0;
        List<Character> listOfChars = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {

          if(Character.isDigit(input.charAt(i))){
              digitCounter++;
              listOfChars.add(input.charAt(i));
          } else {
              if (digitCounter >= 3 ) {
                  listOfChars.forEach(sb::append);
                  sb.append(", ");
                  listOfChars.clear();
              }
              digitCounter = 0;
          }

            System.out.println(digitCounter);
        }
        return sb.toString().substring(0,sb.length()-2);
    }


}
