package com.truongdinh.waiterapp.data.source

import com.truongdinh.waiterapp.domain.model.MenuItem

object MenuItemSource {
    val menuItems = listOf(
        // Cà phê
        MenuItem(
            id = 1,
            name = "Cà phê đen",
            price = 25000L,
            image = ImageSource.BLACK_COFFEE,
            categoryId = 1,
            isAvailable = true
        ),
        MenuItem(
            id = 2,
            name = "Cà phê sữa",
            price = 27000L,
            image = ImageSource.MILK_COFFEE,
            categoryId = 1,
            isAvailable = true
        ),
        MenuItem(
            id = 3,
            name = "Bạc xỉu",
            price = 32000L,
            image = ImageSource.ICEE_COFFEE_WITH_CONDENSED_MILK,
            categoryId = 1,
            isAvailable = true
        ),
        MenuItem(
            id = 4,
            name = "Cà phê muối",
            price = 35000L,
            image = ImageSource.SALT_COFFEE,
            categoryId = 1,
            isAvailable = true
        ),
        MenuItem(
            id = 5,
            name = "Cà phê trứng",
            price = 35000L,
            image = ImageSource.EGG_COFFEE,
            categoryId = 1,
            isAvailable = true
        ),
        MenuItem(
            id = 6,
            name = "Cà phê dừa",
            price = 35000L,
            image = ImageSource.COCONUT_COFFEE,
            categoryId = 1,
            isAvailable = true
        ),
        MenuItem(
            id = 7,
            name = "Cà phê sữa chua",
            price = 27000L,
            image = ImageSource.YOGURT_COFFEE,
            categoryId = 1,
            isAvailable = true
        ),

        // Trà
        MenuItem(
            id = 8,
            name = "Trà đào cam sả",
            price = 35000L,
            image = ImageSource.PEACH_ORANGE_LEMONGRASS_TEA,
            categoryId = 2,
            isAvailable = true
        ),
        MenuItem(
            id = 9,
            name = "Trà đào",
            price = 35000L,
            image = ImageSource.PEACH_TEA,
            categoryId = 2,
            isAvailable = true
        ),
        MenuItem(
            id = 10,
            name = "Trà gừng",
            price = 35000L,
            image = ImageSource.GINGER_TEA,
            categoryId = 2,
            isAvailable = true
        ),
        MenuItem(
            id = 11,
            name = "Trà sen vàng",
            price = 35000L,
            image = ImageSource.GOLDEN_LOTUS_TEA,
            categoryId = 2,
            isAvailable = true
        ),
        MenuItem(
            id = 12,
            name = "Trà atiso",
            price = 35000L,
            image = ImageSource.ARTICHOKE_TEA,
            categoryId = 2,
            isAvailable = true
        ),
        MenuItem(
            id = 13,
            name = "Trà chanh",
            price = 35000L,
            image = ImageSource.LEMON_TEA,
            categoryId = 2,
            isAvailable = true
        ),
        MenuItem(
            id = 14,
            name = "Trà vải",
            price = 35000L,
            image = ImageSource.LYCHEE_TEA,
            categoryId = 2,
            isAvailable = true
        ),

        // Sinh tố
        MenuItem(
            id = 15,
            name = "Sinh tố bơ",
            price = 32000L,
            image = ImageSource.AVOCADO_SMOOTHIE,
            categoryId = 3,
            isAvailable = true
        ),
        MenuItem(
            id = 16,
            name = "Sinh tố chuối",
            price = 32000L,
            image = ImageSource.BANANA_SMOOTHIE,
            categoryId = 3,
            isAvailable = true
        ),
        MenuItem(
            id = 17,
            name = "Sinh tố dâu",
            price = 35000L,
            image = ImageSource.STRAWBERRY_SMOOTHIE,
            categoryId = 3,
            isAvailable = true
        ),
        MenuItem(
            id = 18,
            name = "Sinh tố dưa hấu",
            price = 32000L,
            image = ImageSource.WATER_MELON_SMOOTHIE,
            categoryId = 3,
            isAvailable = true
        ),
        MenuItem(
            id = 19,
            name = "Sinh tố xoài",
            price = 32000L,
            image = ImageSource.MANGO_SMOOTHIE,
            categoryId = 3,
            isAvailable = true
        ),
        MenuItem(
            id = 20,
            name = "Sinh tố mãng cầu",
            price = 35000L,
            image = ImageSource.CUSTARD_APPLE_SMOOTHIE,
            categoryId = 3,
            isAvailable = true
        ),

        // Nước ép
        MenuItem(
            id = 21,
            name = "Nước ép cam",
            price = 32000L,
            image = ImageSource.ORANGE_JUICE,
            categoryId = 4,
            isAvailable = true
        ),
        MenuItem(
            id = 22,
            name = "Nước cà rốt",
            price = 32000L,
            image = ImageSource.CARROT_JUICE,
            categoryId = 4,
            isAvailable = true
        ),
        MenuItem(
            id = 23,
            name = "Nước ép dứa",
            price = 32000L,
            image = ImageSource.PINEAPPLE_JUICE,
            categoryId = 4,
            isAvailable = true
        ),
        MenuItem(
            id = 24,
            name = "Nước ép táo",
            price = 32000L,
            image = ImageSource.APPLE_JUICE,
            categoryId = 4,
            isAvailable = true
        ),
        MenuItem(
            id = 25,
            name = "Nước ép cần tây",
            price = 32000L,
            image = ImageSource.CELERY_JUICE,
            categoryId = 4,
            isAvailable = true
        ),

        // Tráng miệng
        MenuItem(
            id = 26,
            name = "Bánh flan",
            price = 20000L,
            image = ImageSource.FLAN,
            categoryId = 5,
            isAvailable = true
        ),
        MenuItem(
            id = 27,
            name = "Bánh pateso",
            price = 25000L,
            image = ImageSource.PATE_CHAUD,
            categoryId = 5,
            isAvailable = true
        ),
        MenuItem(
            id = 28,
            name = "Bánh su kem",
            price = 15000L,
            image = ImageSource.CREAM_PUFF,
            categoryId = 5,
            isAvailable = true
        ),
        MenuItem(
            id = 29,
            name = "Bánh tiramisu",
            price = 30000L,
            image = ImageSource.TIRAMISU,
            categoryId = 5,
            isAvailable = true
        ),
        MenuItem(
            id = 30,
            name = "Bánh cheese",
            price = 35000L,
            image = ImageSource.CHEESECAKE,
            categoryId = 5,
            isAvailable = true
        )
    )
}