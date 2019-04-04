package ccs.configs;

import ccs.dao.ShareDao;
import ccs.jdo.Course;
import ccs.jdo.Share;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private ShareDao shareDao;

    private LocalDate date = LocalDate.now().minusYears(1);
    private Random random = new Random();

    @Override
    public void run(ApplicationArguments args) {
        shareDao.saveAll(getLoaderData());
    }

    private Set<Share> getLoaderData() {
        Set<Share> shares = new LinkedHashSet<>();
        Stream.iterate(1, n -> n + 1)
                .limit(1000)
                .forEach(i -> shares.add(getNewShare()));

        return shares;
    }

    private Share getNewShare() {
        Share share = new Share();
        share.setName(RandomStringUtils.randomAlphabetic(4));
        share.setActive(random.nextBoolean());
        share.setCourses(getCourses(share));

        return share;
    }

    private Set<Course> getCourses(Share share) {
        Set<Course> courses = new HashSet<>();


        Stream.iterate(1, n -> n + 1)
                .limit(365)
                .forEach(i -> courses.add(getNewCourse(share, i)));

        return courses;
    }

    private Course getNewCourse(Share share, int i) {
        Date dateCourse = Date.from(date.plusDays(i).atStartOfDay(ZoneId.systemDefault()).toInstant());
        double min = 5.00;
        double max = 150.00;

        Course course = new Course();
        course.setShare(share);
        course.setDate(dateCourse);
        course.setPrice(ThreadLocalRandom.current().nextDouble(min, max));

        return course;
    }
}
