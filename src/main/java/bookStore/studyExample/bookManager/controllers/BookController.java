package bookStore.studyExample.bookManager.controllers;

import bookStore.studyExample.bookManager.domain.Book;
import bookStore.studyExample.bookManager.domain.User;
import bookStore.studyExample.bookManager.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class BookController {
    @Autowired
    private BooksRepository booksRepository;

    //для подгрузки файлов
    @Value("${upload.path}")
    private String uploadPath;

    //обращение к приветственной странице
    @GetMapping("/")
    public String getOpenPage(Map<String, Object> model) {
        return "open";
    }

    //создание модели и View для страницы books
    //также реализован фильтр для сообщений
    @GetMapping("/books")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       Model model) {
        Iterable<Book> books = booksRepository.findAll();
        if (filter != null && !filter.isEmpty()) {
            books = booksRepository.findByTitle(filter);
        } else {
            books = booksRepository.findAll();
        }

        model.addAttribute("books", books);
        model.addAttribute("filter", filter);
        return "books";
    }

    //основной обработчик (пока)
    //позволяет работать с файлами (подгружать), с проверкой их возможного существования
    //выводит заполненные данные в View
    //добавляет заполненные данные по таблицам, со связью oneToMany (один пользователь -- много книг)
    @PostMapping("/books")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String title,
                      @RequestParam String author,
                      @RequestParam String genre,
                      @RequestParam("file") MultipartFile file,
                      Map<String, Object> model) throws IOException {
        Book book = new Book(title, author, genre, user);

        if(file !=null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
                if(!uploadDir.exists()){
                    uploadDir.mkdir();
                }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile +"."+ file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            book.setFilename(resultFileName);
        }

        booksRepository.save(book);

        Iterable<Book> books = booksRepository.findAll();
        model.put("books", books);

        return "books";
    }


}