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
package io.datavines.metric.result.formula;

import io.datavines.metric.api.MetricDirectionType;
import io.datavines.metric.api.ResultFormula;
import io.datavines.metric.api.ResultFormulaType;

import java.math.BigDecimal;

public class Count implements ResultFormula {

    @Override
    public String getName() {
        return "Actual";
    }

    @Override
    public String getZhName() {
        return "实际值";
    }

    @Override
    public BigDecimal getResult(BigDecimal actualValue, BigDecimal expectedValue) {
        return actualValue;
    }

    @Override
    public BigDecimal getScore(BigDecimal actualValue, BigDecimal expectedValue, boolean isSuccess, MetricDirectionType direction) {
        return isSuccess ? new BigDecimal(100) : new BigDecimal(0) ;
    }

    @Override
    public String getResultFormat(boolean isEn) {
        return isEn ? "Actual(${actual_value})" : "实际值(${actual_value})";
    }

    @Override
    public String getSymbol() {
        return "#";
    }

    @Override
    public ResultFormulaType getType() {
        return ResultFormulaType.VALUE;
    }
}
