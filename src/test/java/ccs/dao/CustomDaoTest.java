package ccs.dao;

import ccs.dao.impl.CustomDaoImpl;
import ccs.jdo.Course;
import ccs.jdo.Share;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@JdbcTest
@Import(CustomDaoImpl.class)
public class CustomDaoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomDao customDao;

    private Date date;

    private String NAME = "test";
    private Double PRICE = 1D;
    private Integer FROM = 0;
    private Integer TO = 1;

    @Before
    public  void init() {
        Share share = new Share();
        share.setName(NAME);
        share.setActive(true);

        date = new Date();

        Course course = new Course();
        course.setDate(date);
        course.setPrice(PRICE);
        course.setShare(share);

        Set<Course> courses = new HashSet<>();
        courses.add(course);

        share.setCourses(courses);

        entityManager.persist(share);
        entityManager.flush();
    }

    @Test
    public void testGetShareListBySearch() {
        List<Share> shares = customDao.getShareListBySearch(NAME, FROM, TO);

        assertEquals(1, shares.size());
    }

    @Test
    public void testGetShareListBySearchAndActive() {
        List<Share> shares = customDao.getShareListBySearch(NAME, FROM, TO, true);

        assertEquals(1, shares.size());
    }

    @Test
    public void testFindCourseByShareId() {
        List<Share> shares = customDao.getShareListBySearch(NAME, FROM, TO);
        List<Course> courses = customDao.findCourseByShareId(shares.get(0).getId());

        assertEquals(1, courses.size());
    }

    @Test
    public void testGetCourseByDate() {
        Double price = customDao.getCourseByDate(NAME, date);

        assertEquals(PRICE, price);
    }
}
