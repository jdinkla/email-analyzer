package net.dinkla.controller

import net.dinkla.email.EmailService
import net.dinkla.imap.EmailServerProperties
import net.dinkla.imap.EmailServerService
import net.dinkla.utils.Analyze
import org.apache.commons.lang.time.DateUtils
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.context.ApplicationContext
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

    @Value('${spring.data.elasticsearch.cluster-nodes}')
    String esNodes

    @Value('${emailanalyzer.index}')
    String esIndex

    @Value('${emailanalyzer.type}')
    String esType

    @RequestMapping(value = "/", method = RequestMethod.GET)
    def index(Model model) {
        logger.info("GET index")

        model.numLoaded = 0
        model.numberOfEmails = service.repository.count()
        model.analyze = new Analyze()
        model.esNodes = esNodes
        model.esIndex = esIndex
        model.esType = esType

        "index"
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    def importEdit(Model model) {
        logger.info("GET import")

        model.exception = false
        model.esNodes = esNodes
        model.esIndex = esIndex
        model.esType = esType
        model.emailServerProperties = new EmailServerProperties()

        "import"
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    def importRun(Model model, @Valid EmailServerProperties props, BindingResult bindingResult) {
        logger.info("POST import")
        logger.info("importRun: props=$props, bindingResult=$bindingResult")

        if (bindingResult.hasErrors()) {
            model.exception = false
            model.esNodes = esNodes
            model.esIndex = esIndex
            model.esType = esType
            model.emailServerProperties = props
            return "import"
        }

        // run the import
        Long numLoaded = 0
        try {
            numLoaded = emailServerService.importEmails(props)
        } catch (Exception e) {
            logger.error("Exception e=$e")
            model.exception = true
            model.exceptionText = e.toString()
            model.esNodes = esNodes
            model.esIndex = esIndex
            model.esType = esType
            model.emailServerProperties = props
            return "import"
        }

        // if successful
        // redirect to the start page
        /*
        model.numLoaded = 1234
        model.numberOfEmails = service.repository.count()
        model.analyze = new Analyze()
        model.esNodes = esNodes
        model.esIndex = esIndex
        model.esType = esType
        */

        return "redirect:/index?numLoaded=${numLoaded}"
    }


    /*
    @RequestMapping(value = "/importTest", method = RequestMethod.POST)
    def importTest(Model model, @Valid EmailServerProperties props, BindingResult result) {
        logger.info("importTest: result=$result")
        model.numLoaded = 0

        if (result.hasErrors()) {
            // handle errors
        }
        // test connection, then
        model.emailServer = props
        model.tested = true

        "index"
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    def importEmails(Model model, @Valid EmailServerProperties props, BindingResult result) {
        logger.info("importEmails: props=$props, result=$result")

        if (result.hasErrors()) {
            logger.warn("BindingResult has errors")
            // handle errors
        }

        model.emailServer = props
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

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    def error(Model model) {
        model.status = "Status"
        model.error = "Error"
        "error"
    }

    */

}
