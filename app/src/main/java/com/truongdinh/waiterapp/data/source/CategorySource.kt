package com.truongdinh.waiterapp.data.source

import com.truongdinh.waiterapp.domain.model.Category

object CategorySource {
    val categories = listOf(
        Category(1, "Cà phê"),
        Category(2, "Trà"),
        Category(3, "Sinh tố"),
        Category(4, "Nước ép"),
        Category(5, "Tráng miệng")
    )
}