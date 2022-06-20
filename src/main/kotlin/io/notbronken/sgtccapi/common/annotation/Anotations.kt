package io.notbronken.sgtccapi.common.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class NoArgCompositeId

@RequestMapping(
    consumes = [MediaType.APPLICATION_JSON_VALUE],
    produces = [MediaType.APPLICATION_JSON_VALUE],
    method = [RequestMethod.GET]
)
@ResponseStatus(code = HttpStatus.CREATED)
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class MappingJson(
    /**
     * Alias for [RequestMapping.name].
     */
    @get:AliasFor(annotation = RequestMapping::class)
    val name: String = "",

    /**
     * Alias for [RequestMapping.value].
     */
    @get:AliasFor(annotation = RequestMapping::class)
    val value: Array<String> = [],

    /**
     * Alias for [RequestMapping.path].
     */
    @get:AliasFor(annotation = RequestMapping::class)
    val path: Array<String> = [],

    /**
     * Alias for [RequestMapping.method].
     */
    @get:AliasFor(annotation = RequestMapping::class)
    val method: Array<RequestMethod> = [],

    /**
     * Alias for [RequestMapping.params].
     */
    @get:AliasFor(annotation = RequestMapping::class)
    val params: Array<String> = [],

    /**
     * Alias for [RequestMapping.headers].
     */
    @get:AliasFor(annotation = RequestMapping::class)
    val headers: Array<String> = [],

    /**
     * Alias for [ResponseStatus.code].
     */
    @get:AliasFor(annotation = ResponseStatus::class)
    val code: HttpStatus = HttpStatus.CREATED,
)