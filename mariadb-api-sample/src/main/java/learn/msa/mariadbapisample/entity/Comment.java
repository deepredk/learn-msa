package learn.msa.mariadbapisample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "comment")
@Entity
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)
    @Nullable
    private User user;

    @Column
    private String comment;

    public Comment(String comment) {
        this.comment = comment;
    }
}
