package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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


@Controller
@CrossOrigin(origins="http://localhost:4200")
public class TradeController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
   private TradeService tradeService;



    @GetMapping("/trade/list")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Trade> getTradeList(Model model)
    {
        model.addAttribute("trades",tradeService.getAllTrades());
        logger.info("Liste des trade chargée");
        return tradeService.getAllTrades();
    }

    @GetMapping("/trade/add")
    @ResponseStatus(code = HttpStatus.OK)
    public String addTrade(Trade trade) {
        logger.info("Page d'ajout des trades chargée");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    @ResponseStatus(code = HttpStatus.OK)
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            tradeService.saveTrade(trade);
            model.addAttribute("trades", tradeService.getAllTrades());
            logger.info("Nouveau trade ajouté");
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    @ResponseStatus(code = HttpStatus.OK)

    public String getTradeById(@PathVariable("id") Integer id, Model model) {
        // TODO: get Trade by Id and to model then show to the form
        model.addAttribute("trade",tradeService.getTradeById(id));
        logger.info("Page pour la mise à jour d'un trade chargée");
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        logger.info("Trade avec l'id "+id+" mit à jour");
        trade.setTradeId(id);
        Trade t = tradeService.getTradeById(id);
        t.setAccount(trade.getAccount());
        t.setBuyQuantity(trade.getBuyQuantity());
        t.setType(trade.getType());
        tradeService.saveTrade(t);
        model.addAttribute("trades", tradeService.getAllTrades());
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeService.deleteTrade(id);
        model.addAttribute("trades", tradeService.getAllTrades());
        logger.info("trade avec l'id "+id+" chargé");
        return "redirect:/trade/list";
    }
}
