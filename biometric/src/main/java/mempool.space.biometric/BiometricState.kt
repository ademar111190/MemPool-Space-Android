package mempool.space.biometric

sealed class BiometricState {
    object Loading : BiometricState()

    data class Enrolled(
        val sessionAuthenticated: Boolean,
    ) : BiometricState()

    data class EnrolledButUnavailable(
        val reason: String,
    ) : BiometricState()

    object Available : BiometricState()

    data class Unavailable(
        val reason: String,
    ) : BiometricState()

    object AuthFailed : BiometricState()
}
