import redis.clients.jedis.Jedis;

/**
 * Created by hvazquez on 1/29/2017.
 */
public class JedisTest {
  public static void main(String[] args) {
    Jedis jedis = new Jedis("192.168.33.10");
    jedis.set("name", "Herminio");
    String value = jedis.get("name");
    System.out.println("Value: " + value);
    jedis.del("name");

    // Working with lists
    jedis.lpush("accountNumbers", "001", "002", "003");
    System.out.println(jedis.rpop("accountNumbers"));
    System.out.println(jedis.rpop("accountNumbers"));
    System.out.println(jedis.rpop("accountNumbers"));
    jedis.del("accountNumbers");

    // Working with sets
    jedis.sadd("sofinumber", "1001", "1002","1003");
    System.out.println(jedis.smembers("sofinumber"));
    jedis.sadd("sofinumber", "1001", "1004");
    System.out.println(jedis.smembers("sofinumber"));

    System.out.println(jedis.sismember("sofinumber", "1002"));
    jedis.del("sofinumber");

  }
}
