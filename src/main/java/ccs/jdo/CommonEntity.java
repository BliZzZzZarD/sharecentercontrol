package ccs.jdo;

import javax.persistence.*;

@MappedSuperclass
public abstract class CommonEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
