package task3.flow

import io.mockk.coVerify
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import task3.model.MovementAction
import task3.model.SpeakingAction

@ExtendWith(value = [MockKExtension::class])
internal class MovementActionPerformerTest {
    private val performer: MovementActionPerformer = MovementActionPerformer()

    @ParameterizedTest
    @ValueSource(ints = [1, 3])
    fun `performMovementActionSaying calls movementAction and speakingAction N times`(n: Int) {
        val speakingAction = mockk<SpeakingAction>(relaxed = true)
        val movementAction = mockk<MovementAction>(relaxed = true)

        runBlocking {
            val job = performer.performMovementActionSaying(
                movementAction = movementAction,
                times = n,
                speakingAction = speakingAction,
                text = "text",
            )
            job.join()

            coVerify(exactly = n) {
                movementAction.perform()
            }

            coVerify(exactly = n) {
                speakingAction.perform("text")
            }
        }
    }

    @Test
    fun `performMovementActionSaying do not call movementAction and speakingAction when passed 0 times`() {
        val speakingAction = mockk<SpeakingAction>(relaxed = true)
        val movementAction = mockk<MovementAction>(relaxed = true)

        runBlocking {
            val job = performer.performMovementActionSaying(
                movementAction = movementAction,
                times = 0,
                speakingAction = speakingAction,
                text = "text",
            )
            job.join()

            coVerify(exactly = 0) {
                movementAction.perform()
            }

            coVerify(exactly = 0) {
                speakingAction.perform("text")
            }
        }
    }
}