
import java.util.*;

public class Test {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap();
        Map map1 = new HashMap();
        map1.put("1","ABC");
        Map map2 = new HashMap();
        map2.put("2","DEF");
        map.put("x", map1.toString());
        map.put("y", map2.toString());
        System.out.println(map.getOrDefault("x", "").toString());
    }
}