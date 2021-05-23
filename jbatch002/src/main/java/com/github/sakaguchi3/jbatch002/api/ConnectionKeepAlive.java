package com.github.sakaguchi3.jbatch002.api;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * https://kazuhira-r.hatenablog.com/entry/20171125/1511601958
 */
public class ConnectionKeepAlive {

	// ---------------------------------------------------------
	// field
	// ---------------------------------------------------------

	static final Logger LOGGER = LogManager.getLogger();

	protected long interval = 4;

	// ---------------------------------------------------------
	// constructor
	// ---------------------------------------------------------

	public static void main(String[] args) {

		System.out.println("start ----");

		var exec = new ConnectionKeepAlive();
		var data = List.of("data1", "data2", "data3");

		exec.exec2(data);

		System.out.println("end ----");
	}

	// ---------------------------------------------------------
	// method
	// ---------------------------------------------------------

	public void exec2(List<String> datas) {
		System.setProperty("jdk.httpclient.keepalive.timeout", "1000000");

		if (Objects.isNull(datas) || datas.isEmpty()) {
			return;
		}

		try {
			var url = new URL("http://localhost:8080/server002/s003");
			var con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);

			InputStream is = null;
			OutputStream os = null;

			for (var d : datas) {

				System.out.println("io:" + con.getDoInput());
				System.out.println("op: " + con.getDoOutput());

				if (os == null) {
					os = con.getOutputStream();
				}

				try (var bo = new BufferedOutputStream(os)) {
					bo.write(d.getBytes());
					bo.flush();
				}

				if (is == null) {
					is = con.getInputStream();
				}

				try (var br = new BufferedReader(new InputStreamReader(is));) {
					var result = br.lines().collect(Collectors.joining());
					System.out.println("result:" + result);
				}

				System.out.println("code: " + con.getResponseCode());
				System.out.println("loop:----");
				TimeUnit.SECONDS.sleep(interval);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public void exec(List<String> datas) {

		System.setProperty("jdk.httpclient.keepalive.timeout", "1");

		if (Objects.isNull(datas) || datas.isEmpty()) {
			return;
		}

		try {

			HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
			BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8);

			for (var d : datas) {
				HttpRequest request = HttpRequest.newBuilder() //
						.uri(URI.create("http://localhost:8080/server002/s003")) //
						.POST(BodyPublishers.ofString(d)) //
						.build();
				HttpResponse<String> response = client.send(request, bodyHandler);
				var result = response.body();

				System.out.println(result);
				TimeUnit.SECONDS.sleep(interval);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

//		try {
//			ServerSocket skt = new ServerSocket(1111); 
//			Socket clientSocket = skt.accept(); 
//			clientSocket.setKeepAlive(true); 
//			System.out.println("Connected.."); 
//			BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//
//			String inputLine;
//
//			while ((inputLine = input.readLine()) != null) {
//				System.out.println(inputLine);
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public void aaa() throws IOException {
		System.out.println("start >>>");

		try ( //
				ServerSocket server = new ServerSocket(80); //
				Socket socket = server.accept(); //
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8")); //
		) {

			String line = in.readLine();
			StringBuilder header = new StringBuilder();

			while (line != null && !line.isEmpty()) { // ★空行になるまで読み込みを続ける
				header.append(line + "\n");
				line = in.readLine();
			}

			System.out.println(header);
		}

		System.out.println("<<< end");
	}

	public void connection() {
		try {
			URL a = new URL("http://localhost:8080/server002/s003");
			URLConnection urlc = a.openConnection();
			keepAlive(urlc);

			SocketConfig socketConfig = SocketConfig.custom() //
					.setSoKeepAlive(true) //
					.setSoTimeout(3600000) //
					.build(); // We need to set socket keep alive
			RequestConfig requestConfig = RequestConfig.custom() //
					.setConnectTimeout(3600000) //
					.build();
			CloseableHttpClient httpClient = HttpClientBuilder.create() //
					.setDefaultRequestConfig(requestConfig) //
					.setDefaultSocketConfig(socketConfig) //
					.build();
			HttpPost post = new HttpPost(a.toString());
			CloseableHttpResponse response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();

//			HttpParams params = httpClient.getParams();
//			HttpConnectionParams.setSoKeepalive(params, true);

//			 var client = HttpClient.newHttpClient();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	void keepAlive(URLConnection urlc) {

		byte[] buf = new byte[3000];
		try {
			InputStream is = urlc.getInputStream();
			int ret = 0;
			while ((ret = is.read(buf)) > 0) {
				processBuf(buf);
			}
			// close the inputstream
			is.close();
		} catch (IOException e) {
			try {
				int respCode = ((HttpURLConnection) urlc).getResponseCode();
				InputStream es = ((HttpURLConnection) urlc).getErrorStream();
				int ret = 0;
				// read the response body
				while ((ret = es.read(buf)) > 0) {
					processBuf(buf);
				}
				// close the errorstream
				es.close();
			} catch (IOException ex) {
				// deal with the exception
			}
		}
	}

	void processBuf(byte[] buf) {

	}

	// ---------------------------------------------------------
	// setter/getter
	// ---------------------------------------------------------
}
