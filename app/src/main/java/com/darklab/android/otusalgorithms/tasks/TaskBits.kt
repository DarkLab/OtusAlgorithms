package com.darklab.android.otusalgorithms.tasks

class TaskBits() : ITask {
    override val rootPath: String
        get() = "taskbits"

    override fun run(data: Array<String>): String {
        return try {
            calculatePossibleMoves(data.first().toInt()).let {
                "${it.first}\r\n${it.second}"
            }
        } catch (e: Exception) {
            "Ошибка данных ${e.localizedMessage ?: ""}"
        }
    }

    private fun calculatePossibleMoves(startPosition: Int): Pair<Int, ULong> {
        return kingMoves(startPosition)
    }

    private val kingMoves: (Int) -> Pair<Int, ULong> = { position ->
        val k = 1UL shl position
        val kNoA = k and 0xfefefefefefefefeUL
        val kNoH = k and 0x7f7f7f7f7f7f7f7fUL
        val res = (kNoH shl 1) or (kNoA shr 1) or
                (k shl 8) or (k shr 8) or
                (kNoA shl 7) or (kNoH shr 7) or
                (kNoH shl 9) or (kNoA shr 9)
        res.countOneBits() to res
    }

    private val bishopMoves: (Int) -> Pair<Int, ULong> = { position ->
        3 to 770u
    }

    private val knightMoves: (Int) -> Pair<Int, ULong> = { position ->
        3 to 770u
    }

    private val rookMoves: (Int) -> Pair<Int, ULong> = { position ->
        3 to 770u
    }

    private val queenMoves: (Int) -> Pair<Int, ULong> = { position ->
        3 to 770u
    }

    val possibleMoves = arrayOf(
        kingMoves,
        bishopMoves,
        knightMoves,
        rookMoves,
        queenMoves
    )
}