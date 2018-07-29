import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lxj on 2018/7/29.
 */
public class RedisTest {
    private Jedis jedis;

    public void initJedis(){
        jedis = new Jedis("localhost",6379);
    }

    public void KeyTest(){
        jedis.set("one","1");
        System.out.println(jedis.get("one"));
        for (String str : jedis.keys("*")){
            System.out.println(str);
        }
    }

    public void StringTest(){
        jedis.setnx("key","value");
        jedis.append("key","123");
        System.out.println(jedis.get("key"));
    }

    public void MapTest(){
        Map<String,String> map = new HashMap<>();
        map.put("Lilei", "1989");
        map.put("HanMeimei","1990");
        jedis.hmset("Person", map);
        System.out.println(jedis.hget("Person", "Lilei"));
        for (String str : jedis.hkeys("Person")){
            System.out.println(str);
        }
    }

    public void  ListTest(){
        jedis.lpush("Name","Lilei");
        jedis.lpush("Name","HanMeimei");
        System.out.println(jedis.llen("Name"));
        System.out.println(jedis.lrange("Name",0, -1));
    }

    public void SetTest(){
        jedis.sadd("Year","1989");
        jedis.sadd("Year","1990");
        for (String str : jedis.smembers("Year")){
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        RedisTest redisTest = new RedisTest();
        redisTest.initJedis();
        System.out.println("Key op ======");
        redisTest.KeyTest();
        System.out.println("String op =======");
        redisTest.StringTest();
        System.out.println("Map op ======");
        redisTest.MapTest();
        System.out.println("List op ======");
        redisTest.ListTest();
        System.out.println("Set op =====");
        redisTest.SetTest();
    }
}
