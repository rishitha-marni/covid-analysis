package com.mindtree.daoimpl;


import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mindtree.dao.CovidAnalysisDao;
import com.mindtree.entity.CovidEntity;
import com.mindtree.exception.InvalidDateException;
import com.mindtree.exception.InvalidDateRangeException;
import com.mindtree.exception.InvalidStateCodeException;
import com.mindtree.exception.NoDataFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class CovidAnalysisDaoImpl implements CovidAnalysisDao {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<String> getStates() throws NoDataFoundException {
		TypedQuery<String> query = entityManager.createQuery("SELECT DISTINCT c.state FROM CovidEntity c", String.class);
        List<String> states = query.getResultList();
        if (states.isEmpty()) {
            throw new NoDataFoundException("No states found in the database");
        }
        return states;
	}

	@Override
	public List<String> getDistrictsByState(String stateCode) throws InvalidStateCodeException, NoDataFoundException {
		TypedQuery<String> query = entityManager
				.createQuery("SELECT DISTINCT c.district FROM CovidEntity c WHERE c.state = :state", String.class);
		query.setParameter("state", stateCode);
		return query.getResultList();
	}

	@Override
	public List<CovidEntity> getDataByStateAndDateRange(String state, Date startDate, Date endDate)
			throws InvalidDateException, InvalidDateRangeException, NoDataFoundException {
		TypedQuery<CovidEntity> query = entityManager.createQuery("SELECT c FROM CovidEntity c WHERE c.state = :state AND c.date BETWEEN :startDate AND :endDate", CovidEntity.class);
        query.setParameter("state", state);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
	}

	@Override
	public List<CovidEntity> compareConfirmedCases(String state1, String state2, Date startDate, Date endDate)
			throws InvalidDateException, InvalidDateRangeException, NoDataFoundException {
	    TypedQuery<CovidEntity> query = entityManager.createQuery("SELECT c FROM CovidEntity c WHERE (c.state = :state1 OR c.state = :state2) AND c.date BETWEEN :startDate AND :endDate", CovidEntity.class);
        query.setParameter("state1", state1);
        query.setParameter("state2", state2);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
	}

}
