import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;

public class Test {

	public static final String URL = "https://reqres.in/api/users";

	public static void main(String[] args) throws IOException, JSONException {

		ArrayList<User> userList = Helper.getUserList(URL);

		for (User user : userList) {
			System.out.println(user);
		}
	
	}

}
