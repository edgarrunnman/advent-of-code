package shared

import DataFetcher

class DataFetcherMock(override var fetched: List<String>) : DataFetcher {
    override fun getPuzzleInput(year: Int, day: Int): List<String> = fetched
}
