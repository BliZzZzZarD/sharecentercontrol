package ccs.dao;

import ccs.jdo.Share;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareDao extends CrudRepository<Share, Long> {
    @Query("select sh.name from Share sh")
    List<String> findAllName();
}
