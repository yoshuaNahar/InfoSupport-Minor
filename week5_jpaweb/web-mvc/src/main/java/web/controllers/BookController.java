package web.controllers;

import jpa.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.services.BookService;

@Controller
public class BookController {

  private BookService bookService;

  @Autowired
  public void setProductService(BookService bookService) {
    this.bookService = bookService;
  }

  @RequestMapping(value = {"/"}, method = RequestMethod.GET)
  public String home(Model model) {
    model.addAttribute("books", bookService.listAllBooks());
    return "index";
  }

  @RequestMapping(value = {"/books"}, method = RequestMethod.GET)
  public String list(Model model) {
    model.addAttribute("books", bookService.listAllBooks());
    return "books";
  }

  @RequestMapping("book/{id}")
  public String showBook(@PathVariable Long id, Model model) {
    model.addAttribute("book", bookService.getBookById(id));
    return "bookshow";
  }

  @RequestMapping("book/edit/{id}")
  public String edit(@PathVariable Long id, Model model) {
    model.addAttribute("book", bookService.getBookById(id));
    return "bookform";
  }

  @RequestMapping("book/new")
  public String newBook(Model model) {
    model.addAttribute("book", new Book());
    return "bookform";
  }

  @RequestMapping(value = "book", method = RequestMethod.POST)
  public String saveBook(Book book) {
    bookService.saveBook(book);
    return "redirect:/books";
  }

  @RequestMapping("book/delete/{id}")
  public String removeBook(@PathVariable Long id) {
    bookService.deleteBook(id);
    return "redirect:/books";
  }

}
