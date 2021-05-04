package com.example.multiapp.shared

import com.example.multiapp.shared.cache.Database
import com.example.multiapp.shared.cache.DatabaseDriverFactory
import com.example.multiapp.shared.entity.RocketLaunch
import com.example.multiapp.shared.network.SpaceXApi

class SpaceXSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = SpaceXApi()

    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNullOrEmpty() && !forceReload){
            cachedLaunches
        }else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }
}