package com.ddd.sikdorok.domain.modify

import com.ddd.sikdorok.shared.base.SikdorokResponse
import com.ddd.sikdorok.shared.modify.FeedRequest

interface ModifyRepository {
    suspend fun createFeed(file: String, body: FeedRequest): SikdorokResponse<String>

    suspend fun updateFeed(file: String, body: FeedRequest): SikdorokResponse<String>

    suspend fun deleteFeed(feedId: String): SikdorokResponse<Unit>
}
