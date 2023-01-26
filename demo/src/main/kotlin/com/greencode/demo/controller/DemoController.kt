package com.greencode.demo.controller

import com.greencode.demo.model.dto.ScanRequestDto
import com.greencode.demo.model.dto.ScanResponseDto
import com.greencode.demo.model.Shape
import com.greencode.demo.service.DemoService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody


@RestController
class DemoController(
    private val demoService: DemoService,
) {

    @PostMapping(value = ["/demo/scan"])
    fun scanArea(@RequestBody scanRequest: ScanRequestDto): ResponseEntity<ScanResponseDto> {
        val scannedShape = demoService.scanArea(scanRequest.area)
        return ResponseEntity.ok(
            ScanResponseDto(
                found = scannedShape != Shape.UNKNOWN,
                shape = scannedShape.serialName
            )
        )
    }
}
