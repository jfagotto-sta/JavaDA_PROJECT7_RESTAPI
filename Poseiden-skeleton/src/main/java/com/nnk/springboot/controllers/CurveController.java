package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//import javax.validation.Valid;

@Controller
@CrossOrigin(origins="http://localhost:4200")
public class CurveController {

    @Autowired
    private CurvePointService curvePointService;

    Logger logger = LoggerFactory.getLogger(this.getClass());



    @RequestMapping("/curvePoint/list")
    @ResponseStatus(code = HttpStatus.OK)
    public String home(Model model)
    {
        model.addAttribute("curvePoints", curvePointService.getAllCurvePoints());
        logger.info("Liste des curvepoints chargée");

        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    @ResponseStatus(code = HttpStatus.OK)
    public String addCurvePoint(CurvePoint curvePoint) {
        logger.info("Page d'ajout des curvepoints chargée");

        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    @ResponseStatus(code = HttpStatus.OK)
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            curvePointService.saveNewCurvePoint(curvePoint);
            model.addAttribute("curvePoints", curvePointService.getAllCurvePoints());
            logger.info("Nouveau curvepoint ajouté");
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.getCurvePoint(id);
        model.addAttribute("curvePoint", curvePoint);
        logger.info("Page pour la mise à jour d'un rating curvepoint");
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                                   BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        curvePoint.setCurveId(id);
        curvePointService.saveNewCurvePoint(curvePoint);
        model.addAttribute("curvePoints", curvePointService.getAllCurvePoints());
        logger.info("Curvepoint avec l'id "+id+" mit à jour");
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        curvePointService.deleteCurvePoint(id);
        model.addAttribute("curvePoints", curvePointService.getAllCurvePoints());
        logger.info("curvepoint avec l'id "+id+" chargé");
        return "redirect:/curvePoint/list";
    }
}
