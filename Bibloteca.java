import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Bibloteca {

	public static boolean utilizatorValid(User user) {

		boolean esteValidaParola = false;
		// verificare username
		if (user.getUsername().length() < 3) {
			return false;
		}

		// verificare parola

		for (char c : user.getPassword().toCharArray()) {
			if (Character.isLetterOrDigit(c)) {
				if (Character.isUpperCase(c)) {
					esteValidaParola = true;
					break;
				}

			}
		}

		if (!esteValidaParola) {
			return false;
		}

		if (!user.getUserType().equals("angajat") && !user.getUserType().equals("administrator")
				&& !user.getUserType().equals("cititor")) {
			return false;

		}
		return true;

	}

	public static void stergere_cititor(List<Cititor> cititori) {

		Scanner sc = new Scanner(System.in);

		boolean stergere = true;

		System.out.println("Introduceti username pe care doriti sa il stergeti: ");
		String username = sc.nextLine();

		for (Cititor a : cititori) {
			if (a.getUsername().equals(username)) {
				cititori.remove(a);
				return;
			} else if (!a.getUsername().equals(username)) {
				System.out.println("Username ul nu este corect!");
				break;
			}
		}

		while (stergere) {

			System.out.println("Doriti sa stergeti alt cititor? (Da/Nu)  ");
			String optiune = sc.nextLine().toLowerCase();

			if (optiune.equals("da")) {
				stergere = true;
				System.out.println("Introduceti username: ");
				username = sc.nextLine();
				for (Cititor a : cititori) {
					if (a.getUsername().equals(username)) {
						cititori.remove(a);
						return;
					}
				}
			} else if (optiune.equals("nu")) {
				stergere = false;
			} else {
				System.out.println("Optiune invalida. Va rugam sa introduceti \\\"Da\\\" sau \\\"Nu\\");
				break;
			}
		}
	}

	public static void adaugare_cititor(List<Cititor> cititori) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Introduceti username: ");
		String username = sc.nextLine();
		System.out.println("Introduceti parola: ");
		String password = sc.nextLine();
		Map<String, String> map = new HashMap<>();
		boolean adaugaCarti = true;
		while (adaugaCarti) {
			System.out.println("Introduceti cartea imprumutata: ");
			String key = sc.nextLine();
			System.out.println("Introduceti data de returnare: ");
			String data = sc.nextLine();
			map.put(key, data);
			System.out.println("Doriti sa adaugati o alta carte:  (Da/Nu)");
			String optiune = sc.nextLine().toLowerCase();
			if (optiune.equals("da")) {
				adaugaCarti = true;
			} else if (optiune.equals("nu")) {
				adaugaCarti = false;
			} else {
				System.out.println("Optiune invalida. Va rugam sa introduceti \"Da\" sau \"Nu\".");
				break;
			}
		}

		Cititor cititorNou = new Cititor(username, password, "cititor", map);

		boolean CititorExista = false;
		boolean este_valid = true;

		// verificare daca este valid
		if (!utilizatorValid(cititorNou)) {
			este_valid = false;
		}

		// verificare daca nu exista deja in lista
		for (Cititor cititor : cititori) {
			if (cititor.getUsername().equals(cititorNou.getUsername())
					&& cititor.getPassword().equals(cititorNou.getPassword())
					&& cititor.getHistory_about_borrower().equals(cititorNou.getHistory_about_borrower())) {
				CititorExista = true;
				break;
			}
		}

		if (CititorExista) {
			System.out.println("Cititorul nu poate fi adaugat in lista!");
		} else if (!este_valid) {
			System.out.println("Cititorul nu este valid!");
		} else {
			cititori.add(cititorNou);
			System.out.println("Cititorul a fost adaugat cu succes in lista!");
		}

		sc.close();
	}

	public static void main(String[] args) {

		List<User> useri = new ArrayList<>();
		List<Administrator> admini = new ArrayList<>();
		List<Cititor> cititori = new ArrayList<>();
		List<Angajat> angajati = new ArrayList<>();

		try {
			Scanner sc = new Scanner(new File("C:\\Users\\mariu\\eclipse-workspace\\Bibloteca\\src\\bibloteca"));
			while (sc.hasNextLine()) {
				String linie = sc.nextLine();

				String[] elemente = linie.split(" \\* ");

				String username = elemente[0].trim();
				String password = elemente[1].trim();
				String userType = elemente[2].trim();

				User user = new User(username, password, userType);
				if (utilizatorValid(user)) {
					useri.add(user);
				}

				if (utilizatorValid(user)) {

					switch (userType) {
					case "administrator": {
						Administrator admin = new Administrator(username, password, userType);
						admini.add(admin);
						break;
					}
					case "angajat": {
						String nr_de_identificare = "";
						String departament = "";

						if (elemente.length > 4) {
							departament = elemente[4].trim();
						}
						if (elemente.length > 3) {
							nr_de_identificare = elemente[3].trim();
						}
						Angajat angajat = new Angajat(username, password, userType, nr_de_identificare, departament);
						angajati.add(angajat);
						break;
					}
					case "cititor": {
						Map<String, String> map = new HashMap<>();
						for (int i = 3; i < elemente.length; i++) {
							String[] cartiImprumutate = elemente[i].split(",* ");

							String carte = cartiImprumutate[0];
							String data = cartiImprumutate[1];
							String value = data;
							value = value.substring(0, value.length() - 1);
							map.put(carte, value);

							String carte2 = cartiImprumutate[2];
							String data2 = cartiImprumutate[3];
							map.put(carte2, data2);
						}
						Cititor cititor = new Cititor(username, password, userType, map);
						cititori.add(cititor);
						break;
					}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (User user : useri) {
			System.out.println(user.afisare());
		}
		System.out.println("========");
		System.out.println("Lista adminilor: ");
		for (Administrator admin : admini) {
			System.out.println(admin);
		}

		System.out.println("=========");
		System.out.println("Lista angajatilor: ");
		for (Angajat angajat : angajati) {
			System.out.println(angajat);
		}

		System.out.println("=======");
		System.out.println("Lista cititorilor: ");
		for (Cititor cititor : cititori) {
			System.out.println(cititor);
		}
		System.out.println("=====");
		System.out.println("Verificare lista elemente unice: ");
		for (User user : useri) {
			System.out.println(user.getElementeUnice(useri));
			break;
		}
		System.out.println("======");
		System.out.println("Verificare daca are minim o carte imprumutata fiecare cititor: ");
		for (Cititor cititor : cititori) {
			if (cititor.VerificareImprumut(cititori)) {
				System.out.println("Cititorul " + cititor.getUsername() + " are minim o carte imprumutata!");
			} else {
				System.out.println("Cititorul " + cititor.getUsername() + "  nu are nicio carte imprumutata!");
			}
		}

		System.out.println("=====");
		System.out.println("Carti imprumutate in anul 2022: ");
		for (Cititor cititor : cititori) {
			cititor.carte_imprumutata_in_ianuarie_2022(cititori);
			break;
		}

		System.out.println("=======");
		System.out.println("Verificare nr angajati mai mare decat un numar dat sau nu: ");

		for (Angajat angajat : angajati) {
			angajat.verificareDepartamentA(1, angajati);
			break;
		}

		System.out.println("======");
		System.out.println("Sortare lista angajatilor dupa numele de utilizator: ");

		Collections.sort(angajati, new Comparator<Angajat>() {

			@Override
			public int compare(Angajat o1, Angajat o2) {

				return o1.getUsername().compareTo(o2.getUsername());
			}
		});

		for (Angajat angajat : angajati) {
			System.out.println(angajat);
		}

		System.out.println("======");
		System.out.println("Adaugare cititor in lista");

		adaugare_cititor(cititori);
		System.out.println("========");
		System.out.println("Lista cititorilor este: ");
		for (Cititor cititor : cititori) {
			System.out.println(cititor);
		}

		System.out.println("======");
		System.out.println("Stergere cititor in lista");

		stergere_cititor(cititori);
		System.out.println("===========");
		System.out.println("Lista cititorilor este: ");
		for (Cititor cititor : cititori) {
			System.out.println(cititor);
		}

	}

}
