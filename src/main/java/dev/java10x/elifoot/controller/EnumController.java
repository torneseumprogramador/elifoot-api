package dev.java10x.elifoot.controller;

import dev.java10x.elifoot.controller.response.PositionResponse;
import dev.java10x.elifoot.entity.Position;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/enums")
public class EnumController {

    @GetMapping("/positions")
    public List<PositionResponse> getPositions() {
        return Arrays.stream(Position.values())
                .map(position -> new PositionResponse(position.name(), position.getLabel()))
                .toList();
    }
}
