package com.nnk.springboot.controllers;

import com.nnk.springboot.Utils.PasswordHashing;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import javax.validation.Valid;

@Controller
public class CurveController {

    @Autowired
    private CurvePointRepository curvePointRepository;


    @GetMapping("/curvepoint/list")
    @ResponseStatus(code = HttpStatus.OK)
    public List<CurvePoint> usersList(){
        return curvePointRepository.findAll();
    }

    @GetMapping("/curvepoint/id")
    @ResponseStatus(code = HttpStatus.OK)
    public CurvePoint getUserById(@RequestParam int id){
        return curvePointRepository.getById(id);
    }

    @PostMapping("/curvepoint/add")
    @ResponseStatus(code = HttpStatus.OK)
    public CurvePoint addUser(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    @PostMapping("curvepoint/update")
    @ResponseStatus(code = HttpStatus.OK)
    public CurvePoint updateUser(@RequestBody CurvePoint curvePoint){
        CurvePoint c = curvePointRepository.getById(curvePoint.getId());
      c.setAsOfDate(curvePoint.getAsOfDate());
        c.setCreationDate(curvePoint.getCreationDate());
        c.setTerm(curvePoint.getTerm());
        curvePointRepository.save(c);
        return c;
    }

    @DeleteMapping("curvepoint/delete/id")
    @ResponseStatus(code = HttpStatus.OK)
    public Boolean deleteCurvepoint(@RequestParam int id){
        CurvePoint c = curvePointRepository.getById(id);
        curvePointRepository.delete(c);
        return true;
    }
}
