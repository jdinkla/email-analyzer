package net.dinkla.controller

import net.dinkla.email.EmailService
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Created by Dinkla on 12.05.2016.
 */
@Controller
class EmailController {

    static Log logger = LogFactory.getLog(EmailController.class)

    @Autowired
    EmailService service

    @RequestMapping(value = "/", method = RequestMethod.GET)
    def index(Model model) {
        logger.info("index")
        model.addAttribute("name", "the name is bunny")

        final long numberOfEmails = service.repository.count()
        model.addAttribute("numberOfEmails", numberOfEmails)

        "index"

    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    def importEmails(Model model) {
        logger.info("importEmails")
        println("model=$model")
        model["loaded"] = 123
        "index"
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    def deleteEmails(Model model) {
        logger.info("delete")
        "index"
    }

}
