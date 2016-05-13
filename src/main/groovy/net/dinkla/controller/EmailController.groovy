package net.dinkla.controller

import net.dinkla.email.EmailService
import net.dinkla.imap.EmailServerProperties
import net.dinkla.imap.EmailServerService
import net.dinkla.utils.Analyze
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import javax.validation.Valid

/**
 * Created by Dinkla on 12.05.2016.
 */
@Controller
class EmailController {

    static Log logger = LogFactory.getLog(EmailController.class)

    @Autowired
    EmailService service

    @Autowired
    EmailServerService emailServerService

    @RequestMapping(value = "/", method = RequestMethod.GET)
    def index(Model model) {
        logger.info("index")
        model.addAttribute("name", "the name is bunny")

        model.numLoaded = 0
        model.numberOfEmails = service.repository.count()

        "index"
    }

    @RequestMapping(value = "/importTest", method = RequestMethod.POST)
    def importTest(Model model, @Valid EmailServerProperties props, BindingResult result) {
        logger.info("importTest: result=$result")
        if (result.hasErrors()) {
            // handle errors
        }
        // test connection, then

        model.tested = true
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    def importEmails(Model model, @Valid EmailServerProperties props, BindingResult result) {
        logger.info("importEmails: result=$result")

        if (result.hasErrors()) {
            // handle errors
        }
        try {
            model.numLoaded = emailServerService.importEmails(props)
        } catch (Exception e) {
            logger.error("Exception e=$e")
            model.error = e.toString()
        }
        model.numberOfEmails = service.repository.count()

        "index"
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    def delete(Model model) {
        logger.info("delete")
        model.numLoaded = 0
        model.numberOfEmails = 0
//        model.numberOfEmails = service.repository.count()
        "redirect:/"
    }

    @RequestMapping(value = "/analyse", method = RequestMethod.POST)
    def analyse(Model model, @Valid Analyze topics, BindingResult result) {
        logger.info("analyse topics=$topics")
        model.numLoaded = 0
        model.numberOfEmails = 0
//        model.numberOfEmails = service.repository.count()
        "index"
    }



}
