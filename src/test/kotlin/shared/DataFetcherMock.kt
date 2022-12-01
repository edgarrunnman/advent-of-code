package shared

import DataFetcher

class DataFetcherMock(override var fetched: String) : DataFetcher {
    override fun getPuzzleInput(year: Int, day: Int): String = fetched
}
