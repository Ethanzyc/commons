package com.ethanzyc.commons.base.filter;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.proxy.jdbc.ResultSetProxy;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import com.alibaba.druid.stat.JdbcDataSourceStat;
import com.alibaba.druid.stat.JdbcSqlStat;
import com.ethanzyc.commons.base.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;
import java.util.Date;

/**
 * @author ethan
 * @date 2019/8/25 23:59
 */
@Slf4j
public class PrintSqlFilter extends StatFilter {

    /**
     * sql 最大容忍时间，超过1秒会打error日志
     */
    private static final Long maxTime = 1000L;

    public PrintSqlFilter() {
        log.info("==========SlowSqlConfig实例化==============");
    }

    @Override
    protected void statementExecuteUpdateAfter(StatementProxy statement, String sql, int updateCount) {
        internalAfterStatementExecute(statement, false, updateCount);
    }

    @Override
    protected void statementExecuteQueryAfter(StatementProxy statement, String sql, ResultSetProxy resultSet) {
        internalAfterStatementExecute(statement, true);
    }

    @Override
    protected void statementExecuteAfter(StatementProxy statement, String sql, boolean firstResult) {
        internalAfterStatementExecute(statement, firstResult);
    }

    @Override
    protected void statementExecuteBatchAfter(StatementProxy statement, int[] result) {
        internalAfterStatementExecute(statement, false, result);

    }

    private final void internalAfterStatementExecute(StatementProxy statement, boolean firstResult,
                                                     int... updateCountArray) {

        final long nowNano = System.nanoTime();
        final long nanos = nowNano - statement.getLastExecuteStartNano();

        JdbcDataSourceStat dataSourceStat = statement.getConnectionProxy().getDirectDataSource().getDataSourceStat();
        dataSourceStat.getStatementStat().afterExecute(nanos);

        final JdbcSqlStat sqlStat = statement.getSqlStat();

        if (sqlStat != null) {

            long millis = nanos / (1000 * 1000);

            int parametersSize = statement.getParametersSize();

            String lastExecSql = statement.getLastExecuteSql();
            StringBuilder finalSql = new StringBuilder();

            if (parametersSize > 0) {

                String[] split = lastExecSql.split("\\?");
                finalSql.append(split[0]);

                for (int i = 0; i < parametersSize; ++i) {
                    Object value = statement.getParameter(i).getValue();
                    String str;

                    if (value == null) {
                        str="";
                    } else if (value instanceof String) {
                        String text = (String) value;
                        if (text.length() > 100) {
                            str = text.substring(0, 97) + "...";
                        } else {
                            str = text;
                        }
                    } else if (value instanceof Number) {
                        str = value.toString();
                    } else if (value instanceof java.util.Date) {
                        Date date = (Date) value;
                        str = DateUtil.format(date, DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS_SSS);
                    } else if (value instanceof Boolean) {
                        str = value.toString();
                    } else if (value instanceof InputStream) {
                        str = "<InputStream>";
                    } else if (value instanceof NClob) {
                        str = "<NClob>";
                    } else if (value instanceof Clob) {
                        str = "<Clob>";
                    } else if (value instanceof Blob) {
                        str = "<Blob>";
                    } else {
                        str = '<' + value.getClass().getName() + '>';
                    }

                    finalSql.append(str);

                    if (split.length > i + 1) {
                        finalSql.append(split[i+1]);
                    }
                }

            } else {
                finalSql.append(lastExecSql);
            }

            if (millis >= maxTime) {
                log.error("\n\ntime: " + millis + " ms -- 超过" + maxTime + "ms -- 需要优化\n" + finalSql + "\n");
            } else {
                log.info("\n\ntime: " + millis + " ms\n" + finalSql + "\n");
            }

            // todo 写一个线程池，把这些sql写到本地文件中，或者存入mongo中，秒数从配置文件中获取

        }
    }
}
