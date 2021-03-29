import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Helper {

	public static ArrayList<User> getUserList(String url) throws IOException, JSONException {

		BufferedReader reader2 = null;
		StringBuilder response2;
		ArrayList<User> userList = new ArrayList<User>();

		URLConnection url1 = new URL(url).openConnection();
		url1.setRequestProperty("User-Agent", "Mozilla");
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(url1.getInputStream(), Charset.forName("UTF-8")));

		StringBuilder response = new StringBuilder();
		String line1;
		while ((line1 = reader.readLine()) != null) {

			response.append(line1);

		}

		JSONObject jsonObject1 = new JSONObject(response.toString());
		int totalPageNumber = (int) jsonObject1.get("total_pages");

		try {

			for (int j = 1; j <= totalPageNumber; j++) {

				String url2 = "https://reqres.in/api/users?page=" + j + '"';
				URLConnection url1Connection = new URL(url2).openConnection();
				url1Connection.setRequestProperty("User-Agent", "Mozilla");
				reader2 = new BufferedReader(
						new InputStreamReader(url1Connection.getInputStream(), Charset.forName("UTF-8")));

				response2 = new StringBuilder();
				String line;
				while ((line = reader2.readLine()) != null) {

					response2.append(line);

				}

				JSONObject jsonObject2 = new JSONObject(response2.toString());
				JSONArray jsonarray = new JSONArray(jsonObject2.get("data").toString());
				int perPage = (int) jsonObject2.get("per_page");
				
				for (int i = 0; i < perPage; i++) {

					JSONObject jsonobject = new JSONObject(jsonarray.get(i).toString());
					User u = (User) getUser(jsonobject);
					userList.add(u);

				}

			}
			return userList;

		} catch (IOException e) {
			throw e;
		} finally {

			closeReader(reader2);
			// closeReader(readerForPage2);

		}

	}

	private static User getUser(JSONObject jsonobject) throws JSONException {

		String id = jsonobject.get("id").toString();
		String firstName = jsonobject.get("first_name").toString();
		String lastName = jsonobject.get("last_name").toString();
		String email = jsonobject.get("email").toString();
		String avatar = jsonobject.get("avatar").toString();

		return new User(id, firstName, lastName, avatar, email);
	}

	public static void closeReader(AutoCloseable closeable) {
		try {
			if (closeable != null) {
				closeable.close();

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(System.err);
		}

	}

	public static int getTotal(String url) throws MalformedURLException, IOException, JSONException {

		

		URLConnection urlConnection = new URL(url).openConnection();

		StringBuilder response = new StringBuilder();

		urlConnection.setRequestProperty("User-Agent", "Mozilla");

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(urlConnection.getInputStream(), Charset.forName("UTF-8")));

		String line;
		while ((line = reader.readLine()) != null) {

			response.append(line);

		}

		JSONObject jsonObject = new JSONObject(response.toString());
		int total = (int) jsonObject.get("total");
		return total;

	}
}
