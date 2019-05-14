package bookStore.studyExample.bookManager.controllers;

import bookStore.studyExample.bookManager.domain.Role;
import bookStore.studyExample.bookManager.domain.User;
import bookStore.studyExample.bookManager.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    //добавление пользователя, с проверкой существующих записей в БД
    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userFromDB = usersRepository.findByUsername(user.getUsername());

        if(userFromDB !=null){
            model.put("message", "Пользователь уже существует");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        usersRepository.save(user);

        return "redirect:/login";
    }
}
