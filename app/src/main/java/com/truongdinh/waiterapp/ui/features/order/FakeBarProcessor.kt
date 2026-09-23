package com.truongdinh.waiterapp.ui.features.order

import android.os.Build
import androidx.annotation.RequiresApi
import com.truongdinh.waiterapp.data.repository.OrderRepository
import com.truongdinh.waiterapp.di.ApplicationScope
import com.truongdinh.waiterapp.domain.model.OrderStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@RequiresApi(Build.VERSION_CODES.O)
@Singleton
class FakeBarProcessor @Inject constructor(
    private val orderQueue: FakeOrderQueue,
    private val orderRepository: OrderRepository,
    @ApplicationScope private val applicationScope: CoroutineScope
) {
    fun start() {
        applicationScope.launch {
            while (true) {
                val orderId = orderQueue.dequeue()
                if (orderId != null) {
                    processNextOrder(orderId)
                }
                delay(2000)
            }
        }
    }

    private suspend fun processNextOrder(orderId: Int) {

        orderRepository.updateOrderStatus(orderId, OrderStatus.OPEN)

        delay(3000)
        orderRepository.updateOrderStatus(orderId, OrderStatus.PREPARING)
    }
}