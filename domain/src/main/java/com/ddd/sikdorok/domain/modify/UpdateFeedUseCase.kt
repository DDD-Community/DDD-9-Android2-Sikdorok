package com.ddd.sikdorok.domain.modify

import com.ddd.sikdorok.domain.repository.ModifyRepository
import com.ddd.sikdorok.shared.modify.FeedRequest
import javax.inject.Inject

class UpdateFeedUseCase @Inject constructor(
    private val modifyRepository: ModifyRepository
) {
    suspend operator fun invoke(path: String, body: FeedRequest) =
        modifyRepository.updateFeed(path, body)
}
