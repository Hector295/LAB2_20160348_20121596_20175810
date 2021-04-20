package com.ipt.dashboard.repository;

import com.ipt.dashboard.entity.Areas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreasRepository extends JpaRepository<Areas,Integer> {

}
