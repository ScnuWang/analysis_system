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
                <!-- data block start -->
                  <thead>
                    <tr>
                      <th>原始项目编号</th>
                      <th>平台名称</th>
                      <th>产品名称</th>
                      <th>产品目标价格</th>
                      <th>产品当前金额</th>
                      <th>产品当前完成度</th>
                      <th>产品状态</th>
                    </tr>
                  </thead>
                <#if productList?exists&&productList?size gt 0>
                <tbody>
                    <#list productList as entity>
                        <tr data-id = "${entity.pkId?c}">
                            <td style="width: 100px;">${entity.originalId}</td>
                            <td style="width: 100px;">
                                <#if entity.website == 1>
                                                                                                    淘宝众筹
                                </#if>
                                <#if entity.website == 2>
                                                                                                    京东众筹
                                </#if>
                                <#if entity.website == 15>
                                                                                                    小米众筹
                                </#if>
                                <#if entity.website == 7>
                                                                                                    苏宁众筹
                                </#if>
                            </td>
                            <td style="width: 300px;">${entity.productName}</td>
                            <td style="width: 100px;">${entity.itemCorePrice}</td>
                            <td style="width: 100px;">${entity.currMoney}</td>
                            <td style="width: 100px;">${entity.finishPer}</td>
                            <td style="width: 100px;">${entity.productStatus}</td>
                            <td ><a href="/analysis/product/${entity.originalId}/${entity.website}">查看详情</a></td>
                        </tr>
                    </#list>
                </tbody>                                        
                </#if>
                <!-- data block end -->
            </table>
        </div>
    </div>
</div>
    </div>
</div>
</body>