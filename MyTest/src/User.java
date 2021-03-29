
public class User {

	String id;
	String firstName;
	String lastName;
	String email;
	String avatar;

	public User(String id, String firstName, String lastName, String email, String avatar) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.avatar = avatar;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getAvatar() {
		return avatar;
	}

	@Override
	public String toString() {
		return "{ id: " + id + ", first_name: " + firstName + ", last_name: " + lastName + ", email: " + email

				+ ", avatar: " + avatar + "} ";
	}

}
