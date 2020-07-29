$(function () {
    bootbox.setDefaults("locale","zh_CN");
    //修改
    $("#roleTable tbody").on("click", ".modify", modify);
    $("#roleTable tbody").on("click", ".del", del);
    $("#roleTable tbody").on("click", ".available", modifyAvailable);
    $("#roleTable tbody").on("click", ".unAvailable", modifyUnAvailable);

    $("#confirmModifyRole").click(confirmModify)
    $("#confirmAddRole").click(add);

    getAppInfo();
})

function add() {
    var roleName=$("#add-roleName").val();
    var descr=$("#add-descr").val();
    var appId=$("#add-appId").val();

    if(!checkHasApp()){
        swalFail("没有创建应用",null);
        return;
    }

    if(roleName == "" || roleName == undefined || roleName == null){
        swalFail("角色名称不能为空",null)
        return;
    }

    ajaxSetup("post","/role/add","role="+roleName+"&descr="+descr+"&appId="+appId,ajaxCallBack)
}
function modify() {
    var id=$(this).attr("role");
    var roleName=$(this).attr("roleName");
    var descr=$(this).attr("descr");
    var appId=$(this).attr("app");

    $("#roleName").val(roleName);

    if(descr!=null&&descr!="null"){
        $("#descr").val(descr);
    }

    $("#appId").val(appId).trigger("change");

    $("#modify-role").modal("show");
    $("#roleId").val(id);
}

function confirmModify () {
    var roleName=$("#roleName").val();
    var descr=$("#descr").val();
    var roleId=$("#roleId").val();
    var appId=$("#appId").val();

    $.ajax({
               type:"post",
               url:"/role/modify",
               dataType:"json",
               data:{
                   id:roleId,
                   role:roleName,
                   descr:descr,
                   appId:appId,
               },
               success:function (data) {
                   if(data.success){
                       swal({
                                title: "修改成功",
                                confirmButtonColor: "#66BB6A",
                                type: "success"
                            });

                       //刷新
                       $("#modify-role").modal("hide");
                       window.location.reload();
                   }else {
                       $("#modify-role").modal("hide");
                       swal({
                                title: "修改失败",
                                text:data.msg,
                                confirmButtonColor: "#EF5350",
                                type: "error"
                            });
                   }
               }
           })
}

function del() {
    var id=$(this).attr("role");
    bootbox.confirm("确定删除?", function(result) {
        if(result){
            $.ajax({
                       type:"post",
                       url:"/role/delete",
                       dataType:"json",
                       data:{
                           id:id
                       },
                       success:function (data) {
                           if(data.success){
                               swal({
                                        title: "删除成功",
                                        confirmButtonColor: "#66BB6A",
                                        type: "success"
                                    });

                               //刷新
                               window.location.reload();
                           }else {
                               swal({
                                        title: "删除失败",
                                        text:data.msg,
                                        confirmButtonColor: "#EF5350",
                                        type: "error"
                                    });
                           }
                       }
                   })
        }
    });
}

function modifyAvailable() {
    var id=$(this).attr("role");
    bootbox.confirm("确定设置为可用?", function(result) {
        if(result){
            $.ajax({
                       type:"post",
                       url:"/role/setAvailable",
                       dataType:"json",
                       data:{
                           id:id
                       },
                       success:function (data) {
                           if(data.success){
                               swal({
                                        title: "设置成功",
                                        confirmButtonColor: "#66BB6A",
                                        type: "success"
                                    });

                               //刷新
                               window.location.reload();
                           }else {
                               swal({
                                        title: "设置失败",
                                        text:data.msg,
                                        confirmButtonColor: "#EF5350",
                                        type: "error"
                                    });
                           }
                       }
                   })
        }
    });
}

function modifyUnAvailable() {
    var id=$(this).attr("role");
    bootbox.confirm("确定设置为不可用?", function(result) {
        if(result){
            $.ajax({
                       type:"post",
                       url:"/role/setUnAvailable",
                       dataType:"json",
                       data:{
                           id:id
                       },
                       success:function (data) {
                           if(data.success){
                               swal({
                                        title: "设置成功",
                                        confirmButtonColor: "#66BB6A",
                                        type: "success"
                                    });

                               //刷新
                               window.location.reload();
                           }else {
                               swal({
                                        title: "设置失败",
                                        text:data.msg,
                                        confirmButtonColor: "#EF5350",
                                        type: "error"
                                    });
                           }
                       }
                   })
        }
    });
}

function getAppInfo() {
    $.ajax({
               async:false,
               type:"post",
               url:"/app/getAppInfo",
               dataType:"json",
               success:function (data) {
                   if(data.success) {
                       $("#add-appId").select2({
                                                   language: 'zh-CN',
                                                   data: data.result,
                                                   placeholder:'请选择应用',
                                               })
                       $("#appId").select2({
                                               language: 'zh-CN',
                                               data: data.result,
                                               placeholder:'请选择应用',
                                           })
                   }
               }
           })
}


function checkHasApp () {
    var hasApp=false;
    $.ajax({
               async:false,
               type:"post",
               url:"/app/count",
               dataType:"json",
               success:function (data) {
                   if(data.success){
                       if(data.result>0){
                           hasApp=true;
                       }
                   }
               }
           })
    return hasApp;
}
