package com.mindtree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mindtree.dao.CovidAnalysisDao;
import com.mindtree.entity.CovidEntity;
import com.mindtree.exception.InvalidDateException;
import com.mindtree.exception.InvalidDateRangeException;
import com.mindtree.exception.InvalidStateCodeException;
import com.mindtree.exception.NoDataFoundException;
import com.mindtree.serviceimpl.CovidAnalysisServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CovidAnalysisUnitTestCases {

    @Mock
    CovidAnalysisDao analysisDao;

    @InjectMocks
    CovidAnalysisServiceImpl service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStatesValidData() throws NoDataFoundException {
        List<String> expectedStates = new ArrayList<>();
        expectedStates.add("JK");
        expectedStates.add("DL");

        when(analysisDao.getStates()).thenReturn(expectedStates);

        List<String> actualStates = service.getStates();

        assertNotNull(actualStates);
        assertEquals(expectedStates.size(), actualStates.size());
        assertEquals(expectedStates, actualStates);
    }

    @Test
    public void testGetDistrictsByStateValidData() throws NoDataFoundException, InvalidStateCodeException {
        String stateCode = "JK";
        List<String> expectedDistricts = new ArrayList<>();
        expectedDistricts.add("Budgam");
        expectedDistricts.add("Jammu");

        when(analysisDao.getDistrictsByState(stateCode)).thenReturn(expectedDistricts);

        List<String> actualDistricts = service.getDistrictsByState(stateCode);

        assertNotNull(actualDistricts);
        assertEquals(expectedDistricts.size(), actualDistricts.size());
        assertEquals(expectedDistricts, actualDistricts);
    }

    @Test
    public void testGetDataByStateWithinDateRangeValidData() throws NoDataFoundException, InvalidStateCodeException, InvalidDateException, InvalidDateRangeException {
        String stateCode = "JK";
        Date startDate = Date.valueOf("2020-07-07");
        Date endDate = Date.valueOf("2020-09-07");

        CovidEntity data1 = new CovidEntity(1L, Date.valueOf("2020-07-08"), "JK", "District1", "Tested1", "Confirmed1", "Recovered1");
        CovidEntity data2 = new CovidEntity(2L, Date.valueOf("2020-07-09"), "JK", "District2", "Tested2", "Confirmed2", "Recovered2");

        List<CovidEntity> expectedData = new ArrayList<>();
        expectedData.add(data1);
        expectedData.add(data2);


        when(analysisDao.getDataByStateAndDateRange(stateCode, startDate, endDate)).thenReturn(expectedData);

        List<CovidEntity> actualData = service.getDataByStateAndDateRange(stateCode, startDate, endDate);

        assertNotNull(actualData);
        assertEquals(expectedData.size(), actualData.size());
        // Add more assertions based on your specific requirements
    }

    @Test
    public void testGetDataByStateWithinDateRangeInvalidDate() {
        String stateCode = "JK";
        Date startDate = Date.valueOf("2024-06-01");
        Date endDate = Date.valueOf("2024-01-01");

        assertThrows(InvalidDateRangeException.class, () -> {
            service.getDataByStateAndDateRange(stateCode, startDate, endDate);
        });
    }

    @Test
    public void testGetConfirmedCasesByStatesWithinDateRangeInvalidDate() {
        String stateCode1 = "JK";
        String stateCode2 = "DL";
        Date startDate = Date.valueOf("2024-06-01");
        Date endDate = Date.valueOf("2024-01-01");

        assertThrows(InvalidDateRangeException.class, () -> {
            service.getConfirmedCasesByStatesWithInDateRange(stateCode1, stateCode2, startDate, endDate);
        });
    }
}
