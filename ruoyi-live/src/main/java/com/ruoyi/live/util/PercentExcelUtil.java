package com.ruoyi.live.util;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.poi.ss.usermodel.*;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 方案2：导出时把指定字段显示为百分比格式（0.00%）
 * 要求：字段值仍为 0~1 的数值（BigDecimal/Double）
 */
public class PercentExcelUtil<T> extends ExcelUtil<T> {

    /** 需要显示为百分比的字段名 */
    private final Set<String> percentFieldNames = new HashSet<>();

    /** 样式缓存：避免每个单元格都 createCellStyle（POI 样式数量有限） */
    private final Map<Short, CellStyle> percentStyleCache = new HashMap<>();

    /** 百分比格式：0.00% */
    private String percentFormat = "0.00%";

    public PercentExcelUtil(Class<T> clazz) {
        super(clazz);
    }

    public PercentExcelUtil<T> percentFields(String... fieldNames) {
        if (fieldNames != null) {
            percentFieldNames.addAll(Arrays.asList(fieldNames));
        }
        return this;
    }

    public PercentExcelUtil<T> percentFormat(String fmt) {
        if (fmt != null && !fmt.isBlank()) {
            this.percentFormat = fmt;
        }
        return this;
    }

    @Override
    public Cell addCell(Excel attr, Row row, T vo, Field field, int column) {
        // 先走父类，把值正常写入（数值 0~1）
        Cell cell = super.addCell(attr, row, vo, field, column);

        if (cell == null) {
            return null;
        }

        // 只对指定字段套百分比格式
        if (!percentFieldNames.contains(field.getName())) {
            return cell;
        }

        // 只对数值型单元格处理（避免文本/图片等）
        // 注意：BigDecimal 在父类里会 setCellValue(double)，最终 cellType 会是 NUMERIC
        if (cell.getCellType() != CellType.NUMERIC) {
            return cell;
        }

        CellStyle base = cell.getCellStyle();
        short baseIdx = base.getIndex();

        CellStyle percentStyle = percentStyleCache.get(baseIdx);
        if (percentStyle == null) {
            Workbook wb = row.getSheet().getWorkbook();
            percentStyle = wb.createCellStyle();
            percentStyle.cloneStyleFrom(base);

            DataFormat df = wb.createDataFormat();
            percentStyle.setDataFormat(df.getFormat(percentFormat));

            percentStyleCache.put(baseIdx, percentStyle);
        }

        cell.setCellStyle(percentStyle);
        return cell;
    }
}
