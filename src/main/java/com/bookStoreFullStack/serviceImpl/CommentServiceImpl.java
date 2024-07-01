package com.bookStoreFullStack.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStoreFullStack.entity.Comment;
import com.bookStoreFullStack.repository.CommentRepository;
import com.bookStoreFullStack.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getCommentsByBook(int bookId) {
        return commentRepository.findByBookId(bookId);
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment getCommentById(int id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment updateComment(Comment comment) {
        if (commentRepository.existsById(comment.getId())) {
            return commentRepository.save(comment);
        } else {
            throw new IllegalArgumentException("Comment with id " + comment.getId() + " does not exist");
        }
    }

    @Override
    public void deleteComment(int id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Comment with id " + id + " does not exist");
        }
    }
}
