package com.example.cinemavericks.controllers;

import com.example.cinemavericks.models.PostReviewDTO;
import com.example.cinemavericks.models.Review;
import com.example.cinemavericks.models.ReviewDTO;
import com.example.cinemavericks.repositories.ReviewRepository;
import com.example.cinemavericks.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody PostReviewDTO postReviewDTO){
        Review newReview = reviewService.createReview(postReviewDTO);
        return new ResponseEntity<>(newReview, HttpStatus.CREATED);
    }
    
    @PatchMapping("/{reviewId}")
    public ResponseEntity<Review> editReview(@RequestBody ReviewDTO reviewDTO, @PathVariable long reviewId ){
        Optional<Review> targetReview = reviewService.getReviewById(reviewId);

        if(targetReview.isPresent()) {
        Review updatedReview = reviewService.editReview(reviewDTO, reviewId);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> deleteReview(@PathVariable long id){
        reviewService.deleteReview(id);
        return new ResponseEntity<>(id,HttpStatus.NO_CONTENT);
    }




}
