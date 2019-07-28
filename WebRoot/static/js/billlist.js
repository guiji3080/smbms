var billObj;

//订单管理页面上点击删除按钮弹出删除框(billlist.jsp)

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeBi').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeBi').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){
	$(".viewBill").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/jsp/bill.do?method=view&billid="+ obj.attr("billid");
	});
	
	$(".modifyBill").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/jsp/bill.do?method=modify&billid="+ obj.attr("billid");
	});
	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteBill(billObj);
	});	
	
$(".deleteBill").on("click",function(){
	var obj = $(this);
	var billid=obj.attr("billid");
			$.ajax({
				type:"GET",
				url:path+"/bill/delbill/"+billid,
				//data:{billid:obj.attr("billid")},
				dataType:"json",
				success:function(data){
					if(data.delResult == "true"){//删除成功：移除删除行
						alert("删除成功");
						obj.parents("tr").remove();
					}else if(data.delResult == "false"){//删除失败
						alert("对不起，删除订单【"+obj.attr("billcc")+"】失败");
					}else if(data.delResult == "notexist"){
						alert("对不起，订单【"+obj.attr("billcc")+"】不存在");
					}
				},
				error:function(data){
					alert("对不起，删除失败");
				}
			});
		});
	});
