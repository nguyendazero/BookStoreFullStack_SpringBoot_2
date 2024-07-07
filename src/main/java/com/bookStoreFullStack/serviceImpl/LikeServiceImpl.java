package com.bookStoreFullStack.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStoreFullStack.entity.Book;
import com.bookStoreFullStack.entity.Like;
import com.bookStoreFullStack.repository.LikeRepository;
import com.bookStoreFullStack.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public List<Like> getLikesByBook(int bookId) {
        return likeRepository.findByBookId(bookId);
    }

    @Override
    public Like saveLike(Like like) {
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

}
