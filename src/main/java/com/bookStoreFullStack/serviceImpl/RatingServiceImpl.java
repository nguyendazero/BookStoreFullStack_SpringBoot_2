package com.bookStoreFullStack.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStoreFullStack.entity.Rating;
import com.bookStoreFullStack.repository.RatingRepository;
import com.bookStoreFullStack.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Rating> getRatingsByBook(int bookId) {
        return ratingRepository.findByBookId(bookId);
    }

    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating getRatingById(int id) {
        return ratingRepository.findById(id).orElse(null);
    }

    @Override
    public Rating updateRating(Rating rating) {
        if (ratingRepository.existsById(rating.getId())) {
            return ratingRepository.save(rating);
        } else {
            throw new IllegalArgumentException("Rating with id " + rating.getId() + " does not exist");
        }
    }

    @Override
    public void deleteRating(int id) {
        if (ratingRepository.existsById(id)) {
            ratingRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Rating with id " + id + " does not exist");
        }
    }

}
