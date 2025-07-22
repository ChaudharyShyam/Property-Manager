package Property.Property.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Property.Property.entity.Property;
import Property.Property.jpa.PropertyRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PropertyRepository propertyRepository;

//    public List<Property> getAllProperties() {
//        return propertyRepository.findAll();
//    }
    

    public List<Property> searchProperties(String keyword) {
        return propertyRepository.searchByKeyword(keyword);
    }

    public List<Property> filterPropertiesByType(String type) {
        return propertyRepository.findByType(type);
    }
    
    public Page<Property> paginationProperties(Pageable pageable) {
        return propertyRepository.findAll(pageable); 
    }

}
