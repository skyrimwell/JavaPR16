package PR15.Application.controller;

import PR15.Application.model.Manufacture;
import PR15.Application.model.Phone;
import PR15.Application.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PhoneController {
    @Autowired
    private PhoneService phoneService;

    @PostMapping("/phones")
    public void addPhone(@RequestBody Phone phone) {
        phoneService.addPhone(phone);
    }

    @GetMapping("/phones")
    public List<Phone> getPhones() {
        return phoneService.getPhones();
    }

    @PostMapping("/PhoneManufacture/{id}")
    public void addManufacture(@PathVariable UUID id, @RequestBody String name, @RequestBody String address) {
        phoneService.addManufacture(id, new Manufacture(name, address));
    }

    @DeleteMapping("/PhoneManufacture/{id}")
    public void deleteManufacture(@PathVariable UUID id, Manufacture manufacture) {
       phoneService.removeManufacture(id, manufacture);
    }

    @GetMapping("/phone/{id}")
    public Phone getPhone(@PathVariable UUID id) {
        return phoneService.getPhone(id);
    }

    @DeleteMapping("/phone/{id}")
    public void deletePhone(@PathVariable UUID id) {
        phoneService.deletePhone(id);
    }
}
