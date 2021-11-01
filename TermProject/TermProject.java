import java.util.*;

class TermProject {
	public static void main(String[] args) {
		System.out.println("Welcome to Big Data Processing Application");
		System.out.println("Please type the number that corresponds to which application you would like to run:");
		System.out.println("1. Apache Hadoop");
		System.out.println("2. Apache Spark");
		System.out.println("3. Jupyter Notebook");
		System.out.println("4. SonarQube and SonarScanner");

		System.out.print("Type the number here > ");

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();

		System.out.println("");

		switch(n) {
			case 1:
				openHadoop();
				break;

			case 2:
				openSpark();
				break;

			case 3:
				openJupyter();
				break;

			case 4:
				openSonar();
				break;

			default:
				System.out.println("Did not enter a valid number. Please try again.");
				break;
		}
	}

	private static void openHadoop() {
		System.out.println("Opening Hadoop");
	}

	private static void openSpark() {
		System.out.println("Opening Spark");
	}

	private static void openJupyter() {
		System.out.println("Opening Jupyter");
	}

	private static void openSonar() {
		System.out.println("Opening Sonar");
	}
}