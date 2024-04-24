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
        description = "¡Conoce a Lolita, la encantadora mascota que está buscando un hogar para" +
                " siempre! Lolita es una fiel compañera que ha cautivado a todos con su dulce" +
                " personalidad y lealtad inquebrantable. Con su mirada tierna y su carácter " +
                "amigable, Lolita se gana el corazón de todos los que tienen el placer de" +
                " conocerla. Esta adorable mascota está lista para encontrar un lugar especial " +
                "en tu vida y llenar tu hogar de amor y alegría. ¡No pierdas la oportunidad de" +
                " darle a Lolita el hogar amoroso que se merece! Si estás listo para abrir tu" +
                " corazón y tu hogar a esta maravillosa compañera, contáctanos hoy mismo para" +
                " conocer más sobre cómo puedes adoptar a Lolita y hacerla parte de tu familia" +
                " para siempre. ¡Lolita te espera con los brazos abiertos para compartir " +
                "momentos inolvidables juntos!",
        latitude = 3.338658,
        longitude = -76.5494643
    ),
    Pet(
        id = 2L,
        name = "Simba",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651693211.jpg",
        tag = "Playful pet",
        description = "¡Descubre a Simba, la mascota que iluminará tu vida con su energía" +
                " juguetona y su personalidad encantadora! Simba es un compañero leal que" +
                " siempre está listo para explorar el mundo contigo y compartir momentos " +
                "llenos de diversión y aventura. Su espíritu juguetón y su amor por la vida" +
                " hacen que cada día sea una nueva aventura. ¿Estás listo para embarcarte " +
                "en un viaje emocionante junto a Simba?"
        ,
        latitude = 3.3950644,
        longitude = -76.525664
    ),
    Pet(
        id = 3L,
        name = "Rayo",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651691628.jpg",
        tag = "Faithful pet",
        description = "¡Conoce a Rayo, la mascota llena de energía que te robará el corazón " +
                "con su carácter enérgico y su vitalidad contagiosa! Rayo es un compañero lleno " +
                "de alegría que siempre está listo para animarte con su entusiasmo y amor " +
                "incondicional. Si buscas un compañero activo y divertido para compartir tus " +
                "días, ¡Rayo es la elección perfecta!"
        ,
        latitude = 3.3697814,
        longitude = -76.53704
    ),
    Pet(
        id = 4L,
        name = "Toby",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651688168.jpg",
        tag = "Energy mascot",
        description = "¡Conoce a Toby, el fiel compañero que hará que cada día sea especial " +
                "con su afecto y devoción incondicional! Toby es un amigo leal que estará a" +
                " tu lado en cada momento, brindándote amor y compañía sin igual. Con su " +
                "naturaleza cariñosa y su dulce temperamento, Toby iluminará tu vida y llenará " +
                "tu hogar de amor y felicidad. ¿Estás listo para recibir a Toby en tu vida y " +
                "hacerlo parte de tu familia?"
        ,
        latitude = 3.4100706,
        longitude = -76.5621871
    ),
    Pet(
        id = 5L,
        name = "Chispa",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651689459.jpg",
        tag = "Pet friendly",
        description = "¡Descubre a Chispa, la mascota que te robará el corazón con su" +
                " encanto y carisma irresistible! Chispa es un compañero cariñoso que siempre" +
                " está listo para compartir su amor y alegría contigo. Con su personalidad " +
                "amigable y su actitud positiva, Chispa hará que cada día sea especial y " +
                "llenará tu hogar de amor y felicidad. ¡Prepárate para enamorarte de Chispa " +
                "y hacerla parte de tu familia para siempre!"
        ,
        latitude = 3.4091105,
        longitude = -76.5797172
    ),
    Pet(
        id = 6L,
        name = "Luna",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651275948.jpg",
        tag = "Protective pet",
        description = "¡Conoce a Luna, la mascota que te acompañará en cada momento con" +
                " su ternura y su espíritu protector! Luna es una compañera leal que " +
                "siempre estará a tu lado, brindándote amor y apoyo en todo momento. Con " +
                "su mirada tierna y su corazón bondadoso, Luna iluminará tu vida y te " +
                "recordará el verdadero significado del amor incondicional. ¿Estás listo " +
                "para abrir tu corazón y tu hogar a Luna y hacerla parte de tu familia " +
                "para siempre?"
        ,
        latitude = 3.4487506,
        longitude = -76.541014
    ),
    Pet(
        id = 7L,
        name = "Coco",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651692429.jpg",
        tag = "Smart pet",
        description = "¡Descubre a Coco, la mascota inteligente y astuta que te sorprenderá con" +
                " su ingenio y su carácter perspicaz! Coco es un compañero curioso que siempre " +
                "está listo para explorar el mundo y descubrir nuevas aventuras contigo. Con su " +
                "mente aguda y su personalidad encantadora, Coco hará que cada día sea emocionante" +
                " y lleno de diversión. ¡Prepárate para compartir momentos inolvidables junto " +
                "a Coco y hacerla parte de tu vida para siempre!",
        latitude = 4.7986924,
        longitude = -75.29124
    ),
    Pet(
        id = 8L,
        name = "Max",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651689262.jpg",
        tag = "Obedient pet",
        description = "¡Conoce a Max, el compañero obediente y leal que te acompañará en " +
                "cada paso del camino con su afecto y devoción incondicional! Max es un amigo" +
                " fiel que siempre estará a tu lado, brindándote amor y compañía en los buenos" +
                " y malos momentos. Con su naturaleza cariñosa y su corazón bondadoso, Max hará" +
                " que cada día sea especial y llenará tu hogar de amor y felicidad. ¡Prepárate" +
                " para recibir a Max en tu vida y hacerlo parte de tu familia para siempre!"
        ,
        latitude = 3.4744071,
        longitude = -76.4833757
    ),
    Pet(
        id = 9L,
        name = "Oreo",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651692356.jpg",
        tag = "Loving pet",
        description = "¡Descubre a Oreo, la mascota amorosa y cariñosa que te conquistará" +
                " con su ternura y su dulzura inigualable! Oreo es un compañero afectuoso " +
                "que siempre está listo para compartir su amor y afecto contigo. Con su " +
                "naturaleza tierna y su corazón bondadoso, Oreo hará que cada día sea especial" +
                " y llenará tu hogar de amor y felicidad. ¡Prepárate para enamorarte de Oreo" +
                " y hacerlo parte de tu familia para siempre!"
        ,
        latitude = 3.4896294,
        longitude = -76.5432513
    ),
    Pet(
        id = 10L,
        name = "Zoe",
        image_url = "https://www.fundacionanimalove.org/images/productos/1_1651682963.jpg",
        tag = "Agile pet",
        description = "¡Conoce a Zoe, la mascota ágil y juguetona que te sorprenderá con su" +
                " vitalidad y su espíritu aventurero! Zoe es una compañera llena de energía " +
                "que siempre está lista para explorar el mundo contigo y disfrutar de nuevas " +
                "experiencias. Con su entusiasmo contagioso y su personalidad encantadora, " +
                "Zoe hará que cada día sea emocionante y lleno de diversión. ¡Prepárate para " +
                "compartir momentos inolvidables junto a Zoe y hacerla parte de tu vida " +
                "para siempre!"
        ,
        latitude = 3.4691669,
        longitude = -76.4957139
    )
)