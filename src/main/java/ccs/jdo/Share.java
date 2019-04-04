package ccs.jdo;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "shares")
public class Share extends CommonEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @JsonManagedReference
    @OneToMany(mappedBy = "share", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Course> courses;

    public Share() {
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
