package com.example.githubtest.data.local.database.repos

import com.example.githubtest.core.entity.ReposItem
import com.example.githubtest.data.local.RepoDao

class FavoriteRepositoryImpl(val repoDao: RepoDao) : FavoriteRepository {
    override suspend fun getAll(): List<ReposItem> {
        return repoDao.getAll().map { it.getRepo() }
    }

    override suspend fun insert(repo: ReposItem) {
        repoDao.insert(repo.getEntity())
    }

    override suspend fun delete(repo: ReposItem) {
        repoDao.delete(repo.getEntity())
    }

    override suspend fun getRepoById(id: Int): ReposItem? {
        return repoDao.getRepoById(id)?.getRepo()
    }
}