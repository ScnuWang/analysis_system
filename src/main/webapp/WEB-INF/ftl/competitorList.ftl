<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>智能硬件|极客视界</title>
    <link rel="icon" type="image/png" href="https://s3.cn-north-1.amazonaws.com.cn/official/common1.0/images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="https://s3.cn-north-1.amazonaws.com.cn/index/pc/pc_index0.8/css/common.css" />
    <link rel="stylesheet" type="text/css" href="https://s3.cn-north-1.amazonaws.com.cn/index/pc/pc_index0.8/css/base.css" />
    <link rel="stylesheet" type="text/css" href="https://s3.cn-north-1.amazonaws.com.cn/index/pc/pc_index0.8/css/daily_list.css" />
    <script src="https://s3.cn-north-1.amazonaws.com.cn/index/pc/pc_index0.8/js/jquery.js"></script>
</head>
<body>
<div class="list__cxt">
    <div class="cxt__box clearfix">
        <div class="cxt__table_wrap cxt__table_list">
            <table border="0" cellspacing="0" cellpadding="0" width="100%" id="table_list">
                <thead>
                    <tr>
                      <th>二级分类编号</th>
                      <th>二级分类名称</th>
                      <th>最高的产品金额</th>
                      <th>最低的产品金额</th>
                      <th>最高的支持人数</th>
                      <th>支持人数最多的产品的目标价格</th>
                      <th>最低的支持人数</th>
                      <th>最高的目标价格</th>
                      <th>最低的目标价格</th>
                    </tr>
                  </thead>
                <!-- data block start -->
                <tbody>
                <#if competitorList?exists&&competitorList?size gt 0>
                    <#list competitorList as entity>
                        <tr data-id = "${entity.pkId?c}">
                            <td style="width: 100px;">${entity.pkId}</td>
                            <td style="width: 100px;">${entity.compName}</td>
                            <td style="width: 100px;">${entity.topProductMoney!"暂无数据"}</td>
                            <td style="width: 100px;">${entity.lowProductMoney!"暂无数据"}</td>
                            <td style="width: 100px;">${entity.topProductPeople!"暂无数据"}</td>
                            <td style="width: 100px;">${entity.topProductPeopleIdCoreprice!"暂无数据"}</td>
                            <td style="width: 100px;">${entity.lowProductPeople!"暂无数据"}</td>
                            <td style="width: 100px;">${entity.topCoreproce!"暂无数据"}</td>
                            <td style="width: 120px;">${entity.lowCoreprice!"暂无数据"}</td>
                            <td ><a href="/show/product/${entity.pkId}">查看详情</a></td>
                        </tr>
                    </#list>
                </#if>
                </tbody>
                <!-- data block end -->
            </table>
        </div>
    </div>
</div>
    </div>
</div>
</body>