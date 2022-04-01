package io.github.lostblackknight.hospital.controller;

import io.github.lostblackknight.model.entity.hospital.Hospital;
import io.github.lostblackknight.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 医院控制器
 *
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestController
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping("/hospitals")
    public void getHospitalByPage(@RequestParam(required = false) String pageNum,
                                  @RequestParam(required = false) String pageSize) {
    }

    @GetMapping("/hospitals/{hospitalCode}")
    public void getHospitalByHospitalCode(@PathVariable String hospitalCode) {

    }

    @PostMapping("/hospitals")
    public void createHospital(@RequestBody Hospital hospital) {

    }

    @PutMapping("/hospitals")
    public void updateHospital(@RequestBody Hospital hospital) {

    }

    @DeleteMapping("/hospitals/{hospitalCode}")
    public void removeHospital(@PathVariable String hospitalCode) {

    }
}
