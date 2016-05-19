package net.dinkla.controller

import com.fasterxml.jackson.databind.ObjectMapper
import net.dinkla.Constants
import net.dinkla.email.Email
import net.dinkla.email.EmailAddress
import net.dinkla.email.EmailService
import net.dinkla.imap.EmailServerProperties
import net.dinkla.imap.EmailServerService
import net.dinkla.utils.AnalyzeParameters
import net.dinkla.utils.Graph
import net.dinkla.utils.Histogram
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

import javax.validation.Valid
import java.text.SimpleDateFormat

/**
 * Created by Dinkla on 12.05.2016.
 */
@Controller
class EmailController {

    static final Log logger = LogFactory.getLog(EmailController.class)

    @Autowired
    EmailService service

    @Autowired
    EmailServerService emailServerService

    @Value('${spring.data.elasticsearch.cluster-nodes}')
    String esNodes

    final String esIndex = Constants.EMAIL_INDEX
    final String esType = Constants.EMAIL_TYPE

    @RequestMapping(value = "/", method = RequestMethod.GET)
    def index(Model model, @RequestParam(value = "numImported", required = false) Integer numImported) {
        logger.info("GET index numImported=$numImported")

        model.numLoaded = 0
        model.numberOfEmails = service.repository.count()
        model.analyzeParameters = new AnalyzeParameters()
        model.esNodes = esNodes
        model.esIndex = esIndex
        model.esType = esType
        model.numImported = numImported

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
        logger.info("POST importRun: props=$props, bindingResult=$bindingResult")

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
        Long numImported = 0
        try {
            numImported = emailServerService.importEmails(props)
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
        return "redirect:/?numImported=${numImported}"
    }

    @RequestMapping(value = "/analyze", method = RequestMethod.POST)
    def analyze(Model model, @Valid AnalyzeParameters params, BindingResult bindingResult) {
        logger.info("POST analyse: params=$params, bindingResult=$bindingResult")

        if (bindingResult.hasErrors()) {
            model.exception = false
            model.analyzeParameters = params
            model.keywords = params.topics
            return "analyze"
        }

        // query the database
        ObjectMapper mapper = new ObjectMapper()
        def jsons = []
        try {
            def topics = params.split()
            for (String topic : topics) {
                final Histogram<String, Integer> hist = service.getWeeklyHistogram(topic)
                def graph = new Graph<String, Integer>(hist)
                def json = mapper.writeValueAsString(graph)
                jsons.add json
            }
        } catch (Exception e) {
            logger.error("Exception e=$e")
            model.exception = true
            model.exceptionText = e.toString()
            model.analyzeParameters = params
            model.keywords = params.topics
            return "analyze"
        }

        // if a result is returned
        model.analyzeParameters = new AnalyzeParameters()
        model.data = jsons
        model.keywords = params.topics

        return "analyze"
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    def delete(Model model) {

        service.deleteAll();
        return "redirect:/"
    }

    @RequestMapping(value = "/debug_create", method = RequestMethod.GET)
    def debugCreate(Model model, @Valid AnalyzeParameters topics, BindingResult result) {
        Email em = new Email()
        em.addFroms(new EmailAddress('dubdi@dibdi.dub'))
        em.addFroms(new EmailAddress('dibdi@dubdi.dub'))
        em.subject = 'Subject1'
        em.texts = [ "Howdy" ]
        def sdf = new SimpleDateFormat("yyyy-MM-dd")
        em.sentDate = sdf.parse('2016-01-01')
        em.receivedDate = sdf.parse('2016-01-01')
        em.addRecipient(new EmailAddress('to@to.to'))
        em.addRecipient(new EmailAddress('cc@cc.cc'))

        service.add(em)

        return "redirect:/"
    }

}
