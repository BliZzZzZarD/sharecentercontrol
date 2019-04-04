package ccs.dao;

import ccs.jdo.Course;
import ccs.jdo.Share;

import java.util.Date;
import java.util.List;

public interface CustomDao {
    List<Share> getShareListBySearch(String src, Integer fromPos, Integer toPos);

    List<Share> getShareListBySearch(String src, Integer fromPos, Integer toPos, Boolean isActive);

    List<Course> findCourseByShareId(Long shareId);

    Double getCourseByDate(String name, Date date);
}
