
package com.example.githubtest.data.network.base

import com.example.githubtest.core.entity.CoreReceivedModel


interface MappableDTO<M : CoreReceivedModel> {
    fun map() : M
}