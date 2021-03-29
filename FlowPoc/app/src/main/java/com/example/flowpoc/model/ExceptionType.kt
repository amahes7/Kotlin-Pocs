package com.example.flowpoc.model

enum class ExceptionType constructor(val serviceException: ServiceException) {

    NETWORK_ERROR(ServiceException(201, "201", "Sorry. Internet might not be connected.")),
    GENERIC_ERROR(ServiceException(101, "101", "Sorry. Something went wrong."))
}