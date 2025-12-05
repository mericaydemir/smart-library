import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;
import java.io.PrintStream;
import Models.*;
import Repositories.*;
import Database.Database;

public class Main {
    public static void main(String[] args) {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "chcp", "65001").inheritIO().start().waitFor();
            }
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (Exception e) {
        }

        Database.createNewDatabase();

        try (Scanner scanner = createScanner()) {

            BookRepository bookRepo = new BookRepository();
            StudentRepository studentRepo = new StudentRepository();
            LoanRepository loanRepo = new LoanRepository();

            while (true) {
                System.out.println("\n--- SMART LIBRARY Y\u00d6NET\u0130M S\u0130STEM\u0130 ---");
                System.out.println("1. Kitap Ekle");
                System.out.println("2. Kitaplar\u0131 Listele");
                System.out.println("3. Kitap Sil");
                System.out.println("4. \u00d6\u011frenci Ekle");
                System.out.println("5. \u00d6\u011frencileri Listele");
                System.out.println("6. \u00d6\u011frenci Sil");
                System.out.println("7. Kitap \u00d6d\u00fcn\u00e7 Ver");
                System.out.println("8. \u00d6d\u00fcn\u00e7 Listesini G\u00f6r\u00fcnt\u00fcle");
                System.out.println("9. Kitap Geri Teslim Al");
                System.out.println("0. \u00c7\u0131k\u0131\u015f");
                System.out.print("Se\u00e7iminiz: ");

                int choice = -1;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("L\u00fctfen ge\u00e7erli bir say\u0131 giriniz.");
                    continue; 
                }

                switch (choice) {
                    case 1:
                        System.out.println("--- Kitap Ekleme (\u0130ptal i\u00e7in '0' yaz\u0131n\u0131z) ---");
                        System.out.print("Kitap Ad\u0131: ");
                        String title = scanner.nextLine();
                        if (title.equals("0")) {
                            System.out.println("\u0130\u015flem iptal edildi.");
                            break;
                        }
                        if (title.trim().isEmpty()) {
                            System.out.println("HATA: Kitap ad\u0131 bo\u015f olamaz!");
                            break;
                        }
                        System.out.print("Yazar: ");
                        String author = scanner.nextLine();
                        if (author.trim().isEmpty()) {
                            System.out.println("HATA: Yazar ad\u0131 bo\u015f olamaz!");
                            break;
                        }
                        System.out.print("Y\u0131l: ");
                        int year;
                        try {
                            year = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("HATA: Y\u0131l ge\u00e7erli bir say\u0131 olmal\u0131d\u0131r!");
                            break;
                        }
                        bookRepo.add(new Book(title, author, year));
                        break;

                    case 2:
                        List<Book> books = bookRepo.getAll();
                        System.out.println("\n--- Kitap Listesi ---");
                        if (books.isEmpty()) {
                            System.out.println("UYARI: Listelenecek kitap bulunmamaktad\u0131r.");
                        } else {
                            for (Book b : books) {
                                System.out.println(b); 
                            }
                        }
                        break;

                    case 3:
                        System.out.println("--- Kitap Silme (\u0130ptal i\u00e7in '0' yaz\u0131n\u0131z) ---");
                        System.out.print("Silinecek Kitap ID: ");
                        String deleteBookIdInput = scanner.nextLine();
                        if (deleteBookIdInput.equals("0")) {
                            System.out.println("\u0130\u015flem iptal edildi.");
                            break;
                        }
                        int deleteBookId;
                        try {
                            deleteBookId = Integer.parseInt(deleteBookIdInput);
                            bookRepo.delete(deleteBookId);
                        } catch (NumberFormatException e) {
                            System.out.println("HATA: Kitap ID say\u0131sal olmal\u0131d\u0131r!");
                        }
                        break;

                    case 4:
                        System.out.println(
                                "--- \u00d6\u011frenci Ekleme (\u0130ptal i\u00e7in '0' yaz\u0131n\u0131z) ---");
                        System.out.print("\u00d6\u011frenci Ad\u0131: ");
                        String name = scanner.nextLine();
                        if (name.equals("0")) {
                            System.out.println("\u0130\u015flem iptal edildi.");
                            break;
                        }
                        if (name.trim().isEmpty()) {
                            System.out.println("HATA: \u00d6\u011frenci ad\u0131 bo\u015f olamaz!");
                            break;
                        }
                        System.out.print("B\u00f6l\u00fcm: ");
                        String dept = scanner.nextLine();
                        if (dept.trim().isEmpty()) {
                            System.out.println("HATA: B\u00f6l\u00fcm ad\u0131 bo\u015f olamaz!");
                            break;
                        }
                        studentRepo.add(new Student(name, dept));
                        break;

                    case 5:
                        List<Student> students = studentRepo.getAll();
                        System.out.println("\n--- \u00d6\u011frenci Listesi ---");
                        if (students.isEmpty()) {
                            System.out.println("UYARI: Listelenecek \u00f6\u011frenci bulunmamaktad\u0131r.");
                        } else {
                            for (String s : students.stream().map(Object::toString).collect(Collectors.toList())) {
                                System.out.println(s);
                            }
                        }
                        break;

                    case 6:
                        System.out.println(
                                "--- \u00d6\u011frenci Silme (\u0130ptal i\u00e7in '0' yaz\u0131n\u0131z) ---");
                        System.out.print("Silinecek \u00d6\u011frenci ID: ");
                        String deleteStudentIdInput = scanner.nextLine();
                        if (deleteStudentIdInput.equals("0")) {
                            System.out.println("\u0130\u015flem iptal edildi.");
                            break;
                        }
                        int deleteStudentId;
                        try {
                            deleteStudentId = Integer.parseInt(deleteStudentIdInput);
                            studentRepo.delete(deleteStudentId);
                        } catch (NumberFormatException e) {
                            System.out.println("HATA: \u00d6\u011frenci ID say\u0131sal olmal\u0131d\u0131r!");
                        }
                        break;

                    case 7:
                        System.out.println(
                                "--- Kitap \u00d6d\u00fcn\u00e7 Verme (\u0130ptal i\u00e7in '0' yaz\u0131n\u0131z) ---");
                        System.out.print("Kitap ID: ");
                        String bookIdInput = scanner.nextLine();
                        if (bookIdInput.equals("0")) {
                            System.out.println("\u0130\u015flem iptal edildi.");
                            break;
                        }
                        int bookId;
                        try {
                            bookId = Integer.parseInt(bookIdInput);
                        } catch (NumberFormatException e) {
                            System.out.println("HATA: Kitap ID say\u0131sal olmal\u0131d\u0131r!");
                            break;
                        }
                        System.out.print("\u00d6\u011frenci ID: ");
                        int studentId;
                        try {
                            studentId = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("HATA: \u00d6\u011frenci ID say\u0131sal olmal\u0131d\u0131r!");
                            break;
                        }
                        System.out.print("Tarih (GG.AA.YYYY): ");
                        String date = scanner.nextLine();
                        if (date.trim().isEmpty()) {
                            System.out.println("HATA: Tarih bo\u015f olamaz!");
                            break;
                        }

                        loanRepo.add(new Loan(bookId, studentId, date));
                        break;

                    case 8:
                        List<Loan> loans = loanRepo.getAll();
                        System.out.println("\n--- \u00d6d\u00fcn\u00e7 Ge\u00e7mi\u015fi ---");
                        if (loans.isEmpty()) {
                            System.out.println("UYARI: \u00d6d\u00fcn\u00e7 kayd\u0131 bulunmamaktad\u0131r.");
                        } else {
                            for (Loan l : loans) {
                                System.out.println(l);
                            }
                        }
                        break;

                    case 9:
                        System.out.println("--- Kitap \u0130ade Alma (\u0130ptal i\u00e7in '0' yaz\u0131n\u0131z) ---");
                        System.out.print("Teslim Al\u0131nacak Kitap ID: ");
                        String returnBookIdInput = scanner.nextLine();
                        if (returnBookIdInput.equals("0")) {
                            System.out.println("\u0130\u015flem iptal edildi.");
                            break;
                        }
                        int returnBookId;
                        try {
                            returnBookId = Integer.parseInt(returnBookIdInput);
                        } catch (NumberFormatException e) {
                            System.out.println("HATA: Kitap ID say\u0131sal olmal\u0131d\u0131r!");
                            break;
                        }
                        System.out.print("Teslim Tarihi (GG.AA.YYYY): ");
                        String returnDate = scanner.nextLine();
                        if (returnDate.trim().isEmpty()) {
                            System.out.println("HATA: Tarih bo\u015f olamaz!");
                            break;
                        }

                        loanRepo.returnBook(returnBookId, returnDate);
                        break;

                    case 0:
                        System.out.println("Sistem kapat\u0131l\u0131yor...");
                        return;

                    default:
                        System.out.println("Ge\u00e7ersiz se\u00e7im!");
                }

                if (choice != 0) {
                    System.out
                            .println("\nAna men\u00fcye d\u00f6nmek i\u00e7in Enter tu\u015funa bas\u0131n\u0131z...");
                    scanner.nextLine();
                }
            }
        }
    }

    private static Scanner createScanner() {
        try {
            return new Scanner(System.in, "UTF-8");
        } catch (Exception e) {
            return new Scanner(System.in);
        }
    }
}