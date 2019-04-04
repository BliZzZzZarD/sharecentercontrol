package ccs.dao;

import ccs.jdo.Share;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ShareDaoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShareDao shareDao;

    @Test
    public void testFindAllName() {
        Share share = new Share();
        share.setName("test");
        share.setActive(true);

        entityManager.persist(share);
        entityManager.flush();

        List<String> shares = shareDao.findAllName();

        assertEquals(1, shares.size());
    }
}
