package learn.msa.mariadbapisample.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)
    private User user;

    private String comment;
}
