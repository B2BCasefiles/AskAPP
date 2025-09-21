package com.example.retro_savings_challenge.navigation

object Routes {
    const val ONBOARDING = "onboarding"
    const val DASHBOARD = "dashboard"
    const val CHALLENGE_BROWSER = "challenge_browser"
    const val CHALLENGE_DETAILS = "challenge_details/{challengeId}"
    const val ADD_TRANSACTION = "add_transaction"

    fun challengeDetails(challengeId: String) = "challenge_details/$challengeId"
}
