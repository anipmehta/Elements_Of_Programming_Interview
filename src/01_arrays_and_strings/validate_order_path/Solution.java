package doordash.validate_order_path;

import java.util.*;

public class Solution {
    public static void main(String [] args){
        char ch = 'a'+ 1;
        System.out.println(ch);
        String [] orders = new String[]{"P1", "P2", "D1", "D2"};
        String [] orders2 = new String[]{"P1", "D1", "P2", "D2"};
        String [] orders3 = new String[]{"P1", "D2"};
        Solution solution = new Solution();
        System.out.println(solution.validateDeliveryPath(Arrays.asList(orders)));
        System.out.println(solution.validateDeliveryPath(Arrays.asList(orders2)));
        System.out.println(solution.validateDeliveryPath(Arrays.asList(orders3)));
        System.out.println(solution.validateDeliveryPath(new ArrayList<>()));
    }

    public boolean validateDeliveryPath(List<String> orders){
        Set<String> set = new HashSet<>();
        for(String order : orders){
            if(order.startsWith("P")){
                set.add(order);
                continue;
            }
            if(!set.contains(order.replaceAll("D", "P"))){
                return false;
            }
        }
        return orders.size() > 0;
    }
}
