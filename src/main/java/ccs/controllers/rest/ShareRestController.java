package ccs.controllers.rest;

import ccs.dao.CustomDao;
import ccs.dao.ShareDao;
import ccs.exeptions.BusinessException;
import ccs.responses.Response;
import ccs.services.ShareLogic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Api(description = "Available requests")
public class ShareRestController {
    @Autowired
    private ShareLogic shareLogic;
    @Autowired
    private CustomDao customDao;
    @Autowired
    private ShareDao shareDao;

    private static final Logger log = LoggerFactory.getLogger(ShareLogic.class);

    private static final String DEFAULT_ERROR_MESSAGE = "Getting of data has finished with error";

    @ExceptionHandler(Exception.class)
    public Response handleDefaultException() {
        return Response.error(DEFAULT_ERROR_MESSAGE);
    }

    @ExceptionHandler(BusinessException.class)
    public Response handleBusinessException(Exception e) {
        return Response.error(e.getMessage());
    }

    @GetMapping("/shares")
    @ApiOperation(value = "The method is allowed to get a list of shares by part of name. List is displayed in parts.")
    public Response shares(@RequestParam String src, @RequestParam Integer fromPos, @RequestParam Integer toPos) {
        log.info("process method shares for src: {}", src);
        return Response.ok(shareLogic.getShareListBySearch(src, fromPos, toPos));
    }

    @GetMapping("/courses")
    @ApiOperation(value = "The method is allowed to get a list of course by shareId.")
    public Response courses(@RequestParam Long shareId) {
        log.info("process method courses for shareId: {}", shareId);
        return Response.ok(customDao.findCourseByShareId(shareId));
    }

    @GetMapping("/share-names")
    @ApiOperation(value = "The method is allowed to get a name list of all shares.")
    public Response shareNames() {
        log.info("process method shareNames");
        return Response.ok(shareDao.findAllName());
    }

    @GetMapping("/date-course")
    @ApiOperation(value = "The method is allowed to get a price of share on date.")
    public Response dateCourse
            (@RequestParam String name, @RequestParam @DateTimeFormat(pattern="yyyy.MM.dd") Date date) {
        log.info("process method dateCourse for name: {} on date: {}", name, date);
        return Response.ok(customDao.getCourseByDate(name, date));
    }
}
