package exceptions

class AggressiveRateValidationException : RuntimeException(
    "Aggressive rate should be in range from 0.0 to 1.0"
)