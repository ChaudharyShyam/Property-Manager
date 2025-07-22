package Property.Property.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Property.Property.entity.Property;
import Property.Property.entity.Review;
import Property.Property.service.PropertyService;
import Property.Property.service.ReviewService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final PropertyService propertyService;

    @GetMapping("/{propertyId}")
    public String viewReviews(@PathVariable int propertyId, Model model) {
        List<Review> reviews = reviewService.getReviewsByPropertyId(propertyId);
        model.addAttribute("reviews", reviews);
        
       
        Property property = propertyService.getById(propertyId);
        model.addAttribute("property", property);  

        return "user/reviews"; 
    }

    @PostMapping("/add")
    public String addReview(@RequestParam int propertyId, 
                            @RequestParam String content, 
                            @RequestParam int rating) {
        Review review = new Review();
        review.setContent(content);
        review.setRating(rating);
        reviewService.addReview(propertyId, review);  
        return "redirect:/user/reviews/" + propertyId;
    }

    @PostMapping("/delete/{reviewId}")
    public String deleteReview(@PathVariable int reviewId, @RequestParam int propertyId) {
        reviewService.deleteReview(reviewId);
        return "redirect:/user/reviews/" + propertyId;
    }
    
    

}
