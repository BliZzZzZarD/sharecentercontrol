package ccs.jdo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "courses")
public class Course extends CommonEntity {
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "share_id")
    private Share share;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "price", nullable = false)
    private Double price;

    public Course() {
    }

    public Share getShare() {
        return share;
    }

    public Date getDate() {
        return date;
    }

    public Double getPrice() {
        return price;
    }

    public void setShare(Share share) {
        this.share = share;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
