package ccs.services;

import ccs.dao.CustomDao;
import ccs.jdo.Share;
import ccs.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareLogic {
    @Autowired
    private CustomDao customDao;

    private static final Logger log = LoggerFactory.getLogger(ShareLogic.class);

    public List<Share> getShareListBySearch(String src, Integer fromPos, Integer toPos) {
        log.info("process method getShareListBySearch for src: {}", src);
        return customDao.getShareListBySearch(StringUtils.getPrepareText(src), fromPos, toPos);
    }
}
