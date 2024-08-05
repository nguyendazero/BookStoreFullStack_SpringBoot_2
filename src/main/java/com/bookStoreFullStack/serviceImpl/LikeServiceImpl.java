package com.bookStoreFullStack.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStoreFullStack.entity.Book;
import com.bookStoreFullStack.entity.LikeRating;
import com.bookStoreFullStack.entity.Rating;
import com.bookStoreFullStack.entity.User;
import com.bookStoreFullStack.repository.LikeRepository;
import com.bookStoreFullStack.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public List<LikeRating> getLikesByBook(int bookId) {
        return likeRepository.findByBookId(bookId);
    }

    @Override
    public LikeRating saveLike(LikeRating like) {
        return likeRepository.save(like);
    }

    @Override
    public void deleteLike(int id) {
        if (likeRepository.existsById(id)) {
            likeRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Like with id " + id + " does not exist");
        }
    }
    
    @Override
    public List<LikeRating> findLikesByRatingId(int ratingId) {
        return likeRepository.findByRatingId(ratingId);
    }
    
    @Override
    public LikeRating findLikeByUserAndRating(User user, Rating rating) {
        return likeRepository.findByUserAndRating(user, rating);
    }

}
