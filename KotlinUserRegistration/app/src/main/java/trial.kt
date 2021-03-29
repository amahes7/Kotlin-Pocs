import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

suspend fun main() {
    val a: Flow<List<Int>> = combine(f1, f2, f3) { elements: Array<List<Int>> ->
        elements.flatMap { it }
    }
    a.collect { println(it) }
}

val f1 = flow {
    emit(listOf(1, 2))
}

val f2 = flow {
    emit(listOf(3, 4))
}

val f3 = flow {
    emit(listOf(5, 6))
}
