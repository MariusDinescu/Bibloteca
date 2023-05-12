import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cititor extends User {

	private Map<String, String> history_about_borrower = new HashMap<>();

	public Cititor(String username, String password, String userType, Map<String, String> history_about_borrower) {
		super(username, password, "cititor");
		this.history_about_borrower = history_about_borrower;
	}

	public Map<String, String> getHistory_about_borrower() {
		return history_about_borrower;
	}

	public void setHistory_about_borrower(Map<String, String> history_about_borrower) {
		this.history_about_borrower = history_about_borrower;
	}

	@Override
	public String toString() {
		return "Cititor" + super.toString() + "[history_about_borrower=" + history_about_borrower + "]";
	}

	public boolean VerificareImprumut(List<Cititor> cititori) {

		boolean nu_a_imprumutat = false;

		for (Cititor cititor : cititori) {
			if (cititor.history_about_borrower.isEmpty()) {
				nu_a_imprumutat = true;
			}
		}

		if (nu_a_imprumutat == true) {
			return false;
		}
		return true;
	}

	public void carte_imprumutata_in_ianuarie_2022(List<Cititor> cititori) {

		int count = 0;

		for (Cititor cititor : cititori) {

			if (cititor.getHistory_about_borrower().values().stream().anyMatch(v -> v.contains("01.2022"))) {
				count++;

			}
		}
		System.out.println(count);

	}

}
