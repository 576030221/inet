$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'curriculum/inetclass/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '课名', name: 'classNameId', index: 'class_name_id', width: 80 },
			{ label: '开始时间', name: 'startTime', index: 'start_time', width: 80 },
			{ label: '持续时间', name: 'continuousTime', index: 'continuous_time', width: 80 },
			{ label: '教室', name: 'classroom', index: 'classroom', width: 80 },
			{ label: '状态', name: 'status', index: 'status', width: 80,formatter: function(value, options, row){
                    if (value == 0)
                        return '<span class="label label-success">上课中</span>';
                    else if(value == 2)
                        return '<span class="label label-danger">已结束</span>';
                    else if(value == 1)
                        return '<span class="label label-warning">未开始</span>';
                }},
			{ label: '创建时间', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '操作者', name: 'createUserAccountName', index: 'create_user_id', width: 80 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});


var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
        gradeList:null,
        classNameList:null,
		inetClass: {}
	},
    mounted:function(){
        // 初始化获取可选年级
        $.get(baseURL + "sys/constant/getGrade", function(r){
            vm.gradeList = r.list;
        });
        // console.log(this.gradeList)
        // console.log(laydate)

    },
	methods: {

	    getClassNameList:function(event){
	        console.log(vm.inetClass.gradeNumber)
            // 初始化获取可选年级
            $.get(baseURL + "curriculum/inetclassname/listByGradeNumber?gradeNumber=" + vm.inetClass.gradeNumber, function(r){
                vm.classNameList = r.list;
                console.log(vm.classNameList)
            });
        },
        getStartTime:function(){
            laydate.render({
                elem: "#startTimeInput", //指定元素
                type: 'datetime',
                format:'yyyy-MM-dd HH:mm:ss',
                trigger: 'click',
                done:function(value, date, endDate){
                    vm.inetClass.startTime = value;
                    console.log(value)
                }
            });
        },
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.inetClass = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.inetClass.id == null ? "curriculum/inetclass/save" : "curriculum/inetclass/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.inetClass),
                    success: function(r){
                        if(r.code === 0){
                             layer.msg("操作成功", {icon: 1});
                             vm.reload();
                             $('#btnSaveOrUpdate').button('reset');
                             $('#btnSaveOrUpdate').dequeue();
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
               if(!lock) {
                    lock = true;
		            $.ajax({
                        type: "POST",
                        url: baseURL + "curriculum/inetclass/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
                        success: function(r){
                            if(r.code == 0){
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
				    });
			    }
             }, function(){
             });
		},
		getInfo: function(id){
			$.get(baseURL + "curriculum/inetclass/info/"+id, function(r){
                vm.inetClass = r.inetClass;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});