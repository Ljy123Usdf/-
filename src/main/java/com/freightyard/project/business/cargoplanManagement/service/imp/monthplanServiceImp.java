package com.freightyard.project.business.cargoplanManagement.service.imp;

import com.freightyard.project.business.cargoplanManagement.dao.MonthplanDao;
import com.freightyard.project.business.cargoplanManagement.modal.monthPlanCondition;
import com.freightyard.project.business.cargoplanManagement.modal.monthplanExcelTitle;
import com.freightyard.project.business.cargoplanManagement.service.monthplanService;
import com.freightyard.project.business.common.dao.MonthPlanMapper;
import com.freightyard.project.business.common.modal.MonthPlan;
import com.freightyard.project.utils.PoiUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class monthplanServiceImp implements monthplanService {

    @Value("${ExcelFileName.monthexcelname}")
    String monthplanfile;

    @Autowired
    monthplanExcelTitle monthplanExcelTitle;

    @Autowired
    private MonthPlanMapper monthPlanMapper;

    @Autowired
    MonthplanDao monthplanDao;

    @Override
    public PageInfo selectAllForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo(monthPlanMapper.selectAll());
    }

    @Override
    public Integer updateMonthplan(MonthPlan monthPlan) {
        return monthplanDao.updatemonthplan(monthPlan);
    }

    @Override
    public Integer deleteMonthplan(Integer mpid) {
        return monthplanDao.deletemonthplan(mpid);
    }

    @Override
    public List<MonthPlan> selectmonthplanBycondition(monthPlanCondition monthPlanCondition) {
        return monthplanDao.selectMonthplanByObject(monthPlanCondition);
    }

    @Override
    public Integer insertManyplans(List<MonthPlan> monthPlanList, MultipartFile multipartFile) throws Exception {

        InputStream inputStream = multipartFile.getInputStream();
        HSSFWorkbook wb = new HSSFWorkbook(inputStream);
        HSSFSheet sheetlist = wb.getSheet("sheetlist");
        int rowlNum = sheetlist.getLastRowNum();
        for (int i = 1; i<=rowlNum; i++) {
            HSSFRow row = sheetlist.getRow(i);

        }
        return 0;

    }

    @Override
    public void downloadMonthplanTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String fileName = monthplanfile;
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
        List<String> list = monthplanExcelTitle.getExcelTitle();
        //绘制表格
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheetlist = wb.createSheet("sheetlist");
        //设置列宽
        for (int i = 0; i < list.size(); i++) {
            sheetlist.setColumnWidth(i, 3766);
        }
        sheetlist.setColumnWidth(6, 6000);
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
        //日均（车）	净载重（t/车）	车数	运价（吨公里)	发送吨（开始发送时的重量)
        sheetlist = PoiUtil.setHSSFPrompt(sheetlist, "请输入数字", "例如：1", 1, 500, 1, 1);
        sheetlist = PoiUtil.setHSSFPrompt(sheetlist, "请输入每车净载数", "例如：20.0", 1, 500, 2, 2);
        sheetlist = PoiUtil.setHSSFPrompt(sheetlist, "请输入数字", "例如：20", 1, 500, 3, 3);
        sheetlist = PoiUtil.setHSSFPrompt(sheetlist, "输入运价", "例如：15.0", 1, 500, 4, 4);
        sheetlist = PoiUtil.setHSSFPrompt(sheetlist, "请输发货货物的重量", "例如：20.0", 1, 500, 5, 5);
        sheetlist = PoiUtil.setHSSFPrompt(sheetlist, "请输入日期", "例如：yyyy-mm-dd", 1, 500, 8, 8);
        PoiUtil.setColumnToTextFormat(wb,sheetlist,1,1,500,16);
        //写出excel文件
        wb.write(out);
        out.close();
    }
}
