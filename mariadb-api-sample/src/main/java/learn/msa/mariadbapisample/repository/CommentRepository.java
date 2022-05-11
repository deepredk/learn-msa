package learn.msa.mariadbapisample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import learn.msa.mariadbapisample.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    
}
