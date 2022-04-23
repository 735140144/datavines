/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.datavines.server.coordinator.api.controller;

import io.datavines.common.dto.workspace.WorkSpaceCreate;
import io.datavines.common.dto.workspace.WorkSpaceUpdate;
import io.datavines.server.DataVinesConstants;
import io.datavines.server.coordinator.api.entity.ResultMap;
import io.datavines.server.coordinator.repository.service.WorkSpaceService;
import io.datavines.server.exception.DataVinesServerException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "/workspace", tags = "workspace", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = DataVinesConstants.BASE_API_PATH + "/workspace", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkSpaceController {

    @Autowired
    private WorkSpaceService workSpaceService;

    @ApiOperation(value = "create workspace")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object createWorkSpace(@RequestBody WorkSpaceCreate workSpaceCreate) throws DataVinesServerException {
        Map<String,Object> result = new HashMap<>();
        result.put("id", workSpaceService.insert(workSpaceCreate));
        return new ResultMap().success().payload(result);
    }

    @ApiOperation(value = "update workspace")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object updateWorkSpace(@RequestBody WorkSpaceUpdate workSpaceUpdate) throws DataVinesServerException {
        Map<String,Object> result = new HashMap<>();
        result.put("result", workSpaceService.update(workSpaceUpdate)>0);
        return new ResultMap().success().payload(result);
    }

    @ApiOperation(value = "delete workspace")
    @DeleteMapping(value = "/{id}")
    public Object deleteWorkSpace(@PathVariable Long id)  {
        Map<String,Object> result = new HashMap<>();
        result.put("result", workSpaceService.deleteById(id));
        return new ResultMap().success().payload(result);
    }

    @ApiOperation(value = "list workspace by user id")
    @GetMapping(value = "list/{id}")
    public Object listByUserId(@PathVariable Long id)  {
        Map<String,Object> result = new HashMap<>();
        result.put("list", workSpaceService.listByUserId(id));
        return new ResultMap().success().payload(result);
    }
}
