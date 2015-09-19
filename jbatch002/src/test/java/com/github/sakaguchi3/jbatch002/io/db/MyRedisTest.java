package com.github.sakaguchi3.jbatch002.io.db;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import testcase.other.RedisX;

/** 
 */
public class MyRedisTest {

	// ---------------------------------------------------------
	// test method
	// ---------------------------------------------------------

	@Test
	public void jedisSingleTest() {

		var myRedis = new RedisX();
//		try (var js = new Jed("example-dev.localdomain", 6379);) {
		try (var js = new Jedis("localhost", 6379);) {

			// jedis single

			breakPoint();

			if (js instanceof Jedis) {
				var castJs = (Jedis) js;
				var pingpong = castJs.ping();
			}

			js.append("kkkslll", "valll");
			js.set("kkkkk", "kkkkklll");
			var v1 = js.get("c");

			breakPoint();

		} catch (Throwable e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void jedisClusterTest() {

		var myRedis = new RedisX();
		try (var jc = myRedis.getJedis();) {

			// jedis cluster

			if (jc instanceof JedisCluster) {
				var castjc = (JedisCluster) jc;
				var node = castjc.getClusterNodes();
			}

			breakPoint();

			var v200 = jc.set("test", "63kkk79");
			var v201 = jc.append("akkkkk", "vallll");

			breakPoint();

		} catch (Throwable e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	// ---------------------------------------------------------
	// debug method
	// ---------------------------------------------------------

	void breakPoint() {

	}
}
