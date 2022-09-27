package com.nnk.springboot.services;

import com.nnk.springboot.Utils.PasswordHashing;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurvePointService {

    @Autowired
    private CurvePointRepository curvePointRepository;

    public CurvePoint getCurvePoint (Integer id){
        return curvePointRepository.getById(id);
    }

    public List<CurvePoint> getAllCurvePoints(){
        return curvePointRepository.findAll();
    }

    public void saveNewCurvePoint(CurvePoint curvePoint){
        curvePointRepository.save(curvePoint);
    }

    public void deleteCurvePoint(Integer id){
        CurvePoint c = curvePointRepository.getById(id);
        curvePointRepository.delete(c);
    }



}
