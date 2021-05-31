package com.polar.cape.assignment.controller;

import com.polar.cape.assignment.model.Book;
import com.polar.cape.assignment.model.Ebook;
import com.polar.cape.assignment.model.PrintCopy;
import com.polar.cape.assignment.repository.BookRepository;
import com.polar.cape.assignment.repository.EbookRepository;
import com.polar.cape.assignment.repository.PrintCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired()
    BookRepository bookRepository;

    @Autowired()
    EbookRepository ebookRepository;

    @Autowired()
    PrintCopyRepository printCopyRepository;

    @RequestMapping("/books")
    public List<Book> findAll() {

        List<Book> bookList = bookRepository.
                findAll();
        bookList.sort(Comparator.comparing(b -> b.getAuthor().getLastName()));

        return bookList;
    }


    @PostMapping(path = "/ebook", consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Ebook> addebook(@RequestBody Ebook ebook) {


        if (ebook == null)
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        else {
            ebookRepository.save(ebook);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PostMapping(path = "/printcopy", consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<PrintCopy> addPrintCopyBook(@RequestBody PrintCopy printCopy) {


        if (printCopy != null) {
            printCopyRepository.save(printCopy);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

    }


    @DeleteMapping(path = "/deleteBook/{ISBN}")
    public void deleteBook(@PathVariable Long ISBN) {
        try {
            bookRepository.deleteById(ISBN);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }


}
