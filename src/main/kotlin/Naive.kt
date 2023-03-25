import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class Naive {
    class Fork {
        var owner: Philosopher? = null
    }

    class Philosopher(val leftFork: Fork, val rightFork: Fork) {

        fun take(fork: Fork): Boolean {
            if (fork.owner == null) {
                fork.owner = this
                return true
            }
            return false
        }

        fun putOn(fork: Fork) {
            fork.owner = null
        }

        suspend fun tryToEat() = coroutineScope {
            var count = 0
            while (!take(leftFork)) {
                delay(4000L)
                count += 1
                if (count > 10) throw Exception("BLOCK")
            }
            delay(1000L)
            count = 0
            while (!take(rightFork)) {
                delay(4000L)
                count += 1
                if (count > 10) throw Exception("BLOCK")
            }
            delay(4000L)
            putOn(rightFork)
            delay(1000L)
            putOn(leftFork)
        }
    }

    suspend fun task(n: Int) = coroutineScope {
        val forks = mutableListOf<Fork>()
        for (i in 1..n) {
            val fork = Fork()
            forks.add(fork)
        }
        val philosophers = mutableListOf<Philosopher>()
        for (i in 1..n) {
            val philosopher = Philosopher(forks[i % n], forks[(i + 1) % n])
            philosophers.add(philosopher)
        }
        val jobs = mutableListOf<Job>()
        for (philosopher in philosophers) {
            jobs.add(launch { philosopher.tryToEat() })
        }
        jobs.forEach { it.start() }
    }
}
