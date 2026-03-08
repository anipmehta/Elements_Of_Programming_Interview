package sticker_to_spell_word;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String [] args){
        System.out.println(minStickers(new String[]{"with","example", "science"}, "thehat"));
        System.out.println(minStickers(new String[]{"notice","possible"}, "basicbasic"));
    }

    public static int minStickers(String[] stickers, String target) {
        Map<String, Integer> cache = new HashMap<>();
        int minStickers = helper(stickers, target, cache);
      return minStickers == Integer.MAX_VALUE ? -1 : minStickers;
    }
    public static int helper(String [] stickers, String target, Map<String, Integer> cache) {
        if(target.isEmpty()){
            return 0;
        }
        if(cache.containsKey(target)){
            return cache.get(target);
        }
        int minStickers = Integer.MAX_VALUE;
        for(String sticker : stickers) {
            String remainingTarget = match(sticker, target);
            if(remainingTarget.equals(target)){
                continue;
            }
            int stickerCount = helper(stickers, remainingTarget, cache);
            minStickers = Math.min(minStickers, stickerCount == Integer.MAX_VALUE ? Integer.MAX_VALUE : stickerCount + 1);
        }
        cache.put(target, minStickers);
        return minStickers;
    }

    private static String match(String sticker, String target) {
        Map<Character, Integer> stickerCharCount = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder("");
        for(Character ch : sticker.toCharArray()){
            stickerCharCount.put(ch, stickerCharCount.getOrDefault(ch, 0) + 1);
        }
        for(Character ch : target.toCharArray()) {
            if(stickerCharCount.containsKey(ch)){
                stickerCharCount.put(ch, stickerCharCount.get(ch) - 1);
                if(stickerCharCount.get(ch)==0){
                    stickerCharCount.remove(ch);
                }
            }
            else{
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }
}
