package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
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
public class BidListController {

   @Autowired
   private BidListService bidListService;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("/bidList/list")
    @ResponseStatus(code = HttpStatus.OK)
    public String home(Model model)
    {
        model.addAttribute("bidLists", bidListService.getAllBidList());
        logger.info("Liste des bidlist chargée");

        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    @ResponseStatus(code = HttpStatus.OK)
    public String addBidList(BidList bidList) {
        logger.info("Page d'ajout des bidlist chargée");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    @ResponseStatus(code = HttpStatus.OK)
    public String validate(@Valid BidList bidList, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            bidListService.saveNewBidList(bidList);
            model.addAttribute("bidLists", bidListService.getAllBidList());
            logger.info("Nouvelle bidlist ajouté");
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.getBidListById(id);
        model.addAttribute("bidList", bidList);
        logger.info("Page pour la mise à jour d'un rating bidlist");
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String updateBidList(@PathVariable("id") Integer id, @Valid BidList bidList,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/update";
        }
        bidList.setBid(id);
        bidListService.saveNewBidList(bidList);
        model.addAttribute("bidLists", bidListService.getAllBidList());
        logger.info("Bidlist avec l'id "+id+" mit à jour");
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String deleteBidList(@PathVariable("id") Integer id, Model model) {
        bidListService.deleteBidList(id);
        model.addAttribute("bidLists", bidListService.getAllBidList());
        logger.info("Bidlist avec l'id "+id+" chargé");
        return "redirect:/bidList/list";
    }
}
