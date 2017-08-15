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
                      <th>一级分类编号</th>
                      <th>一级分类名称</th>
                      <th>行业累计资金</th>
                      <th>行业累计资金排名</th>
                      <th>行业累计项目数</th>
                      <th>行业累计项目排名</th>
                      <th>行业累计支持人数</th>
                      <th>行业累计支持人数排名</th>
                    </tr>
                  </thead>
                <!-- data block start -->
                <tbody>
                <#if categoryList?exists&&categoryList?size gt 0>
                    <#list categoryList as entity>
                        <tr data-id = "${entity.pkId?c}">
                            <td style="width: 100px;">${entity.pkId}</td>
                            <td style="width: 100px;">${entity.categoryName}</td>
                            <td style="width: 140px;">${entity.totalMoney!0.00}</td>
                            <td style="width: 140px;">${entity.totalMoneyRank!0}</td>
                            <td style="width: 140px;">${entity.totalProduct!0}</td>
                            <td style="width: 140px;">${entity.totalProductRank!0}</td>
                            <td style="width: 140px;">${entity.totalSupport!0}</td>
                            <td style="width: 160px;">${entity.totalSupportRank!0}</td>
                            <td style="width: 90px;"><a href="/show/category/${entity.pkId}">查看详情</a></td>
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