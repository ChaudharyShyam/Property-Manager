package Property.Property.service;

import java.util.List;

import org.springframework.stereotype.Service;

import Property.Property.entity.Property;
import Property.Property.entity.Review;
import Property.Property.jpa.PropertyRepository;
import Property.Property.jpa.ReviewRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {


    private final ReviewRepository reviewRepository;
    
    private final PropertyRepository propertyRepository;

    public List<Review> getReviewsByPropertyId(int propertyId) {
        return reviewRepository.findByPropertyId(propertyId);
    }

    public void addReview(int propertyId, Review review) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        review.setProperty(property);
        reviewRepository.save(review);
    }


    public void deleteReview(int reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
