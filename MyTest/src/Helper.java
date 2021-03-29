import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Helper {

	public static ArrayList<User> getUserList(String url1, String url2) throws IOException, JSONException {

		BufferedReader readerForPage1 = null;
		BufferedReader readerForPage2 = null;

		try {

			URLConnection url1Connection = new URL(url1).openConnection();
			URLConnection url2Connection = new URL(url2).openConnection();
			StringBuilder response1 = new StringBuilder();
			StringBuilder response2 = new StringBuilder();
			url1Connection.setRequestProperty("User-Agent", "Mozilla");
			url2Connection.setRequestProperty("User-Agent", "Mozilla");
			readerForPage1 = new BufferedReader(
					new InputStreamReader(url1Connection.getInputStream(), Charset.forName("UTF-8")));
			readerForPage2 = new BufferedReader(
					new InputStreamReader(url2Connection.getInputStream(), Charset.forName("UTF-8")));

			String lineForPage1;
			while ((lineForPage1 = readerForPage1.readLine()) != null) {

				response1.append(lineForPage1);

			}

			String lineForPage2;
			while ((lineForPage2 = readerForPage2.readLine()) != null) {

				response2.append(lineForPage2);

			}

			JSONObject jsonObject1 = new JSONObject(response1.toString());
			JSONArray jsonarray1 = new JSONArray(jsonObject1.get("data").toString());

			JSONObject jsonObject2 = new JSONObject(response2.toString());
			JSONArray jsonArray2 = new JSONArray(jsonObject2.get("data").toString());

			ArrayList<User> userList = new ArrayList<User>();

			for (int i = 0, limit = jsonarray1.length(); i < limit; i++) {

				JSONObject jsonobject = new JSONObject(jsonarray1.get(i).toString());
				User u = (User) getUser(jsonobject);
				userList.add(u);

			}

			for (int i = 0, limit = (int) jsonObject2.get("per_page"); i < limit; i++) {

				JSONObject jsonobject = new JSONObject(jsonArray2.get(i).toString());
				User u2 = (User) getUser(jsonobject);
				userList.add(u2);

			}

			if (userList.size() == (int) jsonObject2.get("total")) {
				System.out.println("Equals");
			}

			return userList;

		} catch (IOException e) {
			throw e;
		} finally {

			closeReader(readerForPage1);
			closeReader(readerForPage2);

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
}
