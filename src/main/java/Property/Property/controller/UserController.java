package Property.Property.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Property.Property.entity.Property;
import Property.Property.service.PropertyService;
import Property.Property.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PropertyService serviceIMPL;

    @GetMapping("/user/home")
    public String getAllProperties(Model model) {
        // Fetch all properties from the database
    	List<Property> properties = serviceIMPL.getAllProperties();
    	if (properties == null) {
            properties = new ArrayList<>(); // Prevent null errors
        }
    	int limit = Math.min(6, properties.size());
        List<Property> limitedProperties = properties.subList(0, limit);
        System.out.println("Fetched properties: " + properties);
        //model.addAttribute("propertyList", serviceIMPL.getAllProperties());
        model.addAttribute("propertyList", limitedProperties);
        return "user/index"; 
    }
    
	/*
	 * @GetMapping("/user/home/properties") public String getAllProp(Model model) {
	 * List<Property> properties = serviceIMPL.getAllProperties();
	 * 
	 * if (properties == null) { properties = new ArrayList<>(); // Prevent null
	 * issues } model.addAttribute("propertyALL",properties);
	 * 
	 * return "user/properties"; }
	 */
    
    @GetMapping("/user/home/properties")
    public String getAllProp(@RequestParam(defaultValue = "0") int page, 
                             @RequestParam(defaultValue = "6") int size, 
                             Model model) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Property> propertyPage = userService.paginationProperties(pageable);

        model.addAttribute("propertyPage", propertyPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", propertyPage.getTotalPages());

        return "user/properties"; 
    }

    
    
    
    @GetMapping("/user/search")
    public String searchProperties(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("propertyList", userService.searchProperties(keyword));
        model.addAttribute("keyword", keyword);
        return "user/index";
    }

    @GetMapping("/user/filter")
    public String filterProperties(@RequestParam("type") String type, Model model) {
        if ("all".equalsIgnoreCase(type)) {
            model.addAttribute("propertyList", serviceIMPL.getAllProperties()); 
        } else {
            model.addAttribute("propertyList", userService.filterPropertiesByType(type));
        }
    	
        return "user/index";
    }
    
    
    @GetMapping("/user/home/about")
    public String about() {
        return "user/aboutpage"; 
    }
    
    @GetMapping("/user/home/login")
    public String showLoginPage() {
        return "user/login";
    }

    
    @GetMapping("/user/home/register")
    public String showRegisterPage() {
        return "user/register"; 
    }
    
    @GetMapping("/user/home/payment")
	public String showPaymentPage() {
		return "user/payment";
	}
    

}
