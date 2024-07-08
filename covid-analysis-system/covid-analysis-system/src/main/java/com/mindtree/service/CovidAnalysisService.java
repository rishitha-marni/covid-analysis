package com.mindtree.service;

import java.sql.Date;
import java.util.List;

import com.mindtree.entity.CovidEntity;
import com.mindtree.exception.InvalidDateException;
import com.mindtree.exception.InvalidDateRangeException;
import com.mindtree.exception.InvalidStateCodeException;
import com.mindtree.exception.NoDataFoundException;

public interface CovidAnalysisService {
	
	List<String> getStates() throws NoDataFoundException;

	List<String> getDistrictsByState(String state) throws InvalidStateCodeException, NoDataFoundException;

	List<CovidEntity> getDataByStateAndDateRange(String state, Date startDate, Date endDate)
			throws InvalidDateException, InvalidDateRangeException, NoDataFoundException;

	List<CovidEntity> getConfirmedCasesByStatesWithInDateRange(String state1, String state2, Date startDate, Date endDate)
			throws InvalidDateException, InvalidDateRangeException, NoDataFoundException;

}
