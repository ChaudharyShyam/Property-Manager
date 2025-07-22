package Property.Property.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Property.Property.entity.Property;
import Property.Property.jpa.PropertyRepository;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository repo;

    public List<Property> getAllProperties() {
        return repo.findAll();
    }

    public Property getById(int id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteProperty(int id) {
        repo.deleteById(id);
    }

    public Property saveProperty(Property property) {
        return repo.save(property);
    }
    
    public List<Property> searchProperties(String keyword) {
        return repo.searchByKeyword(keyword);
    }
}