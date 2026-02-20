package com.klu.libraryapp;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class LibraryController {

    List<Book> bookList = new ArrayList<>();

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Online Library System";
    }

    @GetMapping("/count")
    public int count() {
        return bookList.size();
    }

    @GetMapping("/price")
    public double price() {
        return 499.99;
    }

    @GetMapping("/books")
    public List<String> books() {
        List<String> titles = new ArrayList<>();
        for(Book b : bookList) {
            titles.add(b.getTitle());
        }
        return titles;
    }

    @GetMapping("/books/{id}")
    public Book bookById(@PathVariable int id) {
        for(Book b : bookList) {
            if(b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    @GetMapping("/search")
    public String search(@RequestParam String title) {
        return "Searching book with title: " + title;
    }

    @GetMapping("/author/{name}")
    public String author(@PathVariable String name) {
        return "Books written by author: " + name;
    }

    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        bookList.add(book);
        return "Book added successfully";
    }

    @GetMapping("/viewbooks")
    public List<Book> viewBooks() {
        return bookList;
    }
}