package net.dinkla.controller

import com.fasterxml.jackson.databind.ObjectMapper
import net.dinkla.email.EmailService
import net.dinkla.imap.EmailServerProperties
import net.dinkla.imap.EmailServerService
import net.dinkla.utils.Analyze
import net.dinkla.utils.Graph
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
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

        return "index"
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    def importEdit(Model model) {
        logger.info("GET import")

        model.exception = false
        model.esNodes = esNodes
        model.esIndex = esIndex
        model.esType = esType
        model.emailServerProperties = new EmailServerProperties()
        model.protocolOptions = emailServerService.getProviders()

        return "import"
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
            model.protocolOptions = emailServerService.getProviders()

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
            model.protocolOptions = emailServerService.getProviders()

            return "import"
        }

        // if successful, redirect to the start page
        return "redirect:/index?numLoaded=${numLoaded}"
    }

    @RequestMapping(value = "/analyse", method = RequestMethod.GET)
    def analyse(Model model, @Valid Analyze topics, BindingResult result) {
        logger.info("analyse topics=$topics")

        final def dates = ["2016-01-01 00:00:00.000000", "2016-02-01 00:00:00.000000", "2016-03-01 00:00:00.000000", "2016-04-01 00:00:00.000000"]

        def t0 = new Graph()
        t0.x = dates
        t0.y = [10, 15, 13, 17]
        t0.type = 'scatter'
        t0.name = 'Java'

        def t1 = new Graph()
        t1.x = dates
        t1.y = [16, 5, 11, 9]
        t1.type = 'scatter'
        t1.mode = 'lines+markers'
        t1.name = 'C++'

        //model.data = [t0, t1]
        ObjectMapper mapper = new ObjectMapper()
        model.data = [mapper.writeValueAsString(t0), mapper.writeValueAsString(t1)]
        "analyze"
    }


}
