
public class Administrator extends User {

	public Administrator(String username, String password, String userType) {
		super(username, password, userType);
		
	}

	public String afisare() {
		return super.toString();
	}

}
