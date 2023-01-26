package com.greencode.demo.service

import com.greencode.demo.model.Shape
import org.springframework.stereotype.Service

@Service
class DemoService {

    fun scanArea(area: List<List<Int>>): Shape {
        area.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, element ->
                // For each found '1', try searching for every possible rectangle that would have its top left corner here
                if (element == 1 && rowIndex != area.size && columnIndex != row.size) {
                    val maxHeight = area.size - rowIndex - 1
                    val maxWidth = row.size - columnIndex - 1
                    for (height in 1..maxHeight) {
                        for (width in 1..maxWidth) {
                            if (isRectangle(area, Pair(rowIndex, columnIndex), height, width)) {
                                // Square is just a special case of a rectangle
                                return if (height == width) Shape.SQUARE else Shape.RECTANGLE
                            }
                        }
                    }
                }
            }
        }
        return Shape.UNKNOWN
    }

    private fun isRectangle(area: List<List<Int>>, position: Pair<Int, Int>, height: Int, width: Int): Boolean {
        // What is a rectangle? Two straight lines, connected at their ends.

        // Let's check the side lines first
        for (x in 0..height) {
            if (area[position.first + x][position.second] != 1 ||
                area[position.first + x][position.second + width] != 1) {
                return false
            }
        }

        // Now check the connecting lines at the top and bottom
        for (y in 0..width) {
            if (area[position.first][position.second + y] != 1 ||
                area[position.first + height][position.second + y] != 1) {
                return false
            }
        }

        return true
    }
}
