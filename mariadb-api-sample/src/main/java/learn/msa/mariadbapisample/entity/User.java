package learn.msa.mariadbapisample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;
}
