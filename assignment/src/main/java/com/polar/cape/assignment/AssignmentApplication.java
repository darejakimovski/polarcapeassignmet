package com.polar.cape.assignment;

import com.polar.cape.assignment.model.*;
import com.polar.cape.assignment.repository.BookRepository;
import com.polar.cape.assignment.repository.EbookRepository;
import com.polar.cape.assignment.repository.PrintCopyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class AssignmentApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(AssignmentApplication.class, args);

        BookRepository eBookRepository = configurableApplicationContext.getBean(EbookRepository.class);
        BookRepository printCopyRepository = configurableApplicationContext.getBean(PrintCopyRepository.class);


        Author ernestHemingway = new Author("Ernest", "Hemingway", 1899);
        Book theOldManAndTheSea = new PrintCopy("The Old Man And The Sea", 1952, ernestHemingway, 128, 0.2);
        Book forWhomtheBellTolls = new PrintCopy("For Whom The Bell Tolls", 1940, ernestHemingway, 480, 0.6);
        Book toHaveAndToHaveNot = new PrintCopy("To Have And To Have Not", 1937, ernestHemingway, 272, 0.4);
        Book menWithoutWomen = new PrintCopy("Men Without Women", 1927, ernestHemingway, 288, 0.4);

        Author friedrichNietzsche = new Author("Friedrich", "Nietzsche", 1844);
        Book theAntichrist = new PrintCopy("The Antichrist", 1895, friedrichNietzsche, 96, 0.1);
        Book theGayScience = new PrintCopy("The Gay Science", 1882, friedrichNietzsche, 254, 0.4);
        Book beyondGoodAndEvil = new PrintCopy("Beyond Good And Evil", 1886, friedrichNietzsche, 240, 0.35);
        Book theDawnOfDay = new PrintCopy("The Dawn Of Day", 1881, friedrichNietzsche, 418, 0.5);

        Author bruceEckel = new Author("Bruce", "Eckel", 1957);
        Book thinkingInJava = new Ebook("Thinking In Java", 1998, bruceEckel, EbookFormat.PDF, 5.79);
        Book onJavaEight = new Ebook("On Java 8", 2017, bruceEckel, EbookFormat.PDF, 6.88);
        Book atomicKotlin = new Ebook("Atmoic Kotlin", 2021, bruceEckel, EbookFormat.PDF, 4.5);
        Book usingCplusplus = new Ebook("Using C++", 1989, bruceEckel, EbookFormat.PDF, 3.2);

        Author rumenaBuzharovska = new Author("Rumena", "Buzharovska", 1981);
        Book osmica = new PrintCopy("Osmica", 2010, rumenaBuzharovska, 146, 0.15);
        Book mojotMazh = new Ebook("Mojot Mazh", 2015, rumenaBuzharovska, EbookFormat.PDF, 2.8);

        eBookRepository.save(thinkingInJava);
        eBookRepository.save(onJavaEight);
        eBookRepository.save(atomicKotlin);
        eBookRepository.save(usingCplusplus);
        eBookRepository.save(mojotMazh);
        printCopyRepository.save(osmica);
        printCopyRepository.save(theOldManAndTheSea);
        printCopyRepository.save(forWhomtheBellTolls);
        printCopyRepository.save(toHaveAndToHaveNot);
        printCopyRepository.save(menWithoutWomen);
        printCopyRepository.save(theAntichrist);
        printCopyRepository.save(theGayScience);
        printCopyRepository.save(beyondGoodAndEvil);
        printCopyRepository.save(theDawnOfDay);

        List<Book> bookList = eBookRepository.findAll();
		bookList.addAll(printCopyRepository.findAll());


        System.out.println("All books sorted by year");
        printAllBooksSortedByYear(bookList);
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter first letter of Author's last name:");
        String letter = s.next();
        System.out.println("All books with Author's first letter: ");
        printAllBooksWithAuthorLastNameStartingAt(bookList, letter);
        System.out.println("All books in a decade");
        System.out.println("Please enter the name of the author: ");
        String name = s.next();
        printAllAuthorsForBooksInADecade(bookList, name);
        System.out.println("All authors that wrote more than 3 books ");
        printAllAuthorsWithMoreThanThreeBooks(bookList);
        System.out.println("Oldest book: ");
        printOldestBook(bookList);
        System.out.println("Newest book: ");
        printNewestBook(bookList);


    }

    static void printAllBooksSortedByYear(List<Book> bookList) {

        bookList.stream().sorted(Comparator.comparingInt(Book::getYearOfRelease)).forEach(System.out::println);


    }

    static void printAllBooksWithAuthorLastNameStartingAt(List<Book> bookList, String letter) {
        bookList.stream()
                .filter(b -> b.getAuthor() != null)
                .filter(b -> b.getAuthor().getLastName().startsWith(letter))
                .forEach(System.out::println);
    }

    public static void printAllAuthorsForBooksInADecade(List<Book> bookList, String authorName) {
        Author author = bookList.stream().map(Book::getAuthor).filter(a -> a.getName().equals(authorName)).findFirst().orElse(null);
        if (author != null) {
            List<Book> books = bookList.stream()
                    .filter(b -> b.getYearOfRelease() >= findDecade(author
                            .getYearOfBirth()) && b.getYearOfRelease() <= findDecade(author
                            .getYearOfBirth()) + 10)
                    .collect(Collectors.toList());
            if (books.isEmpty()) {
                System.out.println("No books found in the decade " + authorName + " was born");
            } else {
                books.forEach(System.out::println);
            }

        } else {
            System.out.println("Author name doesn't exist!");
        }
    }

    static void printAllAuthorsWithMoreThanThreeBooks(List<Book> bookList) {
        Map<Author, List<Book>> resultMap = bookList.stream().collect(Collectors.groupingBy(Book::getAuthor));
        resultMap.entrySet().stream().filter(e -> e.getValue().size() > 3).distinct().forEach(System.out::println);
    }


    static int findDecade(int year) {
        int start = year % 10;
        return year - start;
    }

    static void printOldestBook(List<Book> bookList) {
        bookList.stream().min(Comparator.comparingInt(Book::getYearOfRelease)).ifPresent(System.out::println);
    }

    public static void printNewestBook(List<Book> bookList) {
        bookList.stream().max(Comparator.comparingInt(Book::getYearOfRelease)).ifPresent(System.out::println);
    }

}
