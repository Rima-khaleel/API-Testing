import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;

public class Test {

	public static final String URL_1 = "https://reqres.in/api/users?page=1";
	public static final String URL_2 = "https://reqres.in/api/users?page=2";

	public static void main(String[] args) throws IOException, JSONException {

		ArrayList<User> userList = Helper.getUserList(URL_1, URL_2);

		for (User user : userList) {
			System.out.print(user);
		}

	}

}
