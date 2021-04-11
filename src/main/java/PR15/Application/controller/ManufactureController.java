package PR15.Application.controller;
import PR15.Application.model.Phone;
import PR15.Application.model.Manufacture;
import PR15.Application.service.ManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ManufactureController {
    @Autowired
    private ManufactureService manufactureService;

    @PostMapping("/manufacture")
    public void addManufacture(@RequestBody Manufacture manufacture) {
        manufactureService.addManufacture(manufacture);
    }

    @GetMapping("/manufactures")
    public List<Manufacture> getManufactures() {
        return manufactureService.getManufactures();
    }

    @GetMapping("/manufactures/{id}")
    public Manufacture getManufacture(@PathVariable UUID id) {
        return manufactureService.getManufacture(id);
    }

    @DeleteMapping("/manufactures/{id}")
    public void deleteManufacture(@PathVariable UUID id) {
        manufactureService.deleteManufacture(id);
    }
}
