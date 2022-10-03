package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.CurvePointService;
import com.nnk.springboot.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
public class CurvePointTest {

    @Autowired
    private CurvePointService curvePointService;

    @BeforeAll
    public void deleteall() {
        curvePointService.deleteAll();
    }

    @Test
    public void curvePointTest() {
        CurvePoint c = new CurvePoint();
        c.setTerm(1);
        // Save
        curvePointService.saveNewCurvePoint(c);
        CurvePoint c2 = curvePointService.getCurvePoint(1);
        assertNotNull(c2);

        // Update
        c.setTerm(100);
        curvePointService.saveNewCurvePoint(c);
        assertTrue(c.getTerm() == 100);

        // Find
        List<CurvePoint> listResult = curvePointService.getAllCurvePoints();
        assertTrue(listResult.size() > 0);

        // Delete
        curvePointService.deleteCurvePoint(c.getId());
        assertTrue(curvePointService.getAllCurvePoints().size()==0);	}
}

