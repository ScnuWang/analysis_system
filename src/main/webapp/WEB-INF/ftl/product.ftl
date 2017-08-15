<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
    <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/modules/exporting.js"></script>
    <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
  </head>
    
  <body>
  该产品的目标价格为：${itemTargetPrice}
    <div id="all" ></div>
    <div id="price" ></div>
    <div id="supportpeople" ></div>
    <div id="money" ></div>
    <script type="text/javascript">
        $(function(){
                 $('#all').highcharts({
                chart: {
                    zoomType: 'xy'
                },
                title: {
                    text: '${productName}'
                },
                subtitle: {
                    text: '数据来源: 极客视界(www.geekview.cn)'
                },
                xAxis: [{
                    categories: [
                                    <#list updateDate as current>
                                        '${current}',
                                    </#list>
                                ],
                    crosshair: true
                }],
                yAxis: [{ // Primary yAxis
                    labels: {
                        format: '{value} %',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
                    title: {
                        text: '完成百分比',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
                    opposite: true
                }, { // Secondary yAxis
                    gridLineWidth: 0,
                    title: {
                        text: '支持人数',
                        style: {
                            color: Highcharts.getOptions().colors[1]
                        }
                    },
                    labels: {
                        format: '{value} 人',
                        style: {
                            color: Highcharts.getOptions().colors[1]
                        }
                    },
                    opposite: true
                }, { // Tertiary yAxis
                    gridLineWidth: 0,
                    title: {
                        text: '众筹金额',
                        style: {
                            color: Highcharts.getOptions().colors[2]
                        }
                    },
                    labels: {
                        format: '{value} RMB',
                        style: {
                            color: Highcharts.getOptions().colors[2]
                        }
                    }
                    
                }],
                tooltip: {
                    shared: true
                },
                legend: {
                    layout: 'vertical',
                    align: 'left',
                    x: 80,
                    verticalAlign: 'top',
                    y: 55,
                    floating: true,
                    backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
                },
                series: [{
                    name: '完成百分比',
                    type: 'spline',
                    yAxis: 0,
                    data: [
                        <#list finishPercent as current>
                             ${current},
                        </#list>
                    ],
                    tooltip: {
                        valueSuffix: ' %'
                    }
                }, {
                    name: '支持人数',
                    type: 'spline',
                    yAxis: 1,
                    data: [
                        <#list supportPerson as current>
                             ${current},
                        </#list>
                    ],
                    marker: {
                        enabled: false
                    },
                    dashStyle: 'shortdot',
                    tooltip: {
                        valueSuffix: ' 人'
                    }
                }, {
                    name: '众筹金额',
                    type: 'spline',
                    yAxis: 2,
                    data: [
                        <#list currentMoney as current>
                             ${current},
                        </#list>
                    ],
                    tooltip: {
                        valueSuffix: ' RMB'
                    }
                }]
            });
            
            
            
            $('#price').highcharts({
                chart:{
                    type:'line'
                },
                title:{
                    text: '完成百分比'
                },
                subtitle: {
                    text: '数据来源: 极客视界(www.geekview.cn)'
                },
                xAxis: {
                    categories: [
                                    <#list updateDate as current>
                                        '${current}',
                                    </#list>
                                ]
                },
                yAxis: {
                    title: {
                        text: '完成百分比 (%)'
                    }
                },
                plotOptions: {
                    line: {
                        dataLabels: {
                            enabled: true          // 开启数据标签
                        },
                        enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                    }
                },
                series: [ {
                    name: '完成百分比',
                    data: [
                        <#list finishPercent as current>
                             ${current },
                        </#list>
                    ]
                }]
            });
            
            
            $('#supportpeople').highcharts({
                chart:{
                    type:'line'
                },
                title:{
                    text: '支持人数'
                },
                subtitle: {
                    text: '数据来源: 极客视界(www.geekview.cn)'
                },
                xAxis: {
                    categories: [
                                    <#list updateDate as current>
                                        '${current}',
                                    </#list>
                                ]
                },
                yAxis: {
                    title: {
                        text: '支持人数(人) '
                    }
                },
                plotOptions: {
                    line: {
                        dataLabels: {
                            enabled: true          // 开启数据标签
                        },
                        enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                    }
                },
                series: [ {
                    name: '支持人数',
                    data: [
                        <#list supportPerson as current>
                             ${current },
                        </#list>
                    ]
                }]
            });
            
            $('#money').highcharts({
                chart:{
                    type:'line'
                },
                title:{
                    text: '金额'
                },
                subtitle: {
                    text: '数据来源: 极客视界(www.geekview.cn)'
                },
                xAxis: {
                    categories: [
                                    <#list updateDate as current>
                                        '${current}',
                                    </#list>
                                ]
                },
                yAxis: {
                    title: {
                        text: '人民币 (RMB)'
                    }
                },
                plotOptions: {
                    line: {
                        dataLabels: {
                            enabled: true          // 开启数据标签
                        },
                        enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                    }
                },
                series: [{
                    name: '金额',
                    data: [
                    <#list currentMoney as current>
                         ${current },
                    </#list>
                    ]
                }]
            });
        
        
        
        
        
        //产品项
        <#if itemList??>
            <#list itemList as items>
        var boarddiv = "<div id = "+${items_index}+"> </div>"; 
                $(document.body).append(boarddiv); 
                
               $("#"+${items_index}).highcharts({
                chart: {
                    zoomType: 'xy'
                },
                title: {
                    text: '${productName}'
                },
                subtitle: {
                    text: '数据来源: 极客视界(www.geekview.cn)'
                },
                xAxis: [{
                    categories: [
                                    <#list updateDate as current>
                                        '${current}',
                                    </#list>
                                ],
                    crosshair: true
                }],
                yAxis: [{ // Primary yAxis
                    labels: {
                        format: '{value} RMB',
                        style: {
                            color: Highcharts.getOptions().colors[1]
                        }
                    },
                    title: {
                        text: '产品项支持金额',
                        style: {
                            color: Highcharts.getOptions().colors[1]
                        }
                    }
                }, { // Secondary yAxis
                    gridLineWidth: 0,
                    title: {
                        text: '期望支持人数',
                        style: {
                            color: Highcharts.getOptions().colors[2]
                        }
                    },
                    labels: {
                        format: '{value} 人',
                        style: {
                            color: Highcharts.getOptions().colors[2]
                        }
                    },
                    opposite: true
                }, { // Tertiary yAxis
                    gridLineWidth: 0,
                    title: {
                        text: '实际支持人数',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
                    labels: {
                        format: '{value} RMB',
                        style: {
                            color: Highcharts.getOptions().colors[0]
                        }
                    },
                    opposite: true
                }],
                tooltip: {
                    shared: true
                },
                legend: {
                    layout: 'vertical',
                    align: 'left',
                    x: 80,
                    verticalAlign: 'top',
                    y: 55,
                    floating: true,
                    backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
                },
                series: [
                           {
                                name: '实际支持人数',
                                type: 'spline',
                                yAxis: 2,
                                data: [
                                    <#list items as curitem>
                                         <#list curitem as cur>
                                            <#if curitem_index == 0>
                                                ${cur?string("###0.0#")},
                                            </#if>
                                         </#list>
                                    </#list>
                                ],
                                tooltip: {
                                    valueSuffix: ' 人'
                                }
                            },
                           {
                                name: '产品项支持金额',
                                type: 'spline',
                                yAxis: 0,
                                data: [
                                    <#list items as curitem>
                                         <#list curitem as cur>
                                            <#if curitem_index == 1>
                                               ${cur?string("###0.0#")},
                                            </#if>
                                        </#list>
                                    </#list>
                                ],
                                tooltip: {
                                    valueSuffix: ' RMB'
                                }
                            },
                           {
                                name: '期望支持人数',
                                type: 'spline',
                                yAxis: 1,
                                data: [
                                <#list items as curitem>
                                     <#list curitem as cur>
                                        <#if curitem_index == 2>
                                           ${cur?string("###0.0#")},
                                        </#if>
                                    </#list>
                                </#list>
                                ],
                                marker: {
                                    enabled: false
                                },
                                dashStyle: 'shortdot',
                                tooltip: {
                                    valueSuffix: ' 人'
                                }
                          }
                     ]
                 });
            </#list>
        </#if>
        });     
    </script>
  </body>
</html>
