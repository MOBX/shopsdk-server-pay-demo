/**
 * Simplified Chinese translation for bootstrap-datatable
 *
 */

;(function($){
    var language = {
        "processing": "......",
        "lengthMenu": "<select class='form-control input-xsmall'><option value='10'>10</option><option value='20'>20</option><option value='30'>30</option></select>条记录",
        "zeroRecords": "<p class='f16 align-center margin_bottom10 margin_top10 gary'><i class='fa fa-file-text f24 linght_gary'></i>&nbsp;&nbsp;没有数据显示</p>",
        "sEmptyTable": "<p class='f16 align-center margin_bottom10 margin_top10 gary'><i class='fa fa-file-text f24 linght_gary'></i>&nbsp;&nbsp;没有数据显示。</p>",
        "info": "总共_PAGES_ 页，显示第_START_ 到第 _END_ ，筛选之后得到 _TOTAL_ 条",
        "infoEmpty": "0条记录",
        "infoFiltered": "",
        "infoPostFix": "",
        "search": "<span class='label label-success'>搜索：</span>",
        "url": "",
        "paginate": {
            "previous": "上一页",
            "next": "下一页",
            "first": "第一页",
            "last": "最后"
        },
        "sLoadingRecords": "载入中..."
    };
    $.extend(true, $.fn.dataTable.defaults, {
        language: language,
        "searching": false,
        "serverSide": true,
        "dom": '<"toolbar">frtip',
        destroy:true,
        "ordering": false
    });
}(jQuery));