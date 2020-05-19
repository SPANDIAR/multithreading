package io.spandiar;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Main {

	private static final String SOURCEFILE = "resource/LoremIpsum.txt";
	private static final int THREADPOOLSIZE = 2;
	private static final String SERVERCONTEXT = "/search";

	public static void main(String[] args) {

		try {
			String sourceString = new String(Files.readAllBytes(Paths.get(SOURCEFILE)));
			httpServer(sourceString);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void httpServer(String sourceString) throws IOException {

		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0); 
		server.createContext(SERVERCONTEXT, new SearchHandler(sourceString));
		Executor executor = Executors.newFixedThreadPool(THREADPOOLSIZE);
		server.setExecutor(executor);
		server.start();
	}

	private static class SearchHandler implements HttpHandler{

		private String sourceString;

		public SearchHandler(String search) {
			super();
			this.sourceString = search;
		}

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			String query = exchange.getRequestURI().getQuery();
			String[] split = query.split("=");
			String action = split[0];
			String searchFor = split[1];
			if(!action.equalsIgnoreCase("word")) {
				exchange.sendResponseHeaders(400, 0);
				return;
			}

			long countResult = wordCount(searchFor);
			exchange.sendResponseHeaders(200, countResult);
			OutputStream responseBody = exchange.getResponseBody();
			responseBody.write(Long.toString(countResult).getBytes());
			responseBody.close();
		}

		private long wordCount(String searchString) {

			long count = 0;
			int index = 0;

			while(index>=0) {
				index=this.sourceString.indexOf(searchString, index);
				if(index>=0) {
					count++;
					index++;
				}
			}
			return count;
		}
	}
}
