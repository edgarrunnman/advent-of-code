fun neighborsOf(x: Int, y: Int, levels: Int = 1): List<Pair<Int, Int>> =
    (-levels..levels).flatMap { dx ->
        (-levels..levels).mapNotNull { dy ->
            if (dx == 0 && dy == 0) null
            else (x + dx) to (y + dy)
        }
    }

