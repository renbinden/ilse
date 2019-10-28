package uk.co.renbinden.ilse.tiled


class Polygon(points: List<Pair<Double, Double>>) {
    val points = if (points.isEmpty()) listOf(Pair(0, 0)) else points
}