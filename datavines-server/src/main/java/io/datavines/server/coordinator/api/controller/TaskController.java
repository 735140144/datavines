package io.datavines.server.coordinator.api.controller;

import javax.validation.Valid;

import io.datavines.server.coordinator.api.entity.ResultMap;
import io.datavines.server.coordinator.repository.service.TaskResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import io.datavines.server.DataVinesConstants;

import io.datavines.server.coordinator.api.dto.task.SubmitTask;
import io.datavines.server.coordinator.repository.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

@Api(value = "/task", tags = "task", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@RequestMapping(value = DataVinesConstants.BASE_API_PATH + "/task", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskResultService taskResultService;

    @ApiOperation(value = "submit task")
    @PostMapping(value = "/submit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object submitTask(@Valid @RequestBody SubmitTask submitTask) {
        Map<String,Object> result = new HashMap<>();
        result.put("taskId",taskService.submitTask(submitTask));
        return new ResultMap().success().payload(result);
    }

    @ApiOperation(value = "submit task")
    @DeleteMapping(value = "/kill/{id}")
    public Object killTask(@PathVariable("id") Long taskId) {
        Map<String,Object> result = new HashMap<>();
        result.put("taskId",taskService.killTask(taskId));
        return new ResultMap().success().payload(result);
    }

    @ApiOperation(value = "get task status")
    @GetMapping(value = "/status/{id}")
    public Object getTaskStatus(@PathVariable("id") Long taskId) {
        Map<String,Object> result = new HashMap<>();
        result.put("taskStatus",taskService.getById(taskId).getStatus());
        return new ResultMap().success().payload(result);
    }

    @ApiOperation(value = "get task status")
    @GetMapping(value = "result/{id}")
    public Object getTaskResultInfo(@PathVariable("id") Long taskId) {
        Map<String,Object> result = new HashMap<>();
        result.put("taskResult", taskResultService.getByTaskId(taskId));
        return new ResultMap().success().payload(result);
    }

    @ApiOperation(value = "get task status")
    @DeleteMapping(value = "result/{id}")
    public Object deleteTaskResultInfo(@PathVariable("id") Long taskId) {
        Map<String,Object> result = new HashMap<>();
        result.put("result", taskResultService.deleteByTaskId(taskId));
        return new ResultMap().success().payload(result);
    }

}
