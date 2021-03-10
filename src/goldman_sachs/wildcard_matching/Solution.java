package goldman_sachs.wildcard_matching;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(matches("aa", "a"));
        System.out.println(matches("aa", "*"));
        System.out.println(matches("cb", "?a"));
        System.out.println(matches("cb", "?b"));
        System.out.println(matches("adceb", "*a*b"));

    }

    public static boolean matches(String S, String P) {
        if(S.equals(P)){
            return true;
        }
        if(S.isEmpty() || P.isEmpty())
            return false;
        if(S.charAt(0) == P.charAt(0) ||  P.charAt(0) == '?') {
            return matches(S.substring(1), P.substring(1));
        }
        if(P.charAt(0)=='*'){
            return matches(S.substring(1), P) || matches(S, P.substring(1));
        }
        return S.charAt(0) == P.charAt(0);
    }

}
