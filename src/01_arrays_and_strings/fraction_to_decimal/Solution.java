package fraction_to_decimal;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
    }

    public String fractionToDecimal(int numerator, int denominator) {
        String answer = "";
        boolean seenDecimal = false;
        int originalNumerator = numerator;
        while(numerator!=0){
          if(numerator<denominator){
              if(!seenDecimal){
                  answer += ".";
                  seenDecimal = true;
              }
              numerator *= 10;
              answer += "0";
              continue;
          }
          if(numerator == originalNumerator){
              String [] split = answer.split(".");
              answer = split[0] + "(" + split[1] + ")";
              break;
          }
          numerator = numerator - numerator/denominator;
          answer += numerator;
        }
        return answer;
    }
}
