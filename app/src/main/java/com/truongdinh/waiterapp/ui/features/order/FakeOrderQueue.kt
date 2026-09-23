package com.truongdinh.waiterapp.ui.features.order

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeOrderQueue @Inject constructor() {
    private val queue = ArrayDeque<Int>()

    @Synchronized
    fun enqueue(orderId: Int) {
        queue.addLast(orderId)
    }

    @Synchronized
    fun dequeue(): Int? {
        return queue.removeFirstOrNull()
    }
}