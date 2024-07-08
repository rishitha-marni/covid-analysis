package com.mindtree.controller;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.entity.CovidEntity;
import com.mindtree.exception.InvalidDateException;
import com.mindtree.exception.InvalidDateRangeException;
import com.mindtree.exception.InvalidStateCodeException;
import com.mindtree.exception.NoDataFoundException;
import com.mindtree.service.CovidAnalysisService;

@RestController
@RequestMapping("/covid")
public class CovidAnalysisController {

    @Autowired
    private CovidAnalysisService service;

    @GetMapping("/states")
    public List<String> getStatesName() throws NoDataFoundException {
        return service.getStates();
    }

    @GetMapping("/districts/{stateCode}")
    public List<String> getDistrictNameForGivenState(@PathVariable String stateCode) throws InvalidStateCodeException, NoDataFoundException {
        return service.getDistrictsByState(stateCode);
    }

    @GetMapping("/data/state")
    public List<CovidEntity> getDataByStateWithinDateRange(@RequestParam String stateCode,
                                                          @RequestParam String startDateStr,
                                                          @RequestParam String endDateStr)
            throws ParseException, InvalidStateCodeException, InvalidDateException, InvalidDateRangeException, NoDataFoundException {
        java.util.Date parsedStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
        java.util.Date parsedEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
        
        // Convert java.util.Date to java.sql.Date
        Date startDate = new Date(parsedStartDate.getTime());
        Date endDate = new Date(parsedEndDate.getTime());
        
        return service.getDataByStateAndDateRange(stateCode, startDate, endDate);
    }

    @GetMapping("/confirmedCases/state")
    public List<CovidEntity> getConfirmedCasesByStatesWithinDateRange(@RequestParam String stateCode1,
                                                                      @RequestParam String stateCode2,
                                                                      @RequestParam String startDateStr,
                                                                      @RequestParam String endDateStr)
            throws ParseException, InvalidDateException, InvalidDateRangeException, NoDataFoundException {
        java.util.Date parsedStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
        java.util.Date parsedEndDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
        
        // Convert java.util.Date to java.sql.Date
        Date startDate = new Date(parsedStartDate.getTime());
        Date endDate = new Date(parsedEndDate.getTime());
        
        return service.getConfirmedCasesByStatesWithInDateRange(stateCode1, stateCode2, startDate, endDate);
    }

}
