package com.example.petmatch.view.screens.petdetail

import androidx.compose.runtime.Immutable

@Immutable
data class Pet (
    val id: Long = 0L,
    val name: String = "",
    val image_url: String = "",
    val tag: String = "",
    val description: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)


val pets = listOf(
    Pet(
        id = 1L,
        name = "Lolita",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1707177763.jpg",
        tag = "Loyal pet",
        description = "¡Meet Lolita, the lovely pet who is looking for a forever home! " +
                "Lolita is a faithful companion who has captivated everyone with her sweet " +
                "personality and unwavering loyalty. With her tender gaze and friendly nature," +
                " Lolita wins the hearts of all who have the pleasure of meeting her. This" +
                " adorable pet is ready to find a special place in your life and fill your " +
                "home with love and joy. Don't miss your chance to give Lolita the loving " +
                "home she deserves! If you're ready to open your heart and home to this " +
                "wonderful companion, contact us today to learn more about how you can " +
                "adopt Lolita and make her a part of your forever family. Lolita is " +
                "waiting for you with open arms to share unforgettable moments together.",
        latitude = 3.338658,
        longitude = -76.5494643
    ),
    Pet(
        id = 2L,
        name = "Simba",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651693211.jpg",
        tag = "Playful pet",
        description = "¡Discover Simba, the pet that will brighten your life with his playful" +
                " energy and charming personality! Simba is a loyal companion who is always" +
                " ready to explore the world with you and share moments full of fun and " +
                "adventure. His playful spirit and love of life make every day a new " +
                "adventure. Are you ready to embark on an exciting journey with Simba?"
        ,
        latitude = 3.3950644,
        longitude = -76.525664
    ),
    Pet(
        id = 3L,
        name = "Rayo",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651691628.jpg",
        tag = "Faithful pet",
        description = "¡Meet Rayo, the energetic mascot who will steal your heart with his" +
                " energetic character and contagious vitality! Rayo is a joy-filled companion" +
                " who is always ready to cheer you up with his enthusiasm and unconditional" +
                " love. If you are looking for an active and fun companion to share your days" +
                " with, Rayo is the perfect choice!!"
        ,
        latitude = 3.3697814,
        longitude = -76.53704
    ),
    Pet(
        id = 4L,
        name = "Toby",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651688168.jpg",
        tag = "Energy mascot",
        description = "¡Meet Toby, the faithful companion who will make every day special" +
                " with his unconditional affection and devotion. Toby is a loyal friend" +
                " who will be by your side every step of the way, providing you with" +
                " unparalleled love and companionship. With his loving nature and sweet" +
                " temperament, Toby will brighten your life and fill your home with love" +
                " and happiness. Are you ready to welcome Toby into your life and make" +
                " him part of your family?"
        ,
        latitude = 3.4100706,
        longitude = -76.5621871
    ),
    Pet(
        id = 5L,
        name = "Chispa",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651689459.jpg",
        tag = "Pet friendly",
        description = "¡Discover Chispa, the pet that will steal your heart with his" +
                " charm and irresistible charisma. Chispa is a loving companion who" +
                " is always ready to share his love and joy with you. With her friendly " +
                "personality and positive attitude, Chispa will make every day special" +
                " and fill your home with love and happiness. Get ready to fall in love" +
                " with Chispa and make her part of your family forever!!"
        ,
        latitude = 3.4091105,
        longitude = -76.5797172
    ),
    Pet(
        id = 6L,
        name = "Luna",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651275948.jpg",
        tag = "Protective pet",
        description = "¡Meet Luna, the pet that will accompany you at every moment with" +
                " her tenderness and protective spirit. Luna is a loyal companion who will" +
                " always be by your side, giving you love and support at all times. With" +
                " her tender gaze and kind heart, Luna will brighten your life and remind " +
                "you of the true meaning of unconditional love. Are you ready to open your" +
                " heart and home to Luna and make her part of your family forever?!"
        ,
        latitude = 3.4487506,
        longitude = -76.541014
    ),
    Pet(
        id = 7L,
        name = "Coco",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651692429.jpg",
        tag = "Smart pet",
        description = "¡Discover Coco, the smart and clever pet that will surprise you with" +
                " his wit and insightful character. Coco is a curious companion who is " +
                "always ready to explore the world and discover new adventures with you. " +
                "With her sharp mind and charming personality, Coco will make every day " +
                "exciting and full of fun. Get ready to share unforgettable moments together" +
                " with Coco and make her part of your life forever!!",
        latitude = 4.7986924,
        longitude = -75.29124
    ),
    Pet(
        id = 8L,
        name = "Max",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651689262.jpg",
        tag = "Obedient pet",
        description = "¡Meet Max, the obedient and loyal companion who will accompany you" +
                " every step of the way with his unconditional affection and devotion. Max" +
                " is a faithful friend who will always be by your side, giving you love" +
                " and companionship in good times and bad. With his affectionate nature" +
                " and kind heart, Max will make every day special and fill your home with" +
                " love and happiness. Get ready to welcome Max into your life and make " +
                "him part of your family forever!!"
        ,
        latitude = 3.4744071,
        longitude = -76.4833757
    ),
    Pet(
        id = 9L,
        name = "Oreo",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651692356.jpg",
        tag = "Loving pet",
        description = "¡Discover Oreo, the loving and affectionate pet that will win you over" +
                " with his tenderness and unmatched sweetness. Oreo is an affectionate " +
                "companion who is always ready to share his love and affection with you. " +
                "With his tender nature and kind heart, Oreo will make every day special " +
                "and fill your home with love and happiness. Get ready to fall in love with " +
                "Oreo and make him part of your family forever!!"
        ,
        latitude = 3.4896294,
        longitude = -76.5432513
    ),
    Pet(
        id = 10L,
        name = "Zoe",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651682963.jpg",
        tag = "Agile pet",
        description = "¡Meet Zoe, the agile and playful pet that will surprise you with" +
                " her vitality and adventurous spirit. Zoe is an energetic companion who " +
                "is always ready to explore the world with you and enjoy new experiences." +
                " With her contagious enthusiasm and charming personality, Zoe will make" +
                " every day exciting and fun-filled, so get ready to share unforgettable " +
                "moments with Zoe and make her a part of your life forever!!"
        ,
        latitude = 3.4691669,
        longitude = -76.4957139
    )
)