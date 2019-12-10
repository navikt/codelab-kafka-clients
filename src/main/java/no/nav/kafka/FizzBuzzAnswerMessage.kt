package no.nav.kafka

data class FizzBuzzAnswerMessage(val answer: String, val candidate: Int, val groupId: String)