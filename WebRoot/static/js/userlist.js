var userObj;

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeUse').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeUse').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){

	$(".viewUser").on("click",function(){
		var obj = $(this);
		var uid=obj.attr("userid");
		$.ajax({
			type:"GET",
			url:path+"/user/view/"+uid,
			//data:{uid:uid},
			dataType:"json",
			success:function(data){
				$("#userName").val(data.userName);
				if(data.gender==1){
					$("#gender").append("<option>男</option>");
				}else{
					$("#gender").append("<option>女</option>");
				}
				$("#birthday").val(data.birthday);
				$("#phone").val(data.phone);
				$("#address").val(data.address);
				if(data.userRole==1){
					$("#userRole").append("<option>系统管理员</option>");
				}else if(data.userRole==2){
					$("#userRole").append("<option>经理</option>");
				}else{
					$("#userRole").append("<option>普通员工</option>");
				}
				$("#myview").toggle();
				//console.log(data);
				//alert(data.userName);
			},
			error:function(){
				alert("服务器出现错误!");
			}
		});
		
	});
	
	$(".modifyUser").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/user/modify/"+ obj.attr("userid");
	});

	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteUser(userObj);
	});

	$(".deleteUser").on("click",function(){
		var obj=$(this);
		var uid=obj.attr("userid");
		$.ajax({
			type:"GET",
			url:path+"/user/userDel/"+uid,
			//data:{uid:uid},
			dataType:"json",
			success:function(data){
				if(data.delResult == "true"){//删除成功：移除删除行
					alert("删除成功");
					obj.parents("tr").remove();
				}else if(data.delResult == "false"){//删除失败
					alert("对不起，删除用户【"+obj.attr("username")+"】失败");
				}else if(data.delResult == "notexist"){
					alert("对不起，用户【"+obj.attr("username")+"】不存在");
				}
			},
			error:function(data){
				alert("对不起，删除失败");
			}
		});
	});
});
