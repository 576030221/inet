$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/user/list?status=2',
        datatype: "json",
        colModel: [
            { label: '学号', name: 'username', width: 75 },
            { label: '姓名', name: 'accountName', width: 75 },
            { label: 'QQ号', name: 'qq', sortable: false, width: 75 },
			{ label: '年级', name: 'gradeNumber', width: 90 },
			{ label: '申请时间', name: 'createTime', width: 100 },
			{ label: '状态', name: 'status', width: 60, formatter: function(value, options, row){
                    if (value == 1)
                        return '<span class="label label-success">正常</span>';
                    else if(value == 0)
                        return '<span class="label label-danger">禁用</span>';
                    else if(value == 2)
                        return '<span class="label label-warning">待审核</span>';
                    else if(value == 3)
                        return '<span class="label label-danger">驳回</span>';
                    else if(value == 4)
                        return '<span class="label label-danger">退社</span>';

			}},
            {
                label: '操作', name: 'remarks', index: 'remarks', width: 130, formatter: function (value, options, row) {
                    var htmlStr;
                    htmlStr = '<a class="btn btn-primary" onclick="vm.yes(' + '\'' + row.userId + '\'' + ')"><i class="fa fa-pencil-square-o"></i>&nbsp;通过 </a>'
                    htmlStr = htmlStr + ' <a class="btn btn-primary" onclick="vm.no(' + row.userId + ')"><i class="fa fa-trash-o"></i>&nbsp;驳回</a>';
                    return htmlStr;
                }
            }
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
var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};
var ztree;

var vm = new Vue({
    el:'#rrapp',
    data:{
        queryData:{
            username: null,
            status:"2",
        },
        showList: true,
        title:null,
        roleList:{},
        user:{
            status:1,
            deptId:null,
            deptName:null,
            roleIdList:[],
            accountName:null
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.roleList = {};
            vm.user = {deptName:null, deptId:null, status:1, roleIdList:[]};

            //获取角色信息
            this.getRoleList();

            vm.getDept();
        },
        getDept: function(){
            //加载部门树
            $.get(baseURL + "sys/dept/list", function(r){
                ztree = $.fn.zTree.init($("#deptTree"), setting, r);
                var node = ztree.getNodeByParam("deptId", vm.user.deptId);
                if(node != null){
                    ztree.selectNode(node);

                    vm.user.deptName = node.name;
                }
            })
        },
        update: function () {
            var userId = getSelectedRow();
            if(userId == null){
                return ;
            }

            vm.showList = false;
            vm.title = "修改";

            vm.getUser(userId);
            //获取角色信息
            this.getRoleList();
        },
        permissions: function () {
            var userId = getSelectedRow();
            if(userId == null){
                return ;
            }

            window.location.href=baseURL+"sys/permissions/index/"+userId;
        },
        /**
         * 审核通过
         * @param userId
         */
        yes: function (userId) {
            $.ajax({
                type: "POST",
                url: baseURL + "sys/user/examine",
                // contentType: "application/json",
                data: {
                    "userId" : userId,
                    "status" : "1"
                },
                success: function(r){
                    if(r.code == 0){
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        /**
         * 驳回
         * @param userId
         */
        no: function (userId) {
            $.ajax({
                type: "POST",
                url: baseURL + "sys/user/examine",
                // contentType: "application/json",
                data: {
                    "userId" : userId,
                    "status" : "3"
                },
                success: function(r){
                    if(r.code == 0){
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        del: function () {
            var userIds = getSelectedRows();
            if(userIds == null){
                return ;
            }

            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "sys/user/delete",
                    contentType: "application/json",
                    data: JSON.stringify(userIds),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(){
                                vm.reload();
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function () {
            var url = vm.user.userId == null ? "sys/user/save" : "sys/user/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.user),
                success: function(r){
                    if(r.code === 0){
                        alert('操作成功', function(){
                            vm.reload();
                        });
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        getUser: function(userId){
            $.get(baseURL + "sys/user/info/"+userId, function(r){
                vm.user = r.user;
                vm.user.password = null;

                vm.getDept();
            });
        },
        getRoleList: function(){
            $.get(baseURL + "sys/role/select", function(r){
                vm.roleList = r.list;
            });
        },
        deptTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择部门",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级部门
                    vm.user.deptId = node[0].deptId;
                    vm.user.deptName = node[0].name;

                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:vm.queryData,
                page:page
            }).trigger("reloadGrid");
        }
    }
});