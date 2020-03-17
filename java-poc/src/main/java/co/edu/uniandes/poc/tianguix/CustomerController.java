package co.edu.uniandes.poc.tianguix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/")
public class CustomerController {

    @Autowired
    private CustomerRepossitory repository;

    @PostMapping
    public @ResponseBody String addNewUser (@RequestBody Customer c) {
        repository.save(c);
        return "Saved";
    }

    @GetMapping
    public @ResponseBody Iterable<Customer> getAllUsers() {
        return repository.findAll();
    }
}