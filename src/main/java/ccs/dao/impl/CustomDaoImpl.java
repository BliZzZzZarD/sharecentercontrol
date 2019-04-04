package ccs.dao.impl;

import ccs.dao.CustomDao;
import ccs.exeptions.BusinessException;
import ccs.jdo.Course;
import ccs.jdo.Share;
import ccs.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomDaoImpl implements CustomDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(CustomDaoImpl.class);

    private static final String GET_SHARES_SEARCH = FileUtils.loadScriptFromFile("/sql/getSharesSearch.sql");
    private static final String GET_CURSES_BY_SHARE_ID = FileUtils.loadScriptFromFile("/sql/getCourseByShareId.sql");
    private static final String GET_COURSE_BY_DATE = FileUtils.loadScriptFromFile("/sql/getCourseByDate.sql");

    private static final RowMapper<Share> SHARE_MAPPER = new BeanPropertyRowMapper<>(Share.class);
    private static final RowMapper<Course> COURSE_MAPPER = new BeanPropertyRowMapper<>(Course.class);

    private static final String NO_COURSE_ERROR_MESSAGE = "No course for this share name and date";

    @Override
    public List<Share> getShareListBySearch(String src, Integer fromPos, Integer toPos) {
        log.info("process method getShareListBySearch for src: {}", src);
        Map<String, Object> params = new HashMap<>();
        params.put("filter", src);
        params.put("fromPos", fromPos);
        params.put("toPos", toPos);

        return namedParameterJdbcTemplate.query(GET_SHARES_SEARCH, params, SHARE_MAPPER);
    }

    @Override
    public List<Course> findCourseByShareId(Long shareId) {
        log.info("process method findCourseByShareId for shareId: {}", shareId);

        return namedParameterJdbcTemplate
                .query(GET_CURSES_BY_SHARE_ID, Collections.singletonMap("shareId", shareId), COURSE_MAPPER);
    }

    @Override
    public Double getCourseByDate(String name, Date date) {
        try {
            log.info("process method getCourseByDate for name: {} on date", date);
            Map<String, Object> params = new HashMap<>();
            params.put("name", name);
            params.put("date", date);

            return namedParameterJdbcTemplate.queryForObject(GET_COURSE_BY_DATE, params, Double.class);
        } catch (EmptyResultDataAccessException e) {
            log.warn("method getShareListBySearch has gotten an empty result");
            throw new BusinessException(NO_COURSE_ERROR_MESSAGE);
        }
    }
}
