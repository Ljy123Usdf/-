package com.freightyard.project.business.cargoplanManagement.service.imp;

import com.freightyard.project.business.cargoplanManagement.dao.waybillDao;
import com.freightyard.project.business.cargoplanManagement.modal.waybillCondition;
import com.freightyard.project.business.cargoplanManagement.modal.waybillExcelTitle;
import com.freightyard.project.business.cargoplanManagement.service.waybillService;
import com.freightyard.project.business.common.dao.CargoWaybillMapper;
import com.freightyard.project.business.common.modal.CargoWaybill;
import com.freightyard.project.utils.PoiUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

@Service
public class waybillServiceImp implements waybillService {
    @Autowired
    CargoWaybillMapper cargoWaybillMapper;

    @Autowired
    waybillDao waybillDao;

    @Autowired
    waybillExcelTitle waybillExcelTitle;

    @Value("${ExcelFileName.waybillexcelname}")
    String waybillexcelname;

    @Override
    public PageInfo selectAllwaybillForpage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(cargoWaybillMapper.selectAll());
    }

    @Override
    public List<CargoWaybill> queryWaybillByCondition(waybillCondition waybillCondition) {
        return waybillDao.queryWaybillByCondition(waybillCondition);
    }

    @Override
    public void downloadwayBillTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String fileName = waybillexcelname;
        String userAgent = request.getHeader("User-Agent");
        boolean isMSIE = userAgent != null && (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("like Gecko") > -1);
        if (isMSIE) {
            fileName = URLEncoder.encode(fileName, "UTF8");
        } else {
            fileName = new String(fileName.getBytes("gbk"), "ISO8859-1");
        }
        response.setContentType("application/octet-stream");
        // 设置文件名
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + ".xls");
        ServletOutputStream out = response.getOutputStream();
        //从配置文件中获取表头信息
        List<String> list = waybillExcelTitle.getExcelTitle();
        //绘制表格
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheetlist = wb.createSheet("sheetlist");
        //设置列宽
        for (int i = 0; i < list.size(); i++) {
            sheetlist.setColumnWidth(i, 5000);
        }
        //表头格式
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //表格格式（只加边框）
        HSSFCellStyle cellStyle2 = wb.createCellStyle();
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        HSSFRow row = sheetlist.createRow(0);
        for (int j = 0; j < list.size(); j++) {
            HSSFCell cell = row.createCell(j);
            cell.setCellValue(list.get(j));
            cell.setCellStyle(cellStyle);
        }
        sheetlist = PoiUtil.setHSSFPrompt(sheetlist, "请输入十位运单号", "例如：1000000000", 1, 500, 0, 0);
        sheetlist = PoiUtil.setHSSFPrompt(sheetlist, "请输入重量", "例如：20.0", 1, 500, 6, 6);
        sheetlist = PoiUtil.setHSSFPrompt(sheetlist, "请输入体积", "例如：20.0", 1, 500, 7, 7);
        sheetlist = PoiUtil.setHSSFPrompt(sheetlist, "请输入运费", "例如：150.0", 1, 500, 8, 8);
        sheetlist = PoiUtil.setHSSFPrompt(sheetlist, "请输入日期", "例如：20.0", 1, 500, 11, 11);
        sheetlist = PoiUtil.setHSSFPrompt(sheetlist, "请输入日期", "例如：yyyy-mm-dd", 1, 500, 13, 13);
        PoiUtil.setColumnToTextFormat(wb,sheetlist,1,1,500,16);
        //写出excel文件
        wb.write(out);
        out.close();
    }

    @Override
    public Integer deleteManywaybills(List<Integer> cwidList) {
        return waybillDao.deleteManywaybills(cwidList);
    }


}
