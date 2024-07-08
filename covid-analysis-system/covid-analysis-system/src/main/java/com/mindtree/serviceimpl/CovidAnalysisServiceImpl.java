package com.mindtree.serviceimpl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.dao.CovidAnalysisDao;
import com.mindtree.entity.CovidEntity;
import com.mindtree.exception.InvalidDateException;
import com.mindtree.exception.InvalidDateRangeException;
import com.mindtree.exception.InvalidStateCodeException;
import com.mindtree.exception.NoDataFoundException;
import com.mindtree.service.CovidAnalysisService;

@Service
public class CovidAnalysisServiceImpl implements CovidAnalysisService {

	@Autowired
	CovidAnalysisDao analysisDao;
	
	@Override
	public List<String> getStates() throws NoDataFoundException {
		List<String> states = analysisDao.getStates();
        if (states.isEmpty()) {
            throw new NoDataFoundException("No data present");
        }
        return states;
	}

	@Override
	public List<String> getDistrictsByState(String state) throws InvalidStateCodeException, NoDataFoundException {
		 List<String> districts = analysisDao.getDistrictsByState(state);
	        if (districts.isEmpty()) {
	            throw new InvalidStateCodeException("Invalid State code, please check your input");
	        }
	        return districts.stream().sorted().collect(Collectors.toList());
	}

	@Override
	public List<CovidEntity> getDataByStateAndDateRange(String state, Date startDate, Date endDate)
			throws InvalidDateException, InvalidDateRangeException, NoDataFoundException {
		 validateDates(startDate, endDate);
	        List<CovidEntity> data = analysisDao.getDataByStateAndDateRange(state, startDate, endDate);
	        if (data.isEmpty()) {
	            throw new NoDataFoundException("No data present");
	        }
	        return data.stream().sorted((d1, d2) -> d1.getDate().compareTo(d2.getDate())).collect(Collectors.toList());
	    
	}

	@Override
	public List<CovidEntity> getConfirmedCasesByStatesWithInDateRange(String state1, String state2, Date startDate, Date endDate)
			throws InvalidDateException, InvalidDateRangeException, NoDataFoundException {
		validateDates(startDate, endDate);
        List<CovidEntity> data = analysisDao.compareConfirmedCases(state1, state2, startDate, endDate);
        if (data.isEmpty()) {
            throw new NoDataFoundException("No data present");
        }
        return data;
	}
	
	 private void validateDates(Date startDate, Date endDate) throws InvalidDateException, InvalidDateRangeException {
	        if (startDate == null) {
	            throw new InvalidDateException("Invalid Start date, please check your input");
	        }
	        if (endDate == null) {
	            throw new InvalidDateException("Invalid End date, please check your input");
	        }
	        if (endDate.before(startDate)) {
	            throw new InvalidDateRangeException("Invalid Date Range, Please check your input");
	        }
	    }
	 
	

}
