package Property.Property.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Property.Property.entity.Property;
import Property.Property.routes.PropertyRoutes;
import Property.Property.service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(PropertyRoutes.BASE_ROUTE)
@RequiredArgsConstructor
public class mycontroller {

    private final PropertyService ps;

    @GetMapping(PropertyRoutes.GET_ALL)
    public String getAllProperties(Model model) {
        model.addAttribute("propertyList", ps.getAllProperties());
        return "admin/propertyHome";
    }

    @GetMapping(PropertyRoutes.NEW_PROPERTY)
    public String getAllData(Model model) {
        model.addAttribute("property", new Property());
        return "admin/addproperty";
    }

    @PostMapping(PropertyRoutes.SAVE_PROPERTY)
    public String saveNewProperty(@Valid @ModelAttribute Property property, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/addproperty"; 
        }
        ps.saveProperty(property);
        return "redirect:" + PropertyRoutes.BASE_ROUTE + PropertyRoutes.GET_ALL;
    }

    @GetMapping(PropertyRoutes.UPDATE_PROPERTY)
    public String updateProperty(@PathVariable int id, Model model) {
        Property property = ps.getById(id);
        if (property != null) {
            model.addAttribute("property", property);
            return "admin/editproperty";
        } else {
            return "redirect:" + PropertyRoutes.BASE_ROUTE + PropertyRoutes.GET_ALL;
        }
    }

    @PostMapping(PropertyRoutes.UPDATE_PROPERTY)
    public String updateProperty(@PathVariable int id, @Valid @ModelAttribute Property property, BindingResult result) {
        if (result.hasErrors()) {
            return "editproperty"; 
        }
        property.setId(id);
        ps.saveProperty(property);
        return "redirect:" + PropertyRoutes.BASE_ROUTE + PropertyRoutes.GET_ALL;
    }

    @PostMapping(PropertyRoutes.DELETE_PROPERTY)
    public String deleteProperty(@PathVariable int id) {
        ps.deleteProperty(id);
        return "redirect:" + PropertyRoutes.BASE_ROUTE + PropertyRoutes.GET_ALL;
    }

    
    @GetMapping(PropertyRoutes.SEARCH_BAR)
    public String searchProperties(@RequestParam("keyword") String keyword, Model model) {
        List<Property> properties = ps.searchProperties(keyword);
        model.addAttribute("propertyList", properties);
        model.addAttribute("keyword", keyword);
        return "admin/propertyHome";
    }

    
}
