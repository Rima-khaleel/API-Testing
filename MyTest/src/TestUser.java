import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

class TestUser {

	@Test
	void testTotal() throws IOException, JSONException {
		String url = "https://reqres.in/api/users";
       ArrayList<User> userList = Helper.getUserList(url);
       int total = Helper.getTotal(url);
       
       assertEquals(userList.size(), total);
		
	}

}
