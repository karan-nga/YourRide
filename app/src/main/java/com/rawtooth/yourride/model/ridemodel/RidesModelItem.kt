package com.rawtooth.yourride.model.ridemodel

data class RidesModelItem(
    val city: String,
    val date: String,
    val destination_station_code: Int,
    val id: Int,
    val map_url: String,
    val origin_station_code: Int,
    val state: String,
    val station_path: List<Int>,
    var nearestStation : Int,
    var difference : Int
) : Comparable<RidesModelItem> {
    // [31, 41, 56, 62, 71, 85]
    fun findClosest(target: Int): Int {
        val n = station_path.size
        var i = 0
        var j = n-1
        var mid = 0

        while (i <= j) {
            mid = (i + j) / 2
            if (station_path[mid] == target) return station_path[mid]
            if (target < station_path[mid]) {
                j = mid - 1
            } else {
                i = mid + 1
            }
        }
        nearestStation = station_path[mid]
        difference = Math.abs(nearestStation - target)
        return station_path[mid]
    }

    override fun compareTo(other: RidesModelItem): Int {
        return difference-other.difference
    }
}

