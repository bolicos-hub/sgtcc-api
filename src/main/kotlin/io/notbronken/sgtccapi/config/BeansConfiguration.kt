package io.notbronken.sgtccapi.config

import org.springframework.hateoas.config.HypermediaMappingInformation
import org.springframework.http.MediaType
import org.springframework.stereotype.Component

@Component
class BeansConfiguration {

}

//@Component
//class JsonHypermediaMappingInformation : HypermediaMappingInformation {
//
//    override fun getMediaTypes(): MutableList<MediaType> {
//        return mutableListOf(MediaType.APPLICATION_JSON)
//    }
//}