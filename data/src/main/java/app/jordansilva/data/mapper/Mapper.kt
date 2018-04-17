package com.unimedbh.prestador.data.mapper

interface Mapper<Domain, Model> {

    fun mapFromDomain(type: Domain): Model
    fun mapToDomain(type: Model): Domain
}