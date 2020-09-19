package com.github.sakaguchi3.jbatch002.io.resource;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.github.sakaguchi3.jbatch002.api.Constants;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 *
 */
public class RedisX {

	private JedisCluster jedis;

	public RedisX() {
		var hostPortsStr = Constants.REDIS_CLUSTER_ACTION;
		var nodes = Arrays.asList(hostPortsStr.split(",")).stream() //
				.map(v -> {
					String[] hostPortStr = v.split(":");
					String host = hostPortStr[0];
					int port = Integer.parseInt(hostPortStr[1]);

					return new HostAndPort(host, port);
				}) //
				.collect(Collectors.toSet());

//		var nodes = new HashSet<HostAndPort>();
//		nodes.add(new HostAndPort("localhost", 7000));
//		nodes.add(new HostAndPort("localhost", 7001));

//		final GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig<Object>();
//		poolConfig.setMaxWaitMillis(2000);
//		poolConfig.setMaxTotal(300);

//		this.jedis = new JedisCluster(nodes, poolConfig);
		this.jedis = new JedisCluster(nodes);

		breakPoint();
	}

	public JedisCluster getJedis() {
		return this.jedis;
	}

	void breakPoint() {

	}

}
