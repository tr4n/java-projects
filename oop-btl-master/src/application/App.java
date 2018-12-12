package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import databaseaccess.DataAccessObject;
import databaseaccess.DatabaseGeneration;
import databaseaccess.Query;
import utils.Utils;

public class App {
	private static final String SERVER_URL = "http://localhost:10035";
	private static final String CATALOG_ID = "/";
	private static final String REPOSITORY_ID = "oop";
	private static final String USERNAME = "test";
	private static final String PASSWORD = "xyzzy";

	private static int[] n = { 100, 5000, 60000, 300000, 600000 };
	private static int[] m = { 200, 7000, 80000, 400000, 1200000 };

	private static List<String> queryList;
	private static FileOutputStream outputStream;

	private DataAccessObject dataAccessObject;
	private DatabaseGeneration databaseGeneration;
	private Query query;

	public App(String queryListFileName, String resultFileName) {
		dataAccessObject = new DataAccessObject(SERVER_URL, USERNAME, PASSWORD, CATALOG_ID,
				REPOSITORY_ID);
		databaseGeneration = new DatabaseGeneration(dataAccessObject);
		query = new Query(dataAccessObject.getConnection());

		setQueryList(queryListFileName);
		setOutputStream(resultFileName);
	}

	public void run() {
		dataAccessObject.clearConnection();

		try {
			outputStream.write(new String("Begin at " + new Date().toString() + "\n").getBytes());

			for (int i = 0; i < 2; i++) {
				databaseGeneration.generateDatabase(n[i], m[i]);

				outputStream.write(new String("(" + n[i] + ", " + m[i] + ")\t").getBytes());
				int j;
				for (j = 0; j < 10; j++) {
					long queryTime = query.querySPARQLTime(queryList.get(j));
					outputStream.write(new String(queryTime + "\t").getBytes());
				}

				for (; j < 20; j++) {
					long queryTime = query.queryPROLOGTime(queryList.get(j));
					outputStream.write(new String(queryTime + "\t").getBytes());
				}

				outputStream.write(new String("\n").getBytes());
			}
			outputStream.write(new String("End at " + new Date().toString()).getBytes());
			outputStream.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setQueryList(String fileName) {
		queryList = Utils.readStringListFromFile("queries.txt");
	}

	public void setOutputStream(String ouputFileName) {
		try {
			outputStream = new FileOutputStream(new File(ouputFileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		App app = new App("queries.txt", "result.txt");
		app.run();
	}
}
