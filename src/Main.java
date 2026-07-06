import ir.exception.BookNotFoundException;
import ir.model.Book;
import ir.model.Member;
import ir.repository.BookRepository;
import ir.repository.MemberRepository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BookRepository bookRepository = new BookRepository();
        MemberRepository memberRepository = new MemberRepository();

        while (true) {

            System.out.println("===== Library Management System =====");
            System.out.println("1. Register Book");
            System.out.println("2. Register Member");
            System.out.println("3. Find Book");
            System.out.println("4. Update Book Price");
            System.out.println("5. Delete Book");
            System.out.println("6. Delete Member");
            System.out.println("8. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    scanner.nextLine();

                    System.out.print("Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Author: ");
                    String author = scanner.nextLine();

                    System.out.print("Price: ");
                    double price = scanner.nextDouble();

                    System.out.print("Stock: ");
                    int stock = scanner.nextInt();

                    Book book = new Book(0, title, author, price, stock);

                    bookRepository.create(book);

                    System.out.println("Book Registered Successfully.");
                    break;

                case 2:

                    scanner.nextLine();

                    System.out.print("Full Name: ");
                    String fullName = scanner.nextLine();

                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();

                    Member member = new Member(0, fullName, phone);

                    memberRepository.registerMember(member);

                    System.out.println("Member Registered Successfully.");
                    break;

                case 3:

                    System.out.print("Book ID: ");
                    int findId = scanner.nextInt();

                    try {
                        System.out.println(bookRepository.findById(findId));
                    } catch (BookNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 4:

                    System.out.print("Book ID: ");
                    int updateId = scanner.nextInt();

                    System.out.print("New Price: ");
                    double newPrice = scanner.nextDouble();

                    bookRepository.updatePrice(updateId, newPrice);

                    System.out.println("Price Updated Successfully.");
                    break;

                case 5:

                    System.out.print("Book ID: ");
                    int deleteBookId = scanner.nextInt();

                    bookRepository.delete(deleteBookId);

                    System.out.println("Book Deleted Successfully.");
                    break;

                case 6:

                    System.out.print("Member ID: ");
                    int deleteMemberId = scanner.nextInt();

                    memberRepository.deleteMember(deleteMemberId);

                    System.out.println("Member Deleted Successfully.");
                    break;

                case 0:

                    System.out.println("Good Bye...");
                    return;

                default:

                    System.out.println("Invalid Choice!");
                    System.out.println("Invalid Choice!");
            }
        }
    }
}