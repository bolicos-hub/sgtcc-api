package io.notbronken.sgtccapi.common.error

class BusinessException(message: String) : RuntimeException(message)
class BusinessEntityNotFoundException(message: String) : RuntimeException(message)