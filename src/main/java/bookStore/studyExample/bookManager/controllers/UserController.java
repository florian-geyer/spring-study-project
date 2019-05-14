package bookStore.studyExample.bookManager.controllers;

import bookStore.studyExample.bookManager.domain.Role;
import bookStore.studyExample.bookManager.domain.User;
import bookStore.studyExample.bookManager.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

//Класс-обработчик для страницы со списком пользователей
//Пользователей модно редактировать
//Здесь же выполняем проверку администраторских прав
@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    UsersRepository usersRepository;

    //возврат списка всех пользователей, для админитратора
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", usersRepository.findAll());
       return "userList";
    }

    //редактирование пользователя, назначение ему администраторских пра
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    //сохранение нового пользователя
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user)
    {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).
                map(Role::name).
                collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        usersRepository.save(user);
        return "redirect:/user";
    }
}
