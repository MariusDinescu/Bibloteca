import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

	private String username;
	private String password;
	private String userType;

	public User(String username, String password, String userType) {
		super();
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	public String afisare() {
		return "User[username=" + username + ", password=" + password + ", userType=" + userType + "]";
	}

	@Override
	public String toString() {
		return "[username=" + username + ", password=" + password + ", userType=" + userType + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public boolean getElementeUnice(List<User> useri) {

		Set<User> set_useri = new HashSet<>(useri);

		return set_useri.size() == useri.size();
	}

}
