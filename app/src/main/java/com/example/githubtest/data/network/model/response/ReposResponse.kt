package com.example.githubtest.data.network.model.response

import com.example.githubtest.core.entity.Repos
import com.example.githubtest.data.network.model.dto.ReposResponseItem
import com.example.githubtest.data.network.base.MappableDTO

class ReposResponse : ArrayList<ReposResponseItem>(), MappableDTO<Repos> {
    override fun map(): Repos {
        val repos = Repos()
        forEach {
            repos.add(it.map())
        }
        return repos
    }
}