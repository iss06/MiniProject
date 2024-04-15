import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class Project {
	private static String rootPath = System.getProperty("user.dir") + "\\files\\";
	private static String filename = rootPath + "PhoneDB.txt";

	public static void main(String[] args) {
		boolean run = true;
		Scanner scanner = new Scanner(System.in);

		System.out.println("************************************");
		System.out.println("*         전화번호 관리 프로그램         *");
		System.out.println("************************************");
		while (run) {
			System.out.println("1. 리스트 2. 등록 3. 삭제 4. 검색 5. 종료");
			System.out.println("-------------------------------------");
			System.out.print(">메뉴번호:");

//			Scanner scanner = new Scanner(System.in);
			int list = scanner.nextInt();

			switch (list) {

			case 1:
				System.out.println("<1.리스트>");

				try {
					BufferedReader br = new BufferedReader(new FileReader(filename));
					String phoneList;

					while ((phoneList = br.readLine()) != null) {
						System.out.println(phoneList);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

				break;

			case 2:
				File file = new File(filename);
				System.out.println("<2.등록>");
				System.out.println(">이름: ");
				System.out.println(">휴대전화: ");
				System.out.println(">회사전화: ");

				String name = scanner.next();
				String hp = scanner.next();
				String cp = scanner.next();

				try (Writer writer = new FileWriter(filename, true)) {
					writer.write(name);
					writer.write(" " + hp);
					writer.write(" " + cp);
					writer.flush();
					System.out.println("\n[등록되었습니다.]");

				} catch (IOException e) {
					e.printStackTrace();

				}

				break;

			case 3:
				System.out.println("<3.삭제>");
				System.out.println(">번호: ");
				String deleteNum = scanner.next();

				try {
					File inputFile = new File(filename);
					File tempFile = new File(rootPath + "TempPhoneDB.txt");

					BufferedReader reader = new BufferedReader(new FileReader(inputFile));
					BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

					String lineToRemove = ">번호: " + deleteNum;
					String currentLine;

					boolean found = false;
					while ((currentLine = reader.readLine()) != null) {

						if (currentLine.equals(lineToRemove)) {
							found = true;

							continue;

						}
						writer.write(currentLine + "\n");

					}
					writer.close();
					reader.close();
					System.out.println("\n[삭제되었습니다.]");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
}
