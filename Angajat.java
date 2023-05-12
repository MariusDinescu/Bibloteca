import java.util.List;

public class Angajat  extends User{

	
	private String nr_de_identificare;
	private String departament;
	
	
	public Angajat(String username, String password, String userType, String nr_de_identificare, String departament) {
		super(username, password, userType);
		this.nr_de_identificare = nr_de_identificare;
		this.departament = departament;
	}
	

	public String getNr_de_identificare() {
		return nr_de_identificare;
	}

	@Override
	public String toString() {
		return  "Angajat" + super.toString() + " [nr_de_identificare=" + nr_de_identificare + ", departament=" + departament
				+ ", Nr_de_identificare=" + getNr_de_identificare() + ", Departament=" + getDepartament()
				+ "]";
	}



	public void setNr_de_identificare(String nr_de_identificare) {
		this.nr_de_identificare = nr_de_identificare;
	}

	public String getDepartament() {
		return departament;
	}

	public void setDepartament(String departament) {
		this.departament = departament;
	}
	
	public void verificareDepartamentA(int k , List<Angajat>angajati) {
		int count_depA = 0;
		
		for(Angajat angajat:angajati) {
			if(angajat.getDepartament().equals("departamentA")) {
				count_depA++;
			}
		}
		
		if(count_depA > k) {
			System.out.println("Corect!");
		}else {
			System.out.println("Incorect!");
		}
				
				
				
	}
	
	
	
}
