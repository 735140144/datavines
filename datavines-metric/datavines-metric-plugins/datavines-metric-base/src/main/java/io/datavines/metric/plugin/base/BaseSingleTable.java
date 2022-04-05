package io.datavines.metric.plugin.base;

import io.datavines.common.entity.ExecuteSql;
import io.datavines.common.utils.JSONUtils;
import io.datavines.metric.api.SqlMetric;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseSingleTable implements SqlMetric {

    private StringBuilder invalidateItemsSql = new StringBuilder("select * from ${src_table}");

    private StringBuilder actualValueSql = new StringBuilder("select count(*) as actual_value from ${invalidate_items_table}");

    protected List<String> filters = new ArrayList<>();

    @Override
    public ExecuteSql getInvalidateItems() {
        ExecuteSql executeSql = new ExecuteSql();
        executeSql.setResultTable("invalidate_items");
        executeSql.setSql(invalidateItemsSql.toString());
        executeSql.setErrorOutput(isInvalidateItemsCanOutput());
        return executeSql;
    }

    @Override
    public ExecuteSql getActualValue() {
        ExecuteSql executeSql = new ExecuteSql();
        executeSql.setResultTable("invalidate_count");
        executeSql.setSql(actualValueSql.toString());
        executeSql.setErrorOutput(false);
        return executeSql;
    }

    @Override
    public List<String> getConfigList() {

        List<String> list = new ArrayList<>();
        list.add("src_table");
        list.add("src_filter");
        list.add("src_column");
        return list;
    }

    @Override
    public void prepare(Map<String, String> config) {
        if (config.containsKey("src_filter")) {
            filters.add(config.get("src_filter"));
        }
    }

    protected void addFiltersIntoInvalidateItemsSql() {
        if (filters.size() > 0) {
            System.out.println(JSONUtils.toJsonString(filters));
            invalidateItemsSql.append(" where ").append(String.join(" and ", filters));
        }
    }
}